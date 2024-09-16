package dz.izemaghilas.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {

    private static final String filePath = "pw.bin";

    public static void init() throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    public static List<String> findPasswords(String email, String websiteUrl) {
        return List.of("pw1", "pw2");
    }

    public static void storePassword(String password, String email, String websiteUrl) {

    }
}
