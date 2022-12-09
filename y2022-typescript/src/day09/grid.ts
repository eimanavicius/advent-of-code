import { Pos } from './pos';

export class Grid {
  private head: Pos = new Pos(0, 0);
  private tail: Pos = new Pos(0, 0);
  private visited: Pos[] = [this.tail];

  amountOfPositionsTailVisitedAtLeastOnce(): number {
    return this.visited
      .reduce((set, pos) => {
        set.findIndex(p => p.equalTo(pos)) > -1 || set.push(pos);
        return set;
      }, [] as Pos[])
      .length;
  }

  move(motion: Pos): Grid {
    this.head = this.head.move(motion);
    this.tail = this.tail.moveCloserTo(this.head);
    this.visited.push(this.tail);
    return this;
  }
}
