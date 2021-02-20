//给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的
//根节点的引用。 
//
// 一般来说，删除节点可分为两个步骤： 
//
// 
// 首先找到需要删除的节点； 
// 如果找到了，删除它。 
// 
//
// 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。 
//
// 示例: 
//
// 
//root = [5,3,6,2,4,null,7]
//key = 3
//
//    5
//   / \
//  3   6
// / \   \
//2   4   7
//
//给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
//
//一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
//
//    5
//   / \
//  4   6
// /     \
//2       7
//
//另一个正确答案是 [5,2,6,null,4,null,7]。
//
//    5
//   / \
//  2   6
//   \   \
//    4   7
// 
// Related Topics 树 
// 👍 390 👎 0

package leetcode.editor.cn;

import javax.swing.tree.TreeNode;

//Java：删除二叉搜索树中的节点
public class P450DeleteNodeInABst {
    public static void main(String[] args) {
        Solution solution = new P450DeleteNodeInABst().new Solution();
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
        public TreeNode deleteNode(TreeNode root, int key) {
            // 先找到待删除的节点
            if (root == null) {
                return null;
            }
            if (key < root.val) {
                // root.left为了返回root为根节点, 否则root会向下走
                root.left = deleteNode(root.left, key);
                return root;
            }
            if (key > root.val) {
                root.right = deleteNode(root.right, key);
                return root;
            }
            else {
                // 此条件中, key == root.val
                if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                } else {
                    // 左右子树都不为空
                    // 其左子树转移到其右子树的最左节点的左子树上，然后右子树顶替其位置，由此删除了该节点
                    TreeNode changeNode = rightTreeLastLeftNode(root.right);
                    changeNode.left = root.left;
                    root = root.right;
                    return root;
                }
            }
        }

        private TreeNode rightTreeLastLeftNode(TreeNode node) {
            if (node.left == null) {
                return node;
            }
            return rightTreeLastLeftNode(node.left);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
