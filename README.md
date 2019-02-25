```                                         
                                         
`7MMM.     ,MMF'                         
  MMMb    dPMM                           
  M YM   ,M MM   ,6"Yb.  M"""MMV .gP"Ya  
  M  Mb  M' MM  8)   MM  '  AMV ,M'   Yb 
  M  YM.P'  MM   ,pm9MM    AMV  8M"""""" 
  M  `YM'   MM  8M   MM   AMV  ,YM.    , 
.JML. `'  .JMML.`Moo9^Yo.AMMmmmM `Mbmmd'    Simple
                                         
                                         
                                   
```

A simple maze, described as a text file. Where 'X' 
indicates a wall (blocked passage) and '{space}' indicates a navigable empty space 
or pathway. The Maze 
has a single 'S' start point and a single 'F' Finish.

An example Text file containing a Maze is shown below


```

           North
    
       0.... n Cols

    0  XXXXXXXXXXXXXXX
    .  X             X
    .  X XXXXXXXXXXX X
    .  X XS        X X
    n  X XXXXXXXXX X X
W      X XXXXXXXXX X X    E
e   r  X XXXX      X X    a
s   o  X XXXX XXXX X X    s
t   w  X XXXX XXXX X X    t
    s  X X    XXXXXX X
       X X XXXXXXXXX X
       X X XXXXXXXXX X
       X X         X X
       X XXXXXXXXX   X
       XFXXXXXXXXXXXXX

            South

```
_fig. 1 A simple maze model_

The use cases being resolved here are:

### _As a world famous explorer of Mazes I would like a maze to exist. So that I can explore it._ ###

#### Acceptance Criteria

* A Maze (as defined in fig. 1 above ) consists of walls 'X', Empty spaces ' ', one and only one Start point 'S' and one and only one exit 'F'
* After a maze has been created the number of walls and empty spaces should be available to me
* After a maze has been created I should be able to put in a co ordinate and know what exists at that point



### _As a world famous explorer of Mazes I would like to exist in a maze and be able to navigate it. So that I can explore it_ 
###

#### Acceptance Criteria

* Given a maze the explorer should be able to drop in to the Start point (facing north)
* An explorer on a maze must be able to move forward
* An explorer on a maze must be able to turn left and right (changing direction the explorer is facing)
* An explorer on a maze must be able to declare what is in front of them
* An explorer on a maze must be able to declare all movement options from their given location
* An explorer on a maze must be able to report a record of where they have been in an understandable fashion



### Assumptions, terminology and concepts ###
- The Maze is 2D definition as a N x N grid of characters within a rectlinear matrix/grid, 
  composed of 'cells'.
- Navigational semantics are North, East, South and West, as indicated in fig. 1 above. 
- The co-ordinate system will be Col. & Rows. Where 0,0 is Taken to be the  top left 
  corner as seen in Fig.1 above.
- An 'X' indicates a wall. Which is taken to mean a blocked cell location within the matrix. Not 
  the boundary of a rectangular cell.
- the area outside of the maze definition is implicitly blocked (a wall), and can not 
  be navigated.
- An un-navigable 'void' exist around the maze. This is to allow for any path segments 
  that may have been declared at the boundary of the Maze definition.
- Moving Forward is not permitted if doing so will result in breaching the confins 
  of the maze... Not permitted to enter the void.
- Zero based numbering is assumed, for Co-ordinate system. ) 0,0 being top left corner 
  of the maze. 

  

### Acknowledgments ###
- (Banner)[http://patorjk.com/software/taag]
- Too much time spent on an ICL2904 teletype link to Horsted Colledge data center when 
  I was a child.


## Build and Run 
Down load the project code from GitHub
```
git@github.com:gavinJLing/RetroMaze.git
cd RetroMaze
```
Build (Gradle) the .jar
```
./gradlew clean bootJar
```

Execute the Jar - play the game
```
java -jar build/lib/maze-0.0.1-SNAPSHOT.jar
```

## Game play
Enter the single character command code. Except for the '(I)nspect' command where the syntax requires col/row co-ordinates of the cell to inspect. e.g. i:3/3 will provide the 'Start' cell. 
Various stats are shown when you select the '(H)istory' command. Showing the makeup of the maze in blocks and path cells. Where the 'Start' & 'Finish' are taken to be path special cells.

Commands:
```
M    Map display. Shows the Maze Map.
H    History display of the maze, shows visited cells.
Q    Quit the game.
L    Left turn 
R    Right turn
F    Forward from the direction you are facing.
```

Example Gameplay console trace
```
@Pos:3/3. On a path,  facing NORTH,
Cmds: (M)ap, (H)istory of moves and (Q)uit Game.
(L)eft turn, (R)ight turn.
Enter command: 
r

@Pos:3/3. On a path,  facing EAST,
Cmds: (M)ap, (H)istory of moves and (Q)uit Game.
(L)eft turn, (R)ight turn, (F)orward.
Enter command: 
f

@Pos:4/3. On a path,  facing EAST,
Cmds: (M)ap, (H)istory of moves and (Q)uit Game.
(L)eft turn, (R)ight turn, (F)orward.
Enter command: 
f

@Pos:5/3. On a path,  facing EAST,
Cmds: (M)ap, (H)istory of moves and (Q)uit Game.
(L)eft turn, (R)ight turn, (F)orward.
Enter command: 
h

The Maze including users route.
0    XXXXXXXXXXXXXXX
1    X             X
2    X XXXXXXXXXXX X
3    X X...      X X
4    X XXXXXXXXX X X
5    X XXXXXXXXX X X
6    X XXXX      X X
7    X XXXX XXXX X X
8    X XXXX XXXX X X
9    X X    XXXXXX X
10   X X XXXXXXXXX X
11   X X XXXXXXXXX X
12   X X         X X
13   X XXXXXXXXX   X
14   XFXXXXXXXXXXXXX
Maze statistics:
Walls:149, Path:76 (Visited:3, Game moves:3)

@Pos:5/3. On a path,  facing EAST,
Cmds: (M)ap, (H)istory of moves and (Q)uit Game.
(L)eft turn, (R)ight turn, (F)orward.
Enter command: 

```




