<?php declare(strict_types=1);

namespace Eimanavicius\Y2023Php\day02;

final class CubeConundrum
{

    private const MAX_RED = 12;
    private const MAX_GREEN = 13;
    private const MAX_BLUE = 14;

    public static function part1(string $input): int
    {
        $validGames = [];
        $sets = preg_split("/\r?\n/", $input);
        foreach ($sets as $line) {
            [$game, $roundsLine] = explode(': ', $line);

            $rounds = array_map(
                static fn(string $round) => array_map(
                    static fn(string $turn) => explode(' ', $turn),
                    explode(', ', $round)
                ),
                explode('; ', $roundsLine)
            );

            foreach ($rounds as $round) {
                $red = 0;
                $green = 0;
                $blue = 0;
                foreach ($round as $turn) {
                    [$count, $color] = $turn;
                    if ($color === "red") {
                        $red += (int)$count;
                    } elseif ($color === "green") {
                        $green += (int)$count;
                    } elseif ($color === "blue") {
                        $blue += (int)$count;
                    }
                }
                if ($red > self::MAX_RED || $green > self::MAX_GREEN || $blue > self::MAX_BLUE) {
                    continue 2;
                }
            }

            $validGames[] = [
                'id' => (int)(explode(" ", $game)[1])
            ];
        }

        return array_reduce(
            $validGames,
            static fn($acc, $curr) => $acc + $curr['id'],
            0
        );
    }

    public static function part2(string $input): int
    {
        $validGames = [];
        $sets = preg_split("/\r?\n/", $input);

        foreach ($sets as $line) {
            [$game, $roundsLine] = explode(': ', $line);

            $rounds = array_map(
                static fn(string $round) => array_map(
                    static fn(string $turn) => explode(' ', $turn),
                    explode(', ', $round)
                ),
                explode('; ', $roundsLine)
            );

            $red = 1;
            $green = 1;
            $blue = 1;
            foreach ($rounds as $round) {
                foreach ($round as $turn) {
                    [$count, $color] = $turn;
                    $count = (int)$count;
                    if ($color === "red" && $count > $red) {
                        $red = $count;
                    } elseif ($color === "green" && $count > $green) {
                        $green = $count;
                    } elseif ($color === "blue" && $count > $blue) {
                        $blue = $count;
                    }
                }
            }
            $validGames[] = [
                'id' => $red * $green * $blue
            ];
        }

        return array_reduce(
            $validGames,
            static fn($acc, $curr) => $acc + $curr['id'],
            0
        );
    }
}
