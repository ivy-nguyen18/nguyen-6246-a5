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

public class HtmlFiles {
    //read from HTML
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

    //convert to HTML
    public void saveFile(ArrayList<Item> allItems, File selectedFile){

        try {
            //write in list of allItems into file
            FileWriter writer = new FileWriter(selectedFile);
            writer.write(formatHTML(selectedFile,allItems));
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String formatHTML(File selectedFile, ArrayList<Item> allItems){

        return ("<!DOCTYPE html>\n"+
                            "<html>\n" +
                            "<head>\n" +
                            "<style>\n" +
                            "table, th, td {\n " +
                                "\tborder: 1px solid black;\n" +
                                "\tborder-collapse: collapse;\n" +
                            "}\n" +
                            "</style>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "<h2>Inventory Manager</h2>\n" +
                            "<table style=\"width:100%\">\n" +
                                "\t<tr>\n" +
                                "\t\t<th>Value</th>\n" +
                                "\t\t<th>Serial Number</th>\n" +
                                "\t\t<th>Name</th>\n" +
                                "\t</tr>\n" +
                            formatHTMLTable(allItems) +
                            "</table>\n" +
                            "</body>\n"+
                            "</html>");
    }

    private String[] itemAsStringArray(Item item){
        //set string array with item variables
        String [] stringArray =  new String[3];
        stringArray[0] = item.getValue();
        stringArray[1] = item.getSerialNumber();
        stringArray[2] = item.getName();
        return stringArray;
    }

    private String formatHTMLTable(ArrayList<Item> allItems){

        //piece together as one big string
        StringBuilder builder = new StringBuilder();
        for(Item item: allItems){
            String [] stringArray = itemAsStringArray(item);
            builder.append("\t<tr>\n");
            builder.append(String.format("\t\t<td>%s</td>\n", stringArray[0]));
            builder.append(String.format("\t\t<td>%s</td>\n", stringArray[1]));
            builder.append(String.format("\t\t<td>%s</td>\n", stringArray[2]));
            builder.append("\t</tr>\n");
        }
        return builder.toString();
    }
}
