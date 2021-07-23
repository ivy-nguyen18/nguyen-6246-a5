package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileFunctions {
    private ObservableList<Item> itemObservableList;

    public void setItemObservableList(ObservableList<Item> itemObservableList){
        this.itemObservableList = itemObservableList;
    }


    //store as JSON
    public void storeAsJSON(){

    }

    //store as HTML
    public void storeAsHTML(){

    }

    //store as TSV
    public void storeAsTSV(){

    }

    public File openFile(){
        //create FileChooser
        FileChooser fileChooser = new FileChooser();

        //create stage for fileChooser
        Stage fileStage = new Stage();
        fileStage.setTitle("Open");

        //set default directory to be user's home directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        //make sure file is saved as .ser to be serializable
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON file","*.json"),
                new FileChooser.ExtensionFilter("HTML file", "*.html"),
                new FileChooser.ExtensionFilter("TSV file","*.txt"));

        //show the file stage

        //change instance of selected file to selected file in FileChooser
        return fileChooser.showOpenDialog(fileStage);

    }


}
