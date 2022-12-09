import { Pos } from './pos';

export type Direction = 'R' | 'L' | 'U' | 'D';

export class Move {
  private readonly direction: Direction;
  private readonly amount: number;

  constructor(direction: Direction, amount: number) {
    this.direction = direction;
    this.amount = amount;
  }

  steps(): Pos[] {
    switch (this.direction) {
      case 'R':
        return [...Array(this.amount).keys()].map(() => new Pos(1, 0));
      case 'L':
        return [...Array(this.amount).keys()].map(() => new Pos(-1, 0));
      case 'U':
        return [...Array(this.amount).keys()].map(() => new Pos(0, 1));
      case 'D':
        return [...Array(this.amount).keys()].map(() => new Pos(0, -1));
    }
  }
}
