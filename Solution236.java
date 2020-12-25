import otherType.*;
import java.util.*;


/*99.85% 60.86%
class Solution {

    private TreeNode ans;

    public Solution() {
        this.ans = null;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        } 
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }
}*/
//29%,80%
public class Solution236 {
    TreeNode ans;
    int lval = 0;
    int rval = 0;
    int flag = 0;
    int parentVal;
    boolean IsFind = false;
    List<TreeNodeParent> childs = new ArrayList<TreeNodeParent>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lval = p.val;
        rval = q.val;
        transform(root, null);
        TreeNodeParent child = childs.get(0);
        while (child != null) {
            child.IsParent = true;
            child = child.parent;
        }
        child = childs.get(1);
        while (child != null) {
            if (child.IsParent) {
                parentVal = child.val;
                break;
            }
            child = child.parent;
        }
        ans(root);
        return ans;
    }

    void ans(TreeNode node) {
        if (IsFind || node == null) {
            return ;
        } else if (node.val == parentVal) {
            IsFind = true;
            ans=node;
            return ;
        } else {
            ans(node.left);
            ans(node.right);
        }
    }

    TreeNodeParent transform(TreeNode root, TreeNodeParent parent) {
        if (flag == 2||root == null) {
            return null;
        }
        int value = root.val;
        TreeNodeParent result = new TreeNodeParent(value);
        result.parent = parent;
        if (value == rval || value == lval) {
            flag++;
            childs.add(result);
        }
        result.left = transform(root.left, result);
        result.right = transform(root.right, result);
        return result;
    }

    class TreeNodeParent {
        public int val;
        public TreeNodeParent parent;
        public TreeNodeParent left;
        public TreeNodeParent right;
        public boolean IsParent = false;

        public TreeNodeParent(int val) {
            this.val = val;
        }
    }
}