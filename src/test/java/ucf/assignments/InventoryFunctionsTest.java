package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InventoryFunctionsTest {

    @Test
    void able_to_interact_with_GUI (){
    }

    @Test
    void item_able_to_have_value_represented_in_US_dollars (){
    }

    @Test
    void item_able_to_have_serial_number_represented_as_10X_with_letters_and_digits (){
    }

    @Test
    void item_able_to_have_unique_serial_number(){
    }

    @Test
    void item_able_to_have_name_between_2_and_256_characters_inclusive(){
    }

    @Test
    void able_to_add_new_item(){
        InventoryFunctions inventoryFunctions = new InventoryFunctions();

        ItemWrapper item1 = new ItemWrapper("100.00", "1234567890", "Theodore");
        ItemWrapper item2 = new ItemWrapper("100.00", "1234567890", "Simon");
        ArrayList<ItemWrapper> itemsList = new ArrayList<>();
        itemsList.add(item1);
        itemsList.add(item2);
        inventoryFunctions.setAllItems(itemsList);

        inventoryFunctions.addItem("Alvin", "1234567890", "100.00");
        int actual = inventoryFunctions.getAllItems().size();
        int expected = 3;

        assertEquals(actual, expected);
    }

    @Test
    void able_to_show_error_for_duplicate_serial_numbers(){
    }

    @Test
    void able_to_remove_existing_item(){
    }

    @Test
    void able_to_edit_value_of_existing_item(){
    }

    @Test
    void able_to_edit_serial_number_of_existing_item(){
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
    }

    @Test
    void able_to_search_inventory_by_serial_number(){
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