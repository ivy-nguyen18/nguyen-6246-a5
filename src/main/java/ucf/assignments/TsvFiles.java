/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ivy Nguyen
 */
package ucf.assignments;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The TsvFiles class provides functions for saving and loading to and from txt file
 */
public class TsvFiles extends FileFunctions{

    public List<Item> loadFromPrevious(File file){
        List<String> lines = new ArrayList<>(Collections.emptyList());

        try{
            BufferedReader br = new BufferedReader(new FileReader(file.toString()));

            br.readLine();
            br.readLine();
            String line = null;
            while((line = br.readLine() )!= null){
                lines.add(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return parseStringList(lines);
    }

    private List<Item> parseStringList(List<String> lines){
        List<Item> itemList = new ArrayList<>();

        for(String line: lines){
            String [] stringArray = line.split("\t");

            itemList.add(stringArrayAsItem(stringArray));
        }
        return itemList;
    }

    private Item stringArrayAsItem(String [] stringArray){
        return new Item(stringArray[0], stringArray[1], stringArray[2]);
    }

    private String[] itemAsStringArray(Item item){
        String [] stringArray =  new String[3];

        stringArray[0] = item.getValue();
        stringArray[1] = item.getSerialNumber();
        stringArray[2] = item.getName();
        return stringArray;
    }

    private String concatenateAllItemStrings(ArrayList<Item> allItems){
        StringBuilder builder = new StringBuilder();

        builder = createHeader(builder);
        for(Item item: allItems){
            String [] stringArray = itemAsStringArray(item);
            builder.append((String.format("%s\t%s\t%s\n", stringArray[0], stringArray[1], stringArray[2])));
        }
        return builder.toString();
    }

    private StringBuilder createHeader(StringBuilder builder){
        String header = "Value\tSerial Number\tName\n";
        String dash = "-";

        builder.append(header);
        builder.append(dash.repeat(header.length()+10) + "\n");
        return builder;
    }

    public void saveFile(ArrayList<Item> allItems, File selectedFile){
        try {
            FileWriter writer = new FileWriter(selectedFile);

            writer.write(concatenateAllItemStrings(allItems));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
