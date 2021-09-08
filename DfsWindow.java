/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazefinal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Flávio
 */
public class DfsWindow extends JFrame{
    
    
    private final Square[][] maze;
    private final Square startSquare, endSquare ;
    private List <Square> way;
    private Double countTime;
    
    private JList lstRoom;
    private JScrollPane scrlPane;

    public DfsWindow() {
        Maze m = new FileReader("labyrinth.txt").getMaze();
        
        maze = m.getMaze();
        startSquare = m.getStart();
        endSquare = m.getEnd();
        way = new LinkedList<>();
        
        drawElements();
        
        setTitle("DFS Maze Solver");
        setSize(1000, 550);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void drawElements() {
        drawEve();
        add(new JPanel());
    }

    private void drawEve() {
        JLabel dfsAlg = new JLabel("DFS Algorithm");
        dfsAlg.setFont(new Font("Arial Black", Font.PLAIN, 30));
        JLabel nOfSteps = new JLabel("Number of Steps :");
        JTextField steps = new JTextField();
        JLabel time = new JLabel("Time :");
        JTextField timeInS = new JTextField();
        JLabel sec = new JLabel("s");
        JButton solve = new JButton("Solve");
        JButton clear = new JButton("Clear");
        
        dfsAlg.setBounds(580,20,250,100);
        solve.setBounds(645,115,100,35);
        nOfSteps.setBounds(590,167,110,35);
        steps.setBounds(700,175,80,20);
        time.setBounds(655,210,110,35);
        timeInS.setBounds(700,218,80,20);
        sec.setBounds(785,218,80,20);
        clear.setBounds(645,270,100,35);
        
        steps.setEditable(false);
        timeInS.setEditable(false);
        
        add(dfsAlg);
        add(solve);
        add(nOfSteps);
        add(steps);
        add(time);
        add(timeInS);
        add(sec);
        add(clear);
        
        solve.addActionListener((ActionEvent e) -> {
            solveDFS();
            timeInS.setText(String.format("%.5f", countTime));
            steps.setText(String.format("%d", way.size()));
        });
        
        clear.addActionListener((ActionEvent e) -> {
            timeInS.setText("");
            steps.setText("");
            
            if(scrlPane != null)
                remove(scrlPane);
            
            way = new LinkedList<>();
            revalidate();
            repaint();
        });
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.translate(50, 70);
    
        //Paint the path
        if(!way.isEmpty()){
            
            for (int p = 0; p < way.size(); p++) {
                Square r = way.get(p);
                g.setColor(Color.YELLOW);
                g.fillRect(r.getX() * 50, r.getY() * 50, 50, 50);
                drawList();
            }
        }
       
        
        
        //Paint beg and end
        g.setColor(Color.GREEN);
        g.fillRect(startSquare.getX() * 50, startSquare.getY() * 50, 50, 50);
        
        g.setColor(Color.RED);
        g.fillRect(endSquare.getX() * 50, endSquare.getY() * 50, 50, 50);
        
        
        //Paint the maze on the Component
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                Square r = maze[row][col];
                
                if(r.isWallNorth()){
                    g.setColor(Color.BLUE);
                    g.drawRect(50 * col, 50 * row, 50, 0);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                    g.drawRect(50 * col, 50 * row, 50, 0);
                }
                
                if(r.isWallWest()){
                    g.setColor(Color.RED);
                    g.drawRect(50 * col, 50 * row, 0, 50);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                    g.drawRect(50 * col, 50 * row, 0, 50);
                }
                
                if(r.isWallEast()) {
                    g.setColor(Color.YELLOW);
                    g.drawRect(50 * (col + 1), 50 * row, 0, 50);
                }
                
                if (r.isWallSouth()) {
                    g.setColor(Color.GREEN);
                    g.drawRect(50 * col, 50 * (row + 1), 50, 0);
                }
            }
        }
 
    }
    
    private void drawList(){
        String[] list = new String[way.size()];
        int i = 0;
        int j = 0;
        Collections.reverse(way);//Para reverter a lista

            for(Square r : way) {
               
                j++;
                list[i] = j + ". coordinate: " + r.toString();
                i++;
               
            }
        
        if(scrlPane != null)
            remove(scrlPane);
        
        scrlPane = new JScrollPane();
        lstRoom = new JList(list);
        scrlPane.setViewportView(lstRoom);
        add(scrlPane);
        scrlPane.setBounds(500, 335, 400, 100);
        
        scrlPane.validate();
        scrlPane.repaint();
    }

    
    public void solveDFS(){
        way = new LinkedList<>();
        List<Square> knownVertices = new LinkedList<>();
        
        Long startTime = System.nanoTime();
        DFS(maze, startSquare.getX(), startSquare.getY(), endSquare, way, knownVertices);
        Long endTime = System.nanoTime();
        countTime = (double)(endTime - startTime)/1000000000;
        
        revalidate();
        repaint();
    }
    
    public static boolean DFS(Square[][] maze, int x, int y, Square finalSquare, List<Square> way, List<Square> knownVertices) {
        if (maze[y][x] == finalSquare) {
            way.add(maze[y][x]);
            return true;
        }
        
        if (!knownVertices.contains(maze[y][x])) {
            knownVertices.add(maze[y][x]);
            Square r = maze[y][x];
            
            if(!r.isWallNorth() && ((r.getY() - 1) != -1 && (r.getY() - 1) != maze.length)){
                int dx = 0;
                int dy = -1;
                
                if (DFS(maze, x + dx, y + dy, finalSquare, way, knownVertices)) {
                    way.add(maze[y][x]);
                    return true;
                }
            }
            
            if(!r.isWallWest() && ((r.getX() - 1) != -1 && (r.getX() - 1) != maze[0].length)){
                int dx = -1;
                int dy = 0;
                if (DFS(maze, x + dx, y + dy, finalSquare, way, knownVertices)) {
                    way.add(maze[y][x]);
                    return true;
                }
            }
            
            if(!r.isWallSouth() && ((r.getY() + 1) != -1 && (r.getY() + 1) != maze.length)){
                int dx = 0;
                int dy = 1;
                if (DFS(maze, x + dx, y + dy, finalSquare, way, knownVertices)) {
                    way.add(maze[y][x]);
                    return true;
                }
            }

            if(!r.isWallEast() && ((r.getX() + 1) != -1 && (r.getX() + 1) != maze[0].length)){
                int dx = 1;
                int dy = 0;
                if (DFS(maze, x + dx, y + dy, finalSquare, way, knownVertices)) {
                    way.add(maze[y][x]);
                    return true;
                }
            }
        }
        
        return false;
    }
    
}
