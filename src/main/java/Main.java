import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static java.lang.Math.cos;
import static util.Constants.*;


public class Main {

    private static final int BACKGROUND_GREEN = 227;


    public static void main(String[] args) throws IOException {
        BufferedImage bufferedImage = null;
        Map<String, Country> countryMap = new HashMap<>();
        countryMap = loadCountries(countryMap);
        countryMap = loadAdditionalCountryData(countryMap);
        List<Airport> airports = new ArrayList<>();
        airports = loadAirports(airports);
        bufferedImage = loadBufferedImage(bufferedImage);
        airports = setAirportCountries(airports, countryMap);
        Map<String,String> countryCodes = new HashMap<String,String>();
        countryCodes = setCountryCodes(countryMap,countryCodes);
        List<Route> routes = new ArrayList<Route>();

        int mapHeight;
        mapHeight = bufferedImage.getHeight();
        int mapWidth;
        mapWidth = bufferedImage.getWidth();
        for (Map.Entry<String,String> entry : countryCodes.entrySet()) {
            System.out.println(entry.getKey() + " corresponds to " + entry.getValue());
        }

        for (Airport airport : airports) {
            airport.setLon();
            airport.setLat();

            airport.setPopulationInRadius(calcPopInRadius(airport.getLat(), airport.getLon(), mapHeight, mapWidth, bufferedImage));
        }
         routes = makeTestRoutes(airports, routes, countryMap,countryCodes);


    }
    private static List<Route> makeTestRoutes(List<Airport> airports, List<Route> routes, Map<String, Country> countryMap, Map<String, String> countryCodes){
        Times t = new Times(1,1,1,1,1);

        Airport airport2 = airports.get(0);
        String countryKey2 = airport2.getCountryKey();
        String country2 = countryCodes.get(countryKey2);


        for (int i = 1; i <airports.size() ; i++) {
            Airport airport = airports.get(i);
            String countryKey = airport.getCountryKey();
            String country = countryCodes.get(countryKey);

            Route newRoute = new Route(airports.get(0), airports.get(i),country2,country,t);
            newRoute.setBasePAXNumber(countryMap);
            routes.add(newRoute);
        }
        return routes;
    }

    public static float calcPopInRadius(float lattitude, float longditude, int height, int width, BufferedImage popmap) throws IOException {
        int pixely = 0;
        int pixelx = 0;
        //finds x/y pixels

        if (lattitude >= 0) {
            pixely = (int) ((90 - lattitude) * 24) - 120;

        } else if (lattitude <= 0) {
            pixely = (int) ((90 - lattitude) * 24) - 120;

        }

        if (longditude >= 0) {

            pixelx = (int) ((width / 2) - ((longditude / 180) * (width / 2)));

        } else if (longditude <= 0) {
            pixelx = (int) ((width / 2) - ((longditude / 180) * (width / 2)));

        }
        // conbensation for mercader wackness
        int trueRad = (int) (20 * (1 / (cos(Math.toRadians(lattitude)))));



        double kFactor = (1 / (cos(Math.toRadians(lattitude))));

        trueRad = Math.abs(trueRad);


        float population = 0;
        //adds popuation using map
        for (int i = pixelx - trueRad; i < pixelx + trueRad; i++) {
            for (int j = pixely - trueRad; j < pixely + trueRad; j++) {

                if ((int) (Math.sqrt(((pixelx - i) * (pixelx - i) + ((pixely - j) * (pixely - j))))) < trueRad) {
                    Color c1 = new Color(popmap.getRGB(i, j));
                    double popInc = getPopInc(c1);

                    double popPerPixel = (54 / (kFactor * kFactor) * popInc);
                    population += popPerPixel;


                }

            }
        }

       return population;

    }


    private static double getPopInc(Color c1) {
        double popInc = 0;
        if (c1.equals(WHITE)) {
            popInc = 0;
        } else if (c1.equals(ZERO)) {
            popInc = 0.25;
        } else if (c1.equals(POINT_FIVE)) {
            popInc = 0.75;
        } else if (c1.equals(ONE)) {
            popInc = 2;
        } else if (c1.equals(THREE)) {
            popInc = 4;
        } else if (c1.equals(FIVE)) {
            popInc = 7.5;
        } else if (c1.equals(TEN)) {
            popInc = 17.5;
        } else if (c1.equals(TWENTY_FIVE)) {
            popInc = 37.5;
        } else if (c1.equals(FIFTY)) {
            popInc = 75;
        } else if (c1.equals(ONE_HUNDRED)) {
            popInc = 125;
        } else if (c1.equals(ONE_FIFTY)) {
            popInc = 175;
        } else if (c1.equals(TWO_HUNDRED)) {
            popInc = 250;
        } else if (c1.equals(THREE_HUNDRED)) {
            popInc = 350;
        } else if (c1.equals(FOUR_HUNDRED)) {
            popInc = 450;
        } else if (c1.equals(FIVE_HUNDRED)) {
            popInc = 550;
        } else if (c1.equals(SIX_HUNDRED)) {
            popInc = 700;
        } else if (c1.equals(EIGHT_HUNDRED)) {
            popInc = 900;
        } else if (c1.equals(ONE_THOUSAND)) {
            popInc = 1125;
        } else if (c1.equals(THOUSAND_TWO_FIFTY)) {
            popInc = 1375;
        } else if (c1.equals(THOUSAND_FIVE_HUNDRED)) {
            popInc = 1675;
        } else if (c1.equals(THOUSAND_SEVEN_FIFTY)) {
            popInc = 1875;
        } else if (c1.equals(TWO_THOUSAND)) {
            popInc = 2250;
        } else if (c1.equals(TWO_THOUSAND_FIVE_HUNDRED)) {
            popInc = 2750;
        } else if (c1.equals(THREE_THOUSAND)) {
            popInc = 3500;
        } else if (c1.equals(FOUR_THOUSAND)) {
            popInc = 5000;
        } else if (c1.equals(SIX_THOUSAND)) {
            popInc = 7000;
        } else if (c1.equals(EIGHT_THOUSAND)) {
            popInc = 9000;
        } else if (c1.equals(TEN_THOUSAND)) {
            popInc = 17500;
        } else if (c1.equals(TWENTY_FIVE_THOUSAND)) {
            popInc = 37500;
        } else if (c1.equals(FIFTY_THOUSAND_PLUS)) {
            popInc = 50000;
        }

        return popInc;
    }

    public static Map loadCountries(Map<String, Country> countryMap) throws IOException {


        //read file into stream, try-with-resources
        countryMap = parseArrivals(countryMap, System.getProperty("user.dir") + "/src/main/resources/tourism_arrivals");
        countryMap = parseDepartures(countryMap, System.getProperty("user.dir") + "/src/main/resources/tourism_departures");
        countryMap = parseExpenditures(countryMap, System.getProperty("user.dir") + "/src/main/resources/tourism_expenditures");


        float arrivals = 0;
        for (Country countries : countryMap.values()) {
            arrivals += countries.arrivals;
        }


        return countryMap;
    }

    private static Map<String, Country> parseExpenditures(Map<String, Country> countryMap, String fileLocation) {
        try (Stream<String> stream = Files.lines(Paths.get(fileLocation))) {
            stream.forEach(line -> {

                        String[] parse2 = line.split("[\\d+]");
                        String name = parse2[0].trim();

                        String restOfStuff = line.substring(name.length()).trim();
                        String[] parse3 = restOfStuff.split("\\s");

                        int year;
                        float info;
                        try {
                            year = Integer.parseInt(parse3[0].trim());
                            info = Float.parseFloat(parse3[1].trim().replaceAll(",", ""));
                        } catch (Exception e) {
                            year = -1;
                            info = -1;
                        }
                        Country thisCountry = new Country(name, year, info);
                        if (countryMap.containsKey(name)) {
                            Country country = countryMap.get(name);
                            country.expenditures = info;
                        }
                    }
            );


        } catch (IOException e) {
            e.printStackTrace();
        }
        return countryMap;
    }

    private static Map<String, Country> parseDepartures(Map<String, Country> countryMap, String fileLocation) {
        try (Stream<String> stream = Files.lines(Paths.get(fileLocation))) {
            stream.forEach(line -> {
                        String[] parse = line.split("\\s");

                        String[] parse2 = line.split("[\\d+]");


                        String name = parse2[0].trim();

                        String restOfStuff = line.substring(name.length()).trim();
                        String[] parse3 = restOfStuff.split("\\s");

                        int year;
                        float info;
                        try {
                            year = Integer.parseInt(parse3[0].trim());
                            info = Float.parseFloat(parse3[1].trim().replaceAll(",", ""));
                        } catch (Exception e) {
                            year = -1;
                            info = -1;
                        }
                        Country thisCountry = new Country(name, year, info);
                        if (countryMap.containsKey(name)) {
                            Country country = countryMap.get(name);
                            country.departures = info;
                        }
                    }
            );


        } catch (IOException e) {
            e.printStackTrace();
        }
        return countryMap;
    }

    static Map<String, Country> parseArrivals(Map<String, Country> countryMap, String fileLocation) {
        try (Stream<String> stream = Files.lines(Paths.get(fileLocation))) {
            stream.forEach(line -> {

                        String[] parse2 = line.split("[\\d+]");


                        String name = parse2[0].trim();


                        String restOfStuff = line.substring(name.length()).trim();
                        String[] parse3 = restOfStuff.split("\\s");

                        int year;
                        float info;
                        try {
                            year = Integer.parseInt(parse3[0].trim());
                            info = Float.parseFloat(parse3[1].trim().replaceAll(",", ""));
                        } catch (Exception e) {
                            year = -1;
                            info = -1;
                        }
                        Country thisCountry = new Country(name, year, info);
                        countryMap.put(name, thisCountry);
                    }
            );


        } catch (IOException e) {
            e.printStackTrace();
        }
        return countryMap;
    }
    public static Map<String,Country> loadAdditionalCountryData(Map<String, Country> incountryMap){
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\wayne\\IdeaProjects\\projectname\\src\\main\\resources\\countriesdata.csv")));
            String line = br.readLine();

            while ((line = br.readLine()) != null) {

                String[] columns = line.split(",");



                try {
                    String key = columns[0].trim();
                    if(incountryMap.containsKey(key)) {
                        float gdp = 0;
                        String countryKey = "";
                        int population = 0;
                        float industry = 0;
                        float service = 0;
                        float agriculture = 0;
                        Country country = incountryMap.get(key);
                         population = Integer.parseInt(columns[3]);
                         gdp = Float.parseFloat(columns[9]);
                         countryKey = columns[1].trim();
                         if(columns.length>19) {
                             industry = Float.parseFloat((columns[19]));
                             service = Float.parseFloat(columns[20]);
                             agriculture = Float.parseFloat(columns[18]);
                             country.setData(agriculture, industry, service, gdp, population,countryKey);
                         }
                    }else{

                        Country country = new Country(key, 0, 0);
                        country.setData((float)0.33, (float)0.33, (float)0.33, Float.parseFloat(columns[8]), Integer.parseInt(columns[2]),columns[1]);
                        incountryMap.put(key,country);
                    }



                } catch (NumberFormatException e) {


                }
            }

        } catch (IOException e) {

        }
        return incountryMap;
    }
    private static List<Airport> setAirportCountries(List<Airport> airports, Map<String, Country> countryMap){

        return airports;






    }

        private static void readAirports(int treshhold) {


    }
    public static List<Airport> loadAirports(List<Airport> airports){
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\wayne\\IdeaProjects\\projectname\\src\\main\\resources\\Airports_Passsengers - AV_AIRPORTS (3).csv")));
            String line = br.readLine();
            if (!line.equals("AP_ICAO,AP_Name,AP_IATA,AP_Country,AP_LatLong,AP_Municipality,AP_Type,AP_AltitudeFt,AP_RunwayLenM,AP_Passengers,AP_MoreInfoLink,AP_URLHome")) {
                System.out.println("wrong file");
                System.exit(1);
            }
            while ((line = br.readLine()) != null) {


                String[] columns = line.split(",");

                try {

                    airports.add(new Airport(
                            columns[0], // icac
                            columns[1], // name
                            columns[2], // iata
                            columns[3], // country
                            columns[4], // lat
                            columns[5], // lon
                            columns[6], // municpal
                            columns[7], // type
                            Integer.parseInt(columns[8]),
                            Integer.parseInt(columns[9]),
                            Integer.parseInt(columns[10]),
                            columns[11], // homelink
                            columns[12]
                    ));
                } catch (NumberFormatException e) {
                    // System.out.println("error at " + columns[1]);

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return airports;
    }
    public static BufferedImage loadBufferedImage(BufferedImage img){
        try {
            img = ImageIO.read(new File("C:\\Users\\wayne\\IdeaProjects\\projectname\\src\\main\\resources\\klcpmgdsilh11.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {


            File outputfile = new File("C:\\Users\\wayne\\IdeaProjects\\projectname\\src\\main\\resources\\tempmap1");
            ImageIO.write(img, "png", outputfile);
        } catch (IOException ex) {

        }
        return img;

    }
    public static Map<String,String> setCountryCodes(Map<String,Country> countryMap, Map<String,String> countryCodes) {

        for (Map.Entry<String,Country> entry : countryMap.entrySet()) {
            Country c = countryMap.get(entry.getKey());
            String realName = c.getName();
            countryCodes.put(c.getKey(),realName);
        }
        return countryCodes;
    }


}
