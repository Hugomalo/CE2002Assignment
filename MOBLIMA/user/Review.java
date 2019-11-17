package MOBLIMA.user;

import java.io.Serializable;

public class Review implements Serializable {
    private static final long serialVersionUID = 1L;
    int rating;
    String review;

    public int getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }
}
