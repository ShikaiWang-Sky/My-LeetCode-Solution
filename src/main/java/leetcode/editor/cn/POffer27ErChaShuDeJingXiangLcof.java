//请完成一个函数，输入一个二叉树，该函数输出它的镜像。 
//
// 例如输入： 
//
// 4 
// / \ 
// 2 7 
// / \ / \ 
//1 3 6 9 
//镜像输出： 
//
// 4 
// / \ 
// 7 2 
// / \ / \ 
//9 6 3 1 
//
// 
//
// 示例 1： 
//
// 输入：root = [4,2,7,1,3,6,9]
//输出：[4,7,2,9,6,3,1]
// 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 1000 
//
// 注意：本题与主站 226 题相同：https://leetcode-cn.com/problems/invert-binary-tree/ 
// Related Topics 树 
// 👍 101 👎 0

package leetcode.editor.cn;

import javax.swing.tree.TreeNode;
import java.util.ArrayDeque;
import java.util.Deque;

//Java：二叉树的镜像
public class POffer27ErChaShuDeJingXiangLcof {
    public static void main(String[] args) {
        Solution solution = new POffer27ErChaShuDeJingXiangLcof().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int x) {
                val = x;
            }

            public TreeNode mirrorTree(TreeNode root) {
                if (root == null) {
                    return null;
                }
                Deque<TreeNode> queue = new ArrayDeque<>();
                queue.add(root);
                while (!queue.isEmpty()) {
                    TreeNode node = queue.pop();
                    TreeNode temp = node.left;
                    node.left = node.right;
                    node.right = temp;
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
                return root;
            }
        }
//leetcode submit region end(Prohibit modification and deletion)
    }
}
