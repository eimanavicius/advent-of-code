import { part1, part2 } from './calorie-counting';
import * as fs from 'fs';


describe('puzzle module', () => {
  let input: string;
  let sample: string;
  beforeAll(() => {
    input = fs.readFileSync(__dirname + '/input.txt', 'utf-8');
    sample = fs.readFileSync(__dirname + '/sample.txt', 'utf-8');
  });

  test('part 1 sample', () => {
    expect(part1(sample)).toBe(24000);
  });

  test('part 1', () => {
    expect(part1(input)).toBe(70374);
  });

  test('part 2 sample', () => {
    expect(part2(sample)).toBe(45000);
  });

  test('part 2', () => {
    expect(part2(input)).toBe(204610);
  });
});
