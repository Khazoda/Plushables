package com.khazoda.plushables.block.tooltip;

import org.jetbrains.annotations.Nullable;

public class TooltipDataBuilder {
  private String number = TooltipData.DEFAULT.number();
  private String artist = TooltipData.DEFAULT.artist();
  private String creationDate = TooltipData.DEFAULT.creationDate();
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
    this.creationDate = creationDate;
    return this;
  }

  public TooltipDataBuilder trivia(@Nullable String trivia) {
    this.trivia = trivia;
    return this;
  }

  public TooltipData build() {
    return new TooltipData(number, artist, creationDate, trivia);
  }
}
