package LeetCode._0068_Text_Justification;
import java.util.ArrayList;
import java.util.List;

/*
68. Text Justification
Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
For the last line of text, it should be left-justified and no extra space is inserted between words.

Note:
A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.

Example 1:
Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]

Example 2:
Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
Note that the second line is also left-justified becase it contains only one word.
*/

/*
Solution
we can divide the function into 3 parts
part 1: packLines function => pack as many words as you can in each line

part 2: getSpace function => for each line, calculate the space for each word

part 3: process the two edge case, 1. the last line  2. the line which length is 1

tip: use StringBuilder(append) instead of string(+) improve from 11 ms to 2 ms
*/
public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        // pack each line
        List<List<String>> lines = packLines(words, maxWidth);
        List<String> res = new ArrayList<>();
        for(int i = 0; i < lines.size(); i++){
            List<String> line = lines.get(i);
            int length = 0;
            for(String word : line) length += word.length();
            // process the last line
            StringBuilder newLine = new StringBuilder();
            if(i != 0 && i == lines.size() - 1){
                for(String word : line){
                    newLine.append(word).append(" ");
                }
                if(newLine.length() > maxWidth){
                    res.add(newLine.deleteCharAt(newLine.length() - 1).toString());
                    break;
                }
                while(newLine.length() < maxWidth){
                    newLine.append(" ");
                }
                res.add(newLine.toString());
                break;
            }
            // process the line which length is 1
            if(line.size() == 1){
                newLine.append(line.get(0));
                while(newLine.length() < maxWidth){
                    newLine.append(" ");
                }
                res.add(newLine.toString());
                continue;
            }
            // calculate space for the line
            List<String> spaceWord = getSpace(length, line.size(), maxWidth);
            for(int j = 0; j < line.size(); j++){
                if(j !=0 && j == line.size() - 1){
                    newLine.append(line.get(j));
                    break;
                }
                newLine.append(line.get(j)).append(spaceWord.get(j));
            }
            res.add(newLine.toString());
        }

        return res;
    }

    public List<List<String>> packLines(String[] words, int maxWidth){
        List<List<String>> list = new ArrayList<>();
        int width = 0;
        List<String> line = new ArrayList<>();
        int wordCnt = 0;
        for(int i = 0; i < words.length; i++){
            if(width + words[i].length() >= maxWidth - wordCnt + 1){
                list.add(new ArrayList<>(line));
                line = new ArrayList<>();
                line.add(words[i]);
                wordCnt = 1;
                width = words[i].length();
            }else{
                line.add(words[i]);
                width += words[i].length();
                wordCnt++;
            }
        }
        list.add(line);

        return list;
    }

    // length = 10
    // maxWidth = 18
    // cnt = 4
    // a[3 space]b[3 space]c[2 space]d
    static List<String> getSpace(int length, int cnt, int maxWidth){
        List<String> res = new ArrayList<>();
        List<Integer> space = new ArrayList<>();
        int remainSpace = maxWidth - length;
        int eachSpace = remainSpace / (cnt - 1);
        int remainSpace2 = remainSpace % (cnt - 1);
        for(int i = 0; i < cnt - 1; i++){
            space.add(eachSpace);
        }
        for (int i = 0; i < remainSpace2; i++){
            space.set(i, space.get(i) + 1);
        }
        for (int i = 0; i < cnt - 1; i++){
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < space.get(i); j++){
                s.append(" ");
            }
            res.add(s.toString());
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(getSpace(10, 4, 18));
    }
}
