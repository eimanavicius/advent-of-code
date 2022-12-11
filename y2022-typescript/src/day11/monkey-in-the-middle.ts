type Operation = (a: number) => number;
type Relief = (value: number, operation: Operation) => number;

interface Tester {
  (worryLevel: number): number;

  divider: number;
}

class MonkeyInTheMiddle {
  private listOfMonkeys: Monkey[] = [];

  private mapOfMonkeys: { [key: number]: Monkey } = {};

  constructor(public readonly relief: Relief) {
  }

  static fromString(input: string, relief: Relief): MonkeyInTheMiddle {
    return input.split(/\n\n/)
      .map(m => m.split('\n'))
      .reduce((monkeys, lines, index) => {

        const items = lines[1].split(': ')[1].split(', ').map((x) => parseInt(x));

        const operation = parseOperation(lines[2]);

        const divider = parseInt(lines[3].split(' ').pop() as string);
        const trueIndex = parseInt(lines[4].split(' ').pop() as string);
        const falseIndex = parseInt(lines[5].split(' ').pop() as string);
        const test = (worryLevel: number) => worryLevel % divider == 0 ? trueIndex : falseIndex;
        test.divider = divider;

        monkeys.add(
          index,
          new Monkey(
            items,
            operation,
            test,
            (x: number, operation: Operation) => monkeys.relief(x, operation)
          )
        );

        return monkeys;
      }, new MonkeyInTheMiddle(relief));
  }

  private add(index: number, monkey: Monkey): void {
    this.listOfMonkeys.push(monkey);
    this.mapOfMonkeys[index] = monkey;
  }

  play(rounds: number): void {
    for (let i = 0; i < rounds; i++) {
      for (let key in this.mapOfMonkeys) {
        let thrownItems = this.mapOfMonkeys[key].turn();
        thrownItems.forEach((x) => {
          this.mapOfMonkeys[x.monkey].catch(x.item);
        });
      }
    }
  }

  top2ActiveMonkeys(): number {
    this.listOfMonkeys
      .sort((a, b) => a.activity() - b.activity())
      .reverse();

    return this.listOfMonkeys[0].activity() * this.listOfMonkeys[1].activity();
  }

  forEachMonkey(callback: (monkey: Monkey) => void): void {
    this.listOfMonkeys.forEach(callback);
  }
}

class Monkey {
  private inspections: number = 0;

  constructor(
    private items: number[],
    public readonly operation: Operation,
    public readonly test: Tester,
    public readonly relief: (x: number, operation: Operation) => number
  ) {
  }

  turn(): ThrownItem[] {
    let thrownItems = this.items.map(value => {
      let worryLevel = this.relief(value, this.operation);
      this.inspections++;
      return new ThrownItem(this.test(worryLevel), worryLevel);
    });
    this.items = [];
    return thrownItems;
  }

  catch(item: number): void {
    this.items.push(item);
  }

  activity() {
    return this.inspections;
  }
}

class ThrownItem {
  public readonly monkey: number;
  public readonly item: number;

  constructor(monkey: number, item: number) {
    this.monkey = monkey;
    this.item = item;
  }
}

function parseOperation(line: string): Operation {
  const sign = line[23];
  const part2 = line.split(sign + ' ')[1];
  if (part2 === 'old') {
    return sign === '*' ? (a: number) => a * a : (a: number) => a + a;
  }
  const amount = parseInt(part2);
  return sign === '*' ? (a: number) => a * amount : (a: number) => a + amount;
}

export function part1(input: string) {
  let ms = MonkeyInTheMiddle.fromString(
    input,
    (value: number, operation: Operation): number => Math.floor(operation(value) / 3)
  );

  ms.play(20);

  return ms.top2ActiveMonkeys();
}

class Reliever {
  private manageableRelief = 1;

  relief(): Relief {
    return (value: number, operation: Operation): number => {
      return operation(value % this.manageableRelief);
    };
  }

  trackRelief(): (monkey: Monkey) => void {
    return (monkey: Monkey) => {
      this.manageableRelief *= monkey.test.divider || 1;
    };
  }
}

export function part2(input: string) {
  const reliever = new Reliever();

  let ms = MonkeyInTheMiddle.fromString(input, reliever.relief());
  ms.forEachMonkey(reliever.trackRelief());

  ms.play(10000);

  return ms.top2ActiveMonkeys();
}
