export const toHalfPair = (x: string) => [x.slice(0, x.length / 2), x.slice(x.length / 2)];

export const toTrioGroups = (groups: string[][], elf: string, index: number) => {
  const group = Math.floor(index / 3);
  groups[group] = [...groups[group] || [], elf];
  return groups;
};

export const toPairIntersection = (x: string[]) => x[0].split('')
  .filter(a => x[1].indexOf(a) != -1);

export const toTrioIntersection = (x: string[]) => x[0].split('')
  .filter(a => x[1].indexOf(a) != -1)
  .filter(a => x[2].indexOf(a) != -1);

export const toFirst = (x: string[]) => x[0];

export const toPriority = (x: string) => x.charCodeAt(0) - (x >= 'a' ? 97 - 1 : 65 - 27);

export const toSum = (sum: number, value: number) => sum + value;

export function part1(input: string) {
  return input.split('\n')
    .map(toHalfPair)
    .map(toPairIntersection)
    .map(toFirst)
    .map(toPriority)
    .reduce(toSum, 0);
}

export function part2(input: string) {
  return input.split('\n')
    .reduce(toTrioGroups, [] as string[][])
    .map(toTrioIntersection)
    .map(toFirst)
    .map(toPriority)
    .reduce(toSum, 0);
}
