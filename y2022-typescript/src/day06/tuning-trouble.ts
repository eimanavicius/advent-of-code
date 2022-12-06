function unique(buffer: string) {
  for (let j = 0; j < buffer.length; j++) {
    if (buffer.indexOf(buffer[j]) != j) {
      return false;
    }
  }
  return true;
}

export function part1(line: string): number {
  let buffer = '';
  for (let i = 0; i < line.length; i++) {
    buffer += line[i];
    if (buffer.length > 4) {
      buffer = buffer.substring(1, 5);
      if (buffer.indexOf(buffer[3]) == 3 && buffer.indexOf(buffer[2]) == 2 && buffer.indexOf(buffer[1]) == 1) {
        return i + 1;
      }
    }
  }
  throw new Error('Can\'t find start-of-packet marker!');
}

export function part2(line: string): number {
  let buffer = '';
  for (let i = 0; i < line.length; i++) {
    buffer += line[i];
    if (buffer.length > 14) {
      buffer = buffer.substring(1, 15);
      if (unique(buffer)) {
        return i + 1;
      }
    }
  }
  throw new Error('Can\'t find start-of-message marker!');
}
