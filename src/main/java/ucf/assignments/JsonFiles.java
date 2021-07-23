package ucf.assignments;

import com.google.gson.Gson;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonFiles extends FileFunctions{
    private ObservableList<Item> itemObservableList;

    //convert to JSON
    //read from JSON
    public List<Item> loadFromPrevious(File file){
        List<Item> itemList = new ArrayList<>();
        try{
            //new GSON object
            Gson gson = new Gson();

            //read file into a list of Item
            Reader reader = Files.newBufferedReader(Paths.get(String.valueOf(file)));
            itemList = Arrays.asList(gson.fromJson(reader, Item[].class));

            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return itemList;
    }

    public void saveFile(ArrayList<Item> allItems, File selectedFile){
        //new Gson object
        Gson gson = new Gson().newBuilder().setPrettyPrinting().create();

        try {
            //write in list of allItems into file
            FileWriter writer = new FileWriter(selectedFile);
            gson.toJson(allItems, writer);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
