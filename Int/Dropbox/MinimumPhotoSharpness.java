/*
Given 2-d array of "sharpness" values, fine the path from left to right which
has the highest minimum sharpness:
route from left->right, each time can go from (i, j) to (i, j+1)/(i-1, j+1)/(i+1, j+1)
eg:
.5 .7 .2
.7 .5 .8
.9 .1 .5
path: .7->.7->.8 => return 7
since 7 as the min value of 7-7-8 path, is the biggest among all paths
*/

/*
1. keep recording max min value of each route in dp[]:
for each item in the col:
dp[i][j] = Min{Max{dp[i][j-1], dp[i-1][j-1], dp[i+1][j-1]}, a[i][j]}
2. after last col find max value of dp[]
*/
public class Solution{
    public static void main(String[] args) {
        int[][] photo = {
            {5, 7, 2},
            {7, 5, 8},
            {9, 1, 5}
        };

        System.out.println(MinimumPhotoSharpness(photo));
    }

    public static int MinimumPhotoSharpness(int[][] a) {
        if (a == null || a.length == 0) {
            return -1;
        }
        int res = Integer.MIN_VALUE;
        int i, j;
        int m = a.length;
        int n = a[0].length;
        // rolling array for size optimization
        int[][] dp = new int[m][n];
        // init dp with 1st col
        for (i = 0; i < m; ++i) {
            dp[i][0] = a[i][0];
        }
        // scan each col and find min
        for (j = 1; j < n; ++j) {
            for (i = 0; i < m; ++i) {
                int cur = dp[i][j - 1];
                if (i != 0) {
                    cur = Math.max(cur, dp[i - 1][j - 1]);
                }
                if (i != m - 1) {
                    cur = Math.max(cur, dp[i + 1][j - 1]);
                }
                // check whether max prev dp or a[i][j]
                // is the min value in current route
                dp[i][j] = Math.min(cur, a[i][j]);
            }
        }
        // dp contains min value, find max one
        for (i = 0; i < m; ++i) {
            res = Math.max(res, dp[i][n - 1]);
        }
        return res;
    }
}

/*
follow up:
如果图片是1million*1million的怎么办，整张图片读不进内存，我答说dp结构可以改成一维的，
然后每次只读一列。小哥说每次读一列的第一个字符非常耗时，因为要不断的跳读指针，
然后我说可以对这个矩阵转置写到另外一个文件里，然后每次想做这个函数就对这个新文件操作就好了，
这样就能按行读。小哥就问我怎么转置再存到另外一个文件里，我说根据内存大小可以多读几列。
然后小哥就说还可以再优化，他说这有一个balance，读行输出列，写文件就很耗时，读列输出行，
读文件就很耗时（主要问题是 写指针或读指针跳转到下一行 所带来的时间消耗），
其实听到这里我就应该有反应了，但当时还是傻傻的想。最后结果是每次根据内存大小读一个接近正方形的矩形，
将他们写到新文件，再读下一块矩形。这样的话，读写指针跳转次数就最小了.
*/

/*
optimize space with rolling array
*/
public class solution{
    public int MinimumPhotoSharpness(int[][] a) {
        if (a == null || a.length == 0) {
            return -1;
        }
        int res = Integer.MIN_VALUE;
        int i, j;
        int m = a.length;
        int n = a[0].length;
        // rolling array for size optimization
        int[][] dp = new int[m][2];
        // init dp with 1st col
        for (i = 0; i < m; ++i) {
            dp[i][0] = a[i][0];
        }
        // scan each col and find min
        for (j = 1; j < n; ++j) {
            for (i = 0; i < m; ++i) {

                int cur = dp[i][(j - 1) % 2];
                if (i != 0) {
                    cur = Math.max(cur, dp[i - 1][(j - 1) % 2]);
                }
                if (i != m - 1) {
                    cur = Math.max(cur, dp[i + 1][(j - 1) % 2]);
                }
                // check whether max prev dp or a[i][j]
                // is the min value in current route
                dp[i][j % 2] = Math.min(cur, a[i][j]);
            }
        }
        // dp contains min value, find max one
        for (i = 0; i < m; ++i) {
            res = Math.max(res, dp[i][(j - 1) % 2]);
        }
        return res;
    }
}
