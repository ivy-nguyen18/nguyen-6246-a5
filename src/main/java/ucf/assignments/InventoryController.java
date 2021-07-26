/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
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

/**
 * The InventoryController class provides functions that respond to events on the main fxml layout
 */
public class InventoryController {

    /*create the table*/
    @FXML private TableView<Item> itemTableView = new TableView<>();
    @FXML private TableColumn<Item, String> valueColumn;
    @FXML private TableColumn<Item, String> serialNumberColumn;
    @FXML private TableColumn<Item, String> nameColumn;

    /*fx id to add new Items*/
    @FXML private TextField nameTextField;
    @FXML private TextField serialNumberTextField;
    @FXML private TextField valueTextField;

    /*fx id for search menu*/
    @FXML private ComboBox<String> searchByComboBox;
    @FXML private TextField searchByTextField;

    /*fx id for error labels*/
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
        file.setItemObservableList(itemObservableList);

        String fileName = selectedFile.toString();
        String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());

        ArrayList<Item> itemList = file.observableListToArrayList(itemObservableList);
        file.storeFileFormatted(fileType, selectedFile, itemList);
    }

    @FXML
    public void saveAsClicked(ActionEvent actionEvent) {
        FileFunctions file = new FileFunctions();

        file.setItemObservableList(itemObservableList);

        File checkFile = file.saveAs();
        if(checkFile != null){
            this.selectedFile = checkFile;
            String fileName = selectedFile.toString();
            String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());

            ArrayList<Item> itemList = file.observableListToArrayList(itemObservableList);
            file.storeFileFormatted(fileType, selectedFile, itemList);
        }
    }

    @FXML
    public void openClicked(ActionEvent actionEvent) {
        FileFunctions file = new FileFunctions();

        file.setItemObservableList(itemObservableList);
        File checkFile = file.openFile();
        if(checkFile != null){
            this.selectedFile = checkFile;
            String fileName = selectedFile.toString();
            String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());

            file.readFileFormatted(fileType,selectedFile);
            updateTableView(file.getItemObservableList());
            inventoryFunctions.loadPreviousSet(itemObservableList);

            /*set filteredItems to loaded list*/
            inventoryFunctions.setFilteredItems(inventoryFunctions.observableListToArrayList(itemObservableList));
        }
    }

    @FXML
    public void addItemButtonClicked(ActionEvent actionEvent) {
        String name = nameTextField.getText();
        String value = valueTextField.getText();
        String serialNumber = serialNumberTextField.getText().toUpperCase();

        if(validateInputs(name, serialNumber, value)){
            initializeLabels();
            inventoryFunctions.addItem(name, serialNumber.toUpperCase(), value);
            updateTableView(inventoryFunctions.getAllItemsObservable());
        }
    }

    @FXML
    public void comboBoxClicked(ActionEvent actionEvent){
        searchByTextField.setEditable(true);
    }

    @FXML
    public void searchButtonClicked(ActionEvent actionEvent) {
        String choice = searchByComboBox.getValue();
        String searchItem = searchByTextField.getText();

        switch(choice){
            case "Name" -> {
                inventoryFunctions.searchByName(searchItem);
                itemTableView.setItems(inventoryFunctions.getFilteredItems());
            }
            case "Serial Number" -> {
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

        /*reset search field*/
        searchByComboBox.getSelectionModel().clearSelection();
        searchByComboBox.setValue(null);
        searchByTextField.clear();
        searchByTextField.setEditable(false);
        inventoryFunctions.setFilteredItems(inventoryFunctions.observableListToArrayList(itemObservableList));
    }

    public void initialize(){
        inventoryFunctions.setItemObservableList(itemObservableList);
        inventoryFunctions.setSerialNumSet(serialNumSet);
        inventoryFunctions.setFilteredItems(inventoryFunctions.observableListToArrayList(itemObservableList));
        initializeSearchFields();
        initializeLabels();
        initializeTable();
        sortValues();
    }


    private void initializeLabels(){
        nameErrorLabel.setText("");
        valueErrorLabel.setText("");
        serialNumberErrorLabel.setText("");
    }

    private void initializeTable(){
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        editColumns();
        itemTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        itemTableView.setItems(itemObservableList);
    }

    private void editColumns(){
        itemTableView.setEditable(true);
        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        serialNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML
    public void editName(TableColumn.CellEditEvent edittedCell){
        Item itemSelected = itemTableView.getSelectionModel().getSelectedItem();
        String newName = edittedCell.getNewValue().toString();

        if(inventoryFunctions.validateName(newName)){
            inventoryFunctions.editName(itemSelected, newName);
            updateTableView(inventoryFunctions.getAllItemsObservable());
        }else{
            showErrorPopUp("nameFormatError");
            itemSelected.setName(edittedCell.getOldValue().toString());
        }
        itemTableView.refresh();
    }

    @FXML
    public void editValue(TableColumn.CellEditEvent edittedCell){
        Item itemSelected = itemTableView.getSelectionModel().getSelectedItem();
        String newValue = edittedCell.getNewValue().toString();

        if(inventoryFunctions.validateValue(newValue)){
            inventoryFunctions.editValue(itemSelected, newValue);
            updateTableView(inventoryFunctions.getAllItemsObservable());
        }else{
            showErrorPopUp("valueFormatError");
            itemSelected.setName(edittedCell.getOldValue().toString());
        }
        itemTableView.refresh();
    }

    @FXML
    public void editSerialNumber(TableColumn.CellEditEvent edittedCell){
        Item itemSelected = itemTableView.getSelectionModel().getSelectedItem();
        String newSerialNum = edittedCell.getNewValue().toString().toUpperCase();
        String oldSerialNum = edittedCell.getOldValue().toString().toUpperCase();

        if(!inventoryFunctions.isDuplicate(newSerialNum) && inventoryFunctions.validateSerialNumberFormat(newSerialNum)){
            inventoryFunctions.editSerialNumber(itemSelected, newSerialNum, oldSerialNum);
            updateTableView(inventoryFunctions.getAllItemsObservable());
        }else{
            if(!inventoryFunctions.validateSerialNumberFormat(newSerialNum)){
                showErrorPopUp("serialNumberFormatError");
            }
            if(inventoryFunctions.isDuplicate(newSerialNum)){
                showErrorPopUp("duplicateError");
            }
            itemSelected.setSerialNumber(oldSerialNum);
        }
        itemTableView.refresh();
    }

    private void initializeSearchFields(){
        initializeComboBox();
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
        /*does name have enough characters*/
        if(!inventoryFunctions.validateName(name)){
            nameErrorLabel.setText("Name must contain between 2 and 256 characters (inclusive)");
            isValid = false;
        }else{
            nameErrorLabel.setText("");
        }
        /*is value entered convertible to US currency*/
        if(!inventoryFunctions.validateValue(value)){
            valueErrorLabel.setText("Value must contain digits only");
            isValid = false;
        }else{
            valueErrorLabel.setText("");
        }
        /*is serial number in correct format*/
        if(!inventoryFunctions.validateSerialNumberFormat(serialNumber)){
            serialNumberErrorLabel.setText("Serial number format must be in format XXXXXXXXXX, where X is a digit or letter");
            isValid = false;
        }else{
            serialNumberErrorLabel.setText("");
        }
        /*is serial number a duplicate (true is duplicate, false is unique)*/
        if(inventoryFunctions.isDuplicate(serialNumber)){
            serialNumberErrorLabel.setText("Serial number is a duplicate");
            isValid = false;
        }
        return isValid;
    }

    private void updateTableView(ObservableList<Item> allItemsList){
        itemObservableList = allItemsList;
        inventoryFunctions.setItemObservableList(itemObservableList);
        itemTableView.setItems(itemObservableList);
    }

    public void showErrorPopUp(String errorType){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        switch (errorType) {
            case "serialNumberFormatError" -> {
                errorAlert.setHeaderText("Serial Number Format Error");
                errorAlert.setContentText("The format of serial number entered is incorrect." +
                        "\nSerial number should be in the format XXXXXXXXXX, where X can be either a letter or a digit." +
                        " Please enter a valid serial number.");
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
