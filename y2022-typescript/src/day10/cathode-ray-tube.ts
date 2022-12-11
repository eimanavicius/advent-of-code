interface Instruction {
  readonly cycles: number;

  apply(registry: number): number;
}

class Noop implements Instruction {
  readonly cycles: number = 1;

  apply(registry: number): number {
    return registry;
  }
}

class Addx implements Instruction {
  readonly cycles: number = 2;

  constructor(public readonly number: number) {
  }

  apply(registry: number): number {
    return registry + this.number;
  }
}

class CPU {
  private cycle = 0;
  private snapshots: number[][] = [];
  private nextCapture = 20;

  constructor(private X: number) {
  }

  run(instruction: Instruction): CPU {
    for (let i = 0; i < instruction.cycles; i++) {
      this.cycle++;
      if (this.cycle === this.nextCapture) {
        this.snapshots.push([this.nextCapture, this.X]);
        this.nextCapture += 40;
      }
    }
    this.X = instruction.apply(this.X);
    return this;
  }

  sumOfSixSignalStrengths(): number {
    return this.snapshots.slice(0, 6)
      .reduce((sum, capture) => sum + (capture[0] * capture[1]), 0);
  }
}

export function part1(input: string) {
  return input.split(/\r?\n/)
    .map(x => x.split(' '))
    .map((x): Instruction => x[0] === 'addx' ? new Addx(parseInt(x[1])) : new Noop())
    .reduce((cpu: CPU, instruction: Instruction) => cpu.run(instruction), new CPU(1))
    .sumOfSixSignalStrengths();
}

export function part2(input: string) {
  return input.split(/\r?\n/)
    .reduce((count) => count + 2, 0);
}
