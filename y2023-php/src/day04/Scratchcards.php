<?php declare(strict_types=1);

namespace Eimanavicius\Y2023Php\day04;

final class Scratchcards
{

    public static function part1(string $input): int
    {
        $cards = preg_split("/\r?\n/", $input);
        foreach ($cards as &$card) {
            $card = preg_split('/(: )|( \| )/', $card);
            $card[0] = explode(' ', $card[0])[1];
            $card[1] = array_filter(explode(' ', $card[1]));
            $card[2] = array_filter(explode(' ', $card[2]));
        }
        unset($card);
        $wins = [];

        foreach ($cards as $card) {
            $count = 0;
            foreach ($card[1] as $number) {
                if (in_array($number, $card[2], true)) {
                    $count++;
                }
            }
            if ($count > 0) {
                $wins[] = 2 ** ($count - 1);
            }
        }

        return array_reduce(
            $wins,
            static fn($sum, $value) => $sum + $value,
            0
        );
    }

    public static function part2(string $input): int
    {
        $cards = preg_split("/\r?\n/", $input);
        $copies = [];
        foreach ($cards as &$card) {
            $card = preg_split('/(: )|( \| )/', $card);
            $card[0] = (int)trim(explode(' ', $card[0], 2)[1]);
            $card[1] = array_filter(explode(' ', $card[1]));
            $card[2] = array_filter(explode(' ', $card[2]));

            $copies[$card[0]] = 1;
        }
        unset($card);

        foreach ($cards as $iValue) {
            $card = $iValue;
            $count = 0;
            foreach ($card[1] as $number) {
                if (in_array($number, $card[2], true)) {
                    $count++;
                }
            }
            for ($j = 1; $j <= $count; $j++) {
                $copies[$card[0] + $j] += $copies[$card[0]];
            }
        }

        return array_reduce(
            $copies,
            static fn($sum, $value) => $sum + $value,
            0
        );
    }
}
