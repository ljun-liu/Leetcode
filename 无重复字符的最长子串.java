// (JAVA)
// 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

// 示例 1:
// 输入: "abcabcbb"
// 输出: 3 
// 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

// 示例 2:
// 输入: "bbbbb"
// 输出: 1
// 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

// 示例 3:
// 输入: "pwwkew"
// 输出: 3
// 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

// *----------------------------------------------------------------------------------------------------------------------------------*
// 方法一：暴力法
// 题目更新后由于时间限制，会出现 TLE。

// 思路:
// 逐个检查所有的子字符串，看它是否不含有重复的字符。

// 算法:
// 假设我们有一个函数 boolean allUnique(String substring) ，如果子字符串中的字符都是唯一的，它会返回 true，否则会返回 false。 我们可以遍历
// 给定字符串 s 的所有可能的子字符串并调用函数 allUnique。 如果事实证明返回值为 true，那么我们将会更新无重复字符子串的最大长度的答案。

// *--------------------------------------------------------------------*
public int lengthOfLongestSubstring(String s) {
    int n = s.length();
    int ans = 0;
    for (int i = 0; i < n; i++)
        for (int j = i + 1; j <= n; j++)
            if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
    return ans;
}

public boolean allUnique(String s, int start, int end) {
    Set<Character> set = new HashSet<>();
    for (int i = start; i < end; i++) {
        Character ch = s.charAt(i);
        if (set.contains(ch)) return false;
        set.add(ch);
     }
    return true;
}

// *--------------------------------------------------------------------*
// 复杂度分析：
// -时间复杂度:O(n^3)
// -空间复杂度:O(min(n, m))，我们需要 O(k) 的空间来检查子字符串中是否有重复字符，其中 k 表示 Set 的大小。而 Set 的大小取决于字符串 n 的大
//            小以及字符集/字母 m 的大小。


// *----------------------------------------------------------------------------------------------------------------------------------*
// 方法二：滑动窗口
// 算法:
// 暴力法太慢了。那么我们该如何优化它呢？在暴力法中，我们会反复检查一个子字符串是否含有有重复的字符，但这是没有必要的。如果从索引 i 到 j - 1 
// 之间的子字符串 s_{ij}已经被检查为没有重复字符。我们只需要检查 s[j] 对应的字符是否已经存在于子字符串 s_{ij}中。

// 通过使用 HashSet 作为滑动窗口，我们可以用 O(1) 的时间来完成对字符是否在当前的子字符串中的检查。
// 滑动窗口是数组/字符串问题中常用的抽象概念。 窗口通常是在数组/字符串中由开始和结束索引定义的一系列元素的集合，即 [i, j)（左闭，右开）。而滑
// 动窗口是可以将两个边界向某一方向“滑动”的窗口。例如，我们将 [i, j) 向右滑动 1 个元素，则它将变为 [i+1, j+1)（左闭，右开）。

// *------------------------------------------------------*
public int lengthOfLongestSubstring(String s) {
    int n = s.length();
    Set<Character> set = new HashSet<>();
    int ans = 0, i = 0, j = 0;
    while (i < n && j < n) {
        // try to extend the range [i, j]
        if (!set.contains(s.charAt(j))) {
            set.add(s.charAt(j++));
            ans = Math.max(ans, j - i);
        }
        else {
            set.remove(s.charAt(i++)); 
        }
    }
    return ans;
}

// *------------------------------------------------------*
// 复杂度分析:
// -时间复杂度:O(2n) = O(n)，在最糟糕的情况下，每个字符将被 i 和 j 访问两次。
// -空间复杂度:O(min(m, n))，与之前的方法相同。滑动窗口法需要 O(k) 的空间，其中 k 表示 Set 的大小。而 Set 的大小取决于字符串 n 的大小以及字
//            符集/字母 mm 的大小。


// *----------------------------------------------------------------------------------------------------------------------------------*
// 方法三：优化的滑动窗口
// 上述的方法最多需要执行 2n 个步骤。事实上，它可以被进一步优化为仅需要 n 个步骤。我们可以定义字符到索引的映射，而不是使用集合来判断一个字符是否存
// 在。 当我们找到重复的字符时，我们可以立即跳过该窗口。也就是说，如果 s[j] 在 [i, j) 范围内有与 j' 重复的字符，我们不需要逐渐增加 i 。 我们可以
// 直接跳过 [i，j'] 范围内的所有元素，并将 i 变为 j' + 1。

// *------------------------HashMap-----------------------*
public int lengthOfLongestSubstring(String s) {
    int n = s.length(), ans = 0;
    // current index of character
    Map<Character, Integer> map = new HashMap<>(); 
    // try to extend the range [i, j]
    for (int j = 0, i = 0; j < n; j++) {
        if (map.containsKey(s.charAt(j))) {
            i = Math.max(map.get(s.charAt(j)), i);
        }
        ans = Math.max(ans, j - i + 1);
        map.put(s.charAt(j), j + 1);
    }
    return ans;
}
// Map：（值在i~j之间时）键为滑动窗里的字符

// *------------------Table - ASCII 128-------------------*
// 以前的我们都没有对字符串 s 所使用的字符集进行假设。当我们知道该字符集比较小的时侯，我们可以用一个整数数组作为直接访问表来替换 Map。

// 常用的表如下所示：
// -int [26] ：字母‘a’ - ‘z’ 或 ‘A’ - ‘Z’
// -int [128] ：ASCII码
// -int [256] ：扩展ASCII码

// *------------------------------------------------------*
public int lengthOfLongestSubstring(String s) {
    int n = s.length(), ans = 0;
    // current index of character
    int[] index = new int[128]; 
    // try to extend the range [i, j]
    for (int j = 0, i = 0; j < n; j++) {
        i = Math.max(index[s.charAt(j)], i);
        ans = Math.max(ans, j - i + 1);
        index[s.charAt(j)] = j + 1;
    }
    return ans;
}
// Table：与Map同理。初始值为0，以ASCII码为键，（值在i~j之间时）键为滑动窗里的字符

// *------------------------------------------------------*
// 复杂度分析:
// -时间复杂度：O(n)，索引 j 将会迭代 n 次。
// -空间复杂度（HashMap）：O(min(m, n))，与之前的方法相同。
// -空间复杂度（Table）：O(m)，m 是字符集的大小。






