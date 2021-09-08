/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazefinal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Flávio
 */
public class FileReader {
    private BufferedReader reader;
    private Maze maze;
    private Square[][] matrix;
    private String start;
    private String end;
    
    
    //Read txt File iwth a Buffer to reader
    public FileReader(String filename) {
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            this.readfile();
        } catch (FileNotFoundException e) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    //Create a matrix to see where the walls are at,
    //read each line to a Collector Interface that gathers the input data onto a new list
    //we divide the rows by 2 because we have, to read 2 by 2 in order to make a square
    //we check where the walls are at
    private void readfile(){
        List<String> lines = reader.lines().collect(Collectors.toList());
        
        int rows = (lines.size() / 2) - 1, columns = lines.get(1).toCharArray().length / 2;
        Square[][] matriz = new Square[rows][columns];
        
        for(int i = 1; i < lines.size() - 3; i = i + 2){
            char[] line = lines.get(i).toCharArray();
            
            for(int j = 1; j < line.length - 1; j = j + 2){
                Square a = new Square(String.format("%s,%s", (j/2), (i/2)));
                if(line[j - 1] == '|')
                    a.setWallWest(true);
                if(line[j + 1] == '|')
                    a.setWallEast(true);
                if(lines.get(i-1).charAt(j) == '-')
                    a.setWallNorth(true);
                if(lines.get(i+1).charAt(j) == '-')
                    a.setWallSouth(true);
                
                matriz[i/2][j/2] = a;
            }
        }
        
        maze = new Maze(matriz,splitIntoCoordinates(matriz, lines.get(lines.size() - 2)), splitIntoCoordinates(matriz, lines.get(lines.size() - 1)));
    }
    
    //split the coordinates to see where is the beg and the end
    private Square splitIntoCoordinates(Square[][] matrix, String coordinates){
        String[] coordinatesSplitted = coordinates.split(",");
        return matrix[Integer.parseInt(coordinatesSplitted[0])][Integer.parseInt(coordinatesSplitted[1])];
    }
    
    public Maze getMaze(){
        return this.maze;
    } 
}
