# (python 3)

# 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。注意子串要与 words 中的单词完
# 全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。

# 示例 1：
# 输入：
#   s = "barfoothefoobarman",
#   words = ["foo","bar"]
# 输出：[0,9] （解释：从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。输出的顺序不重要, [9,0] 也是有效答案。）

# 示例 2：
# 输入：
#   s = "wordgoodgoodgoodbestword",
#   words = ["word","good","best","word"]
# 输出：[]

# *------------------------------------------------------------------------------------------------------------------------------*
# 思路：
# 每次从 s 截取一定长度（固定）的字符串，看这段字符串出现单词个数是否和要匹配的单词个数相等!

# *-------------------------------------------------------------------*
# def findSubstring(self, s: str, words: List[str]) -> List[int]:
#     from collections import Counter
#     if not s or not words:return []
#     all_len = sum(map(len, words))
#     n = len(s)
#     words = Counter(words)
#     res = []
#     for i in range(0, n - all_len + 1):
#         tmp = s[i:i+all_len]
#         flag = True
#         for key in words:
#             if words[key] != tmp.count(key):
#                 flag = False
#                 break
#         if flag:res.append(i)
#     return res
  
# *-------------------------------------------------------------------*
# 但是比如：s = "ababaab", words = ["ab","ba","ba"] 就会报错！
# 错误原因：因为计算时候我们会从字符串中间计算，也就是说会出现 *单词截断* 的问题。

# *------------------------------------------------------------------------------------------------------------------------------*
# 思路一：因为单词长度固定的，我们可以计算出截取字符串的单词个数是否和 words 里相等，所以我们可以借用哈希表。一个是哈希表是 words，一个哈
# 希表是截取的字符串，比较两个哈希是否相等！

# *-------------------------------------------------------------------*
def findSubstring(self, s: str, words: List[str]) -> List[int]:
    from collections import Counter
    if not s or not words:return []
    one_word = len(words[0])
    all_len = len(words) * one_word
    n = len(s)
    words = Counter(words)
    res = []
    for i in range(0, n - all_len + 1):
        tmp = s[i:i+all_len]
        c_tmp = []
        for j in range(0, all_len, one_word):
            c_tmp.append(tmp[j:j+one_word])
        if Counter(c_tmp) == words:
            res.append(i)
    return res
  
# *-------------------------------------------------------------------*
# 因为遍历和比较都是线性的，所以时间复杂度：O(n^2)

# *------------------------------------------------------------------------------------------------------------------------------*
# 思路二：
# 滑动窗口！
# 我们一直在 s 维护着所有单词长度总和的一个长度队列！

# 时间复杂度：O(n)

# *-------------------------------------------------------------------*
# def findSubstring(self, s: str, words: List[str]) -> List[int]:
#     from collections import Counter
#     if not s or not words:return []
#     one_word = len(words[0])
#     word_num = len(words)
#     n = len(s)
#     words = Counter(words)
#     res = []
#     for i in range(0, one_word):
#         cur_cnt = 0
#         left = i
#         right = i
#         cur_Counter = Counter()
#         while right + one_word <= n:
#             w = s[right:right + one_word]
#             right += one_word
#             cur_Counter[w] += 1
#             cur_cnt += 1
#             while cur_Counter[w] > words[w]:
#                 left_w = s[left:left+one_word]
#                 left += one_word
#                 cur_Counter[left_w] -= 1
#                 cur_cnt -= 1
#             if cur_cnt == word_num :
#                 res.append(left)
#     return res
  
# 再优化：
# *-------------------------------------------------------------------*
def findSubstring(s: str, words: List[str]) -> List[int]:
    from collections import Counter
    if not s or not words:return []
    
    one_word = len(words[0])
    word_num = len(words)
    n = len(s)
    
    if n < one_word: return []
    
#     自动生成{word:count}字典
    words = Counter(words)
    res = []
#     one_word为一个周期
    for i in range(0, one_word):
        cur_cnt = 0
        left = i
        right = i
        cur_Counter = Counter()
#         s中至少包含一个word
        while right + one_word <= n:
            w = s[right:right + one_word]   
#             下一个word
            right += one_word
            if w not in words:
#                 指向下一个word
                left = right
                cur_Counter.clear()
                cur_cnt = 0
            else:
                cur_Counter[w] += 1
                cur_cnt += 1
#                 w长度超长，减少cur_Counter直至等长
                while cur_Counter[w] > words[w]:
                    left_w = s[left:left+one_word]
                    left += one_word
                    cur_Counter[left_w] -= 1
                    cur_cnt -= 1
                if cur_cnt == word_num :
                    res.append(left)
    return res
