package organizador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Calendario implements ActionListener{
     
    private static JFrame calendario;
    private JPanel panelTitulo;
    private JLabel labelTitulo;
    
    private JButton botonVolver;
    
    
    private JPanel panelCalendario;
    
    private JPanel panelDias;
    private JLabel labelDias[] = new JLabel[7];
    private final String dias[] = {"Sabado", "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
    
    private JPanel panelBotonCalendario;
    
    private JButton botonCalendario[] = new JButton[42];
    
    final public static int meses[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    Calendar now = Calendar.getInstance();
    int day = 1;
    int dayToday = now.get(Calendar.DAY_OF_MONTH);
    int month = now.get(Calendar.MONTH) + 1;
    int year  =now.get(Calendar.YEAR);
    int diaHoy = Calendario.date(day, month, year);
   
    HashMap<Integer,  HashMap<Integer, String>> eventos = new HashMap<Integer, HashMap<Integer, String>>();
    
    public Calendario() throws IOException
    {
        
        eventos = lecturaEventos();
        //System.out.println(day + " " + month + " " + year);
        
        //System.out.println("DiaHoy: " + diaHoy);
        calendario = new JFrame("Calendario");
        calendario.setLayout(new BorderLayout());
        calendario.setSize(400, 500);
        calendario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        calendario.add(creacionTitulo(), BorderLayout.NORTH);
        calendario.add(creacionCalendario(), BorderLayout.CENTER);
        calendario.setVisible(false);
    }

    public static void setCalendarioVisible()
    {
        calendario.setVisible(true);
    }
    
    public static void setCalendarioInvisible()
    {
        calendario.setVisible(false);
    }
    
    
    private JPanel creacionTitulo()
    {
            panelTitulo = new JPanel();
            panelTitulo.setLayout(new BorderLayout());
            //panelTitulo.setPreferredSize(new Dimension(100, 300));
            panelTitulo.setOpaque(false);
            
            botonVolver = new JButton("<-");
            botonVolver.addActionListener(this);
            
            labelTitulo = new JLabel("Calendario");
            labelTitulo.setVerticalAlignment(JLabel.CENTER);
            labelTitulo.setHorizontalAlignment(JLabel.CENTER);
            labelTitulo.setBackground(Color.red);
            labelTitulo.setOpaque(true);
            panelTitulo.add(botonVolver, BorderLayout.WEST);
            panelTitulo.add(labelTitulo, BorderLayout.CENTER);
            return panelTitulo;
    }
    
    private JPanel creacionCalendario()
    {
        panelCalendario = new JPanel();
        panelCalendario.setLayout(new BorderLayout());
        panelCalendario.setOpaque(false);
        
        //Se hace la parte de dias
        panelDias = new JPanel();
        panelDias.setLayout(new GridLayout());
        panelDias.setOpaque(false);
        for (int i = 0; i < labelDias.length; i++)
        {
            labelDias[i] = new JLabel(dias[i]);
            panelDias.add(labelDias[i]);
        }
        
        //Se hace la parte de las fechas con botones
        panelBotonCalendario = new JPanel();
        panelBotonCalendario.setLayout(new GridLayout(6, 7));
        panelBotonCalendario.setOpaque(false);
        
        for (int i = 0; i < botonCalendario.length; i++)
        {
            botonCalendario[i] = new JButton();
            botonCalendario[i].addActionListener(this);
            panelBotonCalendario.add(botonCalendario[i]);
            
        }
        
        for (int i = 0, k = 0; k + diaHoy< botonCalendario.length; i++, k++)
        {
            if(i == meses[month])
            {
                i = 0;
            }
            botonCalendario[k+diaHoy].setText(String.valueOf(i+1));
            if (i+1 == dayToday)
            {
                botonCalendario[k+diaHoy].setBackground(new Color(100, 140, 136));
            }
            if(eventos.containsKey(month))
            {
                if(eventos.get(month).containsKey(k+1))
                {
                botonCalendario[k+diaHoy].setBackground(new Color(200, 140, 136));
                String str = eventos.get(month).get(k+1);
                    System.out.println(str);
                }
            }
        }
        panelCalendario.add(panelDias, BorderLayout.NORTH);
        panelCalendario.add(panelBotonCalendario, BorderLayout.CENTER);
        return panelCalendario;
    }
    
    //Si un año es bisiesto o no
    public static boolean year_bis(int year)
    {
    boolean bis; // En esta variabe 1 va a indicar que es bisiesto, mientras que 0 india que es un a�o no bisiesto
    if((year % 4) == 0)
    {
        if ((year % 100) == 0)
        {
            if ((year % 400) == 0)
            {
	bis = true;
            }
            else
            {
                bis = false;
            }
        }
        else
        {
            bis = true;
        }
    }
    else
    {   
         bis = false;
    }
        return bis;
    }
    
    //En que fecha cae un día en particular
    public static int date(int day, int month, int year)
    {
    // h = (q + 2.6 (m + 1) + K + K / 4 + J / 4 - 2J) mod 7
    // Empieza en 0 con sabado, 1 domingo, 2 lunes
    int m = month;
    int q = day;
    int k = year % 100;
    int j = year / 100;	
    if (month <= 2)
        {
    m = month + 12;
    k = (year - 1) % 100;
    j = (year - 1) / 100;
    }
    int dos = 13 * (m + 1) / 5;
    int kcuarto = k / 4;
    int jcuarto = j / 4; 
            
    int h = (q + dos + k + kcuarto + jcuarto + 5 * j) % 7;
    return h;
}
    
    public HashMap<Integer,  HashMap<Integer, String>> lecturaEventos() throws FileNotFoundException, IOException
    {
        String contenido;
        String mes = "";
        String dia = "";
        String evento = "";
        int puntoYComa = 0;
        int punto = 0;
        int primerLinea = 0;
        HashMap<Integer,  HashMap<Integer, String>> eventos = new HashMap<Integer,  HashMap<Integer, String>>();
        FileReader archivo = new FileReader("C:\\Users\\Avaya\\Documents\\NetBeansProjects\\Organizador\\src\\organizador\\Eventos.csv");
        BufferedReader buffer = new BufferedReader(archivo);
        
        while ((contenido = buffer.readLine()) != null)
        {
            puntoYComa = 0;
            punto = 0;  
            mes = "";
            dia = "";
            evento = "";
            if(primerLinea == 0)
            {
                primerLinea = 1;
                continue;
            }
            //System.out.println(contenido);
            for (char c : contenido.toCharArray())
            {
                if (c != ';')
                {
                    if (puntoYComa == 0)
                    {
                        mes += c;
                    }
                    else if(puntoYComa == 1)
                    {
                        dia += c;
                    }
                }
                else
                {
                    puntoYComa++;
                }
                if(puntoYComa == 2)
                {
                    evento = contenido.substring(punto+1);
                    puntoYComa++;
                }
                punto++;
            }
            //Si existe ya la llave
            if(eventos.containsKey(Integer.valueOf(mes)))
            {
                //Si existe un evento en ese día que ya fue creado
                if(eventos.get(Integer.valueOf(mes)).containsKey(Integer.valueOf(day)))
                {
                    String str = eventos.get(Integer.valueOf(mes)).get(Integer.valueOf(day));
                    evento = str + "\n"+  evento;
                    eventos.get(Integer.valueOf(mes)).put(Integer.valueOf(day), evento);
                }
                //Si no existe un evento ese dias
                else
                {
                    eventos.get(Integer.valueOf(mes)).put(Integer.valueOf(day), evento);
                }
            }
            //Si no existe un evento en ese mes y no fue creado
            else
            {
                eventos.put(Integer.valueOf(mes), new HashMap<Integer, String>());
                eventos.get(Integer.valueOf(mes)).put(Integer.valueOf(dia), evento);
            }   
            //System.out.println("El mes es el " + mes);
            //System.out.println("El dia es el " +dia);
            //System.out.println("El evento es: " + evento);
        }
        
        for(int i : eventos.keySet())
        {
            //System.out.println(i);
            //System.out.println(eventos.get(i));
        }
        buffer.close();
        return eventos;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int k = 0;
        
        if (e.getSource()== botonVolver)
        {
            MainFrame.setMainFrameVisible();
            Calendario.setCalendarioInvisible();
            
        }
        for (int i = 0; i < botonCalendario.length; i++)
        {
            if(e.getSource() == botonCalendario[i])
            {
                k = i + 1;
                break;
            }
        }
        System.out.println(k);
        if(eventos.get(month).containsKey(k+diaHoy))
        {
            JOptionPane.showMessageDialog(calendario,eventos.get(month).get(k+diaHoy), "Evento", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
