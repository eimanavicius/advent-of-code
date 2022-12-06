export function part1(input: string) {
  return input.split('\n')
    .map(x => [x.slice(0, x.length / 2), x.slice(x.length / 2)])
    .map(x => x[0].split('').filter(a => x[1].indexOf(a) != -1)[0])
    .map(x => x.charCodeAt(0) - (x >= 'a' ? 97 - 1 : 65 - 27))
    .reduce((a, b) => a + b, 0);
}

export function part2(input: string) {
  const rugs = input.split('\n');
  const groups = [];
  for (let i = 0; i < rugs.length; i += 3) groups.push(rugs.slice(i, i + 3));
  return groups
    .map(x => [
      x[0].split('').filter(a => x[1].indexOf(a) != -1),
      x[0].split('').filter(a => x[2].indexOf(a) != -1),
    ])
    .map(x => x[0].filter(a => x[1].includes(a))[0])
    .map(x => x.charCodeAt(0) - (x >= 'a' ? 97 - 1 : 65 - 27))
    .reduce((a, b) => a + b, 0);
}
