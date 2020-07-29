package me.simpleppt.frame;

import me.simpleppt.proxy.DataProxy;
import me.simpleppt.proxy.Skin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadingComponent extends JPanel {
    private JLabel [] jLabels;
    private int w=50;
    private int h=50;
    private int m=0;
    private Timer up;
    private Timer down;
    private int [] sites={0,30,50};
    public LoadingComponent(){
        jLabels=new JLabel[1];
        setLayout(null);
        init();
    }
    private void  init() {
        getRPos();
        int marginLeft = 100;
        int marginTop = 100;

        System.out.println(getXByY(getRPos()[1]-100));
        for (int i = 0; i < jLabels.length; i++) {

              JLabel jLabel=new JLabel();
              jLabel.setIcon(new ImageIcon(Skin.BOMBS[0]));
              jLabel.setBounds(getXByY(getRPos()[1]),getRPos()[1],50,50);
              this.add(jLabel);
            this.repaint();
            DataProxy.jFrame.setVisible(true);
        }
    }
    private int[] getRPos(){
        int [] pos=new int[2];
        pos[0]=DataProxy.indexPanel.getWidth()/2-25;
        pos[1]=DataProxy.indexPanel.getHeight()/2-40;
        return pos;
    }
    private int getYByX(int x){
        // pass
       return 0;
    }
    private int getXByY(int y){
        int r=100;
        int a=getRPos()[0];
        int b=getRPos()[1];
        int x= (int) Math.abs(Math.sqrt(r*r-Math.pow(y-b,2))-a); //
        return x;
    }
}
