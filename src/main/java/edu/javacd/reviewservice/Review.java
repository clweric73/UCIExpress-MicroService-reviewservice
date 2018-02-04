package edu.javacd.reviewservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table( name = "Review" )
@NamedQueries({
	@NamedQuery(name = "Reivew.findAll", query = "SELECT r FROM Review r"),
	@NamedQuery(name = "Review.findById", query = "SELECT r FROM Review r WHERE r.id = :id"),
	@NamedQuery(name = "Reivew.findByMovieId", query ="SELECT r FROM Review r WHERE r.movieId = :id")})
public class Review {
	@Id
	@Column(name = "ID")
	private int id;
	@NotNull
	@Column(name = "MOVIEID")
	private int movieId;
	@Size(min = 0, max = 5)
	@Column(name = "RATING")
	private int rating;
	@NotNull
	@Size(min = 0, max = 250)
	@Column(name = "DESCRIPTION")
	private String description;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getMovieId() {
		return movieId;
	}
	
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
