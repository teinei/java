
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class EfficientMarkovModel extends AbstractMarkovModel{

    private int N;
    private HashMap <String ,ArrayList<String>> map;
    public EfficientMarkovModel(int n) {
        myRandom = new Random();
        N = n;
        map = new HashMap<String ,ArrayList<String>>();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    public void buildMap(){
        //map = new HashMap<String ,ArrayList<String>>();
        //yes-this-is-a-thin-pretty-pink-thistle
        for(int i=0;i<myText.length();i++){
            if(i+N<=myText.length()){
                String key = myText.substring(i,i+N);
                if(!map.containsKey(key)){
                    map.put(key,new ArrayList<String>());
                }
                //ArrayList <String> followed = map.get(key);
                //System.out.println("The ArrayList before adding is "+followed);
                //if(i + key.length()+1<myText.length()){
                //   String next = myText.substring(i+key.length(),i+key.length()+1);
                //   followed.add(next);
                   //System.out.println("The ArrayList after adding is "+followed);
                //   map.put(key,followed);
                //}
                    
                    /*while(pos <myText.length()){
                        int start = myText.indexOf(key,pos);
                        System.out.println("The start is "+start);
                        if(start == -1){
                            break;
                        }
                        if(start + key.length()>myText.length()-1){
                            break;
                        }
                        String next = myText.substring(start+key.length(),start+key.length()+1);
                        System.out.println("The letter will be added is "+next);
                        followed.add(next);
                        pos = start + 1;
                        map.put(key,followed);
                    }*/
                
            }
        }
    }
    public void printHashMapInfo(){
        /*if(map.size()<100){
            for(String s : map.keySet()){
                System.out.println(s+" : "+map.get(s));
            }
        }*/
        System.out.println("Size : "+map.size());
        int currentMax = 0;
        String max = null;
        for(String s : map.keySet()){
            if(map.get(s).size()>currentMax){
                currentMax = map.get(s).size();
                max = s;
            }
        }
        System.out.println("Size of the biggest ArrayList: "+currentMax);
        System.out.println("The key of the biggest ArrayList: "+max);
    }
    public ArrayList <String> getFollows(String key){
        buildMap();
        ArrayList <String> follows = map.get(key);
        return follows;
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(index,index+N);
        sb.append(key);
        for(int k=0; k < numChars-N; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1)+next;
        }
        
        return sb.toString();
    }
    public String toString(){
        int n = N;
        return "Efficient MarkovModel of order "+n;
    }
}

