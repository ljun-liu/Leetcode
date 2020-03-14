# (python 3)

# 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。说明：解集不能包含重复的子集。

# 示例:
# 输入: [1,2,2]
# 输出:
# [
#   [2],
#   [1],
#   [1,2,2],
#   [2,2],
#   [1,2],
#   []
# ]

#----------------------------------------------------------------------------------------------------------------------------#
# 排序+回溯:
# -对数组进行排序，初始化结果res=[]，数组长度为n
# -定义回溯函数track_back(i,tmp)，ii表示当前访问的数组下标，tmp为中间子集。
#   ·将tmp加入到res中。
#   ·若i==n，说明当前路径已经访问完成，需要return。
#   ·执行遍历，遍历区间[i,n)：
#     -若 j>i and nums[j]==nums[j−1]，跳过，此步为了去除重复的子集。
#     -执行track_back(j+1,tmp+[nums[j]])
# -执行track_back(0,[])
# -返回resres

# (1,2,2,3)
# []->1->1,2->1,2,2->1,2,2,3
#             1,2,3<-
#        1,3<-
#     2<-
#      ->...
      
# 复杂度分析:
# -时间复杂度：O(n!)
# -空间复杂度：O(1)
           
#---------------------------------------------------------------#
def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
    n=len(nums)
    nums.sort()
    def track_back(i,tmp):
        res.append(tmp)
        if(i==n):
            return
        for j in range(i,n):
#             j>i: 保证j-1不出界
            if(j>i and nums[j]==nums[j-1]):
                continue
            track_back(j+1,tmp+[nums[j]])
    res=[]
    track_back(0,[])
    return res
