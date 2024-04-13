import java.util.*;

// Class to represent an edge between two railway stations
class Edge implements Comparable<Edge> {
    RailwayStation source;
    RailwayStation destination;
    double weight;

    public Edge(RailwayStation source, RailwayStation destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Double.compare(this.weight, other.weight);
    }
}

// Class to represent a railway station and its location
class RailwayStation {

    String name;
    double latitude;
    double longitude;
    static ArrayList<RailwayStation> stationsList = new ArrayList<RailwayStation>();

    RailwayStation(){}

    public RailwayStation(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static void stationsAroundWorld(){
        stationsList.add(new RailwayStation("Ahmedabad_Junction", 23.02666977651141, 72.60126184319768));
        stationsList.add(new RailwayStation("Vadodara_Junction", 22.310973715726394, 73.18074322015786));
        stationsList.add(new RailwayStation("Surat_Railway_Station", 21.203463508200027, 72.84022823917537));
        stationsList.add(new RailwayStation("Rajkot_Junction", 22.31323518356964, 70.80260665455393));
        stationsList.add(new RailwayStation("Bhavnagar_Terminus",21.781934334056068, 72.1422784391928 ));
        stationsList.add(new RailwayStation("Jamnagar_Railway_Station",22.49290348635437, 70.05343554655907 ));
        stationsList.add(new RailwayStation("Gandhinagar_Capital_Railway_Station", 23.23505108878305, 72.63030945649254));
        stationsList.add(new RailwayStation("Bhuj_Railway_Station", 23.266086959759, 69.67848148541435));
        stationsList.add(new RailwayStation("Anand_Junction", 22.560997266320612, 72.96679633921698));
        stationsList.add(new RailwayStation("Valsad_Railway_Station",20.607994650314794, 72.93354292381338 ));
        stationsList.add(new RailwayStation("Grand_Central_Terminal",40.75354296700538, -73.97673337768325));
        stationsList.add(new RailwayStation("Chennai Central", 13.082976543272784, 80.2754138659693));
        stationsList.add(new RailwayStation("Mumbai CST", 18.940295706171753, 72.83574212871628));
        stationsList.add(new RailwayStation("Howrah Junction", 22.58392782060404, 88.3421614527092));
        stationsList.add(new RailwayStation("Bangalore City", 12.9779, 77.5669));
        stationsList.add(new RailwayStation("New Delhi", 28.6142, 77.2023));
        stationsList.add(new RailwayStation("Kolkata Railway Station", 22.5726, 88.3639));
        stationsList.add(new RailwayStation("Hyderabad Deccan", 17.3616, 78.4747));
        stationsList.add(new RailwayStation("Pune Junction", 18.5204, 73.8567));
        stationsList.add(new RailwayStation("Jaipur Junction", 26.9124, 75.7873));
        stationsList.add(new RailwayStation("Lucknow Charbagh", 26.8504, 80.9499));
        stationsList.add(new RailwayStation("Kanpur Central", 26.4499, 80.3319));
        stationsList.add(new RailwayStation("Patna Junction", 25.6093, 85.1235));
        stationsList.add(new RailwayStation("Thane Railway Station", 19.2183, 72.9781));
        stationsList.add(new RailwayStation("Nagpur Junction", 21.1501, 79.0129));
        stationsList.add(new RailwayStation("Guwahati Railway Station", 26.1445, 91.7362));
        stationsList.add(new RailwayStation("Trivandrum Central", 8.4981, 76.9493));
        stationsList.add(new RailwayStation("Visakhapatnam Junction", 17.7265, 83.3149));
        stationsList.add(new RailwayStation("Bhopal Junction", 23.2599, 77.4126));
        stationsList.add(new RailwayStation("Indore Junction", 22.7196, 75.8577));
        stationsList.add(new RailwayStation("Agra Cantt", 27.1619, 78.0469));
        stationsList.add(new RailwayStation("Varanasi Junction", 25.3176, 82.9739));
        stationsList.add(new RailwayStation("Kochi (Ernakulam) South", 9.9585, 76.2746));
    }
    public static void addStation(String name, double latitude, double longitude) {
        stationsList.add(new RailwayStation(name, latitude, longitude));
        System.out.println("Station added successfully!");
    }
}



public class NearestRailwayStation {
    // Function to calculate the distance between two points using Haversine formula
    static Scanner sc = new Scanner(System.in);
    static double userLat=0.0,userLon=0.0;

    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2)
                * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // convert to kilometers

        return distance;
    }

    // Function to find the nearest railway station to the user's location without mst
    public static RailwayStation findNearestRailwayStation(double userLat, double userLon, List<RailwayStation> stations) {
        RailwayStation nearestStation = null;
        double minDistance = Double.MAX_VALUE;

        // Calculate distances between user location and all railway stations
        for (RailwayStation station : stations) {
            double distance = calculateDistance(userLat, userLon, station.latitude, station.longitude);
            if (distance < minDistance) {
                minDistance = distance;
                nearestStation = station;
            }
        }

        return nearestStation;
    }

    // Function to find the nearest railway station from user location using MST algorithm
    public static RailwayStation findNearestRailwayStationMST(double userLat, double userLon, List<RailwayStation> stations) {
        RailwayStation nearestStation = null;
        double minDistance = Double.MAX_VALUE;

        // Create edges between user location and all railway stations
        List<Edge> edges = new ArrayList<>();
        for (RailwayStation station : stations) {
            double distance = calculateDistance(userLat, userLon, station.latitude, station.longitude);
            edges.add(new Edge(new RailwayStation("User", userLat, userLon), station, distance));
        }

        // Sort edges by weight
        Collections.sort(edges);

        // Find the nearest station based on the minimum weight edge
        Edge minEdge = edges.get(0);
        minDistance = minEdge.weight;
        nearestStation = minEdge.destination;

        if(minDistance<250){
            return nearestStation;
        }
        return null;
    }
    public static List<RailwayStation> find_nearest_railway_stations_MST(double userLat, double userLon, List<RailwayStation> stations, int count) {
        List<RailwayStation> nearestStations = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();

        // Create edges between user location and all railway stations
        for (RailwayStation station : stations) {
            double distance = calculateDistance(userLat, userLon, station.latitude, station.longitude);
            edges.add(new Edge(new RailwayStation("User", userLat, userLon), station, distance));
        }

        // Sort edges by weight
        Collections.sort(edges);

        // Add the nearest stations to the list
        for (Edge edge : edges) {
            nearestStations.add(edge.destination);
            if (nearestStations.size() == count) {
                break;
            }
        }

        return nearestStations;
    }
    public static List<RailwayStation> searchRailwayStation(String query) {
        List<RailwayStation> searchResults = new ArrayList<>();

        // Convert the query to lowercase for case-insensitive search
        String lowercaseQuery = query.toLowerCase();

        // Iterate through all railway stations in the stationsList
        for (RailwayStation station : RailwayStation.stationsList) {
            // Check if the station name contains the query string
            if (station.name.toLowerCase().contains(lowercaseQuery)) {
                // If a match is found, add the station to the search results
                searchResults.add(station);
            }
        }

        return searchResults;
    }
    public static RailwayStation search(String query) {
        RailwayStation station = new RailwayStation() ;

        // Convert the query to lowercase for case-insensitive search
        String lowercaseQuery = query.toLowerCase();

        // Iterate through all railway stations in the stationsList
        for (RailwayStation st : RailwayStation.stationsList) {
            // Check if the station name contains the query string
            if (st.name.toLowerCase().contains(lowercaseQuery)) {
                // If a match is found, add the station to the search results
                station = st;
            }
        }

        return station;
    }
    public static void removeStation(String stationName){
        RailwayStation station = search(stationName);
        boolean contains = RailwayStation.stationsList.remove(station);
        if(contains){
            System.out.println("STATION IS DELETED SUCCESSFULLY !");
        }
        else{
            System.out.println("STATION IS NOT FOUND !");
        }
    }

    public static void main(String[] args) throws Exception {

        // Sample railway station
        RailwayStation.stationsAroundWorld();

        System.out.println("\033[1;32m\033[1;35m"); // Bold green color
        System.out.println("*******************************************");
        System.out.println("*             WELCOME TO THE              *");
        System.out.println("*          RAILWAY STATION FINDER         *");
        System.out.println("*******************************************");
        System.out.println("\033[0m"); // Reset color to default

        try{
            System.out.println("Enter Latitude of your Location : ");
            userLat = sc.nextDouble();
            System.out.println("Enter Longitude of your Location : ");
            userLon =  sc.nextDouble();
            sc.nextLine();
        }
        catch (Exception ex){
            System.out.println("ENTER VALID INPUT TYPE !");
            sc.nextLine();
        }
        while (true) {
            System.out.println("\033[1;31m┌───────────────────────────────────────────────┐\033[0m");
            System.out.println("\033[1;34m│ \033[0m\033[1;33m\t\t\t\t\t MAIN MENU \033[0m\033[1;34m                 │\033[0m");
            System.out.println("\033[1;31m├───────────────────────────────────────────────┤\033[0m");
            System.out.println("\033[1;34m│ \033[0m\033[1;33m1 : FIND NEAREST RAILWAY STATION\033[0m\033[1;34m              │\033[0m");
            System.out.println("\033[1;34m│ \033[0m\033[1;33m2 : ADD RAILWAY STATIONS         \033[0m\033[1;34m             │\033[0m");
            System.out.println("\033[1;34m│ \033[0m\033[1;33m3 : FIND NUMBER OF NEAREST STATIONS  \033[0m\033[1;34m         │\033[0m");
            System.out.println("\033[1;34m│ \033[0m\033[1;33m4 : IF YOU WANT TO CHANGE YOUR LAT AND LONG \033[0m\033[1;34m  │\033[0m");
            System.out.println("\033[1;34m│ \033[0m\033[1;33m5 : SEARCH RAILWAY STATION \033[0m\033[1;34m                   │\033[0m");
            System.out.println("\033[1;34m│ \033[0m\033[1;33m6 : DISPLAY RAILWAY STATIONS \033[0m\033[1;34m                 │\033[0m");
            System.out.println("\033[1;34m│ \033[0m\033[1;33m7 : DELETE RAILWAY STATIONS \033[0m\033[1;34m                  │\033[0m");
            System.out.println("\033[1;34m│ \033[0m\033[1;33m8 : EXIT                         \033[0m\033[1;34m             │\033[0m");
            System.out.println("\033[1;31m└───────────────────────────────────────────────┘\033[0m");
            System.out.println("\033[1;32mENTER CHOICE : \033[0m");
            String choice = sc.nextLine();
            if (choice.equalsIgnoreCase("1")) {
                // Find the nearest railway station using MST algorithm
                RailwayStation nearestStation = findNearestRailwayStationMST(userLat, userLon, RailwayStation.stationsList);

                // Output the nearest railway station
                if (nearestStation != null) {
                    System.out.println("Nearest railway station: " + nearestStation.name+"\n\n");
                }  else {
                    System.out.println("No railway stations found.");
                }
            }
            else if(choice.equalsIgnoreCase("2")) {
                System.out.println("Do you want to add a new railway station? (y/n)");
                String ch = sc.nextLine();
                if (ch.equalsIgnoreCase("y")) {
                    String rs_name = "";
                    double rs_Lat =0.0,rs_Long = 0.0;
                    try {
                        System.out.println("ENTER RAILWAY STATION NAME : ");
                        rs_name = sc.nextLine();
                        System.out.println("ENTER RAILWAY STATION LATITUDE : ");
                        rs_Lat = sc.nextDouble();
                        System.out.println("ENTER RAILWAY STATION LONGITUDE : ");
                        rs_Long = sc.nextDouble();
                        sc.nextLine();
                    }
                    catch(Exception ex){
                        System.out.println("ENTER VALID INPUT TYPE !");
                        sc.nextLine();
                        continue;
                    }
                    for (RailwayStation ele : RailwayStation.stationsList) {
                        if ((ele.name.equalsIgnoreCase(rs_name)) || ele.latitude==rs_Lat || ele.longitude==rs_Long){
                            System.out.println("Sorry , this station is already added to our data base!");
                        }
                        else{
                            RailwayStation.addStation(rs_name, rs_Lat, rs_Long);
                            break;
                        }
                    }

                } else if (ch.equalsIgnoreCase("n")) {
                    continue;
                } else {
                    System.out.println("ENTER VALID CHOICE !");
                }
            }
            else if(choice.equalsIgnoreCase("3")) {
                System.out.println("WANT TO KNOW HOW MANY STATIONS ARE CLOSEST TO YOU ?");
                System.out.print("ENTER NUMBER OF STATIONS :- ");
                int number = sc.nextInt();
                sc.nextLine();
                List<RailwayStation> nearestStations = find_nearest_railway_stations_MST(userLat, userLon, RailwayStation.stationsList, number);
                // Output the nearest railway stations
                System.out.println("Nearest railway stations:");
                System.out.println("\033[1;31m┌───────────────────────────────────────────┐──────────────────────────┐\033[0m");
                System.out.println("\033[1;33m│                 STATION NAME              │    Distance from user    │\033[0m");
                System.out.println("\033[1;31m├───────────────────────────────────────────┤──────────────────────────┤\033[0m");
                for (RailwayStation station : nearestStations) {
                    double distance = calculateDistance(userLat, userLon, station.latitude, station.longitude);
                    String formattedDistance = String.format("%.2f km", distance); // Format distance to two decimal places
                    System.out.printf("\033[1;33m│ %-41s │ %-24s │\033[0m%n", station.name, formattedDistance);
                }
                System.out.println("\033[1;31m└───────────────────────────────────────────┴──────────────────────────┘\033[0m");
            }
            else if(choice.equalsIgnoreCase("4")) {
                try{
                    System.out.println("Enter Latitude of your Location : ");
                    userLat = sc.nextDouble();
                    System.out.println("Enter Longitude of your Location : ");
                    userLon =  sc.nextDouble();
                    sc.nextLine();
                }
                catch (Exception ex){
                    System.out.println("ENTER VALID INPUT TYPE !");
                    sc.nextLine();
                }
            }
            else if (choice.equalsIgnoreCase("5")) {
                System.out.println("Enter the name of the railway station you want to search: ");
                String searchQuery = sc.nextLine();
                List<RailwayStation> searchResults = NearestRailwayStation.searchRailwayStation(searchQuery);
                if (!searchResults.isEmpty()) {
                    System.out.println("\033[1;31m┌─────────────────────────────────────────┐\033[0m");
                    System.out.println("\033[1;34m│ \033[0m\033[1;33m SEARCH RESULT : \033[0m\033[1;34m                       │\033[0m");
                    System.out.println("\033[1;31m└─────────────────────────────────────────┘\033[0m");
                    for (RailwayStation result : searchResults) {
                        double distanceStation = calculateDistance(userLat, userLon, result.latitude, result.longitude);
                        String formattedLat = String.format("%.5f", result.latitude);
                        String formattedLon = String.format("%.5f", result.longitude);
                        String formattedDistance = String.format("%.5f", distanceStation);

                        System.out.println("\033[1;31m┌───────────────────────────────────────────────────────┐\033[0m");
                        System.out.println("\033[1;34m│ \033[0m\033[1;33m STATION NAME: " + String.format("%-39s", result.name) + "\033[0m\033[1;34m│\033[0m");
                        System.out.println("\033[1;34m│ \033[0m\033[1;33m LATITUDE: " + String.format("%-43s", formattedLat) + "\033[0m\033[1;34m│\033[0m");
                        System.out.println("\033[1;34m│ \033[0m\033[1;33m LONGITUDE: " + String.format("%-42s", formattedLon) + "\033[0m\033[1;34m│\033[0m");
                        System.out.println("\033[1;34m│ \033[0m\033[1;33m DISTANCE : " + String.format("%-42s", formattedDistance + " KM") + "\033[0m\033[1;34m│\033[0m");
                        System.out.println("\033[1;31m└───────────────────────────────────────────────────────┘\033[0m");
                    }

                } else {
                    System.out.println("No matching railway stations found.");
                }
            }
            else if(choice.equalsIgnoreCase("6")){
                List<RailwayStation> nearestStations = find_nearest_railway_stations_MST(userLat, userLon, RailwayStation.stationsList, RailwayStation.stationsList.size());
                // Output the nearest railway stations
                System.out.println("Nearest railway stations:");
                System.out.println("\033[1;31m┌───────────────────────────────────────────┐──────────────────────────┐\033[0m");
                System.out.println("\033[1;33m│                STATION NAME               │    Distance from user    │\033[0m");
                System.out.println("\033[1;31m├───────────────────────────────────────────┤──────────────────────────┤\033[0m");
                for (RailwayStation station : nearestStations) {
                    double distance = calculateDistance(userLat, userLon, station.latitude, station.longitude);
                    String formattedDistance = String.format("%.2f km", distance); // Format distance to two decimal places
                    System.out.printf("\033[1;33m│\t  %-37s │\t %-21s │\033[0m%n", station.name, formattedDistance);
                }
                System.out.println("\033[1;31m└───────────────────────────────────────────┴──────────────────────────┘\033[0m");
            }
            else if (choice.equalsIgnoreCase("7")){
                System.out.println("ENTER RAILWAY STATION FOR DELETE IT : ");
                String name = sc.nextLine();
                removeStation(name);
            }
            else if (choice.equalsIgnoreCase("8")) {
                System.exit(0);
            } else {
                System.out.println("Enter Valid Choice !");
            }
        }

    }
}