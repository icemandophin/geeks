package Amazon;
import java.util.*;
public class movies {
   public static void main(String[] args){
	   Scanner in = new Scanner(System.in);
	   PriorityQueue<Movie> queue = new PriorityQueue<Movie>(new Comparator<Movie>(){
		   @Override
		   public int compare(Movie m1, Movie m2){
			   return new Float(m2.getRating()).compareTo(m1.getRating());
		   }
	   });
   }
   public class Movie{
	   public List<Movie> getSimilarMovies(){
		   return null;
	   }
	   public Float getRating(){
		   return null;
	   }
   }
   private static void bfsSearchMovies(Movie movie, PriorityQueue<Movie> queue){
	   for (Movie M:movie.getSimilarMovies()){
		   if (!queue.contains(M)){
			   queue.offer(M);
			   
			   bfsSearchMovies(M,queue);
		   }
	   }
   }

}
