package com.telesoftas.adventofcode.reportrepair;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReportRepairTest {

    @Test
    void throws_exception_when_finds_no_answer() {
        assertAll(
            () -> assertThrows(NothingSumsTo2020Exception.class, () -> ExpenseReport.find2(List.of())),
            () -> assertThrows(NothingSumsTo2020Exception.class, () -> ExpenseReport.find2(List.of(1))),
            () -> assertThrows(NothingSumsTo2020Exception.class, () -> ExpenseReport.find2(List.of(1, 2)))
        );
    }

    @Test
    void returns_2_multiplied_entries_that_sum_to_2020() {
        assertAll(
            () -> assertEquals(40000, ExpenseReport.find2(List.of(2000, 20))),
            () -> assertEquals(40000, ExpenseReport.find2(List.of(10, 2000, 20))),
            () -> assertEquals(40000, ExpenseReport.find2(List.of(10, 2000, 30, 20)))
        );
    }

    @Test
    void collects_numbers_from_input_stream_to_integers_stream() {
        InputStream input = new ByteArrayInputStream("12\n23\n34".getBytes());

        List<Integer> list = ExpenseReport.toIntegersStream(input).collect(toList());

        assertEquals(List.of(12, 23, 34), list);
    }
}
