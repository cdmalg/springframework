package com.spring.leetcode;

import java.util.Arrays;

/**
 * 多数元素
 *
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 */
public class Code313 {

    //解题思路：由于出现次数大于n/2，则排序后下标为n/2的元素就是多数元素
    public static int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    //解题思路：既然出现次数大于n/2，那么多数元素的个数肯定比剩下的所有元素个数加起来还大，所以一遍循环便可以将多数元素找出
    public int majorityElementPro(int[] nums) {
        int count = 0;
        int tmp = nums[0];
        for(int i = 0;i<nums.length;i++){
            if(tmp ==nums[i])
                count++;
            else{
                count--;
                if(count==0)
                    tmp = nums[i+1];
            }
        }
        return tmp;
    }

    public static void main(String[] args) {
        int a[] = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(a));
    }
}
