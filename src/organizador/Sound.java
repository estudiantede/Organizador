/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package organizador;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    String name;
    int  num;
    File files[];
    private int SonandoMusica;
    private int nuevoAudio;
    Clip clip;
    
    public Sound(int snum)
    {
        this.files = findAllFilesInFolder(new File("C:\\Users\\Avaya\\Documents\\NetBeansProjects\\Organizador\\Musica"));
        this.SonandoMusica = 0;
        this.nuevoAudio = 0;
        this.name = files[num].getName();
        
        this.name = this.name.substring(0, this.name.indexOf('.'));
    }
    
    public static File[] findAllFilesInFolder(File folder) {
     return folder.listFiles();
    }
    
    public void ponerSonido() throws UnsupportedAudioFileException, LineUnavailableException, IOException
    {
        if(nuevoAudio == 0)
        {
            AudioInputStream audioStream  = AudioSystem.getAudioInputStream(files[num]);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(10);
            nuevoAudio = 1;
        }
        //Interfaz.cambiarNombreLabelMusica(this.name);
        clip.start();
        this.SonandoMusica = 1;
        
    }
    
        public int getNuevoAudio()
    {
        return this.nuevoAudio;
    }
    
    public void setNuevoAudio(int num)
    {
        this.nuevoAudio = num;
    }
    
    public int getSonandoMusica()
    {
        return this.SonandoMusica;
    }
    
    public void setSonandoMusica(int num)
    {
        this.SonandoMusica = num;
    }
    
       public int getNum()
    {
        return this.num;
    }
    
    public void setNum()
    {
        System.out.println(files.length);
        System.out.println(num);
        if (files.length-1 == num)
        {
            this.num = 0;
        }
        else
        {
            this.num++;
        }
    }
    
    public void pararMusica()
    {
        
        if(this.getSonandoMusica() == 1)
         {
             clip.stop();
         }
        this.SonandoMusica = 0;
    }
}
