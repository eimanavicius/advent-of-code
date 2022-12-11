import { Bus } from './cpu';

export class CRT implements Bus {
  private sprite: string = '';
  private nextNewLine: number = this.wide;
  private readonly display: string[] = ['', '', '', '', '', '',];

  constructor(private readonly wide: number) {
  }

  onBeforeInstruction(_: number, X: number): void {
    this.sprite = CRT.makeSprite(X);
  }

  onBeforeCycle(cycle: number, X: number): void {
    if (cycle === this.nextNewLine) {
      this.nextNewLine += this.wide;
    }
    let column = cycle % this.wide;
    let row = Math.floor((this.nextNewLine - this.wide) / this.wide);
    this.display[row] += this.sprite[column] === '#' ? '#' : '.';
  }

  private static makeSprite(x: number): string {
    const start = (x - 1) % 40;
    const sprite = [...Array(40).keys()].map(() => '.');

    sprite[start] = '#';
    sprite[start + 1] = '#';
    sprite[start + 2] = '#';

    return sprite.join('');
  }

  onAfterCycle(cycle: number, X: number): void {
  }

  draw(): string {
    return this.display.map(x => x).join('\n');
  }
}
