package pl.olin44.aviationapp.json_file_reading;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import pl.olin44.aviationapp.exceptions.FileNotFoundException;

import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

@Slf4j
public class JSONReader {
    public JSONReader() {
    }

    public <T> List<T> read(String filePath, Type type) {
        Reader reader = getReader(filePath);
        Gson gson = new Gson();
        return gson.fromJson(reader, type);
    }

    private Reader getReader(String filePath) {
        Reader reader;
        try {
            reader = new InputStreamReader(this.getClass()
                    .getResourceAsStream(filePath));
        } catch (NullPointerException exception) {
            log.error(String.format("File %s not found", filePath));
            throw new FileNotFoundException(String.format("File %s not found", filePath), exception);
        }
        return reader;
    }
}
