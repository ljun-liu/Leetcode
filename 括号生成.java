(python 3)

给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。

例如，给出 n = 3，生成结果为：
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

方法一：暴力法
思路

我们可以生成所有 2^{2n}2 
2n
  个 '(' 和 ')' 字符构成的序列。然后，我们将检查每一个是否有效。

算法

为了生成所有序列，我们使用递归。长度为 n 的序列就是 '(' 加上所有长度为 n-1 的序列，以及 ')' 加上所有长度为 n-1 的序列。

为了检查序列是否为有效的，我们会跟踪 平衡，也就是左括号的数量减去右括号的数量的净值。如果这个值始终小于零或者不以零结束，该序列就是无效的，否则它是有效的。

JavaPython
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current))
                result.add(new String(current));
        } else {
            current[pos] = '(';
            generateAll(current, pos+1, result);
            current[pos] = ')';
            generateAll(current, pos+1, result);
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return (balance == 0);
    }
}

def generateParenthesis(self, N):
    if N == 0: return ['']
    ans = []
    for c in xrange(N):
        for left in self.generateParenthesis(c):
            for right in self.generateParenthesis(N-1-c):
                ans.append('({}){}'.format(left, right))
    return ans
复杂度分析

时间复杂度：O(2^{2n}n)O(2 
2n
 n)，对于 2^{2n}2 
2n
  个序列中的每一个，我们用于建立和验证该序列的复杂度为 O(n)O(n)。

空间复杂度：O(2^{2n}n)O(2 
2n
 n)，简单地，每个序列都视作是有效的。请参见 方法三 以获得更严格的渐近界限。

方法二：回溯法
思路和算法

只有在我们知道序列仍然保持有效时才添加 '(' or ')'，而不是像 方法一 那样每次添加。我们可以通过跟踪到目前为止放置的左括号和右括号的数目来做到这一点，

如果我们还剩一个位置，我们可以开始放一个左括号。 如果它不超过左括号的数量，我们可以放一个右括号。

JavaPython
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, String cur, int open, int close, int max){
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }

        if (open < max)
            backtrack(ans, cur+"(", open+1, close, max);
        if (close < open)
            backtrack(ans, cur+")", open, close+1, max);
    }
}
复杂度分析

我们的复杂度分析依赖于理解 generateParenthesis(n) 中有多少个元素。这个分析超出了本文的范畴，但事实证明这是第 n 个卡塔兰数 \dfrac{1}{n+1}\binom{2n}{n} 
n+1
1
​	
 ( 
n
2n
​	
 )，这是由 \dfrac{4^n}{n\sqrt{n}} 
n 
n
​	
 
4 
n
 
​	
  渐近界定的。

时间复杂度：O(\dfrac{4^n}{\sqrt{n}})O( 
n
​	
 
4 
n
 
​	
 )，在回溯过程中，每个有效序列最多需要 n 步。

空间复杂度：O(\dfrac{4^n}{\sqrt{n}})O( 
n
​	
 
4 
n
 
​	
 )，如上所述，并使用 O(n)O(n) 的空间来存储序列。

方法三：闭合数
思路

为了枚举某些内容，我们通常希望将其表示为更容易计算的不相交子集的总和。

考虑有效括号序列 S 的 闭包数：至少存在 index >= 0，使得 S[0], S[1], ..., S[2*index+1]是有效的。 显然，每个括号序列都有一个唯一的闭包号。 我们可以尝试单独列举它们。

算法

对于每个闭合数 c，我们知道起始和结束括号必定位于索引 0 和 2*c + 1。然后两者间的 2*c 个元素一定是有效序列，其余元素一定是有效序列。

JavaPython
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left: generateParenthesis(c))
                    for (String right: generateParenthesis(n-1-c))
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }
}
复杂度分析

时间和空间复杂度：O(\dfrac{4^n}{\sqrt{n}})O( 
n
​	
 
4 
n
 
​	
 )，该分析与 方法二 类似。

作者：LeetCode
链接：https://leetcode-cn.com/problems/generate-parentheses/solution/gua-hao-sheng-cheng-by-leetcode/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
