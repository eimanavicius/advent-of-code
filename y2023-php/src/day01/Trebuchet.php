<?php declare(strict_types=1);

namespace Eimanavicius\Y2023Php\day01;

final class Trebuchet
{

    public static function part1(string $input): int
    {
        $document = preg_split("/\r?\n/", $input);
        foreach ($document as &$line) {
            $line = preg_replace('/\D/', '', $line);
            $line = $line[0] . $line[strlen($line) - 1];
        }
        return array_reduce(
            $document,
            static fn($sum, $value) => $sum + $value,
            0
        );
    }

    public static function part2(string $input): int
    {
        $nums = [
            'one' => '1',
            'two' => '2',
            'three' => '3',
            'four' => '4',
            'five' => '5',
            'six' => '6',
            'seven' => '7',
            'eight' => '8',
            'nine' => '9',
        ];
        $words = array_keys($nums);


        $document = array_map(static function ($line) use ($words, $nums) {
            $numbers = '';
            foreach (str_split($line) as $key => $char) {
                if (is_numeric($char)) {
                    $numbers .= $char;
                    continue;
                }
                foreach ($words as $word) {
                    if (strpos($line, $word, $key) === $key) {
                        $numbers .= $nums[$word];
                        break;
                    }
                }
            }
            return $numbers[0] . $numbers[strlen($numbers) - 1];
        }, preg_split("/\r?\n/", $input));

        return array_reduce(
            $document,
            static fn($sum, $value) => $sum + (int)$value,
            0
        );
    }
}
