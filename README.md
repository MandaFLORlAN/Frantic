# Frantic Digital Version
This is the attempt to capture the card game Frantic in a digital version.
Deffinitly check out the real game at [Rulefactory](https://rulefactory.ch/)

## Current Build
The easyest way to test it is by open and run it with a IDE.
The current build inclides a version that can only be played in the console and does not include all cards or events.
Missing cards and Events will be addet in the Future.

## Current content
(May not be complete due to acctuality of this file)
- Standard cards
- Black Cards
- Fantastic

## For Coders, the idea behind the files
### Game
serves as the controll that everithing is in order and legal.
Keeps a record of all players cards without ever directly talking to them.
### Player
Is the interface of every input needed from a participant in the game.
This can be a Bot (RandomBot) or a interface to get input from a person (humanPlayer)
### Connector
Handles communication between the Player and the Game as well as special Cards.
Mainly serves so that if the interface changes to a possible grafical interface, the cards and Game remain the same.
Due to the Idea to add a web version the Player may only communicate by Strings or booleans.
### Special Card
This is the basis for all special Cards. Defines that Special cards can be executed.
