// (JAVA)
// 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。你可以假设
// nums1 和 nums2 不会同时为空。

// tip: 时间复杂度为O(log(m + n))->二分法

// *------------------------------------------------*
// 示例 1:
// nums1 = [1, 3]
// nums2 = [2]
// 则中位数是 2.0
  
// 示例 2:
// nums1 = [1, 2]
// nums2 = [3, 4]
// 则中位数是 (2 + 3)/2 = 2.5

// *------------------------------------------------*
// 方法：递归法
// 在统计中，中位数被用来：将一个集合划分为两个*长度相等*的子集，其中一个子集中的元素*总是大于*另一个子集中的元素。

// 我们只需要保证：
// -i+j=m-i+n-j（或：m−i+n−j+1）；如果 n≥m，只需要使 i=0~m, j=((m+n+1)/2)-i (为什么n≥m？由于0≤i≤m 且 j=(m+n+1)/2−i 不是负数)
// -B[j−1] ≤ A[i] 以及 A[i−1]≤B[j]

// *------------------------------------------------*
public double findMedianSortedArrays(int[] A, int[] B) {
    int m = A.length;
    int n = B.length;
    // to ensure m<=n
    if (m > n) { 
        // 交换A、B；m、n
        int[] temp = A; A = B; B = temp;
        int tmp = m; m = n; n = tmp;
    }
    int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
    while (iMin <= iMax) {
        // 由iMin和iMax来调整i的值
        int i = (iMin + iMax) / 2;
        // 由i来调整j的值
        int j = halfLen - i;
        if (i < iMax && B[j-1] > A[i]){
            // i is too small
            iMin = i + 1; 
        }
        else if (i > iMin && A[i-1] > B[j]) {
            // i is too big
            iMax = i - 1; 
        }
        // i is perfect
        else {
            int maxLeft = 0;
            // 为0分别讨论：因为i-1（或j-1）此时i此时为-1
            if (i == 0) { maxLeft = B[j-1]; }
            else if (j == 0) { maxLeft = A[i-1]; }
            else { maxLeft = Math.max(A[i-1], B[j-1]); }
            if ( (m + n) % 2 == 1 ) { return maxLeft; }

            int minRight = 0;
            // i，j均代表minRight
            // 分别讨论：因为i == m（或j == n）时，索引出界
            if (i == m) { minRight = B[j]; }
            else if (j == n) { minRight = A[i]; }
            else { minRight = Math.min(B[j], A[i]); }

            return (maxLeft + minRight) / 2.0;
        }
    }
    return 0.0;
}

// *------------------------------------------------*
// 复杂度分析:
// -时间复杂度:O(log(min(m,n)))，首先，查找的区间是 [0, m]。而该区间的长度在每次循环之后都会减少为原来的一半。所以，我们只需要执行 log(m) 
//            次循环。由于我们在每次循环中进行常量次数的操作，所以时间复杂度为 O(log(m))。由于 m≤n，所以时间复杂度是 O(log(min(m,n)))。
// -空间复杂度:O(1)，我们只需要恒定的内存来存储 99 个局部变量， 所以空间复杂度为 O(1)。
