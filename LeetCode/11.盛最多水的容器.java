/*
 * @lc app=leetcode.cn id=11 lang=java
 *
 * [11] 盛最多水的容器
 */

// @lc code=start
class Solution {
    public int maxArea(int[] height) {
        int maxArea = Math.min(height[0], height[height.length-1]) * (height.length -1);
        for (int i = 0, j = height.length - 1; i < j;) {
            if (height[i]<height[j]) {
                i++;
                maxArea = Math.max(maxArea, (j-i) * Math.min(height[i],height[j]));
            } else {
                j--;
                maxArea = Math.max(maxArea, (j-i) * Math.min(height[i],height[j]));
            }
        }
        return maxArea;
    }
}


// @lc code=end

