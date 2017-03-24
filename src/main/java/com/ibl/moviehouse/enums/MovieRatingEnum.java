package com.ibl.moviehouse.enums;

import java.util.Arrays;
import java.util.List;

public enum MovieRatingEnum {
    ZERO(0),
    TEN(10),
    TWENTY(20),
    THIRTY(30),
    FOURTY(40),
    FIFTY(50),
    SIXTY(60),
    SEVENTY(70),
    EIGHTY(80),
    NINETY(90),
    HUNDRET(100);

    private final int value;

    public static final List<Integer> all = Arrays.asList(ZERO.getValue(), TEN.getValue(), TWENTY.getValue(),
            THIRTY.getValue(), FOURTY.getValue(), FIFTY.getValue(), SIXTY.getValue(), SEVENTY.getValue(), EIGHTY.getValue(),
            NINETY.getValue(), HUNDRET.getValue());

    MovieRatingEnum(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }



}
