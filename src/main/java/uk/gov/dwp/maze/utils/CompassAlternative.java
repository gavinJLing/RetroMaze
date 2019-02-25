package uk.gov.dwp.maze.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * An alternative compass using a rotating String of direction pointers. 
 * With far less code 
 * 0:North   1:East   2:South   3: West
 * 
 * By leveraging Apache String Utils I can rotate a simple string left and right.
 * which models a users turn left/ turn right.
 * e.g.
 *   "0123"  ->  "1230" -> "2301" -> "3012" -> "0123" 
 * @author gavinling
 *
 */
@Service
public class CompassAlternative {

	private String directions;
	
	public CompassAlternative() {
        init(Direction.NORTH);
    }
	
	
	
	public void init(Direction direction) {
        switch (direction) {
        case EAST:
            directions = "1230";
            break;
        case SOUTH:
            directions = "2301";
            break;
        case WEST:
            directions = "3012";
            break;
            
        default:
            directions = "0123";
            break;
        }
	}
	
	public Direction getDirectionAhead() {
		return Direction.valueOfIndex(  StringUtils.left(directions, 1) );
		  
	}
	public Direction getDirectionLeft() {
		return Direction.valueOfIndex( StringUtils.right(directions, 1) );
	}
	public Direction getDirectionRight() {
		return Direction.valueOfIndex( StringUtils.mid(directions, 1,1) );
	}
	
	
	public void turnLeft() {
		directions = StringUtils.rotate(directions, 1);
		
	}
	
	public void turnRight() {
		directions = StringUtils.rotate(directions, -1);
		
	}
	
	
}
