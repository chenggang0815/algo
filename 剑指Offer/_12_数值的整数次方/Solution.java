package 剑指Offer._12_数值的整数次方;
/*
给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。

保证base和exponent不同时为0

思路1：数学解
1. base == 0 :
      exp <0 分母为负数
      exp =0 return 1;
      exp >0 return 0;
2. base !=0 :
      exp <0 exp = -exp
      exp =0 return 1;
      exp >0 return base^exp


思路2：暴力递归
思路3：递归（快速幂）
思路4：非递归（快速幂）

 */
public class Solution {
    // time: o(n) space: o(1)
    static double Power1(double base, int exponent) {
        if(exponent == 0) return 1;
        if(base == 0 && exponent < 0){
            throw new RuntimeException("分母不能为0");
        }

        int exp = (exponent > 0 ? exponent : -exponent);
        double res = 1.0;
        for(int i = 0; i < exp; i++){
            res *= base;
        }

        if(exponent < 0) return 1/res;
        return res;
    }
/*
暴力递归
 */
    // time: o(n) space: o(n)
    static double Power2(double base, int exponent) {
        if (base == 0 && exponent < 0){
            throw new RuntimeException("分母不能为0");
        }
        if (exponent == 0) return 1;

        int exp = exponent >0 ? exponent : -exponent;
        if (exponent > 0) {
            return base * Power1(base, exp - 1);
        }else {
            return 1/(base*Power2(base, exp - 1));
        }
    }
/*
递归（快速幂）
1. 偶数：x^8 = x^4^2 => x^n = x^(n/2)^2
2. 奇数：x^7 = x*x^3^2  => x^n = x*x^(n/2)^2
*/
    // time: o(log(n)) space: o(log(n))
    static double Power3(double base, int exponent) {
        if (exponent == 0) return 1;
        if (base == 0 && exponent < 0){
            throw new RuntimeException("分母不能为0");
        }

        int exp = exponent > 0 ? exponent : -exponent;
        if (exponent > 0){
            if ((exp & 1) == 1){ //奇数
                return base * Power3(base,exp/2) * Power3(base,exp/2);
            }else {
                return Power3(base,exp/2) * Power3(base, exp/2);
            }
        }else{
            if ((exp & 1) == 1){ //奇数
                return 1 / (base * Power3(base,exp/2) * Power3(base,exp/2));
            }else {
                return 1 / (Power3(base,exp/2) * Power3(base, exp/2));
            }
        }
    }

/*
非递归（快速幂） eg: x^14
1. 14的二进制: 1110 = 1*2^3 + 1*2^2 + 1*2^1 + 0*2^0
2. x^14 = x^(1*2^3 + 1*2^2 + 1*2^1 + 0*2^0) = x^(1*2^3 + 1*2^2 + 1*2^1 + 0*1)
从右自左： 如果位数 == 1 则 res = res * base, base更新 => base = base * base ;
          如果位数 == 0 则 res不更新；base更新

base每次更新的意义：
x = x * x = x^2
x = x^2 * x^2 = x^4
x = x^4 * x^4 = x^8
分别对应了二进制每个位数上的值

每次位数==1才乘以结果的意义：
因为位数为0时，值等于x^0
*/
    // time: o(log(n)) space: o(1)
    static double Power4(double base, int exponent) {
        if (exponent == 0) return 1;
        if (base == 0 && exponent < 0){
            throw new RuntimeException("分母不能为0");
        }

        double res = 1.0;
        int exp = exponent > 0 ? exponent : -exponent;
        while (exp != 0){
            if ((exp & 1) == 1){
                res *= base;
            }
            base *= base;
            exp >>= 1;
        }

        if (exponent > 0) return res;
        else return 1/res;
    }
        public static void main(String[] args) {
        System.out.println(Power4(2,-3));

    }
}
