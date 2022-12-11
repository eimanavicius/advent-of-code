import { part1, part2 } from './cathode-ray-tube';
import fs from 'fs';

let sample: string;
let input: string;

beforeAll(() => {
  sample = fs.readFileSync(__dirname + '/sample.txt', 'utf-8');
  input = fs.readFileSync(__dirname + '/input.txt', 'utf-8');
});

test('part 1 small sample', () => {
  expect(part1(`noop
addx 3
addx -5`)).toBe(0);
});

test('part 1 sample', () => {
  expect(part1(sample)).toBe(13140);
});

test('part 1', () => {
  expect(part1(input)).toBe(13520);
});

test('part 2 sample', () => {
  expect(part2(sample)).toBe(`##..##..##..##..##..##..##..##..##..##..
###...###...###...###...###...###...###.
####....####....####....####....####....
#####.....#####.....#####.....#####.....
######......######......######......####
#######.......#######.......#######.....`);
});

test('part 2', () => {
  expect(part2(input)).toBe(`###...##..###..#..#.###..####..##..###..
#..#.#..#.#..#.#..#.#..#.#....#..#.#..#.
#..#.#....#..#.####.###..###..#..#.###..
###..#.##.###..#..#.#..#.#....####.#..#.
#....#..#.#....#..#.#..#.#....#..#.#..#.
#.....###.#....#..#.###..####.#..#.###..`);
});
