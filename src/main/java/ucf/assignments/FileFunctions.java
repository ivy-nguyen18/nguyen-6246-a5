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

    public void storeFileFormatted(String fileType, File selectedFile){
        //call corresponding functions for file type
        switch(fileType){
            case ".json" -> {
                //call json function
            }
            case ".txt" -> {
                //call TSV function
            }
            default ->{
                //call HTML function
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
                JsonFiles jsonFiles = new JsonFiles();
                itemList = jsonFiles.loadFromPrevious(selectedFile);

                //add items from itemList to observable list
                itemObservableList.addAll(itemList);
            }
            case ".txt" -> {
                //call TSV function
            }
            default ->{
                //call HTML function
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
        //fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        //add .json, .txt, and .html extensions
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON file","*.json"),
                new FileChooser.ExtensionFilter("HTML file", "*.html"),
                new FileChooser.ExtensionFilter("TSV file","*.txt"));

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
        //fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        //add .json, .txt, and .html extensions
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON file (*.json)","*.json"),
                new FileChooser.ExtensionFilter("HTML file (*.html)", "*.html"),
                new FileChooser.ExtensionFilter("TSV file (*.txt)","*.txt"));

        //show the file stage
        //return file chosen
        return fileChooser.showSaveDialog(fileStage);
    }


}
