package main;

import entity.Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import tools.ManipulaArquivo;

/**
 *
 * @author Mateus CohuzEr
 */
public class Battle extends JDialog {

    JPanel pnCentro = new JPanel();
    JPanel pnOeste = new JPanel();
    JPanel pnNorte = new JPanel();
    JPanel pnSul = new JPanel();

    JLabel lbCentro = new JLabel();

    JButton bt1 = new JButton();
    JButton bt2 = new JButton();
    JButton bt3 = new JButton();
    JButton bt4 = new JButton();
    JButton btExit = new JButton();
    JButton btStartBattle = new JButton();

    JProgressBar lifePlayer = new JProgressBar();
    JProgressBar lifeRival = new JProgressBar();

    JTextField tfPlayerName = new JTextField();
    JTextField tfRivalName = new JTextField();

    JTextField tfPlayerAct = new JTextField(20);
    JTextField tfRivalAct = new JTextField(20);

    int turnFlag;
    int rivalLifePoints;
    int playerLifePoints;

    public Battle() {
    }

    public void startBattle(String rivalName) throws InterruptedException {
        GUI_Play gp = new GUI_Play();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        KeyHandler keyHandler = new KeyHandler(gp);
        turnFlag = 0;

        int turn = 0; //if turn == 0 - its players turn, if turn == 1 - its rivals turn

        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        Player player = new Player(gp, keyHandler);
        playerLifePoints = player.playerLife;
        String[] lista = loadRival(rivalName);
        rivalLifePoints = Integer.valueOf(lista[6]);

        this.add(pnNorte, BorderLayout.NORTH);
        this.add(pnCentro, BorderLayout.CENTER);
        this.add(pnSul, BorderLayout.SOUTH);
        this.add(pnOeste, BorderLayout.WEST);

        //Center
        pnCentro.add(lbCentro);
        try {
            ImageIcon icone = new ImageIcon(getClass().getResource("../res/imgs/itadori_hanami_battle.jpg"));
            Image imagemAux;
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(1150, 680, Image.SCALE_FAST));

            lbCentro.setIcon(icone);
        } catch (Exception e) {
            System.out.println("Erro ao carregar a imagem");
        }

        //North
        pnNorte.setLayout(new GridLayout(2, 2));
        pnNorte.add(lifePlayer);
        pnNorte.add(lifeRival);
        pnNorte.add(tfPlayerName);
        pnNorte.add(tfRivalName);

        tfPlayerName.setText("Itadori Yuji");
        tfPlayerName.setHorizontalAlignment(SwingConstants.RIGHT);
        tfRivalName.setText(lista[0]);

        tfPlayerName.setEditable(false);
        tfRivalName.setEditable(false);

        lifePlayer.setValue(playerLifePoints);
        lifePlayer.setStringPainted(true);
        lifePlayer.setString(String.valueOf(playerLifePoints));
        lifePlayer.setBounds(0, 0, gp.screenWidth / 2, gp.screenHeight / 2);

        lifeRival.setValue(rivalLifePoints);
        lifeRival.setStringPainted(true);
        lifeRival.setString(String.valueOf(rivalLifePoints));
        lifeRival.setBounds(gp.screenWidth / 2, gp.screenHeight, gp.screenWidth / 2, gp.screenHeight / 2);

        bt1.setText("Kokusen");
        bt2.setText("Divergent Fist");
        bt3.setText("Knife Attack");
        bt4.setText("Sukuna's Punch");

        //SOUTH
        pnSul.setLayout(new GridLayout(3, 2));
        pnSul.add(bt1);
        pnSul.add(bt2);
        pnSul.add(bt3);
        pnSul.add(bt4);

        bt1.setBackground(Color.WHITE);
        bt2.setBackground(Color.WHITE);
        bt3.setBackground(Color.WHITE);
        bt4.setBackground(Color.WHITE);

        btExit.setBackground(Color.GRAY);
        btStartBattle.setBackground(Color.GRAY);

        pnSul.add(btStartBattle);
        pnSul.add(btExit);

        btExit.setText("Exit");
        btStartBattle.setText("Dev Button");

        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfPlayerAct.setText("Itadori uses KOKUSEN!");
                String atkName = "Kokusen";
                rivalLifePoints -= 130;
                updateHP(lifeRival, rivalLifePoints);
                turnFlag = 1;
                botAttack(rivalName);
            }
        });

        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfPlayerAct.setText("Itadori uses Divergent Fist!");
                String atkName = "Divergent Fist";
                rivalLifePoints -= 80;
                updateHP(lifeRival, rivalLifePoints);
                turnFlag = 1;
                botAttack(rivalName);
            }
        });

        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfPlayerAct.setText("Itadori uses a cursed Knife to Attack!");
                String atkName = "Knife Attack";
                rivalLifePoints -= 40;
                updateHP(lifeRival, rivalLifePoints);
                turnFlag = 1;
                botAttack(rivalName);
            }
        });

        bt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfPlayerAct.setText("Itado...S-Sukuna attacks with a Divergent Fist!!!");
                String atkName = "Sukuna's Fist";
                rivalLifePoints -= 160;
                updateHP(lifeRival, rivalLifePoints);
                turnFlag = 1;
                botAttack(rivalName);
            }
        });

        btExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerLifePoints -= playerLifePoints;
                updateHP(lifePlayer, playerLifePoints);
            }
        });

        btStartBattle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (playerLifePoints >= 0 || rivalLifePoints >= 0) {
                    if (turnFlag == 0) {
                        tfPlayerAct.setText("Seu Turno!");
                        checkBattleResult(player);
                    }
                    if (turnFlag == 1) {
                        tfRivalAct.setText("Turno do Oponente");
                        checkBattleResult(player);
                    }
                }
            }
        });

        //WEST
        pnOeste.setLayout(new GridLayout(2, 1));
        pnOeste.add(tfRivalAct);
        pnOeste.add(tfPlayerAct);
        
        tfRivalAct.setText("Press START BATTLE to start");
        tfPlayerAct.setText("Press START BATTLE to start");
        tfRivalAct.setEditable(false);
        tfPlayerAct.setEditable(false);

        this.setTitle("Mugen Project - Battle VS " + lista[0]);
        this.setSize(1280, 720);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);
    }

    public void updateHP(JProgressBar bar, int value) {
        bar.setValue(value);
        bar.setString(String.valueOf(value));
    }

    private String[] loadRival(String rivalName) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        String[] retorno = new String[7];
        String path = "src/res/csvs/battle_info.csv";
        ArrayList<String[]> retorno_ = new ArrayList();
        String aux[];
        if (manipulaArquivo.existeOArquivo(path)) {
            List<String> l_string = manipulaArquivo.abrirArquivo(path);
            for (String ll : l_string) {
                aux = ll.split(";");
                retorno_.add(aux);
            }
        } else {
            System.out.println("Archive not founded");
        }
        for (int i = 0; i < retorno_.size(); i++) {
            if (retorno_.get(i)[0].equals(rivalName)) {
                retorno[0] = String.valueOf(retorno_.get(i)[0]); //name
                retorno[1] = String.valueOf(retorno_.get(i)[1]); //atk1
                retorno[2] = String.valueOf(retorno_.get(i)[2]); //atk2
                retorno[3] = String.valueOf(retorno_.get(i)[3]); //atk3
                retorno[4] = String.valueOf(retorno_.get(i)[4]); //atk4
                retorno[5] = String.valueOf(retorno_.get(i)[5]); //img
                retorno[6] = String.valueOf(retorno_.get(i)[6]); //life
            }
        }
        return retorno;
    }

    private void botAttack(String rivalName) {
        String[] lista = loadRival(rivalName);
        Random random = new Random();
        int attack = random.nextInt(6);
        while (attack == 0 || attack == 6) {
            attack = random.nextInt(6);
        }
        String[] escopo = lista[attack].split("-");
        tfRivalAct.setText("Hanami uses " + escopo[0]);
        playerLifePoints -= Integer.valueOf(escopo[1]);
        updateHP(lifeRival, playerLifePoints);
        turnFlag = 0;
    }

    private void checkBattleResult(Player player) {
        if (playerLifePoints <= 0) {
            int option = JOptionPane.showConfirmDialog(null, "<YOU LOSE! STAY DETERMINED...>", "OK", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.OK_OPTION) {
                player.battleCount--;
                this.dispose();
            }
        }
        if (rivalLifePoints <= 0) {
            int option = JOptionPane.showConfirmDialog(null, "<YOU WIN!!! CONGRATULATIONS!>", "OK", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.OK_OPTION) {
                player.battleCount++;
                this.dispose();
            }
        }
    }

}
