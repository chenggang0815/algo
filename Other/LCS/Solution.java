package Other.LCS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

*/
public class Solution {
    // Java program to print longest palindromic
//subsequence


        /* Returns LCS X and Y */
        static String lcs(String a, String b) {
            int m = a.length();
            int n = b.length();
            System.out.println(n);
            System.out.println(m);
            char X[] = a.toCharArray();
            char Y[] = b.toCharArray();
            System.out.println(X);
            System.out.println(Y);
            int L[][] = new int[m + 1][n + 1];
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    if (i == 0 || j == 0) {
                        L[i][j] = 0;
                        list.add(0);
                    } else if (X[i - 1] == Y[j - 1]) {
                        L[i][j] = L[i - 1][j - 1] + 1;
                        list.add(L[i - 1][j - 1] + 1);
                    } else {
                        L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
                        list.add(Math.max(L[i - 1][j], L[i][j - 1]));
                    }
                }
            }
            System.out.println(list);
            for (int[] nums : L){
                System.out.println(Arrays.toString(nums));
            }

            // Following code is used to print LCS
            int index = L[m][n];

            // Create a String length index+1 and
            // fill it with \0
            char[] lcs = new char[index];

            // Start from the right-most-bottom-most
            // corner and one by one store characters
            // in lcs[]
            int i = m, j = n;
            System.out.println(j);
            System.out.println(i);
            System.out.println(Arrays.toString(X));

            while (i > 0 && j > 0) {
                // If current character in X[] and Y
                // are same, then current character
                // is part of LCS

                if (X[i - 1] == Y[j - 1]) {
                    // Put current character in result
                    lcs[index - 1] = X[i - 1];
                    i--;
                    j--;

                    // reduce values of i, j and index
                    index--;
                } // If not same, then find the larger of
                // two and go in the direction of larger
                // value
                else if (L[i - 1][j] > L[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }

                System.out.println(Arrays.toString(lcs));
            }

            String ans = "";
            for (int x = 0; x < lcs.length; x++) {
                ans += lcs[x];
            }

            return ans;
        }

        // Returns longest palindromic subsequence
// of str
        static String longestPalSubseq(String str) {
            // Find reverse of str
            String rev = str;
            rev = reverse(rev);

            // Return LCS of str and its reverse
            return lcs(str, rev);
        }

        static String reverse(String str) {
            String ans = "";
            // convert String to character array
            // by using toCharArray
            char[] try1 = str.toCharArray();

            for (int i = try1.length - 1; i >= 0; i--) {
                ans += try1[i];
            }
            return ans;
        }

    public static void main(String[] args) {
        String str = "badccbda";
        String s = longestPalSubseq(str);
        System.out.println(s.length());
        System.out.println(s);
    }
}
