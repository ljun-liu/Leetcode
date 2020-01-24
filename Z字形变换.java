// (JAVA)

// 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
// 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
// L   C   I   R
// E T O E S I I G
// E   D   H   N
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

// *----------------------------------------------------------------------------------------------------------------------------------*
// 方法一：按行排序
// 思路:
// 通过从左向右迭代字符串，我们可以轻松地确定字符位于 Z 字形图案中的哪一行。

// 算法:
// 我们可以使用 min(numRows,len(s)) 个列表来表示 Z 字形图案中的非空行。
// 从左到右迭代 s，将每个字符添加到合适的行。可以使用当前行和当前方向这两个变量对合适的行进行跟踪。
// 只有当我们向上移动到最上面的行或向下移动到最下面的行时，当前方向才会发生改变。

// *----------------------------------------------------------------------------*
public String convert(String s, int numRows) {

    if (numRows == 1) return s;

    List<StringBuilder> rows = new ArrayList<>();
    for (int i = 0; i < Math.min(numRows, s.length()); i++)
        rows.add(new StringBuilder());

    // 此时位于第几行
    int curRow = 0;
    // 上升状态或下降状态
    boolean goingDown = false;

    // 遍历s
    for (char c : s.toCharArray()) {
        // 存储每层的字符串
        rows.get(curRow).append(c);
        if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
        curRow += goingDown ? 1 : -1;
    }

    StringBuilder ret = new StringBuilder();
    for (StringBuilder row : rows) ret.append(row);
    return ret.toString();
}

// *----------------------------------------------------------------------------*
// 复杂度分析:
// 时间复杂度：O(n)，其中 n==len(s)
// 空间复杂度：O(n)
  
// *----------------------------------------------------------------------------------------------------------------------------------*
// 方法二：按行访问

// 思路：
// 按照与逐行读取 Z 字形图案相同的顺序访问字符串。

// 算法：
// 首先访问 行 0 中的所有字符，接着访问 行 1，然后 行 2，依此类推...

// 对于所有整数 k:
// -行 0 中的字符位于索引 k(2⋅numRows−2) 处;
// -行 numRows−1 中的字符位于索引 k(2⋅numRows−2)+numRows−1 处;
// -内部的行 i 中的字符位于索引 k(2⋅numRows−2)+i 以及 (k+1)(2⋅numRows−2)−i 处;

//    | j=0   |       | j=1   |       | j=2   |       | j=3   |         
// ---|-------|-------|-------|-------|-------|-------|-------|--------
// i=0| L(01) |       | C(05) |       |I(09)  |       | R(13) |         
// ---|-------|-------|-------|-------|-------|-------|-------|--------
// i=1| E(02) | T(04) | O(06) | E(08) |S(10)  | I(12) | I(14) | G(16)   
// ---|-------|-------|-------|-------|-------|-------|-------|--------
// i=2| E(03) |       | D(07) |       |H(11)  |       | N(15) |         
//    |<------------->|
//    2row_num - 2为一组

// *----------------------------------------------------------------------------*
public String convert(String s, int numRows) {
    if (numRows == 1) return s;

    StringBuilder ret = new StringBuilder();
    int n = s.length();
    int cycleLen = 2 * numRows - 2;

    for (int i = 0; i < numRows; i++) {
        // 第i行所有字符; j = k*cycleLen (k=0,1,2...)
        for (int j = 0; j + i < n; j += cycleLen) {
            ret.append(s.charAt(j + i));
            // 添加第中间行的中间值
            if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                ret.append(s.charAt(j + cycleLen - i));
        }
    }
    return ret.toString();
}

// *----------------------------------------------------------------------------*
// 复杂度分析:
// -时间复杂度：O(n)，其中 n==len(s)。每个索引被访问一次。
// -空间复杂度：O(n)。对于 C++ 实现，如果返回字符串不被视为额外空间，则复杂度为 O(1)。
