The program has been written in Java on IntelliJ IDEA. This is tested utilizing JUnit 4.7 tools. 

Run using IntelliJ IDEA
=======================
1. Open the project in IntelliJ.
2. Build the project and run "Grocery.java".
3. Test cases are written in *.java file whose names ends with the sub-string "Test". Run each test using JUnit framework.


Run using shell:
================
1. Go to "GroceryPOS/src"
2. Issue "make" to build java files.
3. Run the script "run.sh"
4. Input files are: "GroceryPOS/input/catalog.csv"
                    "GroceryPOS/input/purchasedItems.csv"
5. Output file is : "GroceryPOS/output/receipt.txt"   


Format of Input files:
======================
#catalog.csv: <Item_Id>,<Item_Name>,<Unit>,<Price>,<Discount(%)>,<Buy 'N' Get 1 free>
 description: +Item_Id -    Unique id of an item.
              +Item_Name -  Name of the item.
              +Unit -       Unit of measurement(kg, pound, unit). If this attribute is named as "unit",
                            the item is countable entities, e.g., 4 pens, 6 pencils, etc.
              +Price-       Price per unit of the item.
              +Discount(%)- Percentage of discount on the item.
              +Buy 'N' .. - If a customer purchases 'N' number for items, he/she will get 1 item free.  

#purchasedItems.csv: <Item_Id>,<Amount>
 description: +Item_Id -    Unique id of the item purchased.
              +Amount  -    Amount of items purchased.

#receipt.txt: <Item_Name> <Description of purchase> <Net cost of purchased items>


API:
===
API of the program is documented in "GroceryPOS/API/" folder.
