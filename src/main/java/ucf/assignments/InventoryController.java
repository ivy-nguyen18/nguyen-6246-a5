/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ivy Nguyen
 */
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
import java.util.*;

public class InventoryController {

    //create the table
    @FXML private TableView<Item> itemTableView = new TableView<>();
    @FXML private TableColumn<Item, String> valueColumn;
    @FXML private TableColumn<Item, String> serialNumberColumn;
    @FXML private TableColumn<Item, String> nameColumn;

    //fx id to add new Items
    @FXML private TextField nameTextField;
    @FXML private TextField serialNumberTextField;
    @FXML private TextField valueTextField;

    //fx id for search menu
    @FXML private ComboBox<String> searchByComboBox;
    @FXML private TextField searchByTextField;

    //fx id for error labels
    @FXML private Label nameErrorLabel;
    @FXML private Label valueErrorLabel;
    @FXML private Label serialNumberErrorLabel;

    @FXML private ObservableList<Item> itemObservableList = FXCollections.observableArrayList();

    private Stage primaryStage;
    private File selectedFile;
    private final InventoryFunctions inventoryFunctions = new InventoryFunctions();
    private final Set<String> serialNumSet = new HashSet<>();

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    @FXML
    public void saveClicked(ActionEvent actionEvent) {
        FileFunctions file = new FileFunctions();
        //initialize FileFunctions with object
        file.setItemObservableList(itemObservableList);

        //get the extension of the file and send to appropriate function
        String fileName = selectedFile.toString();
        String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());

        //call saveFile
        ArrayList<Item> itemList = file.observableListToArrayList(itemObservableList);
        file.storeFileFormatted(fileType, selectedFile, itemList);
    }

    @FXML
    public void saveAsClicked(ActionEvent actionEvent) {
        FileFunctions file = new FileFunctions();
        //set item observableList in file functions
        file.setItemObservableList(itemObservableList);

        //set new file
        File checkFile = file.saveAs();
        if(checkFile != null){
            this.selectedFile = checkFile;

            //get the extension of the file and send to appropriate function
            String fileName = selectedFile.toString();
            String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());

            //save the file
            ArrayList<Item> itemList = file.observableListToArrayList(itemObservableList);
            file.storeFileFormatted(fileType, selectedFile, itemList);
        }

    }

    @FXML
    public void openClicked(ActionEvent actionEvent) {
        FileFunctions file = new FileFunctions();
        //set item observableList in file functions
        file.setItemObservableList(itemObservableList);

        //set new file
        File checkFile = file.openFile();
        if(checkFile != null){
            this.selectedFile = checkFile;

            //get the extension of the file and send to appropriate function
            String fileName = selectedFile.toString();
            String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());

            file.readFileFormatted(fileType,selectedFile);

            //set that list to the the current observable list and update table;
            updateTableView(file.getItemObservableList());

            inventoryFunctions.loadPreviousSet(itemObservableList);
            inventoryFunctions.setFilteredItems(inventoryFunctions.observableListToArrayList(itemObservableList));
        }
    }

    @FXML
    public void addItemButtonClicked(ActionEvent actionEvent) {
        //get the input
        String name = nameTextField.getText();
        String value = valueTextField.getText();
        String serialNumber = serialNumberTextField.getText();

        //validate inputs
        if(validateInputs(name, serialNumber, value)){
            initializeLabels();
            inventoryFunctions.addItem(name, serialNumber.toUpperCase(), value);
            updateTableView(inventoryFunctions.getAllItemsObservable());
        }
    }

    @FXML
    public void comboBoxClicked(ActionEvent actionEvent){
        //make the search text field editable now
        searchByTextField.setEditable(true);
    }

    @FXML
    public void searchButtonClicked(ActionEvent actionEvent) {
        //get the choice from ComboBox
        String choice = searchByComboBox.getValue();

        String searchItem = searchByTextField.getText();

        switch(choice){
            case "Name" -> {
                //search by name
                inventoryFunctions.searchByName(searchItem);
                itemTableView.setItems(inventoryFunctions.getFilteredItems());
            }
            case "Serial Number" -> {
                //search by serial number
                inventoryFunctions.searchBySerialNumber(searchItem);
                itemTableView.setItems(inventoryFunctions.getFilteredItems());
            }
            default -> {
                itemTableView.setItems(itemObservableList);
            }
        }
    }

    public void sortValues(){
        Comparator<String> columnComparator =
                (String v1, String v2) -> {
                    String dollars = v1.substring(1);
                    double amount1 = Double.parseDouble(dollars);
                    String dollars2 = v2.substring(1);
                    double amount2 = Double.parseDouble(dollars2);
                    return Double.compare(amount1,amount2);

                };

        valueColumn.setComparator(columnComparator);
    }

    @FXML
    public void deleteButtonClicked(ActionEvent actionEvent) {
        Item selectedItem = itemTableView.getSelectionModel().getSelectedItem();

        inventoryFunctions.deleteItem(selectedItem);
        updateTableView(inventoryFunctions.getAllItemsObservable());
    }

    @FXML
    public void refreshButtonClicked(ActionEvent actionEvent){
        itemTableView.setItems(itemObservableList);
        itemTableView.refresh();

        //reset search field
        searchByComboBox.getSelectionModel().clearSelection();
        searchByComboBox.setValue(null);
        searchByTextField.clear();
        searchByTextField.setEditable(false);

        //reset filtered list
        inventoryFunctions.setFilteredItems(inventoryFunctions.observableListToArrayList(itemObservableList));
    }

    public void initialize(){
        //make InventoryFunctions object
        //InventoryFunctions inventoryFunctions = new InventoryFunctions();
        inventoryFunctions.setItemObservableList(itemObservableList);
        inventoryFunctions.setSerialNumSet(serialNumSet);
        inventoryFunctions.setFilteredItems(inventoryFunctions.observableListToArrayList(itemObservableList));
        //initialize search field
        initializeSearchFields();
        //initialize the labels
        initializeLabels();
        //set up the table
        initializeTable();
        sortValues();
    }


    private void initializeLabels(){
        //all labels initialize the empty
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

    @FXML
    public void editName(TableColumn.CellEditEvent edittedCell){

        //get new changes
        Item itemSelected = itemTableView.getSelectionModel().getSelectedItem();
        String newName = edittedCell.getNewValue().toString();

        //if name is in correct format, update list
        if(inventoryFunctions.validateName(newName)){
            inventoryFunctions.editName(itemSelected, newName);
            updateTableView(inventoryFunctions.getAllItemsObservable());
        }else{
            //else display popup
            showErrorPopUp("nameFormatError");
            itemSelected.setName(edittedCell.getOldValue().toString());
        }
        //update table on any changes
        itemTableView.refresh();
    }

    @FXML
    public void editValue(TableColumn.CellEditEvent edittedCell){

        //get new changes
        Item itemSelected = itemTableView.getSelectionModel().getSelectedItem();
        String newValue = edittedCell.getNewValue().toString();


        //if value is in correct format, update list
        if(inventoryFunctions.validateValue(newValue)){
            inventoryFunctions.editValue(itemSelected, newValue);
            updateTableView(inventoryFunctions.getAllItemsObservable());
        }else{
            //if value is not in correct format, display error
            showErrorPopUp("valueFormatError");
            itemSelected.setName(edittedCell.getOldValue().toString());
        }
        //update table on any changes
        itemTableView.refresh();
    }

    @FXML
    public void editSerialNumber(TableColumn.CellEditEvent edittedCell){
        //get new changes
        Item itemSelected = itemTableView.getSelectionModel().getSelectedItem();
        String newSerialNum = edittedCell.getNewValue().toString();
        String oldSerialNum = edittedCell.getOldValue().toString();

        //if serial number is not a duplicate and is in the correct format, update list
        if(!inventoryFunctions.isDuplicate(newSerialNum) && inventoryFunctions.validateSerialNumberFormat(newSerialNum)){
            inventoryFunctions.editSerialNumber(itemSelected, newSerialNum, oldSerialNum);
            updateTableView(inventoryFunctions.getAllItemsObservable());
        }else{
            //if serial number is not in correct format, display appropriate error
            if(!inventoryFunctions.validateSerialNumberFormat(newSerialNum)){
                showErrorPopUp("serialNumberFormatError");
            }
            //if serial number is a duplicate, display appropriate error
            if(inventoryFunctions.isDuplicate(newSerialNum)){
                showErrorPopUp("duplicateError");
            }
            itemSelected.setSerialNumber(oldSerialNum);
        }
        //update table on any changes
        itemTableView.refresh();
    }

    private void initializeSearchFields(){
        //initialize ComboBox
        initializeComboBox();

        //make search field not editable until comboBox is clicked
        searchByTextField.setEditable(false);
    }


    private void initializeComboBox() {
        searchByComboBox.setPromptText("Search by...");
        searchByComboBox.getItems().add("Name");
        searchByComboBox.getItems().add("Serial Number");
    }

    private boolean validateInputs(String name, String serialNumber, String value){
        inventoryFunctions.setItemObservableList(itemObservableList);
        boolean isValid = true;
        //does name have enough characters
        if(!inventoryFunctions.validateName(name)){
            nameErrorLabel.setText("Name must contain between 2 and 256 characters (inclusive)");
            isValid = false;
        }else{
            nameErrorLabel.setText("");
        }
        //is value entered convertible to US currency
        if(!inventoryFunctions.validateValue(value)){
            valueErrorLabel.setText("Value must contain digits only");
            isValid = false;
        }else{
            valueErrorLabel.setText("");
        }
        //is serial number in correct format
        if(!inventoryFunctions.validateSerialNumberFormat(serialNumber)){
            serialNumberErrorLabel.setText("Serial number format must be in format XXXXXXXXXX, where X is a digit or letter");
            isValid = false;
        }else{
            serialNumberErrorLabel.setText("");
        }
        //is serial number a duplicate (true is duplicate, false is unique)
        if(inventoryFunctions.isDuplicate(serialNumber)){
            serialNumberErrorLabel.setText("Serial number is a duplicate");
            isValid = false;
        }
        return isValid;
    }

    private void updateTableView(ObservableList<Item> allItemsList){
        itemObservableList = allItemsList;

        //set new itemObservableList
        inventoryFunctions.setItemObservableList(itemObservableList);

        itemTableView.setItems(itemObservableList);
    }

    public void showErrorPopUp(String errorType){
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
