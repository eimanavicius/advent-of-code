<?php declare(strict_types=1);

namespace Eimanavicius\Y2023Php\day06;

final class WaitForIt
{

    public static function part1(string $input): int
    {
        [$time, $distance] = preg_split("/\r?\n/", $input);
        $time = array_values(array_filter(explode(' ', explode(': ', $time)[1])));
        $time = array_map(static fn($t) => (int)$t, $time);

        $distance = array_values(array_filter(explode(' ', explode(': ', $distance)[1])));
        $distance = array_map(static fn($d) => (int)$d, $distance);

        $options = [];
        foreach ($time as $i => $t) {
            $tries = 0;
            for ($hold = 1; $hold < $t; $hold++) {
                if ($distance[$i] < ($t - $hold) * $hold) {
                    $tries++;
                }
            }
            $options[] = $tries;
        }

        return array_product($options);
    }

    public static function part2(string $input): int
    {
        [$time, $distance] = preg_split("/\r?\n/", $input);

        $time = (int)str_replace(' ', '', explode(': ', $time)[1]);
        $distance = (int)str_replace(' ', '', explode(': ', $distance)[1]);

        for ($hold = 1; $hold < $time; $hold++) {
            if ($distance < ($time - $hold) * $hold) {
                break;
            }
        }
        for ($rev = $time; $rev > $hold; $rev--) {
            if ($distance < ($time - $rev) * $rev) {
                break;
            }
        }

        return $rev - $hold + 1;
    }
}
