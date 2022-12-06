import { part1, part2 } from './tuning-trouble';
import fs from 'fs';

let input: string;

beforeAll(() => {
  input = fs.readFileSync(__dirname + '/input.txt', 'utf-8');
});

test('part 1 sample', () => {
  expect(part1('mjqjpqmgbljsphdztnvjfqwrcgsmlb')).toBe(7);
  expect(part1('bvwbjplbgvbhsrlpgdmjqwftvncz')).toBe(5);
  expect(part1('nppdvjthqldpwncqszvftbrmjlhg')).toBe(6);
  expect(part1('nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg')).toBe(10);
  expect(part1('zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw')).toBe(11);
});

test('part 1', () => {
  expect(part1(input)).toBe(1361);
});

test('part 2 sample', () => {
  expect(part2('mjqjpqmgbljsphdztnvjfqwrcgsmlb')).toBe(19);
  expect(part2('bvwbjplbgvbhsrlpgdmjqwftvncz')).toBe(23);
  expect(part2('nppdvjthqldpwncqszvftbrmjlhg')).toBe(23);
  expect(part2('nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg')).toBe(29);
  expect(part2('zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw')).toBe(26);
});

test('part 2', () => {
  expect(part2(input)).toBe(3263);
});
