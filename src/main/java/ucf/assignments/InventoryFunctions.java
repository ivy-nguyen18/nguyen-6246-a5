package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.converter.BigDecimalStringConverter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class InventoryFunctions {
    private Set<String> serialNumSet = new HashSet<>();
    private ArrayList<ItemWrapper> allItems = new ArrayList<>();

    public void setSerialNumSet(Set<String> serialNumSet){
        this.serialNumSet = serialNumSet;
    }

    public Set<String> getSerialNumSet(){
        return serialNumSet;
    }

    public void setItemObservableList(ObservableList<ItemWrapper> observableList){
        this.allItems = new ArrayList<>(observableList);
    }

    public ArrayList<ItemWrapper> getAllItems(){
        return allItems;
    }

    public void setAllItems(ArrayList<ItemWrapper> allItems){
        this.allItems = allItems;
    }

    public ObservableList<ItemWrapper> getAllItemsObservable(){
        return FXCollections.observableArrayList(allItems);
    }

    public void addItem(String name, String serialNumber, String value){
        String newValue = formatCurrency(value);

        ItemWrapper newItem = new ItemWrapper(newValue, serialNumber, name);

        //add serial number to hashset
        serialNumSet.add(serialNumber);

        //add item to list
        allItems.add(newItem);
    }

    public void deleteItem(ItemWrapper selectedItem){
        //remove serial number from serial number list
        serialNumSet.remove(selectedItem.getSerialNumber());
        //remove item from list
        allItems.remove(selectedItem);
    }

    public void editName(ItemWrapper selectedItem, String newName){
        selectedItem.setName(newName);
    }

    public void editValue(ItemWrapper selectedItem, String newValue){
        newValue = formatCurrency(newValue);
        selectedItem.setValue(newValue);
    }

    public void editSerialNumber(ItemWrapper selectedItem, String newSerialNum, String oldSerialNum){
        serialNumSet.remove(oldSerialNum);
        serialNumSet.add(newSerialNum);
        selectedItem.setSerialNumber(newSerialNum);
    }

    public void searchByName(){

    }

    public void searchByValue(){

    }

    private String formatCurrency(String value){
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
        BigDecimal expectedValue = new BigDecimal(value);
        return n.format(expectedValue);
    }

    public boolean validateName(String inputName){
        return (inputName.length() >= 2 && inputName.length() <= 256);
    }

    public boolean validateSerialNumberFormat(String inputSerialNum){
        return inputSerialNum.matches("^\\p{Alnum}{10}$");
    }

    public boolean isDuplicate(String inputSerialNum){
        return serialNumSet.contains(inputSerialNum);
    }

    public boolean validateValue(String inputValue){
        boolean isValid = true;
        try{
            NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
            BigDecimal expectedValue = new BigDecimal(inputValue);
            String s = n.format(expectedValue);
        }catch(Exception e){
            isValid = false;
        }
        return isValid;
    }
}
