package me.simpleppt.frame;

import me.simpleppt.element.PlayerOne;
import me.simpleppt.element.PlayerTwo;
import me.simpleppt.lg.UpdateInventroy;
import me.simpleppt.proxy.DataProxy;
import me.simpleppt.proxy.Skin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryComponent extends JPanel {
    private static boolean isPause=false;
    private static PlayerOne playerOne;
    private static PlayerTwo playerTwo;
    private Font font=new Font("宋体",Font.BOLD,22);
    private InventoryComponent(){}
    private static InventoryComponent inventory;
    //private final static String [] PROPS_PATH_PLAYER_ONE={"resource/image/player/player1.png","resource/image/prop/1.png","resource/image/prop/2.png","resource/image/prop/3.png","resource/image/prop/zheng.png"};
    //private final static String [] PROPS_PATH_PLAYER_TWO={"resource/image/player/player2.png","resource/image/prop/1.png","resource/image/prop/2.png","resource/image/prop/3.png","resource/image/prop/zheng.png"};
    public final static JButton BOMB_A=new JButton("",new ImageIcon("resource/image/prop/1.png"));
    public final static JButton BOMB_B=new JButton("",new ImageIcon("resource/image/prop/1.png"));
    public final static JButton POWER_A=new JButton("",new ImageIcon("resource/image/prop/2.png"));
    public final static JButton POWER_B=new JButton("",new ImageIcon("resource/image/prop/2.png"));
    public final static JButton SPEED_A=new JButton("",new ImageIcon("resource/image/prop/3.png"));
    public final static JButton SPEED_B=new JButton("",new ImageIcon("resource/image/prop/3.png"));
    public final static JButton LIFE_A=new JButton("",new ImageIcon("resource/image/prop/zheng.png"));
    public final static JButton LIFE_B=new JButton("",new ImageIcon("resource/image/prop/zheng.png"));
    public final static JLabel TIME=new JLabel("120",JLabel.CENTER);
    public final static JButton headerA=new JButton("",new ImageIcon(DataProxy.selectPlayerOneImgPath));
    public final static JButton headerB=new JButton("",new ImageIcon(DataProxy.selectPlayerTwoImgPath));
    public final static JLabel  PLAYER_A_J=new JLabel("玩家2",JLabel.CENTER);
    public final static JLabel PLAYER_B_J=new JLabel("玩家1",JLabel.CENTER);
    public final static JLabel  BOMB_A_J=new JLabel("1",JLabel.CENTER);
    public final static JLabel  BOMB_B_J=new JLabel("1",JLabel.CENTER);
    public final static JLabel POWER_A_J=new JLabel("1",JLabel.CENTER);
    public final static JLabel POWER_B_J=new JLabel("1",JLabel.CENTER);
    public final static JLabel SPEED_A_J=new JLabel("1",JLabel.CENTER);
    public final static JLabel SPEED_B_J=new JLabel("1",JLabel.CENTER);
    public final static JLabel LIFE_A_J=new JLabel("1",JLabel.CENTER);
    public final static JLabel LIFE_B_J=new JLabel("1",JLabel.CENTER);
    public final static JButton timerBtn=new JButton("",new ImageIcon("resource/image/bg/pause.png"));
    static {
        InventoryComponent.inventory=new InventoryComponent();
        InventoryComponent.inventory.setLayout(null);
//        playerOne= (PlayerOne) ElementManager.getManager().getMap().get("playerOne").get(0);
//        System.out.println(playerOne.getBubblenum());
//        System.out.println(playerTwo.getBubblenum());
//        playerTwo= (PlayerTwo) ElementManager.getManager().getMap().get("playerTwo").get(0);
        InventoryComponent.inventory.addBtn();
        InventoryComponent.inventory.addPlayerStatusDescribe();
    }
    // 单例模式 确保 整个游戏只有 一个Inventory
   public static InventoryComponent getInventoryComponent(){
       return  inventory;
   }

   // 物品栏图标下方的按钮状态描述
   private void addPlayerStatusDescribe(){
        int marginLeft=0;
        int marginTop=40;
        int w=50;
        int h=w;
        JLabel [] js={PLAYER_B_J,BOMB_B_J,POWER_B_J,SPEED_B_J,LIFE_B_J,PLAYER_A_J,BOMB_A_J,POWER_A_J,SPEED_A_J,LIFE_A_J};
        for (int c=0;c<js.length;c++){
            js[c].setBounds(marginLeft,marginTop,w,h);
            js[c].setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,16));
            InventoryComponent.inventory.add(js[c]);
            if (c==4) marginLeft+=150;
            else  marginLeft+=50;
        }
        timerBtn.setBounds(275,40,50,35);
        timerBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        timerBtn.setOpaque(false);
        timerBtn.setBorderPainted(true);
        timerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPause){ //如果已经暂停
//                    DataProxy.isContinue=true;
                    timerBtn.setIcon(new ImageIcon("resource/image/bg/pause.png"));
                    DataProxy.isS=true;
                    DataProxy.jFrame.requestFocus();
                }else {
                    timerBtn.setIcon(new ImageIcon("resource/image/bg/continue.png"));
                      DataProxy.isS=false;
//                    DataProxy.isContinue=false;
                }
                isPause=!isPause;

            }
        });
        this.add(timerBtn);

   }
   private void addBtn(){
        // Player one

       JButton[] btsA={headerA,BOMB_A,POWER_A,SPEED_A};
       JButton [] btsB={headerB,BOMB_B,POWER_B,SPEED_B};
       String [] tookits={"可放泡泡数","泡泡威力","人物移动速度"};
       //setTookit
       btsA[0].setToolTipText("玩家2");
       btsB[0].setToolTipText("玩家1");
       for (int i=1;i<4;++i){
           btsA[i].setToolTipText(tookits[i-1]);
           btsB[i].setToolTipText(tookits[i-1]);
       }
       int marginLeft=0;
       int marginTop=0;
       int w=50;
       int h=50;


       // 第一玩家
       for (int a=0;a<4;++a){
           btsB[a].setBounds(marginLeft, marginTop, w, h);
           btsB[a].setHorizontalTextPosition(SwingConstants.CENTER);
           btsB[a].setBorderPainted(false);
           btsB[a].setOpaque(false);
           btsB[a].setFont(font);
//           btsB[a].setToolTipText(tookits[a]);
           btsB[a].setFocusPainted(false);
           btsB[a].addActionListener(new Clear());
           btsB[a].setBackground(Color.WHITE);
           btsB[a].setForeground(Color.GREEN);
           InventoryComponent.inventory.add(btsB[a]);
           marginLeft+=w;
           //add time component
           TIME.setToolTipText("剩余时间");
           TIME.setBounds(marginLeft+75,0,50,50);
           this.add(TIME);
       }
       LIFE_B.setBounds(marginLeft,marginTop,w,h);
       LIFE_B.setHorizontalTextPosition(SwingConstants.CENTER);
       LIFE_B.setBorderPainted(true);
       LIFE_B.setOpaque(false);
       LIFE_B.setForeground(Color.GREEN);
       LIFE_B.setBackground(Color.WHITE);
       LIFE_B.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       LIFE_B.setFont(font);
       LIFE_B.setToolTipText("生命针");
       LIFE_B.addActionListener(new LifeA());
       InventoryComponent.inventory.add(LIFE_B);
       marginLeft+=3*50;
       // 第二玩家
       for (int a=0;a<4;++a){
               btsA[a].setBounds(marginLeft, marginTop, w, h);
               btsA[a].setHorizontalTextPosition(SwingConstants.CENTER);
               btsA[a].setBorderPainted(false);
               btsA[a].setOpaque(false);
               btsA[a].setForeground(Color.GREEN);
               btsA[a].setBackground(Color.WHITE);
               btsA[a].setFont(font);
               btsA[a].setFocusPainted(false);
               btsA[a].addActionListener(new Clear());
               InventoryComponent.inventory.add(btsA[a]);
               marginLeft+=w;
       }
       LIFE_A.setBounds(marginLeft,marginTop,w,h);
       LIFE_A.setHorizontalTextPosition(SwingConstants.CENTER);
       LIFE_A.setBorderPainted(true);
       LIFE_A.setOpaque(false);
       LIFE_A.setForeground(Color.GREEN);
       LIFE_A.setFont(font);
       LIFE_A.addActionListener(new LifeB());
       LIFE_A.setBackground(Color.WHITE);
       LIFE_A.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       LIFE_A.setToolTipText("生命针");
       InventoryComponent.inventory.add(LIFE_A);


   }
   class LifeA implements ActionListener{

       @Override
       public void actionPerformed(ActionEvent e) {
          new UpdateInventroy().updatePlayerTwoLife();
          DataProxy.jFrame.setFocusable(true);
          DataProxy.jFrame.requestFocus();
       }
   }
   class LifeB implements ActionListener{

       @Override
       public void actionPerformed(ActionEvent e) {
           new UpdateInventroy().updatePlayerOneLife();
           DataProxy.jFrame.setFocusable(true);
           DataProxy.jFrame.requestFocus();
       }
   }
   class Clear implements ActionListener{

       @Override
       public void actionPerformed(ActionEvent e) {
           DataProxy.jFrame.requestFocus();
       }
   }
}
