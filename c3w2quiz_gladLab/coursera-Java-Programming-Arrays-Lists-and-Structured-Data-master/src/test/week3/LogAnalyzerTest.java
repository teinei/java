package week3;

/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class LogAnalyzerTest
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
    	LogAnalyzer analyzer = new LogAnalyzer();
    	analyzer.readFile("data/weblogparser/short-test_log");
    	analyzer.printAll();
    }
    
    public void testUniqueIP() {
    	LogAnalyzer analyzer = new LogAnalyzer();
    	analyzer.readFile("data/weblogparser/weblog2_log");
    	System.out.println("The file has " + analyzer.countUniqueIPs() + " unique IPs");
    }
    
    public void testUniqueIPsInRange(int low, int high) {
    	LogAnalyzer analyzer = new LogAnalyzer();
    	analyzer.readFile("data/weblogparser/weblog2_log");
    	System.out.println("The file has " + analyzer.countUniqueIPsInRange(low, high) + " unique IPs in range " + low + " to " + high);
    }
    
    public void testPrintAllHigherThanNum(int num) {
    	LogAnalyzer analyzer = new LogAnalyzer();
    	analyzer.readFile("data/weblogparser/short-test_log");
    	analyzer.printAllHigherThanNum(num);
    }
    
    public void testUniqueIPVisitsOnDay(String someday) {
    	LogAnalyzer analyzer = new LogAnalyzer();
    	analyzer.readFile("data/weblogparser/weblog2_log");
    	List<String> uniqueIPs = analyzer.uniqueIPVisitsOnDay(someday);
    	System.out.println("Unique IP visits on " + someday + ": " + uniqueIPs.size());
    }
    
    public void testCountVisitsPerIP() {
    	LogAnalyzer analyzer = new LogAnalyzer();
    	analyzer.readFile("data/weblogparser/weblog1_log");
    	Map<String, Integer> map = analyzer.countVisitsPerIP();
    	System.out.println("Count Visits Per IP: " + map);
    }
    
    public void testMostNumberVisitsByIP() {
    	LogAnalyzer analyzer = new LogAnalyzer();
    	analyzer.readFile("data/weblogparser/weblog2_log");
    	Map<String, Integer> map = analyzer.countVisitsPerIP();
    	int result = analyzer.mostNumberVisitsByIP(map);
    	System.out.println("Most Number Visits By IP: " + result);
    }
    
    public void testIPsMostVisits() {
    	LogAnalyzer analyzer = new LogAnalyzer();
    	analyzer.readFile("data/weblogparser/weblog2_log");
    	Map<String, Integer> map = analyzer.countVisitsPerIP();
    	List<String> result = analyzer.iPsMostVisits(map);
    	System.out.println("IPs with the most visits: " + result);
    }
    
    public void testIPsForDays() {
    	LogAnalyzer analyzer = new LogAnalyzer();
    	analyzer.readFile("data/weblogparser/weblog3-short_log");
    	Map<String, List<String>> map = analyzer.iPsForDays();
    	System.out.println("IPs for days: " + map);
    }
    
    public void testDayWithMostIPVisits() {
    	LogAnalyzer analyzer = new LogAnalyzer();
    	analyzer.readFile("data/weblogparser/weblog2_log");
    	Map<String, List<String>> map = analyzer.iPsForDays();
    	String result = analyzer.dayWithMostIPVisits(map);
    	System.out.println("Day with most visits: " + result);
    }
    
    public void testIPsWithMostVisitsOnDay() {
    	LogAnalyzer analyzer = new LogAnalyzer();
    	analyzer.readFile("data/weblogparser/weblog2_log");
    	Map<String, List<String>> map = analyzer.iPsForDays();
    	List<String> ips = analyzer.iPsWithMostVisitsOnDay(map, "Sep 29");
    	System.out.println("IPs with Most Visits on Day: " + ips);
    }
    
    public static void main(String[] args) {
    	LogAnalyzerTest test = new LogAnalyzerTest();
    	//test.testLogAnalyzer();
    	test.testUniqueIP();
    	//test.testPrintAllHigherThanNum(300);
    	test.testUniqueIPVisitsOnDay("Sep 24");
    	test.testUniqueIPsInRange(400, 499);
    	
    	test.testCountVisitsPerIP();
    	test.testMostNumberVisitsByIP();
    	test.testIPsMostVisits();
    	test.testIPsForDays();
    	test.testDayWithMostIPVisits();
    	test.testIPsWithMostVisitsOnDay();
    }
}
