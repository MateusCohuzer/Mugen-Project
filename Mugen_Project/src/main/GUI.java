package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import tools.Musicas;

/**
 *
 * @author Mateus CohuzEr
 */
class GUI extends JFrame {

    Container cp;
    Container gp;
    JPanel pnCentro = new JPanel();
    JPanel pnOeste = new JPanel();

    JLabel lbOeste = new JLabel();
    JLabel lbCentro = new JLabel();

    JButton btPlay = new JButton("Play");
    JButton btExit = new JButton("Exit");
    JButton btInfos = new JButton("Characters Info");
    JButton btPlaces = new JButton("Places Info");
    JButton btSettings = new JButton("Settings");
    JButton btInventory = new JButton("Inventory");

    Musicas musicas = new Musicas();

    public GUI() throws UnsupportedAudioFileException, IOException, LineUnavailableException  {

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cp.add(pnOeste, BorderLayout.WEST);
        cp.add(pnCentro, BorderLayout.CENTER);
        pnCentro.add(lbCentro);

        try {
            ImageIcon icone = new ImageIcon(getClass().getResource("../res/imgs/StartScreen_Gojou_MugenProject.jpg"));
            Image imagemAux;
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(1150, 680, Image.SCALE_FAST));

            lbCentro.setIcon(icone);
        } catch (Exception e) {
            System.out.println("Erro ao carregar a imagem");
        }

        //Play Button
        btPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                GUI_Play_Frame gui_Play_Frame = new GUI_Play_Frame();
                try {
                    gui_Play_Frame.start_frame();
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //Characters Button
        btInfos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI_Characters gui_Characters = new GUI_Characters();
                gui_Characters.start_frame();
            }
        });

        //Places Button
        btPlaces.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI_Places gui_Places = new GUI_Places();
                gui_Places.start_frame();
            }
        });

        //Settings Button
        btSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GUI_Settings gui_Settings = new GUI_Settings();
                //gui_Settings.start_frame();
                JOptionPane.showMessageDialog(null, "Coming soon...");
            }
        });

        //Inventory Button
        btInventory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GUI_Inventory gui_Inventory = new GUI_Inventory();
                //gui_Inventory.start_frame();
                JOptionPane.showMessageDialog(null, "Coming soon...");
            }
        });

        //Exit Button
        btExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to exit the program?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        pnOeste.setLayout(new GridLayout(6, 1));
        pnOeste.add(btPlay);
        pnOeste.add(btInfos);
        pnOeste.add(btPlaces);
        pnOeste.add(btSettings);
        pnOeste.add(btInventory);
        pnOeste.add(btExit);

        btPlay.setBackground(Color.WHITE);
        btInfos.setBackground(Color.WHITE);
        btPlaces.setBackground(Color.WHITE);
        btSettings.setBackground(Color.WHITE);
        btInventory.setBackground(Color.WHITE);
        btExit.setBackground(Color.WHITE);
        pnCentro.setBackground(Color.WHITE);

        musicas.playBGM();

        setTitle("Mugen Project");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../res/imgs/icon.png")));
        setSize(1280, 720);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
