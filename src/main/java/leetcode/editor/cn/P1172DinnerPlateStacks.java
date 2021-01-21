//我们把无限数量 ∞ 的栈排成一行，按从左到右的次序从 0 开始编号。每个栈的的最大容量 capacity 都相同。 
//
// 实现一个叫「餐盘」的类 DinnerPlates： 
//
// 
// DinnerPlates(int capacity) - 给出栈的最大容量 capacity。 
// void push(int val) - 将给出的正整数 val 推入 从左往右第一个 没有满的栈。 
// int pop() - 返回 从右往左第一个 非空栈顶部的值，并将其从栈中删除；如果所有的栈都是空的，请返回 -1。 
// int popAtStack(int index) - 返回编号 index 的栈顶部的值，并将其从栈中删除；如果编号 index 的栈是空的，请返回 -
//1。 
// 
//
// 
//
// 示例： 
//
// 输入： 
//["DinnerPlates","push","push","push","push","push","popAtStack","push","push",
//"popAtStack","popAtStack","pop","pop","pop","pop","pop"]
//[[2],[1],[2],[3],[4],[5],[0],[20],[21],[0],[2],[],[],[],[],[]]
//输出：
//[null,null,null,null,null,null,2,null,null,20,21,5,4,3,1,-1]
//
//解释：
//DinnerPlates D = DinnerPlates(2);  // 初始化，栈最大容量 capacity = 2
//D.push(1);
//D.push(2);
//D.push(3);
//D.push(4);
//D.push(5);         // 栈的现状为：    2  4
//                                    1  3  5
//                                    ﹈ ﹈ ﹈
//D.popAtStack(0);   // 返回 2。栈的现状为：      4
//                                          1  3  5
//                                          ﹈ ﹈ ﹈
//D.push(20);        // 栈的现状为：  20  4
//                                   1  3  5
//                                   ﹈ ﹈ ﹈
//D.push(21);        // 栈的现状为：  20  4 21
//                                   1  3  5
//                                   ﹈ ﹈ ﹈
//D.popAtStack(0);   // 返回 20。栈的现状为：       4 21
//                                            1  3  5
//                                            ﹈ ﹈ ﹈
//D.popAtStack(2);   // 返回 21。栈的现状为：       4
//                                            1  3  5
//                                            ﹈ ﹈ ﹈ 
//D.pop()            // 返回 5。栈的现状为：        4
//                                            1  3 
//                                            ﹈ ﹈  
//D.pop()            // 返回 4。栈的现状为：    1  3 
//                                           ﹈ ﹈   
//D.pop()            // 返回 3。栈的现状为：    1 
//                                           ﹈   
//D.pop()            // 返回 1。现在没有栈。
//D.pop()            // 返回 -1。仍然没有栈。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 20000 
// 1 <= val <= 20000 
// 0 <= index <= 100000 
// 最多会对 push，pop，和 popAtStack 进行 200000 次调用。 
// 
// Related Topics 设计 
// 👍 24 👎 0

package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//Java：餐盘栈
public class P1172DinnerPlateStacks {
    public static void main(String[] args) {
        DinnerPlates solution = new P1172DinnerPlateStacks().new DinnerPlates(1);
        // TO TEST
        solution.push(1);
        solution.push(2);
        solution.push(3);
        System.out.println(solution.popAtStack(0));
        System.out.println(solution.pop());
        System.out.println(solution.pop());
        solution.push(4);
        solution.push(5);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class DinnerPlates {
        int capacity;
        // 左边第一个未满栈的下标
        int left;
        // 右边第一个非空栈的下标
        int right;
        public List<Deque<Integer>> stacks;

        public DinnerPlates(int capacity) {
            stacks = new ArrayList<>();
            this.capacity = capacity;
            this.right = 0;
            this.left = 0;
            stacks.add(new ArrayDeque<>());
        }

        /**
        直接在left所在位置进行入栈，push后需要遍历查找下一个未满栈
        细节问题：
            1. 需要对list进行扩容操作
            2. 需要更新right位置（right宽松，不保证一定是非空，可以在第一个非空的右边）
        */
        public void push(int val) {
            if (left < 0) {
                left = 0;
            }
            Deque<Integer> stack = stacks.get(left);
            stack.push(val);
            // 当前栈满, 则找下一个非满栈
            while (left < stacks.size() && stacks.get(left).size() == capacity) {
                left++;
            }
            // 所有的栈都满了, 要扩容, 增加一个栈
            if (left >= stacks.size()) {
                stacks.add(new ArrayDeque<>());
            }
            // 更新right的位置(left指向的位置一定是非空栈)
            //  right < left的情况只发生在left左侧都非空的情况下
            if (right < left) {
                right = left;
            }
        }

        /**
        以right为起点，往左遍历查找第一个非空栈
        细节问题：
            移除数据后，需要判断所在位置是不是比left更小，如果满足则需要更新left
            移除数据后，需要判断所在位置是不是空栈，空栈则将right左移一个（right宽松，不保证一定是非空，可以在第一个非空的右边）
        */
        public int pop() {
            // 从right开始寻找第一个非空栈
            for (int i = right; i >= 0; i--) {
                Deque<Integer> stack = stacks.get(i);
                if (!stack.isEmpty()) {
                    right = i;
                    int res = stack.pop();
                    // 如果移除的位置在left左边，需要将left修复到right位置
                    if (left > right) {
                        left = right;
                    }
                    if (stack.isEmpty()) {
                        right--;
                    }
                    return res;
                }
            }
            return -1;
        }

        /**
        移除数据后，需要判断所在位置是不是比left更小，如果满足则需要更新left
        */
        public int popAtStack(int index) {
            // 越界检查
            if (index > stacks.size() - 1) {
                return -1;
            }
            Deque<Integer> stack = stacks.get(index);
            if (!stack.isEmpty()) {
                // 在left左边出栈, 更新left
                if (left > index) {
                    left = index;
                }
                return stack.pop();
            } else {
                return -1;
            }
        }
    }

/**
 * Your DinnerPlates object will be instantiated and called as such:
 * DinnerPlates obj = new DinnerPlates(capacity);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAtStack(index);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
