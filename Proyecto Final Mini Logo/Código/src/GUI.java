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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class GUI extends JFrame {
    Parser parser;
   
    JTextArea codeArea;
    JScrollPane codeScroll;
    JPanelDibujo drawArea;
    JButton trazar,limpiar,limpiarDibujo, b1;
    
    public GUI(){
        
        super("COMPILADORES - Proyecto LOGO");
       
        parser = new Parser();
        parser.symbolInst();

        drawArea = new JPanelDibujo();
        drawArea.setBounds(520,10,650,650);
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
        
        JLabel Basic = new JLabel("Formas Basicas");
        Basic.setBounds(10, 550, 100, 50);
        add(Basic);
       
        //FIGURAS BASICAS
        addBasicShapeSquare();
        addBasicShapeCircle();
        addBasicShapePentagram();
        addBasicShapeTwoPentagram();
        addBasicShapePentagon();
        addBasicShapeHexagon();
        addBasicShapeHexagon7();
        addBasicShapeHexagon8();
        addBasicShapeHexagon9();
        addBasicShapeHexagon10();
        
        JLabel Basic2 = new JLabel("Figuras");
        Basic2.setBounds(10, 630, 100, 50);
        add(Basic2);
        
        //Figuras
        addFigureRegla();
        addFigureEspiral();
        addFigureFlor();
        addFigureStar();
        addFigureKoch();
        addFigureStar2();
        addFigureStar3();
        addFigurePiram();
        addFigureP1();
        addFigureBrain();
        addFigureRose();
        addFigureTree();
        addFigureSpiral();
        addFigureCoinProcedimientos();
        addFigureSquiral();
        addFigureH0();
        addFigureH2();
        addFigureH3();
        addFigureH5();
        
        this.setLayout(null);
        this.setBounds(50,50,1180,750);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        
    }
    
    public void addBasicShapeSquare(){
        b1 = new JButton("1");
        b1.setBounds(10,590,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "AVANZA[-100];\n" +
                        "GIRA[90];\n" +
                        "AVANZA[-100];\n" +
                        "GIRA[-90];\n" +
                        "for(i=0;i<4;i=i+1){\n" +
                        "	COLOR[i*13, i*26, i*51]; \n" +
                        "	AVANZA[100];\n" +
                        "	GIRA[90];\n" +
                        "}");
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
        add(b1);
    }
    
    public void addBasicShapeCircle(){
        b1 = new JButton("2");
        b1.setBounds(60,590,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "procedure circulo(){\n" +
                        "   for(aux=0; aux<360; aux=aux+1){\n" +
                        "      COLOR[$3,$1,$2];\n" +
                        "      AVANZA[2];\n" +
                        "     \n" +
                        "     GIRA[1];\n" +
                        "   }\n" +
                        "}\n" +
                        "circulo(30,32,60);\n" +
                        "GIRA[-80];");
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
        add(b1);
    }
    
    public void addBasicShapePentagram(){
        b1 = new JButton("3");
        b1.setBounds(110,590,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "AVANZA[-150];\n" +
                        "COLOR[100, 250, 300]; \n" +
                        "GIRA[-60];\n" +
                        "for(i=0;i<2;i=i+1){   	  \n" +
                        "	AVANZA[180];\n" +
                        "GIRA[120];	\n" +
                        "}\n" +
                        "AVANZA[180];\n" +
                        "AVANZA[-120];\n" +
                        "COLOR[180, 290, 180];\n" +
                        "GIRA[120];\n" +
                        "AVANZA[120];\n" +
                        "GIRA[-120];\n" +
                        "for(i=0;i<3;i=i+1){   	  \n" +
                        "	AVANZA[180];\n" +
                        "GIRA[-120];	\n" +
                        "}");
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
        add(b1);
    }
    
    public void addBasicShapeTwoPentagram(){
        b1 = new JButton("4");
        b1.setBounds(160,590,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "AVANZA[150];\n" +
                        "for(i=0;i<130;i=i+1){\n" +
                        "	COLOR[i*13, i*26, i*51];\n" +
                        "	GIRA[216];\n" +
                        "	AVANZA[300];\n" +
                        "}\n" +
                        "procedure prueba(){\n" +
                        "	AVANZA[150];\n" +
                        "for(i=0;i<130;i=i+1){\n" +
                        "	COLOR[i*13, i*26, i*51];\n" +
                        "	GIRA[216];\n" +
                        "	AVANZA[300];\n" +
                        "}\n" +
                        "}\n" +
                        "prueba();");
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
        add(b1);
    }
    
    public void addBasicShapePentagon(){
        b1 = new JButton("5");
        b1.setBounds(210,590,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "AVANZA[-100];\n" +
                        "GIRA[90];\n" +
                        "AVANZA[-200];\n" +
                        "GIRA[-90];\n" +
                        "for(i=0;i<5;i=i+1){\n" +
                        "   	COLOR[i*13, i*26, i*51];   	\n" +
                        "	AVANZA[200];\n" +
                        "GIRA[72];\n" +
                        "}");
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
        add(b1);
    }
    
    public void addBasicShapeHexagon(){
        b1 = new JButton("6");
        b1.setBounds(260,590,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "AVANZA[-100];\n" +
                        "GIRA[90];\n" +
                        "AVANZA[-200];\n" +
                        "GIRA[-90];\n" +
                        "for(i=0;i<6;i=i+1){\n" +
                        "	COLOR[i*13, i*26, i*51];  \n" +
                        "	AVANZA[200];\n" +
                        "GIRA[60];\n" +
                        "}");
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
        add(b1);
    }
    
    public void addBasicShapeHexagon7(){
        b1 = new JButton("7");
        b1.setBounds(310,590,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "AVANZA[-100];\n" +
                        "GIRA[90];\n" +
                        "AVANZA[-200];\n" +
                        "GIRA[-90];\n" +
                        "for(i=0;i<7;i=i+1){\n" +
                        "   	COLOR[i*13, i*26, i*51];   	\n" +
                        "	AVANZA[150];\n" +
                        "GIRA[52];\n" +
                        "}");
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
        add(b1);
    }
    
    public void addBasicShapeHexagon8(){
        b1 = new JButton("8");
        b1.setBounds(360,590,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "AVANZA[-100];\n" +
                        "GIRA[90];\n" +
                        "AVANZA[-200];\n" +
                        "GIRA[-90];\n" +
                        "for(i=0;i<8;i=i+1){\n" +
                        "   	COLOR[i*13, i*26, i*51];  \n" +
                        "	AVANZA[150];\n" +
                        "GIRA[45];\n" +
                        "}");
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
        add(b1);
    }
    
    public void addBasicShapeHexagon9(){
        b1 = new JButton("9");
        b1.setBounds(410,590,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "AVANZA[-100];\n" +
                        "GIRA[90];\n" +
                        "AVANZA[-200];\n" +
                        "GIRA[-90];\n" +
                        "for(i=0;i<9;i=i+1){\n" +
                        "   	COLOR[i*13, i*26, i*51];   	\n" +
                        "	AVANZA[150];\n" +
                        "GIRA[40];\n" +
                        "}");
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
        add(b1);
    }
    
     public void addBasicShapeHexagon10(){
        b1 = new JButton("10");
        b1.setBounds(460,590,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "AVANZA[-100];\n" +
                        "GIRA[90];\n" +
                        "AVANZA[-200];\n" +
                        "GIRA[-90];\n" +
                        "for(i=0;i<10;i=i+1){\n" +
                        "   	COLOR[i*13, i*26, i*51];   	\n" +
                        "	AVANZA[120];\n" +
                        "GIRA[36];\n" +
                        "}");
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
        add(b1);
    }
     
     
    public void addFigureRegla(){
        b1 = new JButton("Regla");
        b1.setBounds(10,670,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "AVANZA[-200];\n" +
                        "for(i=0;i<40;i=i+1){\n" +
                        "	COLOR[i*13, i*26, i*51];   \n" +
                        "	AVANZA[10];\n" +
                        "	GIRA[90];\n" +
                        "	AVANZA[5];\n" +
                        "	GIRA[180];\n" +
                        "	AVANZA[10];\n" +
                        "	GIRA[180];\n" +
                        "	AVANZA[5];\n" +
                        "	GIRA[-90];\n" +
                        "}");
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
        add(b1);
    }
    
    public void addFigureEspiral(){
        b1 = new JButton("Espiral");
        b1.setBounds(60,670,60,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "for(i=0;i<80;i=i+1){\n" +
                        "	COLOR[i*13, i*26, i*51];\n" +
                        "	AVANZA[i*5];\n" +
                        "	GIRA[90];\n" +
                        "}");
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
        add(b1);
    }
    
    public void addFigureFlor(){
        b1 = new JButton("Flor");
        b1.setBounds(120,670,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "procedure circulo(){\n" +
                        "   for(aux=0; aux<360; aux=aux+1){\n" +
                        "		COLOR[$1,$2,$3];\n" +
                        "		AVANZA[2];\n" +
                        "		GIRA[1];\n" +
                        "   }\n" +
                        "}\n" +
                        "procedure flor(){\n" +
                        "   for(i=0;i<8;i=i+1){\n" +
                        "		circulo((i+1)*30, (i+1)*32, (i+1)*60);\n" +
                        "		GIRA[45];\n" +
                        "   }\n" +
                        "}\n" +
                        "flor();");
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
        add(b1);
    }
    
    public void addFigureStar(){
        b1 = new JButton("Star");
        b1.setBounds(170,670,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "procedure estrella() {\n" +
                        "    for (i = 0; i < 500; i=i+1){\n" +
                        "		COLOR[i*3,i*4,i*5];\n" +
                        "		GIRA[-90 * i];        \n" +
                        "		AVANZA[150];\n" +
                        "		GIRA[90 * i];        \n" +
                        "		AVANZA[20];\n" +
                        "		GIRA[-90 * i];\n" +
                        "		GIRA[1];\n" +
                        "    }\n" +
                        "}\n" +
                        "estrella();");
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
        add(b1);
    }
    
    public void addFigureKoch(){
        b1 = new JButton("Koch");
        b1.setBounds(220,670,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "procedure cuadrado(){\n" +
                        "	COLOR[0, 0, 255];\n" +
                        "   	if($1<0){\n" +
                        "		AVANZA[$2];\n" +
                        "	}\n" +
                        "	else{	\n" +
                        "		cuadrado($1-1,$2*0.33333333);\n" +
                        "		GIRA[60];\n" +
                        "		cuadrado($1-1,$2*0.33333333);\n" +
                        "		GIRA[-60];\n" +
                        "		GIRA[-60];\n" +
                        "		cuadrado($1-1,$2*0.33333333);\n" +
                        "		GIRA[60];\n" +
                        "		cuadrado($1-1,$2*0.33333333);\n" +
                        "	}\n" +
                        "}\n" +
                        "procedure cuadrados(){ \n" +
                        "	AVANZA[-280];  \n" +
                        "	cuadrado($1,500);\n" +
                        "}\n" +
                        "cuadrados(3);");
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
        add(b1);
    }
    
    public void addFigureStar2(){
        b1 = new JButton("Star2");
        b1.setBounds(270,670,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "for(i=0;i<130;i=i+1){\n" +
                        "	COLOR[i*13, i*26, i*51];\n" +
                        "	AVANZA[i*5];\n" +
                        "	GIRA[150];\n" +
                        "}");
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
        add(b1);
    }
    
    public void addFigureStar3(){
        b1 = new JButton("Star3");
        b1.setBounds(320,670,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "for(i=0;i<130;i=i+1){\n" +
                        "	COLOR[i*13, i*26, i*51];\n" +
                        "	AVANZA[i*5];\n" +
                        "	GIRA[250];\n" +
                        "}");
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
        add(b1);
    }
    
    public void addFigurePiram(){
        b1 = new JButton("Piram");
        b1.setBounds(370,670,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "for(i=0;i<130;i=i+1){\n" +
                        "	COLOR[200, 0, 200];\n" +
                        "	AVANZA[i*4];\n" +
                        "	GIRA[120];\n" +
                        "}\n" +
                        "GIRA[30];\n" +
                        "AVANZA[300];\n" +
                        "GIRA[-60];\n" +
                        "for(i=0;i<80;i=i+1){\n" +
                        "	COLOR[0, 200,150];\n" +
                        "	AVANZA[i*5];\n" +
                        "	GIRA[90];\n" +
                        "}");
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
        add(b1);
    }
    
    public void addFigureP1(){
        b1 = new JButton("P1");
        b1.setBounds(420,670,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "procedure patron() {\n" +
                        "    GIRA[-90];\n" +
                        "    for (i = 0; i < 22; i = i + 1) {\n" +
                        "		GIRA[-90];\n" +
                        "		COLOR[0, 255, 255];\n" +
                        "		AVANZA[110 - (i * 10)];\n" +
                        "		GIRA[-90];\n" +
                        "		COLOR[255, 255, 51];\n" +
                        "		AVANZA[i * 10];    \n" +
                        "    }\n" +
                        "}\n" +
                        "patron();");
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
        add(b1);
    }
    
    public void addFigureBrain(){
        b1 = new JButton("Brain");
        b1.setBounds(470,670,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "COLOR[255, 191, 190];\n" +
                        "GIRA[90];\n" +
                        "AVANZA[-100];\n" +
                        "GIRA[180+90];\n" +
                        "procedure brain(){\n" +
                        "	if($1>4){\n" +
                        "		AVANZA[$1];\n" +
                        "		GIRA[20];\n" +
                        "		COLOR[247,0,190];\n" +
                        "		brain($1-4);\n" +
                        "		GIRA[320];\n" +
                        "		COLOR[247,0,190];\n" +
                        "		brain($1-4);\n" +
                        "		GIRA[20];\n" +
                        "		COLOR[247,0,190];\n" +
                        "		AVANZA[(-1)*($1)];\n" +
                        "	}\n" +
                        "}\n" +
                        "GIRA[90];\n" +
                        "COLOR[247, 191, 190];\n" +
                        "GIRA[180];\n" +
                        "AVANZA[100];\n" +
                        "GIRA[180];\n" +
                        "COLOR[255, 191, 190];\n" +
                        "brain(50);");
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
        add(b1);
    }
    
    public void addFigureRose(){
        b1 = new JButton("Rose");
        b1.setBounds(520,670,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "procedure rosa() {\n" +
                        "GIRA[-90];\n" +
                        "    for (i = 0; i <= 100; i = i + 1) {\n" +
                        "        COLOR[7*i,0,3*i];\n" +
                        "        AVANZA[i * 2];\n" +
                        "        GIRA[-81];\n" +
                        "    }\n" +
                        "}\n" +
                        "rosa();");
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
        add(b1);
    }
    
    public void addFigureTree(){
        b1 = new JButton("Tree");
        b1.setBounds(570,670,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "procedure tree(){\n" +
                        "	if($1>5){\n" +
                        "		AVANZA[$1];\n" +
                        "		GIRA[20];\n" +
                        "		tree($1-15);\n" +
                        "		GIRA[320];\n" +
                        "		tree($1-15);\n" +
                        "		GIRA[20];\n" +
                        "		AVANZA[(-1)*($1)];\n" +
                        "	}\n" +
                        "}\n" +
                        "GIRA[90];\n" +
                        "COLOR[0, 0, 0];\n" +
                        "GIRA[180];\n" +
                        "AVANZA[200];\n" +
                        "GIRA[180];\n" +
                        "COLOR[0,255,0];\n" +
                        "tree(100);");
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
        add(b1);
    }
    
    public void addFigureSpiral(){
        b1 = new JButton("Spiral");
        b1.setBounds(620,670,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "for(i=0;i<200;i=i+1){\n" +
                        "   COLOR[0, i*26, i*51];\n" +
                        "   AVANZA[i*4];\n" +
                        "   GIRA[145];\n" +
                        "}");
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
        add(b1);
    }
    
    public void addFigureCoinProcedimientos(){
        b1 = new JButton("Coin");
        b1.setBounds(670,670,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "procedure cuadro(){\n" +
                        "   for(j=0;j<4;j=j+1){\n" +
                        "      AVANZA[100];\n" +
                        "      GIRA[90];\n" +
                        "   }\n" +
                        "}\n" +
                        "COLOR[50,50,50];\n" +
                        "for(i=0; i< 360; i=i+1){\n" +
                        "cuadro();\n" +
                        "GIRA[1];\n" +
                        "}");
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
        add(b1);
    }
     
    public void addFigureSquiral(){
        b1 = new JButton("Squiral");
        b1.setBounds(720,670,60,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "procedure squiral() {\n" +
                        "    GIRA[-90];\n" +
                        "    for (i = 0; i < 250; i = i + 1) {\n" +
                        "        AVANZA[i * 2];\n" +
                        "        GIRA[-91];\n" +
                        "    }\n" +
                        "}\n" +
                        "COLOR[0, 255, 255];\n" +
                        "squiral();");
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
        add(b1);
    }
    
    public void addFigureH0(){
        b1 = new JButton("H0");
        b1.setBounds(780,670,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "procedure generaHilbert(){\n" +
                        "	if($1 > 0){\n" +
                        "		COLOR[106, 105, 216];\n" +
                        "		GIRA[-90*$2];\n" +
                        "		generaHilbert($1-1, -1 * $2, $3);\n" +
                        "		AVANZA[$3];\n" +
                        "		GIRA[90*$2];\n" +
                        "		generaHilbert($1-1, $2, $3);\n" +
                        "		AVANZA[$3];\n" +
                        "		generaHilbert($1-1, $2, $3);\n" +
                        "		GIRA[90*$2];\n" +
                        "		AVANZA[$3];\n" +
                        "		generaHilbert($1-1, -1 * $2, $3);\n" +
                        "		GIRA[-90*$2];\n" +
                        "	}\n" +
                        "}\n" +
                        "\n" +
                        "procedure curvaHilbert(){\n" +
                        "	generaHilbert(0+1, 1, 250*1);\n" +
                        "}\n" +
                        "\n" +
                        "COLOR[206, 205, 216];\n" +
                        "GIRA[90];\n" +
                        "AVANZA[290];\n" +
                        "GIRA[90];\n" +
                        "AVANZA[290];\n" +
                        "GIRA[180];\n" +
                        "curvaHilbert();");
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
        add(b1);
    }
    
    public void addFigureH2(){
        b1 = new JButton("H2");
        b1.setBounds(830,670,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "procedure generaHilbert(){\n" +
                        "	if($1 > 0){\n" +
                        "		COLOR[106, 105, 216];\n" +
                        "		GIRA[-90*$2];\n" +
                        "		generaHilbert($1-1, -1 * $2, $3);\n" +
                        "		AVANZA[$3];\n" +
                        "		GIRA[90*$2];\n" +
                        "		generaHilbert($1-1, $2, $3);\n" +
                        "		AVANZA[$3];\n" +
                        "		generaHilbert($1-1, $2, $3);\n" +
                        "		GIRA[90*$2];\n" +
                        "		AVANZA[$3];\n" +
                        "		generaHilbert($1-1, -1 * $2, $3);\n" +
                        "		GIRA[-90*$2];\n" +
                        "	}\n" +
                        "}\n" +
                        "\n" +
                        "procedure curvaHilbert(){\n" +
                        "	generaHilbert(1+1, 1, 250*0.33);\n" +
                        "}\n" +
                        "\n" +
                        "COLOR[206, 205, 216];\n" +
                        "GIRA[90];\n" +
                        "AVANZA[290];\n" +
                        "GIRA[90];\n" +
                        "AVANZA[290];\n" +
                        "GIRA[180];\n" +
                        "curvaHilbert();");
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
        add(b1);
    }
    
    public void addFigureH3(){
        b1 = new JButton("H3");
        b1.setBounds(880,670,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "procedure generaHilbert(){\n" +
                        "	if($1 > 0){\n" +
                        "		COLOR[106, 105, 216];\n" +
                        "		GIRA[-90*$2];\n" +
                        "		generaHilbert($1-1, -1 * $2, $3);\n" +
                        "		AVANZA[$3];\n" +
                        "		GIRA[90*$2];\n" +
                        "		generaHilbert($1-1, $2, $3);\n" +
                        "		AVANZA[$3];\n" +
                        "		generaHilbert($1-1, $2, $3);\n" +
                        "		GIRA[90*$2];\n" +
                        "		AVANZA[$3];\n" +
                        "		generaHilbert($1-1, -1 * $2, $3);\n" +
                        "		GIRA[-90*$2];\n" +
                        "	}\n" +
                        "}\n" +
                        "\n" +
                        "procedure curvaHilbert(){\n" +
                        "	generaHilbert(2+1, 1, 250*0.125);\n" +
                        "}\n" +
                        "\n" +
                        "COLOR[206, 205, 216];\n" +
                        "GIRA[90];\n" +
                        "AVANZA[290];\n" +
                        "GIRA[90];\n" +
                        "AVANZA[290];\n" +
                        "GIRA[180];\n" +
                        "curvaHilbert();");
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
        add(b1);
    }
    
    public void addFigureH5(){
        b1 = new JButton("H5");
        b1.setBounds(930,670,50,50);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                parser.limpiar();
                codeArea.setText(
                        "procedure generaHilbert(){\n" +
                        "	if($1 > 0){\n" +
                        "		COLOR[106, 105, 216];\n" +
                        "		GIRA[-90*$2];\n" +
                        "		generaHilbert($1-1, -1 * $2, $3);\n" +
                        "		AVANZA[$3];\n" +
                        "		GIRA[90*$2];\n" +
                        "		generaHilbert($1-1, $2, $3);\n" +
                        "		AVANZA[$3];\n" +
                        "		generaHilbert($1-1, $2, $3);\n" +
                        "		GIRA[90*$2];\n" +
                        "		AVANZA[$3];\n" +
                        "		generaHilbert($1-1, -1 * $2, $3);\n" +
                        "		GIRA[-90*$2];\n" +
                        "	}\n" +
                        "}\n" +
                        "\n" +
                        "procedure curvaHilbert(){\n" +
                        "	generaHilbert(5+1, 1, 200*0.041);\n" +
                        "}\n" +
                        "\n" +
                        "COLOR[206, 205, 216];\n" +
                        "GIRA[90];\n" +
                        "AVANZA[290];\n" +
                        "GIRA[90];\n" +
                        "AVANZA[290];\n" +
                        "GIRA[180];\n" +
                        "curvaHilbert();");
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
        add(b1);
    }
}
