import { Instruction } from './cpu';

export class Noop implements Instruction {
  readonly cycles: number = 1;

  apply(registry: number): number {
    return registry;
  }
}

export class Addx implements Instruction {
  readonly cycles: number = 2;

  constructor(public readonly number: number) {
  }

  apply(registry: number): number {
    return registry + this.number;
  }
}
