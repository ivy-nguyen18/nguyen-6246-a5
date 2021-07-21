package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.File;
import java.math.BigDecimal;

public class InventoryController {

    //create the table
    @FXML private TableView<ItemWrapper> itemTableView = new TableView<>();
    @FXML private TableColumn<ItemWrapper, String> valueColumn;
    @FXML private TableColumn<ItemWrapper, String> serialNumberColumn;
    @FXML private TableColumn<ItemWrapper, String> nameColumn;

    //fx id to add new Items
    @FXML private TextField nameTextField;
    @FXML private TextField serialNumberTextField;
    @FXML private TextField valueTextField;

    //fx id for search menu
    @FXML private SplitMenuButton searchBySplitMenuButton;
    @FXML private TextField searchByTextField;
    @FXML private ObservableList<ItemWrapper> itemObservableList = FXCollections.observableArrayList();
    @FXML private Label itemErrorLabel;

    //fx id for error labels
    @FXML private Label nameErrorLabel;
    @FXML private Label valueErrorLabel;
    @FXML private Label serialNumberErrorLabel;

    private Stage primaryStage;
    private File selectedFile;
    private  InventoryFunctions inventoryFunctions;

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    @FXML
    public void saveClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void saveAsClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void openClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void addItemButtonClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void serialNumberSearchClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void nameSearchClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void searchButtonClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void deleteButtonClicked(ActionEvent actionEvent) {
    }

    public void initialize(){
        //make InventoryFunctions object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();
        //initialize the labels
        initializeLabels();
        //set up the table
        initializeTable();
    }

    private void initializeLabels(){
        //all labels initialize the empty
        itemErrorLabel.setText("");
        nameErrorLabel.setText("");
        valueErrorLabel.setText("");
        serialNumberErrorLabel.setText("");
    }

    private void initializeTable(){
        //initialize columns of table to take in specified instance variables
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //make table editable
        editColumns();

        //set selection model to single
        itemTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //set table to view
        itemTableView.setItems(itemObservableList);
    }

    private void editColumns(){
        //allow table to be editable
        itemTableView.setEditable(true);

        //set all columns to editable
        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        serialNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML public void showErrorPopUp(String errorType){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        switch (errorType) {
            case "serialNumberFormatError" -> {
                errorAlert.setHeaderText("Serial Number Format Error");
                errorAlert.setContentText("The format of serial number entered is incorrect." +
                        "\nSerial number should be in the format XXXXXXXXXX, where X can be either a letter or a digit. Please enter a valid serial number.");
            }
            case "nameFormatError" -> {
                errorAlert.setHeaderText("Name Format Error");
                errorAlert.setContentText("There are too few or too many characters in the name." +
                        "\nA name should be between 2 and 256 characters in length, inclusive. Please enter a valid name.");
            }
            case "valueFormatError" -> {
                errorAlert.setHeaderText("Value Format Error");
                errorAlert.setContentText("The value entered is in an incorrect format." +
                        "\nA value must represent it's monetary value in US dollars. Please enter a valid value.");
            }
            default -> {
                errorAlert.setHeaderText("Serial Number Duplicate Error");
                errorAlert.setContentText("The serial number entered is a duplicate." +
                        "\nPlease enter another serial number.");
            }
        }
        errorAlert.showAndWait();
    }

}
