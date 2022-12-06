import { part1, part2 } from './puzzle';
import fs from 'fs';

let sample: string;
let input: string;

beforeAll(() => {
  sample = fs.readFileSync('src/day00/sample.txt', 'utf-8');
  input = fs.readFileSync('src/day00/input.txt', 'utf-8');
});

test('part 1 sample', () => {
  expect(part1(sample)).toBe('answer 1');
});

test('part 1', () => {
  expect(part1(input)).toBe('answer 1');
});

test('part 2 sample', () => {
  expect(part2(sample)).toBe('answer 2');
});

test('part 2', () => {
  expect(part2(input)).toBe('answer 2');
});
