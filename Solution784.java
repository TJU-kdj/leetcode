import java.util.*;

public class Solution784 {
    //自己写的39.81%，83.37%
    String source;
    List<String> ans=new ArrayList<>();
    public List<String> letterCasePermutation(String S) {
        source=S;
        IsDigit(0, new StringBuilder());
        return ans;
    }

    void transform(int index,StringBuilder stringBuilder,boolean IsTransform,String s){
        if(IsTransform)stringBuilder.replace(index,index+1,s.toUpperCase());
        else stringBuilder.replace(index,index+1,s.toLowerCase());
        if(index==source.length()-1)ans.add(stringBuilder.toString());
        else {
            index++;
            IsDigit(index, stringBuilder);
        }
    }

    void IsDigit(int index,StringBuilder stringBuilder){
        char c=source.charAt(index);
        String s=Character.toString(c);
        if(!Character.isDigit(c)){
            transform(index, stringBuilder, false,s);
        }
        transform(index, stringBuilder, true,s);
    }

    //别人写的100%，76.84%
    List<String> list;
    public List<String> letterCasePermutation2(String S) {
        list = new ArrayList<>();
        recurse(S.toCharArray(), 0);
        return list;
    }
    
    public void recurse(char[] chars, int idx){
        if(idx == chars.length){
            list.add(new String(chars));
            return;
        }
        recurse(chars, idx + 1);
        if(chars[idx] >= 'A'){
            chars[idx] = chars[idx] < 'a'? (char)(chars[idx] + 32): (char)(chars[idx] - 32);
            recurse(chars, idx + 1);
        }
    }
}