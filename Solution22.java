import java.util.*;

//没做出来
class Solution22 {
    int pairNumber=0;
    List<String> ans=new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        StringBuilder sBuilder=new StringBuilder();
        pairNumber=n;
        GenerateAll(0, sBuilder);
        return ans;
    }

    int currentNumber=0;//第几个括号
    void GenerateAll(int start,StringBuilder stringBuilder){
        for(int i=0;i<2*pairNumber-start;i++){
            currentNumber++;currentNumber%=pairNumber;
            System.out.println("("+currentNumber);
            GenerateAll(start, stringBuilder);
        }
    }

    //别人的解法 95.89%   79.13%
    ArrayList<String> strings = null;
    public List<String> generateParenthesis1(int n) {
        strings = new ArrayList<String>();
        generateParenthesis1(n,0,0,"");
        return strings;
    }

    public void generateParenthesis1(int n, int left, int right, String s){
        if(left == n && right == n){
            strings.add(s);
            return;
        }
        if(left<n) generateParenthesis1(n,left+1,right,s+"(");
        if(right<left) generateParenthesis1(n,left,right+1,s+")");
    }

    //方法一：暴力法,时间复杂度：O(2^{2n}n)   33.87%   75.45%
    public List<String> generateParenthesis2(int n) {
        List<String> combinations = new ArrayList<String>();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }
    
    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current)) {
                result.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') {
                ++balance;
            } else {
                --balance;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }
}