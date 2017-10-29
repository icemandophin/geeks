package Amazon;
import java.util.*;

public class movies {
   public static void main(String[] args) {
	   Scanner in = new Scanner(System.in);
       // build min heap for final result
	   PriorityQueue<Movie> queue = new PriorityQueue<Movie>(new Comparator<Movie>() {
		   @Override
		   public int compare(Movie m1, Movie m2) {
			   return new Float(m2.getRating()).compareTo(m1.getRating());
		   }
	   });
   }
   // implemented by system
   public class Movie{
	   public List<Movie> getSimilarMovies() {
		   return null;
	   }
	   public Float getRating() {
		   return null;
	   }
   }
   private static void bfsSearchMovieNetwork(Movie movie, PriorityQueue<Movie> res) {
       // one queue for BFS
       Queue que = new LinkedList<Movie>();
       que.offer(movie);
       while(!que.isEmpty()) {
           Movie cur = que.poll();
           if(res.size() < k) {
               // direct insert
               queue.offer(m);
            } else if (queue.peek().ratings < m.ratings) {
               // replace with bigger one
               res.poll();
               res.offer(m);
            }
            // enqueue similiar items of cur for BFS
            for (Movie m : cur.getSimilarMovies()) {
                que.offer(m);
            }
       }
   }
}
