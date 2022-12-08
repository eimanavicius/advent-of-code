export function part1(input: string) {
  let trees = input.split(/\r?\n/)
    .map(x => x.split(''));

  let count = 0;
  for (let i = 1; i < trees.length - 1; i++) {
    for (let j = 1; j < trees[i].length - 1; j++) {
      const tree = trees[i][j];
      let visible = 4;
      for (let ii = i + 1; ii < trees.length; ii++) {
        if (tree <= trees[ii][j]) {
          visible -= 1;
          break;
        }
      }
      for (let ii = i - 1; ii >= 0; ii--) {
        if (tree <= trees[ii][j]) {
          visible -= 1;
          break;
        }
      }
      for (let jj = j + 1; jj < trees[i].length; jj++) {
        if (tree <= trees[i][jj]) {
          visible -= 1;
          break;
        }
      }
      for (let jj = j - 1; jj >= 0; jj--) {
        if (tree <= trees[i][jj]) {
          visible -= 1;
          break;
        }
      }
      if (visible > 0) count++;
    }
  }

  return count + (trees.length * 2) + ((trees[0].length - 2) * 2);
}

export function part2(input: string) {
  let trees = input.split(/\r?\n/)
    .map(x => x.split(''));

  let max = 0;
  for (let i = 1; i < trees.length - 1; i++) {
    for (let j = 1; j < trees[i].length - 1; j++) {
      const tree = trees[i][j];
      let score = 1;
      let count = 0;
      for (let ii = i + 1; ii < trees.length; ii++) {
        if (tree > trees[ii][j]) {
          count++;
        } else {
          count++;
          break;
        }
      }
      score *= count;
      count = 0;
      for (let ii = i - 1; ii >= 0; ii--) {
        if (tree > trees[ii][j]) {
          count++;
        } else {
          count++;
          break;
        }
      }
      score *= count;
      count = 0;
      for (let jj = j + 1; jj < trees[i].length; jj++) {
        if (tree > trees[i][jj]) {
          count++;
        } else {
          count++;
          break;
        }
      }
      score *= count;
      count = 0;
      for (let jj = j - 1; jj >= 0; jj--) {
        if (tree > trees[i][jj]) {
          count++;
        } else {
          count++;
          break;
        }
      }
      score *= count;
      if (max < score) {
        max = score;
      }
    }
  }

  return max;
}
