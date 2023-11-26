package io.github.kronae.korean.combiner.part.consonant;

import io.github.kronae.korean.combiner.part.KoreanPart;

public interface KoreanConsonant extends KoreanPart {
    int getAdding();
    int getId();
    boolean equals(KoreanPart part);
}
