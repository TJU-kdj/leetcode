import java.util.*;

//自己做出来了但是效率很低
public class Solution726 {
    /* Stack<Pair> stack = new Stack<>();
    Map<String, Integer> map = new TreeMap<>();
    StringBuilder stringBuilder;
    Pair tempPair;
    int i = 0;

    public String countOfAtoms(String formula) {
        stringBuilder = new StringBuilder(formula);
        StringBuilder result = new StringBuilder();
        
        while (i < stringBuilder.length()) {
            // 读取元素
            StringBuilder atom = new StringBuilder();
            atom.append(stringBuilder.charAt(i));
            while (i + 1 < stringBuilder.length() && (int) stringBuilder.charAt(i + 1) > 96) {
                i++;
                atom.append(stringBuilder.charAt(i));
            }
            String string = atom.toString();

            if (string.equals("(")) {
                Pair pair = new Pair(string, -1);
                stack.add(pair);
            } else if (string.equals(")")) {
                
                if (++i<stringBuilder.length()&&tryConvertToInteger(Character.toString(stringBuilder.charAt(i))) != -1) {// 证明后一个是数字
                    char c_after = stringBuilder.charAt(i);
                    StringBuilder number = new StringBuilder();
                    number.append(c_after);
                    int factor = getNumber(i, number);
                    CountAtomsNumber(factor);
                } else {
                    CountAtomsNumber(1);
                    i--;
                }
            } else {// 可能是字母也可能是数字
                if (tryConvertToInteger(string) == -1) {// 证明是字母
                    Pair pair;
                    if (++i < stringBuilder.length()) {// 证明i没有超出界限
                        char c_after = stringBuilder.charAt(i);
                        if (tryConvertToInteger(Character.toString(c_after)) != -1) {// 证明后一个是数字
                            StringBuilder number = new StringBuilder();
                            number.append(c_after);
                            int factor = getNumber(i, number);
                            pair = new Pair(string, factor);
                        } else {
                            i--;
                            pair = new Pair(string, 1);
                        }
                    } else {
                        pair = new Pair(string, 1);
                    }
                    stack.add(pair);
                }
            }
            i++;
        }
        // 全都出栈进行计算最终原子数目
        while (!stack.empty()) {
            Pair pair = stack.pop();
            if (map.containsKey(pair.key)) {
                map.put(pair.key, map.get(pair.key) + pair.value);
            } else {
                map.put(pair.key, pair.value);
            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            result.append(entry.getKey());
            int value = entry.getValue();
            if (value != 1) {
                result.append(entry.getValue());
            }
        }
        return result.toString();
    }

    class Pair {
        public int value = 0;
        public String key;

        public Pair(String c, int i) {
            value = i;
            key = c;
        }
    }

    // 匹配到右括号,把前面的拿出来进行计算,factor为右括号后的数字
    void CountAtomsNumber(int factor) {
        Stack<Pair> tempStack = new Stack<>();
        Pair pair = stack.pop();
        while (!pair.key.equals("(")) {
            pair.value *= factor;
            tempStack.add(pair);
            pair = stack.pop();
        }

        while (!tempStack.empty()) {
            stack.add(tempStack.pop());
        }
    }

    // 把字符转换为数字,成功则返回整数,否则返回-1
    int tryConvertToInteger(String c) {
        try {
            int i = Integer.parseInt(c);
            return i;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // 获取数字
    int getNumber(int i, StringBuilder number) {
        // 获取数字
        while (i + 1 < stringBuilder.length()
                && tryConvertToInteger(Character.toString(stringBuilder.charAt(i + 1))) != -1) {
            i++;
            number.append(stringBuilder.charAt(i));
        }
        return Integer.parseInt(number.toString());
    } */

/*正则表达式方法，代码最短
import java.util.regex.*;

class Solution {
    public String countOfAtoms(String formula) {
        Matcher matcher = Pattern.compile("([A-Z][a-z]*)(\\d*)|(\\()|(\\))(\\d*)").matcher(formula);
        Stack<Map<String, Integer>> stack = new Stack();
        stack.push(new TreeMap());

        while (matcher.find()) {
            String match = matcher.group();
            if (match.equals("(")) {
                stack.push(new TreeMap());
            } else if (match.startsWith(")")) {
                Map<String, Integer> top = stack.pop();
                int multiplicity = match.length() > 1 ? Integer.parseInt(match.substring(1, match.length())) : 1;
                for (String name: top.keySet()) {
                    stack.peek().put(name, stack.peek().getOrDefault(name, 0) + top.get(name) * multiplicity);
                }
            } else {
                int i = 1;
                while (i < match.length() && Character.isLowerCase(match.charAt(i))) {
                    i++;
                }
                String name = match.substring(0, i);
                int multiplicity = i < match.length() ? Integer.parseInt(match.substring(i, match.length())) : 1;
                stack.peek().put(name, stack.peek().getOrDefault(name, 0) + multiplicity);
            }
        }

        StringBuilder ans = new StringBuilder();
        for (String name: stack.peek().keySet()) {
            ans.append(name);
            final int count = stack.peek().get(name);
            if (count > 1) ans.append(String.valueOf(count));
        }
        return ans.toString();
    */

    //递归算法
    int i;
    public String countOfAtoms(String formula) {
        StringBuilder ans = new StringBuilder();
        i = 0;
        Map<String, Integer> count = parse(formula);
        for (String name: count.keySet()) {
            ans.append(name);
            int multiplicity = count.get(name);
            if (multiplicity > 1) ans.append("" + multiplicity);
        }
        return new String(ans);
    }

    public Map<String, Integer> parse(String formula) {
        int N = formula.length();
        Map<String, Integer> count = new TreeMap<>();
        while (i < N && formula.charAt(i) != ')') {
            if (formula.charAt(i) == '(') {
                i++;
                for (Map.Entry<String, Integer> entry: parse(formula).entrySet()) {
                    count.put(entry.getKey(), count.getOrDefault(entry.getKey(), 0) + entry.getValue());
                }
            } else {
                int iStart = i++;
                while (i < N && Character.isLowerCase(formula.charAt(i))) i++;
                String name = formula.substring(iStart, i);
                iStart = i;
                while (i < N && Character.isDigit(formula.charAt(i))) i++;
                int multiplicity = iStart < i ? Integer.parseInt(formula.substring(iStart, i)) : 1;
                count.put(name, count.getOrDefault(name, 0) + multiplicity);
            }
        }
        int iStart = ++i;
        while (i < N && Character.isDigit(formula.charAt(i))) i++;
        if (iStart < i) {
            int multiplicity = Integer.parseInt(formula.substring(iStart, i));
            for (String key: count.keySet()) {
                count.put(key, count.get(key) * multiplicity);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Solution726 solution729 = new Solution726();
        String s = solution729.countOfAtoms("(H)");
        System.out.println(s);
    }
}
