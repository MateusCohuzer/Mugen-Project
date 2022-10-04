package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import main.Battle;
import main.GUI_Play;
import main.KeyHandler;

/**
 *
 * @author Mateus CohuzEr
 */
public class Player extends Entity {

    GUI_Play gp;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    int hasFinger = 0;
    public boolean trashBool = false;
    public int battleCount = 0;
    public int playerLife;

    int counterX;
    int counterY;

    public Player(GUI_Play gp, KeyHandler keyHandler) {

        this.gp = gp;
        this.keyHandler = keyHandler;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 8;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 48;
        solidArea.height = 48;
        counterX = 48;
        counterY = 48;
        playerLife = 100;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 12;
        worldY = gp.tileSize * 7;
        speed = gp.worldWidth / (gp.worldWidth / 4);
        direction = "down";
    }

    public void getPlayerImage() {
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("../res/imgs/sprites/Itadori/itadori_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("../res/imgs/sprites/Itadori/itadori_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("../res/imgs/sprites/Itadori/itadori_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("../res/imgs/sprites/Itadori/itadori_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("../res/imgs/sprites/Itadori/itadori_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("../res/imgs/sprites/Itadori/itadori_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("../res/imgs/sprites/Itadori/itadori_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("../res/imgs/sprites/Itadori/itadori_right_2.png"));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update() throws InterruptedException {

        if (keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.leftPressed == true || keyHandler.rightPressed == true) {
            if (keyHandler.upPressed == true) {
                direction = "up";
            } else if (keyHandler.downPressed == true) {
                direction = "down";
            } else if (keyHandler.leftPressed == true) {
                direction = "left";
            } else if (keyHandler.rightPressed == true) {
                direction = "right";
            }

            // Check Tile
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //check obj collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //if collision is false, player can move
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        counterY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        counterY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        counterX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        counterX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) throws InterruptedException {
        if (i != 999) {

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Sukuna Finger":
                    hasFinger++;
                    playerLife += 200;
                    gp.obj[i] = null;
                    if (hasFinger == 3) {
                        JOptionPane.showConfirmDialog(gp, "You win!!! You have the three fingers!");
                    }
                    break;
                case "Trash Can":
                    if (battleCount != 1) {
                        battleCount++;
                    }
                    if (battleCount == 1) {
                        battleCount += 1;
                        Battle battle = new Battle();
                        System.out.println("Battle!");
                        battle.startBattle("Hanami");
                        if (!battle.isActive()) {
                            battleCount = 0;
                        }
                        worldY -= 16;
                        worldX -= 16;
                        break;
                    }
                    if (battleCount == -1) {
                        JOptionPane.showMessageDialog(gp, "The trash can is dented, Hanami seems to have damaged it during the fight.");
                    }
                    break;
                case "Getou's Chest":
                    if (hasFinger == 20) {
                        hasFinger += 15;
                    }
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        BufferedImage fingerCounterImg = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }

        try {
            fingerCounterImg = ImageIO.read(getClass().getResourceAsStream("../res/imgs/objects/sukuna_finger.png"));
        } catch (Exception e) {
            System.out.println(e);
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        //g2.drawImage(fingerCounterImg, counterX, counterY, gp.tileSize, gp.tileSize, null);
    }
}
