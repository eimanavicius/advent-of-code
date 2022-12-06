import { part1, part2 } from './puzzle';
import fs from 'fs';


describe('puzzle module', () => {
  let input: string;
  beforeAll(() => {
    input = fs.readFileSync('src/day00/input.txt', 'utf-8');
  });

  test('part 1', () => {
    expect(part1(input)).toBe('answer 1');
  });

  test('part 2', () => {
    expect(part2(input)).toBe('answer 2');
  });
});
