package edu.javacd.reviewservice;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/reviews")
public class ReviewService {
	@EJB
	ReviewEJB ejb;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Review> listReviews() {
		return ejb.listReviews();
	}
	
	private String getMOvieTitle(int movieId) {
		return "TDB";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	@Path("/{reviewId}")
	public HashMap getReview(@PathParam("reviewId") int reviewId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		Review review = ejb.getReview(reviewId);
		int movieId = review.getMovieId();
		String movieTitle = getMOvieTitle(movieId);
		
		map.put("id", review.getId());
		map.put("title", movieTitle);
		map.put("rating", review.getRating());
		map.put("description", review.getDescription());
		
		return map;
	}
}
