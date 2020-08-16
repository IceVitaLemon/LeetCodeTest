import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] nums = {1,5,7,1,4,6,32,32,6,8};
        new QuickSort().quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void quickSort(int[] nums){
        quickSort(nums, 0, nums.length - 1);
    }

    public void quickSort(int[] nums, int left, int right){
        if (nums == null || nums.length == 0){
            return;
        }
        int index = partition(nums, left, right);
        if (index > left){
            quickSort(nums, left, index - 1);
        }
        if (index < right){
            quickSort(nums, index + 1, right);
        }
    }

    public int partition(int[] nums, int left, int right){
        int small = left - 1;
        int randomPos = (int)(Math.random() * (right - left + 1) + left);
        swap(nums, randomPos, right);
        for (int i = left; i < right; i++) {
            if(nums[i] < nums[right]){
                ++small;
                if (i != small){
                    swap(nums, i, small);
                }
            }
        }
        ++small;
        swap(nums, small, right);
        return small;
    }

    public void swap(int[] nums, int posA, int posB){
        int tmp = nums[posA];
        nums[posA] = nums[posB];
        nums[posB] = tmp;
    }
}
