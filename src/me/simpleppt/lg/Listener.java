package me.simpleppt.lg;

import me.simpleppt.Main;
import me.simpleppt.element.PlayerOne;
import me.simpleppt.element.PlayerTwo;
import me.simpleppt.frame.GameBar;
import me.simpleppt.man.GameContext;
import me.simpleppt.proxy.DataProxy;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Listener implements KeyListener{
	public final static String LEFT="LEFT";
	public final static String UP="UP";
	public final static String RIGHT="RIGHT";
	public final static String DOWN="DOWN";
	public final static String STOP="STOP";
	public final static String L_LEFT="left";
	public final static String L_UP="up";
	public final static String L_RIGHT="right";
	public final static String L_DOWN="down";
	private List<?> list;
	private List<?> listB;
	private List<String> list1=new ArrayList<>();
	private List<String> list2=new ArrayList<>();
	@Override
	public void keyTyped(KeyEvent e) {
		//pass
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// 从游戏退回到首页
		if (e.isControlDown()&&e.getKeyCode()==KeyEvent.VK_Q){
			DataProxy.isContinue=false;
			GameBar.getGameBar().reName("Easy");
//			DataProxy.jFrame.remove(DataProxy.gamePanel);
//			DataProxy.jFrame.removeAll();
			DataProxy.jFrame.remove(DataProxy.mainPanel);
			DataProxy.jFrame.removeKeyListener(DataProxy.gameListener);
			DataProxy.jFrame.setSize(Main.JFRAME_WIDTH, Main.JFRAME_HEIGHT);
			DataProxy.jFrame.add(DataProxy.indexPanel);
			new UpdateInventroy().clear();
			DataProxy.jFrame.setVisible(false);
			DataProxy.jFrame.setVisible(true);
		}
		if (e.getKeyCode()==KeyEvent.VK_L){
			new UpdateInventroy().updatePlayerOneLife();
		}
		if (e.getKeyCode()==KeyEvent.VK_R){
			new UpdateInventroy().updatePlayerTwoLife();
		}
		if(e.getKeyCode()==17||e.getKeyCode()==37||e.getKeyCode()==38||e.getKeyCode()==39||e.getKeyCode()==40){
			list= GameContext.getManager().getElementList("playerOne");
			//list.size()==1 list里面存放了一个Player对象
	//		listA=ElementManager.getManager().getElementList("playerB");
			PlayerOne oneplayer=(PlayerOne)list.get(0);
			list1=oneplayer.getList1(); //存放事件
			switch(e.getKeyCode()){
			case 17:oneplayer.addBubble();break;
			case 37:if(!list1.contains(LEFT))list1.add(LEFT);break;
			case 38:if(!list1.contains(UP))list1.add(UP);break;
			case 39:if(!list1.contains(RIGHT))list1.add(RIGHT);break;
			case 40:if(!list1.contains(DOWN))list1.add(DOWN);break;
			}
			oneplayer.setList1(list1);
		}
		// W A S D
		if(e.getKeyCode()==65||e.getKeyCode()==87||e.getKeyCode()==68||e.getKeyCode()==83||e.getKeyCode()==32){
			listB= GameContext.getManager().getElementList("playerTwo");
			PlayerTwo twoplayer=(PlayerTwo)listB.get(0);
			list2=twoplayer.getList2();
			switch(e.getKeyCode()){
			case 32:twoplayer.addBomb();break;
			case 65:if(!list2.contains(LEFT))list2.add(LEFT);break;
			case 87:if(!list2.contains(UP))list2.add(UP);break;
			case 68:if(!list2.contains(RIGHT))list2.add(RIGHT);break;
			case 83:if(!list2.contains(DOWN))list2.add(DOWN);break;
			}
			twoplayer.setList2(list2);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		/*
		*  按钮按下时 添加 值 事件列表 ,按钮抬起后移除
		* */
		//System.out.println("keyReleased"+e.getKeyCode());
		if(e.getKeyCode()==37||e.getKeyCode()==38||e.getKeyCode()==39||e.getKeyCode()==40){
			list= GameContext.getManager().getElementList("playerOne");
			PlayerOne oneplayer=(PlayerOne)list.get(0);
			list1=oneplayer.getList1();
			switch(e.getKeyCode()){
			case 37:if(list1.contains(LEFT)){
				list1.remove(LEFT);
			}
			case 38:list1.remove(UP);break;
			case 39:list1.remove(RIGHT);break;
			case 40:list1.remove(DOWN);break;
			}
			if(list1.contains(LEFT)==false&&list1.contains(UP)==false&&list1.contains(RIGHT)==false&&list1.contains(DOWN)==false&&list1.contains(STOP)==false){
				list1.add(STOP);
			}
			oneplayer.setList1(list1);				
		}
		if(e.getKeyCode()==65||e.getKeyCode()==87||e.getKeyCode()==68||e.getKeyCode()==83){
			listB= GameContext.getManager().getElementList("playerTwo");
			PlayerTwo twoplayer=(PlayerTwo)listB.get(0);
			list2=twoplayer.getList2();
			switch(e.getKeyCode()){
			case 65:if(list2.contains(LEFT)){
				list2.remove(LEFT);break;
			}
			case 87:list2.remove(UP);break;
			case 68:list2.remove(RIGHT);break;
			case 83:list2.remove(DOWN);break;
			}
			if(list2.contains(LEFT)==false&&list2.contains(UP)==false&&list2.contains(RIGHT)==false&&list2.contains(DOWN)==false&&list2.contains(STOP)==false){
				list2.add(STOP);
			}
			twoplayer.setList2(list2);	
		}
	}

}
