package pl.pmatwiejuk.substring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SubstringTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"", "", true},
                {"", "*", true},
                {"", "**", true},
                {"*", "*", true},
                {"*", "\\*", true},
                {"aaabbb", "aabb", true},
                {"aaaaaaaaabbbbbbbbbcccccccc", "aaaaaaaabbbbbb", true},
                {"abcd", "a*c", true},
                {"abcdefghijk", "*", true},
                {"aaaaaaaaabbbbbbbbbcccccccc", "aab*bbcc", true},
                {"aaaaaaaaabbbbbbbbbcccccccc", "bbccc*ccccc", true},
                {"     aabbbbbbbbbcccccccc", "bbccc*ccccc", true},
                {"aabbccc  ccccc", "bbccc*ccccc", true},
                {"aabb", "*a*a*b*b*", true},
                {"\\*abcde*fghijk", "\\\\**", true},
                {"\\*abcde*fghijk", "\\\\**de\\**k", true},
                {"\\*abcde*\\fghijk", "\\\\**de\\**k", true},
                {"\\*abcde*\\fghijk", "\\\\**de\\*\\", true},

                {"", "a", false},
                {"", "\\*", false},
                {"abcd", "a\\*c", false},
                {"abcd", "ac", false},
                {"aabb", "aaabbb", false},
                {"aabb     cccccccc", "bbccc*ccccc", true},
                {"aabb", "*a*a*a*b*b*b*", false},
                {"abcdefghijk", "ab\\*efg*hi*", false},
                {"aaaaaaaaabbbbbbbbbcccccccc", "aaaaaaaabbbbbbd", false},
                {"aaaaaaaaabbbbbbbbbcccccccc", "bbcccc*ccccc", false},
                {"aaaa\\bcd", "\\\\", false},
                {"\\*abcde\\*fghijk", "\\\\**de\\**k", false}

        });
    }

    @Parameter
    public String text;

    @Parameter(1)
    public String patternToFind;

    @Parameter(2)
    public Boolean expectedResult;

    @Test
    public void substringTest() {
        Boolean result = Substring.test(text, patternToFind);
        assertEquals("Expected substring to return " + expectedResult + ", but was " + result,
                expectedResult, result);
    }
}
