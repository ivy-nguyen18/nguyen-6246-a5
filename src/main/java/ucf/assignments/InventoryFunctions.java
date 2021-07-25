package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class InventoryFunctions {
    private Set<String> serialNumSet = new HashSet<>();

    private ArrayList<Item> allItems = new ArrayList<>();
    private ArrayList<Item> filteredItems = new ArrayList<>();

    public void setSerialNumSet(Set<String> serialNumSet){
        this.serialNumSet = serialNumSet;
    }

    public void setItemObservableList(ObservableList<Item> observableList){
        this.allItems = new ArrayList<>(observableList);
    }

    public Set<String> updateSerialNumSet() {
        for(Item item: allItems){
            serialNumSet.add(item.getSerialNumber());
        }
        return serialNumSet;
    }


    public ArrayList<Item> getAllItems(){
        return allItems;
    }

    public void setAllItems(ArrayList<Item> allItems){
        this.allItems = allItems;
    }

    public ObservableList<Item> getAllItemsObservable(){
        return FXCollections.observableArrayList(allItems);
    }

    public ObservableList<Item> getFilteredItems(){
        return FXCollections.observableArrayList(filteredItems);
    }

    public void addItem(String name, String serialNumber, String value){
        String newValue = formatCurrency(value);

        Item newItem = new Item(newValue, serialNumber, name);

        //add serial number to hashset
        serialNumSet.add(serialNumber);

        //add item to list
        allItems.add(newItem);
    }

    public void loadPreviousSet(ObservableList<Item> itemObservableList){
        serialNumSet.clear();
        for(Item item: itemObservableList){
            serialNumSet.add(item.getSerialNumber());
        }
    }
    public void deleteItem(Item selectedItem){
        //remove serial number from serial number list
        serialNumSet.remove(selectedItem.getSerialNumber());
        //remove item from list
        allItems.remove(selectedItem);
    }

    public void editName(Item selectedItem, String newName){
        selectedItem.setName(newName);
    }

    public void editValue(Item selectedItem, String newValue){
        newValue = formatCurrency(newValue);
        selectedItem.setValue(newValue);
    }

    public void editSerialNumber(Item selectedItem, String newSerialNum, String oldSerialNum){
        serialNumSet.remove(oldSerialNum);
        serialNumSet.add(newSerialNum);
        selectedItem.setSerialNumber(newSerialNum);
    }

    public void searchByName(String name){
        filteredItems.clear();
        for(Item item: allItems){
            if(item.getName().toLowerCase().contains(name.toLowerCase())){
                filteredItems.add(item);
            }
        }
    }

    public void searchBySerialNumber(String serialNum){
        filteredItems.clear();
        for(Item item: allItems){
            if(item.getSerialNumber().toLowerCase().contains(serialNum.toLowerCase())){
                filteredItems.add(item);
            }
        }
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
