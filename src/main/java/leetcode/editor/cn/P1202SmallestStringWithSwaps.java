//给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。 
//
//
// 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。 
//
// 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。 
//
// 
//
// 示例 1: 
//
// 输入：s = "dcab", pairs = [[0,3],[1,2]]
//输出："bacd"
//解释： 
//交换 s[0] 和 s[3], s = "bcad"
//交换 s[1] 和 s[2], s = "bacd"
// 
//
// 示例 2： 
//
// 输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
//输出："abcd"
//解释：
//交换 s[0] 和 s[3], s = "bcad"
//交换 s[0] 和 s[2], s = "acbd"
//交换 s[1] 和 s[2], s = "abcd" 
//
// 示例 3： 
//
// 输入：s = "cba", pairs = [[0,1],[1,2]]
//输出："abc"
//解释：
//交换 s[0] 和 s[1], s = "bca"
//交换 s[1] 和 s[2], s = "bac"
//交换 s[0] 和 s[1], s = "abc"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10^5 
// 0 <= pairs.length <= 10^5 
// 0 <= pairs[i][0], pairs[i][1] < s.length 
// s 中只含有小写英文字母 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 哈希表 字符串 
// 👍 231 👎 0

package leetcode.editor.cn;

import org.jetbrains.annotations.Nls;

import java.util.*;

//Java：交换字符串中的元素
public class P1202SmallestStringWithSwaps {
    public static void main(String[] args) {
        Solution solution = new P1202SmallestStringWithSwaps().new Solution();
        // TO TEST
        String s = "dcab";
        List<List<Integer>> pairs = new ArrayList<>();
        List<Integer> pair1 = new ArrayList<Integer>() {{
                add(0);
                add(3);
        }};
        List<Integer> pair2 = new ArrayList<Integer>() {{
            add(1);
            add(2);
        }};
        pairs.add(pair1);
        pairs.add(pair2);
        System.out.println(solution.smallestStringWithSwaps(s, pairs));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        class UFS {
            private int[] parent;
            private int[] rank;

            public void init(int n) {
                if (parent == null) {
                    parent = new int[n];
                    this.rank = new int[n];

                }
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                    rank[i] = 1;
                }
            }

            public int find(int i) {
                if (i != parent[i]) {
                    parent[i] = find(parent[i]);
                }
                return parent[i];
            }

            public void union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);

                if (rootX != rootY) {
                    if (rank[rootX] < rank[rootY]) {
                        parent[rootX] = rootY;
                    } else if (rank[rootX] > rank[rootY]) {
                        parent[rootY] = rootX;
                    } else {
                        parent[rootY] = rootX;
                        rank[rootX] += 1;
                    }
                }
            }
        }

        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            UFS ufs = new UFS();
            ufs.init(s.length());
            for (List<Integer> pair : pairs) {
                ufs.union(pair.get(0), pair.get(1));
            }
            Map<Integer, List<Character>> parentCharMap = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                int parent = ufs.find(i);
                if (!parentCharMap.containsKey(parent)) {
                    parentCharMap.put(parent, new ArrayList<Character>());
                }
                parentCharMap.get(parent).add(s.charAt(i));
            }
            // 反向排列,方便每次取最后一个缩短队列
            for (Map.Entry<Integer, List<Character>> entry : parentCharMap.entrySet()) {
                Collections.sort(entry.getValue(), new Comparator<Character>() {
                    @Override
                    public int compare(Character o1, Character o2) {
                        return o2 - o1;
                    }
                });
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                int rootI = ufs.find(i);
                List<Character> list = parentCharMap.get(rootI);
                // 取出最后一个
                stringBuilder.append(list.remove(list.size() - 1));
            }
            return stringBuilder.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
