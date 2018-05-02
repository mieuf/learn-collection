package mieuf.org.base.dell.sort;

import mieuf.org.base.dell.SingletonDemo;

/**
 * Created by Administrator on 2018/5/2.
 * TODO -----
 */
public class InsertSort {

    /**
     * 直接插入排序
     * 时间复杂度O(n^2)
     * @param r
     */
    public void insertSort (int[] r) {
        int j,temp;
        for (int i = 1; i<r.length; i++) {
            temp = r[i];
            j = i - 1;
            while (j >= 0 && temp < r[j]) {
                r[j+1] = r[j];
                j--;
            }
            r[j+1] = temp;
        }
        switch (SingletonDemo.instance) {

        }

    }

    /**
     * 折半查找插入位置,返回应该移动的位置。
     * @param r
     * @param node
     * @return
     */
    public int insertHalfSort(int[] r,int node) {
        int i,j,mid;
        int high = r.length;
        int low = 0;
        while (high > low){
            mid = (low + high) / 2;
            if (node > r[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (r.length%2>0)
            return low;
        else
            return low + 1;
    }

}
