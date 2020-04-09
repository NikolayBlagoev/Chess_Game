# Chess_Game
A chess engine that interprets rules given by the user to create a game with custom board, custom rules, piece placement, custom pieces, player count, win conditions, and so on
  
#### Syntax for the console:  
  
board - displays the entire board in its current state  
get [field] - displays information about a piece at a given field  
move [field1] [field2] - moves a piece from field1 to field2 if move is legal  
  
  
All fields are written in the format LetterNumber (so A1, E3, B5, AC42), where the letter part specifies the x-axis of the board and the number part - the y-axis
  
  
  
## Action plan:  
Create the chess engine (with all the move verification and customisability)  
Append ui elements to it  
Allow for multi-player  (also web browser support)  
And finally a 3d fantasy chess client built on unity of jmonkey.   
