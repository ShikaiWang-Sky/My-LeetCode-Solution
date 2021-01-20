//设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。 
//
// 
// push(x) —— 将元素 x 推入栈中。 
// pop() —— 删除栈顶的元素。 
// top() —— 获取栈顶元素。 
// getMin() —— 检索栈中的最小元素。 
// 
//
// 
//
// 示例: 
//
// 输入：
//["MinStack","push","push","push","getMin","pop","top","getMin"]
//[[],[-2],[0],[-3],[],[],[],[]]
//
//输出：
//[null,null,null,null,-3,null,0,-2]
//
//解释：
//MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.getMin();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.getMin();   --> 返回 -2.
// 
//
// 
//
// 提示： 
//
// 
// pop、top 和 getMin 操作总是在 非空栈 上调用。 
// 
// Related Topics 栈 设计 
// 👍 770 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：最小栈
public class P155MinStack {
    public static void main(String[] args) {
        MinStack solution = new P155MinStack().new MinStack();
        // TO TEST
        solution.push(2147483646);
        solution.push(2147483646);
        solution.push(2147483647);
        System.out.println(solution.top());
        solution.pop();
        System.out.println(solution.getMin());
        solution.pop();
        System.out.println(solution.getMin());
        solution.pop();
        solution.push(2147483647);
        System.out.println(solution.top());
        System.out.println(solution.getMin());
        solution.push(-2147483648);
        System.out.println(solution.top());
        System.out.println(solution.getMin());
        solution.pop();
        System.out.println(solution.getMin());
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MinStack {
        List<Object> elements;
        List<Object> minElements;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            elements = new ArrayList<>();
            minElements = new ArrayList<>();
        }

        public void push(int x) {
            if (elements.isEmpty()) {
                elements.add(x);
                minElements.add(x);
            } else {
                int preNum = (int) minElements.get(minElements.size() - 1);
                elements.add(x);
                minElements.add(Math.min(preNum, x));
            }
        }

        public void pop() {
            if (!elements.isEmpty()) {
                int lastIndex = elements.size() - 1;
                elements.remove(lastIndex);
                minElements.remove(lastIndex);
            }
        }

        public int top() {
            return (int) elements.get(elements.size() - 1);
        }

        public int getMin() {
            return (int) minElements.get(minElements.size() - 1);
        }
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
