package Day04;

import org.junit.Assert;
import org.junit.Test;

public class SolutionPart2 {

    private static boolean isViable(int num) {
        byte[] digits = toDigits(num);
        return isAscending(digits) && has2EqualAdjacents(digits);
    }

    private static byte[] toDigits(int num) {
        return new byte[] {
                (byte) ( (num / 100000) % 10),
                (byte) ( (num / 10000) % 10),
                (byte) ( (num / 1000) % 10),
                (byte) ( (num / 100) % 10),
                (byte) ( (num / 10) % 10),
                (byte) ( num % 10),
        };
    }

    private static boolean has2EqualAdjacents(byte[] digits) {
        for(int i = 0; i < 5; i++) {
            if (digits[i] == digits[i+1]) {
                if ( (i == 4 || digits[i+2] != digits[i]) && (i == 0 || digits[i-1] != digits[i])) {
                    return true;
                }

                //move two steps
                i += 2;
            }
        }
        return false;
    }

    private static boolean isAscending(byte[] digits) {
        for(int i = 0; i < 5; i++) {
            if (digits[i] > digits[i+1]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testPart2() {
        int min = 367479;
        int max = 893698;

        int viablePasswords = 0;
        for(int password = min; password <= max; password++) {
            if (isViable(password)) {
                viablePasswords ++;
            }
        }

        System.out.println("The solution to day 4 part 2 is:");
        System.out.println(viablePasswords);
    }

    @Test
    public void testHas2Adjacents() {
        Assert.assertTrue(has2EqualAdjacents(toDigits(112222)));
        Assert.assertTrue(has2EqualAdjacents(toDigits(211222)));
        Assert.assertTrue(has2EqualAdjacents(toDigits(221122)));
        Assert.assertTrue(has2EqualAdjacents(toDigits(222112)));
        Assert.assertTrue(has2EqualAdjacents(toDigits(222211)));
        Assert.assertTrue(has2EqualAdjacents(toDigits(112233)));
        Assert.assertTrue(has2EqualAdjacents(toDigits(111122)));
        Assert.assertTrue(has2EqualAdjacents(toDigits(788999)));

        Assert.assertFalse(has2EqualAdjacents(toDigits(111222)));
        Assert.assertFalse(has2EqualAdjacents(toDigits(211112)));
        Assert.assertFalse(has2EqualAdjacents(toDigits(111111)));
        Assert.assertFalse(has2EqualAdjacents(toDigits(211111)));
        Assert.assertFalse(has2EqualAdjacents(toDigits(111112)));
        Assert.assertFalse(has2EqualAdjacents(toDigits(123444)));
        Assert.assertFalse(has2EqualAdjacents(toDigits(123456)));
    }
}
