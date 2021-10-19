package Amazon._0828_Count_Unique_Characters_of_All_Substrings_of_a_Given_String;
import java.util.ArrayList;
import java.util.List;

/*

*/
public class Solution {
    static List<String> subString(String str){
        List<String> res = new ArrayList<>();
        backTracking(res, str, 0, new StringBuilder());

        return res;
    }

    static void backTracking(List<String> res, String str, int index, StringBuilder sb){
        if (sb.length() != 0) res.add(sb.toString());

        for (int i = index; i < str.length(); i++){
            sb.append(str.charAt(i));
            backTracking(res, str, i + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(subString("ABC"));
    }
}
