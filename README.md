# The Kong

## COMP 167 Major Programming Assignment 3

## Due: 2018-11-26 11:59 pm

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
#### 10 points

For this level, you will be working mostly in `MainController.java`. You must instantiate a `GamePane`, `LevelView`, a `PlayAreaView`, `StatusView`, and a `CommandView`.

- PlayAreaView
  - The `PlayAreaView` will be made up of the Sprites and the `LevelView`
  - LevelView
    - The `LevelView` should display all of the ladders and platforms described in the Object configuration file for the first level.
  - Sprites
    - You should add a `HeroView`, `PrincessView`, and `KongView` to the `PlayAreaView` at the locations specified in the Sprite config file.
  - The `PlayAreaView` should be in the center region of the `GamePane`.
- StatusView
  - Declare and instantiate a `StatusView`, this should appear at the top of the `GamePane`.
- CommandView
  - Declare and instantiate a `CommandView`, this should appear at the bottom of the `GamePane`.


### Level 4 - Walking into walls
#### 20 points

Animation is an important part of game development. Create a class called `AnimationController` that extends `AnimationTimer`. The `AnimationController` constructor should take one argument, the `PlayAreaView`. In your `handle()` method, you should call `moveSprites()`.

In your `MainController` class, you should handle Keyboard events. If a right arrow is pressed, the `HeroView` should have a direction of `0.0` and have a speed that makes it move reasonably across the scene. If the left arrow is pressed, the `HeroView` should have a direction of `180.0`. When these arrows are released, the speed should be set back to `0.0`.

Finally, you must check for collisions in the `AnimationController.handle()` method. If the `HeroView` hits either end of the play area, you should stop it so it stays in view.

### Level 5 - Jumping and climbing
#### 20 points

In order to implement jumping and climbing, your `AnimationController` must _first_ know if your hero is on a ladder (this is another collision that should be checked in `handle()`). If the hero is on a ladder, then pressing the up arrow should set the direction to `270.0` and the same speed as walking left and right. If the hero is on a ladder and the down arrow is pressed, set the direction to `90.0` and set the speed.

Next, you should implement gravity for your hero. If the hero is not on a ladder and not on a floor (guess what? another collision!), then you should increment the hero's y position by a number (I used 6). This will make the hero fall until he reaches a platform or the floor.

Finally, let's make the hero jump! If the hero _is not on a ladder_ and the up arrow is pressed, then for the next 6 times the handle method is called, move the hero UP (which is decrementing the Y position) by TWICE the value of gravity, so I used 12.

### Level 6 - Bad guys and barrels
#### 20 points

The Kong doesn't need to move for this level, just make sure it is in the `PlayAreaView` at the given starting position.

At a random interval (somewhere between 30 and 80 calls of the `handle()` method), a barrel should appear at the given default barrel starting position. The barrel should also be affected by gravity, like the hero is. Additionally, it should move to the right at a speed of about 12 until it hits the right wall (collision!) and then it should move to the left until it hits the left wall of the PlayArea (collision again!). This should continue until the barrel reaches the bottom, where it should disappear.

If the hero collides with a barrel, show a message informing the user that they have lost, and quit the game.

### Level 7 - Pacing princess and winning
#### 10 points

Princesses don't like being held hostage, go figure. Make the princess pace on the top platform. She should move to one side of the platform, pause for a reasonable amount of time, and then move to the other side. This should continue for the whole game.

If the hero reaches the princess's platform without being struck by a barrel, the user wins! Display a message that the user has won, and then quit the game.

### Extra Credit Opportunities

There are several opportunities for extra credit on this lab. Each of them must be completed on a separate branch, and that branch must include the option number to be graded. Each extra credit option is worth 10 points, each student may earn up to 30 points extra credit on this program.

#### Option 1 - Animate the Hero

When the hero moves, change the ViewPort as appropriate to make it look like the hero is walking and climbing in the direction he is moving.

#### Option 2 - Animate any other Sprite

Animate any other sprite using the ViewPort to make it look like it is moving.

#### Option 3 - Keep score

Develop a scoring algorithm, and make the score in the `StatusView` update as needed. The details of the scoring algorithm are up to you. It would be wise to explain the algorithm in a comment, or in the pull request.

#### Option 4 - Add additional levels

Add the data for additional levels to the ObjectConfig.txt file. When the hero reaches the princess's platform, have the next level show up.

#### Option 5 - Make the command pane functional

Make the "Start" button on the `CommandView` start the animation timer. Additionally, have the "end" button exit the game.
