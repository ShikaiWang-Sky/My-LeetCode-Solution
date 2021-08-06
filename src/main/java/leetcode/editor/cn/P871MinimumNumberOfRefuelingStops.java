//汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。 
//
// 沿途有加油站，每个 station[i] 代表一个加油站，它位于出发位置东面 station[i][0] 英里处，并且有 station[i][1] 升汽
//油。 
//
// 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。 
//
// 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。 
//
// 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。 
//
// 注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。 
//
// 
//
// 示例 1： 
//
// 输入：target = 1, startFuel = 1, stations = []
//输出：0
//解释：我们可以在不加油的情况下到达目的地。
// 
//
// 示例 2： 
//
// 输入：target = 100, startFuel = 1, stations = [[10,100]]
//输出：-1
//解释：我们无法抵达目的地，甚至无法到达第一个加油站。
// 
//
// 示例 3： 
//
// 输入：target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
//
//输出：2
//解释：
//我们出发时有 10 升燃料。
//我们开车来到距起点 10 英里处的加油站，消耗 10 升燃料。将汽油从 0 升加到 60 升。
//然后，我们从 10 英里处的加油站开到 60 英里处的加油站（消耗 50 升燃料），
//并将汽油从 10 升加到 50 升。然后我们开车抵达目的地。
//我们沿途在1两个加油站停靠，所以返回 2 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= target, startFuel, stations[i][1] <= 10^9 
// 0 <= stations.length <= 500 
// 0 < stations[0][0] < stations[1][0] < ... < stations[stations.length-1][0] < 
//target 
// 
// Related Topics 贪心 数组 动态规划 堆（优先队列） 
// 👍 176 👎 0

package leetcode.editor.cn;

import java.util.Collections;
import java.util.PriorityQueue;

//Java：最低加油次数
public class P871MinimumNumberOfRefuelingStops {
    public static void main(String[] args) {
        Solution solution = new P871MinimumNumberOfRefuelingStops().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minRefuelStops(int target, int startFuel, int[][] stations) {
            // 大顶堆
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            int res = 0;
            // 上一个加油站到起点的距离
            int prev = 0;
            // 油箱内的油量
            int tank = startFuel;

            for (int[] station : stations) {
                int location = station[0];
                int capacity = station[1];
                tank -= (location - prev);

                // 如果油量到不了当前加油站且加油站队列不为空
                while (tank < 0 && !pq.isEmpty()) {
                    // 加最大的油量, 同时加油过的加油站出队
                    tank += pq.poll();
                    // 加油次数加一
                    res++;
                }

                // 如果加油后还是到不了当前的位置, 说明无法达到目的地, 返回 -1
                if (tank < 0) {
                    return -1;
                }

                // 加油后可以继续走, 当前加油站入队, 更新上一个使用过的加油站的位置为当前位置
                pq.offer(capacity);
                prev = location;
            }

            // target处可以视为一个油量为0的加油站
            tank -= (target - prev);
            while (!pq.isEmpty() && tank < 0) {
                tank += pq.poll();
                res++;
            }
            if (tank < 0) {
                return -1;
            }
            return res;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
