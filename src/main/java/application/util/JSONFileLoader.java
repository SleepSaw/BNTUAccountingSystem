package application.util;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class JSONFileLoader {
    public JSONObject loadJsonFile(String fileName) throws IOException {
        try (InputStream inputStream = getClass().getResourceAsStream("/files/" + fileName)) {
            if (inputStream == null) {
                throw new IOException("File not found: " + fileName);
            }
            try (Scanner scanner = new Scanner(inputStream).useDelimiter("\\A")) {
                String jsonContent = scanner.hasNext() ? scanner.next() : "";
                return new JSONObject(jsonContent);
            }
        }
     catch (IOException e) {
        throw new RuntimeException(e);
    }
    }

}
