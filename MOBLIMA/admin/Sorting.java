package MOBLIMA.admin;

import java.util.ArrayList;

class Sorting {
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
