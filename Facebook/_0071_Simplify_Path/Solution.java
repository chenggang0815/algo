package Facebook._0071_Simplify_Path;

import java.util.*;

/*
71. Simplify Path

Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.
In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.
The canonical path should have the following format:
The path starts with a single slash '/'.
Any two directories are separated by a single slash '/'.
The path does not end with a trailing '/'.
The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
Return the simplified canonical path.

Example 4:
Input: path = "/a/./b/../../c/"
Output: "/c"
*/

/*
Solution: Stack time:O(n) space:O(n)
1. assume "/a/./b/../../c/" is valid, which means a ---- b
                                                    ---- c

   b,c both are sub directory of a
2. split the path by "/", iterate the array
3. if current array equal ".." and stack is not empty => stack.pop()
4. if current is not ".","..","" => stack.push(str)
5. iterate the stack from the top, so we can use ArrayDeque instead of Stack
6. use StringBuilder instead of "+" can avoid create a lot of Strings objects
*/
public class Solution {
    // 19ms
    static String simplifyPath(String path) {
        ArrayDeque<String> stack = new ArrayDeque<>();
        HashSet<String> set = new HashSet<>(Arrays.asList(".", "..", ""));
        for (String str : path.split("/")){
            if (!stack.isEmpty() && str.equals("..")){
                stack.pollLast();
            }else if (!set.contains(str)){
                stack.addLast(str);
            }
        }


        String res = "";
        while (!stack.isEmpty()){
            res = "/" + stack.pollLast();
        }

        return res.length() == 0 ? "/" : res;
    }

    // 10ms
    public String simplifyPath2(String path) {
        ArrayDeque<String> stack = new ArrayDeque<>();
        HashSet<String> set = new HashSet<>(Arrays.asList(".", "..", ""));
        for (String str : path.split("/")){
            if (!stack.isEmpty() && str.equals("..")){
                stack.pollLast();
            }else if (!set.contains(str)){
                stack.addLast(str);
            }
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()){
            res.append("/" + stack.pollFirst());
        }

        return res.length() == 0 ? "/" : res.toString();
    }

    // 4ms
    public String simplifyPath3(String path) {
        ArrayDeque<String> stack = new ArrayDeque<>();
        HashSet<String> set = new HashSet<>(Arrays.asList(".", "..", ""));
        for (String str : path.split("/")){
            if (!stack.isEmpty() && str.equals("..")){
                stack.pollLast();
            }else if (!set.contains(str)){
                stack.addLast(str);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()){
            res.append("/").append(stack.pollFirst());
        }

        return res.length() == 0 ? "/" : res.toString();
    }

    public static void main(String[] args) {
        System.out.println(simplifyPath("/a/./b/../../c/"));
    }
}
