import { Pos } from './pos';
import { Grid } from './grid';
import { Direction, Move } from './move';

function parseMovements(input: string): Move[] {
  return input.split(/\r?\n/)
    .map(x => x.split(' '))
    .map((x): Move => new Move(x[0] as Direction, parseInt(x[1])));
}

export function part1(input: string) {
  return parseMovements(input)
    .reduce(
      (grid, move) => move.steps().reduce((g: Grid, m: Pos) => grid.move(m), grid),
      new Grid()
    )
    .amountOfPositionsTailVisitedAtLeastOnce();
}

export function part2(input: string) {
  return -1;
}
