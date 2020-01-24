# (python 3)

# 将字符串转换成整数：
# 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后
# 面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。该字符串除了有
# 效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

# 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。在任何情况下，若函
# 数不能进行有效的转换时，请返回 0。

# 说明：假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回 INT_MAX (231 − 1) 
# 或 INT_MIN (−231)。

# 示例 1:
# 输入: "42"
# 输出: 42
  
# 示例 2:
# 输入: "   -42"
# 输出: -42 （解释: 第一个非空白字符为 '-', 它是一个负号。我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。）
     
# 示例 3:
# 输入: "4193 with words"
# 输出: 4193 （解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。）

# 示例 4:
# 输入: "words and 987"
# 输出: 0 （解释: 第一个非空字符是 'w', 但它不是数字或正、负号。因此无法执行有效的转换。）

# 示例 5:
# 输入: "-91283472332"
# 输出: -2147483648 （解释: 数字 "-91283472332" 超过 32 位有符号整数范围。因此返回 INT_MIN (−231) 。）

# *-----------------------------------------------------------------------------------------------------------------------------------*
# 使用正则表达式：
#   ^：匹配字符串开头
#   [\+\-]：代表一个+字符或-字符
#   ?：前面一个字符可有可无
#   \d：一个数字
#   +：前面一个字符的一个或多个
#   \D：一个非数字字符
#   *：前面一个字符的0个或多个
#   max(min(数字, 2**31 - 1), -2**31) 用来防止结果越界

# 为什么可以使用正则表达式？如果整数过大溢出怎么办？
# 题目中描述： 假设我们的环境只能存储 32 位大小的有符号整数。首先，这个假设对于 Python 不成立，Python 不存在 32 位的 int 类型。其次，即使搜索
# 到的字符串转32位整数可能导致溢出，我们也可以直接通过字符串判断是否存在溢出的情况（比如 try 函数 或 判断字符串长度 + 字符串比较）

# *------------------------------------------------------------------------------------------*
# 在函数 myAtoi 中，参数 s 预期是 str 类型，并且返回 int 类型。子类型允许作为参数。
def myAtoi(self, s: str) -> int:
#     *: 解包(数组转字符串提供给int()) -> re.findall返回的是一个字符串列表，int()数据类型转换不支持列表，用*对列表解包得到字符串
    return max(min(int(*re.findall('^[\+\-]?\d+', s.lstrip())), 2**31 - 1), -2**31)
