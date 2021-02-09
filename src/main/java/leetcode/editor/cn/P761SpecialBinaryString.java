//特殊的二进制序列是具有以下两个性质的二进制序列： 
//
// 
// 0 的数量与 1 的数量相等。 
// 二进制序列的每一个前缀码中 1 的数量要大于等于 0 的数量。 
// 
//
// 给定一个特殊的二进制序列 S，以字符串形式表示。定义一个操作 为首先选择 S 的两个连续且非空的特殊的子串，然后将它们交换。（两个子串为连续的当且仅当第一
//个子串的最后一个字符恰好为第二个子串的第一个字符的前一个字符。) 
//
// 在任意次数的操作之后，交换后的字符串按照字典序排列的最大的结果是什么？ 
//
// 示例 1: 
//
// 
//输入: S = "11011000"
//输出: "11100100"
//解释:
//将子串 "10" （在S[1]出现） 和 "1100" （在S[3]出现）进行交换。
//这是在进行若干次操作后按字典序排列最大的结果。
// 
//
// 说明: 
//
// 
// S 的长度不超过 50。 
// S 保证为一个满足上述定义的特殊 的二进制序列。 
// 
// Related Topics 递归 字符串 
// 👍 56 👎 0

package leetcode.editor.cn;

//Java：特殊的二进制序列
public class P761SpecialBinaryString {
    public static void main(String[] args) {
        Solution solution = new P761SpecialBinaryString().new Solution();
        // TO TEST
        System.out.println(solution.makeLargestSpecial("11011000"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String makeLargestSpecial(String S) {
            // 递归
            // 1. 结束条件
            if (S.length() <= 1) {
                return S;
            }
            // 2. 函数主功能 => 遍历查找子串, 子串排序, 子串逆序拼接成字符串
            StringBuilder sb = new StringBuilder();
            /*
            存储连续的特殊子串，符合要求的字符串必定是1开头0结尾的
            符合规则特殊子串的起始位置
            存放1, 0的数量差
             */
            String[] arr = new String[25];
            int index = 0;
            int start = 0;
            int countOne = 0;

            // 遍历, 以start开头是否存在符合要求的子串
            for (int i = 0; i < S.length(); i++) {
                countOne += S.charAt(i) == '1' ? 1 : -1;
                if(countOne == 0) {
                    // 去掉头尾的1 和 0 => 如果此时的子串满足要求, 去掉头部的1和尾部的0得到的是下一个待判断的子串,
                    // 范围是在当前的i之前的子串
                    String result = makeLargestSpecial(S.substring(start + 1, i));
                    // 在递归结果还原 1 和 0
                    arr[index++] = "1" + result + "0";
                    // 记录特殊字串下标位置
                    start = i + 1;
                }
            }
            // 对数组中的连续子串进行冒泡排序
            bubbleSort(arr, index);
            // 对排序后的连续子串, 逆序拼接成字符串
            for (int i = index - 1; i >= 0; i--) {
                sb.append(arr[i]);
            }
            return sb.toString();
        }

        /**
         * 冒泡排序
         * @param arr 待排序的字符串数组
         * @param length 有效字符串数组的索引(大小)
         */
        public void bubbleSort(String[] arr, int length) {
            for (int i = 0; i < length - 1; i++) {
                for (int j = 0; j < length - 1 - i; j++) {
                    if (arr[j].compareTo(arr[j + 1]) > 0) {
                        String temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }

        /**
         * 快速排序
         * @param arr 待排序的字符串数组
         * @param low 向后搜索指针
         * @param high 向前搜索指针
         */
        public void quickSort(String[] arr, int low, int high) {
            int i = low;
            int j = high;
            String temp = arr[i];
            while (i < j) {
                // arr[j]不小于基准, 不用交换, 继续向前搜索
                while (i < j && arr[j].compareTo(temp) >= 0) {
                    j--;
                }
                // while结束, 满足 i < j, 说明arr[j] 小于基准, 记录到前面(i的后一个), 并移动i指针到当前位置
                if (i < j) {
                    arr[i++] = arr[j];
                }
                // arr[i]不大于基准, 不用交换, 继续向后搜索
                while (i < j && arr[i].compareTo(temp) <= 0) {
                    i++;
                }
                // while结束, 满足 i < j, 说明arr[i] 大于基准, 记录到后面(j的前一个), 并移动j指针到当前位置
                if (i < j) {
                    arr[j--] = arr[i];
                }
            }
            // 确定基准记录位置
            arr[i] = temp;
            // 递归处理左子区
            if (low < i - 1) {
                quickSort(arr, low, i - 1);
            }
            // 递归处理右子区
            if (high > i + 1) {
                quickSort(arr, i + 1, high);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
