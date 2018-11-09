# The Kong

## COMP 167 Major Programming Assignment 3

## Due: TBA

**READ THESE INSTRUCTIONS THOROUGHLY BEFORE STARTING THE PROJECT**

### Submission

All submissions for MP3 **MUST** be submitted using GitHub Pull Requests. Assignments will not be accepted in any other form. Failure to commit and/or push code is not an excuse for failing to submit. The following resources will help with using GitHub for this project:
 * [GitHub for Major Programs Video](https://www.youtube.com/watch?v=l2bP9JKQkdA)
 * [GitHub for Major Programs Reference Sheet](https://gist.github.com/ccannon94/511115be821a873ae9ec5f4db9cfdda0)

### Introduction

Finally, this semester's project is all coming together! Included in this repository is a NetBeans project called _TheKong_. You should add your code to this project, DO NOT create your own NetBeans project. There will be more classes added after the project is assigned, check BlackBoard periodically for updates.

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

- StatusView
  - `StatusView` has no assigned UML diagram, you may choose to design it as you wish, given the following parameters:
    - `StatusView` must display the active player's name, the current score, the game's high score, and the current level.
    - `StatusView` must extend `Pane`, or a subclass thereof.
    - `StatusView` must have one or more methods that allows the user to update the displayed data.

- CommandView
  - `CommandView` has no UML diagram, it must meet the following parameters:
    - `CommandView` must extend `Pane`, or a subclass thereof.
    - `CommandView` should have 2 `Button` objects, one to start the game and one to exit.
    - The two buttons should be accessible from outside this class, but should be immutable.

- GameView
  - `GameView` will be the root pane for this program, and also has no UML. `GameView` must meet the following paramters:
    - `GameView` should be large enough to fully display the largest level and controls.
    - `GameView` must include a `StatusView` and a `CommandView`, and will display the `PlayAreaView` when the final version of that class is released (there will be a BlackBoard announcement).

### Level 3 - Load the first level
#### 5 points

TBA

### Level 4 - Walking into walls
#### 15 points

TBA

### Level 5 - Jumping and climbing
#### 20 points

TBA

### Level 6 - Bad guys and barrels
#### 20 points

TBA

### Level 7 - Level up!
#### 20 points

TBA
