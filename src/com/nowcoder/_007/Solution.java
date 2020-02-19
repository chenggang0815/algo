package com.nowcoder._007;
//理解递归的执行顺序

public class Solution {

    public static void recursion1(int n){
        System.out.printf("1th - level: %d\n ",n);
        if (n<3){
            recursion1(n+1);
        }
        System.out.printf("2th - level: %d\n ",n);

    }

    public static int recursion2(int n){
        if (n>3){
            return n;
        }
        System.out.printf("1th - level: %d\n",n);

        int result = recursion2(n+1);
        System.out.printf("2th - level: %d\n",n);
        System.out.printf("2th - result: %d\n",result);
        return n;
    }

    public static void main(String[] args) {

        //recursion1(1);
        int result=recursion2(1);
        System.out.printf("main - result: %d\n",result);

    }
}

