package com.kronae.korean.combiner.part.consonant;

import com.kronae.korean.combiner.part.KoreanPart;
import com.kronae.korean.combiner.part.consonant.KoreanConsonant;
import org.jetbrains.annotations.NotNull;

public enum KoreanFinalSound implements KoreanConsonant { // Jong Sung (종성) THIRD(FINAL)
    ㄱ(0, 1),
    ㄲ(1, 2),
    ㄳ(2, 3),
    ㄴ(3, 4),
    ㄵ(4, 5),
    ㄶ(5, 6),
    ㄷ(6, 7),
    ㄹ(8, 8),
    ㄺ(9, 9),
    ㄻ(10, 10),
    ㄼ(11, 11),
    ㄽ(12, 12),
    ㄾ(13, 13),
    ㄿ(14, 14),
    ㅀ(15, 15),
    ㅁ(16, 16),
    ㅂ(17, 17),
    ㅄ(19, 18),
    ㅅ(20, 19),
    ㅆ(21, 20),
    ㅇ(22, 21),
    ㅈ(23, 22),
    ㅊ(25, 23),
    ㅋ(26, 24),
    ㅌ(27, 25),
    ㅍ(28, 26),
    ㅎ(29, 27);
    private final int id;
    private final int ai;
    KoreanFinalSound(int id, int ai) {
        this.id = id;
        this.ai = ai;
    }
    public int getId() {
        return id;
    }
    public int getAdding() {
        return ai;
    }
    public boolean equals(@NotNull KoreanPart part) {
        if(!(part instanceof KoreanConsonant))
            return false;
        return part.getId() == getId();
    }
}
