import { part1, part2 } from './camp-cleanup';
import fs from 'fs';

let sample: string;
let input: string;

beforeAll(() => {
  sample = fs.readFileSync(__dirname + '/sample.txt', 'utf-8');
  input = fs.readFileSync(__dirname + '/input.txt', 'utf-8');
});

test('part 1 sample', () => {
  expect(part1(sample)).toBe(2);
});

test('part 1', () => {
  expect(part1(input)).toBe(569);
});

test('part 2 sample', () => {
  expect(part2(sample)).toBe(4);
});

test('part 2', () => {
  expect(part2(input)).toBe(936);
});
