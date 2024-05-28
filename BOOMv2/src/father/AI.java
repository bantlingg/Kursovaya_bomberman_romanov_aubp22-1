package father;

import main.Arena;
import main.Keyhandler;
import main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class AI extends Father{
    Arena arena;
    int[][] xyRunOut={{0 , 0}, {0 , 0}} ;
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

    public int[][] mem = new int[][]{
            {1,1},
            {1,1},
            {1,1},
    };
    private int t=0;
    private int WASDai=1;


    public AI(Arena arena,Keyhandler WASD,Map map){
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
        speed=4;

    }
    private int[] chekEnemy(){

        for (int i = 0; i < arena.rows; i++) {
            for (int j = 0; j < arena.columns; j++) {
                if (map.scene[i][j] == 12) {
                    return new int[]{i, j};

                }

            }}return new int[]{0, 0};
    }
    private int[] chekBomb(){

        for (int i = 0; i < arena.rows; i++) {
            for (int j = 0; j < arena.columns; j++) {
                if ((map.scene[i][j] == 3)|| (map.scene[i][j] == 4)) {
                    return new int[]{i, j};

                }

            }}return new int[]{0, 0};
    }

    public int[][] chzh(int yyy,int xxx) {
        int[][] ints;
       // int yyy=xx;
       // int xxx=yy;
        if (map.scene[yyy - 1][xxx] == 0) {
            if (map.scene[yyy - 1][xxx - 1] == 0) {
                ints = new int[][]{{yyy - 1, xxx}, {yyy - 1, xxx-1}};
                return ints;
            }if(yyy>=2){
                if (map.scene[yyy - 2][xxx] == 0) {
                    ints = new int[][]{{yyy - 1, xxx}, {yyy - 2, xxx}};
                    return ints;
                }}
            if (map.scene[yyy - 1][xxx + 1] == 0) {
                ints = new int[][]{{yyy - 1, xxx}, {yyy - 1, xxx+1}};
                return ints;
            }

        }
        if (map.scene[yyy ][xxx-1] == 0) {
            if (map.scene[yyy - 1][xxx - 1] == 0) {
                ints = new int[][]{{yyy , xxx-1}, {yyy-1 , xxx- 1}};
                return ints;
            }if(xxx>=2){
                if (map.scene[yyy ][xxx-2] == 0) {
                    ints = new int[][]{{yyy , xxx-1}, {yyy , xxx- 2}};
                    return ints;
                }}
            if (map.scene[yyy + 1][xxx - 1] == 0) {
                ints = new int[][]{{yyy , xxx-1}, {yyy+1 , xxx- 1}};
                return ints;
            }

        }
        if (map.scene[yyy + 1][xxx] == 0) {

            if (map.scene[yyy + 2][xxx] == 0) {
                 ints = new int[][]{{yyy + 1, xxx}, {yyy + 2, xxx}};
                return ints;

            }
            if (map.scene[yyy + 1][xxx + 1] == 0) {
                ints = new int[][]{{yyy + 1, xxx}, {yyy + 1, xxx+1}};
                return ints;

            }
            if (map.scene[yyy + 1][xxx - 1] == 0) {
                ints = new int[][]{{yyy + 1, xxx}, {yyy + 1, xxx-1}};
                return ints;
            }
        }
        if (map.scene[yyy ][xxx+1] == 0) {
            if (map.scene[yyy ][xxx+2] == 0) {
                ints = new int[][]{{yyy , xxx+1}, {yyy , xxx+ 2}};
                return ints;
            }
            if (map.scene[yyy + 1][xxx + 1] == 0) {
                ints = new int[][]{{yyy , xxx+ 1}, {yyy + 1, xxx+ 1}};
                return ints;
            }
            if (map.scene[yyy - 1][xxx + 1] == 0) {
                ints = new int[][]{{yyy , xxx+ 1}, {yyy -1, xxx+ 1}};
                return ints;
            }
        }
        ints = new int[][]{{0 , 0}, {0 , 0}};
        return ints;
    }
    @Override
    public void update(){
        int xb,yb;
        xb= (int) ((x+24) / (16 * arena.scale));


        yb= (int) ((y+24) / (16 * arena.scale));

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
        xb= (int) ((x+24) / (16 * arena.scale));


        yb= (int) ((y+24) / (16 * arena.scale));
        int[] xye ;
            xye=chekEnemy();
            if(map.bomb==1) {
                if ((((xye[1]) * (16 * arena.scale))-1 >= x) && (isFree(x + speed, y))) {
                x += speed;
                    WASDai=2;if((b%2)>0){
                    numspr++;}
            }
            if ((((xye[0]) * (16 * arena.scale))-1 >= y) && (isFree(x, y + speed))) {
                y += speed;
                WASDai=4;if((b%2)>0){
                numspr++;}
            }
            if ((((xye[1]) * (16 * arena.scale))+1 < x) && (isFree(x - speed, y))) {
                x -= speed;
                WASDai=1;if((b%2)>0){
                numspr++;}
            }
            if ((((xye[0]) * (16 * arena.scale))+1 < y) && (isFree(x, y - speed))) {
                y -= speed;
                WASDai=3;if((b%2)>0){
                numspr++;}
            }

        }
        int[] xyb ;
        xyb=chekBomb();
        xb= (int) ((x+47) / (16 * arena.scale));


        yb= (int) ((y+47) / (16 * arena.scale));
        //xyRunOut=chzh(yb,xb);
        if(map.bomb==0) {
            if (((xyRunOut[1][1]*(16 * arena.scale)) < x) && (isFree(x - speed+3, y))) {
                x -= speed;
                WASDai=1;if((b%2)>0){
                numspr++;}
            }else
            if (((xyRunOut[1][1]*(16 * arena.scale)) > x) && (isFree(x + speed-3, y))) {
                x += speed;if((b%2)>0){
                WASDai=2;}
                numspr++;
            }
            if (((xyRunOut[1][0]*(16 * arena.scale)) < y) && (isFree(x, y - speed+3))) {
                y -= speed;
                WASDai=3;if((b%2)>0){
                numspr++;}

            }else

            if (((xyRunOut[1][0]*(16 * arena.scale)) > y) && (isFree(x, y + speed-3))) {
                y += speed;
                WASDai=4;if((b%2)>0){
                numspr++;}
            }
            if(numspr>2) {
                numspr=0;
            }

            if (((xyRunOut[1][0]*(16 * arena.scale))==y)&&((xyRunOut[1][1]*(16 * arena.scale)) == x)){
                if (((map.scene[yb][xb + 1] == 12)) || ((map.scene[yb][xb -1] == 12)) || ((map.scene[yb+1][xb] == 12)) || ((map.scene[yb-1][xb] == 12))) {
                t++;
                xb= (int) ((x+24) / (16 * arena.scale));


                yb= (int) ((y+24) / (16 * arena.scale));
                xyRunOut=chzh(yb,xb);

            }}





        }xb= (int) ((x+24) / (16 * arena.scale));


        yb= (int) ((y+24) / (16 * arena.scale));
        if (map.bomb == 1) {
            if ((map.scene[yb][xb + 1] == 2)||(map.scene[yb][xb + 1] == 12)) {
                map.bomb = 0;
                xyRunOut=chzh(yb,xb);

                 map.scene[yb][xb] = 3;
            }else
            if ((map.scene[yb][xb - 1] == 2)||(map.scene[yb][xb - 1] == 12)) {
                map.bomb = 0;
                xyRunOut=chzh(yb,xb);
                map.scene[yb][xb] = 3;
            }else
            if ((map.scene[yb+1][xb] == 2)||(map.scene[yb+1][xb] == 12)) {
                map.bomb = 0;
                xyRunOut=chzh(yb,xb);
                map.scene[yb][xb] = 3;
            }else
            if ((map.scene[yb-1][xb] == 2)||(map.scene[yb-1][xb] == 12)) {
                map.bomb = 0;
                xyRunOut=chzh(yb,xb);
                map.scene[yb][xb] = 3;
            }
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
    @Override
    public void draw(Graphics2D g2){

        if (WASDai==2) {
            image=playerAnimRight[numspr];

        }
        if (WASDai==1){
            image=playerAnimLeft[numspr];


        }
        if (WASDai==3) {
            image=playerAnimUp[numspr];


        }
        if (WASDai==4) {
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
