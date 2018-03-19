
/**
 * @author Miguel Hernandez
 * @version nil
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
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "data/earthQuakeDataDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");
        //sortByMagnitude(list);
        //sortByLargestDepth(list, 50);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        /*
        System.out.println("EarthQuakes in sorted order:");
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }
        */
        
    }
    
    public void printList(ArrayList<QuakeEntry> printme) {
        for (int i = 0; i < printme.size(); i++) {
            System.out.println(printme.get(i));
        }
        System.out.println("\n");
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
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int maxIdx = from;
        for (int i=from+1; i< quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in, int passes) {
        for (int i=0; i < passes; i++) {
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
        }
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i = 1; i < quakeData.size() - numSorted; i++) {
            QuakeEntry currEntry = quakeData.get(i-1);
            QuakeEntry nextEntry = quakeData.get(i);
            double currMagn = currEntry.getMagnitude();
            double nextMagn = nextEntry.getMagnitude();
            if (!(currMagn < nextMagn)) {
                quakeData.set(i-1, nextEntry);
                quakeData.set(i, currEntry);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        int N = in.size();
        System.out.println("Printing quakes before the pass...");
        printList(in);
        for (int i = 0; i < N-1; i++) {
            onePassBubbleSort(in, i);
            System.out.println("Printing quakes after pass " + i + "...");
            printList(in);
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        int numTrue = 0;
        for (int i = 1; i < quakes.size(); i++) {
            QuakeEntry currEntry = quakes.get(i-1);
            QuakeEntry nextEntry = quakes.get(i);
            double currMagn = currEntry.getMagnitude();
            double nextMagn = nextEntry.getMagnitude();
            if (currMagn < nextMagn) {
                numTrue++;
            }
        }
        if (numTrue == quakes.size()-1) {
            return true;
        }
        return false;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int N = in.size();
        //System.out.println("Printing quakes before the pass...");
        //printList(in);
        int numSorted = 0;
        for (int i = 0; i < N-1; i++) {
            if (!checkInSortedOrder(in)) {
                onePassBubbleSort(in, i);
                numSorted += 1;
                //System.out.println("Printing quakes after pass " + i + "...");
                //printList(in);
            } else {
                break;
            }
        }
        System.out.println("Array list sorted after " + numSorted + " passes");
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int numPasses = 0;
        for (int i=0; i < in.size(); i++) {
            if (!checkInSortedOrder(in)) {
                int minIdx = getSmallestMagnitude(in,i);
                QuakeEntry qi = in.get(i);
                QuakeEntry qmin = in.get(minIdx);
                in.set(i,qmin);
                in.set(minIdx,qi);
                numPasses += 1;
            } else {
                break;
            }
        }
        System.out.println("Array List sorted after " + numPasses + " passes");
    }
}
