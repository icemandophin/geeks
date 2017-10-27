package Amazon;

import java.util.*;

public class cutTree {
	public static void main(String[] args){
		int n = 2, m = 4;
		List<List<Integer>> fields = new ArrayList<>();
		fields.add(Arrays.asList(1,1,0,2));
		fields.add(Arrays.asList(3,1,1,1));
//	
//		
//		PriorityQueue<int[]> treelist = new PriorityQueue<int[]>(
//				new Comparator<int[]>(){
//					@Override
//					public int compare(int[] tree1, int[] tree2) {
//						// TODO Auto-generated method stub
//						return fields.get(tree1[0]).get(tree1[1]) - fields.get(tree2[0]).get(tree2[1]);
//					}
//				});
//		int[][] map = new int[n][m];
//        
//		for (int i = 0;i<n;i++){
//			for (int j = 0;j< m;j++){
//				map[i][j] = fields.get(i).get(j);
//				if (fields.get(i).get(j) > 1){
//					treelist.add(new int[]{i,j});
//				}
//			}
//		}
//		while(!treelist.isEmpty()){
//
//			System.out.println(treelist.peek()[0]+" "+treelist.peek()[1]);
//			treelist.poll();
//		}
       System.out.println(flatFields(n,m,fields));
	}
	public static int flatFields (int numRows, int numColumns, List<List<Integer>> fields) {
		PriorityQueue<int[]> treelist = new PriorityQueue<int[]>(
				new Comparator<int[]>(){
					@Override
					public int compare(int[] tree1, int[] tree2) {
						// TODO Auto-generated method stub
						return fields.get(tree1[0]).get(tree1[1]) - fields.get(tree2[0]).get(tree2[1]);
					}
				});
		int[][] map = new int[numRows][numColumns];
        
		for (int i = 0;i<numRows;i++){
			for (int j = 0;j< numColumns;j++){
				map[i][j] = fields.get(i).get(j);
				if (fields.get(i).get(j) > 1){
					treelist.add(new int[]{i,j});
				}
			}
		}
		int sum = 0;
		int[] start = new int[]{0,0};
		int[] end = new int[2];
        int res = 0;
		while(!treelist.isEmpty()){
			end = treelist.remove();
			int tH = map[end[0]][end[1]];
			map[end[0]][end[1]] = 1;
			res = shortestBFS(map,start,end);

			if (res == -1){
				return -1;
			}
			sum += (res + tH);

			start = end;
			
		}
		return sum;
	}
///**********************************BFS*******************************
    public static int shortestBFS(int[][] maze ,int[] start ,int[] dest){
    	int m = maze.length, n = maze[0].length;
    	int[][] path = new int[m][n];
    	int[] dirs = {-1,0,1,0,-1};
    	Queue<int[]> q = new LinkedList<>();
    	q.offer(start);
    	while(!q.isEmpty()){
    		int[] curr = q.poll();
    		for(int d = 0;d < 4;d++){
    			int x = curr[0], y = curr[1], count = 0;
    			while(x+dirs[d] >= 0 && x+dirs[d] < m && y+dirs[d+1] >= 0 && y+dirs[d+1] < n && 
    					maze[x+dirs[d]][y+dirs[d+1]] == 1){
    				x += dirs[d];
    				y += dirs[d+1];
    				count++;
    			}
    			if ((x != start[0] || y!= start[1])&&
    					(path[x][y] == 0 || path[x][y] >path[curr[0]][curr[1]]+count)){
    				path[x][y] = path[curr[0]][curr[1]]+count;
    				q.offer(new int[]{x,y});
    			}
    		}
    		
    				
    		}
    	return path[dest[0]][dest[1]] == 0?
    			-1 : path[dest[0]][dest[1]];
    			
    }	
	
	
	 public static int shortpath(int[][] maze,int[] start,int[] dest){
	    	int[][] distance = new int[maze.length][maze[0].length];
	    	for (int[] row: distance){
	    		Arrays.fill(row, Integer.MAX_VALUE);
	    	}
	    	distance[start[0]][start[1]] = 0;
	    	int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};
	    	Queue <int[]> q = new LinkedList<>();
	    	q.add(start);
	    	while(!q.isEmpty()){
	    		int[] buff = q.remove();
	    		for (int[] dir:dirs){
	    			int x = buff[0] + dir[0];
	    			int y = buff[1] + dir[1];
	    			int sum = 0;
	    			while(x>=0 && y>=0 && x<maze.length && y < maze[0].length && maze[x][y] == 1){
	    				x +=dir[0];
	    				y +=dir[1];
	    				sum++;
	    			}
	    			if (distance[buff[0]][buff[1]] + sum < distance[x-dir[0]][y-dir[1]]){
	    				distance[x-dir[0]][y-dir[1]] = distance[buff[0]][buff[1]] + sum;
	    				q.add(new int[] {x-dir[0],y-dir[1]});
	    			}
	    		}
	    	}
	    	return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1:distance[dest[0]][dest[1]];
	    }
}
