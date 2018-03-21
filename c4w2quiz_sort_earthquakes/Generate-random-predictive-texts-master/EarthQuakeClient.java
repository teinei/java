import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe:quakeData){
            if(qe.getMagnitude()>magMin){
                answer.add(qe);
            }
        }

        return answer;
    }
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double minDepth,double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe:quakeData){
            if(qe.getDepth()>minDepth&&qe.getDepth()<maxDepth){
                answer.add(qe);
            }
        }

        return answer;
    }
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe:quakeData){
            Location qeloc = qe.getLocation();
            if(qeloc.distanceTo(from)<distMax){
                answer.add(qe);
            }
        }

        return answer;
    }
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String where,
    String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe:quakeData){
            Location qeloc = qe.getLocation();
            String title = qe.getInfo();
            
            if(where.equals("start")){
                String startPhrase = title.substring(0,phrase.length());
                if(startPhrase.equals(phrase)){
                    answer.add(qe);
                }
            }
            if(where.equals("end")){
                String endPhrase = title.substring(title.length()-phrase.length(),title.length());
                if(endPhrase.equals(phrase)){
                    answer.add(qe);
                }
            }
            if(where.equals("any")){
                if(title.indexOf(phrase)!=-1){
                    answer.add(qe);
                }
            }
        }

        return answer;
    }
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> bigquakes = filterByMagnitude(list,5.0);
        for (QuakeEntry qe : bigquakes) {
            System.out.println(qe);
        }
        System.out.println("Found "+bigquakes.size()+" quakes that match that criteria.");

    }
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> Depth = filterByDepth(list,-4000.0, -2000.0);
        for (QuakeEntry qe : Depth) {
            System.out.println(qe);
        }
        System.out.println("Found "+Depth.size()+" quakes that match that criteria.");

    }
    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
       
        // This location is Durham, NC
        Location city = new Location(35.988, -78.907);
        // This location is Bridgeport, CA
        // Location city =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list,1000000,city);
        for (QuakeEntry qe : close) {
            System.out.println(qe);
        }
        System.out.println("Found "+close.size()+" quakes that match that criteria.");
        // This location is Bridgeport, CA
        // Location city =  new Location(38.17, -118.82);

        // TODO
    }
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> Depth = filterByPhrase(list,"any", "Can");
        for (QuakeEntry qe : Depth) {
            System.out.println(qe);
        }
        System.out.println("Found "+Depth.size()+" quakes that match that criteria.");

    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
