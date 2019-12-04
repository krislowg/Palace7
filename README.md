# Palace7 < Resort Arena Palace >

## Built With
* Intellij by JetBrains
* SceneBuilder
* JavaFX

## Authors
Team Tuesday 7. Team name : Legendary Coders.
Team members :
* Shane Broxson
* Iman Essaghir
* Mireya Hernandez Cruz
* Benjamin Kotwall
* Yoisi Low Gonzalez
* Gianni Perez

## Licenses
* GNU General Public License
* IntelliJ IDEA Ultimate

## Acknowledgments
* Dr Koufakou
* Professor Vanselow OOP class and his Youtube videos
* StackOverFlow
* Rashid Coder Youtube videos
* Youtube videos in general 

## Software product timeline

  ## First preview
https://drive.google.com/file/d/1T3Wn6rUYQMldoa9ntdLhChPQp992EJ_0/view?usp=sharing

  ## Second preview
https://drive.google.com/file/d/1PPBPwu7s-430b_km9pkvz8YZ5iRaQLuz/view?usp=sharing

  ## Final preview
https://drive.google.com/file/d/1w8O5FAhZO_VHsSHmzitY_ycLcK0UfBxa/view

## Data Base implementation history 

  ## Data Base access description before the Maven version
The following steps need to be performed everytime in order to connect the database:
* Load the project
* Go to the right side of Intellij window
* You will find a tab called Database
* Click on this tab then click on "H2 Palace7" and remove it
* After that click on the add sign the click on "Data Source from Path"

![database pic2](https://user-images.githubusercontent.com/47893994/68534747-05c97f80-0306-11ea-9ec0-3a5dfc173a16.jpg)

* Click on the path where the project is located and be situated in the "res" folder. For example: C:/Users/hp/IdeaProjects/Palace7/res
* Click OK
* A window will pop up with two fields; Path and Driver
* In Path add /palace to it: C:/Users/hp/IdeaProjects/Palace7/res/palace
* In Driver, choose H2 and click Ok then Click Ok one more time

![database pic3](https://user-images.githubusercontent.com/47893994/68534842-55f51180-0307-11ea-9583-7a29056c975c.jpg)

* Now the database is connected

  ## Data Base access description after the Maven version
The Maven IntelliJ structure has been implemented in order to manage the project dependencies. Those dependencies enable the automatic updates for all the features including the H2 jar for the database. This structure has been specially implemented to avoid the database connection failure some team members had due to the fact that the H2 jar was missing. 
When the project is running, the database connection cannot be established.  Clicking on the database section will result in the pop up of credentials window. In this case, close this window. After stopping the run, clicking on the refresh button under the database section gives access to the data tables. The event reservation and guest tables are under the following path:  
H2 palace ->PALACE -> schemas -> Public -> EVENTRESERVATION; GUEST

## Software Installation and Run Instructions
1.	Have IntelliJ IDEA Ultimate version installed 
2.	Click on VCS then click on Check out from version control then Git
3.	Copy the following URL in the URL entry: https://github.com/krislowg/Palace7
4.	In the bottom right side of your IntelliJ window, a small window related to Maven will pop up, so click on Enable automatically. Likewise, all the dependencies will be updated whenever needed.
5.	In order to run the software, go to the main.java file and click on Run.
6.	NOTE: The database tables are not accessible while the program is running. As long as it is running, if the user clicks on H2 palace the tables will not be displayed and an error window will pop up if the manager logs in to see the report. Those tables will be accessible after stopping the run and refreshing the project.
                                        
## Software description
The software product to be produced is a Resort Booking System which will automate the hotel’s reservation and booking process. The system shall be able to handle rooms’ reservation in an appropriate way and with a subjective satisfaction. It includes 6 subsystems:
1.	Checking Availabilities: The user will be asked to select the check in and check out dates, as well as to enter the number of adults and children willing to reside. 
2.	Room and Amenities Selection:  Before booking a room, the client will be able to see the available types of rooms and amenities provided.
3.	Payment and Creating Account: After completing the booking process, the client will be asked to select a payment method and enter the necessary information. Hence, a user account is created, the room’s status is changed to booked, and a booking confirmation window appears including a reservation summary option. 
4.	Wellness and Events: The customer will be able to check the offered wellness sessions, also the restaurant's menu. Reservations are made by contacting the responsible via a phone number displayed on the screens.
5.	Events Reservation: The system will keep track of the booked events, display the related information as a data table and create a bar chart from those statistics.
6.	Rooms Management:  This will provide a summary of all the rooms that are either booked or vacant to the resort Manager. It will also allow the manager to check if the rooms are being sold out and to handle reservations' cancellation. The guest can also cancel his / her reservation. A data table including all the booking information is displayed as well as a bar chart summarizing the booking system.

## Final Presentation
Please see the lin below for the final presentation of the product:
https://eaglefgcu-my.sharepoint.com/:p:/r/personal/iessaghir3962_eagle_fgcu_edu/_layouts/15/Doc.aspx?sourcedoc=%7B0D603F82-C884-4A6E-B898-506F0DF3C5BC%7D&file=Resort%20Arena%20Palace%20Presentation.pptx&action=edit&mobileredirect=true&cid=c3108286-cd5c-4131-a68c-69c53089f009

