import { part1, part2, toPriority, toTrioGroups } from './rucksack-reorganization';
import fs from 'fs';

let sample: string;
let input: string;

beforeAll(() => {
  sample = fs.readFileSync('src/day03/sample.txt', 'utf-8');
  input = fs.readFileSync('src/day03/input.txt', 'utf-8');
});

test('part 1 sample', () => {
  expect(part1(sample)).toBe(157);
});

test('part 1', () => {
  expect(part1(input)).toBe(7824);
});

test('part 2 sample', () => {
  expect(part2(sample)).toBe(70);
});

test('part 2', () => {
  expect(part2(input)).toBe(2798);
});

describe('Priority conversion', () => {
  it.each([
    ['a', 1],
    ['n', 14],
    ['z', 26],
    ['A', 27],
    ['E', 31],
    ['Z', 52]
  ])('%p to %p', (letter: string, priority: number) => {
    expect(toPriority(letter)).toBe(priority);
  });
});

describe('Group by 3', () => {
  test('init group before adding item to group index', () => {
    expect(toTrioGroups([], 'items', 0)[0]).toBeDefined();
    expect(toTrioGroups([], 'items', 3)[1]).toBeDefined();
  });

  test('add item to group index', () => {
    expect(toTrioGroups([], 'items', 0)).toEqual([['items']]);
    expect(toTrioGroups([[], ['existing']], 'items', 3)).toEqual([[], ['existing', 'items']]);
  });

  test('reduces array to groups of 3 items', () => {
    expect(['a', 'b', 'c'].reduce(toTrioGroups, [] as string[][]))
      .toEqual([['a', 'b', 'c']]);

    expect(['a', 'b', 'c', 'd', 'e', 'f'].reduce(toTrioGroups, [] as string[][]))
      .toEqual([['a', 'b', 'c'], ['d', 'e', 'f']]);
  });
});
