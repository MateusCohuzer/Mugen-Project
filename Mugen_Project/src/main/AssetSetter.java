package main;

import objects.OBJ_Chest;
import objects.OBJ_Sukuna_Finger;
import objects.OBJ_Trash_Can;

/**
 *
 * @author Mateus CohuzEr
 */
public class AssetSetter {
    GUI_Play gp;
    
    public AssetSetter(GUI_Play gp){
        this.gp = gp;
    }
    
    public void setObject(){
        
        gp.obj[0] = new OBJ_Sukuna_Finger();
        gp.obj[0].worldX = 2 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;
        
        gp.obj[1] = new OBJ_Sukuna_Finger();
        gp.obj[1].worldX = 13 * gp.tileSize;
        gp.obj[1].worldY = 6 * gp.tileSize;
        
        gp.obj[2] = new OBJ_Sukuna_Finger();
        gp.obj[2].worldX = 32 * gp.tileSize;
        gp.obj[2].worldY = 9 * gp.tileSize;
        
        gp.obj[3] = new OBJ_Chest();
        gp.obj[3].worldX = 62 * gp.tileSize;
        gp.obj[3].worldY = 8 * gp.tileSize;
        
        gp.obj[4] = new OBJ_Trash_Can();
        gp.obj[4].worldX = 18 * gp.tileSize;
        gp.obj[4].worldY = 6 * gp.tileSize;
    }
}
