package main;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Mateus CohuzEr
 */
public class GUI_Play_Frame {

    JFrame window = new JFrame();

    public void start_frame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        window.setBackground(Color.black);
        window.setResizable(false);
        window.setTitle("Mugen Project - Play");

        GUI_Play gui_Play = new GUI_Play();
        window.add(gui_Play);

        window.pack();
        window.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../res/imgs/icon.png")));
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gui_Play.setupGame();
        gui_Play.startGameThread();
    }
}
