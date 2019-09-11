import java.util.ArrayList;
import java.util.List;

public class Airport {
    String icac, name, iata, countrykey, latti,longi, municipality, type;
    int altitude, runwayLength, passengerInitial;
    String infolink, homeLink;
    String countryFullName;
    float catchmentRadius;
    float populationInRadius;
    String realNameOfOriginCountry;
    List<String> airportsInRad = new ArrayList<>();

    int popInRad;
    float lat, lon;



    public Airport(String icac, String inname, String iata, String countrykey, String latti, String longi, String municipality, String type, int altitude, int runwayLength, int passengerInitial, String infolink, String homeLink) {
        this.icac = icac;
        name = inname;
        this.iata = iata;
        this.countrykey = countrykey;
        this.latti = latti;
        this.longi = longi;
        this.municipality = municipality;
        this.type = type;
        this.altitude = altitude;
        this.runwayLength = runwayLength;
        this.passengerInitial = passengerInitial;
        this.infolink = infolink;
        this.homeLink = homeLink;
        catchmentRadius = 4+(float)Math.sqrt(passengerInitial)/1000;
        if(catchmentRadius>= 17) {
        catchmentRadius = 17;
        }



    }
    public String getName(){
        return name;
    }
    public String getCountry(){
        return countrykey;
    }
    public int getPAX(){
        return passengerInitial;
    }


    public float getLat(){

            return lat;

    }
    public void setLat() {
        if (latti.charAt(1) == 'N') {
            latti = latti.substring(2);
            System.out.println(latti);
            String tempLat = latti.substring(0, 2);
            String tempLat1 = latti.substring(2);
            lat = Float.parseFloat(tempLat + "." + tempLat1);
        } else if (latti.charAt(1) == 'S') {
            latti = latti.substring(2);
            latti = "-" + latti;
            String tempLat = latti.substring(0, 3);
            String tempLat1 = latti.substring(3);
            lat = Float.parseFloat(tempLat + "." + tempLat1);


        }
    }
    public float getLon(){
        return lon;

    }
    public void setLon(){
        if(longi.charAt(0)=='W'){
            longi = longi.substring(1);
            String tempLon = longi.substring(0,3);
            String tempLon1 = longi.substring(3,longi.length()-1);
            lon = Float.parseFloat(tempLon+"."+tempLon1);
        }else if(longi.charAt(0)=='E'){
            longi = longi.substring(1);
            longi = "-"+longi;
            String tempLon = longi.substring(0,4);
            String tempLon1 = longi.substring(4,longi.length()-1);
            lon = Float.parseFloat(tempLon+"."+tempLon1);

        }

    }
    public void setAirportsInRad(List<Airport> airports) {
        for (Airport a : airports
        ) {

            float alat = a.getLat();
            float alon = a.getLon();
            int rad = a.getCatchmentRadius();
            float range = (float)Helper.getRange(lat,alat,lon,alon);
            //System.out.println("range between " + a.getName() + " and " + name + " is " + range);
            float realRange = (float) range;
            if (range < 1200) {
                realRange = Helper.kmToPixels((float) range, lat);
                if (realRange < rad + catchmentRadius) {
                    System.out.println(range);
                    airportsInRad.add(a.getName());
                }
            }

        }
        System.out.println("airport " + name + " has follwing airports in radius");
        for (String s:airportsInRad
             ) {
            System.out.println(s);
        }
    }





    public void setPopulationInRadius(float pop){

        populationInRadius = pop;
        //System.out.println("population near airport "+ name + " = " + populationInRadius);
    }
    public int getCatchmentRadius(){
        return (int)catchmentRadius;
    }
    public float getLongdidtude(){
        return lon;

    }
    public float getLattitide(){
        return lat;
    }
    public String getCountryKey(){
        return  countrykey;
    }




}
