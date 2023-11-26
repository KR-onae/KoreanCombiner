package com.kronae.korean.combiner;

import com.kronae.korean.combiner.annotation.StringLimit;
import com.kronae.korean.combiner.part.KoreanPart;
import com.kronae.korean.combiner.part.consonant.KoreanFinalSound;
import com.kronae.korean.combiner.part.consonant.KoreanInitialSound;
import com.kronae.korean.combiner.part.vowel.KoreanMidSound;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

public class KoreanChar {
    private final int codePoint;
    private @Nullable KoreanPart c;
    private @Nullable KoreanMidSound m;
    private @Nullable KoreanFinalSound f;

    /**
     * It takes one Korean character and turns it into a KoreanChar.
     * @param character Korean one letter
     */
    public KoreanChar(@StringLimit(length=1) @NotNull String character) {
        if(character.length() != 1)
            throw new IllegalArgumentException("KoreanChar must be 1 character.");

        this.codePoint = character.codePointAt(0);
        checkCodePoint();
    }

    /**
     * KoreanChar is created using Unicode codePoint.
     * Range: 12593~12643 & 44032~55203
     * @param codePoint Unicode codePoint
     */
    public KoreanChar(int codePoint) {
        this.codePoint = codePoint;
        checkCodePoint();
    }

    /**
     * Create KoreanChar instance with KoreanChar index.
     * @param index -50 ~ -21   : ㄱ(G) ~ ㅎ(H)
     *              -20 ~ 0     : ㅏ(A) ~ ㅣ(I)
     *              1   ~ 11172 : 가(GA) ~ 힣(HIH)
     */
    public KoreanChar(@Range(from=-50, to=11172) short index) {
        if(index <= 0) {
            this.codePoint = 12593 + 50 + index;
        } else {
            this.codePoint = 44032 -  1 + index;
        }
        checkCodePoint();
    }

    /**
     * This method check that the codepoint is korean.
     */
    private void checkCodePoint() {
        if(!((12593 <= codePoint && codePoint <= 12643) || (44032 <= codePoint && codePoint <= 55203)))
            throw new IllegalArgumentException("This is not modern Korean(" + codePoint + ").");
    }

    /**
     * Convert KoreanChar to Korean Hangul characters.
     * @return Korean one letter
     */
    @Override
    public @StringLimit(length=1) String toString() {
        return Character.toString(codePoint);
    }

    /**
     * If getMiddle() is null, it returns a consonant, vowel, or neuter. (KoreanPart)
     * If getMiddle() is not null, it returns the initial consonant. (KoreanInitialSound)
     * @return KoreanPart
     */
    @NotNull
    public KoreanPart getFirst() {
        if(c == null) {
            if(12593 <= codePoint && codePoint <= 12643) {
                int charId = codePoint - 12593;
                c = id2part(charId);
                return c;
            }

            int charId = codePoint - 44032;
            c = iid2i(charId / 588);
        }
        return c;
    }

    /**
     * Returns null if neuter is not combined.
     * If not, returns neuter.
     * @return KoreanMidSound
     */
    @Nullable
    public KoreanMidSound getMiddle() {
        if(m == null) {
            if(12593 <= codePoint && codePoint <= 12643)
                return null;

            int charId = codePoint - 44032;
            int c_last = charId % 588;

            KoreanPart part = id2part(c_last / 28 + 30);
            if(part instanceof KoreanMidSound kms) {
                m = kms;
            } else {
                throw new RuntimeException("Unknown error: This is NOT a KoreanMidSound. (" + part + ")");
            }
        }
        return m;
    }

    /**
     * If the final consonant is not combined, null is returned.
     * If not, it returns the final consonant.
     * @return KoreanFinalSound
     */
    @Nullable
    public KoreanFinalSound getFinal() {
        if(f == null) {
            if(12593 <= codePoint && codePoint <= 12643)
                return null;

            int charId = codePoint - 44032;
            int c_last = charId % 588;
            int m_last = c_last % 28;

            f = fid2f(m_last);
        }
        return f;
    }

    /**
     * The combination of initial, medial and final consonants is listed based on the symbol +.
     * At this time, if the value is empty, it is not written down.
     * Ex) Kew -> ㄲ+ㅞ
     * Ex) Chicken -> ㄷ+ㅏ+ㄺ
     * @return the RECIPE
     */
    public String getRecipe() {
        return  getMiddle() == null ? getFirst().toString() :
                getFinal()  == null ? getFirst() + "+" + getMiddle().toString() :
                getFirst() + "+" + getMiddle().toString() + "+" + getFinal().toString();
    }

    /**
     * Convert charId to KoreanPart
     */
    @Contract(pure = true)
    private static @NotNull KoreanPart id2part(@Range(from=0, to=50) int charId) {
        return  charId == 0  ? KoreanInitialSound.ㄱ :
                charId == 1  ? KoreanInitialSound.ㄲ :
                charId == 2  ? KoreanFinalSound  .ㄳ :
                charId == 3  ? KoreanInitialSound.ㄴ :
                charId == 4  ? KoreanFinalSound  .ㄵ :
                charId == 5  ? KoreanFinalSound  .ㄶ :
                charId == 6  ? KoreanInitialSound.ㄷ :
                charId == 7  ? KoreanInitialSound.ㄸ :
                charId == 8  ? KoreanInitialSound.ㄹ :
                charId == 9  ? KoreanFinalSound  .ㄺ :
                charId == 10 ? KoreanFinalSound  .ㄻ :
                charId == 11 ? KoreanFinalSound  .ㄼ :
                charId == 12 ? KoreanFinalSound  .ㄽ :
                charId == 13 ? KoreanFinalSound  .ㄾ :
                charId == 14 ? KoreanFinalSound  .ㄿ :
                charId == 15 ? KoreanFinalSound  .ㅀ :
                charId == 16 ? KoreanInitialSound.ㅁ :
                charId == 17 ? KoreanInitialSound.ㅂ :
                charId == 18 ? KoreanInitialSound.ㅃ :
                charId == 19 ? KoreanFinalSound  .ㅄ :
                charId == 20 ? KoreanInitialSound.ㅅ :
                charId == 21 ? KoreanInitialSound.ㅆ :
                charId == 22 ? KoreanInitialSound.ㅇ :
                charId == 23 ? KoreanInitialSound.ㅈ :
                charId == 24 ? KoreanInitialSound.ㅉ :
                charId == 25 ? KoreanInitialSound.ㅊ :
                charId == 26 ? KoreanInitialSound.ㅋ :
                charId == 27 ? KoreanInitialSound.ㅌ :
                charId == 28 ? KoreanInitialSound.ㅍ :
                charId == 29 ? KoreanInitialSound.ㅎ :
                charId == 30 ? KoreanMidSound    .ㅏ :
                charId == 31 ? KoreanMidSound    .ㅐ :
                charId == 32 ? KoreanMidSound    .ㅑ :
                charId == 33 ? KoreanMidSound    .ㅒ :
                charId == 34 ? KoreanMidSound    .ㅓ :
                charId == 35 ? KoreanMidSound    .ㅔ :
                charId == 36 ? KoreanMidSound    .ㅕ :
                charId == 37 ? KoreanMidSound    .ㅖ :
                charId == 38 ? KoreanMidSound    .ㅗ :
                charId == 39 ? KoreanMidSound    .ㅘ :
                charId == 40 ? KoreanMidSound    .ㅙ :
                charId == 41 ? KoreanMidSound    .ㅚ :
                charId == 42 ? KoreanMidSound    .ㅛ :
                charId == 43 ? KoreanMidSound    .ㅜ :
                charId == 44 ? KoreanMidSound    .ㅝ :
                charId == 45 ? KoreanMidSound    .ㅞ :
                charId == 46 ? KoreanMidSound    .ㅟ :
                charId == 47 ? KoreanMidSound    .ㅠ :
                charId == 48 ? KoreanMidSound    .ㅡ :
                charId == 49 ? KoreanMidSound    .ㅢ :
                               KoreanMidSound    .ㅣ ;
    }
    /**
     * Convert adding Id to KoreanFinalSound
     */
    @Contract(pure = true)
    private static @NotNull KoreanFinalSound fid2f(@Range(from=1, to=27) int ai) {
        return  ai == 1  ? KoreanFinalSound.ㄱ :
                ai == 2  ? KoreanFinalSound.ㄲ :
                ai == 3  ? KoreanFinalSound.ㄳ :
                ai == 4  ? KoreanFinalSound.ㄴ :
                ai == 5  ? KoreanFinalSound.ㄵ :
                ai == 6  ? KoreanFinalSound.ㄶ :
                ai == 7  ? KoreanFinalSound.ㄷ :
                ai == 8  ? KoreanFinalSound.ㄹ :
                ai == 9  ? KoreanFinalSound.ㄺ :
                ai == 10 ? KoreanFinalSound.ㄻ :
                ai == 11 ? KoreanFinalSound.ㄼ :
                ai == 12 ? KoreanFinalSound.ㄽ :
                ai == 13 ? KoreanFinalSound.ㄾ :
                ai == 14 ? KoreanFinalSound.ㄿ :
                ai == 15 ? KoreanFinalSound.ㅀ :
                ai == 16 ? KoreanFinalSound.ㅁ :
                ai == 17 ? KoreanFinalSound.ㅂ :
                ai == 18 ? KoreanFinalSound.ㅄ :
                ai == 19 ? KoreanFinalSound.ㅅ :
                ai == 20 ? KoreanFinalSound.ㅆ :
                ai == 21 ? KoreanFinalSound.ㅇ :
                ai == 22 ? KoreanFinalSound.ㅈ :
                ai == 23 ? KoreanFinalSound.ㅊ :
                ai == 24 ? KoreanFinalSound.ㅋ :
                ai == 25 ? KoreanFinalSound.ㅌ :
                ai == 26 ? KoreanFinalSound.ㅍ :
                           KoreanFinalSound.ㅎ ;
    }
    /**
     * Convert charId to KoreanInitialSound
     */
    @Contract(pure = true)
    private static @NotNull KoreanInitialSound iid2i(@Range(from=0, to=18) int charId) {
        return  charId == 0  ? KoreanInitialSound.ㄱ :
                charId == 1  ? KoreanInitialSound.ㄲ :
                charId == 2  ? KoreanInitialSound.ㄴ :
                charId == 3  ? KoreanInitialSound.ㄷ :
                charId == 4  ? KoreanInitialSound.ㄸ :
                charId == 5  ? KoreanInitialSound.ㄹ :
                charId == 6  ? KoreanInitialSound.ㅁ :
                charId == 7  ? KoreanInitialSound.ㅂ :
                charId == 8  ? KoreanInitialSound.ㅃ :
                charId == 9  ? KoreanInitialSound.ㅅ :
                charId == 10 ? KoreanInitialSound.ㅆ :
                charId == 11 ? KoreanInitialSound.ㅇ :
                charId == 12 ? KoreanInitialSound.ㅈ :
                charId == 13 ? KoreanInitialSound.ㅉ :
                charId == 14 ? KoreanInitialSound.ㅊ :
                charId == 15 ? KoreanInitialSound.ㅋ :
                charId == 16 ? KoreanInitialSound.ㅌ :
                charId == 17 ? KoreanInitialSound.ㅍ :
                               KoreanInitialSound.ㅎ ;
    }

    /**
     * @return Returns Unicode Codepoint of the KoreanChar.
     */
    public int getUnicodeCodePoint() {
        return codePoint;
    }
    /*
    public KoreanChar(@Range(from=-50, to=11172) short index) {
        if(index <= 0) {
            this.codePoint = 12593 + 50 + index;
        } else {
            this.codePoint = 44032 -  1 + index;
        }
        checkCodePoint();
    }
     */
    public short getKoreanCharId() {
        if(12593 <= codePoint && codePoint <= 12643)
            return (short) -(codePoint - 12593 - 50);

        return (short) (codePoint - 44032 + 1);
    }
}
