package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InventoryFunctionsTest {


    @Test
    void item_able_to_have_value_represented_in_US_dollars_should_be_in_format (){
        //create inventory object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //check if value given can be converted to US dollars
        boolean actual = inventoryFunctions.validateValue("1000");
        boolean expected = true;

        assertEquals(actual,expected);
    }

    @Test
    void item_able_to_have_value_represented_in_US_dollars2_should_not_be_in_format (){
        //create inventory object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //check if value given can be converted to US dollars
        boolean actual = inventoryFunctions.validateValue("aaaaa");
        boolean expected = false;

        assertEquals(actual,expected);
    }

    @Test
    void item_able_to_have_serial_number_represented_as_10X_with_letters_and_digits_should_be_in_format (){
        //create inventory object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //check is serial number given is in acceptable format
        boolean actual = inventoryFunctions.validateSerialNumberFormat("1234567890");
        boolean expected = true;

        assertEquals(actual, expected);
    }

    @Test
    void item_able_to_have_serial_number_represented_as_10X_with_letters_and_digits2_should_not_be_in_format(){
        //create inventory object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //check is serial number given is in acceptable format
        boolean actual = inventoryFunctions.validateSerialNumberFormat("XX");
        boolean expected = false;

        assertEquals(actual, expected);
    }

    @Test
    void item_able_to_have_unique_serial_number_should_not_be_unique(){
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
    void item_able_to_have_unique_serial_number_should_be_unique(){
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
    void item_able_to_have_name_between_2_and_256_characters_inclusive_should_not_be_in_format(){
        //create inventory object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //check if name is in right format
        boolean actual = inventoryFunctions.validateName("A");
        boolean expected = false;

        assertEquals(actual, expected);
    }

    @Test
    void item_able_to_have_name_between_2_and_256_characters_inclusive_should_be_in_format(){
        //create inventory object
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        //check if name is in right format
        boolean actual = inventoryFunctions.validateName("Alvin");
        boolean expected = true;

        assertEquals(actual, expected);
    }

    @Test
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
    void able_to_show_error_for_duplicate_serial_numbers_when_adding_should_not_add(){
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
        int actual = inventoryFunctions.getAllItems().size();
        int expected = 3;

        assertEquals(actual, expected);
    }

    @Test
    void able_to_remove_existing_item(){
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        Item item1 = new Item("100.00", "1234567890", "Theodore");
        Item item2 = new Item("100.00", "1234567890", "Simon");
        Item item3 = new Item("100.00", "1234567890", "Alvin");
        ArrayList<Item> itemsList = new ArrayList<>();
        itemsList.add(item1);
        itemsList.add(item2);
        itemsList.add(item3);
        inventoryFunctions.setAllItems(itemsList);

        inventoryFunctions.deleteItem(item3);
        int actual = inventoryFunctions.getAllItems().size();
        int expected = 2;

        assertEquals(actual, expected);
    }

    @Test
    void able_to_edit_value_of_existing_item(){
        InventoryFunctions inventoryFunctions = new InventoryFunctions();
        Item item1 = new Item("100.00", "1234567890", "Theodore");

        inventoryFunctions.editValue(item1, "1000000.00");
        String actual = item1.getValue();
        String expected = "$1,000,000.00";

        assertEquals(actual, expected);
    }

    @Test
    void able_to_edit_serial_number_of_existing_item(){
        InventoryFunctions inventoryFunctions = new InventoryFunctions();
        Item item1 = new Item("100.00", "1234567890", "Theodore");

        inventoryFunctions.editSerialNumber(item1, "0000000000", "1234567890");
        String actual = item1.getSerialNumber();
        String expected = "0000000000";

        assertEquals(actual, expected);
    }

    @Test
    void able_to_edit_name_of_existing_item(){
        InventoryFunctions inventoryFunctions = new InventoryFunctions();
        Item item1 = new Item("100.00", "1234567890", "Theodore");

        inventoryFunctions.editName(item1, "Simon");
        String actual = item1.getName();
        String expected = "Simon";

        assertEquals(actual, expected);
    }

    @Test
    void able_to_display_error_when_duplicating_serial_number(){
    }

    @Test
    void able_to_sort_inventory_items_by_value(){
    }

    @Test
    void able_to_sort_inventory_items_by_name(){
    }

    @Test
    void able_to_sort_inventory_items_by_serial_number(){
    }

    @Test
    void able_to_search_inventory_by_name(){
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        Item item1 = new Item("100.00", "1234567891", "Theodore");
        Item item2 = new Item("100.00", "1234567890", "Simon");
        Item item3 = new Item("100.00", "1234567890", "Alvin");
        ArrayList<Item> itemsList = new ArrayList<>();
        itemsList.add(item1);
        itemsList.add(item2);
        itemsList.add(item3);

        inventoryFunctions.setAllItems(itemsList);
        inventoryFunctions.searchByName("Alvin");

        int actual = inventoryFunctions.getFilteredItems().size();
        int expected = 1;

        assertEquals(actual, expected);
    }

    @Test
    void able_to_search_inventory_by_serial_number(){
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        Item item1 = new Item("100.00", "1234567891", "Theodore");
        Item item2 = new Item("100.00", "1234567890", "Simon");
        Item item3 = new Item("100.00", "1234567890", "Alvin");
        ArrayList<Item> itemsList = new ArrayList<>();
        itemsList.add(item1);
        itemsList.add(item2);
        itemsList.add(item3);

        inventoryFunctions.setAllItems(itemsList);
        inventoryFunctions.searchBySerialNumber("1234567890");

        int actual = inventoryFunctions.getFilteredItems().size();
        int expected = 2;

        assertEquals(actual, expected);
    }

    @Test
    void able_to_save_items_to_file_as_JSON(){
    }

    @Test
    void able_to_save_items_to_file_as_HTML(){
    }

    @Test
    void able_to_save_items_to_file_as_TSV(){
    }

    @Test
    void able_to_load_items_from_previous(){
    }
}