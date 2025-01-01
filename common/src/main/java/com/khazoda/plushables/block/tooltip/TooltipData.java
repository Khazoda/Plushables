package com.khazoda.plushables.block.tooltip;

import org.jetbrains.annotations.Nullable;

public record TooltipData(String number, String artist, String creationDate, @Nullable String trivia) {
  public static final TooltipData DEFAULT =
      new TooltipData("#0", "Khazoda", "01/01/2000", null);
}