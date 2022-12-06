export function part1(input: string) {
  return input.split('\n')
    .map(x => [x.slice(0, x.length / 2), x.slice(x.length / 2)])
    .map(x => x[0].split('').filter(a => x[1].indexOf(a) != -1)[0])
    .map(x => x.charCodeAt(0) - (x >= 'a' ? 97 - 1 : 65 - 27))
    .reduce((sum, value) => sum + value, 0);
}

export function part2(input: string) {
  return input.split('\n')
    .reduce(
      (groups, elf, index) => {
        const group = Math.floor(index / 3);
        groups[group] = [...groups[group] || [], elf];
        return groups;
      },
      [] as string[][]
    )
    .map(x => [
      x[0].split('').filter(a => x[1].indexOf(a) != -1),
      x[0].split('').filter(a => x[2].indexOf(a) != -1),
    ])
    .map(x => x[0].filter(a => x[1].includes(a))[0])
    .map(x => x.charCodeAt(0) - (x >= 'a' ? 97 - 1 : 65 - 27))
    .reduce((sum, value) => sum + value, 0);
}
