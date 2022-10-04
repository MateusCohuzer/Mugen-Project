package objects;

import javax.imageio.ImageIO;

/**
 *
 * @author Mateus CohuzEr
 */
public class OBJ_Sukuna_Finger extends SuperObject {
        
    public OBJ_Sukuna_Finger() {
        name = "Sukuna Finger";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("../res/imgs/objects/sukuna_finger.png"));
        } catch (Exception e) {
            System.out.println(e);
        }
        collision = true;
    }
}
