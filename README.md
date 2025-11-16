# Frantic Digital Version
This is the attempt to capture the card game Frantic in a digital version.
Deffinitly check out the real game at [Rulefactory](https://rulefactory.ch/)

## Current Build
The current Version runs on Java 21 as indicated in the pom.xml
So far everything is purely console based.

## Current content
- All Cards in the Base Game
- All Events from the Base Game
- A Random Bot as well as multiple versions of a logic Bots
- The Capability to do statistics on the different Bots to test Strategys


## For Coders, the idea behind the files
### Game
The two versions act the same no matter what players or type of connector attached.
The Idea is that this files remain the same accros all Versions of the game 
with future graphical versions requiring different connectors.
### Player
Interface based player. Defines what a player has to do in order to work, 
it doesn't matter if it is the Human player from the Console or a bot reacting automatily.
### Connector
Handles communication between the Player and the Game as well as special Cards.
Mainly serves so that if the interface changes to a possible graphical interface, 
the cards and Game remain the same.
Due to the Idea to add a web version the Player may only communicate by Strings or booleans.
