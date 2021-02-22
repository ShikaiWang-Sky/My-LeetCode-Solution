//请你实现一个「数字乘积类」ProductOfNumbers，要求支持下述两种方法： 
//
// 1. add(int num) 
//
// 
// 将数字 num 添加到当前数字列表的最后面。 
// 
//
// 2. getProduct(int k) 
//
// 
// 返回当前数字列表中，最后 k 个数字的乘积。 
// 你可以假设当前列表中始终 至少 包含 k 个数字。 
// 
//
// 题目数据保证：任何时候，任一连续数字序列的乘积都在 32-bit 整数范围内，不会溢出。 
//
// 
//
// 示例： 
//
// 输入：
//["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","g
//etProduct","add","getProduct"]
//[[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]
//
//输出：
//[null,null,null,null,null,null,20,40,0,null,32]
//
//解释：
//ProductOfNumbers productOfNumbers = new ProductOfNumbers();
//productOfNumbers.add(3);        // [3]
//productOfNumbers.add(0);        // [3,0]
//productOfNumbers.add(2);        // [3,0,2]
//productOfNumbers.add(5);        // [3,0,2,5]
//productOfNumbers.add(4);        // [3,0,2,5,4]
//productOfNumbers.getProduct(2); // 返回 20 。最后 2 个数字的乘积是 5 * 4 = 20
//productOfNumbers.getProduct(3); // 返回 40 。最后 3 个数字的乘积是 2 * 5 * 4 = 40
//productOfNumbers.getProduct(4); // 返回  0 。最后 4 个数字的乘积是 0 * 2 * 5 * 4 = 0
//productOfNumbers.add(8);        // [3,0,2,5,4,8]
//productOfNumbers.getProduct(2); // 返回 32 。最后 2 个数字的乘积是 4 * 8 = 32 
// 
//
// 
//
// 提示： 
//
// 
// add 和 getProduct 两种操作加起来总共不会超过 40000 次。 
// 0 <= num <= 100 
// 1 <= k <= 40000 
// 
// Related Topics 设计 数组 
// 👍 54 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：最后 K 个数的乘积
public class P1352ProductOfTheLastKNumbers {
    public static void main(String[] args) {
        ProductOfNumbers solution = new P1352ProductOfTheLastKNumbers().new ProductOfNumbers();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class ProductOfNumbers {
        private List<Integer> prefix;

        public ProductOfNumbers() {
            this.prefix = new ArrayList<>();
            prefix.add(1);
        }

        public void add(int num) {
            if (num == 0) {
                // 遇到0, 则前面的前缀就没有意义了, 因为计算的是后K个数
                //此时新建一个新的前缀数组
                this.prefix = new ArrayList<>();
                prefix.add(1);
            } else {
                // 否则, 当前数字与前一个(当前prefix的最后一个)求乘积
                prefix.add(prefix.get(prefix.size() - 1) * num);
            }
        }

        public int getProduct(int k) {
            //如果前缀的长度小于K, 说明在K的长度内出现过0, 返回即可, 同时注意我们开头添加的1, 因此此处为小于等于
            if (prefix.size() <= k) {
                return 0;
            } else {
                // 否则正常返回
                return prefix.get(prefix.size() - 1) / prefix.get(prefix.size() - 1 - k);
            }
        }
    }

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
