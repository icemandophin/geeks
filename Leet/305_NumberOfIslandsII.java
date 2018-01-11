/*
Union-Find approach:
save each node and its father's id in hashmap
*/
public class Solution {
    /**
     * @param n an integer
     * @param m an integer
     * @param operators an array of point
     * @return an integer array
     */
    int convertToId(int x, int y, int m){
        return x * m + y;
    }
    class UnionFind{
        private int[] father;
        UnionFind(int n, int m){
            father = new int[n * m];
            for(int i = 0 ; i < n; i++) {
                for(int j = 0 ; j < m; j++) {
                    // init all nodes as land of itself
                    int id = convertToId(i, j, m);
                    father[id] = id;
                }
            }
        }
        int compressed_find(int x){
            if (father[x] == x) {
                return x;
            }
            return father[x] = compressed_find(father[x]);
        }

        void union(int x, int y){
            int fa_x = compressed_find(x);
            int fa_y = compressed_find(y);
            if(fa_x != fa_y) {
                father[fa_x] = fa_y;
            }
        }
    }

    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        // Write your code here
        List<Integer> ans = new ArrayList<Integer>();
        if(operators == null) {
            return ans;
        }

        int []dx = {0,-1, 0, 1};
        int []dy = {1, 0, -1, 0};
        int [][]island = new int[n][m];

        // creat union-find
        UnionFind uf = new UnionFind(n, m);
        int count = 0;
        // traverse and add land
        for(int i = 0; i < operators.length; i++) {
            int x = operators[i].x;
            int y = operators[i].y;
            if(island[x][y] != 1) {
                count ++;
                island[x][y]  = 1;
                int id = convertToId(x, y, m);
                for(int j = 0 ; j < 4; j++) {
                    // search 4 directions
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    // if neighbor is land, compress find and union root of both islands
                    if(0 <= nx && nx < n && 0 <= ny && ny < m && island[nx][ny] == 1) {
                        int nid = convertToId(nx, ny, m);
                        int fa = uf.compressed_find(id);
                        int nfa = uf.compressed_find(nid);
                        // if island root is the same, just add another connect
                        // no need to union and update count
                        if(fa != nfa) {
                            count--;
                            uf.union(id, nid);
                        }
                    }
                }
            }
            ans.add(count);
        }
        return ans;
    }
}
