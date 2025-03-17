package com.khazoda.plushables.block.tooltip;

import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.time.ZoneId;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.Locale;

public record TooltipData(
    String number,
    String artist,
    long creationTimestamp,
    @Nullable String originalDate,
    @Nullable String trivia
) {
  public static final TooltipData DEFAULT =
      new TooltipData("#0", "Khazoda", 946684800000L, null, null);

  public String localizeDate(String rawLocale) {
    // If timestamp is 0, use the original date string as fallback
    if (creationTimestamp == 0 && originalDate != null) {
      return originalDate;
    }

    try {
      Locale locale = Locale.forLanguageTag(rawLocale.replace('_', '-'));
      Instant instant = Instant.ofEpochMilli(creationTimestamp);

      // Get user's calendar system
      Chronology chronology = Chronology.ofLocale(locale);

      // Create formatter that uses the locale's calendar system
      DateTimeFormatter formatter = new DateTimeFormatterBuilder()
          .appendLocalized(FormatStyle.LONG, null)
          .toFormatter(locale)
          .withChronology(chronology)
          .withZone(ZoneId.systemDefault());

      return formatter.format(instant);
    } catch (Exception e) {
      // If anything goes wrong with localization, fall back to original date
      return originalDate != null ? originalDate : "Unknown Date";
    }
  }
}