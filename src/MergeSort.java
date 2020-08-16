import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] data = {1,2,58,23,1,2,58,23,5};
        int[] copy = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            copy[i] = data[i];
        }
        new MergeSort().mergeSort(data, copy, 0, data.length - 1);
        System.out.println(Arrays.toString(copy));
    }

    /***
     * 最后从copy中返回排序后的结果
     * @param data  需要排序的数组
     * @param copy  data的复制
     * @param left
     * @param right
     */
    public void mergeSort(int[] data, int[] copy, int left, int right){
        if(left >= right){
            return;
        }
        int mid = left + ((right - left) >>> 1);
        mergeSort(copy, data, left, mid);
        mergeSort(copy, data, mid + 1, right);

        int pos = right;
        int i = mid;
        int j = right;
        while (i >= left && j >= mid + 1){
            if(data[j] >= data[i]){
                copy[pos] = data[j];
                --j;
            }else {
                copy[pos] = data[i];
                --i;
            }
            --pos;
        }
        while (i >= left){
            copy[pos] = data[i];
            --i;
            --pos;
        }
        while (j >= mid + 1){
            copy[pos] = data[j];
            --j;
            --pos;
        }
    }
}
