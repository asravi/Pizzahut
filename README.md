# Pizzahut
This program uses selenium to automate the testing of the pizzahut.com website. The testing is done in the following order which is listed below:
  - A New Chromedriver instance is created
  - The chromedriver instance invokes the pizzahut.com website
  - The Original Stuffed Crust Pizza Hut image is clicked and directs the user to choose one of two options: Delivery or Carryout
  - The user selects Carryout icon option. The zipcode field appears. Zipcode 20878 is entered in the field.
  - The find a store button below the zipcode field is clicked. Then a list of pizza hut places appears and the user selects the first one on the list
  - This then asks the user what toppings they want. They order mushrooms. Then it clicks continue and a prompt appears asking if they want to order more? They decline. 
  - Then they click view order
  - After that on the next page they click the checkout button. Then the Continue as Guest button is clicked. 
  - Finally the My Information page appears and the browser session terminates. 
 
 The program tests to see whether the My Information Title page appears at the end just before checkout. If it does then the test passes in which the console displays "Test Passed". Otherwise it displays "Test Failed". Meanwhile while the browser session is executing the program is also doing some zip code validation testing with various values to determine whether they are valid by checking if they are 5 digits and numerical. If both conditions are satisfied then it is True and the result is displayed on the console. Otherwise it would be False and that would also be displayed on the console. 
  
