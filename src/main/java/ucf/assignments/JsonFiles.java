/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Ivy Nguyen
 */
package ucf.assignments;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The JsonFiles class provides functions for saving and loading to and from json file.
 */
public class JsonFiles extends FileFunctions{

    public List<Item> loadFromPrevious(File file){
        List<Item> itemList = new ArrayList<>();

        try{
            Gson gson = new Gson();

            Reader reader = Files.newBufferedReader(Paths.get(String.valueOf(file)));
            itemList = Arrays.asList(gson.fromJson(reader, Item[].class));
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return itemList;
    }

    public void saveFile(ArrayList<Item> allItems, File selectedFile){
        Gson gson = new Gson().newBuilder().setPrettyPrinting().create();

        try {
            FileWriter writer = new FileWriter(selectedFile);
            gson.toJson(allItems, writer);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
