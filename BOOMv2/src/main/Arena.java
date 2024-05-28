package main;

import father.Enemy;
import father.AI;
import father.Father;
import father.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Arena extends JPanel implements Runnable{
    private final boolean state;
    BufferedImage GameOver  ;

    final int title=16;
    int i=3;
    public int scale=3;
    public final int titleSize=title*scale,rows = 13, columns = 15;

    Thread arenaThread;
    Keyhandler WASD=new Keyhandler();
    Random random = new Random();
    Map map=new Map(this);
    Father player=new AI(this,WASD,map);
    Enemy enemy[]=new Enemy[100];
    private int b=1;
    private int b1=0;
    Main main;
    private int c=3;

    public Arena(Main main,boolean state){
        this.state=state;
        if(state){
         player=new AI(this,WASD,map);}else {player=new Player(this,WASD,map);}
        this.main=main;
        this.setPreferredSize(new Dimension(titleSize*columns,titleSize*rows));
        this.setBackground(new Color(56, 135, 0));
        //?????????????????
        this.setDoubleBuffered(true);
        this.addKeyListener(WASD);
        //?????????????????
        this.setFocusable(true);
        for(int i=0;i<1;i++){
            enemy[i]= new Enemy(this,WASD,map,random);
        }
    }
    public void Arena1(){b++; b1=0;
         map=new Map(this);for(int i=0;i<b+1;i++){

            enemy[i]= new Enemy(this,WASD,map,random);
        }

        if(state){
            player=new AI(this,WASD,map);}else {player=new Player(this,WASD,map);}
        //?????????????????


    }
    public void start(){
        arenaThread=new Thread(this);
        arenaThread.start();


    }
    @Override
    public void run() {
        while(arenaThread!=null){
            long time=System.nanoTime();
            update();
            repaint();
            try {
                Thread.sleep(1000 / 30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
    }

    public void update(){
        if(player.isAlive()) {
            player.update();
            map.updete();
            for(int i=0;i<b;i++){
                if(enemy[i]!=null){

                enemy[i].update();}
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(map.scene[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("\n");
        System.out.println("\n");
       /* if (WASD.right) {
            playerX += speed;

        }
        if (WASD.left) {
            playerX -= speed;

        }
        if (WASD.up) {
            playerY -= speed;

        }
        if (WASD.down) {
            playerY += speed;

        }*/
    }

    public void paintComponent (Graphics g)
        {
            super.paintComponent (g);
            Graphics2D g2 = (Graphics2D)g;
            //g2.setColor(Color.white);
            map.draw(g2);
            //g2.fillRect (playerX, playerY, titleSize, titleSize);
            player.draw(g2);
            for(int i=0;i<b;i++) {
                if (enemy[i].isAlive()) {
                    enemy[i].draw(g2);
                }
            }
            if(!player.isAlive()) {
                Music GOMusic = new Music();
                GOMusic.playMusic("C:\\Users\\Nikita\\IdeaProjects\\BOOMv2\\src\\GameOver.wav",false);

                try {
                    GameOver=ImageIO.read(getClass().getResourceAsStream("/gameover.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } i--;
                if(i<2){

                try {
                    Thread.sleep(1800);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }main.switchToMenu();

                }
                g2.drawImage(GameOver, (titleSize*columns-200)/2, (titleSize*rows-148)/2, null);




            }
            for(int i=0;i<b;i++) {
                if (!enemy[i].isAlive()) {
                    b1++;

                }
            }
            if(b1==b){c--;b1=0;
                Music GOMusic = new Music();
                GOMusic.playMusic("C:\\Users\\Nikita\\IdeaProjects\\BOOMv2\\src\\win.wav",false);
                try {
                    GameOver=ImageIO.read(getClass().getResourceAsStream("/next.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g2.drawImage(GameOver, (titleSize*columns-360)/2, (titleSize*rows-160)/2, null);
                if(c<1) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    c=3;
                    this.Arena1();
                }
            }b1=0;

                g2.dispose();

        }
}
