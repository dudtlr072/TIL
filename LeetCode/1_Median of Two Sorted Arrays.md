## Problem

There are two sorted arrays **nums1** and **nums2** of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

**Example 1:**

> nums1 = [1, 3]
>
> nums2 = [2]
>
> The median is 2.0

**Example 2:**

> nums1 = [1, 2]
>
> nums2 = [3, 4]
>
> The median is (2 + 3) / 2 = 2.5



## Answer

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;

        if(m == 0 && n == 0) {
            return 0;
        } else if(n == 0) {
            return findMiddleValue(nums1);
        } else if(m == 0) {
            return findMiddleValue(nums2);
        }

        int[] sumArray = new int[m + n];
        int sumIdx = 0;
        int num1Idx = 0;
        int num2Idx = 0;
        int num1 = 0;
        int num2 = 0;

        while(num1Idx < m && num2Idx < n) {
            num1 = nums1[num1Idx];
            num2 = nums2[num2Idx];
            if(num1 <= num2) {
                sumArray[sumIdx++] = num1;
                num1Idx++;
            } else {
                sumArray[sumIdx++] = num2;
                num2Idx++;
            }
        }

        if(num1Idx == m) {
            for(int i = num2Idx; i < n; i++) {
                sumArray[sumIdx++] = nums2[i];
            }
        } else {
            for(int i = num1Idx; i < m; i++) {
                sumArray[sumIdx++] = nums1[i];
            }
        }

        return findMiddleValue(sumArray);
    }

    public double findMiddleValue(int[] array) {
        int length = array.length;

        if(length % 2 == 0) {
            int n = length / 2;
            return (array[n-1] + array[n]) / 2.0;
        } else {
            return (double)array[length / 2];
        }
    }
}
```

