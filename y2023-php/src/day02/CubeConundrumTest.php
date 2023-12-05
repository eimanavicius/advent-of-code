<?php declare(strict_types=1);

namespace Eimanavicius\Y2023Php\day02;

use PHPUnit\Framework\Attributes\BeforeClass;
use PHPUnit\Framework\Attributes\Test;
use PHPUnit\Framework\TestCase;

final class CubeConundrumTest extends TestCase
{
    private static string $sample;
    private static string $input;

    #[BeforeClass]
    public static function beforeClass(): void
    {
        self::$sample = file_get_contents(__DIR__ . '/sample.txt');
        self::$input = file_get_contents(__DIR__ . '/input.txt');
    }

    #[Test]
    public function part_1_sample(): void
    {
        $this->assertThat(CubeConundrum::part1(self::$sample), $this->equalTo(8));
    }

    #[Test]
    public function part_1(): void
    {
        $this->assertThat(CubeConundrum::part1(self::$input), $this->equalTo(2317));
    }

    #[Test]
    public function part_2_sample(): void
    {
        $this->assertThat(CubeConundrum::part2(self::$sample), $this->equalTo(2286));
    }

    #[Test]
    public function part_2(): void
    {
        $this->assertThat(CubeConundrum::part2(self::$input), $this->equalTo(74804));
    }
}
