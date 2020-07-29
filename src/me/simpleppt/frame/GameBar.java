package me.simpleppt.frame;

import me.simpleppt.Main;
import me.simpleppt.lg.UpdateInventroy;
import me.simpleppt.proxy.DataProxy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBar extends JMenuBar {
    private JMenu nd;
    private JMenu game;
    private static GameBar gameBar;
    private GameBar(){
        init();
    }
    static {
        gameBar=new GameBar();
    }
    public static GameBar getGameBar(){
        return gameBar;
    }
    public void reName(String newName){
        nd.setText(newName);
    }

    private void init(){
        game=new JMenu("Game");
        JMenuItem regame=new JMenuItem("Restart");
        JMenuItem backToIndex=new JMenuItem("Quit");
        backToIndex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataProxy.isContinue=false;
                GameBar.getGameBar().reName("Easy");
//			DataProxy.jFrame.remove(DataProxy.gamePanel);
//			DataProxy.jFrame.removeAll();
                DataProxy.jFrame.setJMenuBar(null);
                DataProxy.jFrame.remove(DataProxy.mainPanel);
                DataProxy.jFrame.remove(GameBar.getGameBar());
                DataProxy.jFrame.removeKeyListener(DataProxy.gameListener);
                DataProxy.jFrame.setSize(Main.JFRAME_WIDTH, Main.JFRAME_HEIGHT);
                DataProxy.jFrame.add(DataProxy.indexPanel);
                new UpdateInventroy().clear();
//                DataProxy.jFrame.setVisible(false);
//                DataProxy.jFrame.setVisible(true);
                DataProxy.jFrame.repaint();
            }
        });
        regame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reload(DataProxy.whatMap);
            }
        });
        game.add(regame);
        game.add(backToIndex);
        nd=new JMenu("Easy");
        JMenuItem easy=new JMenuItem("Easy");
        easy.addActionListener(new Easy());
        JMenuItem middel=new JMenuItem("Normal");
        middel.addActionListener(new Middel());
        JMenuItem difficult=new JMenuItem("Hard");
        difficult.addActionListener(new Difficult());
        nd.add(easy);
        nd.add(middel);
        nd.add(difficult);
        this.add(nd);
        this.add(game);
    }
    class  Easy implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            nd.setText("Easy");
            reload(1);
        }
    }
    class Middel implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            nd.setText("Normal");
           reload(2);
        }
    }
    class Difficult implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            nd.setText("Hard");
            reload(3);
        }
    }
    //重新加载游戏
    private void reload(int whatMap){
        DataProxy.isContinue=false;
        DataProxy.mainPanel.remove(DataProxy.gamePanel);
        DataProxy.jFrame.remove(DataProxy.mainPanel);
        DataProxy.jFrame.removeKeyListener(DataProxy.gameListener);
        new UpdateInventroy().clear();
        DataProxy.jFrame.setCursor(Cursor.WAIT_CURSOR);
        DataProxy.jFrame.repaint();
        try {
            Thread.sleep(400);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        IndexComponent.startGame(whatMap);
    }
}
