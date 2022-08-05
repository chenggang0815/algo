package Facebook._0065_Valid_Number;
/*
65. Valid Number

A valid number can be split up into these components (in order):
1. A decimal number or an integer.
2. (Optional) An 'e' or 'E', followed by an integer.

A decimal number can be split up into these components (in order):
1. (Optional) A sign character (either '+' or '-').
2. One of the following formats:
    2.1 One or more digits, followed by a dot '.'.
    2.2 One or more digits, followed by a dot '.', followed by one or more digits.
    2.3 A dot '.', followed by one or more digits.

An integer can be split up into these components (in order):
1. (Optional) A sign character (either '+' or '-').
2. One or more digits.

For example, all the following are valid numbers:
 ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"],
 while the following are not valid numbers:
 ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].

Given a string s, return true if s is a valid number.
*/
/*
Solution
Approach 1: follow the rules

Approach 2: Deterministic Finite Automaton (DFA)
*/
public class Solution {
    public boolean isNumber(String s) {

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == 'e' || s.charAt(i) == 'E'){
                return (isdecimal(s.substring(0, i)) || isInteger(s.substring(0,i)) ) && isInteger(s.substring(i + 1, s.length()));
            }
        }

        return isdecimal(s) || isInteger(s);

    }

    boolean isdecimal(String s){
        if(s.length() < 2) return false;

        boolean dot = true;
        boolean digit = false;

        for(int i = 0; i < s.length(); i++){
            //System.out.print("isdecimal: " + s.charAt(i) + "\n");
            //System.out.print(Character.isDigit(s.charAt(i)) + "\n");
            if(i == 0 && (s.charAt(i) == '+' || s.charAt(i) == '-')) continue;
            if(s.charAt(i) == '.' && dot){
                dot = false;
                continue;
            }

            if(Character.isDigit(s.charAt(i))){
                digit = true;
                continue;
            }

            return false;
        }

        return digit;
    }

    boolean isInteger(String s){
        if(s.length() == 0) return false;
        boolean sign = false;
        boolean digit = false;
        for(int i = 0; i < s.length(); i++){
            //System.out.print("isInteger: " + s.charAt(i) + "\n");
            //System.out.print(Character.isDigit(s.charAt(i)) + "\n");
            if(i == 0 && (s.charAt(i) == '+' || s.charAt(i) == '-' )){
                sign = true;
                continue;
            }

            if(Character.isDigit(s.charAt(i))){
                digit = true;
                continue;
            }

            return false;
        }

        if(sign && s.length() == 1) return false;

        return digit;
    }
    public static void main(String[] args) {

    }
}
