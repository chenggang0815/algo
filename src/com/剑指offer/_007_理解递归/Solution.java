package com.剑指offer._007_理解递归;
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

    static void printout(int n){
        System.out.println(n);
        if (n > 0) {
        printout(n-1);
        printout(n-100);
        }
        System.out.println("最后一句");

        }


    public static void main(String[] args) {

        //recursion1(1);
        //int result=recursion2(1);
        //System.out.printf("main - result: %d\n",result);
        printout(4);
    }
}

