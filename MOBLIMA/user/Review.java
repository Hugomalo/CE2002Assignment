package MOBLIMA.user;

import java.io.Serializable;

/**
 * Review class for getting the rating and review of a movie.
 * @author CE2002 SE3 Group 4
 */

public class Review implements Serializable {
    private static final long serialVersionUID = 1L;
    int rating;
    String review;

    /**
     * Gets the rating of the movie.
     * @return Rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Gets the review of the movie.
     * @return Review
     */
    public String getReview() {
        return review;
    }
}
