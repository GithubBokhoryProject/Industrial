package com.example.industrial;

public class UserRaw {
   private Float rating;
   private String comment;

    public UserRaw(Float rating,String comment) {
        this.rating = rating;
        this.comment=comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
