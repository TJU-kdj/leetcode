import otherType.*;

public class Solution725 {
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] ans=new ListNode[k];
        int length=0;
        ListNode input=root;
        while(root!=null){
            length++;
            root=root.next;
        }
        int remainder=length%k,eachCount=length/k;
        for(int i=0;i<k;i++){
            if(input==null){
                ans[i]=null;
            }else{
                root=input;
                ListNode head=root;
                int n=eachCount+((remainder--<=0)? 0:1);
                for(int j=0;j<n-1&&input!=null;j++){
                    input=root=root.next;
                }
                input=root.next;
                root.next=null;
                ans[i]=head;
            }
        }
        return ans;
    }
}