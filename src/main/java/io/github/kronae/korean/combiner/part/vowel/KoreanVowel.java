package io.github.kronae.korean.combiner.part.vowel;

import io.github.kronae.korean.combiner.part.KoreanPart;

public interface KoreanVowel extends KoreanPart {
    int getAdding();
    int getId();
    boolean equals(KoreanPart part);
}
