//给定一个非空数组，数组中元素为 a0, a1, a2, … , an-1，其中 0 ≤ ai < 231 。 
//
// 找到 ai 和aj 最大的异或 (XOR) 运算结果，其中0 ≤ i, j < n 。 
//
// 你能在O(n)的时间解决这个问题吗？ 
//
// 示例: 
//
// 
//输入: [3, 10, 5, 25, 2, 8]
//
//输出: 28
//
//解释: 最大的结果是 5 ^ 25 = 28.
// 
// Related Topics 位运算 字典树 
// 👍 219 👎 0

package leetcode.editor.cn;

import java.util.HashMap;

//Java：数组中两个数的最大异或值
public class P421MaximumXorOfTwoNumbersInAnArray {
    public static void main(String[] args) {
        Solution solution = new P421MaximumXorOfTwoNumbersInAnArray().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class TrieNode {
        HashMap<Character, TrieNode> child = new HashMap<>();

        public TrieNode() {

        }
    }

    class Solution {
        public int findMaximumXOR(int[] nums) {
            // 计算最大的二进制数字长度
            int maxNum = nums[0];
            for (int num : nums) {
                maxNum = Math.max(num, maxNum);
            }
            // toBinaryString => 十进制转二进制字符串
            int maxLength = Integer.toBinaryString(maxNum).length();

            // 先将数字左侧填充0 => 最长的长度 + 1 位置上添加1, 再String去掉最高位的1
            int mask = 1 << maxLength;
            String[] bitNum = new String[nums.length];
            for (int i = 0; i < nums.length; i++) {
                // 最长的长度 + 1 位置上添加1, 与原来的数字按位与, 再去掉最高位
                bitNum[i] = Integer.toBinaryString(mask | nums[i]).substring(1);
            }

            // 建立字典树
            TrieNode root = new TrieNode();
            // 当前的最大异或
            int maxXor = 0;
            for (String num : bitNum) {
                TrieNode currentNode = root;
                TrieNode xorNode = root;
                int currentXor = 0;
                for (char c : num.toCharArray()) {
                    // 当前子树已经有了c, 继续遍历
                    if (currentNode.child.containsKey(c)) {
                        currentNode = currentNode.child.get(c);
                    } else {
                        // 新建子节点
                        TrieNode newNode = new TrieNode();
                        currentNode.child.put(c, newNode);
                        currentNode = newNode;
                    }

                    // 计算当前插入的子树的最大异或值(是否存在互补元素)
                    // toggleBit为与当前元素互补的bit值
                    Character toggleBit = c == '1' ? '0' : '1';
                    // 同一根节点下存在互补节点, 由于是从高位到低位比较, 左移按位与
                    if (xorNode.child.containsKey(toggleBit)) {
                        currentXor = (currentXor << 1) | 1;
                        xorNode = xorNode.child.get(toggleBit);
                    } else {
                        // 不存在互补节点, 直接左移
                        currentXor = currentXor << 1;
                        xorNode = xorNode.child.get(c);
                    }
                }
                maxXor = Math.max(maxXor, currentXor);
            }
            return maxXor;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
