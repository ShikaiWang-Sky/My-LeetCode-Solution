package leetcode.editor.cn;

public class SegmentTree {
    int[] value, nums;
    // nums的大小
    int n;

    public SegmentTree(int[] value, int[] nums, int n) {
        this.value = new int[4 * n];
        this.nums = nums;
        this.n = n;
        build(0, 0, n - 1);
    }

    /**
     * value[pos]存储的是nums[left, right]区间的和, 构建线段树
     * @param pos 线段树的根节点
     * @param left nums的左区间
     * @param right nums的右区间
     */
    public void build(int pos, int left, int right) {
        // 递归出口
        if (left == right) {
            value[pos] = nums[left];
            return;
        }
        // left和right的中点
        int mid = (left + right) / 2;
        // 更新左右子树, 得到根节点的值
        build(pos * 2 + 1,left, mid);
        build(pos * 2 + 2, mid + 1, right);
        value[pos] = value[pos * 2 +1] + value[pos * 2 + 2];
    }

    /**
     * value[pos]存储的是nums[left, right]区间的和, 查询某个区间的和
     * @param pos 线段树的根节点
     * @param left 待处理的左区间
     * @param right 待处理的右区间
     * @param qLeft 待查询的左区间
     * @param qRight 待查询的右区间
     * @return 区间和
     */
    public int query(int pos, int left, int right, int qLeft, int qRight) {
        // 递归出口
        // 求和区间超出处理区间的范围
        if (qLeft > right || qRight < left) {
            return 0;
        }
        // 处理区间在查询区间内 => (多个子区间相加, 递归就可以拼成一个完整的区间了, 因为上面的if限制了不能超出范围)
        if (qLeft <= left && qRight >= right) {
            return value[pos];
        }
        // 递归查询
        int mid = (left + right) / 2;
        int leftSum = query(2 * pos + 1, left, mid, qLeft, qRight);
        int rightSum =  query(2 * pos + 2, mid + 1, right, qLeft, qRight);
        return leftSum + rightSum;
    }

    /**
     * 在nums[left, right]范围内更新index的值为newValue
     * @param pos 线段树的根节点
     * @param left 待处理的左区间
     * @param right 待处理的右区间
     * @param index 待更新的值在nums中的位置
     * @param newValue 新的值
     */
    public void update(int pos, int left, int right, int index, int newValue) {
        // 递归出口
        if (left == right && left == index) {
            value[pos] = newValue;
            return;
        }
        // 递归更新
        int mid = (left + right) / 2;
        // 如果小于mid, 左子树更新
        if (index <= mid) {
            update(pos * 2 + 1, left, mid, index, newValue);
        } else {
            // 否则更新右子树
            update(pos * 2 + 2, mid + 1, right, index, newValue);
        }
        // 更新根节点
        value[pos] = value[2 * pos + 1] + value[2 * pos + 2];
    }

}
