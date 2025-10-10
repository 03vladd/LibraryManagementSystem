public class Numbers {
    //roman to arabic
    public int romanToArabic(String roman) {
        int total = 0;

        for (int i = 0; i < roman.length(); i++) {
            char ch = roman.charAt(i);

            if (ch == 'I') {
                total += 1;
            } else if (ch == 'V') {
                total += 5;
            } else if (ch == 'X') {
                total += 10;
            }

        }
        return total;
    }
}