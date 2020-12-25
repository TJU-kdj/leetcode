import java.util.*;


public class Solution844{
    public boolean backspaceCompare(String S, String T) {
        return transform(S).equals(transform(T));
    }

    public String transform(String s)
    {
        char[] CharArray=s.toCharArray();
        String result="";
        Stack<Character> stack=new Stack<Character>();
        int i=0;
        while(i<CharArray.length)
        {
            if(CharArray[i]!='#')
            {
                stack.push(CharArray[i]);
            }
            else if(!stack.empty())
            {
                stack.pop();
            }
            i++;
        }
        while(!stack.empty())
        {
            result+=stack.pop();
        }
        return result;
    }

    public static void main(String[] args)
    {
        Solution844 solution=new Solution844();
        boolean f= solution.backspaceCompare("ab#c", "ab#c");
        System.out.println(f);
    }
}
