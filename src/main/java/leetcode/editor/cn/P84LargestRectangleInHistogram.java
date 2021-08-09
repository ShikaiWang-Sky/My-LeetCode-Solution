//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。 
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入：heights = [2,1,5,6,2,3]
//输出：10
//解释：最大的矩形为图中红色区域，面积为 10
// 
//
// 示例 2： 
//
// 
//
// 
//输入： heights = [2,4]
//输出： 4 
//
// 
//
// 提示： 
//
// 
// 1 <= heights.length <=105 
// 0 <= heights[i] <= 104 
// 
// Related Topics 栈 数组 单调栈 
// 👍 1472 👎 0

package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

//Java：柱状图中最大的矩形
public class P84LargestRectangleInHistogram {
    public static void main(String[] args) {
        Solution solution = new P84LargestRectangleInHistogram().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int largestRectangleArea(int[] heights) {
            int N = heights.length;
            int[] left = new int[N];
            int[] right = new int[N];
            int area = 0;
            Deque<Integer> mono_stack = new ArrayDeque<>();
            for (int i = 0; i < N; i++) {
                while (!mono_stack.isEmpty() && heights[mono_stack.peek()] > heights[i]) {
                    int height = heights[mono_stack.pop()];

                    while (!mono_stack.isEmpty() && heights[mono_stack.peek()] == height) {
                        mono_stack.pop();
                    }

                    int width;

                    if (mono_stack.isEmpty()) {
                        width = i;
                    } else {
                        width = i - mono_stack.peek() - 1;
                    }

                    area = Math.max(area, width * height);
                }
                mono_stack.push(i);
            }

            while (!mono_stack.isEmpty()) {
                int height = heights[mono_stack.pop()];

                while (!mono_stack.isEmpty() && heights[mono_stack.peek()] == height) {
                    mono_stack.pop();
                }

                int width;

                if (mono_stack.isEmpty()) {
                    width = N;
                } else {
                    width = N - mono_stack.peek() - 1;
                }

                area = Math.max(area, width * height);
            }

            return area;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
