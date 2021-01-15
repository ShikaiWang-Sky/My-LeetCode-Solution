package leetcode.editor.cn;

public class Factorial {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.factorial(20));
    }

    static class Solution{
        public long factorial (int n) {
            if (n == 1) {
                return 1;
            }
            return n * factorial(n - 1);
        }
    }
}
