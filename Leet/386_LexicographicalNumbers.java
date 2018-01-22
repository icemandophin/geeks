/*
refer: http://blog.csdn.net/mebiuw/article/details/52300529
*/
public class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<Integer>(n);
        if(n==0) return list;
        //用于暂存每一位的取值
        int[] digits = new int[255];
        //当前是在操作哪一位
        int len = 0;
        digits[0] = 1;
        int times = n;
        while(times-->1){
            //先添加当期那的数值
            int tmp=0;
            for(int i=0;i<=len;i++)
                tmp = tmp*10 + digits[i];
            list.add(tmp);
            //按照规则更新
            if(tmp*10 <= n){
                len++;
            } else{
                if(tmp == n){
                    digits[len] = 0;
                    len --;
                }
                while(digits[len]==9){
                    digits[len] = 0;
                    len --;
                }
                digits[len]++;
            }

        }
        int tmp=0;
        for(int i=0;i<=len;i++)
                tmp = tmp*10 + digits[i];
            list.add(tmp);
        return list;

    }
}
