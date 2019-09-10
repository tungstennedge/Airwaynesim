import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {

    private static final int BACKGROUND_GREEN = 227;


    public static void main(String[] args) throws IOException {
        BufferedImage bufferedImage = null;
        Map<String, Country> countryMap = new HashMap<>();
        countryMap = Loader.loadCountries(countryMap);
        countryMap = Loader.loadAdditionalCountryData(countryMap);
        List<Airport> airports = new ArrayList<>();
        airports = Loader.loadAirports(airports);
        bufferedImage = Loader.loadBufferedImage(bufferedImage);
        airports = Loader.setAirportCountries(airports, countryMap);
        Map<String,String> countryCodes = new HashMap<>();
        countryCodes = Loader.setCountryCodes(countryMap,countryCodes);
        List<Route> routes = new ArrayList<>();
        Map<Airline, ArrayList<airlineRoute>> routeMap = new HashMap<>();
        Loader.setAirportsInRadius(airports);

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
            int rad = airport.getCatchmentRadius();

            airport.setPopulationInRadius(Loader.calcPopInRadius(airport.getLat(), airport.getLon(), mapHeight, mapWidth, bufferedImage, rad));
        }
         routes = Loader.makeTestRoutes(airports, routes, countryMap,countryCodes);


    }


}
