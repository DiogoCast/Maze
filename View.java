/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazefinal;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Flávio
 */
public class View extends JFrame{
    

    public View() {
        
      //paintComponents
      JPanel heading = new JPanel();
      JLabel title = new JLabel("Maze Solver");
      JButton dfsMaze = new JButton("Solve Maze DFS"); 
      JButton bfsMaze = new JButton("Solve Maze BFS");
      JButton exit = new JButton("Exit");
      title.setFont(new Font("Arial Black", Font.PLAIN, 60));
      
      title.setBounds(390,0,500,300);
      dfsMaze.setBounds(470,220,200,50);
      bfsMaze.setBounds(470,300,200,50);
      exit.setBounds(470,380,200,50);
      
      add(title);
      add(dfsMaze);
      add(bfsMaze);
      add(exit);
      
      dfsMaze.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              new DfsWindow();
          }
      });
      
      bfsMaze.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              new BfsWindow();
          }
      });
      
      exit.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              System.exit(0);
          }
      });
        
      //frame
      setTitle("Maze");
      setSize(1200,1200);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLocationRelativeTo(this);
      
      //background
      ImageIcon img = new ImageIcon("maze.jpg");
      
      JLabel background = new JLabel("",img,JLabel.CENTER);
      background.setBounds(0,0,1200,1200);
      add(background);
      
      setVisible(true);
        
    }
    
}
