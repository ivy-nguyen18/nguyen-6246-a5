package ucf.assignments;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HtmlFiles {
    private final int PAD = 5;
    //read from HTML
    public List<Item> loadFromPrevious(File file){
        List<Item> itemList = new ArrayList<>();
        try{
            //read file into a list of Item
            Path path = Paths.get(String.valueOf(file));
            String content = Files.readString(path);
            System.out.println(content);
            trimToTableContent(content);
        }catch(IOException e){
            e.printStackTrace();
        }

        return itemList;
    }

    private void trimToTableContent(String content){
        content = content.strip();
        content = content.replaceAll("[\\n\\t\\r]", "");
        String temp = content.substring(content.indexOf("<tr>"), content.lastIndexOf("</table>"));
        String headers = temp.substring(temp.indexOf("<tr>"), temp.indexOf("</tr>") + PAD);
        String tableHtml = temp.replace(headers, "");
        tableHtml = tableHtml.replaceAll("<tr>", "");
        tableHtml = tableHtml.replaceAll("</tr>", "\n");
        tableHtml = tableHtml.replaceAll("<td>", "");
        tableHtml = tableHtml.replaceAll("</td>", " ");
        tableHtml = tableHtml.trim();
        String [] tableArray = tableHtml.split("\\s+");

        System.out.println(tableHtml);

    }


    private Item stringArrayAsItem(String [] stringArray){
        return new Item(stringArray[0], stringArray[1], stringArray[2]);
    }

    //convert to HTML
    public void saveFile(ArrayList<Item> allItems, File selectedFile){

        try {
            //write in list of allItems into file
            FileWriter writer = new FileWriter(selectedFile);
            writer.write(formatHTML(allItems));
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String formatHTML(ArrayList<Item> allItems){

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
