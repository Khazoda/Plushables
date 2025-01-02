package com.khazoda.plushables.block.tooltip;

import org.jetbrains.annotations.Nullable;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public record TooltipData(String number, String artist, long creationTimestamp, @Nullable String trivia) {
  public static final TooltipData DEFAULT =
      new TooltipData("#0", "Khazoda", 946684800000L, null);

  public String localizeDate(String rawLocale) {
    Locale locale = Locale.forLanguageTag(rawLocale.replace('_', '-'));
    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, locale);
    return dateFormat.format(new Date(creationTimestamp));
  }
}