export interface Instruction {
  readonly cycles: number;

  apply(registry: number): number;
}

export interface Bus {
  onBeforeInstruction(cycle: number, X: number): void;

  onBeforeCycle(cycle: number, X: number): void;

  onAfterCycle(cycle: number, X: number): void;
}

export class CPU {
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
