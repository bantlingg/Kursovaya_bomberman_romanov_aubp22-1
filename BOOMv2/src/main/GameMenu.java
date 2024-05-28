package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameMenu extends JPanel {
    private Image backgroundImage;
    private Main main;

    public GameMenu(Main main) {
        this.main = main;
        try {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/menu.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(700, 700));

        JButton startButton = new JButton("Start Game");
        JButton optionsButton = new JButton("Bot play");
        JButton exitButton = new JButton("Exit");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.switchToArena();
            }
        });
        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.switchToArenaAI();

                // Возможные действия для кнопки "Options"
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(290));
        add(Box.createVerticalGlue());
        add(startButton);
        add(Box.createVerticalStrut(20));
        add(optionsButton);
        add(Box.createVerticalStrut(20));
        add(exitButton);
        add(Box.createVerticalGlue());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

