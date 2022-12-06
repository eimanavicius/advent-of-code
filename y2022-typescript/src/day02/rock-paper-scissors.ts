const scores: { [key: string]: number } = {
  'A': 1,
  'B': 2,
  'C': 3,
  'X': 1,
  'Y': 2,
  'Z': 3
};

const wins: { [key: number]: number } = {
  1: 3,
  2: 1,
  3: 2
};

const loss: { [key: number]: number } = {
  3: 1,
  1: 2,
  2: 3
};

export function part1(input: string): number {
  return input.split('\n')
    .map(x => {
      const [him, me] = x.split(' ').map(x => scores[x]);
      return me + (him == me ? 3 : (wins[me] == him ? 6 : 0));
    })
    .reduce((a, b) => a + b, 0);
}

export function part2(input: string): number {
  return input.split('\n')
    .map(x => {
      const [him, me] = x.split(' ').map(x => scores[x]);
      return me == 1 ? wins[him] : (me == 2 ? him + 3 : 6 + loss[him]);
    })
    .reduce((a, b) => a + b, 0);
}
