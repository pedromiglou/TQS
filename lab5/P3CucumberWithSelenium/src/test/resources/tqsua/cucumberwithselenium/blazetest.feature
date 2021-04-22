Feature: Obtain a flight
 
  Scenario: Obtain a flight from Paris to London
  	When I navigate to 'https://blazedemo.com/'
  	And I select origin Paris
  	And I select destination London
  	And I click on "Find Flights"
  	Then I should be shown the title 'Flights from Paris to London:'
  	When I input the name 'Pedro Amaral'
  	And input the credit card number 123456789
  	And I choose the flight from United Airlines
  	Then I should see the title 'Thank you for your purchase today!'
