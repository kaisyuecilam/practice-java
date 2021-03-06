import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

class MUSIC
{
    File    soundFile;
    Clip    clip;
    AudioInputStream audioInputStream;
    AudioFormat     audioFormat;
    DataLine.Info   info;
	
    // Constructor
    MUSIC(String file)
    {   try
        {   soundFile = new File(file);
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            audioFormat = audioInputStream.getFormat();
            info = new DataLine.Info(Clip.class, audioFormat);
            clip = (Clip)AudioSystem.getLine(info);
            clip.open(audioInputStream);
        }
        catch (UnsupportedAudioFileException e)
        {   e.printStackTrace();  }
        catch (IOException e)
        {   e.printStackTrace();  }
        catch (LineUnavailableException e)
        {   e.printStackTrace();  }
    }
	
	private void controlByLinearScalar(FloatControl control, double linearScalar) {
		control.setValue((float)Math.log10(linearScalar) * 20);
	}

    void Play()		//最初から再生
    {   clip.stop();
		clip.setFramePosition(0);
        clip.start();
    }
	
	void sPlay(double des)	//音量三割で再生
	{	FloatControl control = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		controlByLinearScalar(control, des); // 30%の音量で再生する
		clip.stop();
		clip.setFramePosition(0);
        clip.start();
	}
	
	void rePlay()	//途中から再生
	{	clip.start();
	}
	
	void Stop()		//再生一時停止
	{	clip.stop();
	}
}