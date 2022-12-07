function buildFlatTree(input: string): { [p: string]: number } {
  const path: string[] = [];
  let tree = input.split('\n')
    .map(x => x.split(' '))
    .reduce((tree, file) => {
      if (file[1] == 'cd') {
        if (file[2] == '..') {
          path.pop();
          return tree;
        }
        path.push(file[2]);
        tree[path.join('/')] = 0;
        return tree;
      }
      if (file[0].match(/(\d+)/)) {
        tree[path.join('/')] = tree[path.join('/')] || 0;
        tree[path.join('/')] += parseInt(file[0]);
        return tree;
      }
      return tree;
    }, {} as { [key: string]: number });

  let names: string[] = [];
  for (let path in tree) {
    names.push(path);
  }
  names = names.sort();

  for (let path of names) {
    for (let subpath in tree) {
      if (path != subpath && subpath.indexOf(path) == 0) {
        tree[path] += tree[subpath];
      }
    }
  }

  return tree;
}

export function part1(input: string) {
  const tree = buildFlatTree(input);

  let sum = 0;
  for (let path in tree) {
    if (tree[path] <= 100000) {
      sum += tree[path];
    }
  }

  return sum;
}

export function part2(input: string) {
  const tree = buildFlatTree(input);

  const free = 70000000 - tree['/'];
  const need = 30000000 - free;

  let min = tree['/'];
  for (let path in tree) {
    if (tree[path] >= need && tree[path] < min) {
      min = tree[path];
    }
  }

  return min;
}
