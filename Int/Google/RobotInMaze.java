/*
 * this traverse maze with fixed size/known boundary and find all paths
*/
public class Solution {
	public static void main(String[] args) {
		int[][] maze = {
			{1, 0, 1, 0},
			{1, 1, 1, 1},
			{1, 0, 1, 0},
			{1, 1, 1, 1}
		};

		Robot bot = new Robot();
		System.out.println(bot.findPath(maze));
	}
}

class Robot {
	private Map<Integer, String> move;

	public Robot() {
		move = new HashMap<>();
		move.put(0, "UP ");
		move.put(1, "DOWN ");
		move.put(2, "LEFT ");
		move.put(3, "RIGHT ");
	}

	// find path from (0, 0) to (M-1, N-1)
	public List<String> findPath(int[][] map) {
		int m = map.length;
		int n = map[0].length;
		boolean[][] visit = new boolean[m][n];
		// record working route
		List<String> path = new ArrayList<>();
		// store cur trace
		String cur = "";

		dfs(map, visit, 0, 0, path, cur);

		return path;
	}

	private void dfs(int[][] map, boolean[][] visit, int x, int y, List<String> res, String cur) {
		visit[x][y] = true;
		int m = map.length;
		int n = map[0].length;

		if (x == m - 1 && y == n - 1) {
			// found route (0, 0) -> (m - 1, n - 1)
			res.add(cur);
		}

		// up - down - left - right
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};

		for (int i = 0; i < 4; ++i) {
			int a = x + dx[i];
			int b = y + dy[i];
			// try to move towards neighbors
			if (checkValid(map, a, b) && !visit[a][b]) {
				String dir = move.get(i);
				dfs(map, visit, a, b, res, cur + dir);
			}
		}
		// no solution from (x, y) => backtrack
		visit[x][y] = false;
	}
    // check target grid is valid for visit
	private boolean checkValid(int[][] map, int x, int y) {
		int m = map.length;
		int n = map[0].length;

		return (x >= 0 && y >= 0 && x < m && y < n && map[x][y] == 1);
	}
}
