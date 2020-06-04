/*
    File: GUI.java
    Developer: Guerra Vargas Irving Cristobal
    email: guerravargasirving@gmail.com
*/


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class GUI extends JFrame {
    Parser parser;
   
    JTextArea codeArea;
    JScrollPane codeScroll;
    JPanelDibujo drawArea;
    JButton trazar,limpiar,limpiarDibujo;
    
    public GUI(){
        
        super("COMPILADORES - Proyecto LOGO");
       
        parser = new Parser();
        parser.symbolInst();

        drawArea = new JPanelDibujo();
        drawArea.setBounds(520,10,650,600);
        drawArea.setBackground(new Color(255,255,255));
        add(drawArea);
        
        codeArea = new JTextArea(20,20);
        codeArea.setBackground(Color.BLACK);
        codeArea.setFont(new Font("Arial", Font.PLAIN, 20));
        codeArea.setForeground(Color.GREEN);
        codeArea.setLineWrap(true);
        codeArea.setWrapStyleWord(true);
        codeArea.setTabSize(4);
        codeScroll = new JScrollPane (codeArea);
        codeScroll.setBounds(10,10,500,400);
        add(codeScroll);
        
        trazar = new JButton("Dibujar");
        trazar.setBounds(10,420,500,40);
        trazar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                if(parser.compilar(codeArea.getText()))
                    drawArea.setCurrentState(parser.ejecutar());
                else{
                    parser = new Parser();
                    parser.symbolInst();
                    drawArea.setCurrentState(parser.getCurrentState());
                }
                drawArea.repaint();
            }
        });
        add(trazar);
       
        limpiar = new JButton("Limpiar Código");
        limpiar.setBounds(10,470,500,40);
        limpiar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                codeArea.setText(null);
            }
        });
        add(limpiar);
        
        limpiarDibujo = new JButton("Limpiar Dibujo");
        limpiarDibujo.setBounds(10,520,500,40);
        limpiarDibujo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser = new Parser();
                parser.symbolInst();
                drawArea.setCurrentState(parser.getCurrentState());
                drawArea.repaint();
            }
        });
        add(limpiarDibujo);
        
        this.setLayout(null);
        this.setBounds(50,50,1180,649);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        
    }
    
}
