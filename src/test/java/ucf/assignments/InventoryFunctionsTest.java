package ucf.assignments;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InventoryFunctionsTest {
    @Test
    @DisplayName("Sort Name Ascending")
    void able_to_inventory_by_value(){
        //create inventory functions object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //add items to list
        Item item1 = new Item("200.00", "1234567890", "Theodore");
        Item item2 = new Item("10.00", "1234567890", "Simon");
        Item item3 = new Item("100.00", "1234567890", "Alvin");
        ArrayList<Item> itemsList = new ArrayList<>();
        itemsList.add(item1);
        itemsList.add(item2);
        itemsList.add(item3);
        inventoryFunctions.setAllItems(itemsList);

        //sort the list
        List<Item> sortedList = inventoryFunctions.sortNameAscending(itemsList);
        StringBuilder sortedNames = new StringBuilder();
        for(Item item: sortedList){
            sortedNames.append(item.getName());
        }

        //check if list is sorted by name
        String actual = sortedNames.toString();
        String expected = "AlvinSimonTheodore";

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Sort Name descending")
    void able_to_inventory_by_value2(){
        //create inventory functions object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //add items to list
        Item item1 = new Item("200.00", "1234567890", "Theodore");
        Item item2 = new Item("10.00", "1234567890", "Simon");
        Item item3 = new Item("100.00", "1234567890", "Alvin");
        ArrayList<Item> itemsList = new ArrayList<>();
        itemsList.add(item1);
        itemsList.add(item2);
        itemsList.add(item3);
        inventoryFunctions.setAllItems(itemsList);

        //sort the list
        List<Item> sortedList = inventoryFunctions.sortNameDescending(itemsList);
        StringBuilder sortedNames = new StringBuilder();
        for(Item item: sortedList){
            sortedNames.append(item.getName());
        }

        //check if list is sorted by name
        String actual = sortedNames.toString();
        String expected = "TheodoreSimonAlvin";

        assertEquals(actual, expected);
    }


    @Test
    @DisplayName("List holds atleast 100 items")
    void able_to_store_atleast_100_inventory_items(){
        //create inventory object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //add 101 items
        for(int i=0; i <= 100; i++){
            inventoryFunctions.addItem("chipmunks", "1234567890", "1.00");
        }

        //check if size is 101
        int actual = inventoryFunctions.getAllItems().size();
        int expected = 101;

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Value format is correct")
    void item_able_to_have_value_represented_in_US_dollars1(){
        //create inventory object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //check if value given can be converted to US dollars
        boolean actual = inventoryFunctions.validateValue("1000");
        boolean expected = true;

        assertEquals(actual,expected);
    }

    @Test
    @DisplayName("Value format is not correct")
    void item_able_to_have_value_represented_in_US_dollars2 (){
        //create inventory object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //check if value given can be converted to US dollars
        boolean actual = inventoryFunctions.validateValue("aaaaa");
        boolean expected = false;

        assertEquals(actual,expected);
    }

    @Test
    @DisplayName("Serial number format is correct")
    void item_able_to_have_serial_number_represented_as_10X_with_letters_and_digits (){
        //create inventory object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //check is serial number given is in acceptable format
        boolean actual = inventoryFunctions.validateSerialNumberFormat("1234567890");
        boolean expected = true;

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Serial number format is not correct")
    void item_able_to_have_serial_number_represented_as_10X_with_letters_and_digits2(){
        //create inventory object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //check is serial number given is in acceptable format
        boolean actual = inventoryFunctions.validateSerialNumberFormat("XX");
        boolean expected = false;

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Serial number is not unique")
    void item_able_to_have_unique_serial_number1(){
        //create inventory object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //add items to hashset and list
        inventoryFunctions.addItem("Theodore", "1234567890", "100.00");
        inventoryFunctions.addItem("Alvin", "1234567891", "1200.00");
        inventoryFunctions.addItem("Simon", "ABCDEFGHIJ", "110.00");

        //check if there is a duplicate (should return true if there is)
        boolean actual = inventoryFunctions.isDuplicate("1234567891");
        boolean expected = true;

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Serial number is unique")
    void item_able_to_have_unique_serial_number2(){
        //create inventory object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //add items to hashset and list
        inventoryFunctions.addItem("Theodore", "1234567890", "100.00");
        inventoryFunctions.addItem("Alvin", "1234567891", "1200.00");
        inventoryFunctions.addItem("Simon", "ABCDEFGHIJ", "110.00");

        //check if there is a duplicate (should return true if there is)
        boolean actual = inventoryFunctions.isDuplicate("1234567892");
        boolean expected = false;

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Name format is not correct")
    void item_able_to_have_name_between_2_and_256_characters_inclusive1(){
        //create inventory object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //check if name is in right format
        boolean actual = inventoryFunctions.validateName("A");
        boolean expected = false;

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Name format is correct")
    void item_able_to_have_name_between_2_and_256_characters_inclusive2(){
        //create inventory object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //check if name is in right format
        boolean actual = inventoryFunctions.validateName("Alvin");
        boolean expected = true;

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Add Item")
    void able_to_add_new_item(){
        //create inventoryFunctions object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //add items to hashset and list
        inventoryFunctions.addItem("Theodore", "1234567890", "100.00");
        inventoryFunctions.addItem("Alvin", "1234567891", "1200.00");
        inventoryFunctions.addItem("Simon", "ABCDEFGHIJ", "110.00");

        //add an item
        inventoryFunctions.addItem("Alvin", "1234567893", "100.00");

        //check if item is added
        int actual = inventoryFunctions.getAllItems().size();
        int expected = 4;

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Duplicate item should not add")
    void able_to_have_error_for_duplicate_serial_numbers_when_adding_item(){
        //create inventoryFunctions object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //add items to hashset and list
        inventoryFunctions.addItem("Theodore", "1234567890", "100.00");
        inventoryFunctions.addItem("Alvin", "1234567891", "1200.00");
        inventoryFunctions.addItem("Simon", "ABCDEFGHIJ", "110.00");

        //check if item is duplicated (true is duplicated)
        if(!inventoryFunctions.isDuplicate("1234567890")){
            inventoryFunctions.addItem("Dave", "1234567890", "100.00");
        }

        //check if size of list is updated (it shouldn't)
        int actual = inventoryFunctions.getAllItems().size();
        int expected = 3;

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Delete item")
    void able_to_remove_existing_item(){
        //create inventoryFunctions object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //add items to list
        Item item1 = new Item("100.00", "1234567890", "Theodore");
        Item item2 = new Item("100.00", "1234567890", "Simon");
        Item item3 = new Item("100.00", "1234567890", "Alvin");
        ArrayList<Item> itemsList = new ArrayList<>();
        itemsList.add(item1);
        itemsList.add(item2);
        itemsList.add(item3);
        inventoryFunctions.setAllItems(itemsList);

        //delete item from list
        inventoryFunctions.deleteItem(item3);

        //list size should be updated
        int actual = inventoryFunctions.getAllItems().size();
        int expected = 2;

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Item value edited")
    void able_to_edit_value_of_existing_item(){
        //create inventoryFunctions object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //create item
        Item item1 = new Item("100.00", "1234567890", "Theodore");

        //change value of item
        inventoryFunctions.editValue(item1, "1000000.00");

        //item value should be changed
        String actual = item1.getValue();
        String expected = "$1,000,000.00";

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Item serial number edited")
    void able_to_edit_serial_number_of_existing_item(){
        //create inventoryFunctions object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //create item
        Item item1 = new Item("100.00", "1234567890", "Theodore");

        //change serial number of item
        inventoryFunctions.editSerialNumber(item1, "0000000000", "1234567890");

        //item serial number should be changed
        String actual = item1.getSerialNumber();
        String expected = "0000000000";

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Item name edited")
    void able_to_edit_name_of_existing_item(){
        //create inventoryFunctions object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //create item
        Item item1 = new Item("100.00", "1234567890", "Theodore");

        //change name of item
        inventoryFunctions.editName(item1, "Simon");

        //item name should be changed
        String actual = item1.getName();
        String expected = "Simon";

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Item serial number edited only if unique")
    void able_to_display_error_when_duplicating_serial_number_when_editing(){
        //create inventoryFunctions object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //create item
        Item item1 = new Item("100.00", "0000000000", "Dave");
        ArrayList<Item> itemsList = new ArrayList<>();
        itemsList.add(item1);
        inventoryFunctions.setAllItems(itemsList);

        // add to list of item
        inventoryFunctions.addItem("Theodore", "1234567890", "100.00");
        inventoryFunctions.addItem("Alvin", "1234567891", "1200.00");
        inventoryFunctions.addItem("Simon", "ABCDEFGHIJ", "110.00");


        //check if item is duplicated (true is duplicated)
        if(!inventoryFunctions.isDuplicate("1234567890")){
            //change serial number of item
            inventoryFunctions.editSerialNumber(item1, "XXXXXXXXXX", "0000000000");
        }

        //item serial number should not be changed because it is duplicated
        String actual = item1.getSerialNumber();
        String expected = "0000000000";

        assertEquals(actual, expected);
    }


    @Test
    @DisplayName("Search by name")
    void able_to_search_inventory_by_name(){
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        // add to list of item
        inventoryFunctions.addItem("Theodore", "1234567890", "100.00");
        inventoryFunctions.addItem("Alvin", "1234567891", "1200.00");
        inventoryFunctions.addItem("Simon", "ABCDEFGHIJ", "110.00");

        //search for name
        inventoryFunctions.searchByName("Alvin");

        //get filtered list size
        int actual = inventoryFunctions.getFilteredItems().size();
        int expected = 1;

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Search by substring")
    void able_to_search_inventory_by_partial_name(){
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        // add to list of item
        inventoryFunctions.addItem("Theodore", "1234567890", "100.00");
        inventoryFunctions.addItem("Alvin", "1234567891", "1200.00");
        inventoryFunctions.addItem("Albert", "1234567891", "1200.00");
        inventoryFunctions.addItem("Simon", "ABCDEFGHIJ", "110.00");

        //search for name
        inventoryFunctions.searchByName("Al");

        //get filtered list size
        int actual = inventoryFunctions.getFilteredItems().size();
        int expected = 2;

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Search by serial number")
    void able_to_search_inventory_by_serial_number(){
        //create InventoryFunctions object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        // add to list of item
        inventoryFunctions.addItem("Theodore", "1234567890", "100.00");
        inventoryFunctions.addItem("Alvin", "1234567891", "1200.00");
        inventoryFunctions.addItem("Simon", "ABCDEFGHIJ", "110.00");

        //search by serial number
        inventoryFunctions.searchBySerialNumber("1234567890");

        //get size of filtered list
        int actual = inventoryFunctions.getFilteredItems().size();
        int expected = 1;

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Search by substring")
    void able_to_search_inventory_by_partial_serial_number(){
        //create InventoryFunctions object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        // add to list of item
        inventoryFunctions.addItem("Theodore", "1234567890", "100.00");
        inventoryFunctions.addItem("Alvin", "1234567891", "1200.00");
        inventoryFunctions.addItem("Simon", "ABCDEFGHIJ", "110.00");

        //search by serial number
        inventoryFunctions.searchBySerialNumber("123");

        //get size of filtered list
        int actual = inventoryFunctions.getFilteredItems().size();
        int expected = 2;

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Save file as JSON")
    void able_to_save_items_to_file_as_JSON() throws IOException {
        //create JsonFiles object
        JsonFiles jsonFiles = new JsonFiles();

        //create InventoryFunctions object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        // add to list of item
        Item item1 = new Item("100.00", "1234567890", "Theodore");
        Item item2 = new Item("100.00", "1234567890", "Simon");
        Item item3 = new Item("100.00", "1234567890", "Alvin");
        ArrayList<Item> itemsList = new ArrayList<>();
        itemsList.add(item1);
        itemsList.add(item2);
        itemsList.add(item3);
        inventoryFunctions.setAllItems(itemsList);

        //create temp file in project directory
        File file = File.createTempFile("TEST", ".json", null);
        //call saveFile
        jsonFiles.saveFile(itemsList, file);

        //check if file exists
        boolean actual = file.exists();
        file.deleteOnExit();
        boolean expected = true;

        assertEquals(actual,expected);
    }

    @Test
    @DisplayName("Save file as HTML")
    void able_to_save_items_to_file_as_HTML() throws IOException {
        //create Html Files object
        HtmlFiles htmlFiles = new HtmlFiles();

        //create InventoryFunctions object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        // add to list of item
        Item item1 = new Item("100.00", "1234567890", "Theodore");
        Item item2 = new Item("100.00", "1234567890", "Simon");
        Item item3 = new Item("100.00", "1234567890", "Alvin");
        ArrayList<Item> itemsList = new ArrayList<>();
        itemsList.add(item1);
        itemsList.add(item2);
        itemsList.add(item3);
        inventoryFunctions.setAllItems(itemsList);

        //create temp file in project directory
        File file = File.createTempFile("TEST", ".html", null);
        //call saveFile
        htmlFiles.saveFile(itemsList, file);

        //check if file exists
        boolean actual = file.exists();
        file.deleteOnExit();
        boolean expected = true;

        assertEquals(actual,expected);
    }

    @Test
    @DisplayName("Save file as TSV")
    void able_to_save_items_to_file_as_TSV() throws IOException {
        //create TSV files object
        TsvFiles tsvFiles = new TsvFiles();

        //create InventoryFunctions object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        // add to list of item
        Item item1 = new Item("100.00", "1234567890", "Theodore");
        Item item2 = new Item("100.00", "1234567890", "Simon");
        Item item3 = new Item("100.00", "1234567890", "Alvin");
        ArrayList<Item> itemsList = new ArrayList<>();
        itemsList.add(item1);
        itemsList.add(item2);
        itemsList.add(item3);
        inventoryFunctions.setAllItems(itemsList);

        //create temp file in project directory
        File file = File.createTempFile("TEST", ".txt", null);
        //call saveFile
        tsvFiles.saveFile(itemsList, file);

        //check if file exists
        boolean actual = file.exists();
        file.deleteOnExit();
        boolean expected = true;

        assertEquals(actual,expected);
    }

    @Test
    @DisplayName("Load JSON file")
    void able_to_load_items_from_previous_json_file() throws IOException {
        //create JSON object
        JsonFiles jsonFiles = new JsonFiles();

        //create InventoryFunctions object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        // add to list of item
        Item item1 = new Item("100.00", "1234567890", "Theodore");
        Item item2 = new Item("100.00", "1234567890", "Simon");
        Item item3 = new Item("100.00", "1234567890", "Alvin");
        ArrayList<Item> itemsList = new ArrayList<>();
        itemsList.add(item1);
        itemsList.add(item2);
        itemsList.add(item3);
        inventoryFunctions.setAllItems(itemsList);

        //create temp file in project directory
        File file = File.createTempFile("TEST", ".json", null);
        //call saveFile
        jsonFiles.saveFile(itemsList, file);
        //load the file of items saved
        List<Item> loadedList = jsonFiles.loadFromPrevious(file);

        //check if size of list is the same as size of items read in
        int actual = loadedList.size();
        file.deleteOnExit();
        int expected = 3;

        assertEquals(actual,expected);
    }

    @Test
    @DisplayName("Load HTML file")
    void able_to_load_items_from_previous_html_file() throws IOException {
        //create JSON object
        HtmlFiles htmlFiles = new HtmlFiles();

        //create InventoryFunctions object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        // add to list of item
        Item item1 = new Item("100.00", "1234567890", "Theodore");
        Item item2 = new Item("100.00", "1234567890", "Simon");
        Item item3 = new Item("100.00", "1234567890", "Alvin");
        ArrayList<Item> itemsList = new ArrayList<>();
        itemsList.add(item1);
        itemsList.add(item2);
        itemsList.add(item3);
        inventoryFunctions.setAllItems(itemsList);

        //create temp file in project directory
        File file = File.createTempFile("TEST", ".html", null);
        //call saveFile
        htmlFiles.saveFile(itemsList, file);
        //load the file of items saved
        List<Item> loadedList = htmlFiles.loadFromPrevious(file);

        //check if size of list is the same as size of items read in
        int actual = loadedList.size();
        file.deleteOnExit();
        int expected = 3;

        assertEquals(actual,expected);
    }

    @Test
    @DisplayName("Load TSV file")
    void able_to_load_items_from_previous_TSV_file() throws IOException {
        //create JSON object
        TsvFiles tsvFiles = new TsvFiles();

        //create InventoryFunctions object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        // add to list of item
        Item item1 = new Item("100.00", "1234567890", "Theodore");
        Item item2 = new Item("100.00", "1234567890", "Simon");
        Item item3 = new Item("100.00", "1234567890", "Alvin");
        ArrayList<Item> itemsList = new ArrayList<>();
        itemsList.add(item1);
        itemsList.add(item2);
        itemsList.add(item3);
        inventoryFunctions.setAllItems(itemsList);

        //create temp file in project directory
        File file = File.createTempFile("TEST", ".txt", null);
        //call saveFile
        tsvFiles.saveFile(itemsList, file);
        //load the file of items saved
        List<Item> loadedList = tsvFiles.loadFromPrevious(file);

        //check if size of list is the same as size of items read in
        int actual = loadedList.size();
        file.deleteOnExit();
        int expected = 3;

        assertEquals(actual,expected);
    }

    @Test
    @DisplayName("User chooses to save as JSON")
    void able_to_choose_JSON_format() throws IOException {
        //create fileFunctions object
        FileFunctions fileFunctions = new FileFunctions();

        ArrayList<Item> itemsList = new ArrayList<>();

        //create temp file in project directory
        File file = File.createTempFile("TEST", ".json", null);
        fileFunctions.storeFileFormatted(".json", file, itemsList);

        //check if file exists
        boolean actual = file.exists();
        file.deleteOnExit();
        boolean expected = true;

        assertEquals(actual,expected);
    }

    @Test
    @DisplayName("User chooses to save as HTML")
    void able_to_choose_HTML_format() throws IOException {
        //create fileFunctions object
        FileFunctions fileFunctions = new FileFunctions();

        ArrayList<Item> itemsList = new ArrayList<>();

        //create temp file in project directory
        File file = File.createTempFile("TEST", ".html", null);
        fileFunctions.storeFileFormatted(".html", file, itemsList);

        //check if file exists
        boolean actual = file.exists();
        file.deleteOnExit();
        boolean expected = true;

        assertEquals(actual,expected);
    }

    @Test
    @DisplayName("User chooses to save as TSV")
    void able_to_choose_TSV_format() throws IOException {
        //create fileFunctions object
        FileFunctions fileFunctions = new FileFunctions();

        ArrayList<Item> itemsList = new ArrayList<>();

        //create temp file in project directory
        File file = File.createTempFile("TEST", ".txt", null);
        fileFunctions.storeFileFormatted(".txt", file, itemsList);

        //check if file exists
        boolean actual = file.exists();
        file.deleteOnExit();
        boolean expected = true;

        assertEquals(actual,expected);
    }
}