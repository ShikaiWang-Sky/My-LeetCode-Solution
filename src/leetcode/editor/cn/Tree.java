package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

public class Tree {
    // 定义一棵二叉树
    private class TreeNode {
        // 定义节点的值
        int val;
        // 定义左子树
        TreeNode left;
        // 定义右子树
        TreeNode right;

        // 定义初始化方法
        TreeNode(int x) {
            val = x;
        }
    }

    // BFS实现
    public int maxDepthBFS(TreeNode root) {
        // 考虑树为空的特殊情况, BFS无法自动处理
        if (root == null) {
            return 0;
        }

        // 使用队列来记录各层的节点
        Queue<TreeNode> queue = new LinkedList<>();
        // 根节点入队
        queue.offer(root);
        // 目标值
        int res = 0;
        // 判断是否还有没有遍历完的节点
        while (!queue.isEmpty()) {
            // 开始遍历新一层节点前, 队列里即为新一层全部节点
            int size = queue.size();
            // 需要将这一层节点全部遍历完
            while (size > 0) {
                // 遍历节点, 根节点出队
                TreeNode node = queue.poll();
                // 左子树入队
                if (node.left != null) {
                    queue.offer(node.left);
                }
                // 右子树入队
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            // 新一层节点遍历完成, 目标值 + 1
            res++;
        }
        return res;
    }

    // DFS实现
    public int maxDepthDFS(TreeNode root) {
        // 递归三要素
        // 确定函数的等价关系式 (参数, 返回值) => 参数是传入树的根节点, 返回值是树的高度
        int maxDepth = 0;

        // 确定结束条件, 如果节点为空, 返回0
        // 且此处可以处理二叉树为空的corner case
        if (root == null) {
            return 0;
        }

        // 函数主功能, 分别求左右子树最大深度, 返回左右子树深度最大值 + 1
        // 即为当前节点为根节点的树的最大深度
        int leftTreeDepth = maxDepthDFS(root.left);
        int rightTreeDepth = maxDepthDFS(root.right);
        maxDepth = 1 + Math.max(leftTreeDepth, rightTreeDepth);
        return maxDepth;
    }
}
