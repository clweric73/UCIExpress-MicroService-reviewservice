package edu.javacd.reviewservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ReviewEJB {
	@PersistenceContext
	EntityManager em;
	
	public List listReviews() {
		return em.createNamedQuery("Review.findAll").getResultList();
	}
	
	public List<Review> getMovieReviews(int movieId) {
		Query query = em.createNamedQuery("Review.findByMovieId");
		query.setParameter("id",  movieId);
		return (List<Review>)query.getResultList();
	}
	
	public  Review getReview(int id) {
		Query query = em.createNamedQuery("Review.findById");
		query.setParameter("id", id);
		
		Review review = null;
		try {
			review = (Review)query.getSingleResult();
		} catch (NoResultException ex) {
			review = null;
		}
		
		return review;
	}
}
