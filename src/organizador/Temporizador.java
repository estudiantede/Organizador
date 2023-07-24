package organizador;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Temporizador extends Thread{
    
    boolean sigaCronometro = true;
    boolean exit = true;
    long tiempo = 0;
    long tiempoTotal;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    Date date;
        
        public Temporizador(long tiempoTotal)
        {
            this.exit = true;
            this.tiempo = 0;
            this.tiempoTotal = tiempoTotal;
        }
        //Getters y setters
        public boolean getSigaCronometro()
        {
            return this.sigaCronometro;
        }
        
        public void setSigaCronometro(boolean bool)
        {
            this.sigaCronometro = bool;
        }
        
        public boolean getExit()
        {
            return this.exit;
        }
        
        public void setExit(boolean bool)
        {
            this.exit = bool;
        }
        
        public long getTiempo()
        {
            return this.tiempo;
        }
        
        public void setTiempo(long num)
        {
            this.tiempo = num;
        }
        
        private int CalcularPorCiento(long tiempoTranscurrido, long tiempoTotal)
        {
                float porcentaje  =(float)tiempo/(float)tiempoTotal*100;
                System.out.println("El tiempo transcurrido es: " + tiempo);
                System.out.println("El porcentaje que representa es " + porcentaje + " del tiempo" + tiempoTotal);
                return (int)porcentaje*10;
        }
        
    public void  run()
    {
        while(exit == true)
            {
                System.out.println(tiempo);
                if (tiempo == tiempoTotal)
                {
                    exit = false;
                }
                while (sigaCronometro)
                {
                   date = Calendar.getInstance().getTime();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Temporizador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (sigaCronometro == true)
                    {
                    tiempo+= 1;
                    }
                    if(tiempoTotal <= tiempo)
                    {
                        sigaCronometro = false;
                    }
                    System.out.println(tiempo);
                    MainFrame.setCronometro("Tiempo " + String.valueOf(tiempo));
                    //MainFrame.setBarraProgreso(CalcularPorCiento(tiempo, tiempoTotal));
                    MainFrame.setBarraProgreso((int)tiempo);
                    
            }
            }
    }
}
