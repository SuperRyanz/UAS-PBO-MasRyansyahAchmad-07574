package handlers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.KambingEntity;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KambingDatabase {
    private static final String FILE_PATH = "data/kambing_data.json";

    public static void saveGoatData(List<KambingEntity> goats) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(goats, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<KambingEntity> loadGoatData() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            List<KambingEntity> goats = gson.fromJson(reader, new TypeToken<List<KambingEntity>>(){}.getType());
            if (goats == null) {
                return new ArrayList<>(); // Return an empty list instead of null
            }
            return goats;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
