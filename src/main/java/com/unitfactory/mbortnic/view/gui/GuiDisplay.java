package com.unitfactory.mbortnic.view.gui;

import com.unitfactory.mbortnic.controller.GuiMap;
import com.unitfactory.mbortnic.messages.Messages;
import com.unitfactory.mbortnic.model.players.Player;
import com.unitfactory.mbortnic.model.players.PlayerOperations;
import com.unitfactory.mbortnic.reader.Reader;
import com.unitfactory.mbortnic.writer.Writer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiDisplay extends JFrame {

    public int type;
    public String hero;
    public String playerData;
    public String[] ch = null;
    public Player player = new Player();

    public GuiDisplay() {}

    public void createView() {
        JLabel label = new JLabel("Create Player");
        JLabel label1 = new JLabel("Enter player name");
        JButton helloButton = new JButton("ENTER");
        JFrame helloF = new JFrame("Creation of your Player");
        JButton backButton = new JButton("Back");
        JTextField playerName = new JTextField();

        label.setBounds(165, 95, 200, 35);
        label.setBackground(Color.BLACK);
        label.setForeground(Color.GREEN);
        label.setOpaque(true);
        label.setFont(new Font("Courier", Font.PLAIN, 16));

        label1.setBounds(190, 170, 150, 30);
        label1.setBackground(Color.BLACK);
        label1.setForeground(Color.GREEN);
        label1.setOpaque(true);
        label1.setFont(new Font("Courier", Font.PLAIN, 14));

        playerName.setBounds(130, 210, 250, 30);
        playerName.setCaretColor(Color.GREEN);
        playerName.setBackground(Color.BLACK);
        playerName.setForeground(Color.GREEN);
        playerName.setText("Enter name here");
        Border border = BorderFactory.createLineBorder(Color.GREEN, 2);
        playerName.setBorder(border);

        helloButton.setBounds(155, 260, 200, 30);
        helloButton.setBackground(Color.GREEN);
        helloButton.setOpaque(true);
        helloButton.setBorderPainted(false);

        backButton.setBounds(200, 340, 100, 40);
        backButton.setBackground(Color.GREEN);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);

        helloF.add(label);
        helloF.add(label1);
        helloF.add(playerName);
        helloF.add(helloButton);
        helloF.add(backButton);
        helloF.setBackground(Color.yellow);
        helloF.setSize(500, 500);
        helloF.setBackground(Color.BLACK);
        helloF.getContentPane().setBackground(Color.BLACK);
        helloF.setLocationRelativeTo(null);
        helloF.setLayout(null);
        helloF.setVisible(true);
        helloF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        helloF.setResizable(false);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiView();
                helloF.dispose();
            }
        });

        helloButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                hero = playerName.getText();
                hero = hero.trim();
                if (hero.length() > 0){
                    ch = hero.split("\\s");
                    if (ch != null) {
                        hero = String.join("_", ch);
                    }
                    if (hero.isEmpty()) {
                        JOptionPane.showMessageDialog(null, Messages.INVALID_NAME);
                    }
                    else {
                        createPlayerView();
                        helloF.setVisible(false);
                        helloF.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, Messages.INVALID_NAME);
                }
            }
        });
    }

    public void guiView() {
        JButton createPlayerButton = new JButton("Create Player");
        JButton selectPlayerButton = new JButton("Select Player");
        JFrame playerF = new JFrame("SWINGY");

        createPlayerButton.setBounds(180, 170, 220, 40);
        createPlayerButton.setBackground(Color.GREEN);
        createPlayerButton.setOpaque(true);
        createPlayerButton.setBorderPainted(false);
        createPlayerButton.setFont(new Font("Courier", Font.PLAIN, 16));
//        createPlayerButton.setForeground(Color.BLACK);

        selectPlayerButton.setBounds(180, 260, 220, 40);
        selectPlayerButton.setBackground(Color.GREEN);
        selectPlayerButton.setOpaque(true);
        selectPlayerButton.setBorderPainted(false);
        selectPlayerButton.setFont(new Font("Courier", Font.PLAIN, 16));

        playerF.add(createPlayerButton);
        playerF.add(selectPlayerButton);
        playerF.setSize(500, 500);
        playerF.setBackground(Color.BLACK);
        playerF.getContentPane().setBackground(Color.BLACK);
        playerF.setLocationRelativeTo(null);
        playerF.setLayout(null);
        playerF.setVisible(true);
        playerF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playerF.setResizable(false);

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

    public void selectPlayerView() {
        String[] elements = Reader.readLines();
        JList playerList = new JList(elements);
        JLabel label = new JLabel("Select hero from existing ones");
        JFrame selectFr = new JFrame("Select Player");
        JButton enterButton = new JButton("Continue");
        JButton quit = new JButton("Quit");
        JButton backButton = new JButton("Back");

        label.setBounds(20, 20, 200, 30);
        label.setBounds(160, 20, 200, 30);
        label.setBackground(Color.GREEN);
        label.setOpaque(true);
        label.setFont(new Font("Courier", Font.PLAIN, 15));

        playerList.setBounds(20, 50, 350, 520);
        playerList.setBounds(20, 70, 320, 360);
        playerList.setBackground(Color.GREEN);
        playerList.setOpaque(true);
        playerList.setFont(new Font("Courier", Font.PLAIN, 12));
        playerList.setSelectedIndex(0);

        enterButton.setBounds(365, 70, 100, 40); // 365 70 100 40
        enterButton.setBackground(Color.GREEN);
        enterButton.setOpaque(true);
        enterButton.setBorderPainted(false);
        enterButton.setFont(new Font("Courier", Font.PLAIN, 12));

        quit.setBounds(365, 390, 100, 40);
        quit.setBackground(Color.GREEN);
        quit.setOpaque(true);
        quit.setBorderPainted(false);
        quit.setFont(new Font("Courier", Font.PLAIN, 13));

        backButton.setBounds(365, 170, 100, 40); // 365 390 100 40
        backButton.setBackground(Color.GREEN);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setFont(new Font("Courier", Font.PLAIN, 13));

        selectFr.add(label);
        selectFr.add(enterButton);
        selectFr.add(quit);
        selectFr.add(playerList);
        selectFr.add(backButton);
        selectFr.setSize(500, 500);
        selectFr.setBackground(Color.BLACK);
        selectFr.getContentPane().setBackground(Color.BLACK);
        selectFr.setLocationRelativeTo(null);
        selectFr.setLayout(null);
        selectFr.setVisible(true);
        selectFr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        selectFr.setResizable(false);

        playerList.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                playerData = playerList.getSelectedValue().toString();
                player = PlayerOperations.playerToDB(playerData);
            }
        });

        enterButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playerData == null) {
                    JOptionPane.showMessageDialog(null, "Select your hero at first!");
                } else {
                    game();
                    selectFr.setVisible(false);
                    selectFr.dispose();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiView();
                selectFr.dispose();
            }
        });

        quit.addActionListener(e -> selectFr.dispose());

    }

    public void createPlayerView() {
        ButtonGroup buttonGroup = new ButtonGroup();
        JLabel race = new JLabel("CHOOSE YOUR RACE");
        JRadioButton archer = new JRadioButton("Archer");
        JRadioButton battleMage = new JRadioButton("BattleMage");
        JFrame createF = new JFrame("Create Player");
        JButton enterButton = new JButton("CONTINUE");
        JButton backButton = new JButton("Back");

        race.setBounds(165, 55, 200, 30);
        race.setForeground(Color.GREEN);
        race.setFont(new Font("Courier", Font.PLAIN, 16));

        archer.setBounds(200, 130, 100, 40);
        archer.setForeground(Color.GREEN);
        archer.setBackground(Color.BLACK);
        archer.setFont(new Font("Courier", Font.PLAIN, 14));
        archer.setSelected(true);

        battleMage.setBounds(200, 160, 150, 40);
        battleMage.setForeground(Color.GREEN);
        battleMage.setBackground(Color.BLACK);
        battleMage.setFont(new Font("Courier", Font.PLAIN, 14));

        enterButton.setBounds(200, 230, 150, 40);
        enterButton.setBackground(Color.GREEN);
        enterButton.setOpaque(true);
        enterButton.setBorderPainted(false);
        enterButton.setFont(new Font("Courier", Font.PLAIN, 16));

        backButton.setBounds(200, 360, 100, 40);
        backButton.setBackground(Color.GREEN);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setFont(new Font("Courier", Font.PLAIN, 16));

        buttonGroup.add(archer);
        buttonGroup.add(battleMage);

        createF.add(archer);
        createF.add(battleMage);
        createF.add(enterButton);
        createF.add(backButton);
        createF.add(race);
        createF.setSize(500, 500);
        createF.setBackground(Color.BLACK);
        createF.getContentPane().setBackground(Color.BLACK);
        createF.setLocationRelativeTo(null);
        createF.setLayout(null);
        createF.setVisible(true);
        createF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createF.setResizable(false);

        enterButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (archer.isSelected()) {
                    type = 1;
                } else if (battleMage.isSelected()) {
                    type = 2;
                }
                playerStatistics();
                createF.setVisible(false);
                createF.dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createView();
                createF.dispose();
            }
        });

    }

    public void playerStatistics(){
        player = PlayerOperations.newPlayer(type, hero);
        String heroType;
        int lvl;
        int attack;
        int defence;
        int hp;
        String art;
        JLabel label1 = new JLabel("Hero: " + hero);
        JLabel label2 = new JLabel("Hero: " + (heroType = player.getStatistics().getType()));
        JLabel label3 = new JLabel("Level: " +  (lvl = player.getStatistics().getLvl()));
        JLabel label4 = new JLabel("Attack: " + (attack = player.getStatistics().getAttack()));
        JLabel label5 = new JLabel("Defense: " + (defence = player.getStatistics().getDefence()));
        JLabel label6 = new JLabel("Hitpoints: " + (hp = player.getStatistics().getHp()));
        JLabel label7 = new JLabel("Artifact: " + (art = player.getArtifact().getType()));
        JLabel label8 = new JLabel("Player Statistics");
        JFrame statisticsF = new JFrame("Player Statistics");
        JButton enterButton = new JButton("Enter");

        label1.setBounds(110, 130, 400, 30);
        label1.setForeground(Color.GREEN);
        label1.setFont(new Font("Courier", Font.PLAIN, 13));
        label2.setBounds(110, 150, 400, 30);
        label2.setForeground(Color.GREEN);
        label2.setFont(new Font("Courier", Font.PLAIN, 13));
        label3.setBounds(110, 170, 400, 30);
        label3.setForeground(Color.GREEN);
        label3.setFont(new Font("Courier", Font.PLAIN, 13));
        label4.setBounds(110, 190, 400, 30);
        label4.setForeground(Color.GREEN);
        label4.setFont(new Font("Courier", Font.PLAIN, 13));
        label5.setBounds(110, 210, 400, 30);
        label5.setForeground(Color.GREEN);
        label5.setFont(new Font("Courier", Font.PLAIN, 13));
        label6.setBounds(110, 230, 400, 30);
        label6.setForeground(Color.GREEN);
        label6.setFont(new Font("Courier", Font.PLAIN, 13));
        label7.setBounds(110, 250, 400, 30);
        label7.setForeground(Color.GREEN);
        label7.setFont(new Font("Courier", Font.PLAIN, 13));
        label8.setBounds(170, 95, 400, 30);
        label8.setForeground(Color.GREEN);
        label8.setFont(new Font("Courier", Font.PLAIN, 13));

        enterButton.setBounds(180, 300, 100, 40);
        enterButton.setBackground(Color.GREEN);
        enterButton.setOpaque(true);
        enterButton.setBorderPainted(false);
        enterButton.setFont(new Font("Courier", Font.PLAIN, 12));

        statisticsF.add(label1);
        statisticsF.add(label2);
        statisticsF.add(label3);
        statisticsF.add(label4);
        statisticsF.add(label5);
        statisticsF.add(label6);
        statisticsF.add(label7);
        statisticsF.add(label8);
        statisticsF.add(enterButton);
        statisticsF.setSize(500, 500);
        statisticsF.setBackground(Color.BLACK);
        statisticsF.getContentPane().setBackground(Color.BLACK);
        statisticsF.setLocationRelativeTo(null);
        statisticsF.setLayout(null);
        statisticsF.setVisible(true);
        statisticsF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        statisticsF.setResizable(false);

        enterButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playerData = player.getStatistics().getType() + " " + hero + " " + player.getStatistics().getLvl() + " " +
                        player.getStatistics().getAttack() + " " + player.getStatistics().getAttack() + " " +
                        player.getStatistics().getHp() + " " + player.getStatistics().getExp() + " " + art.toUpperCase();
                Writer.writeToPlayersFile(playerData);
                Writer.close();
                game();
                statisticsF.setVisible(false);
                statisticsF.dispose();
            }
        });
    }

    public void game() {
        JFrame swingyF = new JFrame("Ongoing Game");
        JLabel playground = new JLabel(" PLAYGROUND");
        GuiMap map = new GuiMap(swingyF, player);
        JTextArea textArea = map.showMap();
        JButton northButton = new JButton("NORTH");
        JButton southButton = new JButton("SOUTH");
        JButton eastButton = new JButton("EAST");
        JButton westButton = new JButton("WEST");

        playground.setBounds(350, 40, 145, 30);
        playground.setForeground(Color.GREEN);
        playground.setBackground(Color.BLACK);
        playground.setOpaque(true);
        playground.setFont(new Font("Courier", Font.PLAIN, 20));
        Border labelBorder = BorderFactory.createLineBorder(Color.GREEN, 1);
        playground.setBorder(labelBorder);

        textArea.setBounds(50, 100, 757, 615);
        textArea.setForeground(Color.GREEN);
        textArea.setBackground(Color.BLACK);
        Border border1 = BorderFactory.createLineBorder(Color.GREEN, 1);
        textArea.setBorder(border1);

        northButton.setBounds(140, 760, 100, 40);
        northButton.setBackground(Color.GREEN);
        northButton.setOpaque(true);
        northButton.setBorderPainted(false);
        northButton.setFont(new Font("Courier", Font.PLAIN, 13));

        southButton.setBounds(280, 760, 100, 40);
        southButton.setBackground(Color.GREEN);
        southButton.setOpaque(true);
        southButton.setBorderPainted(false);
        southButton.setFont(new Font("Courier", Font.PLAIN, 13));

        westButton.setBounds(440, 760, 100, 40);
        westButton.setBackground(Color.GREEN);
        westButton.setOpaque(true);
        westButton.setBorderPainted(false);
        westButton.setFont(new Font("Courier", Font.PLAIN, 13));

        eastButton.setBounds(590, 760, 100, 40);
        eastButton.setBackground(Color.GREEN);
        eastButton.setOpaque(true);
        eastButton.setBorderPainted(false);
        eastButton.setFont(new Font("Courier", Font.PLAIN, 13));

        northButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updatePlayerPos(0, -1);
            }
        });

        southButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updatePlayerPos(0, 1);
            }
        });

        eastButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updatePlayerPos(1, 0);
            }
        });

        westButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updatePlayerPos(-1, 0);
            }
        });

        swingyF.add(northButton);
        swingyF.add(southButton);
        swingyF.add(eastButton);
        swingyF.add(westButton);
        swingyF.add(textArea);
        swingyF.add(playground);
        swingyF.setSize(857, 1007);
        swingyF.setLocationRelativeTo(null);
        swingyF.setLayout(null);
        swingyF.setVisible(true);
        swingyF.setResizable(false);
        swingyF.setBackground(Color.BLACK);
        swingyF.getContentPane().setBackground(Color.BLACK);
        swingyF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void endGame() {
        JLabel label = new JLabel("Going on with out adventure");
        JButton close = new JButton("GAME COMPLETED");
        JFrame endGameF = new JFrame("Game Ended");

        label.setBounds(145, 90, 150, 40);
        label.setForeground(Color.GREEN);
        label.setFont(new Font("Courier", Font.PLAIN, 16));

        close.setBounds(150, 150, 100, 40);
        close.setBackground(Color.GREEN);
        close.setOpaque(true);
        close.setBorderPainted(false);
        close.setFont(new Font("Courier", Font.PLAIN, 12));

        close.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                endGameF.dispose();
                System.exit(0);
            }
        });

        endGameF.add(label);
        endGameF.add(close);
        endGameF.setSize(400, 400);
        endGameF.setBackground(Color.BLACK);
        endGameF.getContentPane().setBackground(Color.BLACK);
        endGameF.setLocationRelativeTo(null);
        endGameF.setLayout(null);
        endGameF.setVisible(true);
        endGameF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endGameF.setResizable(false);
    }

}
