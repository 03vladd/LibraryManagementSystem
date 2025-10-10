public class  MatheAufgabe {
    // addition zweier Zahlen
    public double sum(int x, int y) {
        return x + y;
    }

    //subtraktion zweier Zahlen
    public double sub(int x, int y) {
        return x - y;
    }

    //multiplikation zweier Zahlen mit sum
    public int mul(int x, int y) {
        int result = 0;

        for (int i = 0; i < y; i++) {
            result = (int) sum(result, x);
        }
        return result;
    }

    public boolean perfectNumber(int n) {
        if (n < 1) {
            return false;
        }

        int sum = 0;

        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }

        return sum == n;
    }
}
