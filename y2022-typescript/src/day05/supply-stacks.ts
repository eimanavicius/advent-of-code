export function part1(input: string) {
  const [suppliesInput, movesInput] = input.split('\n\n');

  const moves = movesInput.split('\n');

  const supplies = suppliesInput.split('\n').reverse();
  supplies.shift();

  const stacks: string[][] = [];
  const numberOfStacks = Math.round(supplies[0].length / 4);
  for (let i = 0; i < numberOfStacks; i++) {
    stacks[i] = [];
    for (let s of supplies) {
      let supply = s[1 + (i * 4)].trim();
      if (supply.length) {
        stacks[i].push(supply);
      }
    }
  }

  moves.map(x => x.match(/.*?(\d+).*?(\d+).*?(\d+)/))
    .map(x => x ? [parseInt(x[1]), parseInt(x[2]), parseInt(x[3])] : [-1, 0, 0])
    .forEach(x => {
      for (let i = 0; i < x[0]; i++) {
        let move = stacks[x[1] - 1].pop();
        if (move) {
          stacks[x[2] - 1].push(move);
        }
      }
    });

  return stacks.map(x => x.pop()).join('');
}

export function part2(input: string) {
  const [suppliesInput, movesInput] = input.split('\n\n');

  const moves = movesInput.trim().split('\n');

  const supplies = suppliesInput.split('\n').reverse();
  supplies.shift();

  const stacks: string[][] = [];
  const numberOfStacks = Math.round(supplies[0].length / 4);
  for (let i = 0; i < numberOfStacks; i++) {
    stacks[i] = [];
    for (let c of supplies) {
      let x = c[1 + (i * 4)].trim();
      if (x.length) {
        stacks[i].push(x);
      }
    }
  }

  moves.map(x => x.match(/.*?(\d+).*?(\d+).*?(\d+)/))
    .map(x => x ? [parseInt(x[1]), parseInt(x[2]), parseInt(x[3])] : [-1, 0, 0])
    .forEach(x => {
      let crates = [];
      for (let i = 0; i < x[0]; i++) {
        crates.unshift(stacks[x[1] - 1].pop() || '-');
      }
      for (let crate of crates) {
        stacks[x[2] - 1].push(crate);
      }
    });

  return stacks.map(x => x.pop()).join('');
}
