package com.example.module1;

public class Tasks {

    /*
    https://www.codewars.com/kata/5412509bd436bd33920011bc
     */
    public static String maskify(String str) {
        if (str.length() <= 4) {
            return str;
        }
        char[] chars = str.toCharArray();
        for (int i = 0; i < str.length() - 4; i++) {
            chars[i] = '#';
        }
        return new String(chars);
    }

    /*
    https://www.codewars.com/kata/5679aa472b8f57fb8c000047
     */
    public static int findEvenIndex(int[] arr) {
        int sum = 0;
        int count = 0;
        int right = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            right += arr[i];
            if (i > 0) {
                count += arr[i - 1];
                System.out.println(count + " " + (sum - right));
                if (count == (sum - right)) {
                    return i;
                }
            } else if (sum - arr[0] == 0)
                return 0;
        }
        return -1;
    }

    /*
    https://www.codewars.com/kata/5727bb0fe81185ae62000ae3
     */
    public String cleanString(String s) {
        String output = "";
        int j = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '#') {
                output += chars[i];
                j++;
            } else if (!output.equals("")) {
                output = output.substring(0, j - 1);
                j--;
            }
        }
        return output;
    }

    /*
    https://www.codewars.com/kata/525f50e3b73515a6db000b83
     */
    public static String createPhoneNumber(int[] numbers) {
        return String.format("(%d%d%d) %d%d%d-%d%d%d%d",
                numbers[0], numbers[1], numbers[2], numbers[3], numbers[4], numbers[5], numbers[6],
                numbers[7], numbers[8], numbers[9]);
    }

    /*
    https://www.codewars.com/kata/56a3f08aa9a6cc9b75000023
     */
    public static boolean validateUsr(String s) {
        return s.matches("[a-z0-9_]{4,16}");
    }
}
