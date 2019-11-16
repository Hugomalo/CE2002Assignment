package MOBLIMA.user;

import java.io.Serializable;

public class Review implements Serializable {
    protected int rating;
    protected String review;

    public int getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }
}
