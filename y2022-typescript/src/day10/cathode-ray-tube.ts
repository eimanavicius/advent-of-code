import { CPU, Instruction } from './cpu';
import { CRT } from './crt';
import { Signals } from './signals';
import { Addx, Noop } from './instructions';

function parseInstructions(input: string): Instruction[] {
  return input.split(/\r?\n/)
    .map(x => x.split(' '))
    .map((x): Instruction => x[0] === 'addx' ? new Addx(parseInt(x[1])) : new Noop());
}

export function part1(input: string) {
  let signals = new Signals(20, 40);
  let cpu = new CPU([signals]);

  parseInstructions(input).forEach(cpu.run, cpu);

  return signals.sumOfSixSignalStrengths();
}

export function part2(input: string) {
  let display = new CRT(40);
  let cpu = new CPU([display]);

  parseInstructions(input).forEach(cpu.run, cpu);

  return display.draw();
}
