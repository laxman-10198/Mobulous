
package com.example.myapplication.Model.Zemato;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRating  {

    @SerializedName("aggregate_rating")
    @Expose
    private float aggregateRating;
    @SerializedName("rating_text")
    @Expose
    private String ratingText;
    @SerializedName("rating_color")
    @Expose
    private String ratingColor;
    @SerializedName("rating_obj")
    @Expose
    private RatingObj ratingObj;
    @SerializedName("votes")
    @Expose
    private Integer votes;

    public float getAggregateRating() {
        return aggregateRating;
    }

    public void setAggregateRating(float aggregateRating) {
        this.aggregateRating = aggregateRating;
    }

    public String getRatingText() {
        return ratingText;
    }

    public void setRatingText(String ratingText) {
        this.ratingText = ratingText;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public RatingObj getRatingObj() {
        return ratingObj;
    }

    public void setRatingObj(RatingObj ratingObj) {
        this.ratingObj = ratingObj;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}
