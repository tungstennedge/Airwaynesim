public class Airport {
    String icac, name, iata, countrykey, latti,longi, municipality, type;
    int altitude, runwayLength, passengerInitial;
    String infolink, homeLink;
    String countryFullName;
    float catchmentRadius;
    float populationInRadius;
    String realNameOfOriginCountry;

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
        catchmentRadius = 7+(float)Math.sqrt(passengerInitial)/1000;
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
