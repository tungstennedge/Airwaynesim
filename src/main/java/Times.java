public class Times {
    int hour;
    int minute;
    int dayOfWeek;
    int month;
    int year;
    Times(int inhour, int inminute, int inday, int inMonth, int inyear){
        hour = inhour;
        minute = inminute;
        dayOfWeek = inday;
        month = inMonth;
        year = inyear;
    }
public int getDayOfWeek(){
        return dayOfWeek;
}
public int getTimeDifference(Times b){
        int hours = dayOfWeek-b.getDayOfWeek()*24;
        if(hours<0){
            hours = hours+168;
        }
        hours*=60;
        int hours2 = hour - b.hour;
        if(hour < 0){
            hours2+=24;
        }
        hours2*=60;

        int mins = minute-b.minute;
        if(mins< 0){
            mins+=60;
        }
        return hours+hours2+mins;
}

}
