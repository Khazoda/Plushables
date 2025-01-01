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

  public TooltipDataBuilder number(String number, String artist, String creationDate, @Nullable String trivia) {
    this.number = number;
    this.artist = artist;
    this.creationDate = creationDate;
    this.trivia = trivia;
    return this;
  }
}
