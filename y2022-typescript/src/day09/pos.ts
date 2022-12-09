export class Pos {
  public readonly x: number;
  public readonly y: number;

  constructor(x: number, y: number) {
    this.x = x;
    this.y = y;
  }

  move(move: Pos): Pos {
    return new Pos(this.x + move.x, this.y + move.y);
  }

  moveCloserTo(head: Pos): Pos {
    if (head.equalTo(this)) {
      return this;
    }
    const top = new Pos(0, 1);
    const tr = new Pos(1, 1);
    const right = new Pos(1, 0);
    const rb = new Pos(1, -1);
    const bottom = new Pos(0, -1);
    const bl = new Pos(-1, -1);
    const left = new Pos(-1, 0);
    const lt = new Pos(-1, 1);

    const adjacent = [top, right, bottom, left, tr, rb, bl, lt];
    let minD: number = this.distance(head);
    let minP: Pos = this;
    for (let movement of adjacent) {
      // if head is close, stop
      if (head.equalTo(this.move(movement))) {
        return this;
      }

      // count distance, to define next move
      let nextTail = this.move(movement);
      let distance = nextTail.distance(head);
      if (distance < minD) {
        minD = distance;
        minP = nextTail;
      }
    }

    return minP;
  }

  equalTo(pos: Pos): boolean {
    return this.x === pos.x && this.y === pos.y;
  }

  distance(that: Pos): number {
    return Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2));
  }
}
