import java.util.Map;

public class Route {
    Airport departureAirport, destinationAirport;
    Airplane airplane;
    Times timeOfDeparture, timeOfArrival;
    int paxNumber;
    String originCountry;
    String arrivalCountry;
    float numBuisness;
    float numBuisnessMustTravel;
    float numEcon;
    float numEconMustTravel;
    float numFirst;
    float numTourists;
    float basePaxNum;
    boolean isDomestic = false;

    Route(Airport indepartureAirport, Airport indestinationAirport, String originCountry, String arrivalCountry, Times t) {
        destinationAirport = indestinationAirport;
        departureAirport = indepartureAirport;
       timeOfArrival = t;
       timeOfDeparture = t;
       this.originCountry = originCountry;
       this.arrivalCountry = arrivalCountry;




    }

    public Route() {
    }

    public void setBasePAXNumber(Map<String,Country> countryMap) {
        //System.out.println(countryMap.get(originCountry).getName());
        if (countryMap.get(originCountry) != null && countryMap.get(arrivalCountry) != null) {
        float populationInRadDeparture = departureAirport.populationInRadius;
        float populationInRadDestination = destinationAirport.populationInRadius;

        float timeModifier = 1;
        if (timeOfArrival.getDayOfWeek() == 1 || timeOfArrival.getDayOfWeek() == 5) {
            timeModifier = (float) 1.3;
        }
        if (timeOfArrival.getDayOfWeek() == 6 || timeOfArrival.getDayOfWeek() == 7) {
            timeModifier = (float) 0.9;
        }
        if (timeOfArrival.getDayOfWeek() == 4) {
            timeModifier = (float) 1.1;
        }

        double lat1 = Math.toRadians(departureAirport.getLattitide());
        double lat2 = Math.toRadians(destinationAirport.getLattitide());
        double long1 = Math.toRadians(departureAirport.getLongdidtude());
        double long2 = Math.toRadians(destinationAirport.getLongdidtude());
        double range = 1.609 * 3963.0 * Math.acos((Math.sin(lat1) * Math.sin(lat2)) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(long2 - long1));

        float gpdOfDepature = countryMap.get(originCountry).getGpdPerCapita();
        float gdpOfArrival = countryMap.get(arrivalCountry).getGpdPerCapita();


            if (countryMap.get(originCountry).getName() == countryMap.get(arrivalCountry).getName()) {
                isDomestic = true;
            }
            float tourismExpenditure = countryMap.get(arrivalCountry).getTourismExpenditure();
            if (tourismExpenditure == 0) {
                tourismExpenditure = gdpOfArrival / 1000 + 1;
            }
            //System.out.println("tourism expend in " + countryMap.get(arrivalCountry).getName() + " " + tourismExpenditure);

            float tourismExpendRatioDeparture = gpdOfDepature*1000/countryMap.get(originCountry).getPopulation()/countryMap.get(originCountry).getGpdPerCapita();
            float tourismExpendRatio = tourismExpenditure*1000 / countryMap.get(arrivalCountry).getPopulation() / countryMap.get(arrivalCountry).getGpdPerCapita();
            float baseTravel = (populationInRadDeparture * gpdOfDepature) + (populationInRadDestination * gdpOfArrival);


            baseTravel *= tourismExpendRatio;
            //System.out.println(tourismExpendRatio);
            if (range == 0) {
                range = 200;
            }
            if (range < 2500) {
                range *= 2500 / range;
            }
            baseTravel /= ((range));
            baseTravel *= timeModifier;
            baseTravel /=10;
            if (isDomestic) {
                baseTravel *= 3;
            }

            basePaxNum = baseTravel;
            System.out.println(("base Travel of : " + departureAirport.getName() + " to " + destinationAirport.getName() + " " + baseTravel));

        }
        }


        public float getPaxNumber () {
            return basePaxNum;
        }




}
