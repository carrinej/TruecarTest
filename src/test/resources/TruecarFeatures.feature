Feature: Truecar

  @End
  Scenario: Obtain Certificate for Vehicle
  Given the homepage at "http://autoblog.truecar.com" is displayed
  When the user enters "Lincoln" for the make
  And selects "MKS" for the model
  And enters "90401" for the zip code
  Then the page should load with the correct car model
  And the trim has been updated to "3.5L EcoBoost AWD"
  When the user updates the color to one that costs money
  Then the prices should update to reflect the cost of the color
  When the user adds a new option that costs money
  Then the price should update to reflect the cost of the option
  When the user registers with "XXXXXXX" and "ZZZZZZZ"
  And selects the cheapest dealer price
  Then the dealer and car info should be output