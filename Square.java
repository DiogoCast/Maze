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
public class Square {
    
    private String coordinates;
    private boolean wallNorth;
    private boolean wallWest;
    private boolean wallEast;
    private boolean wallSouth;

    public Square(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public boolean isWallNorth() {
        return wallNorth;
    }

    public boolean isWallWest() {
        return wallWest;
    }

    public boolean isWallEast() {
        return wallEast;
    }

    public boolean isWallSouth() {
        return wallSouth;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public void setWallNorth(boolean wallNorth) {
        this.wallNorth = wallNorth;
    }

    public void setWallWest(boolean wallWest) {
        this.wallWest = wallWest;
    }

    public void setWallEast(boolean wallEast) {
        this.wallEast = wallEast;
    }

    public void setWallSouth(boolean wallSouth) {
        this.wallSouth = wallSouth;
    }
    
    public int getX(){
        return Integer.parseInt(coordinates.split(",")[0]);
    }
    
    public int getY(){
        return Integer.parseInt(coordinates.split(",")[1]);
    }

    @Override
    public String toString() {
        
        StringBuilder input1 = new StringBuilder();
        input1.append(coordinates);
        input1 = input1.reverse();
        String singleString = input1.toString();
        return  singleString;
    }
    
}
