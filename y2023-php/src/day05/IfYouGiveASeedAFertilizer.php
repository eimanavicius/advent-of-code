<?php declare(strict_types=1);

namespace Eimanavicius\Y2023Php\day05;

final class IfYouGiveASeedAFertilizer
{

    public static function part1(string $input): int
    {
        $sections = preg_split("/\r?\n\r?\n/", $input);

        $seeds = array_map(static fn($s) => (int)$s, explode(" ", explode(': ', $sections[0])[1]));
        $maps = self::extractMaps($sections);

        return self::findLowestSeedLocation($seeds, $maps);
    }

    public static function part2(string $input): int
    {
        $sections = preg_split("/\r?\n\r?\n/", $input);

        $seeds = array_map(static fn($s) => (int)$s, explode(" ", explode(': ', $sections[0])[1]));
        $maps = self::extractMaps($sections);

        $mins = [];
        for ($i = 0, $iMax = count($seeds); $i < $iMax; $i += 2) {
            $range = [];
            for ($j = 0; $j < $seeds[$i + 1]; $j++) {
                $range[] = $seeds[$i] + $j;
            }
            $mins[] = self::findLowestSeedLocation($range, $maps);
            echo $i, PHP_EOL;
        }

        return min($mins);
    }

    private static function extractMaps(array|false $sections): array
    {
        $maps = [];

        for ($i = 1, $count = count($sections); $i < $count; $i++) {
            $map = [];

            foreach (preg_split("/\r?\n/", $sections[$i]) as $range) {
                if ($range[strlen($range) - 1] === ':') {
                    continue;
                }
                $map[] = array_map(static fn($s) => (int)$s, explode(" ", $range));
            }
            $maps[] = $map;
        }
        return $maps;
    }

    /**
     * @param array $seeds
     * @param array $maps
     * @return mixed
     */
    private static function findLowestSeedLocation(array $seeds, array $maps): mixed
    {
        foreach ($seeds as &$seed) {
            foreach ($maps as $map) {
                foreach ($map as $range) {
                    if ($range[1] <= $seed && $seed < $range[1] + $range[2]) {
                        $seed = $range[0] + ($seed - $range[1]);
                        break;
                    }
                }
            }
        }

        return min($seeds);
    }
}
