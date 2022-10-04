package objects;

import javax.imageio.ImageIO;

/**
 *
 * @author Mateus CohuzEr
 */
public class OBJ_Trash_Can extends SuperObject {

    public OBJ_Trash_Can() {
        name = "Trash Can";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("../res/imgs/objects/trash_can.png"));
        } catch (Exception e) {
            System.out.println(e);
        }
        collision = true;
    }
}
