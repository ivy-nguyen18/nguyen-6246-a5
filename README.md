# nguyen-6246-assignment5
### Inventory Manager

## To use this application (Please read carefully before progressing):

* _Add an item:_ In the "Add New Item" section of the window, enter ALL item details and click "Add Item".

    Requirements are as followed:
    * item shall have a value representing its monetary value in US dollars
    * item shall have a unique serial number in the format of XXXXXXXXXX where X can be either a letter or digit
    * item shall have a name between 2 and 256 characters in length (inclusive)
   
   Otherwise, error labels will appear, corresponding to input, and item will not be added. 
   
    **Note**: Adding an item will cause list to resort to default view with all items visible. Also, serial numbers will default to uppercase.

* _Sort list by value, serial number, or name:_ Click on table headers to sort the list.

* _Remove an item:_ At the bottom, there is a "Delete" button, select the item by clicking once and click the "Delete" button.
    
    **Note**: Deleting an item will cause list to resort to default view with all items visible.

* _Edit value of Item:_ In the table, click on item, double click the item's value cell and press Enter to submit the changes. Enter a number only. If the value is not a monetary value, a corresponding error Alert will popup and the changes will not be committed. Table automatically updates. 

* _Edit serial number of Item:_ In the table, click on item, double click the item's serial number cell and press Enter to submit the changes. If the serial number is not in the correct format or is a duplicate, the corresponding error Alert will popup and the changes will not be committed. Also, serial numbers will default to uppercase.
Table automatically updates. 

* _Edit name of Item:_ In the table, click on item, double click the item's name cell and press Enter to submit the changes. If the name is not in the correct format, a corresponding error Alert will popup and the changes will not be committed. Table automatically updates. 

* _To search an item by name:_ At the top of the table is a search bar, the search bar is not typeable until an option on the drop down on the right of the bar is selected. Select "Name". Enter a name to be searched, this also includes parts of a name. 

* _To search an item by serial number:_ At the top of the table is a search bar, the search bar is not typeable until an option on the drop down on the right of the bar is selected. Select "Serial Number". Enter a serial number to be searched, this also includes parts of a serial number. 

* _To save the inventory list and all of its items to a file on user's device:_ Click on "File" in the menu bar at the very top and select "Save As" to create the file. Enter file name and select destination. Note that file can be saved as json, html, or txt. Note: txt is saved as a TSV.

* _To save the inventory list (update list in memory):_ Click on "File" in the menu bar at the very top and select "Save" to save current list.

* _To open and load an inventory list:_ Click on "File" in the menu bar at the very top and select "Open". Select a file (of type json, html, or txt) to open and load. File should automatically be loaded onto current window.
