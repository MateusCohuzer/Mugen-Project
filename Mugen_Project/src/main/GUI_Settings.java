package main;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JDialog;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Mateus CohuzEr
 */
class GUI_Settings extends JDialog {

    void start_frame() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBackground(Color.black);
        this.setResizable(false);
        this.setTitle("Mugen Project - Settings");

        this.setSize(1280, 720);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../imgs/icon.png")));
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);
    }

}
