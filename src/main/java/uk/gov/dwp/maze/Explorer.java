package uk.gov.dwp.maze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import uk.gov.dwp.maze.model.Cell;
import uk.gov.dwp.maze.model.Maze;
import uk.gov.dwp.maze.model.MazeRenderer;
/**
 * The main class for the Maze solution
 * A maze is created form a map file. Resolved to a list of linked cell locations.
 * 
 * A basic game loop which process user command to navigate a maze. In keeping with
 * hints in the use case and the Maze map file layout. To present a 1980's console/Teletype
 * style game simulation of moving around a maze.
 * 
 * 
 * @author gavinling
 *
 */
@Component
public class Explorer implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Maze maze;
    
    @Autowired
    MazeRenderer mazeRenderer;
    
    private boolean running = false;
      
    

    private void initMaze() {
        maze.load("Maze1.txt");         // Construct a Maze
      

        running = true;                 // start the game loop
    }

    private void renderExplorerOptions( List<String> options) {
        Cell currentLocation = maze.getCurrentLocation();
        
        // @Pos:3/3. The start of the Maze,  facing NORTH,
        String pos =    String.format("@Pos:%s/%s", currentLocation.getColIndex(), currentLocation.getRowIndex());
        String where;
        switch (currentLocation.getCellType()) {
        case START:
            where = "The start of the Maze, ";
        case FINISH:
            where = "The end of the Maze, ";
        case UNKNOWN:
            where = "Your in the void!, ";
        case WALL:
            where = "Your in the wall (creepy!), ";
        default:
            where = "On a path, ";
        }
        String facing = maze.directionAhead();
        
        String cmds= "(M)ap, (H)istory of moves and (Q)uit Game.";
        String nav = "(L)eft turn, (R)ight turn";
        String navOptions = options.contains("F") ? ", (F)orward." : ".";
        message(String.format("%n%s. %s facing %s,%nCmds: %s%n%s%s", pos, where, facing, cmds, nav, navOptions ) );
        
    }



   

   
    
    private void processUserRequest(List<String> options) {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        message("Enter command: ");
        try {
            String cmdLine = br.readLine();
            
            
            String cmd = StringUtils.left(cmdLine,1).toUpperCase();
            
            if( ! options.contains(cmd)) {
                cmd ="";
            }
            
            switch (cmd) {
            case "Q":
                running=false;
                message("Bye. ");
                break;
                
            case "M":
                message(mazeRenderer.showMaze(maze, false));
                break;
                
            case "H":
                message(mazeRenderer.showMaze(maze, true) );
                break;
            
            case "L":
                maze.turnLeft();
                break;
                
            case "R":
                maze.turnRight();
                break;
                
            case "F":
                maze.moveForward();
                if (maze.isFinish()) {
                    message("Well done! You've found the end of the maze. Game over");
                    running = false;
                }
                break;
                
            case "I":
                //expected syntax I:{col}/{row}
                String [] inspectCmdParam = StringUtils.split(cmdLine,":/");
                if (inspectCmdParam.length ==3){
                    try {
                    int col = Integer.parseInt(inspectCmdParam[1]);
                    int row = Integer.parseInt(inspectCmdParam[2]);
                    Cell cell = maze.getCell( col, row);
                    message( String.format("Found '%s' as pos %s/%s ", cell.getCellType(), col, row) );
                    } catch (NumberFormatException nfe) {
                        message(String.format("%nusage:  I:{col}/{row} %n            e.g. i:3/3%n - please try again...%n"));
                    }
                } else {
                    message(String.format("%nInvalid (I)nspect cmd.  Try I:3/10 - please try again...%n"));
                }
                break;
                
            default:
                message(String.format("%nSorry i did not understand that - please try again...%n"));
               
                break;
            }
                       
        } catch (IOException e) {
            message(String.format("%nHmm a problem occured.... quitting.%n"));
            
        }
       
        
    }

    /**
     * Produce output for the user.
     * @param msg
     */
    private void message(String msg) {
       System.out.println(msg);
        
    }

    @Override
    public void run(String... args) throws Exception {
        main(args);
        
    }

    private void main(String[] args) {
       
            initMaze();
                  
            while( running )  {
                
                List<String> options = determineOptions();
                renderExplorerOptions(options);
                
                processUserRequest(options);

            }
     }

    private List<String> determineOptions() {
        List<String> options = new ArrayList<>();
        options.add("Q");   // Quit      End the Maze exploration.
        options.add("H");   // History   Show user navigation path(s) taken.
        options.add("M");   // Map       Reveal the Maze.
        options.add("I");   // Inspect:{col}/{row}  Determine cell at Col/Row location
        options.add("L");   // Left      Turn Left, changes the 'face direction'. 
        options.add("R");   // Right     Turn Right, changes the 'face direction'. 
        
        // can I move forward?
        if ( maze.isForwardAvailable() ) {
            options.add("F");   // Forward   Move the forward is possible. Path, Start or finish
        }
        return options;
    }
        
    
}
