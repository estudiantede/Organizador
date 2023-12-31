/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package organizador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

public class MainFrame implements ActionListener
{
    
    
    //Variables
    
    Temporizador temp;
    int cronometroCreado = 0;
    
    Sound sound = new Sound(0);
    int sonidoCreado = 0;
    
    static int t = 20;
    
    
    
   private static JFrame frame;
   private JPanel paneles[];
   private JPanel subPaneles[][] = new JPanel[2][3];;
   final private int filas[] = {1, 1, 1};
   final private int columnas[] = {1, 3, 3};
   private JLabel titulos[] = new JLabel[7];
   private static JProgressBar barraProgreso;
   private JPanel subSubPaneles[][] = new JPanel[2][3];
   private String titulosNombre[] = {"TO-DO", "Tareas", "Cronometro", "Tiempo", "Calendario", "Música", "Trabajado"};
   private Font fuentes[] = {new Font("Papyrus",Font.BOLD  , 50), new Font("Scheherazade", Font.BOLD    , 20)};
   private Color colores[] = {new Color(227, 244, 244), new Color(248, 246, 244), new Color(210, 233, 233), new Color(196, 223, 223), };
   private Color coloresGris[] = {new Color(237, 225, 239), new Color(232, 217  , 226)};

   //Variables de tareas
   private JTextArea tareas;
   private JButton tareasBoton;
   
   //Variables de cronometro
   static private JLabel cronometro;
   private JPanel cronometroPanel;
   private JButton cronometroBoton[] = new JButton[2];
   
   //Variables de tiempo
   private static Scroll tiempo[]; //Esta tiene que ser una clase que tenga un wheel moved listener, asi que teoricamente no sería tan asi
   
   //Variables de calendario
   private JButton calendario;
   
   //Variables de musica
   private JLabel musica;
   private JPanel musicaPanel;
   private JButton musicaBoton[];
   
   //Variables de trabajado
   private JLabel trabajado;
   private JButton trabajadoBoton;
   
   Calendario cal = new Calendario();
   
//Constructor
   public MainFrame() throws IOException
   {
       
       
       frame = creacionJFrame();
       paneles = new JPanel[3];
       
       //Se crean los titulos
       for(int i = 0, bool = 0; i < 7; i++, bool++)
      {
          titulos[i] = creacionTitulos(i, bool);
      }
       titulos[0].setFont(fuentes[0]);
       //Se crean los subSubPaneles
       
       for(int i = 0; i < 2; i++)
       {
           for(int k = 0; k < 3; k++)
           {
               subSubPaneles[i][k] = creacionSubSubPaneles(i, k);
           }
       }
       
       //Se crea las divisiones principales del frame en 3 paneles
       for(int i = 0; i < 3; i++)
       {
           paneles[i] = creacionPaneles(i);
           frame.add(paneles[i]);
       }
       paneles[0].setLayout(new BorderLayout());
       
      paneles[0].add(titulos[0], BorderLayout.NORTH);
       //Se crean las subdivisiones dentro de los paneles
       for(int i = 0, num = 1; i < 2; i++)
       {
           for (int k = 0; k < columnas[i+1]; k++)
           {
               subPaneles[i][k] = creacionSubPaneles(i, k);
               subPaneles[i][k].add(titulos[num], BorderLayout.NORTH);
               subPaneles[i][k].add(subSubPaneles[i][k], BorderLayout.CENTER);
               paneles[i+1].add(subPaneles[i][k]);
               num++;
           }
       }
       paneles[0].add(creacionBarraProgreso(), BorderLayout.CENTER);
       System.out.println("Hola mundo");
       
       creacionTareas(0, 0);
       creacionCronometro(0,1);
       creacionTiempo(0, 2);
       creacionCalendario(1, 0);
       creacionMusica(1, 1);
       creacionTrabajado(1, 2);
       
   } //Fin del constructor
   
   //Este método crea el JFrame dandole sus caracteristicas
   private JFrame creacionJFrame()
   {
       frame = new JFrame("Hola mundo");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(400, 700);
       frame.setVisible(true);
       frame.setLayout(new GridLayout(3, 1));
       frame.setBackground(new Color(227, 244, 244));
       frame.setMinimumSize(new Dimension(600, 700));
       return frame;
   }
   
   //Este método crea los paneles principales dandole sus caracteristicas
   private JPanel creacionPaneles(int num)
   {
       paneles[num] = new JPanel();
       paneles[num].setLayout(new GridLayout(filas[num], columnas[num]));
       paneles[num].setBackground(colores[0]);
       return paneles[num];
   }
   
   //Este método crea las subdivisiones de los paneles principales
   private JPanel creacionSubPaneles(int row, int column)
   {
       subPaneles[row][column] = new JPanel();
       subPaneles[row][column].setLayout(new BorderLayout());
        subPaneles[row][column].setOpaque(false);
       return subPaneles[row][column];
   }
   
   //Este metodo crea paneles que estarán dentro de paneles que dividen a los otros paneles. Estan debajo de los titulos de cada "tema"
   private JPanel creacionSubSubPaneles(int row, int column)
   {
       subSubPaneles[row][column] = new JPanel();
       subSubPaneles[row][column].setLayout(new GridLayout());
       subSubPaneles[row][column].setOpaque(false);
       return subSubPaneles[row][column];
   }
   
   private JProgressBar creacionBarraProgreso()
   {
       barraProgreso = new JProgressBar(0, 1000);
       barraProgreso.setValue(1000);
       barraProgreso.setStringPainted(true);
       barraProgreso.setBorder(BorderFactory.createEmptyBorder(40,40,40, 40));
       barraProgreso.setBackground(colores[0]);
       barraProgreso.setOpaque(true);
       return barraProgreso;
   }
   
   //Este método crea los titulos de los paneles dandole sus respectivos nombres y formatos
   private JLabel creacionTitulos(int num, int bool)
   {
       titulos[num] = new JLabel();
       titulos[num].setText(titulosNombre[num]);
       titulos[num].setVisible(true);
       titulos[num].setFont(fuentes[1]);
       titulos[num].setHorizontalAlignment(JLabel.CENTER);
       titulos[num].setVerticalAlignment(JLabel.BOTTOM);
       if (bool % 2 ==  0)
       {
           titulos[num].setBackground(coloresGris[0]);
       }
       else{
          titulos[num].setBackground(coloresGris[1]);
       }
       if(num == 0) {titulos[num].setBackground(new Color(0,0,0,0));}
       titulos[num].setOpaque(true);
       return titulos[num];
   }
   
   private void creacionTareas(int row, int column)
   {
       tareas = new JTextArea();
       tareas.setEditable(false);
       tareas.setBackground(colores[1]);
       tareas.setFocusable(false);

       agregarLinea("Tareas para realizar", tareas);
       agregarLinea("T -  Hacer la cama", tareas);
       tareas.setLineWrap(true); //Hace que las lineas se corten y bajen si llegan al border
       tareasBoton = new JButton("+");
       tareasBoton.addActionListener(this);
       
       subSubPaneles[row][column].setLayout(new BorderLayout());
       subSubPaneles[row][column].add(tareas, BorderLayout.CENTER);
       subSubPaneles[row][column].add(tareasBoton, BorderLayout.SOUTH);
   }
   
   private void agregarLinea(String texto, JTextArea campo)
   {
       campo.append("\n" + texto);
   }
   
   //Se crean todos los componentes dentro de la pantalla de cronometro
   private void creacionCronometro(int row, int column)
   {
       subSubPaneles[row][column].setLayout(new GridLayout(2, 1));
       
       cronometro = new JLabel("Tiempo");
       cronometro.setVerticalAlignment(JLabel.CENTER);
       cronometro.setHorizontalAlignment(JLabel.CENTER);
       cronometroPanel = new JPanel();
       //cronometroPanel.setLayout(new GridLayout(1, 2)); //Reemplazarlo por GridBagLayout que permite mayor control !!!!
       
       for(int i = 0; i < 2; i++)
       {
           cronometroBoton[i] = new JButton("Boton");
           cronometroBoton[i].addActionListener(this);
           cronometroPanel.add(cronometroBoton[i]);
       }
       
       cronometroBoton[0].setText("Pausar");
       cronometroBoton[1].setText("Abortar");
       subSubPaneles[row][column].add(cronometro);
       subSubPaneles[row][column].add(cronometroPanel);
   }
   
   private void creacionTiempo(int row, int column)
   {
       tiempo  = new Scroll[3];
       
       for(int i = 0; i < 3; i++)
       {
           tiempo[i] = new Scroll();
           tiempo[i].setText(String.valueOf(21 - i));
           tiempo[i].setVerticalAlignment(JLabel.CENTER);
           tiempo[i].setHorizontalAlignment(JLabel.CENTER);
           tiempo[i].setBackground(colores[3]);
           tiempo[i].setOpaque(true);
       }
       
       subSubPaneles[row][column].setLayout(new GridLayout(3, 3));
       
       subSubPaneles[row][column].add(new JLabel());
       subSubPaneles[row][column].add(tiempo[0]);
       subSubPaneles[row][column].add(new JLabel());
       
       subSubPaneles[row][column].add(new JLabel());
       subSubPaneles[row][column].add(tiempo[1]);
       subSubPaneles[row][column].add(new JLabel());
       
       subSubPaneles[row][column].add(new JLabel());
       subSubPaneles[row][column].add(tiempo[2]);
       subSubPaneles[row][column].add(new JLabel());
   }
   
   private void creacionCalendario(int row, int column)
   {
       calendario = new JButton("Calendario");
       calendario.addActionListener(this);
       subSubPaneles[row][column].add(calendario);
   }
   
   private void creacionMusica(int row, int column)
   {
       musica = new JLabel("La música");
       
       musicaPanel = new JPanel();
       
       musicaBoton = new JButton[2];  
       musicaBoton[0] = new JButton("Parar");
       musicaBoton[1]= new JButton("Pasar");
       musicaBoton[0].addActionListener(this);
       musicaBoton[1].addActionListener(this);        
       
       musicaPanel.add(musicaBoton[0]);
       musicaPanel.add(musicaBoton[1]);
       
       subSubPaneles[row][column].setLayout(new GridLayout(2, 1));
       subSubPaneles[row][column].add(musica);
       subSubPaneles[row][column].add(musicaPanel);
   }
   
   private void creacionTrabajado(int row, int column)
   {
       trabajado = new JLabel("Hoy trabajado: ");
       
       trabajadoBoton = new JButton("Trabajo");
       trabajadoBoton.addActionListener(this);
       
       subSubPaneles[row][column].setLayout(new GridLayout(2, 1));
       subSubPaneles[row][column].add(trabajado);
       subSubPaneles[row][column].add(trabajadoBoton);
   }
   //Setters and getters (static)
    public static void setCronometro(String texto)
    {
        cronometro.setText("");
        cronometro.setText(texto);
    }
    
    
    public static void setBarraProgreso(int value)
    {
        System.out.println("El valor es: " + value);
        barraProgreso.setValue(value);
    }
    
    public static void setTiempo(int value)
    {
        if (value > 0)
        {
            if (t > 60)
            {
            
            }
            else if (t < 60)
            {
            t += value;
            if (t == 60)
            {
                tiempo[0].setText(String.valueOf("Tiempo>60"));
                tiempo[1].setText(String.valueOf(t));
                tiempo[2].setText(String.valueOf(t - 1));
            }
            else
            {
            tiempo[0].setText(String.valueOf(t +1));
            tiempo[1].setText(String.valueOf(t));
            tiempo[2].setText(String.valueOf(t - 1));
            }
            }
        }
        else if(value < 0)
        {
            if (t < 1)
            {
            
            }
            else if(t > 1)
            {
                t += value;
                if (t-1 == 0)
                {
                    tiempo[0].setText(String.valueOf(t + 1));
                    tiempo[1].setText(String.valueOf(t));
                    tiempo[2].setText(String.valueOf("Tiempo <0"));
                }
                else
                {
                tiempo[0].setText(String.valueOf(t +1));
                tiempo[1].setText(String.valueOf(t));
                tiempo[2].setText(String.valueOf(t - 1));
                }
            }
        }
    }
    
    private int getTiempo()
    {
        return Integer.parseInt(tiempo[1].getText());
    }
    
    public static void setMainFrameVisible()
    {
        frame.setVisible(true);
    }
    
    public static void setMainFrameInvisible()
    {
        frame.setVisible(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click!");
        //Botones cronometro
        if (e.getSource() == cronometroBoton[0])
        {
            if(cronometroCreado == 0)
            {
                temp = new Temporizador(getTiempo()*60);
                barraProgreso.setMaximum(getTiempo()*60);
                temp.start();
                cronometroCreado = 1;
            }
            else
            {
                if(temp.getSigaCronometro() == true)
                {
                    temp.setSigaCronometro(false);
                }
                else
                {
                    temp.setSigaCronometro(true);
                }
            }
        }
        else if(e.getSource() == cronometroBoton[1])
        {
            
            temp.setSigaCronometro(false);
            temp.setTiempo(0);
            temp.setExit(false);
            barraProgreso.setValue(0);
            MainFrame.setCronometro("Tiempo 0");
            cronometroCreado = 0;
        }
        
         //Botones musica
        else if(e.getSource() == musicaBoton[0])
        {
            if(sound.getSonandoMusica() == 0)
            {
                try {
                    sound.ponerSonido();
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                sound.pararMusica();
            }
            
            
        }
        else if(e.getSource() == musicaBoton[1])
        {
            sound.pararMusica();
            sound.setNum();
            sound.setNuevoAudio(0);
            try {
                sound.ponerSonido();
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
         //Boton tarea
        else if(e.getSource() == tareasBoton)
        {
        
        }
        
         //Boton calendario
        else if(e.getSource() == calendario)
        {
            Calendario.setCalendarioVisible();
            MainFrame.setMainFrameInvisible();
        }
        
         //Botones trabajado
        else if(e.getSource() == trabajadoBoton)
        {
        
        }
    }
}
