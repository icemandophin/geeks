class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] rec = new int[n + 1];
        int[] candidate1 = new int[2];
        int[] candidate2 = new int[2];

        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];

            if (rec[j] != 0) {
                candidate1[0] = rec[j];
                candidate1[1] = j;
                candidate2 = edge;
                break;
            }

            rec[j] = i;
        }


        for (int i = 1; i <= n; i++) {
            rec[i] = i;
        }

        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];

            if (i != candidate2[0] || j != candidate2[1]) {
                int parent1 = find(rec, edge[0]);
                int parent2 = find(rec, edge[1]);

                if (parent1 == parent2) {
                    if (candidate1[0] == 0) {
                        return edge;
                    } else {
                        return candidate1;
                    }
                }

                rec[parent2] = parent1;
            }
        }

        return candidate2;
    }

    private int find(int[] rec, int child) {
        int parent = child;

        while (rec[parent] != parent) {
            parent = rec[parent];
        }

        rec[child] = parent;
        return parent;
    }
}
