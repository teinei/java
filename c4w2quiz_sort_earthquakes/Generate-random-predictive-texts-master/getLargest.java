
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class getLargest {
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, 
    int howMany) {
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        for(int j=0; j < howMany; j++) {
            int maxIndex = indexOfLargest(copy);
            ret.add(copy.get(maxIndex));
            copy.remove(maxIndex);
        }
        
        return ret;
    }
    public int indexOfLargest(ArrayList<QuakeEntry> data){
            int maxIndex = 0;
            for(int k=1; k < data.size(); k++){
                QuakeEntry quake = data.get(k);
                double mag = quake.getMagnitude();
                if (mag > 
                    data.get(maxIndex).getMagnitude()){
                    maxIndex = k;   
                }
            }
            return maxIndex;
        }
    
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom ";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        //Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> largest = getLargest(list,50);
        for(int k=0; k < largest.size(); k++){
            QuakeEntry entry = largest.get(k);
            //double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.println(entry);
        }
        System.out.println("number found: "+largest.size());
    }
}
