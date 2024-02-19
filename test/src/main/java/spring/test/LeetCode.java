package spring.test;

import java.util.Arrays;

public class LeetCode {
    public static void main(String[] args) {
        int[] arr = {-1,1,0,-3,3};
        System.out.println(Arrays.toString(productExceptSelf(arr)));

    }

    public static int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        for (int left = 0, right = 0;left < nums.length && right < nums.length; right++) {
            if (right == 0) {
                result[left] = 1;
            }

            if (left == right) {
                continue;
            }

            result[left] *= nums[right];

            if (right == nums.length - 1) {
                left++;
                right = -1;
            }
        }
        return result;
    }
}
