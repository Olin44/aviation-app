package pl.olin44.aviationapp;

import org.junit.jupiter.api.Test;
import pl.olin44.aviationapp.utils.DateParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

public class DateParserTests {
    public static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    public static LocalTime between(LocalTime startTime, LocalTime endTime) {
        int startSeconds = startTime.toSecondOfDay();
        int endSeconds = endTime.toSecondOfDay();
        int randomTime = ThreadLocalRandom
                .current()
                .nextInt(startSeconds, endSeconds);

        return LocalTime.ofSecondOfDay(randomTime);
    }

    @Test
    void shouldProperParseCurrentDateTime() {
        // Arrange:
        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        String currentDateTimeAsString = createStringRepresentationOfCurrentDateTime(currentLocalDateTime);
        DateParser dateParser = DateParser.builder()
                .locale(Locale.forLanguageTag("en-US"))
                .pattern("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (z)")
                .build();
        // Act:
        LocalDateTime localDateTime = dateParser.parse(currentDateTimeAsString);
        // Assert:
        assertThat(localDateTime).isEqualToIgnoringNanos(currentLocalDateTime);
    }

    @Test
    void shouldProperParseRandomDate() {
        // Arrange:
        LocalDate randomDate = between(LocalDate.of(1900, 1, 1),
                LocalDate.of(2100, 12, 31));
        LocalTime randomTime = between(LocalTime.MIN, LocalTime.MAX);
        LocalDateTime randomDateTime = LocalDateTime.of(randomDate, randomTime);
        String currentDateTimeAsString = createStringRepresentationOfCurrentDateTime(randomDateTime);
        DateParser dateParser = DateParser.builder()
                .locale(Locale.forLanguageTag("en-US"))
                .pattern("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (z)")
                .build();
        // Act:
        LocalDateTime localDateTime = dateParser.parse(currentDateTimeAsString);
        // Assert:
        assertThat(localDateTime).isEqualToIgnoringNanos(randomDateTime);

    }

    private String createStringRepresentationOfCurrentDateTime(LocalDateTime currentLocalDateTime) {
        String SEPARATOR = " ";
        Locale locale = Locale.forLanguageTag("en-US");
        String dayOfWeekShortName = currentLocalDateTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, locale);
        String monthShortName = currentLocalDateTime.getMonth().getDisplayName(TextStyle.SHORT, locale);
        String day = String.valueOf(currentLocalDateTime.getDayOfMonth());
        String year = String.valueOf(currentLocalDateTime.getYear());
        String time = currentLocalDateTime.toLocalTime()
                .truncatedTo((ChronoUnit.SECONDS))
                .toString();
        return dayOfWeekShortName +
                SEPARATOR +
                monthShortName +
                SEPARATOR +
                day +
                SEPARATOR +
                year +
                SEPARATOR +
                time +
                SEPARATOR +
                "GMT+0000 (UTC)";
    }

}
