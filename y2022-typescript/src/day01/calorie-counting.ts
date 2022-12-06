function totalCaloriesCarriedByEachElf(input: string): number[] {
  return input.split('\n\n')
    .map(x => x.split('\n')
      .map(x => parseInt(x))
      .reduce((sum, calories) => sum + calories, 0)
    )
    .sort((a, b) => a - b)
    .reverse();
}

export function part1(input: string): number {
  return totalCaloriesCarriedByEachElf(input)[0];
}

export function part2(input: string): number {
  return totalCaloriesCarriedByEachElf(input)
    .reduce((sum, calories, index) => index < 3 ? calories + sum : sum, 0);
}
