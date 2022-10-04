package objects;

import javax.imageio.ImageIO;

/**
 *
 * @author Mateus CohuzEr
 */
public class OBJ_Chest extends SuperObject {

    public OBJ_Chest() {
        name = "Getou's Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("../res/imgs/objects/chest.png"));
        } catch (Exception e) {
            System.out.println(e);
        }
        collision = true;
    }
}
