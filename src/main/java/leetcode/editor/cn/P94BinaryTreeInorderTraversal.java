//给定一个二叉树的根节点 root ，返回它的 中序 遍历。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[2,1]
// 
//
// 示例 5： 
//
// 
//输入：root = [1,null,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表 
// 👍 856 👎 0

package leetcode.editor.cn;

import java.util.*;

//Java：二叉树的中序遍历
public class P94BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new P94BinaryTreeInorderTraversal().new Solution();
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
            public List<Integer> inorderTraversal(TreeNode root) {
                List<Integer> res = new ArrayList<>();
                TreeNode exPoint = null;

                while (root != null) {
                    // 1. 找到左子树中序遍历最后一个结点
                    if (root.left != null) {
                        // exPoint就是当前root节点向左走一步, 然后一直向右走到无法走为止
                        // 左中右遍历, 最后一个节点必定是根节点的右子树的最后一个右节点
                        exPoint = root.left;
                        while (exPoint.right != null && exPoint.right != root) {
                            exPoint = exPoint.right;
                        }
                        // 右子树为空, 指向root (管理左子树遍历完后可以找到根节点)
                        if (exPoint.right == null) {
                            exPoint.right = root;
                            // 开始遍历左子树
                            root = root.left;
                        } else {
                            // 右子树不为空, 且右子树指向了root节点, 左子树已经访问完了, 记录根节点并访问右子树
                            res.add(root.val);
                            exPoint.right = null;
                            root = root.right;
                        }
                    } else {
                        // 如果没有左子树, 直接访问右子树
                        res.add(root.val);
                        root = root.right;
                    }
                }
                return res;
            }

        }
//leetcode submit region end(Prohibit modification and deletion)

    }
