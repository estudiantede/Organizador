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
    File file;
    private int SonandoMusica;
    private int nuevoAudio;
    Clip clip;
    
    public Sound(File file)
    {
        this.file = file;
        this.SonandoMusica = 0;
        this.nuevoAudio = 0;
        this.name = file.getName();
        
        this.name = this.name.substring(0, this.name.indexOf('.'));
    }
    
    public void ponerSonido() throws UnsupportedAudioFileException, LineUnavailableException, IOException
    {
        if(nuevoAudio == 0)
        {
            AudioInputStream audioStream  = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(10);
            nuevoAudio = 1;
        }
        //Interfaz.cambiarNombreLabelMusica(this.name);
        clip.start();
        this.SonandoMusica = 1;
        
    }
    
    public int getSonandoMusica()
    {
        return this.SonandoMusica;
    }
    
    public void setSonandoMusica(int num)
    {
        this.SonandoMusica = num;
    }
    
    public void pararMusica()
    {
        this.SonandoMusica = 0;
        clip.stop();
    }
}
