import java.util.Arrays;

class Solution {
    
    //快速排序

    private static void quickSort(int[] arr, int low, int high) {
        if( low > high) {
            return;
        }
        int i = low;
        int j = high;

        int key = arr[low];
        while (i<j){

            while (key<=arr[j] && i<j){
                j--;
            }

            while (key>=arr[i] && i<j){
                i++;
            }

            if(i<j){
                int temp =arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        arr[low] = arr[i];
        arr[i] = key;

        quickSort(arr, low, i-1 );
        quickSort(arr, i+1, high);
    }

    public static void main(String[] args) {

        //int[] array = new int[] {40,2,1,311,2,1};
        int[] array = {10,7,2,4,7,62,3,4,2,1,8,9,19};
        quickSort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
        

    }

}




