package pl.olin44.aviationapp.utils;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@RequiredArgsConstructor
@Builder
public class DateParser {
    private final Locale locale;
    private final String pattern;

    public LocalDateTime parse(String date) {
        return LocalDateTime.parse(date, getDateTimeFormatter());
    }

    private DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter
                .ofPattern(pattern)
                .withLocale(locale);
    }

}
