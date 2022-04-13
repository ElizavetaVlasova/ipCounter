package course.ips;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Application {

    public static void main(String [] args) {
        FileService fileService = new FileService();
        String filePath = fileService.getFilePath(args);

        if (filePath == null) {
            throw new IllegalArgumentException("File doesn't exist");
        }
        File file = new File(filePath);
        //File file = new File("src/main/resources/test.txt");


        try (Scanner sc = new Scanner(new FileInputStream(file), StandardCharsets.UTF_8)) {
            IpCounter ipCounter = new IpCounter(sc);
            ipCounter.countIps();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
