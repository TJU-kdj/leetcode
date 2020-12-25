import java.util.*;

//贪心算法(别人的答案)
public class Solution1405 {

    public String longestDiverseString(int a, int b, int c) {
        MyChar[] myChars = new MyChar[] { new MyChar('a', a), new MyChar('b', b), new MyChar('c', c), };
        StringBuilder sb = new StringBuilder();
        while (true) {
            Arrays.sort(myChars);
            // 先放最多的, 如果上个放的2个字符串和剩余个数最多的字符相同，则放置次多的字符
            if (sb.length() >= 2 && sb.charAt(sb.length() - 1) == myChars[2].ch
                    && sb.charAt(sb.length() - 2) == myChars[2].ch) {
                if (myChars[1].count-- > 0) {
                    sb.append(myChars[1].ch);
                } else {
                    break;
                }

            } else {
                if (myChars[2].count-- > 0) {
                    sb.append(myChars[2].ch);
                } else {
                    break;
                }
            }

        }

        return sb.toString();
    }

    private class MyChar implements Comparable<MyChar> {
        char ch;
        int count;

        public MyChar(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }

        @Override
        public int compareTo(MyChar o) {
            return this.count - o.count;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution1405().longestDiverseString(4, 7, 2));
    }
}
