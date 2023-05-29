package com.pembo.store.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReviewDto {
    private float rating;
    private String comment;
    private String dateCreated;
    private  static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ReviewDto() {
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateFormat.format(dateCreated);
    }
}
