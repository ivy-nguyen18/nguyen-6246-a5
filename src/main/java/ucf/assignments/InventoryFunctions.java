/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Ivy Nguyen
 */
package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

/**
 * The InventoryFunctions class provides functions for sorting, editing, adding, deleting,
 * and validating any input from the InventoryController
 */
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

    public void setFilteredItems(ArrayList<Item> filteredItemsArray){
        this.filteredItems = filteredItemsArray;
    }


    public ArrayList<Item> observableListToArrayList(ObservableList<Item> observableList){
        return new ArrayList<>(observableList);
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

    public void loadPreviousSet(ObservableList<Item> itemObservableList){
        serialNumSet.clear();

        for(Item item: itemObservableList){
            serialNumSet.add(item.getSerialNumber());
        }
    }

    public List<Item> sortNameAscending(ArrayList<Item> allItems){
        List<Item> sortList = new ArrayList<>(allItems);

        sortList.sort(Comparator.comparing(Item::getName));
        filteredItems.clear();
        filteredItems.addAll(sortList);

        return sortList;
    }
    public List<Item> sortNameDescending(ArrayList<Item> allItems){
        List<Item> sortList = new ArrayList<>(allItems);

        sortList.sort(Comparator.comparing(Item::getName).reversed());
        filteredItems.clear();
        filteredItems.addAll(sortList);

        return sortList;
    }
    public List<Item> sortValueAscending(ArrayList<Item> allItems){
        List<Item> sortList = new ArrayList<>(allItems);

        sortList.sort(Comparator.comparing(Item::getValueAmount));
        filteredItems.clear();
        filteredItems.addAll(sortList);

        return sortList;
    }
    public List<Item> sortValueDescending(ArrayList<Item> allItems){
        List<Item> sortList = new ArrayList<>(allItems);

        sortList.sort(Comparator.comparing(Item::getValueAmount).reversed());
        filteredItems.clear();
        filteredItems.addAll(sortList);

        return sortList;
    }
    public List<Item> sortSerialNumberAscending(ArrayList<Item> allItems){
        List<Item> sortList = new ArrayList<>(allItems);

        sortList.sort(Comparator.comparing(Item::getSerialNumber));
        filteredItems.clear();
        filteredItems.addAll(sortList);

        return sortList;
    }
    public List<Item> sortSerialNumberDescending(ArrayList<Item> allItems){
        List<Item> sortList = new ArrayList<>(allItems);

        sortList.sort(Comparator.comparing(Item::getSerialNumber).reversed());
        filteredItems.clear();
        filteredItems.addAll(sortList);

        return sortList;
    }

    public void addItem(String name, String serialNumber, String value){
        String newValue = formatCurrency(value);
        Item newItem = new Item(newValue, serialNumber, name);

        serialNumSet.add(serialNumber);
        allItems.add(newItem);
    }

    public void deleteItem(Item selectedItem){
        serialNumSet.remove(selectedItem.getSerialNumber());
        allItems.remove(selectedItem);
        filteredItems.remove(selectedItem);
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
