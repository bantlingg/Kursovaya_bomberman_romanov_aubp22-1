package main;

import javax.swing.*;

public class Main {
    private JFrame window;
    private Arena arena;
    private GameMenu menu;

    public Main() {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Bomberman");

        //arena = new Arena(this,false);
        menu = new GameMenu(this);

        window.add(menu);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    public void switchToMenu() {
        window.remove(arena);
        window.add(menu);


        window.revalidate();
        window.repaint();
        arena.requestFocusInWindow();
        arena.start();
    }
    public void switchToArena() {
        arena = new Arena(this,false);
        window.remove(menu);
        window.add(arena);
        window.add(arena);

        window.revalidate();
        window.repaint();
        arena.requestFocusInWindow();
        arena.start();
    }
    public void switchToArenaAI() {
        arena = new Arena(this,true);
        window.remove(menu);
        window.add(arena);
        window.add(arena);

        window.revalidate();
        window.repaint();
        arena.requestFocusInWindow();
        arena.start();
    }

    public static void main(String[] args) {
        Music musicPlayer = new Music();
        musicPlayer.playMusic("C:\\Users\\Nikita\\IdeaProjects\\BOOMv2\\src\\music.wav",true);
        musicPlayer.setVolume(0.9F);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }

        });
    }
}
/*package main;

import javax.swing.JFrame;
public class Main {
    public static void main(String[] args) {


        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Bomberman ");
        Arena arena=new Arena();
        GameMenu menu =new GameMenu();
        window.add(menu);


        //window.add(arena);
        window.pack();
        window.setLocationRelativeTo (null);
        window.setVisible (true);
        arena.start();



    }
*/