/*
iterative approach:
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

/*
DFS approach:
image branches of the tree
1-10-100-1000-...
 -11-110-1100-...
 -12-120-1200-...
 => always try to add small digit first
 then add same digit with trailing 0s
 then add digit + 1
*/
public class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();
        dfs(1, n, result);

        return result;
    }

    private void dfs(long curr, int n, List<Integer> result) {
        // break condition: cur go beyond size boundary n
        if (curr > n) {
            return;
        }
        // add cur number first
        result.add((int) curr);
        // cur*10 add one additional 0 behind cur
        // if cur*10 < n should add after cur
        dfs(curr * 10, n, result);
        // try to add next closet num => cur + 1
        // notice to skip last digit == 9 scenario
        if (curr % 10 != 9) {
            dfs(curr + 1, n, result);
        }
    }
}
