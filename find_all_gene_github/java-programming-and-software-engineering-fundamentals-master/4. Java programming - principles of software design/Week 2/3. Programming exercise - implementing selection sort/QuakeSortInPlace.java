
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author Brienna Herold
 * @version Dec. 21, 2016
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        sortByMagnitudeWithCheck(list);
        System.out.println("EarthQuakes in sorted order: ");
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
    
    /**
     * Returns the index position of the QuakeEntry with the largest depth considering 
     * only those QuakeEntrys from position from to the end of the ArrayList.
     */
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        // Initialize tracker variable to first quake entry
        int maxIndex = from;
        // For each quake from 'from' to the end of ArrayList
        for (int i = from + 1; i < quakeData.size(); i++) {
            // Get current quake depth
            double depth = quakeData.get(i).getDepth();
            double maxDepth = quakeData.get(maxIndex).getDepth();
            // Compare quake depths
            if (depth > maxDepth) {
                maxIndex = i; 
            }
        }
        return maxIndex;
    }
    
    /**
     * Sorts the QuakeEntrys in the ArrayList by depth using the selection sort 
     * algorithm, from largest depth to smallest depth.
     */
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        // Loop enough times to cover input ArrayList
        for (int i = 0; i < in.size(); i++) {
            // Find index of quake with largest depth in input ArrayList
            int maxIndex = getLargestDepth(in, i);
            // Get values of that quake and the current quake
            QuakeEntry maxQuake = in.get(maxIndex);
            QuakeEntry currQuake = in.get(i);
            // Swap both quakes' values
            in.set(i, maxQuake);
            in.set(maxIndex, currQuake);
        }
    }
    
    /**
     * Makes one pass of bubble sort on the ArrayList, sorting by smallest to 
     * largest magnitude. One pass over the ArrayList compares adjacent elements 
     * and swaps them if they are out of order.
     * @param quakeData is the ArrayList to sort
     * @param numSorted is the number of times this method has already been called
     * on the ArrayList and thus also represents the number of elements that are 
     * guaranteed to already be where they belong when the sorting is complete
     */
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        // For each QuakeEntry in quakeData
        for (int i = 0; i < quakeData.size() - 1 - numSorted; i++) {
            // Save current and next QuakeEntrys
            QuakeEntry currQe = quakeData.get(i);
            QuakeEntry nextQe = quakeData.get(i + 1);
            // Swap QuakeEntrys if their magnitudes are out of order
            if (currQe.getMagnitude() > nextQe.getMagnitude()) {
                quakeData.set(i + 1, currQe);
                quakeData.set(i, nextQe);
            }
        }
    }
    
    /**
     * Calls onePassBubbleSort N – 1 times to sort the elements in an ArrayList 
     * of N length.
     */
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for (QuakeEntry qe : in) {
            System.out.println(qe);
        }
        
        for (int i = 0; i < in.size() - 1; i++) {
            onePassBubbleSort(in, i);
           
            System.out.println("Printing quakes after pass " + i);
            for (QuakeEntry qe : in) {
                System.out.println(qe);
            }
        }
    }
    
    /**
     * Returns true if ArrayList is sorted in order by magnitude 
     * from smallest to largest. Otherwise returns false.
     */
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        // Loop through ArrayList and check if adjacent quakes are out of order
        for (int i = 0; i < quakes.size() - 1; i++) {
            QuakeEntry currQe = quakes.get(i);
            QuakeEntry nextQe = quakes.get(i + 1);
            if (currQe.getMagnitude() > nextQe.getMagnitude()) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Similar to sortByMagnitudeWithBubbleSort, but stops early 
     * if the ArrayList is already sorted.
     */
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size() - 1; i++) {
            onePassBubbleSort(in, i);
            // Stop early if the ArrayList is already sorted
            if (checkInSortedOrder(in)) {
                System.out.println("Sorted with " + (i + 1) + " passes");
                break;
            }
        }
    }
    
    /**
     * Similar to sortByMagnitude, but stops early 
     * if the ArrayList is already sorted.
     */
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            if (checkInSortedOrder(in)) {
                System.out.println("Sorted with " + (i + 1) + " passes");
                break;
            }
        }
    }
}
