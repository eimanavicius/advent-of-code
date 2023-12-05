<?php declare(strict_types=1);

namespace Eimanavicius\Y2023Php\day01;

use PHPUnit\Framework\Attributes\BeforeClass;
use PHPUnit\Framework\Attributes\Test;
use PHPUnit\Framework\TestCase;

final class TrebuchetTest extends TestCase
{
    private const SAMPLE1 = <<<'TXT'
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
        TXT;
    private const SAMPLE2 = <<<'TXT'
        two1nine
        eightwothree
        abcone2threexyz
        xtwone3four
        4nineeightseven2
        zoneight234
        7pqrstsixteen
        TXT;
    private static string $input;

    #[BeforeClass]
    public static function beforeClass(): void
    {
        self::$input = file_get_contents(__DIR__ . '/input.txt');
    }

    #[Test]
    public function part_1_sample(): void
    {
        $this->assertThat(Trebuchet::part1(self::SAMPLE1), $this->equalTo(142));
    }

    #[Test]
    public function part_1(): void
    {
        $this->assertThat(Trebuchet::part1(self::$input), $this->equalTo(56397));
    }

    #[Test]
    public function part_2_sample(): void
    {
        $this->assertThat(Trebuchet::part2(self::SAMPLE2), $this->equalTo(281));
    }

    #[Test]
    public function part_2(): void
    {
        $this->assertThat(Trebuchet::part2(self::$input), $this->equalTo(55701));
    }
}
