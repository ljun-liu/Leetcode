// (JAVA)

// 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

// 示例:
// board =
// [
//   ['A','B','C','E'],
//   ['S','F','C','S'],
//   ['A','D','E','E']
// ]

// 给定 word = "ABCCED", 返回 true.
// 给定 word = "SEE", 返回 true.
// 给定 word = "ABCB", 返回 false.

// *--------------------------------------------------------------------------------------------------------------------------------*
// 这是一个使用回溯算法解决的问题，涉及的知识点有 DFS 和状态重置。

// *-----------------------------------------------------------------*
private boolean[][] marked;

//        x-1,y
// x,y-1  x,y    x,y+1
//        x+1,y
private int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
// 盘面上有多少行
private int m;
// 盘面上有多少列
private int n;
private String word;
private char[][] board;

public boolean exist(char[][] board, String word) {
    m = board.length;
    if (m == 0) {
        return false;
    }
    n = board[0].length;
    marked = new boolean[m][n];
    this.word = word;
    this.board = board;

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (dfs(i, j, 0)) {
                return true;
            }
        }
    }
    return false;
}

private boolean dfs(int i, int j, int start) {
    if (start == word.length() - 1) {
        return board[i][j] == word.charAt(start);
    }
    if (board[i][j] == word.charAt(start)) {
        marked[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int newX = i + direction[k][0];
            int newY = j + direction[k][1];
            if (inArea(newX, newY) && !marked[newX][newY]) {
                if (dfs(newX, newY, start + 1)) {
                    return true;
                }
            }
        }
        marked[i][j] = false;
    }
    return false;
}

private boolean inArea(int x, int y) {
    return x >= 0 && x < m && y >= 0 && y < n;
}

public static void main(String[] args) {

//        char[][] board =
//                {
//                        {'A', 'B', 'C', 'E'},
//                        {'S', 'F', 'C', 'S'},
//                        {'A', 'D', 'E', 'E'}
//                };
//
//        String word = "ABCCED";


    char[][] board = {{'a', 'b'}};
    String word = "ba";
    Solution solution = new Solution();
    boolean exist = solution.exist(board, word);
    System.out.println(exist);
}

// *-----------------------------------------------------------------*
// 说明：
// 1、偏移量数组在二维平面内是经常使用的，可以把它的设置当做一个技巧，并且在这个问题中，偏移量数组内的 4 个偏移的顺序无关紧要；
// 说明：类似使用这个技巧的问题还有：「力扣」第 130 题：被围绕的区域、「力扣」第 200 题：岛屿数量。
// 2、对于这种搜索算法，我认为理解 DFS 和状态重置并不难，代码编写也相对固定，难在代码的编写和细节的处理，建议多次编写，自己多总结多思考，把自己
// 遇到的坑记下。

