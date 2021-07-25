/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ivy Nguyen
 */
package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

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

    public List<Item> sortNameAscending(ArrayList<Item> allItems){
        //sort list
        List<Item> sortList = new ArrayList<>(allItems);
        sortList.sort(Comparator.comparing(Item::getName));

        //add list to filtered item list
        filteredItems.clear();
        filteredItems.addAll(sortList);

        return sortList;
    }
    public List<Item> sortNameDescending(ArrayList<Item> allItems){
        //sort list
        List<Item> sortList = new ArrayList<>(allItems);
        sortList.sort(Comparator.comparing(Item::getName).reversed());

        //add list to filtered item list
        filteredItems.clear();
        filteredItems.addAll(sortList);

        return sortList;
    }
    public List<Item> sortValueAscending(ArrayList<Item> allItems){
        //sort list
        List<Item> sortList = new ArrayList<>(allItems);
        sortList.sort(Comparator.comparing(Item::getValueAmount));

        //add list to filtered item list
        filteredItems.clear();
        filteredItems.addAll(sortList);

        return sortList;
    }
    public List<Item> sortValueDescending(ArrayList<Item> allItems){
        //sort list
        List<Item> sortList = new ArrayList<>(allItems);
        sortList.sort(Comparator.comparing(Item::getValueAmount).reversed());

        //add list to filtered item list
        filteredItems.clear();
        filteredItems.addAll(sortList);

        return sortList;
    }
    public List<Item> sortSerialNumberAscending(ArrayList<Item> allItems){
        //sort list
        List<Item> sortList = new ArrayList<>(allItems);
        sortList.sort(Comparator.comparing(Item::getSerialNumber));

        //add list to filtered item list
        filteredItems.clear();
        filteredItems.addAll(sortList);

        return sortList;
    }
    public List<Item> sortSerialNumberDescending(ArrayList<Item> allItems){
        //sort list
        List<Item> sortList = new ArrayList<>(allItems);
        sortList.sort(Comparator.comparing(Item::getSerialNumber).reversed());

        //add list to filtered item list
        filteredItems.clear();
        filteredItems.addAll(sortList);

        return sortList;
    }

    public void deleteItem(Item selectedItem){
        //remove serial number from serial number list
        serialNumSet.remove(selectedItem.getSerialNumber());
        //remove item from list
        allItems.remove(selectedItem);
        filteredItems.remove(selectedItem);
    }

    public void editName(Item selectedItem, String newName){
        selectedItem.setName(newName);
    }

    public void editValue(Item selectedItem, String newValue){
        //format value as currency and set
        newValue = formatCurrency(newValue);
        selectedItem.setValue(newValue);
    }

    public void editSerialNumber(Item selectedItem, String newSerialNum, String oldSerialNum){
        //replace serial number in set and item setter
        serialNumSet.remove(oldSerialNum);
        serialNumSet.add(newSerialNum);
        selectedItem.setSerialNumber(newSerialNum);
    }

    public void searchByName(String name){
        //clear current filtered list
        filteredItems.clear();
        for(Item item: allItems){
            if(item.getName().toLowerCase().contains(name.toLowerCase())){
                //if item found, add to filtered list
                filteredItems.add(item);
            }
        }
    }

    public void searchBySerialNumber(String serialNum){
        //clear current filtered list
        filteredItems.clear();
        for(Item item: allItems){
            if(item.getSerialNumber().toLowerCase().contains(serialNum.toLowerCase())){
                //if item found, add to list
                filteredItems.add(item);
            }
        }
    }

    private String formatCurrency(String value){
        //format as money
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
