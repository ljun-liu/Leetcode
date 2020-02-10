// (JAVA)

// 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。返回被除数 dividend 除以除数 divisor 得到的商。

// 示例 1:
// 输入: dividend = 10, divisor = 3
// 输出: 3

// 示例 2:
// 输入: dividend = 7, divisor = -3
// 输出: -2

// 说明:
// -被除数和除数均为 32 位有符号整数。
// -除数不为 0。
// -假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。

// *--------------------------------------------------------------------------------------------------------------------------------*
// 用左移(<<)去减。
// 正数边界问题比较麻烦，所以改用负数计算。

*------------------------------------------------------------------------*
public int divide(int dividend, int divisor) {
//     位异或( ^ )：判断是否异号，异号为true
    boolean sign = (dividend > 0) ^ (divisor > 0);
    int result = 0;
    if(dividend>0) dividend = -dividend;
    if(divisor>0) divisor = -divisor;
    
//     dividend: 被除数; divisor: 除数
    while(dividend <= divisor) {
        int temp_result = -1;
        int temp_divisor = divisor;
//         temp_divisor << 1: 2*temp_divisor
        while(dividend <= (temp_divisor << 1)) {
//             判断temp_divisor << 1是否出界
            if(temp_divisor <= (Integer.MIN_VALUE >> 1)) break;
//             temp_result = temp_result * 2
            temp_result = temp_result << 1;            
//             temp_divisor = temp_divisor * 2
            temp_divisor = temp_divisor << 1;
        }
        dividend = dividend - temp_divisor;
//         倍数和（不进里面的循环时，dividend<2*temp_divisor，此时只加-1）
        result += temp_result;
    }
//     同号：取相反数
    if(!sign) {
        if(result <= Integer.MIN_VALUE) return Integer.MAX_VALUE;
        result = - result;
    }
    return result;
}
