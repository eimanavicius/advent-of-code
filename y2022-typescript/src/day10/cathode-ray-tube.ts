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
  private readonly emptySprite = '........................................';
  private sprite: string = '###.....................................';
  private display: string[] = ['', '', '', '', '', '',];

  constructor(
    private X: number,
    private nextCapture: number
  ) {
  }

  run(instruction: Instruction): CPU {
    this.sprite = this.makeSprite();
    for (let i = 0; i < instruction.cycles; i++) {
      let column = this.cycle % 40;
      let row = Math.floor((this.nextCapture-40) / 40);
      this.display[row] += this.sprite[column] === '#' ? '#' : '.';
      this.cycle++;
      if (this.cycle === this.nextCapture) {
        this.snapshots.push([this.nextCapture, this.X]);
        this.nextCapture += 40;
      }
    }
    this.X = instruction.apply(this.X);
    return this;
  }

  private makeSprite(): string {
    const sprite = this.emptySprite.split('');
    const start = (this.X-1) % 40;

    sprite[start] = '#';
    sprite[start + 1] = '#';
    sprite[start + 2] = '#';

    return sprite.join('');
  }

  sumOfSixSignalStrengths(): number {
    return this.snapshots.slice(0, 6)
      .reduce((sum, capture) => sum + (capture[0] * capture[1]), 0);
  }

  drawDisplay(): string {
    return this.display.map(x => x).join('\n');
  }
}

function parseInstructions(input: string): Instruction[] {
  return input.split(/\r?\n/)
    .map(x => x.split(' '))
    .map((x): Instruction => x[0] === 'addx' ? new Addx(parseInt(x[1])) : new Noop());
}

export function part1(input: string) {
  return parseInstructions(input)
    .reduce((cpu: CPU, instruction: Instruction) => cpu.run(instruction), new CPU(1, 20))
    .sumOfSixSignalStrengths();
}

export function part2(input: string) {
  return parseInstructions(input)
    .reduce((cpu: CPU, instruction: Instruction) => cpu.run(instruction), new CPU(1, 40))
    .drawDisplay();
}
