# (python 3; JAVA)

# 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

# 示例:
# 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
# 输出:
# [
#   ["ate","eat","tea"],
#   ["nat","tan"],
#   ["bat"]
# ]

# 说明：所有输入均为小写字母。不考虑答案输出的顺序。

# *----------------------------------------------------------------------------------------------------------------------------*
# 方法一：排序数组分类
# 思路:
# 当且仅当它们的排序字符串相等时，两个字符串是字母异位词。

# 算法:
# 维护一个映射 ans : {String -> List}，其中每个键 K 是一个排序字符串，每个值是初始输入的字符串列表，排序后等于 K。
# 在 Java 中，我们将键存储为字符串，例如，code。 在 Python 中，我们将键存储为散列化元组，例如，('c', 'o', 'd', 'e')。


# *-----------------------------------------------------------------------*
# (JAVA)
public List<List<String>> groupAnagrams(String[] strs) {
    if (strs.length == 0) return new ArrayList();
    Map<String, List> ans = new HashMap<String, List>();
    for (String s : strs) {
        char[] ca = s.toCharArray();
#         排序
        Arrays.sort(ca);
        String key = String.valueOf(ca);
        if (!ans.containsKey(key)) ans.put(key, new ArrayList());
        ans.get(key).add(s);
    }
    return new ArrayList(ans.values());
}

# *-----------------------------------------------------------------------*
# (python 3)
import collections

def groupAnagrams(self, strs):
    ans = collections.defaultdict(list)
    for s in strs:
        ans[tuple(sorted(s))].append(s)
    return ans.values()

# *-----------------------------------------------------------------------*
# 复杂度分析:
# -时间复杂度：O(NKlogK)，其中 N 是 strs 的长度，而 K 是 strs 中字符串的最大长度。当我们遍历每个字符串时，外部循环具有的复杂度为 O(N)。
#             然后，我们在 O(KlogK) 的时间内对每个字符串排序。
# -空间复杂度：O(NK)，排序存储在 ans 中的全部信息内容。

# *----------------------------------------------------------------------------------------------------------------------------*
# 方法二：按计数分类
# 思路:
# 当且仅当它们的字符计数（每个字符的出现次数）相同时，两个字符串是字母异位词。

# 算法:
# 我们可以将每个字符串 s 转换为字符数 count，由26个非负整数组成，表示 a， b， c 的数量等。我们使用这些计数作为哈希映射的基础。
# 在 Java 中，我们的字符数 count 的散列化表示将是一个用 **＃** 字符分隔的字符串。 例如，abbccc 将表示为 ＃1＃2＃3＃0＃0＃0 ...＃0，
#     其中总共有26个条目。 
# 在 python 中，表示将是一个计数的元组。 例如，abbccc 将表示为 (1,2,3,0,0，...，0)，其中总共有 26 个条目。


# *-----------------------------------------------------------------------*
# (JAVA)
public List<List<String>> groupAnagrams(String[] strs) {
    if (strs.length == 0) return new ArrayList();
    Map<String, List> ans = new HashMap<String, List>();
    int[] count = new int[26];
    for (String s : strs) {
        Arrays.fill(count, 0);
        for (char c : s.toCharArray()) count[c - 'a']++;

        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < 26; i++) {
            sb.append('#');
            sb.append(count[i]);
        }
        String key = sb.toString();
        if (!ans.containsKey(key)) ans.put(key, new ArrayList());
        ans.get(key).add(s);
    }
    return new ArrayList(ans.values());
}

# *-----------------------------------------------------------------------*
# (python 3)
import collections

def groupAnagrams(strs):
    ans = collections.defaultdict(list)
    for s in strs:
        count = [0] * 26
        for c in s:
            count[ord(c) - ord('a')] += 1
        ans[tuple(count)].append(s)
    return ans.values()

# *-----------------------------------------------------------------------*
# 复杂度分析:
# -时间复杂度：O(NK)，其中 N 是 strs 的长度，而 K 是 strs 中字符串的最大长度。计算每个字符串的字符串大小是线性的，我们统计每个字符串。
# -空间复杂度：O(NK)，排序存储在 ans 中的全部信息内容。
