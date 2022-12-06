import { part1, part2 } from './rucksack-reorganization';
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
