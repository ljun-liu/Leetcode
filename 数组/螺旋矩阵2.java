// (JAVA)

// 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。

// 示例:
// 输入: 3
// 输出:
// [
//  [ 1, 2, 3 ],
//  [ 8, 9, 4 ],
//  [ 7, 6, 5 ]
// ]

// *---------------------------------------------------------------------------------------------------------------------------------*
// 思路见leetcode 59

// *-------------------------------------------------------------*
public int[][] generateMatrix(int n) {
    int l = 0, r = n - 1, t = 0, b = n - 1;
    int[][] mat = new int[n][n];
    int num = 1, tar = n * n;
    while(num <= tar){
        // left to right.
        for(int i = l; i <= r; i++) mat[t][i] = num++; 
        t++;
        // top to bottom.
        for(int i = t; i <= b; i++) mat[i][r] = num++; 
        r--;
        // right to left.
        for(int i = r; i >= l; i--) mat[b][i] = num++; 
        b--;
        // bottom to top.
        for(int i = b; i >= t; i--) mat[i][l] = num++; 
        l++;
    }
    return mat;
}
