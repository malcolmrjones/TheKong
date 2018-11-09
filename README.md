# The Kong

## COMP 167 Major Programming Assignment 3

## Due: TBA

**READ THESE INSTRUCTIONS THOROUGHLY BEFORE STARTING THE PROJECT**

### Submission

All submissions for MP3 **MUST** be submitted using GitHub Pull Requests. Assignments will not be accepted in any other form. Failure to commit and/or push code is not an excuse for failing to submit. The following resources will help with using GitHub for this project:
 * [GitHub for Major Programs Video](https://www.youtube.com/watch?v=l2bP9JKQkdA)
 * [GitHub for Major Programs Reference Sheet](https://gist.github.com/ccannon94/511115be821a873ae9ec5f4db9cfdda0)

### Introduction

Finally, this semester's project is all coming together! Included in this repository is a NetBeans project called _TheKong_. You should add your code to this project, DO NOT create your own NetBeans project. There will be more classes added after the project is assigned, check BlackBoard for more information.

### Getters/Setters

You will notice there are less get/set methods included in the view classes than in the model classes. Many elements of views will be immutable. You should only implement the get/set methods included in the UML diagrams.

### Level 1 - Logging into the game
#### 10 points

![MainControllerUML](https://github.com/NCATCS/images/blob/master/Fall2018-MP3/MainController.png)

- The `MainController` is in thekongcontroller package.
- `MainController` extends `Application`.
- `MainController` is the main class, and the first thing it should do is read in the 3 provided input files using the preconfigured command-line arguments.
- For level 1, the `MainController` should read the input files, prompt the user to select a profile (by instantiating and using a `LoginController`), and display the selected profile name in the center of the `primaryStage`.

![LoginControllerUML](https://github.com/NCATCS/images/blob/master/Fall2018-MP3/LoginController.png)

- The purpose of the LoginController is to prompt the user to select a `PlayerProfile` and return that selected profile to the `MainController`.
- `getLoginProfile()` should display a `LoginView` which will allow a user to either select or create a `PlayerProfile`. If a new `PlayerProfile` is created, it must be added to the `PlayerProfileCollection` and saved to the config file.
- If no valid `PlayerProfile` is selected, then the `showLoginErrorAndExit()` method should be called. This method should display a JavaFX Alert informing the user of the error, and then close the program.

![LoginViewUML](https://github.com/NCATCS/images/blob/master/Fall2018-MP3/LoginView.png)

- `LoginView` extends `ChoiceDialog<String>`.
- `displayLoginView` should present the custom JavaFX ChoiceDialog to the user including the provided `PlayerProfile` names and the option to create a new profile.
- For hints on using JavaFX Dialogs and Alerts, check out [this website](https://code.makery.ch/blog/javafx-dialogs-official/).

### Level 2 - Complete the game UI
#### 10 points

### Level 3 - Load the first level
#### 5 points

### Level 4 - Walking into walls
#### 15 points

### Level 5 - Jumping and climbing
#### 20 points

### Level 6 - Bad guys and barrels
#### 20 points

### Level 7 - Level up!
#### 20 points
