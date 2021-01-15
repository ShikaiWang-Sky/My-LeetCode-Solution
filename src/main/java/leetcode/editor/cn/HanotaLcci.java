//在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只
//能放在更大的盘子上面)。移动圆盘时受到以下限制: 
//(1) 每次只能移动一个盘子; 
//(2) 盘子只能从柱子顶端滑出移到下一根柱子; 
//(3) 盘子只能叠在比它大的盘子上。 
//
// 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。 
//
// 你需要原地修改栈。 
//
// 示例1: 
//
//  输入：A = [2, 1, 0], B = [], C = []
// 输出：C = [2, 1, 0]
// 
//
// 示例2: 
//
//  输入：A = [1, 0], B = [], C = []
// 输出：C = [1, 0]
// 
//
// 提示: 
//
// 
// A中盘子的数目不大于14个。 
// 
// Related Topics 递归 
// 👍 71 👎 0

package leetcode.editor.cn;

import java.util.List;

//Java：汉诺塔问题
public class HanotaLcci {
    public static void main(String[] args) {
        Solution solution = new HanotaLcci().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
            int size = A.size();
            //0 准备: 根据圆盘的数量确定柱子的排放顺序(使用数组存放顺序)
            List<Integer>[] lists = new List[3];
            lists[0] = A;
            //0.1 若n为偶数, 按照顺时针方向依次摆放 A B C
            if (size % 2 == 0) {
                lists[1] = B;
                lists[2] = C;
            } else {
                //0.2 若n为奇数, 按照顺时针方向依次摆放 A C B
                lists[1] = C;
                lists[2] = B;
            }
            // 最小圆盘所在的柱子的index
            int currentIndex = 0;
            //循环进行两步操作法 (边界条件 => 所有的盘子都移动到C上)
            while (C.size() < size) {
                // 1. 最小的圆盘移动到下一个柱子
                // 1.1 找到最小圆盘所在的柱子current, 找到下一个next
                List<Integer> current = lists[currentIndex];
                //通过下标求余数, 解决循环问题 => 当前为3, 下一个回到1, 则计算 (3+1)%3=1
                List<Integer> next = lists[(currentIndex + 1) % 3];
                // 1.2 最小圆盘移动到下一个柱子next上
                next.add(current.remove(current.size() - 1));
                //最小圆盘位置更新, 原来的加1
                currentIndex = (currentIndex + 1) % 3;

                //2. 另外两根柱子上可以移动的圆盘移动到新的柱子上 ==> 当两根柱子都非空时,移动较小的圆盘
                //2.1 找到另外两个柱子(没有最小元素的柱子, 也就是刚才移动后的current柱子) ==> current, pre(注意, 这时的current和currentIndex
                // 不一致, 因为我们的current在循环的一开始就确定了
                List<Integer> pre = lists[(currentIndex + 1) % 3];
                //2.2 获取栈顶的元素进行比较
                int plateToMove1 = (pre.size() == 0 ? Integer.MAX_VALUE : pre.get(pre.size() - 1));
                int plateToMove2 = (current.size() == 0 ? Integer.MAX_VALUE : current.get(current.size() - 1));
                //2.3 较小的盘子移动到另外一个柱子
                if (plateToMove1 < plateToMove2) {
                    current.add(pre.remove(pre.size() - 1));
                } else if (plateToMove2 < plateToMove1) {
                    pre.add(current.remove(current.size() - 1));
                }
            }
        }

        /**
         * 递归汉诺塔
         * @param size 需要移动的盘子的数量
         * @param start 来源柱子
         * @param auxiliary 中间辅助柱子
         * @param target 目标柱子
         */
        private void movePlate(int size, List<Integer> start, List<Integer> auxiliary, List<Integer> target) {
            // 结束条件 只剩一个盘子时, 直接从第一个柱子移动到第三个柱子即可
            if (size == 1) {
                target.add(start.remove(start.size() - 1));
                return;
            }
            // 函数的主功能
            // 将n-1个盘子从1移动到2
            movePlate(size - 1,start ,target, auxiliary);
            // 将第n个盘子从1到3
            target.add(start.remove(start.size() - 1));
            // 再将 n - 1 个盘子, 从2移动到3
            movePlate(size -1, auxiliary, start, target);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
