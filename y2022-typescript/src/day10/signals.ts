import { Bus } from './cpu';

export class Signals implements Bus {
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
