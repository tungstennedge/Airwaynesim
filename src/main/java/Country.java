class Country{
    String name;
    int year;
    float departures, arrivals, expenditures;
    int population;
    float gpdPerCapita, industryRatio, serviceRatio, agricultureRatio;
    String key;

    public Country(String name, int year, float arrivals) {
        this.name = name;
        this.year = year;
        this.arrivals = arrivals;
        //System.out.println("name+ " + name + "has this many arrivals" + arrivals);
    }
    public void setData(float agr, float indus, float service, float gdp, int inpopulation, String incountryKey){
        //System.out.println("country " + name + "has gdp " + gdp);

        population = inpopulation;
        gpdPerCapita = gdp;
        industryRatio = indus;
        agricultureRatio = agr;
        serviceRatio = service;
        key = incountryKey;
        //System.out.println(name + " has gdppercap of: " + gpdPerCapita);

    }
    public float getGpdPerCapita(){
        return gpdPerCapita;
    }
    public String getKey(){
        return key;
    }
    public String getName(){
        return name;
    }
    public float getTourismExpenditure(){
        return expenditures;
    }
    public int getPopulation(){
        return population;
    }
    public float getArrivals(){
        return arrivals;
    }
    public float getDepartures(){
        return departures;
    }
}