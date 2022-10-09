package com.byd.gzq.dsa;

import com.byd.gzq.utils.Exception.ServiceException;
import com.sun.istack.internal.Nullable;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Leonard
 * @date 2022/9/30 9:45
 */

public class CompleteBTree<T>  implements Serializable {

    // 存放数据的数组
    private T[] data;

    private int capacity;

    private final static int DEFAULTSIZE = 8;

    private final static int DEFAULTINCRE = 1;

    @SuppressWarnings({"unchecked"})
    public CompleteBTree() {
        data = (T[]) new Object[DEFAULTSIZE];
        capacity = DEFAULTINCRE;
    }

    /**
     * 向树中添加节点,可为空值
     * @param val  要添加的元素
     */
    private void put(@Nullable T val){
        if(data.length == capacity){
            capacityIncrement(DEFAULTINCRE);
        }
    }

    private void capacityIncrement(int increSize) {
        if(increSize<=0){
            throw new NegativeArraySizeException();
        }
        int newCapacity = capacity+(capacity>>1);
        if (newCapacity<=0 || newCapacity>Integer.MAX_VALUE-8){
            throw new OutOfMemoryError();
        }
    }

    /**
     * 取第idx个元素
     * @param idx 真实顺序,从0开始
     * @throws ServiceException
     */
    private T get(int idx) {
        if(idx<0 || idx>=data.length){
            throw new ServiceException("下标溢出",909);
        }

        return data[idx-1];
    }

    public void writeObject(){

    }



    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        a = Arrays.copyOf(a,7);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

}
