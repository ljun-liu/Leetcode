// (JAVA)

// 给出一个无重叠的 ，按照区间起始端点排序的区间列表。在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合
// 并区间）。

// 示例 1:
// 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
// 输出: [[1,5],[6,9]]

// 示例 2:
// 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
// 输出: [[1,2],[3,10],[12,16]] （解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。）

// *-------------------------------------------------------------------------------------------------------------------------------*
// 贪心算法：
// 贪心算法一般用来解决需要 “找到要做某事的最小数量” 或 “找到在某些情况下适合的最大物品数量” 的问题，且提供的是无序的输入。贪心算法的思想是每一
// 步都选择最佳解决方案，最终获得全局最佳的解决方案。

// 标准解决方案具有 O(NlogN) 的时间复杂度且由以下两部分组成：
//  -思考如何排序输入数据 O(NlogN) 的时间复杂度）。
//  -思考如何解析排序后的数据（O(N) 的时间复杂度）

// 如果输入数据本身有序，则我们不需要进行排序，那么该贪心算法具有 O(N) 的时间复杂度。
// 如何证明你的贪心思想具有全局最优的效果：可以使用反证法来证明。见了leetcode 57

// 算法：
//  -将 newInterval 之前开始的区间添加到输出。
//  -添加 newInterval 到输出，若 newInterval 与输出中的最后一个区间重合则合并他们。
//  -一个个添加区间到输出，若有重叠部分则合并他们。

// *----------------------------------------------------------------*
public int[][] insert(int[][] intervals, int[] newInterval) {
  // init data
  int newStart = newInterval[0], newEnd = newInterval[1];
  int idx = 0, n = intervals.length;
  LinkedList<int[]> output = new LinkedList<int[]>();

  // add all intervals starting before newInterval
  while (idx < n && newStart > intervals[idx][0])
    output.add(intervals[idx++]);

  // add newInterval
  int[] interval = new int[2];
  // if there is no overlap, just add the interval
  if (output.isEmpty() || output.getLast()[1] < newStart)
    output.add(newInterval);
  // if there is an overlap, merge with the last interval
  else {
    interval = output.removeLast();
    interval[1] = Math.max(interval[1], newEnd);
    output.add(interval);
  }

  // add next intervals, merge with newInterval if needed
  while (idx < n) {
    interval = intervals[idx++];
    int start = interval[0], end = interval[1];
    // if there is no overlap, just add an interval
    if (output.getLast()[1] < start) output.add(interval);
    // if there is an overlap, merge with the last interval
    else {
      interval = output.removeLast();
      interval[1] = Math.max(interval[1], end);
      output.add(interval);
    }
  }
  return output.toArray(new int[output.size()][2]);
}
  
// *----------------------------------------------------------------*
// 复杂度分析:
// -时间复杂度：O(N)。我们只遍历了一次输入元素。
// -空间复杂度：O(N)，输出答案所使用的空间。
