package com.unitfactory.mbortnic.view.gui;

import com.unitfactory.mbortnic.controller.GuiMap;
import com.unitfactory.mbortnic.model.players.Player;
import com.unitfactory.mbortnic.model.players.PlayerOperations;
import com.unitfactory.mbortnic.reader.Reader;
import com.unitfactory.mbortnic.writer.Writer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiDisplay extends JFrame {

    public String hero;
    public String art;
    public String playerData;
    public String[] ch = null;
    public int type;
    public final JFrame createF = new JFrame("Create Player");
    public final JFrame selectFr = new JFrame("Select Player");
    public final JFrame statisticsF = new JFrame("Player Statistics");
    public final JFrame swingyF = new JFrame("Ongoing Game");
    public static JFrame endGameF = new JFrame("Game Ended");
    public final JRadioButton demon = new JRadioButton("Demon");
    public final JRadioButton undead = new JRadioButton("Undead");
    public final String[] elements = Reader.readLines();
    public final JList playerList = new JList(elements);
    public JTextField playerName;
    public JTextArea textArea;
    public Player player = new Player();
    public GuiMap map;

    public GuiDisplay() {}

    public void createView(){
        JLabel label;
        JLabel label1;
        JButton helloButton;
        final JFrame helloF = new JFrame("Creation of your Player");
        label = new JLabel("Create your Player");
        label.setBackground(Color.blue);
        label.setBounds(220,200, 200,30);
        label1 = new JLabel("Enter your player name");
        label1.setBounds(210,240, 200,30);
        label.setBackground(Color.GREEN);
        playerName = new JTextField();
        playerName.setBounds(200, 280, 200, 30);
        playerName.setCaretColor(Color.CYAN);
        helloButton = new JButton("ENTER");
        helloButton.setBounds(200, 320, 200, 30);
        helloButton.setBackground(Color.red);
        helloF.add(label);
        helloF.setBackground(Color.yellow);
        helloF.add(label1);
        helloF.add(playerName);
        helloF.add(helloButton);
        helloF.setSize(600,600);
        helloF.setLocationRelativeTo(null);
        helloF.setLayout(null);
        helloF.setVisible(true);
        helloF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        helloButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                hero = playerName.getText();
                hero = hero.trim();

                if (hero.length() > 0){
                    ch = hero.split("\\s");

                    if (ch != null)
                        hero = String.join("_", ch);

                    if (hero.isEmpty())
                        JOptionPane.showMessageDialog(null, "Enter name! It cannot be empty!");
                    else{
                        createPlayerView();
                        helloF.setVisible(false);
                        helloF.dispose();
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Blanks are not allowed!");

            }
        });
    }

    public void guiView(){
        JButton createPlayerButton;
        JButton selectPlayerButton;
        final JFrame playerF = new JFrame("Welcome to the game of the bravest - SWINGY");
        createPlayerButton = new JButton("Create your Player");
        createPlayerButton.setBackground(Color.yellow);
        createPlayerButton.setBounds(210,240, 200,30);
        selectPlayerButton = new JButton("Select your Player");
        selectPlayerButton.setBackground(Color.MAGENTA);
        selectPlayerButton.setBounds(210,280, 200,30);
        playerF.add(createPlayerButton);
        playerF.add(selectPlayerButton);
        playerF.setSize(600,600);
        playerF.setLocationRelativeTo(null);
        playerF.setLayout(null);
        playerF.setVisible(true);
        playerF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createPlayerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                createView();
                playerF.dispose();
            }
        });

        selectPlayerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPlayerView();
                playerF.setVisible(false);
                playerF.dispose();
            }
        });
    }

    public void selectPlayerView(){
        JLabel label = new JLabel("Select hero from existing ones");;
        JButton enter;
        JButton quit;

        label.setBounds(20, 20, 200, 30);

        playerList.setBounds(20, 50, 350, 520);
        enter = new JButton("Continue");
        enter.setBounds(400, 50, 100, 30);
        quit = new JButton("Quit Game");
        quit.setBounds(400, 105, 100, 30);

        playerList.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                playerData = playerList.getSelectedValue().toString();
                player = PlayerOperations.playerToDB(playerData);

            }
        });

        enter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playerData == null)
                    JOptionPane.showMessageDialog(null, "Select your hero at first!");
                else{
                    game();
                    selectFr.setVisible(false);
                    selectFr.dispose();
                }

            }
        });

        quit.addActionListener(e -> selectFr.dispose());

        selectFr.add(label);
        selectFr.add(enter);
        selectFr.add(quit);
        selectFr.add(playerList);
        selectFr.setSize(600, 600);
        selectFr.setLocationRelativeTo(null);
        selectFr.setLayout(null);
        selectFr.setVisible(true);
        selectFr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void createPlayerView(){
        ButtonGroup group = new ButtonGroup();
        JButton enter = new JButton("CONTINUE");
        demon.setBounds(210, 240, 200, 30);
        undead.setBounds(210, 260, 200, 30);
        enter.setBounds(210,300, 200,30);

        group.add(demon);
        group.add(undead);

        createF.add(demon);
        createF.add(undead);
        createF.add(enter);
        createF.setSize(600,600);
        createF.setLocationRelativeTo(null);
        createF.setLayout(null);
        createF.setVisible(true);
        createF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        enter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (demon.isSelected())
                    type = 2;
                else if (undead.isSelected())
                    type = 1;
                playerStatistics();
                createF.setVisible(false);
                createF.dispose();
            }
        });

    }

    public void playerStatistics(){
        player = PlayerOperations.newPlayer(hero, type);
        JLabel l1;
        JLabel l2;
        JLabel l3;
        JLabel l4;
        JLabel l5;
        JLabel l6;
        JLabel l7;
        JLabel l8;
        JButton enter;

        l8 = new JLabel("Player Statistics");
        l8.setBounds(200,200, 200,30);
        l1 = new JLabel("Hero: " + hero);
        l1.setBounds(200,220, 200,30);
        String heroType;
        l2 = new JLabel("Hero: " + (heroType = player.getStatistics().getType()));
        l2.setBounds(200,240, 200,30);
        int level;
        l3 = new JLabel("Level: " +  (level = player.getStatistics().getLvl()));
        l3.setBounds(200,260, 200,30);
        int attack;
        l4 = new JLabel("Attack: " + (attack = player.getStatistics().getAttack()));
        l4.setBounds(200,280, 200,30);
        int defense;
        l5 = new JLabel("Defense: " + (defense = player.getStatistics().getDefence()));
        l5.setBounds(200,300, 200,30);
        int hitpoints;
        l6 = new JLabel("Hitpoints: " + (hitpoints = player.getStatistics().getHp()));
        l6.setBounds(200,320, 200,30);
        l7 = new JLabel("Artifact: " + (art = player.getArtifact().getType()));
        l7.setBounds(200,340, 200,30);
        enter = new JButton("Enter");
        enter.setBounds(200,370, 200,30);

        statisticsF.add(l1);
        statisticsF.add(l2);
        statisticsF.add(l3);
        statisticsF.add(l4);
        statisticsF.add(l5);
        statisticsF.add(l6);
        statisticsF.add(l7);
        statisticsF.add(l8);
        statisticsF.add(enter);
        statisticsF.setSize(600, 600);
        statisticsF.setLocationRelativeTo(null);
        statisticsF.setLayout(null);
        statisticsF.setVisible(true);
        statisticsF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        enter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playerData = player.getStatistics().getType() + " " +
                        hero + " " + player.getStatistics().getLvl() + " " +
                        player.getStatistics().getAttack() + " " + player.getStatistics().getAttack() +
                        " " + player.getStatistics().getHp() + " " + player.getStatistics().getExp() +
                        " " + art.toUpperCase();

                Writer.writeToPlayersFile(playerData);
                Writer.close();
                game();
                statisticsF.setVisible(false);
                statisticsF.dispose();
            }
        });
    }

    public void game(){
        JButton north;
        JButton south;
        JButton east;
        JButton west;

        map = new GuiMap(player, swingyF);
        textArea = map.showMap();

        north = new JButton("NORTH");
        north.setBounds(20,580, 100, 30);
        south = new JButton("SOUTH");
        south.setBounds(140,580, 100, 30);
        east = new JButton("EAST");
        east.setBounds(280,580, 100, 30);
        west = new JButton("WEST");
        west.setBounds(400,580, 100, 30);

        north.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updatePlayerPos(0, -1);
            }
        });

        south.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updatePlayerPos(0, 1);
            }
        });

        east.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updatePlayerPos(1, 0);
            }
        });

        west.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updatePlayerPos(-1, 0);
            }
        });

        textArea.setBounds(20,90, 800, 480);
        swingyF.add(textArea);
        swingyF.add(north);
        swingyF.add(south);
        swingyF.add(east);
        swingyF.add(west);
        swingyF.setSize(850, 650);
        swingyF.setLocationRelativeTo(null);
        swingyF.setLayout(null);
        swingyF.setVisible(true);
        swingyF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void endGame(){
        JLabel label;
        JButton close;

        label = new JLabel("Going on with out adventure");
        label.setBounds(200,240, 400,30);
        close = new JButton("GAME COMPLETED");
        close.setBounds(210,280, 200,30);

        close.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                endGameF.dispose();
                System.exit(0);
            }
        });

        endGameF.add(label);
        endGameF.add(close);
        endGameF.setSize(600, 600);
        endGameF.setLocationRelativeTo(null);
        endGameF.setLayout(null);
        endGameF.setVisible(true);
        endGameF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
