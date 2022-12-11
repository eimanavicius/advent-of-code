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

class CRT implements Bus {
  private sprite: string = '';
  private display: string[] = ['', '', '', '', '', '',];
  private nextNewLine: number = this.wide;

  constructor(private readonly wide: number) {
  }

  onBeforeInstruction(_: number, X: number): void {
    this.sprite = CRT.makeSprite(X);
  }

  onBeforeCycle(cycle: number, X: number): void {
    if (cycle === this.nextNewLine) {
      this.nextNewLine += this.wide;
    }
    let column = cycle % this.wide;
    let row = Math.floor((this.nextNewLine - this.wide) / this.wide);
    this.display[row] += this.sprite[column] === '#' ? '#' : '.';
  }

  private static makeSprite(x: number): string {
    const start = (x - 1) % 40;
    const sprite = [...Array(40).keys()].map(() => '.');

    sprite[start] = '#';
    sprite[start + 1] = '#';
    sprite[start + 2] = '#';

    return sprite.join('');
  }

  onAfterCycle(cycle: number, X: number): void {
  }

  draw(): string {
    return this.display.map(x => x).join('\n');
  }
}

class Signals implements Bus {
  private snapshots: number[][] = [];

  constructor(private nextCapture: number, private readonly step: number = 40) {
  }

  onAfterCycle(cycle: number, X: number): void {
    if (cycle === this.nextCapture) {
      this.snapshots.push([this.nextCapture, X]);
      this.nextCapture += this.step;
    }
  }

  onBeforeCycle(cycle: number, X: number): void {
    // do nothing
  }

  onBeforeInstruction(cycle: number, X: number): void {
    // do nothing
  }

  sumOfSixSignalStrengths(): number {
    return this.snapshots.slice(0, 6)
      .reduce((sum, capture) => sum + (capture[0] * capture[1]), 0);
  }
}

interface Bus {
  onBeforeInstruction(cycle: number, X: number): void;

  onBeforeCycle(cycle: number, X: number): void;

  onAfterCycle(cycle: number, X: number): void;
}

class CPU {
  constructor(
    private readonly busses: Bus[] = [],
    private X: number = 1,
    private cycle = 0,
  ) {
  }

  run(instruction: Instruction): CPU {
    this.trigger('onBeforeInstruction');
    for (let i = 0; i < instruction.cycles; i++) {
      this.trigger('onBeforeCycle');
      this.cycle++;
      this.trigger('onAfterCycle');
    }
    this.X = instruction.apply(this.X);
    return this;
  }

  private trigger(event: 'onBeforeCycle' | 'onAfterCycle' | 'onBeforeInstruction'): void {
    this.busses.forEach(value => value[event](this.cycle, this.X));
  }
}

function parseInstructions(input: string): Instruction[] {
  return input.split(/\r?\n/)
    .map(x => x.split(' '))
    .map((x): Instruction => x[0] === 'addx' ? new Addx(parseInt(x[1])) : new Noop());
}

export function part1(input: string) {
  let signals = new Signals(20);
  let cpu = new CPU([signals]);

  parseInstructions(input).forEach(cpu.run, cpu);

  return signals.sumOfSixSignalStrengths();
}

export function part2(input: string) {
  let display = new CRT(40);
  let cpu = new CPU([display]);

  parseInstructions(input).forEach(cpu.run, cpu);

  return display.draw();
}
