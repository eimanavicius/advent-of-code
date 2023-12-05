<?php declare(strict_types=1);

namespace Eimanavicius\Y2023Php\day03;

final class GearRatios
{

    public static function part1(string $input): int
    {
        $schematic = array_map(static fn($line) => str_split($line), preg_split("/\r?\n/", $input));
        $parts = [];

        foreach ($schematic as $i => $row) {
            foreach ($row as $j => $cell) {
                if (is_numeric($cell) || $cell === '.') {
                    continue;
                }
                $directions = [[0, 1], [1, 0], [0, -1], [-1, 0], [1, 1], [1, -1], [-1, 1], [-1, -1]];
                $done = [];
                foreach ($directions as $d) {
                    $k = $i + $d[0];
                    $l = $j + $d[1];
                    $name = "$k-$l";
                    if (in_array($name, $done, true)) {
                        continue;
                    }

                    if (isset($schematic[$k][$l]) && is_numeric($schematic[$k][$l])) {
                        $done[] = $name;
                        $number = $schematic[$k][$l];
                        $p = $l - 1;
                        while (isset($schematic[$k][$p]) && is_numeric($schematic[$k][$p])) {
                            $number = $schematic[$k][$p] . $number;
                            $done[] = "$k-$p";
                            $p--;
                        }
                        $p = $l + 1;
                        while (isset($schematic[$k][$p]) && is_numeric($schematic[$k][$p])) {
                            $number .= $schematic[$k][$p];
                            $done[] = "$k-$p";
                            $p++;
                        }
                        $parts[] = (int)$number;
                    }
                }
            }
        }

        return array_sum($parts);
    }

    public static function part2(string $input): int
    {
        $schematic = array_map(static fn($line) => str_split($line), preg_split("/\r?\n/", $input));
        $parts = [];

        foreach ($schematic as $i => $row) {
            foreach ($row as $j => $cell) {
                if ($cell !== '*') {
                    continue;
                }
                $gearParts = [];
                $directions = [[0, 1], [1, 0], [0, -1], [-1, 0], [1, 1], [1, -1], [-1, 1], [-1, -1]];
                $done = [];
                foreach ($directions as $d) {
                    $k = $i + $d[0];
                    $l = $j + $d[1];
                    $name = "$k-$l";
                    if (in_array($name, $done, true)) {
                        continue;
                    }

                    if (isset($schematic[$k][$l]) && is_numeric($schematic[$k][$l])) {
                        $done[] = $name;
                        $number = $schematic[$k][$l];
                        $p = $l - 1;
                        while (isset($schematic[$k][$p]) && is_numeric($schematic[$k][$p])) {
                            $number = $schematic[$k][$p] . $number;
                            $done[] = "$k-$p";
                            $p--;
                        }
                        $p = $l + 1;
                        while (isset($schematic[$k][$p]) && is_numeric($schematic[$k][$p])) {
                            $number .= $schematic[$k][$p];
                            $done[] = "$k-$p";
                            $p++;
                        }
                        $gearParts[] = (int)$number;
                    }
                }
                if (count($gearParts) === 2) {
                    $parts[] = array_product($gearParts);
                }
            }
        }

        return array_sum($parts);
    }
}
