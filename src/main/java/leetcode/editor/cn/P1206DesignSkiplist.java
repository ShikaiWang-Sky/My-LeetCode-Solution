//不使用任何库函数，设计一个跳表。 
//
// 跳表是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思想
//与链表相似。 
//
// 例如，一个跳表包含 [30, 40, 50, 60, 70, 90]，然后增加 80、45 到跳表中，以下图的方式操作： 
//
// 
//Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons 
//
// 跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。跳表的每一个操作的平均时间复杂度是 O(log(
//n))，空间复杂度是 O(n)。 
//
// 在本题中，你的设计应该要包含这些函数： 
//
// 
// bool search(int target) : 返回target是否存在于跳表中。 
// void add(int num): 插入一个元素到跳表。 
// bool erase(int num): 在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可。 
//
// 
//
// 了解更多 : https://en.wikipedia.org/wiki/Skip_list 
//
// 注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。 
//
// 样例: 
//
// Skiplist skiplist = new Skiplist();
//
//skiplist.add(1);
//skiplist.add(2);
//skiplist.add(3);
//skiplist.search(0);   // 返回 false
//skiplist.add(4);
//skiplist.search(1);   // 返回 true
//skiplist.erase(0);    // 返回 false，0 不在跳表中
//skiplist.erase(1);    // 返回 true
//skiplist.search(1);   // 返回 false，1 已被擦除
// 
//
// 约束条件: 
//
// 
// 0 <= num, target <= 20000 
// 最多调用 50000 次 search, add, 以及 erase操作。 
// 
// Related Topics 设计 
// 👍 51 👎 0

package leetcode.editor.cn;

import org.w3c.dom.Node;

//Java：设计跳表
public class P1206DesignSkiplist {
    public static void main(String[] args) {
        Skiplist solution = new P1206DesignSkiplist().new Skiplist();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Skiplist {
        // 链表头结点的值
        final int HEAD_VALUE = -1;
        final Node HEAD = new Node(HEAD_VALUE);
        // 最左上角的头结点, 所有操作开始的位置
        Node head;
        // 当前层级, 即 head 节点所在的最高层数
        int levels;

        // 初始化默认值
        public Skiplist() {
            head = HEAD;
            levels = 1;
        }

        // 节点类
        class Node {
            // 数据域
            int val;
            // 指针域: 向右, 向下
            Node right, down;

            public Node(int val) {
                this.val = val;
            }

            public Node(int val, Node right, Node down) {
                this.val = val;
                this.right = right;
                this.down = down;
            }
        }

        /**
         * 从head 开始，从左到右、从上到下依次查找
         * 1.小忏，往右
         * 2.相同，则返回
         * 3.链表结尾，若大于，往下
         *
         * @param target
         * @return
         */
        public boolean search(int target) {
            Node n = head;
            while (n != null) {
                // 1. 在同一层级上向右查找, 直到链表的结尾
                // 获取该指定数据节点的前一个节点 (方便删除操作)
                while (n.right != null && n.right.val < target) {
                    n = n.right;
                }
                // 2. 若找到
                Node right = n.right;
                // 先判断非空
                if (right != null && right.val == target) {
                    return true;
                }

                // 3. 若右侧数据较大, 向下一层
                n = n.down;
            }
            return false;
        }

        /**
         * 插入节点, 将节点插入到原链表中正确的排序位置
         * 1. 定位插入位置: 原链表中 >= num 的最小节点前
         * 2. 插入新节点
         * 3. 根据扔硬币决定是否生成索引
         *
         * @param num
         */
        public void add(int num) {
            // 1. 定位插入位置: 原链表中 >= num 的最小节点前
            Node node = head;
            // node == null, 到达原链表(原链表的 down 都是 null), 循环结束, 找到了插入节点
            while (node != null) {
                while (node.right != null && node.right.val < num) {
                    node = node.right;
                }
                // 下一层是 null, 到达了原链表, 跳出循环
                if (node.down == null) {
                    break;
                }
                // 继续查找下一层的位置
                node = node.down;
            }

            // 2. 插入新节点
            Node newNode = new Node(num, node.right, null);
            node.right = newNode;
            // 3. 根据扔硬币决定是否生成索引

        }

        /**
         * 遍历跳表，查找与给定值相同的节点，删除每一层
         * 1.获取该指定数据节点的前一个节点
         * 2.与当前层链表断开
         * 3.下移，删除每一层
         * @param num
         * @return
         */
        public boolean erase(int num) {
            boolean exist = false;
            Node n = head;
            // 查找
            while (n != null) {
                // 获取该指定数据节点的前一个节点
                while (n.right != null && n.right.val < num) {
                    n = n.right;
                }
                // 要删除的节点
                // 与当前层链表断开
                Node right = n.right;
                if (right != null && right.val == num) {
                    // n的下一个指向删除节点的右边
                    n.right = right.right;
                    // help GC 回收删除节点的右指针
                    right.right = null;
                    exist = true;
                }
                // 删除下一层
                n = n.down;
            }
            return exist;
        }
    }

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
