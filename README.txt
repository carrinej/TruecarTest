Installation and Run Instructions:

To install this project, simply open your IDE and import this project as a maven project using the pom file. Run a clean
install on the project to download all the needed dependencies.

To run this project and generate a test report, run test. This will kick off the automated tests and create an html
report accessible at /target/cucumber-html-report/index.html.

My Approach:

I decided to use Cucumber,Selenium and Java to complete this challenge. Using Cucumber I create a feature file with one
scenario. Cucumber uses behavior driven development so that people without programming experience can follow along with
the logic of the test scenarios. For each step in the scenario, I've written Java code using Selenium that actually
implements the steps. Selenium allows me to pull elements off a webpage and verify them as well as automatically input
data. Using these features I was able to enter the inputs from the challenge and validate the car information.

Future Enhancements:

There are a few features that can be examined. At the top of the page there is a way to change the make, model, and
location of the car you;re looking for. I would test these fields and make sure that the user always has theability to
go back and change the type of car they'd like to quote or the location. The preferences tab on the left side of the
page shows the options and how much each option costs. I would test to make sure that when an option is selected,
that cost is reflected in the Select Your Preferences tab. When selecting certificates, I would test to make sure that a
user can select more than one certificate and both certificates would be displayed.

Compromises Made:
I firmly believe there's always room for improvements. Currently this feature file has one scenario. With more time I
would break this test case up into several scenarios and make sure that each scenario can access the one driver. With
WebDrivers, waiting for elements to appear is always an issue. Currently I've employed thread.sleeps, but with more time
I would incorporate an explicit wait for the elements.
