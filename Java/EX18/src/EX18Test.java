import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class EX18Test {
    public EX18Interface ex18Interface;
    static List<Object> manyEX18;

    @Parameterized.Parameters
    public static Collection<Object> instancesToTest() {
        if (manyEX18 == null) return Arrays.asList(new EX18());
        return manyEX18;
    }

    /***/
    public EX18Test(EX18Interface ex18Interface) {
        this.ex18Interface = ex18Interface;
    }

    /***/
    @Test
    public void TestEncryptNull() {
        assertEquals(null, ex18Interface.encrypt(null, 1));
    }

    /***/
    @Test
    public void TestEncryptOneLetter() {
        assertEquals("", ex18Interface.encrypt("a", 1));
    }

    /***/
    @Test
    public void TestEncryptSimple() {
        assertEquals("cdef", ex18Interface.encrypt("abcde", 1));
    }


    /***/
    @Test
    public void TestEncryptZeroRot() {
        assertEquals("bcde", ex18Interface.encrypt("abcde", 0));
    }


    /***/
    @Test
    public void TestEncryptZeroRotCapitalLetters() {
        assertEquals("i m the rel slim shdy", ex18Interface.encrypt("I AM THE REAL SLIM SHADY", 0));
    }

    /***/
    @Test
    public void TestEncryptZtoA() {
        assertEquals("b", ex18Interface.encrypt("az", 1));
    }

    /***/
    @Test
    public void TestEncryptZtoABigRot() {
        assertEquals("b", ex18Interface.encrypt("az", 27));
    }

    /***/
    @Test
    public void TestEncryptZtoAMixed() {
        assertEquals("pdo o ikpdan nqoow! p o pda bjwh ykqjpzksj!!!", ex18Interface.encrypt("THIS IS MOTHER RUSSIA! IT IS THE FINAL COUNTDOWN!!!", 22));
    }

    /***/
    @Test
    public void TestDecryptNull() {
        assertEquals(null, ex18Interface.decrypt(null, 1));
    }

    /***/
    @Test
    public void TestDecryptZeroRot() {
        assertEquals("this is the rhytm of the life", ex18Interface.decrypt("this is the rhytm of the life", 0));
    }

    /***/
    @Test
    public void TestDecryptSimple() {
        assertEquals("gdkkn", ex18Interface.decrypt("hello", 1));
    }

    /***/
    @Test
    public void TestDecryptSimpleManyWords() {
        assertEquals("gdkkn fnnc cdx", ex18Interface.decrypt("hello good dey", 1));
    }

    /***/
    @Test
    public void TestDecryptAtoZ() {
        assertEquals("z", ex18Interface.decrypt("a", 1));
    }

    /***/
    @Test
    public void TestDecryptAtoZManyWords() {
        assertEquals("jmrh e gef", ex18Interface.decrypt("find a cab", 22));
    }

    /***/
    @Test
    public void TestDecryptSimpleBigRot() {
        assertEquals("gdkkn", ex18Interface.decrypt("hello", 27));
    }

    /***/
    @Test
    public void TestDecryptAtoZManyWordsBigRot() {
        assertEquals("jmrh e gef", ex18Interface.decrypt("find a cab", 22));
    }


    /***/
    @Test
    public void TestFindMostFrequentlyOccurringLetterNull() {
        assertEquals(null, ex18Interface.findMostFrequentlyOccurringLetter(null));
    }

    /***/
    @Test
    public void TestFindMostFrequentlyOccurringLetterSimple() {
        assertEquals("o", ex18Interface.findMostFrequentlyOccurringLetter("look for the omen"));
    }

    /***/
    @Test
    public void TestFindMostFrequentlyOccurringLetterSameSize() {
        assertEquals("i", ex18Interface.findMostFrequentlyOccurringLetter("this is sin"));
    }

    /***/
    @Test
    public void TestFindMostFrequentlyOccurringLetterCapitalLetters() {
        assertEquals("o", ex18Interface.findMostFrequentlyOccurringLetter("WHO LET THE DOGS OUT"));
    }

    /***/
    @Test
    public void TestFindMostFrequentlyOccurringLetterMixed() {
        assertEquals("e", ex18Interface.findMostFrequentlyOccurringLetter("ThEsE aRe ThE DoNuTs ThInGs"));
    }

    /***/
    @Test
    public void TestFindMostFrequentlyOccurringLetterSingleWord() {
        assertEquals("l", ex18Interface.findMostFrequentlyOccurringLetter("Hallelujah"));
    }

    /***/
    @Test
    public void TestMinimizeTextNull() {
        assertEquals(null, ex18Interface.minimizeText(null));
    }

    /***/
    @Test
    public void TestMinimizeTextSimple() {
        assertEquals("heo", ex18Interface.minimizeText("hello"));
    }

    /***/
    @Test
    public void TestMinimizeTextNullSimpleManyWords() {
        assertEquals("y nae is what, y nae is who, y nae is sli-shady",
                ex18Interface.minimizeText("my name is what, my name is who, my name is slim-shady"));
    }

    /***/
    @Test
    public void TestMinimizeTextSameSizes() {
        assertEquals("th nd is nigh", ex18Interface.minimizeText("the end is nigh"));
    }

    /***/
    @Test
    public void TestMinimizeTextCapital() {
        assertEquals("yu t brutus", ex18Interface.minimizeText("you too Brutus"));
    }

    /***/
    @Test
    public void TestMinimizeTextNonAlphabetic() {
        assertEquals("!@#$%^", ex18Interface.minimizeText("!@#$%^"));
    }

    /***/
    @Test
    public void TestMinimizeTextNonAlphabeticWithSpaces() {
        assertEquals("!@#$%^ !@#$%^", ex18Interface.minimizeText("!@#$%^ !@#$%^"));
    }
}
