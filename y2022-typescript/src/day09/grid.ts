import { Pos } from './pos';

export class Grid {
  private readonly knots: Pos[];
  private visited: Pos[];

  constructor(knotsAmount: number = 2) {
    if (knotsAmount < 2) throw new Error('Has to have at least 2 knots');
    this.knots = [...Array(knotsAmount).keys()].map(() => new Pos(0, 0));
    this.visited = [new Pos(0, 0)];
  }

  amountOfPositionsTailVisitedAtLeastOnce(): number {
    return this.visited
      .reduce((set, pos) => {
        set.findIndex(p => p.equalTo(pos)) > -1 || set.push(pos);
        return set;
      }, [] as Pos[])
      .length;
  }

  move(motion: Pos): Grid {
    this.knots[0] = this.knots[0].move(motion);
    for (let i = 1; i < this.knots.length; i++) {
      this.knots[i] = this.knots[i].moveCloserTo(this.knots[i-1]);
    }
    this.visited.push(this.knots[this.knots.length - 1]);
    return this;
  }
}
