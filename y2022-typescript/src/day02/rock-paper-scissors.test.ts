import { part1, part2 } from './rock-paper-scissors';
import fs from 'fs';

describe('puzzle module', () => {
  let sample: string;
  let input: string;

  beforeAll(() => {
    sample = fs.readFileSync(__dirname + '/sample.txt', 'utf-8');
    input = fs.readFileSync(__dirname + '/input.txt', 'utf-8');
  });

  test('part 1 sample', () => {
    expect(part1(sample)).toBe(15);
  });

  test('part 1', () => {
    expect(part1(input)).toBe(11063);
  });

  test('part 2 sample', () => {
    expect(part2(sample)).toBe(12);
  });

  test('part 2', () => {
    expect(part2(input)).toBe(10349);
  });
});
