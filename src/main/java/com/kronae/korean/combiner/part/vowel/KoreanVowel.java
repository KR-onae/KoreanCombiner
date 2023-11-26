package com.kronae.korean.combiner.part.vowel;

import com.kronae.korean.combiner.part.KoreanPart;

public interface KoreanVowel extends KoreanPart {
    int getAdding();
    int getId();
    boolean equals(KoreanPart part);
}
