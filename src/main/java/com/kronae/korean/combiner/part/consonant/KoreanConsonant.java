package com.kronae.korean.combiner.part.consonant;

import com.kronae.korean.combiner.part.KoreanPart;

public interface KoreanConsonant extends KoreanPart {
    int getAdding();
    int getId();
    boolean equals(KoreanPart part);
}
