package father;

import main.Arena;
import main.Keyhandler;
import main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


public class Enemy extends Father{
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
    Random random;
    Map map;
    int randWASD =3;



    public Enemy(Arena arena,Keyhandler WASD,Map map,Random random){
        this.WASD=WASD;
        this.random = random;

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
        else {

            return false;}
    }
    public boolean isFree(int nextX, int nextY) {
        int size = arena.scale * 16;

        int nextX_1 = (nextX) / size;
        int nextY_1 = (nextY) / size;

        int nextX_2 = (nextX + size - 1) / size;
        int nextY_2 = nextY / size;

        int nextX_3 = nextX / size;
        int nextY_3 = (nextY + size - 1) / size;

        int nextX_4 = (nextX + size - 1) / size;
        int nextY_4 = (nextY + size - 1) / size;

        return !((map.scene[nextY_1][nextX_1] == 1 || map.scene[nextY_1][nextX_1] == 2) ||
                (       map.scene[nextY_2][nextX_2] == 1 || map.scene[nextY_2][nextX_2] == 2) ||
                (map.scene[nextY_3][nextX_3] == 1 || map.scene[nextY_3][nextX_3] == 2) ||
                (map.scene[nextY_4][nextX_4] == 1 || map.scene[nextY_4][nextX_4] == 2)||(map.scene[nextY_1][nextX_1] == 3  ||
                map.scene[nextY_2][nextX_2] == 3) ||
                (map.scene[nextY_3][nextX_3] == 3 ||
                        map.scene[nextY_4][nextX_4] == 3 )||
                (map.scene[nextY_1][nextX_1] == 12  ||
                        map.scene[nextY_2][nextX_2] == 12) ||
                (map.scene[nextY_3][nextX_3] == 12 ||
                        map.scene[nextY_4][nextX_4] == 12 ));
    }

    public void setDef(){
        int size = 16 * arena.scale;
        for(int z = 0; z < 1;) {
            for (int i = 2; i < arena.columns; i++) {
                for (int j = 2; j < arena.rows; j++) {
                    if ((map.scene[j][i] == 0)&&(z<1)&&(random.nextInt(100)<4) ){
                        x = (16 * arena.scale) * i;
                        y = (16 * arena.scale) * j;
                        if ((map.scene[j][i+1] != 1)){map.scene[j][i+1] = 0;}else
                        if ((map.scene[j+1][i] != 1)){map.scene[j+1][i] = 0;} else
                        if ((map.scene[j][i-1] != 1)){map.scene[j][i-1] = 0;}else
                        if ((map.scene[j-1][i] != 1)){map.scene[j-1][i] = 0;}
                        speed = 1;
                        z++;
                    }
                }
            }

        }
    }
    @Override
    public void update(){if(isAlive()){
        int xb,yb;
        xb= (int) ((x+24) / (16 * arena.scale));
        System.out.println(xb);

        yb= (int) ((y+24) / (16 * arena.scale));
        System.out.println(yb);
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
        if(map.scene[yb][xb] ==12 ){
        map.scene[yb][xb] = 0;}
        xb= (int) ((x) / (16 * arena.scale));


        yb= (int) ((y) / (16 * arena.scale));
        if ((randWASD==0 && isFree(x+speed,y))||(randWASD==0&&map.scene[yb][xb]==3&&(!isFree(x+speed,y)))) {
            if(map.scene[yb][xb]==3){randWASD=1;
                x -= speed;}
            else {
            x += speed;}

            if((b%2)>0){
                numspr++;}
        }else
        if ((randWASD==1&& isFree(x-speed,y))||(randWASD==1&&map.scene[yb][xb]==3&&(!isFree(x-speed,y)))) {
            if(map.scene[yb][xb]==3){randWASD=0;
                x += speed;}
            else {
            x -= speed;}
            if((b%2)>0){
                numspr++;}
        }else
        if ((randWASD==2&& isFree(x,y-speed))||(randWASD==2&&map.scene[yb][xb]==3&&(!isFree(x,y-speed)))) {//||(randWASD==2&&map.scene[yb][xb]==3&&(!isFree(x,y-speed)))
            if(map.scene[yb][xb]==3){randWASD=3;
                y += speed;}
            else {
            y -= speed;}
            if((b%2)>0){
                numspr++;}
        }else
        if ((randWASD==3&& isFree(x,y+speed))||(randWASD==3&&map.scene[yb][xb]==3&&(!isFree(x,y+speed)))) {
            if(map.scene[yb][xb]==3){randWASD=2;
                y -= speed;}
            else {
            y += speed;}
            if((b%2)>0){
                numspr++;}
        }else{

        randWASD = random.nextInt(4);}
        if(numspr>2) {
            numspr=0;
        }

        if ((map.scene[yb][xb]==0)){ if(isAlive()) {
            xb= (int) ((x+24) / (16 * arena.scale));

            yb= (int) ((y+24) / (16 * arena.scale));

            map.scene[yb][xb] = 12;
        }}
        b++;

    }}
    @Override
    public void draw(Graphics2D g2){if(isAlive()){
        //g2.setColor(Color.darkGray);
        //g2.fillRect(x,y,arena.titleSize,arena.titleSize);
        //BufferedImage image=null;

            image=playerAnimLeft[numspr];




        g2.drawImage(image,x,y,arena.titleSize,arena.titleSize,null);
    }}
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
                playerAnimLeft[i] = view.getSubimage(i * 16, 240, 16, 16);


            }
            image=playerAnimDown[1];

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
