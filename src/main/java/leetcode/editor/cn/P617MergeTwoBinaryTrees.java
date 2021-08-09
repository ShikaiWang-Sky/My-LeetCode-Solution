//给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。 
//
// 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点
//。 
//
// 示例 1: 
//
// 
//输入: 
//	Tree 1                     Tree 2                  
//          1                         2                             
//         / \                       / \                            
//        3   2                     1   3                        
//       /                           \   \                      
//      5                             4   7                  
//输出: 
//合并后的树:
//	     3
//	    / \
//	   4   5
//	  / \   \ 
//	 5   4   7
// 
//
// 注意: 合并必须从两个树的根节点开始。 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 747 👎 0

package leetcode.editor.cn;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

//Java：合并二叉树
public class P617MergeTwoBinaryTrees {
    public static void main(String[] args) {
        Solution solution = new P617MergeTwoBinaryTrees().new Solution();
        // TO TEST
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            if (root1 == null)
                return root2;
            if (root2 == null)
                return root1;

            TreeNode merged = new TreeNode(root1.val + root2.val);

            Queue<TreeNode> mergeQueue = new LinkedList<>();
            Queue<TreeNode> t1Queue = new LinkedList<>();
            Queue<TreeNode> t2Queue = new LinkedList<>();

            mergeQueue.add(merged);
            t1Queue.add(root1);
            t2Queue.add(root2);

            while (!t1Queue.isEmpty() && !t2Queue.isEmpty()) {
                TreeNode mergeNode = mergeQueue.poll(), t1Node = t1Queue.poll(),
                        t2Node = t2Queue.poll();
                TreeNode t1Left = t1Node.left, t1Right = t1Node.right,
                        t2Left = t2Node.left, t2Right = t2Node.right;
                if (t1Left != null || t2Left != null) {
                    if (t1Left != null && t2Left != null) {
                        TreeNode left = new TreeNode(t1Left.val + t2Left.val);
                        mergeNode.left = left;
                        mergeQueue.add(left);
                        t1Queue.add(t1Left);
                        t2Queue.add(t2Left);
                    } else if (t1Left != null) {
                        mergeNode.left = t1Left;
                    } else {
                        mergeNode.left = t2Left;
                    }
                }

                if (t1Right != null || t2Right != null) {
                    if (t1Right != null && t2Right != null) {
                        TreeNode right = new TreeNode(t1Right.val + t2Right.val);
                        mergeNode.right = right;
                        mergeQueue.add(right);
                        t1Queue.add(t1Right);
                        t2Queue.add(t2Right);
                    } else if (t1Right != null) {
                        mergeNode.right = t1Right;
                    } else {
                        mergeNode.right = t2Right;
                    }
                }
            }

            return merged;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
