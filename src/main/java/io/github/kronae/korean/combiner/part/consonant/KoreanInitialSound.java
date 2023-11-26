package io.github.kronae.korean.combiner.part.consonant;

import io.github.kronae.korean.combiner.part.KoreanPart;
import org.jetbrains.annotations.NotNull;

public enum KoreanInitialSound implements KoreanConsonant { // Cho Sung (초성) FIRST
    ㄱ(0 , 0 ), G (0 , 0 ), GIEOG  (0 , 0 ),
    ㄲ(1 , 1 ), KK(1 , 1 ), GIEOG_ (1 , 1 ),
    ㄴ(3 , 2 ), N (3 , 2 ), NIEUN  (3 , 2 ),
    ㄷ(6 , 3 ), D (6 , 3 ), DIGEUD (6 , 3 ),
    ㄸ(7 , 4 ), TT(7 , 4 ), DIGEUD_(7 , 4 ),
    ㄹ(8 , 5 ), L (8 , 5 ), LEEUL  (8 , 5 ),
    ㅁ(16, 6 ), M (16, 6 ), MIEUM  (16, 6 ),
    ㅂ(17, 7 ), B (17, 7 ), BIEUB  (17, 7 ),
    ㅃ(18, 8 ), PP(18, 8 ), BIEUB_ (18, 8 ),
    ㅅ(20, 9 ), S (20, 9 ), SIOT   (20, 9 ),
    ㅆ(21, 10), C (21, 10), SIOT_  (21, 10),
    ㅇ(22, 11), E (22, 11), IEUNG  (22, 11),
    ㅈ(23, 12), J (23, 12), JIEUT  (23, 12),
    ㅉ(24, 13), JJ(24, 13), JIEUT_ (24, 13),
    ㅊ(25, 14), CH(25, 14), CHIEUT (25, 14),
    ㅋ(26, 15), K (26, 15), KIEUK  (26, 15),
    ㅌ(27, 16), T (27, 16), TIGEUT (27, 16),
    ㅍ(28, 17), P (28, 17), PIEUP  (28, 17),
    ㅎ(29, 18), H (29, 18), HIEUT  (29, 18);

    private final int id;
    private final int ai;
    KoreanInitialSound(int id, int ai) {
        this.id = id;
        this.ai = ai;
    }
    public int getId() {
        return id;
    }
    public int getAdding() {
        return 44032 + ( ai * 588 );
    }
    public boolean equals(@NotNull KoreanPart part) {
        if(!(part instanceof KoreanConsonant))
            return false;
        return part.getId() == getId();
    }
}
