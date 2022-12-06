export function part1(input: string) {
  return input.split('\n')
    .map(x => x.split(/[-,]/).map(x => parseInt(x)))
    .map(x => ((x[0] >= x[2] && x[1] <= x[3]) || (x[2] >= x[0] && x[3] <= x[1])))
    .reduce((count: number, contains: boolean) => count + (contains ? 1 : 0), 0);
}

export function part2(input: string) {
  return input.split('\n')
    .map(x => x.split(/[-,]/).map(x => parseInt(x)))
    .map(x => (
      (x[0] <= x[2] && x[2] <= x[1]) ||
      (x[0] <= x[3] && x[3] <= x[1]) ||
      (x[2] <= x[0] && x[0] <= x[3]) ||
      (x[2] <= x[1] && x[1] <= x[3])
    ))
    .reduce((count: number, contains: boolean) => count + (contains ? 1 : 0), 0);
}
