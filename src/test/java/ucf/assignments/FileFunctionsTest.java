/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Ivy Nguyen
 */
package ucf.assignments;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileFunctionsTest {

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