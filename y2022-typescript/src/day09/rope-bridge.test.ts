import { part1, part2 } from './rope-bridge';
import fs from 'fs';
import { Pos } from './pos';
import { Grid } from './grid';

let sample: string;
let input: string;

beforeAll(() => {
  sample = fs.readFileSync(__dirname + '/sample.txt', 'utf-8');
  input = fs.readFileSync(__dirname + '/input.txt', 'utf-8');
});

test('part 1 sample', () => {
  expect(part1(sample)).toBe(13);
});

test('part 1', () => {
  expect(part1(input)).toBe(5513);
});

test('part 2 sample', () => {
  expect(part2(sample)).toBe(1);
});

test('part 2', () => {
  expect(part2(input)).toBe(2427);
});

describe('Grid', () => {
  test('counts diff positions', () => {
    let grid = new Grid();

    grid.move(new Pos(0, 0));
    grid.move(new Pos(0, 0));
    grid.move(new Pos(1, 2));

    expect(grid.amountOfPositionsTailVisitedAtLeastOnce()).toEqual(2);
  });
});
