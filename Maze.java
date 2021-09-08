/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazefinal;

/**
 *
 * @author Flávio
 */
public class Maze {
    
    private final Square[][] maze;
    private final Square start;
    private final Square end;

    public Maze(Square[][] maze, Square start, Square end) {
        this.maze = maze;
        this.start = start;
        this.end = end;
    }

    public Square[][] getMaze() {
        return maze;
    }

    public Square getStart() {
        return start;
    }

    public Square getEnd() {
        return end;
    }
    
    
}
