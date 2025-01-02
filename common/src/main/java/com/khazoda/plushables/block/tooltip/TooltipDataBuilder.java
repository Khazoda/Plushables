package com.khazoda.plushables.block.tooltip;

import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TooltipDataBuilder {
  private String number = TooltipData.DEFAULT.number();
  private String artist = TooltipData.DEFAULT.artist();
  private long creationTimestamp = TooltipData.DEFAULT.creationTimestamp();
  private String trivia = TooltipData.DEFAULT.trivia();

  public static TooltipDataBuilder create() {
    return new TooltipDataBuilder();
  }

  public TooltipDataBuilder number(int number) {
    this.number = "#" + number;
    return this;
  }

  public TooltipDataBuilder artist(String artist) {
    this.artist = artist;
    return this;
  }

  public TooltipDataBuilder creationDate(String creationDate) {
    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d['st']['nd']['rd']['th'] MMMM yyyy");
    LocalDate parsedDate = LocalDate.parse(creationDate, inputFormatter);
    this.creationTimestamp = parsedDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    return this;
  }

  public TooltipDataBuilder trivia(@Nullable String trivia) {
    this.trivia = trivia;
    return this;
  }

  public TooltipData build() {
    return new TooltipData(number, artist, creationTimestamp, trivia);
  }
}
