package com.kronae.korean.combiner.part.vowel;

import com.kronae.korean.combiner.part.KoreanPart;
import com.kronae.korean.combiner.part.consonant.KoreanConsonant;
import org.jetbrains.annotations.NotNull;

public enum KoreanMidSound implements KoreanVowel { // Jung Sung (중성) SECOND(MIDDLE)
    ㅏ(30), A  (30),
    ㅐ(31), AE (31),
    ㅑ(32), YA (32),
    ㅒ(33), YAE(33),
    ㅓ(34), EO (34),
    ㅔ(35), E  (35),
    ㅕ(36), YEO(36),
    ㅖ(37), YE (37),
    ㅗ(38), O  (38),
    ㅘ(39), WA (39),
    ㅙ(40), WAE(40),
    ㅚ(41), OE (41),
    ㅛ(42), YO (42),
    ㅜ(43), U  (43),
    ㅝ(44), WO (44),
    ㅞ(45), WE (45),
    ㅟ(46), WI (46),
    ㅠ(47), YU (47),
    ㅡ(48), EU (48),
    ㅢ(49), UI (49),
    ㅣ(50), I  (50);
    private final int id;
    KoreanMidSound(int id) {
        this.id = id;
    }
    public int getAdding() {
        return 28*(id-30);
    }

    @Override
    public int getId() {
        return id;
    }
    public boolean equals(@NotNull KoreanPart part) {
        if(!(part instanceof KoreanVowel))
            return false;
        return part.getId() == getId();
    }
}
