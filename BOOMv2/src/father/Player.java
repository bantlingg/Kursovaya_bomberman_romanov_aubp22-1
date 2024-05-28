package father;

import main.Arena;
import main.Keyhandler;
import main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Player extends Father{
    Arena arena;

int numspr=0;

    int xb,yb;
    int b=0;


    Keyhandler WASD;


    BufferedImage[] playerAnimUp, playerAnimDown, playerAnimRight, playerAnimLeft;
    BufferedImage[] bombAnim;
    BufferedImage[] fontExplosion, rightExplosion, leftExplosion, upExplosion, downExplosion;
    BufferedImage[] concreteExploding;

    BufferedImage view, concreteTile, blockTile, player;

    BufferedImage image=null;

    Map map;




    public Player(Arena arena,Keyhandler WASD,Map map){
    this.WASD=WASD;
    this.map=map;
    this.arena=arena;
        setDef();
        try {
            playerTextur();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public boolean isAlive(){
        if(hp){return true;}
        else {return false;}
    }
    public boolean isFree(int nextX, int nextY) {
        int size = arena.scale * 16;

        int nextX_1 = nextX / size;
        int nextY_1 = nextY / size;

        int nextX_2 = (nextX + size - 1) / size;
        int nextY_2 = nextY / size;

        int nextX_3 = nextX / size;
        int nextY_3 = (nextY + size - 1) / size;

        int nextX_4 = (nextX + size - 1) / size;
        int nextY_4 = (nextY + size - 1) / size;

        return !((map.scene[nextY_1][nextX_1] == 1 || map.scene[nextY_1][nextX_1] == 2) ||
                (       map.scene[nextY_2][nextX_2] == 1 || map.scene[nextY_2][nextX_2] == 2) ||
                (map.scene[nextY_3][nextX_3] == 1 || map.scene[nextY_3][nextX_3] == 2) ||
                (map.scene[nextY_4][nextX_4] == 1 || map.scene[nextY_4][nextX_4] == 2));
    }
    public void setDef(){
         x= (16* arena.scale);
         y= (16* arena.scale);
         speed=6;

    }
    public void update(){
        int xb,yb;
        xb= (int) ((x+32) / (16 * arena.scale));


        yb= (int) ((y+32) / (16 * arena.scale));

        if (map.scene[yb][xb]==11){
            map.scene[yb][xb] = 0;
        }

        if (map.scene[yb][xb] == 4) {
            System.out.println("убит");
            hp=false;
        }
        if (map.scene[yb][xb+1] == 4) {
            System.out.println("убит");
            hp=false;
        }
        if (map.scene[yb][xb-1]==4) {
            System.out.println("убит");
            hp=false;
        }
        if (map.scene[yb-1][xb] == 4) {
            System.out.println("убит");
            hp=false;
        }
        if (map.scene[yb+1][xb] == 4) {
            System.out.println("убит");
            hp=false;
        }

        if (map.scene[yb][xb]==12){
            hp=false;
        }

        if (WASD.right && isFree(x+speed,y)) {
            x += speed;
            if((b%2)>0){
            numspr++;}
        }
        if (WASD.left&& isFree(x-speed,y)) {
            x -= speed;
            if((b%2)>0){
            numspr++;}
        }
        if (WASD.up&& isFree(x,y-speed)) {
            y -= speed;
            if((b%2)>0){
            numspr++;}
        }
        if (WASD.down&& isFree(x,y+speed)) {
            y += speed;
            if((b%2)>0){
            numspr++;}
        }
        xb= (int) ((x+32) / (16 * arena.scale));

        yb= (int) ((y+32) / (16 * arena.scale));
        if (map.scene[yb][xb]==0){
            map.scene[yb][xb] = 11;
        }
        if(numspr>2) {
            numspr=0;
        }
        if (map.bomb == 1) {


            if(WASD.space){
                map.bomb =0;

                map.scene[yb][xb]=3;

            }
        }
        b++;

    }
    public void draw(Graphics2D g2){
        //g2.setColor(Color.darkGray);
        //g2.fillRect(x,y,arena.titleSize,arena.titleSize);
      //BufferedImage image=null;
          if (WASD.right) {
            image=playerAnimRight[numspr];

        }
        if (WASD.left) {
            image=playerAnimLeft[numspr];


        }
        if (WASD.up) {
            image=playerAnimUp[numspr];


        }
        if (WASD.down) {
            image=playerAnimDown[numspr];


        }
        if (WASD.down==WASD.up==WASD.left==WASD.right) {}


g2.drawImage(image,x,y,arena.titleSize,arena.titleSize,null);
    }
    public void playerTextur() throws IOException {
        try {
            playerAnimUp = new BufferedImage[3];
            playerAnimDown = new BufferedImage[3];
            playerAnimRight = new BufferedImage[3];
            playerAnimLeft = new BufferedImage[3];

            /*BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/textur.png"));


            for (int i = 0; i < 3; i++) {
                playerAnimLeft[i] = spriteSheet.getSubimage(i * 16, 0, 16, 16);
                playerAnimRight[i] = spriteSheet.getSubimage(i * 16, 16, 16, 16);
                playerAnimDown[i] = spriteSheet.getSubimage((i + 3) * 16, 0, 16, 16);
                playerAnimUp[i] = spriteSheet.getSubimage((i + 3) * 16, 16, 16, 16);
                bombAnim[i] = spriteSheet.getSubimage(i * 16, 3 * 16, 16, 16);
            }*/
             view = ImageIO.read(getClass().getResourceAsStream("/textur.png"));
            for (int i = 0; i < 3; i++) {
                playerAnimLeft[i] = view.getSubimage(i * 16, 0, 16, 16);
                playerAnimRight[i] = view.getSubimage(i * 16, 16, 16, 16);
                playerAnimDown[i] = view.getSubimage((i + 3) * 16, 0, 16, 16);
                playerAnimUp[i] = view.getSubimage((i + 3) * 16, 16, 16, 16);

            }
            image=playerAnimDown[1];

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
