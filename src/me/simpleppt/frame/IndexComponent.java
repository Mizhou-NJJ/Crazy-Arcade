package me.simpleppt.frame;

import me.simpleppt.Main;
import me.simpleppt.lg.UpdateInventroy;
import me.simpleppt.proxy.DataProxy;
import me.simpleppt.proxy.Skin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.channels.SelectionKey;
import java.util.Date;

/*
*  这个类相当于自定义 的组件 时游戏打开时 选择游戏难度的面包
*
*
* */
public class IndexComponent extends JPanel implements MouseListener {
    public static Thread timerThread;
    private JPanel skin;
    private static boolean isExplainShow=false; // 游戏说明文档是否打开了
    private Image image;
    private int height=521;
    private int width=604;
    private int endMarginTop;
    private JLabel explainJlable;
    private JButton explainBtn;
    private Font font=new Font(Font.SERIF,Font.BOLD,14);
    private JButton pl,pr,bl,br;
    private JLabel playerOneJ,playerTwoJ,bombOneJ,bombTwoJ;
    public IndexComponent(){
        image=new ImageIcon("resource/image/bg/bg.png").getImage();
        setLayout(null);
        addTitle();
        addButton();
        setExplainBtn();
        setExplainLable();
//        skin=new JPanel();
//        skin.setLayout(null);
//        skin.setBackground(Color.orange);
//        addSkinJpanel(150);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i=5;i<150;i+=1){
//                    try {
//                        Thread.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    addSkinJpanel(i);
////                   IndexComponent.this.remove(skin);
//                }
//            }
//        }).start();
//
        addSkinJpanel();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,0,0,this.getWidth(),getHeight(),this);
    }

    private void addSkinJpanel(){
        skin =new JPanel();
        skin.setOpaque(false);
        skin.setLayout(null);
        skin.setBounds(200,150,width-400,120);
        playerOneJ=new JLabel();
        playerOneJ.setIcon(new ImageIcon("resource/image/player/player1.png"));
        playerOneJ.setBounds(40,0,70,70);
        playerTwoJ=new JLabel();
        playerTwoJ.setIcon(new ImageIcon("resource/image/player/player2.png"));
        playerTwoJ.setBounds(115,0,70,70);
        bombOneJ=new JLabel();
        bombOneJ.setIcon(new ImageIcon("resource/image/bomb/bomb.png"));
        bombOneJ.setBounds(40,70,70,50);
        bombTwoJ=new JLabel();
        bombTwoJ.setBounds(115,70,70,50);
        bombTwoJ.setIcon(new ImageIcon("resource/image/bomb/bomb.png"));
        skin.add(bombTwoJ);
        skin.add(playerOneJ);
        skin.add(bombOneJ);
        skin.add(playerTwoJ);

        //add change btn
        pl=new JButton("",new ImageIcon("resource/image/bg/left.png"));
        pl.addActionListener(new ChangePlayerSkinLeft());
        pr=new JButton("",new ImageIcon("resource/image/bg/right.png"));
        pr.addActionListener(new ChangePlayerSkinRight());
        bl=new JButton("",new ImageIcon("resource/image/bg/left.png"));
        bl.addActionListener(new ChangeBombSkinLeft());
        br=new JButton("",new ImageIcon("resource/image/bg/right.png"));
        br.addActionListener(new ChangeBombSkinRight());
        JButton []ds={pl,pr,bl,br};
        for (int i=0;i<4;i++){
            ds[i].setContentAreaFilled(false);
            ds[i].setBorder(null);
            ds[i].setBorderPainted(false);
            ds[i].addMouseListener(this);
            ds[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        pl.setBounds(0,25,25,25);
        bl.setBounds(0,85,25,25);
        pr.setBounds(skin.getWidth()-25,25,25,25);
        br.setBounds(skin.getWidth()-25,85,25,25);
        skin.add(pl);
        skin.add(bl);
        skin.add(pr);
        skin.add(br);
        this.add(skin);

    }


    /* 添加按钮 */
    private void  addButton(){
        int interval=10;
        int btnW=150;
        int btnH=35;
        JButton startButton=new JButton("开始");
        ImageIcon imageIcon =new ImageIcon("resource/image/bg/btnbg.png");
        int allHeight=35;
        int marginTop=(height-allHeight)/2+90;
        int marginLeft=(width-btnW)/2;
        startButton.setIcon(imageIcon);
        startButton.setBounds(marginLeft,marginTop,btnW,btnH);
        startButton.setHorizontalTextPosition(SwingConstants.CENTER);
        startButton.setBorderPainted(true);
        startButton.setOpaque(false);
        startButton.setFont(font);
        startButton.setForeground(Color.BLACK);
        startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        startButton.addActionListener(new Start());

        this.add(startButton);
        this.endMarginTop=marginTop;

    }
    // 添加标题
    private void addTitle(){
//        Color.ORANGE,Color.GREEN,Color.GRAY,Color.RED,Color.YELLOW
        String title="<html><span style:color='black'>泡泡堂_2016011841</span></html>";
        JLabel titleLable=new JLabel(title,JLabel.CENTER);
        titleLable.setBounds(0,50,width,100);

        titleLable.setIcon(new ImageIcon("resource/image/bomb/bomb.png"));
        titleLable.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,35));
        this.add(titleLable);
    }
    // 设置 游戏说明按钮
    private void setExplainBtn(){
        //pass
        explainBtn=new JButton();
        explainBtn.setBounds(width-33,0,32,32);
        explainBtn.setIcon(new ImageIcon("resource/image/bg/explain.png"));
        explainBtn.setContentAreaFilled(false); //去除默认填充
        explainBtn.setBorder(null); //去除边框
        explainBtn.setBorderPainted(true);//不答应边框
        explainBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        explainBtn.setToolTipText("游戏说明");
        explainBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 显示 说明Jlable
                isExplainShow=!isExplainShow;
                if (isExplainShow){
                    IndexComponent.this.add(explainJlable);
                    //  更换按钮图标
                    explainBtn.setIcon(new ImageIcon("resource/image/bg/close.png"));
                    IndexComponent.this.repaint();
                }else {
                    IndexComponent.this.remove(explainJlable);
                    explainBtn.setIcon(new ImageIcon("resource/image/bg/explain.png"));
                    IndexComponent.this.repaint();
                }
            }
        });
        this.add(explainBtn);
    }

    private void setExplainLable(){
        String exp="<html>" +
                "<h2>Simple泡泡堂操作说明:</h2>" +
                "方向：一号玩家:<span style=color:'orange'>W</span>,<span style=color:'orange'>A</span>,<span style=color:'orange'>S</span>,<span style=color:'orange'>D</span>,二号玩家:通过<span style=color:'orange'>方向键</span>" +
                "<br>放置炸弹: 一号玩家: <span style=color:'orange'>空格</span>, 二号玩家:<span style=color:'orange'>Ctrl</span style=color:'orange'> " +
                "<br>复活: 一号玩家:<span style=color:'orange'>" +
                "R</span>, 二号玩家:<span style=color:'orange'>L</span>。也可选择点击物品栏。" +
                "<br>游戏中可通过<span style=color:'orange'>Ctrl+Q</span>退出游戏回到首页!</html>";
        JLabel jLabel=new JLabel(exp);
        jLabel.setForeground(Color.WHITE);
        jLabel.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,14));
        jLabel.setBounds(0,endMarginTop-20,width,height-endMarginTop);
        this.explainJlable=jLabel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JButton resB= (JButton) e.getSource();
        if (resB==pl||resB==bl){
            resB.setIcon(new ImageIcon("resource/image/bg/left_black.png"));
        }else if (resB==pr||resB==br){
            resB.setIcon(new ImageIcon("resource/image/bg/right_black.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JButton resB= (JButton) e.getSource();
        if (resB==pl||resB==bl){
            resB.setIcon(new ImageIcon("resource/image/bg/left.png"));
        }else if (resB==pr||resB==br){
            resB.setIcon(new ImageIcon("resource/image/bg/right.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //pass
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //pass
    }

    // 开始游戏按钮监听类
    class  Start implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            DataProxy.whatMap=1;
            removeThis();
            startGame(1);
        }
    }
    private void  removeThis(){
        DataProxy.jFrame.remove(this);
        //DataProxy.jFrame.repaint();
        DataProxy.jFrame.setVisible(false);
    }
    private static void addGamePanel(){
        GJpanel gJpanel=new GJpanel();
        DataProxy.gamePanel=gJpanel;
        DataProxy.jFrame.setJp(gJpanel);
    }
    private static void quit(){
        DataProxy.isContinue=false;
        GameBar.getGameBar().reName("Easy");
//			DataProxy.jFrame.remove(DataProxy.gamePanel);
//			DataProxy.jFrame.removeAll();
        JOptionPane.showMessageDialog(DataProxy.jFrame,"<html><h1><span style=color:'orange'>时间到,游戏结束</span></h1><html>");
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
    private  static int formatS(String ts){
        String [] ms=ts.split(":");
        int f=Integer.parseInt(ms[0]);
        int s=Integer.parseInt(ms[1]);
        return f*60+s;
    }
    private static String formatMMSS(int ts){
        int f=ts/60;
        int s=ts%60;
        String fmt="";
        if (f<10){
            fmt+="0";
        }
        fmt=fmt+f+":";
        if (s<10){
            fmt=fmt+"0";
        }
        fmt+=s;
        return fmt;
    }
    // 开始游戏
    public static void startGame(int whatMap){
        DataProxy.whatMap=whatMap;
        DataProxy.jFrame.setCursor(Cursor.DEFAULT_CURSOR);
        addGamePanel();
        DataProxy.jFrame.requestFocus();
        InventoryComponent.timerBtn.setIcon(new ImageIcon("resource/image/bg/pause.png"));
        DataProxy.isContinue=true;
        DataProxy.jFrame.startGame();
        DataProxy.isS=true;
        InventoryComponent.TIME.setText("03:00");
        // 计时器
        if (timerThread!=null) timerThread.stop();
            timerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (DataProxy.isContinue) {
                        try {
                            Thread.sleep(1000);
                            if (DataProxy.isS) {
                                int old = IndexComponent.formatS(InventoryComponent.TIME.getText());
                                if (old <= 0) {
                                    IndexComponent.quit();
                                    break;
                                }
                                int newTime = old - 1;
                                InventoryComponent.TIME.setText(IndexComponent.formatMMSS(newTime));
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            timerThread.start();

        /* --------------------------*/

        /*---------------------------*/
        DataProxy.gamePanel.setBounds(0,0,600,500); //will change
        InventoryComponent inventoryComponent= InventoryComponent.getInventoryComponent();
        inventoryComponent.setBounds(0,505,600,100);
        JPanel mainPanel=new JPanel();
        mainPanel.setLayout(null);
        DataProxy.mainPanel=mainPanel;
        InventoryComponent.headerA.setIcon(new ImageIcon(DataProxy.selectPlayerOneImgPath));
        InventoryComponent.headerB.setIcon(new ImageIcon(DataProxy.selectPlayerTwoImgPath));
        DataProxy.mainPanel.add(DataProxy.gamePanel);
        DataProxy.mainPanel.add(inventoryComponent);
        //to
        DataProxy.jFrame.setJMenuBar(GameBar.getGameBar());
        DataProxy.jFrame.setFocusable(true); // 获得焦点 否则直接添加KeyListener 可能会无效
        DataProxy.jFrame.addKeyListener(DataProxy.gameListener);
        DataProxy.jFrame.add(DataProxy.mainPanel);
        DataProxy.jFrame.setSize(610,640);
        //DataProxy.jFrame.startGame();
        DataProxy.jFrame.setVisible(true);
    }
    class ChangePlayerSkinLeft implements ActionListener{
       private int whatPlayer=1;
        @Override
        public void actionPerformed(ActionEvent e) {
            playerOneJ.setIcon(new ImageIcon(Skin.PLAYERS[whatPlayer%Skin.PLAYERS.length]));
            DataProxy.selectPlayerTwoImgPath=Skin.PLAYERS[whatPlayer% Skin.PLAYERS.length];
            whatPlayer++;
        }
    }
    class ChangePlayerSkinRight implements ActionListener{
        private int whatPlayer=0;
        @Override
        public void actionPerformed(ActionEvent e) {
            playerTwoJ.setIcon(new ImageIcon(Skin.PLAYERS[whatPlayer%Skin.PLAYERS.length]));
            DataProxy.selectPlayerOneImgPath=Skin.PLAYERS[whatPlayer%Skin.PLAYERS.length];
            whatPlayer++;
        }
    }
    class ChangeBombSkinLeft implements ActionListener{
        private int whatBomb=1;
        @Override
        public void actionPerformed(ActionEvent e) {
            bombOneJ.setIcon(new ImageIcon(Skin.BOMBS[whatBomb%Skin.BOMBS.length]));
            DataProxy.selectPlayerTwoBombImgPath=Skin.BOMBS[whatBomb%Skin.BOMBS.length];
            if (whatBomb%Skin.BOMBS.length==0){
                DataProxy.mappingXGOne=Skin.BOMB_BLUE_XG;
            }else {
                DataProxy.mappingXGOne=Skin.BOMB_RED_XG;
            }
            whatBomb++;
        }
    }
    class ChangeBombSkinRight implements ActionListener{
        private int whatBomb=0;
        @Override
        public void actionPerformed(ActionEvent e) {
            bombTwoJ.setIcon(new ImageIcon(Skin.BOMBS[whatBomb%Skin.BOMBS.length]));
            DataProxy.selectPlayerOneBombImgPath=Skin.BOMBS[whatBomb%Skin.BOMBS.length];
            if (whatBomb%Skin.BOMBS.length==0){
                DataProxy.mappingXgTwo=Skin.BOMB_BLUE_XG;
            }else {
                DataProxy.mappingXgTwo=Skin.BOMB_RED_XG;
            }
            whatBomb++;
        }
    }

}
