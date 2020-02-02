// (JAVA; python 3)

// 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

// 有效字符串需满足：
// -左括号必须用相同类型的右括号闭合。
// -左括号必须以正确的顺序闭合。
// -注意空字符串可被认为是有效字符串。

// 示例 1:
// 输入: "()"
// 输出: true

// 示例 2:
// 输入: "()[]{}"
// 输出: true

// 示例 3:
// 输入: "(]"
// 输出: false

// 示例 4:
// 输入: "([)]"
// 输出: false

// 示例 5:
// 输入: "{[]}"
// 输出: true

// *-----------------------------------------------------------------------------------------------------------------------------*
方法：栈
算法：
-初始化栈 S。
-一次处理表达式的每个括号。
-如果遇到开括号，我们只需将其推到栈上即可。这意味着我们将稍后处理它，让我们简单地转到前面的 子表达式。
-如果我们遇到一个闭括号，那么我们检查栈顶的元素。如果栈顶的元素是一个 相同类型的 左括号，那么我们将它从栈中弹出并继续处理。否则，这意味着表达式无效。
-如果到最后我们剩下的栈中仍然有元素，那么这意味着表达式无效。

// *----------------------------------------------------------------------------------*
// (JAVA)
// Hash table that takes care of the mappings.
private HashMap<Character, Character> mappings;

// Initialize hash map with mappings. This simply makes the code easier to read.
mappings = new HashMap<Character, Character>();
mappings.put(')', '(');
mappings.put('}', '{');
mappings.put(']', '[');

public boolean isValid(String s) {
  // Initialize a stack to be used in the algorithm.
  Stack<Character> stack = new Stack<Character>();

  for (int i = 0; i < s.length(); i++) {
    char c = s.charAt(i);

    // If the current character is a closing bracket. (')',']','}')
    if (mappings.containsKey(c)) {
      // Get the top element of the stack. If the stack is empty, set a dummy value of '#'
      char topElement = stack.empty() ? '#' : stack.pop();

      // If the mapping for this bracket doesn't match the stack's top element, return false.
      if (topElement != mappings.get(c)) {
        return false;
      }
    } else {
      // If it was an opening bracket, push to the stack. ('(','[','{')
      stack.push(c);
    }
  }

  // If the stack still contains elements, then it is an invalid expression.
  return stack.isEmpty();
}

// *----------------------------------------------------------------------------------*
// (python 3)
def isValid(self, s):
//     """
//     :type s: str
//     :rtype: bool
//     """

//     # The stack to keep track of opening brackets.
    stack = []

//     # Hash map for keeping track of mappings. This keeps the code very clean.
//     # Also makes adding more types of parenthesis easier
    mapping = {")": "(", "}": "{", "]": "["}

//     # For every bracket in the expression.
    for char in s:
//         # If the character is an closing bracket
        if char in mapping:
//             # Pop the topmost element from the stack, if it is non empty
//             # Otherwise assign a dummy value of '#' to the top_element variable
            top_element = stack.pop() if stack else '#'

//             # The mapping for the opening bracket in our hash and the top
//             # element of the stack don't match, return False
            if mapping[char] != top_element:
                return False
        else:
//             # We have an opening bracket, simply push it onto the stack.
            stack.append(char)

//     # In the end, if the stack is empty, then we have a valid expression.
//     # The stack won't be empty for cases like ((()
    return not stack

// *----------------------------------------------------------------------------------*
复杂度分析:
-时间复杂度：O(n)，因为我们一次只遍历给定的字符串中的一个字符并在栈上进行 O(1) 的推入和弹出操作。
-空间复杂度：O(n)，当我们将所有的开括号都推到栈上时以及在最糟糕的情况下，我们最终要把所有括号推到栈上。例如 ((((((((((。

