package com.kronae.korean.combiner;

import com.kronae.korean.combiner.annotation.StringLimit;
import com.kronae.korean.combiner.part.consonant.KoreanFinalSound;
import com.kronae.korean.combiner.part.vowel.KoreanMidSound;
import com.kronae.korean.combiner.part.KoreanPart;
import com.kronae.korean.combiner.part.vowel.KoreanVowel;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class KoreanCombiner {
    /**
     * The entered Korean syllables are combined into KoreanChar.
     * You can combine like: I, M, F, I+M, I+M+F
     * @param i When two or more are combined, the initial consonant comes here. If you combine just one, consonants, vowels, and final consonants can come here.
     * @param m If parameter i is written, this can be not null. Here, the neuter consonant is used.
     * @param f If both i and m are written, this can not be null. Here, the final consonant is used.
     * @return The entered Korean syllables are combined into KoreanChar.
     * At this time, you can use KoreanChar.toString() to check one Korean character combined.
     */
    @Contract("_, null, !null -> fail")
    public static @NotNull KoreanChar combine(@NotNull KoreanPart i, @Nullable KoreanMidSound m, @Nullable KoreanFinalSound f) {
        if(m == null && f != null)
            throw new IllegalArgumentException("Korean cannot combine with InitialSound + FinalSound.");

        if(f == null) {
            if(m == null) {
                return new KoreanChar(12593 + i.getId());
            }

            if(i instanceof KoreanFinalSound)
                throw new IllegalArgumentException("Korean cannot combine with FinalSound + MidSound.");

            if(i instanceof KoreanVowel)
                throw new IllegalArgumentException("Korean cannot combine with KoreanVowel + MidSound.");

            return new KoreanChar(i.getAdding() + m.getAdding());
        }

        if(i instanceof KoreanFinalSound)
            throw new IllegalArgumentException("Korean cannot combine with FinalSound + MidSound + FinalSound.");

        if(i instanceof KoreanVowel)
            throw new IllegalArgumentException("Korean cannot combine with KoreanVowel + MidSound + FinalSound.");

        return new KoreanChar(i.getAdding() + m.getAdding() + f.getAdding());
    }
}
