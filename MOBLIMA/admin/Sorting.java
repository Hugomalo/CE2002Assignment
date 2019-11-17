package MOBLIMA.admin;

import java.util.ArrayList;

/**
 * Sorting class for sorting the list of movies by either
 * ticket sales or overall rating.
 * @author CE2002 SE3 Group 4
 */

class Sorting {
    /**
     * Sorts the list of movies by overall rating.
     * @param list List of movies
     */
    static void ratingSort(ArrayList<Movie> list){
        int min;
        Movie temp;
        for (int index = 0; index < list.size()-1; index++){
            min = index;
            for (int scan = index+1; scan < list.size(); scan++)
                if (list.get(scan).compareToRating(list.get(min)) < 0)
                    min = scan;
            // Swap the values
            temp = list.get(min);
            list.set(min, list.get(index));
            list.set(index, temp);
        }
    }
	/**
	 * Sorts the list of movies by ticket sales.
	 * @param list List of movies
	 */
    static void saleSort(ArrayList<Movie> list){
        int min;
        Movie temp;
        for (int index = 0; index < list.size()-1; index++){
            min = index;
            for (int scan = index+1; scan < list.size(); scan++)
                if (list.get(scan).compareToSale(list.get(min)) < 0)
                    min = scan;
            // Swap the values
            temp = list.get(min);
            list.set(min, list.get(index));
            list.set(index, temp);
        }
    }
}
