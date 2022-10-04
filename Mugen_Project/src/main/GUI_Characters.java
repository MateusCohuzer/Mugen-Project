package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import tools.ManipulaArquivo;

/**
 *
 * @author Mateus CohuzEr
 */
class GUI_Characters extends JDialog {

    Container cp;

    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();

    JPanel pnCentro1 = new JPanel();
    JPanel pnCentro2 = new JPanel();

    JLabel lbCentro2 = new JLabel();

    JTextField txtFCity = new JTextField(25);

    JTextArea txtADesc = new JTextArea(39, 55);

    JButton btAccess = new JButton("Access Infos");

    public List<String[]> carregaSave() {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        String path = "src/res/csvs/characters_info.csv";
        ArrayList<String[]> retorno = new ArrayList();
        String aux[];
        if (manipulaArquivo.existeOArquivo(path)) {
            List<String> l_string = manipulaArquivo.abrirArquivo(path);
            for (String ll : l_string) {
                aux = ll.split(";");
                retorno.add(aux);
            }
        } else {
            System.out.println("Archive not founded");
        }
        return retorno;
    }

    public void start_frame() {
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ArrayList<String[]> lista_infos = new ArrayList<>();
        lista_infos = (ArrayList<String[]>) carregaSave();

        String[] titles = new String[lista_infos.size()];
        ArrayList<String> jujutsu = new ArrayList<>();
        ArrayList<String> imgs = new ArrayList<>();
        ArrayList<String> species = new ArrayList<>();
        ArrayList<String> descs = new ArrayList<>();

        for (int i = 0; i < lista_infos.size(); i++) {
            titles[i] = lista_infos.get(i)[0];
            species.add(lista_infos.get(i)[1]);
            jujutsu.add(lista_infos.get(i)[2]);
            imgs.add(lista_infos.get(i)[3]);
            descs.add(lista_infos.get(i)[4]);
        }

        JComboBox cbCharacters = new JComboBox(titles);

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);

        pnCentro.setLayout(new GridLayout(1, 2));
        pnCentro.add(pnCentro1);
        pnCentro.add(pnCentro2);

        pnCentro2.add(lbCentro2);

        pnCentro1.add(txtADesc);
        txtADesc.setLineWrap(true);
        txtADesc.setWrapStyleWord(true);
        txtADesc.setEditable(false);

        try {
            ImageIcon icone = new ImageIcon(getClass().getResource("../res/imgs/places_infos/place_info0.jpg"));
            Image imagemAux;
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(580, 620, Image.SCALE_FAST));
            lbCentro2.setIcon(icone);
        } catch (Exception e) {
            System.out.println("Erro ao carregar a imagem");
        }

        pnNorte.add(cbCharacters);
        pnNorte.add(btAccess);
        pnNorte.add(txtFCity);

        txtFCity.setEditable(false);

        btAccess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = cbCharacters.getSelectedIndex();
                try {
                    ImageIcon icone = new ImageIcon(getClass().getResource(imgs.get(option)));
                    Image imagemAux;
                    imagemAux = icone.getImage();
                    icone.setImage(imagemAux.getScaledInstance(580, 620, Image.SCALE_FAST));
                    lbCentro2.setIcon(icone);
                } catch (Exception ae) {
                    System.out.println("Erro ao carregar a imagem");
                }
                txtADesc.setText("Jujutsu: " + jujutsu.get(option) + "\n" + descs.get(option));
                txtFCity.setText(species.get(option));
            }
        });

        pnNorte.setBackground(Color.WHITE);
        pnCentro1.setBackground(Color.WHITE);
        pnCentro2.setBackground(Color.WHITE);
        btAccess.setBackground(Color.WHITE);

        this.setTitle("Mugen Project - Characters Info");
        this.setSize(1280, 720);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setModal(true);
        this.setVisible(true);
    }

}
