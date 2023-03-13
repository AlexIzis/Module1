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

    // +2 8 kyu
    /*
    https://www.codewars.com/kata/551b4501ac0447318f0009cd
     */
    public static String convert(boolean b) {
        return String.valueOf(b);
    }

    /*
    https://www.codewars.com/kata/57a2013acf1fa5bfc4000921
     */
    public static double find_average(int[] array) {
        int result = 0;
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                result += array[i];
            }

            return result / array.length;
        }

        return 0;
    }

    // +2 7 kyu
    /*
    https://www.codewars.com/kata/5296455e4fe0cdf2e000059f
     */
    public static Double calculate(final double numberOne, final String operation, final double numberTwo)
    {
        switch (operation) {
            case "+" :
                return numberOne + numberTwo;
            case "-" :
                return numberOne - numberTwo;
            case "*" :
                return numberOne * numberTwo + 0.0;
            case "/" :
                return numberTwo != 0 ? numberOne / numberTwo : null;
        }
        return null;
    }

    /*
    https://www.codewars.com/kata/57aa218e72292d98d500240f
     */
    public static double heron(double a, double b, double c)
    {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    // +1 8 kyu
    /*
    https://www.codewars.com/kata/5208f99aee097e6552000148
     */
    public static String camelCase(String input) {
        return input.replaceAll("([A-Z])", " $1");
    }
}
