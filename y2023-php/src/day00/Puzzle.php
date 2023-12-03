<?php declare(strict_types=1);

namespace Eimanavicius\Y2023Php\day00;

final class Puzzle
{

    public static function part1(string $input): int
    {
        return array_reduce(
            preg_split("/\r?\n/", $input),
            static fn($count, $line) => $count + 1,
            0
        );
    }

    public static function part2(string $input): int
    {
        return array_reduce(
            preg_split("/\r?\n/", $input),
            static fn($count, $line) => $count + 2,
            0
        );
    }
}
