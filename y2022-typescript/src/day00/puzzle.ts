export function part1(input: string) {
  return input.split(/\r?\n/)
    .reduce((count) => count + 1, 0);
}

export function part2(input: string) {
  return input.split(/\r?\n/)
    .reduce((count) => count + 2, 0);
}
