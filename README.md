# SalesTaxCalculator
Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical
products that are exempt. Import duty is an additional sales tax
applicable on all imported goods at a rate of 5%, with no exemptions. When I purchase items
I receive a receipt which lists the name of all the items and their price (including tax),
finishing with the total cost of the items,
and the total amounts of sales taxes paid. The rounding rules for sales tax are that for a tax
rate of n%, a shelf price of p contains (np/100 rounded up to the nearest 0.05) amount of
sales tax.

### Instructions:
1. Git clone/download the repo to your machine
2. Open project with your IDE (I used IntelliJ)
3. Run maven>install
4. Once build is succesful, run Main.java to see the desired output

I assumed that in some future scenarios there would be products with a quantity greater than 1 so added logic to deal with this. <br />
The output comes from the PrinterUtil class, which can either print a single receipt, or an array of receipt objects
