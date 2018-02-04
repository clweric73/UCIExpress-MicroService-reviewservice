package edu.javacd.reviewservice;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@Path("/reviews")
public class ReviewService {
	@EJB
	ReviewEJB ejb;
	@Inject
	@ConfigProperty(name = "movieservice.url")
	String movieserviceURL;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Review> listReviews() {
		return ejb.listReviews();
	}
	
	private String getMovieTitle(int movieId) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(movieserviceURL).path("/movie/" + movieId);
		HashMap<String, String> map = target.request(MediaType.APPLICATION_JSON).get(HashMap.class);
		return map.get("name");
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	@Path("/{reviewId}")
	public HashMap getReview(@PathParam("reviewId") int reviewId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		Review review = ejb.getReview(reviewId);
		int movieId = review.getMovieId();
		String movieTitle = getMovieTitle(movieId);
		
		map.put("id", review.getId());
		map.put("title", movieTitle);
		map.put("rating", review.getRating());
		map.put("description", review.getDescription());
		
		return map;
	}
}
