package course.ips;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileService {

    public String getFilePath(String[] args) {
        if (args.length != 2) {
            return null;
        }
        String fileName = args[1];
        if (!Files.exists(Paths.get(fileName))) {
            return null;
        };
        return fileName;
    }
}
