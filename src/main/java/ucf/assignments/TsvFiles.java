/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ivy Nguyen
 */
package ucf.assignments;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TsvFiles extends FileFunctions{
    //read from TSV
    public List<Item> loadFromPrevious(File file){
        List<String> lines = new ArrayList<>(Collections.emptyList());
        try{

            //read file into a list of Item
            BufferedReader br = new BufferedReader(new FileReader(file.toString()));
            //skip headers
            br.readLine();
            br.readLine();
            String line = null;
            while((line = br.readLine() )!= null){
                lines.add(line);
            }

        }catch(IOException e){
            e.printStackTrace();
        }

        //return resulting list of items
        return parseStringList(lines);
    }

    private List<Item> parseStringList(List<String> lines){
        //parse using tab
        List<Item> itemList = new ArrayList<>();
        for(String line: lines){
            String [] stringArray = line.split("\t");
            itemList.add(stringArrayAsItem(stringArray));
        }
        return itemList;
    }

    private Item stringArrayAsItem(String [] stringArray){
        //instantiate Item with variables
        return new Item(stringArray[0], stringArray[1], stringArray[2]);
    }

    private String[] itemAsStringArray(Item item){
        //set string array with item variables
        String [] stringArray =  new String[3];
        stringArray[0] = item.getValue();
        stringArray[1] = item.getSerialNumber();
        stringArray[2] = item.getName();
        return stringArray;
    }

    private String concatenateAllItemStrings(ArrayList<Item> allItems){
        //piece together as one big string
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
