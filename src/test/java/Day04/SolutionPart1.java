package Day04;

import org.junit.Test;

public class SolutionPart1 {

    private static boolean isViable(int num) {
        byte[] digits = toDigits(num);
        return isAscending(digits) && hasEqualAdjacents(digits);
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

    private static boolean hasEqualAdjacents(byte[] digits) {
        for(int i = 0; i < 5; i++) {
            if (digits[i] == digits[i+1]) {
                return true;
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
    public void testPart1() {
        int min = 367479;
        int max = 893698;

        int viablePasswords = 0;
        for(int password = min; password <= max; password++) {
            if (isViable(password)) {
                viablePasswords ++;
            }
        }

        System.out.println("The solution to day 4 part 1 is:");
        System.out.println(viablePasswords);
    }
}
