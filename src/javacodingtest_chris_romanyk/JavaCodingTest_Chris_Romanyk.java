package javacodingtest_chris_romanyk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaCodingTest_Chris_Romanyk {

    // read in each of the flights into a list of flights and then remove doubles and return list
    public static Set<Flight> loadFlights(Set<Flight> flights) {
        //clear the array
        flights.clear();
        //Get all files in the file system placed.
        File[] files = new File("src/flights").listFiles();
        //Flight to bring in from file String 
        String newFlight;
        // BufferedReader Object to read through file 
        BufferedReader br;
        // Create a date time formats to match output and converting for depature and destination time
        SimpleDateFormat slashDates = new SimpleDateFormat("MM/d/yyyy k:mm:ss");
        SimpleDateFormat dashDates = new SimpleDateFormat("MM-d-yyyy k:mm:ss");
        //Create a loop counter for the matcher, I am returning multiple datetimes on every line of the file with 'the matcher.group() 
        // so this is used to diffrentiate the depature and destination times 
        int matchCounter = 0;

        // for each file open read through them, parse into a flight object
        for (File file : files) {

            try {
                // instantiate the reader object with the file s
                br = new BufferedReader(new FileReader(file));

                //for each line in a file, parse the line to a flight 
                while ((newFlight = br.readLine()) != null) {

                    //Create the new flight object
                    Flight nf = new Flight();

                    // get Origin City
                    Pattern pattern = Pattern.compile("^[A-Z]{3}");
                    Matcher matcher = pattern.matcher(newFlight);
                    while (matcher.find()) {
                        nf.setOrigin(matcher.group());
                    }
                    // get destination city
                    pattern = Pattern.compile("[A-Z]{3}");
                    matcher = pattern.matcher((newFlight));
                    while (matcher.find()) {
                        nf.setDestination(matcher.group());
                    }
                    // get Origin and destination times
                    pattern = Pattern.compile("([0-9/-]{9})\\s([0-9:]{7,8})");
                    matcher = pattern.matcher(newFlight);
                    while (matcher.find()) {

                        // Add the dates containing a - as opposed to a /
                        if (matcher.group().contains("-")) {

                            try {
                                // if the loops is running for the first time then you have the depature, if it has run once it is the 
                                // destination 
                                if (matchCounter < 1) {
                                    nf.setDepartureTime(dashDates.parse(matcher.group()));
                                } else {
                                    nf.setDestinationTime(dashDates.parse(matcher.group()));
                                }

                            } catch (Exception ex) {
                                System.out.println(ex);
                            }

                            // Add the dates containing a / as opposed to a -
                        } else {

                            try {
                                if (matchCounter < 1) {
                                    nf.setDepartureTime(slashDates.parse(matcher.group()));
                                } else {
                                    nf.setDestinationTime(slashDates.parse(matcher.group()));
                                }

                            } catch (Exception ex) {
                                System.out.println(ex);
                            }

                        }
                        matchCounter++;
                    }
                    // get the price
                    pattern = Pattern.compile("\\$([0-9.]{0,10})");
                    matcher = pattern.matcher((newFlight));
                    while (matcher.find()) {
                        nf.setPrice(Double.parseDouble(matcher.group(1)));
                    }

                    flights.add(nf);
                    matchCounter = 0;
                }

                br.close();

            } catch (Exception ex) {
            }
        }

        return flights;
    }

    public static void main(String[] args) {

        // Applicatino Variables 1
        //Matcher Counter for mainApp 
        int matchCounter;
        //Loop Counter
        int exitCondition;
        // Scanner Object
        Scanner sc = new Scanner(System.in);
        //Location String
        String location;
        //substrings for flight origin and location that is searched
        String flightOrigin = "";
        String flightDestination = "";
        // list of Flights with duplicates
        Set<Flight> flights = new HashSet<>();
        // Setup a regex parsing pattern 
        // get destination city
        Pattern pattern;
        Matcher matcher;

        try {
            loadFlights(flights);
        } catch (Exception ex) {
            System.out.print(ex);
        }
        do {

            // Initial Method
            System.out.println("Hello, Welcome to Flight Seacher \n 1. Search Flights. \n 2. Exit");
            exitCondition = sc.nextInt();
            sc.nextLine();

            switch (exitCondition) {

                case 1:
                    matchCounter = 0;
                    System.out.println("Search Flights");
                    System.out.println("Please Enter your origin(-0) and destination(-d)");
                    location = sc.nextLine();

                    // get cities
                    pattern = Pattern.compile("(?:\\s*)(\\w{3})");
                    matcher = pattern.matcher((location));
                    while (matcher.find()) {

                        if (matchCounter == 0) {
                            flightOrigin = matcher.group().toUpperCase().trim();
                            matchCounter++;
                        } else {
                            flightDestination = matcher.group().toUpperCase().trim();
                        }
                    }
                    //search flights that match destination and origin
                    List<Flight> searchedFlights = new ArrayList<>();

                    for (Flight flight : flights) {

                        if (flight.getOrigin().equals(flightOrigin) && flight.getDestination().equals(flightDestination)) {
                            searchedFlights.add(flight);
                        }
                    }
                    if (searchedFlights.size() < 1) {
                        System.out.println("No flights found for " + flightOrigin + " --> " + flightDestination);
                    }

                    //Sort the flights based on price and then 
                    Collections.sort(searchedFlights, Comparator.comparingDouble(Flight::getPrice).thenComparing(Flight::getDepartureTime));
                    // display rhe flights
                    searchedFlights.forEach((flight) -> {
                        System.out.println(flight.toString());
                    });
                    break;

                case 2:
                    System.exit(0);
                    break;

                default:
                    break;
            }
        } while (exitCondition != 3);
    }
}
