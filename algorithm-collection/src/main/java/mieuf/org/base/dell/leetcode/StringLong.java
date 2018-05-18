package mieuf.org.base.dell.leetcode;

/**
 * Created by Administrator on 2018/5/9.
 * TODO -----
 */
public class StringLong {

    public int maxStringLong(String s) {
        int result = 0;
        char[] chars = s.toCharArray();
        int length = chars.length;
        int[] temp = new int[length];
        for(int i=0; i<length; i++){
            int j = i + 1;
            while (j<length){
                if(chars[i] == chars[j]){
                    temp[i] = j - i;
                    break;
                }
                j++;
            }
        }
        temp[length] = 1;
        int i = 0;
        while (i < length) {
            result = result <= temp[i] ? temp[i] : result;
            if (result > 2){
                for(int j=i+1; j<temp[i]; j++){
                    if(!(temp[j] >= temp[i] - j + i)){
                        break;
                    } else {
                        result = temp[i];
                    }
                }
            }
            i = i + temp[i];
        }


        return result;
    }
}
