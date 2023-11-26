import io.github.kronae.korean.combiner.KoreanChar;
import io.github.kronae.korean.combiner.KoreanCombiner;
import io.github.kronae.korean.combiner.part.consonant.KoreanFinalSound;
import io.github.kronae.korean.combiner.part.consonant.KoreanInitialSound;
import io.github.kronae.korean.combiner.part.vowel.KoreanMidSound;

public class TestKorean {
    public static void main(String[] args) {
        KoreanChar kc = KoreanCombiner.combine(KoreanInitialSound.ㄱ, KoreanMidSound.ㅏ, KoreanFinalSound.ㄿ);
        System.err.println(kc.getFirst());
        System.err.println(kc.getMiddle());
        System.err.println(kc.getFinal());
        System.err.println(kc.getUnicodeCodePoint());
        System.err.println(new KoreanChar(kc.getKoreanCharId()));
        print((short) 0, kc);

        for(short i = -50; i <= 11172; i++) {
            print(i, new KoreanChar(i));
        }
    }

    private static void print(short i, KoreanChar kc) {
        System.out.printf("[%5d] %s: %s\n", i, kc, kc.getRecipe());
    }
}
