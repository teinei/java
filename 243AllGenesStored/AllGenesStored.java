import edu.duke.*;
/**
 * 243 week 2 section 4 lecture 3
 * Write a description of AllGenesStored here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllGenesStored {
    //
    public StorageResource getAllGenes(String dna){
        //
        StorageResource geneList=new StorageResource();
        int startIndex=0;
        //System.out.println("startIndex is "+startIndex);
        //      
        int i=0;
        int geneCount=0;
        //
        while(true){
            //System.out.println("1)i is "+i);
            //System.out.println("2)dna is "+dna);
            //System.out.println("3)startIndex is "+startIndex);
            String currentGene=findGene(dna,startIndex);// call set [3]
            //
            //System.out.println("4)currentGene is "+currentGene);
            if(currentGene.isEmpty()){
                //leave this loop
                //System.out.println("There are "+geneCount+" genes");
                break;
            }
            //
            geneList.add(currentGene);
            geneCount=geneCount+1;
            //System.out.println(currentGene);
            //System.out.println();
            startIndex=dna.indexOf(currentGene,startIndex)+
                       currentGene.length();
                       //index of currentGene from startIndex
            //System.out.println("5)new startIndex "+startIndex);  
            //
            //i++;
            //System.out.println("6)new i "+i);
            //System.out.println("-------");
            
        }
        //System.out.println("There are "+geneCount+" genes");
        //above line will not print
        return geneList;
    }
    public double cgRatio(String dna){
        //iterate over string, loop though
        int c=0;
        int g=0;
        for(char ch:dna.toCharArray()){
            //
            if(ch=='C')c=c+1;
            if(ch=='G')g=g+1;            
        }
        //System.out.println("c/g ration is "+c+"/"+g);
        return (double)c/g;
    }
    /*public int codonCount(String dna,String codon){
        if(
        return 0;
    }*/
    public void testOn(String dna){
        System.out.println("testing printAllGenes on "+dna);
        //System.out.println("==========");
        StorageResource genes = getAllGenes(dna);// call set [2]
        //q3 how many genes
        int count=0;
        int q4=0;
        int q5=0;
        double cgr=0.0;
        int q6=0;
        int q7=0;//longest dna length
        //String longest="";//longest dna
        for(String g:genes.data()){
            System.out.println(g);
            count=count+1;////q3
            if(g.length()>60) q4=q4+1;//q4
            //--------------
            cgr = cgRatio(g);
            if(cgr>0.35) q5=q5+1;
            int l=g.length();
            if(l>q7)q7=l;            
        }
        System.out.println("-----");
        System.out.println("there are "+count+" genes");
        System.out.println("-----");
        System.out.println("there are "+q4+" genes longer then 60");
        System.out.println("-----");
        System.out.println("there are "+q5+" genes cg ragio greater than 0.35");
        System.out.println("-----");
        System.out.println("the length of longest gene is "+q7);
    }
    public void test(){
        //2210 test find gene
        /*
        String dna="ATGCCCGGGAAATAACCC";
        String gene=findGene(dna);
        if(!gene.equals("ATGCCCGGGAAATAA")){
            System.out.println("error");
        }
        System.out.println("tests finished");
        */
        //
        ///*
        //FileResource fr=new FileResource("GRch38dnapart.fa");
        //FileResource fr=new FileResource();
        //------ test pq 245
        FileResource fr=new FileResource("brca1line.fa");//not pass
        String dna=fr.asString();//convert file resource to string
        testOn(dna);
        //------ test pq 245
        //======
        /*
        //------
        //test 2203, test passed
        String dna="ATGATCGCTAATGCTTAAGCTATG";//eg 2203
        testOn(dna);//call site [1]
        //test 2203
        //------
        */
        //*/
        //testOn("AATGCTAACTAGCTGACTAAT");//practice quiz q1
        /*
        testOn("ATGATCTAAXXXYYY");
        testOn("ATGATCTAATTTATGCTGCTGCAACGGTGAAGA");
        testOn("");
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
        */
    }
    //code from previous
     public int findStopCodon(String dnaStr, 
                             int startIndex, 
                             String stopCodon){
        int currIndex=dnaStr.indexOf(stopCodon,startIndex+3);
        while(currIndex!=-1){           
            //
            int diff=currIndex-startIndex;
            if(diff%3==0){
                return currIndex;
            }
            else{
                //
                currIndex=dnaStr.indexOf(stopCodon,currIndex+1);//bug
            }
        }
        return -1;
    }
    //code from  previous
    public String findGene(String dna,int where){
        int startIndex=dna.indexOf("ATG",where);
        if(startIndex==-1){
            return "";
        }
        int taaIndex=findStopCodon(dna,startIndex,"TAA");//call set [4]
        int tagIndex=findStopCodon(dna,startIndex,"TAG");//call set [5] 
        int tgaIndex=findStopCodon(dna,startIndex,"TGA");//call site [6] 
        // use math function
        int temp=0;
        int minIndex=0;
        int currIndex=0;
        String gene="";
        /*
        if(taaIndex==-1){
            //currIndex!=taaIndex;
            if(tagIndex==-1){
                if(tgaIndex==-1){
                    //currIndex=0;
                    System.out.println("stop codon found");
                }else{
                    currIndex=tgaIndex;
                }
                
            }else{
                //
                currIndex=Math.min(tgaIndex,tagIndex);
            }
        }else{//3 stop codons are all found
            //
            temp=Math.min(taaIndex,tagIndex);
            minIndex=Math.min(temp ,tgaIndex);
            currIndex=minIndex;
        }
        */
        
        //
        if(taaIndex==-1||tagIndex==-1||tgaIndex==-1){
            //
        }else{
            temp=Math.min(taaIndex,tagIndex);
            minIndex=Math.min(temp ,tgaIndex);
            currIndex=minIndex;            
        }
        
        //
        //
        //
        if(taaIndex!=-1&&(tagIndex!=-1&&tgaIndex!=-1)){        
            temp=Math.min(taaIndex,tagIndex);
            minIndex=Math.min(temp ,tgaIndex);
            currIndex=minIndex;
            //gene=dna.substring(startIndex,minIndex+3);
        } 
        if(taaIndex!=-1&&tagIndex!=-1&&tgaIndex==-1){
            currIndex=Math.min(taaIndex,tgaIndex);
        }
        if(taaIndex!=-1&&tagIndex==-1&&tgaIndex==-1){
            currIndex=Math.min(taaIndex,tgaIndex);
        }    //
        if(taaIndex==-1&&tgaIndex!=-1&&tgaIndex!=-1){
            currIndex=Math.min(tagIndex,tgaIndex);
        }
        System.out.println();
        if(taaIndex!=-1&&tagIndex==-1&&tgaIndex==-1){
            currIndex=taaIndex;
        }
        if(taaIndex==-1&&tagIndex!=-1&&tgaIndex==-1){
            currIndex=tagIndex;
        }
        if(taaIndex==-1&&tagIndex==-1&&tgaIndex!=-1){
            currIndex=tgaIndex;
        }
        //
        //
        if(currIndex!=0){
            gene=dna.substring(startIndex,currIndex+3);
        }
        System.out.println(taaIndex+","+tagIndex+","+tgaIndex);
        System.out.println("currIndex is "+currIndex);
        //
        //use logical operator
        /*
        int minIndex=0;
        int maxIndex=0;
        //
        if(taaIndex==-1||
        (tgaIndex!=-1&&tgaIndex<taaIndex)){
            minIndex=tgaIndex;
            maxIndex=taaIndex;
        }else{
            minIndex=taaIndex;
            maxIndex=tgaIndex;
        }
        //
        if(minIndex==-1|| 
        (tagIndex!=-1&&tagIndex<minIndex)){
            minIndex=tagIndex;
            maxIndex=minIndex;
        }
        */
        //
        //
        //
        /*
        if(minIndex==dna.length()){
            return "";
        }
        */
        //System.out.println(minIndex);
        
        
        System.out.println("gene is "+gene);
        return gene;
        //return dna.substring(startIndex,minIndex+3);//out of bounds exception
    }
}
