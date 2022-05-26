package lib;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.Random;

public class Utils {

    public static void ensureFileExists(String path) throws IOException {
        File myObj = new File(path);
        myObj.createNewFile();
    }

    public static double getRandomNumber(double min, double max ){
        Random r = new Random();
        return min + ( max - min) * r.nextDouble();
    }

}
