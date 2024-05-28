package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import father.Player;
import main.*;

import javax.imageio.ImageIO;

public class Map {
    BufferedImage[] bombAnim,fontExplosion, rightExplosion, leftExplosion, upExplosion, downExplosion;
    Arena arena;
    Player player;
    int[] index={0,0};
    public int bomb=1;
     int a=0,a1=0,b=0,b1=0,t=0;
    Graphics2D g2;
    public int[][] scene = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
    BufferedImage view,concreteTile,blockTile,bombTile;
    public Map(Arena arena){
        this.arena=arena;
        this.g2=g2;
        this.player=player;
        randMap();
        try {
            mapTexture();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void randMap(){
       /* for (int b = 0; b < 4; ) {

            for (int i = 0; i < arena.rows; i++) {
                for (int j = 0; j < arena.columns; j++) {
                    if (scene[i][j] == 0) {
                        if (b < 4) {
                            if (new Random().nextInt(100) < 1) {
                                scene[i][j] = 3;
                                b++;
                            }
                        }
                    }
                }
            }
        }*/
        for (int i = 0; i < arena.rows; i++) {
            for (int j = 0; j < arena.columns; j++) {
                if (scene[i][j] == 0) {
                    if (new Random().nextInt(10) < 4) {
                        scene[i][j] = 2;
                    }
                }
            }
        }
        scene[1][1] = 0;
        scene[2][1] = 0;
        scene[1][2] = 0;
        scene[1][3] = 0;
        scene[3][1] = 0;

    }
    public void mapTexture() throws IOException {
        bombAnim = new BufferedImage[3];
        fontExplosion = new BufferedImage[4];
        rightExplosion = new BufferedImage[4];
        leftExplosion = new BufferedImage[4];
        upExplosion = new BufferedImage[4];
        downExplosion = new BufferedImage[4];
        view = ImageIO.read(getClass().getResourceAsStream("/textur.png"));
         concreteTile = view.getSubimage(4 * 16, 3 * 16, 16, 16);
        blockTile = view.getSubimage(3 * 16, 3 * 16, 16, 16);
        bombTile= view.getSubimage(16, 3 * 16, 16, 16);
        for (int i = 0; i < 3; i++) {

            bombAnim[i] = view.getSubimage(i * 16, 3 * 16, 16, 16);
        }

        fontExplosion[0] = view.getSubimage(2 * 16, 6 * 16, 16, 16);
        fontExplosion[1] = view.getSubimage(7 * 16, 6 * 16, 16, 16);
        fontExplosion[2] = view.getSubimage(2 * 16, 11 * 16, 16, 16);
        fontExplosion[3] = view.getSubimage(7 * 16, 11 * 16, 16, 16);

        rightExplosion[0] = view.getSubimage(4 * 16, 6 * 16, 16, 16);
        rightExplosion[1] = view.getSubimage(9 * 16, 6 * 16, 16, 16);
        rightExplosion[2] = view.getSubimage(4 * 16, 11 * 16, 16, 16);
        rightExplosion[3] = view.getSubimage(9 * 16, 11 * 16, 16, 16);

        leftExplosion[0] = view.getSubimage(0, 6 * 16, 16, 16);
        leftExplosion[1] = view.getSubimage(5 * 16, 6 * 16, 16, 16);
        leftExplosion[2] = view.getSubimage(0, 11 * 16, 16, 16);
        leftExplosion[3] = view.getSubimage(5 * 16, 11 * 16, 16, 16);

        upExplosion[0] = view.getSubimage(2 * 16, 4 * 16, 16, 16);
        upExplosion[1] = view.getSubimage(7 * 16, 4 * 16, 16, 16);
        upExplosion[2] = view.getSubimage(2 * 16, 9 * 16, 16, 16);
        upExplosion[3] = view.getSubimage(7 * 16, 9 * 16, 16, 16);

        downExplosion[0] = view.getSubimage(2 * 16, 8 * 16, 16, 16);
        downExplosion[1] = view.getSubimage(7 * 16, 8 * 16, 16, 16);
        downExplosion[2] = view.getSubimage(2 * 16, 13 * 16, 16, 16);
        downExplosion[3] = view.getSubimage(7 * 16, 13 * 16, 16, 16);
    }
    public void updete(){




    }
    public void  draw(Graphics2D g2){
        int size = 16 * arena.scale;
        for (int i = 0; i < arena.columns; i++) {
            for (int j = 0; j < arena.rows; j++) {
                if (scene[j][i] == 1) {
                    g2.drawImage(blockTile, i * size, j * size, size, size, null);
                } else if (scene[j][i] == 2) {
                    g2.drawImage(concreteTile, i * size, j * size, size, size, null);

                }
                if (scene[j][i] == 3) {

                    g2.drawImage(bombAnim[a], i * size, j * size, size, size, null);
                    if((b%3)==0){a++;}
                    if(a==3){a=0;t++;}
                    b++;
                    if((t%4==0)&&(t!=0)){
                        t=0;
                        b=0;
                        scene[j][i] =4;
                        if (scene[j+1][i] == 2) {
                            scene[j+1][i] = 0;
                        }
                        if (scene[j-1][i] == 2) {
                            scene[j-1][i] = -0;
                        }
                        if (scene[j][i+1] == 2) {
                            scene[j][i+1] = -0;
                        }
                        if (scene[j][i-1] == 2) {
                            scene[j][i-1] = -0;

                        }

                        //bomb=1;
                        index[0]=j;
                        index[1]=i;



                    }


                }
                if (scene[j][i] == 4) {
                    Music bombMusic = new Music();
                    bombMusic.playMusic("C:\\Users\\Nikita\\IdeaProjects\\BOOMv2\\src\\bomb.wav",false);
                    bombMusic.setVolume(0.7555F);



                    g2.drawImage(fontExplosion[a1], i * size, j * size, size, size, null);
                    if (scene[j][i+1] != 1) {
                        g2.drawImage(rightExplosion[a1], (i + 1) * size, j * size, size, size, null);
                    }
                    if (scene[j][i-1]!=1) {
                        g2.drawImage(leftExplosion[a1], (i - 1) * size, j * size, size, size, null);
                    }
                    if (scene[j-1][i] != 1) {
                        g2.drawImage(upExplosion[a1], i * size, (j - 1) * size, size, size, null);
                    }
                    if (scene[j+1][i] != 1) {
                        g2.drawImage(downExplosion[a1], i * size, (j+1) * size, size, size, null);
                    }
                    b1++;
                    if((b1%4==0)&&(b1!=0)) {
                        a1++;
                        b1=0;
                    }
                    if(a1==3){a1=0;scene[j][i] =0;bomb=1;



                    }
                }







            }

        }

    }


}
