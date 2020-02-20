# (python 3; JAVA)

# 给定一个没有重复数字的序列，返回其所有可能的全排列。

# 示例:
# 输入: [1,2,3]
# 输出:
# [
#   [1,2,3],
#   [1,3,2],
#   [2,1,3],
#   [2,3,1],
#   [3,1,2],
#   [3,2,1]
# ]

# *----------------------------------------------------------------------------------------------------------------------------*
# 思路1：库函数
# Python3 itertools 文档

# *----------------------------------------------------------*
def permute(self, nums: List[int]) -> List[List[int]]:
    return list(itertools.permutations(nums))
    
# *----------------------------------------------------------------------------------------------------------------------------*
# 思路 2：
# 回溯算法

# *-------------------------------------------------------------------*
# (python 3)
def permute(self, nums: List[int]) -> List[List[int]]:
#     res: 全局变量
    res = []
#     tmp: 局部变量
    def backtrack(nums, tmp):
        print('nums:', nums, 'tmp:', tmp)
        if not nums:
            res.append(tmp)
            print('res:', res,'tmp:', tmp)
            return 
        for i in range(len(nums)):
            backtrack(nums[:i] + nums[i+1:], tmp + [nums[i]])
    backtrack(nums, [])
    return res
 
# nums: [1, 2, 3] tmp: []                                                                      1 —— backtrack([1, 2, 3], [])
    # nums: [2, 3] tmp: [1]                                                                       2 —— backtrack([2, 3], [1])
        # nums: [3] tmp: [1, 2]                                                                       3 —— backtrack([3], [1, 2])
            # nums: [] tmp: [1, 2, 3]                                                                     4 —— backtrack([], [1, 2, 3])
            # ——res: [[1, 2, 3]] tmp: [1, 2, 3] 
        # nums: [2] tmp: [1, 3]                                                                       3 —— backtrack([2], [1, 3])
            # nums: [] tmp: [1, 3, 2]                                                                     4 —— backtrack([], [1, 3, 2])
            # ——res: [[1, 2, 3], [1, 3, 2]] tmp: [1, 3, 2] 
    # nums: [1, 3] tmp: [2]                                                                       2 —— backtrack([1, 3], [2])
        # nums: [3] tmp: [2, 1]                                                                       3 —— backtrack([3], [1, 2])
            # nums: [] tmp: [2, 1, 3]                                                                     4 —— backtrack([], [2, 1, 3])
            # ——res: [[1, 2, 3], [1, 3, 2], [2, 1, 3]] tmp: [2, 1, 3] 
        # nums: [1] tmp: [2, 3]                                                                       3 —— backtrack([1], [2, 3])
            # nums: [] tmp: [2, 3, 1]                                                                     4 —— backtrack([], [2, 3, 1])
            # ——res: [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1]] tmp: [2, 3, 1] 
    # nums: [1, 2] tmp: [3]                                                                       2 —— backtrack([2, 2], [3])
        # nums: [2] tmp: [3, 1]                                                                       3 —— backtrack([2], [3, 1])
            # nums: [] tmp: [3, 1, 2]                                                                     4 —— backtrack([], [3, 1, 2])
            # ——res: [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2]] tmp: [3, 1, 2] 
          # nums: [1] tmp: [3, 2]                                                                     3 —— backtrack([1], [3, 2])
            # nums: [] tmp: [3, 2, 1]                                                                     4 —— backtrack([], [3, 2, 1])
            # ——res: [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]] tmp: [3, 2, 1] 
    
# *-------------------------------------------------------------------*
# (JAVA)
public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    int[] visited = new int[nums.length];
    backtrack(res, nums, new ArrayList<Integer>(), visited);
    return res;

}

private void backtrack(List<List<Integer>> res, int[] nums, ArrayList<Integer> tmp, int[] visited) {
    if (tmp.size() == nums.length) {
        res.add(new ArrayList<>(tmp));
        return;
    }
    for (int i = 0; i < nums.length; i++) {
        if (visited[i] == 1) continue;
        visited[i] = 1;
        tmp.add(nums[i]);
        backtrack(res, nums, tmp, visited);
        visited[i] = 0;
        tmp.remove(tmp.size() - 1);
    }
}
