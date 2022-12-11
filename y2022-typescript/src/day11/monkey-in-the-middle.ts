class Monkey {
  private items: number[];
  private inspections: number = 0;
  private readonly operation: (a: number) => number;
  private readonly test: (worryLevel: number) => number;

  constructor(items: number[], operation: (a: number) => number, test: (worryLevel: number) => number) {
    this.items = items;
    this.operation = operation;
    this.test = test;
  }

  turn(): ThrownItem[] {
    let thrownItems = this.items.map(value => {
      const worryLevel = Math.floor(this.operation(value) / 3);
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

function parseOperation(line: string): (a: number) => number {
  const sign = line[23];
  const part2 = line.split(sign + ' ')[1];
  if (part2 === "old") {
    return sign === '*' ? (a: number) => a * a : (a: number) => a + a;
  }
  const amount = parseInt(part2);
  return sign === '*' ? (a: number) => a * amount : (a: number) => a + amount;
}

export function part1(input: string) {
  let ms = input.split(/\n\n/)
    .map(m => m.split('\n'))
    .reduce((monkeys, lines, index) => {

      const items = lines[1].split(': ')[1].split(', ').map((x) => parseInt(x));

      const operation = parseOperation(lines[2]);

      const divider = parseInt(lines[3].split(' ').pop() as string);
      const trueIndex = parseInt(lines[4].split(' ').pop() as string);
      const falseIndex = parseInt(lines[5].split(' ').pop() as string);
      const test = (worryLevel: number) => worryLevel % divider == 0 ? trueIndex : falseIndex;

      monkeys[index] = new Monkey(items, operation, test);
      return monkeys;
    }, {} as { [key: number]: Monkey });

  for (let i = 0; i < 20; i++) {
    for (let key in ms) {
      let thrownItems = ms[key].turn();
      thrownItems.forEach((x) => {
        ms[x.monkey].catch(x.item);
      });
    }
  }

  const sortMonkeys = [] as Monkey[];
  for (let key in ms) {
    sortMonkeys.push(ms[key]);
  }
  sortMonkeys.sort((a, b) => a.activity() - b.activity()).reverse();

  return sortMonkeys[0].activity() * sortMonkeys[1].activity();
}

export function part2(input: string) {
  return input.split(/\r?\n/)
    .reduce((count) => count + 2, 0);
}
