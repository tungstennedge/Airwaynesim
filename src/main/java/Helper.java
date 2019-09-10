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
}
