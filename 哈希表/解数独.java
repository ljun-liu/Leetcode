// (JAVA)

// 编写一个程序，通过已填充的空格来解决数独问题。

// 一个数独的解法需遵循如下规则：
// -数字 1-9 在每一行只能出现一次。
// -数字 1-9 在每一列只能出现一次。
// -数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
// 空白格用 '.' 表示。

// Note:
// -给定的数独序列只包含数字 1-9 和字符 '.' 。
// -你可以假设给定的数独只有唯一解。
// -给定数独永远是 9x9 形式的。

// *-----------------------------------------------------------------------------------------------------------------------------*
// ·第一个叫做 约束编程。基本的意思是在放置每个数字时都设置约束。在数独上放置一个数字后立即排除当前* 行，列 和 子方块 *对该数字的使用。这会
// 传播* 约束条件 *并有利于减少需要考虑组合的个数。

// ·第二个叫做 回溯。让我们想象一下已经成功放置了几个数字在数独上。但是该组合不是最优的并且不能继续放置数字了。该怎么办？ 回溯。意思是回退，
// 来改变之前放置的数字并且继续尝试。如果还是不行，再次* 回溯 *。

// 如何枚举子方块：（可见"有效的数独"）
// 使用 方块索引= (行 / 3) * 3 + 列 / 3 （其中 / 表示整数除法。）

// 回溯函数：backtrack(row = 0, col = 0)。
// -从最左上角的方格开始 row = 0, col = 0。直到到达一个空方格。
// -从1 到 9 迭代循环数组，尝试放置数字 d 进入 (row, col) 的格子。
//    如果数字 d 还没有出现在当前行，列和子方块中：
//       ·将 d 放入 (row, col) 格子中。
//       ·记录下 d 已经出现在当前行，列和子方块中。
//       ·如果这是最后一个格子row == 8, col == 8 ：意味着已经找出了数独的解。
//       ·否则放置接下来的数字。
//       ·如果数独的解还没找到：将最后的数从 (row, col) 移除。

// *--------------------------------------------------------------------*
// box size
int n = 3;
// row size
int N = n * n;

// 每行每列每格的数字（1-9）填充情况
int [][] rows = new int[N][N + 1];
int [][] columns = new int[N][N + 1];
int [][] boxes = new int[N][N + 1];

// 数独全盘
char[][] board;

boolean sudokuSolved = false;

public boolean couldPlace(int d, int row, int col) {
  /*
  Check if one could place a number d in (row, col) cell
  */
  // idx: box index
  int idx = (row / n ) * n + col / n;
  return rows[row][d] + columns[col][d] + boxes[idx][d] == 0;
}

public void placeNumber(int d, int row, int col) {
  /*
  Place a number d in (row, col) cell
  */
  int idx = (row / n ) * n + col / n;

  rows[row][d]++;
  columns[col][d]++;
  boxes[idx][d]++;
  board[row][col] = (char)(d + '0');
}

public void removeNumber(int d, int row, int col) {
  /*
  Remove a number which didn't lead to a solution
  */
  int idx = (row / n ) * n + col / n;
  rows[row][d]--;
  columns[col][d]--;
  boxes[idx][d]--;
  board[row][col] = '.';
}

public void placeNextNumbers(int row, int col) {
  /*
  Call backtrack function in recursion to continue to place numbers till the moment we have a solution
  */
  // if we're in the last cell that means we have the solution
  if ((col == N - 1) && (row == N - 1)) {
    sudokuSolved = true;
  }
  // if not yet
  else {
    // if we're in the end of the row go to the next row
    if (col == N - 1) backtrack(row + 1, 0);
      // go to the next column
    else backtrack(row, col + 1);
  }
}

public void backtrack(int row, int col) {
  // if the cell is empty
  if (board[row][col] == '.') {
    // iterate over all numbers from 1 to 9
    for (int d = 1; d < 10; d++) {
      if (couldPlace(d, row, col)) {
        placeNumber(d, row, col);
        placeNextNumbers(row, col);
        // if sudoku is solved, there is no need to backtrack since the single unique solution is promised
        if (!sudokuSolved) removeNumber(d, row, col);
      }
    }
  }
  else placeNextNumbers(row, col);
}

public void solveSudoku(char[][] board) {
  this.board = board;

  // init rows, columns and boxes
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      char num = board[i][j];
      if (num != '.') {
        int d = Character.getNumericValue(num);
        placeNumber(d, i, j);
      }
    }
  }
  backtrack(0, 0);
}

// *--------------------------------------------------------------------*
// 复杂性分析:
// 这里的时间复杂性是常数由于数独的大小是固定的，因此没有 N 变量来衡量。
// 但是我们可以计算需要操作的次数：(9!)^9。
// 我们考虑一行，即不多于 9 个格子需要填。第一个格子的数字不会多于 9 种情况，两个格子不会多于 9×8 种情况，三个格子不会多于 9×8×7 种情况等
// 等。总之一行可能的情况不会多于 9! 种可能，所有行不会多于 (9!)^9 种情况。

// 比较一下：
// 9^{81}= 196627050475552913618075908526912116283103450944214766927315415537966391196809为蛮力法，
// 而 (9!)^{9} = 109110688415571316480344899355894085582848000000000为回溯法，
// 即数字的操作次数减少了 10^{27} 倍！

// 空间复杂性：数独大小固定，空间用来存储数独，行，列和子方块的结构，每个有 81 个元素。
