package models;

import entity.KambingEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.*;

public class KambingModel {
    private final String filePath = "data/kambing_data.json";
    private Gson gson = new Gson();

    public List<KambingEntity> loadGoatData() {
        try {
            Reader reader = new FileReader(filePath);
            List<KambingEntity> goats = gson.fromJson(reader, new TypeToken<List<KambingEntity>>() {}.getType());
            reader.close();
            return goats != null ? goats : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveGoatData(List<KambingEntity> goats) {
        try {
            Writer writer = new FileWriter(filePath);
            gson.toJson(goats, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
