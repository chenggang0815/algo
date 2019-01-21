//冒泡排序，选择排序，插入排序。
class Solution {

    //冒泡排序
    public static void bubbleSort(int[] arr){

        int length = arr.length;

        for(int i = 0;i<length-1;i++){
            for(int j = 0;j<length-i-1;j++){
                if (arr[j] >= arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }


    public static void main(String[] args) {
        
        int[] array = new int[] {4,2,1,4,1,1,1,100};
        bubbleSort(array);
        for(int i=0;i<array.length;i++){
            System.out.println(array[i]+"");
        }
    }

}




