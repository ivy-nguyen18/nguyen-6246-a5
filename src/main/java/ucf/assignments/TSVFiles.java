package ucf.assignments;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TSVFiles extends FileFunctions{


    private String[] itemAsStringArray(Item item){
        String [] stringArray =  new String[3];
        stringArray[0] = item.getValue();
        stringArray[1] = item.getSerialNumber();
        stringArray[2] = item.getName();
        return stringArray;
    }

    private String concatenateAllItemStrings(ArrayList<Item> allItems){
        StringBuilder builder = new StringBuilder();
        for(Item item: allItems){
            String [] stringArray = itemAsStringArray(item);
            builder.append((String.format("%s\t%s\t%s\n", stringArray[0], stringArray[1], stringArray[2])));
            System.out.println((String.format("%s\t%s\t%s\n", stringArray[0], stringArray[1], stringArray[2])));
        }
        return builder.toString();
    }

    //write to TSV
    public void saveFile(ArrayList<Item> allItems, File selectedFile){

        try {
            //write in list of allItems into file
            FileWriter writer = new FileWriter(selectedFile);
            writer.write(concatenateAllItemStrings(allItems));
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
