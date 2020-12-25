import java.util.HashMap;
import java.util.*;

class Solution166 {
    static final public int MAXSIZE = 10000;

    // n为分子,d为分母
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder result = new StringBuilder();
        long n = Long.parseLong(Integer.toString(numerator));
        long d = Long.parseLong(Integer.toString(denominator));
        if (numerator < 0 ^ denominator < 0) {
            result.append("-");
        }
        n = Math.abs(n);
        d = Math.abs(d);
        long reminder = n % d;
        result.append(Long.toString(n / d));
        if (reminder == 0) {
            return result.toString();
        }
        result.append(".");
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        while (reminder != 0) {
            if (map.containsKey(reminder)) {
                result.insert(map.get(reminder), "(");
                result.append(")");
                break;
            }
            map.put(reminder, result.length());
            reminder *= 10;
            result.append(Long.toString(reminder / d));
            reminder = reminder % d;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Solution166 solution166 = new Solution166();
        System.out.println(solution166.fractionToDecimal(-50, 8));
    }
}