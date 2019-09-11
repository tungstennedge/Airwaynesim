import static java.lang.Math.cos;

public class Helper {
    public int degreesToPixel(float degreesLat, float degreesMeasured){
        double kFactor = (1 / (cos(Math.toRadians(degreesLat))));
        double pixels = 24*degreesMeasured/kFactor;
        return  (int)pixels;
    }
    public static int kmToPixels(float km, float lat){
        double kFactor = (1 / (cos(Math.toRadians(lat))));
         int pixels = (int)(km/24/kFactor);
         return pixels;
    }
    public static double getRange(float lat1, float lat2, float long1, float long2){
        lat1 = (float)Math.toRadians(lat1);
        lat2 = (float)Math.toRadians(lat2);
        long1 = (float)Math.toRadians(long1);
        long2 = (float)Math.toRadians(long2);


        double range = 1.609 * 3963.0 * Math.acos((Math.sin(lat1) * Math.sin(lat2)) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(long2 - long1));
        return range;
    }
}
