package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileFunctions {
    private ObservableList<Item> itemObservableList;

    public void setItemObservableList(ObservableList<Item> itemObservableList){
        this.itemObservableList = itemObservableList;
    }

    public ObservableList<Item> getItemObservableList(){
        return itemObservableList ;
    }

    public ArrayList<Item> observableListToArrayList(ObservableList<Item> observableList){
        //copy elements in ObservableList to ArrayList
        return new ArrayList<>(observableList);
    }

    public void storeFileFormatted(String fileType, File selectedFile, ArrayList <Item> itemList ){
        //call corresponding functions for file type

        switch(fileType){
            case ".json" -> {
                //save file as json
                JsonFiles jsonFiles = new JsonFiles();
                jsonFiles.saveFile(itemList, selectedFile);
            }
            case ".txt" -> {
                //save file as tsv
                TsvFiles tsvFiles = new TsvFiles();
                tsvFiles.saveFile(itemList,selectedFile);
            }
            default ->{
                //save file as html
                HtmlFiles htmlFiles = new HtmlFiles();
                htmlFiles.saveFile(itemList,selectedFile);
            }
        }
    }

    public void readFileFormatted(String fileType, File selectedFile){
        //clear whatever was on the list
        itemObservableList.clear();

        List<Item> itemList = new ArrayList<>();
        //call corresponding functions for file type
        switch(fileType){
            case ".json" -> {
                //load file
                JsonFiles jsonFiles = new JsonFiles();
                itemList = jsonFiles.loadFromPrevious(selectedFile);
                //add items from itemList to observable list
                itemObservableList.addAll(itemList);
            }
            case ".txt" -> {
                //load file
                TsvFiles tsvFiles = new TsvFiles();
                itemList = tsvFiles.loadFromPrevious(selectedFile);
                //add items from itemList to observable list
                itemObservableList.addAll(itemList);
            }
            default ->{
                //load file
                HtmlFiles htmlFiles = new HtmlFiles();
                itemList = htmlFiles.loadFromPrevious(selectedFile);
                //add items from itemList to observable list
                itemObservableList.addAll(itemList);
            }
        }
    }

    public File openFile(){
        //create FileChooser
        FileChooser fileChooser = new FileChooser();

        //create stage for fileChooser
        Stage fileStage = new Stage();
        fileStage.setTitle("Open");

        //set default directory to be user's home directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        //add .json, .txt, and .html extensions
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON file (*.json)","*.json"),
                new FileChooser.ExtensionFilter("HTML file (*.html)", "*.html"),
                new FileChooser.ExtensionFilter("TSV file (*.txt)","*.txt"));

        //show the file stage
        //change instance of selected file to selected file in FileChooser
        return fileChooser.showOpenDialog(fileStage);
    }

    public File saveAs(){
        //initialize FileChooser
        FileChooser fileChooser = new FileChooser();

        //set stage for fileChooser
        Stage fileStage = new Stage();
        fileStage.setTitle("Save As");

        //set default directory to be the user's home directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        //add .json, .txt, and .html extensions
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON file (*.json)","*.json"),
                new FileChooser.ExtensionFilter("HTML file (*.html)", "*.html"),
                new FileChooser.ExtensionFilter("TSV file (*.txt)","*.txt"));


        //show the file stage
        //return file chosen
        return fileChooser.showSaveDialog(fileStage);
    }

}
