package me.simpleppt.element;

import me.simpleppt.Main;
import me.simpleppt.frame.GameBar;
import me.simpleppt.lg.Listener;
import me.simpleppt.lg.UpdateInventroy;
import me.simpleppt.load.LoadResource;
import me.simpleppt.man.GameContext;
import me.simpleppt.proxy.DataProxy;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayerOne extends Element {
	private int speed;//�����ٶ�
	private int bombCount;//��������
	private int setedBonCount;//�Ѿ��ŵ���������
	private int power;//��������.
	private int condition;//����״̬1:����   0�����ݰ�Χ
	private ImageIcon img;
	private int life=1;
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	private int bursttime=0;
	public PlayerOne(int x, int y, int w, int z, ImageIcon img){
		super(x,y,w,z);
		this.img=img;
		speed=0;
		bombCount=1;
		setedBonCount=0;
		power=1;
		up=false;
		down=false;
		left=false;
		right=false;
		condition=1;
		list1.add(Listener.STOP);
	}
	/*
	* ��� list1 ������� ��ť�¼�ֵ ����˵ left right up��   has setter
	* ͨ���� GameLister �л�ȡplayer��ֵ��Ȼ������¼�
	* */
	private List<String> list1=new ArrayList<>(); //
	public List<String> getList1() {
		return list1;
	}

	public void setList1(List<String> list1) {
		this.list1 = list1;
	}


	public static PlayerOne createPlayer(String str){
		String [] arr=str.split(",");
		int x=Integer.parseInt(arr[2]);
		int y=Integer.parseInt(arr[3]);
		int w=Integer.parseInt(arr[4]);
		int h=Integer.parseInt(arr[5]);
		ImageIcon img=new ImageIcon(DataProxy.selectPlayerOneImgPath);
				//LoadResource.getElementLoad().getMap().get(arr[0]);
		
		return new PlayerOne(x,y,w,h,img);
	}

	@Override
	public void update(){
		if(condition==1) { // ����״̬ ����
			move();
			for(int i=0;i<speed;i++) {
				if(i==1)
					move();
				if(i==3)
					move();
				if(i==5)
					move();
			}
		}
//		updateImage();
		pick();
		burst();
		destroy();
	}
	public void showElement(Graphics g) {
		if(condition==1) {
			g.drawImage(img.getImage(),getX(),getY(),getW(),getH(),null);
		}
		else if(condition==0) {
			g.drawImage(img.getImage(),getX(),getY(),getW(),getH(),null); //����

		}
	}
	/*����ը��*/
	public void addBubble() {
		List<Integer> bubblelist= GameContext.getManager().getBubblelist();
		if(setedBonCount<bombCount&&bubblelist.get((getX()+23)/40+(getY()+53)/40*15)!=1) {
			List<Element> list= GameContext.getManager().getElementList("nbubble");
			// 23 58
			list.add(Bomb.createBomb(getX()+23, getY()+53, DataProxy.selectPlayerOneBombImgPath, "oneplayer", power));
			setedBonCount++;
		}
	}
	public void move(){
		String moveType=list1.get(list1.size()-1); // ѭ���Ĵ��¼��б��ж�ȡ�¼�

		if(moveType.contains(Listener.LEFT)){
			setLeft(true);
			setUp(false);
			setRight(false);
			setDown(false);
		}
		if(moveType.contains(Listener.UP)){
			setLeft(false);
			setUp(true);
			setRight(false);
			setDown(false);
		}
		if(moveType.contains(Listener.RIGHT)){
			setLeft(false);
			setUp(false);
			setRight(true);
			setDown(false);
		}
		if(moveType.contains(Listener.DOWN)){
			setLeft(false);
			setUp(false);
			setRight(false);
			setDown(true);
		}
		if(moveType.contains(Listener.STOP)){
			setLeft(false);
			setUp(false);
			setRight(false);
			setDown(false);
		}
		if(up&&getY()>0){ //���������ƶ�

			switch (checkMap(Listener.L_UP)) {
			case 1:
				setY(getY()-7);
				setX(getX()-10);
				break;
			case 2:
				setY(getY()-7);
				setX(getX()+10);
				break;
			case 3:
				setY(getY()-7);
				break;
			}
		}
		if(down&&getY()<660){ //���������ƶ�
			switch (checkMap(Listener.L_DOWN)) {
			case 1:
				setY(getY()+7);
				setX(getX()-10);
				break;
			case 2:
				setY(getY()+7);
				setX(getX()+10);
				break;
			case 3:
				setY(getY()+7);
				break;
			}
		}
		if(left&&getX()>0&&checkMap(Listener.L_LEFT)==1){
			setX(getX()-10);
		}
		if(right&&getX()<960&&checkMap(Listener.L_RIGHT)==1){
			setX(getX()+10);
		}
		setLayer((getY()+getH())/40+1); //����������ڵ�����
		return;
	}
	
	public int checkMap(String s) {
		int i = -1;
		int j = -1;
		int r = -1;
		int l = -1;
		int m = -1;
		/*
		*
		*  ������� �� �����м� Ҳ������ ���������м�ķָ��߸���
		*  ���� ������ ����� �ڶ��񣬵������� һС���� λ���ڵ�һ����ʱ�� ���ϻ������°�ťʱ����Ҫ������ ����������ƶ� һ����
		* */
		switch (s) {
		case "up":
			i = ((getY()-20+getH())/40)+1;
			j = ((getY()+getH())/40)+1;
			r = (getX()+getW())/40;// > �������ӿ�ȣ��򰴾�������ڵ���������
			l = getX()/40; //�������/���ӿ�� ==������ҵڼ������� ������������ ����������
			m = (getX()+getW()/2)/40;// �� m+1 ��
//			System.out.println("getY:"+getY()+"-----getX():"+getX()+"---getH():"+getH()+"---getW():"+getW());
//			System.out.println("i="+i+"----j="+j+"---r="+r+"---l="+l+"---m="+m);
//			System.out.println(ElementManager.getManager().getMap().get("map11").size());
				if((!GameContext.getManager().getMap().get("map1"+j).get(m).isAcrossPower()&& GameContext.getManager().getMap().get("map1"+(j-1)).get(m).isAcrossPower())||(GameContext.getManager().getMap().get("map1"+i).get(r).isAcrossPower()&& GameContext.getManager().getMap().get("map1"+i).get(l).isAcrossPower()))
					return 3;
				else if(GameContext.getManager().getMap().get("map1"+i).get(r).isAcrossPower()&& GameContext.getManager().getMap().get("map1"+i).get(m).isAcrossPower())
					return 2;
				else if(GameContext.getManager().getMap().get("map1"+i).get(l).isAcrossPower()&& GameContext.getManager().getMap().get("map1"+i).get(m).isAcrossPower())
					return 1;
			else
				break;
		case "down":
			i = ((getY()+10+getH())/40)+1;
			j = ((getY()+getH())/40)+1;
			r = (getX()+getW())/40;
			l = getX()/40;
			m = (getX()+getW()/2)/40;
				if((!GameContext.getManager().getMap().get("map1"+j).get(m).isAcrossPower()&& GameContext.getManager().getMap().get("map1"+(j+1)).get(m).isAcrossPower())||(GameContext.getManager().getMap().get("map1"+i).get(r).isAcrossPower()&& GameContext.getManager().getMap().get("map1"+i).get(l).isAcrossPower()))
					return 3;// ����� �ڸ����м�
				else if(GameContext.getManager().getMap().get("map1"+i).get(r).isAcrossPower()&& GameContext.getManager().getMap().get("map1"+i).get(m).isAcrossPower())
					return 2; // ������� ��һ������߸���
				else if(GameContext.getManager().getMap().get("map1"+i).get(l).isAcrossPower()&& GameContext.getManager().getMap().get("map1"+i).get(m).isAcrossPower())
					return 1; //�����һ�����ұ߸���
			else 
				break;
			/*
			* left �� right �˶�ֻ�����м��ʱ�� �ſ���
			* */
		case "left":
			i = (getY()+getH())/40+1;
			l = (getX()-1)/40;
			m = (getX()+getW()/2)/40;
			if((!GameContext.getManager().getMap().get("map1"+i).get(m).isAcrossPower()&& GameContext.getManager().getMap().get("map1"+i).get(m-1).isAcrossPower())|| GameContext.getManager().getMap().get("map1"+i).get(l).isAcrossPower())
				return 1;
			else 
				break;
		case "right":
			i = (getY()+getH())/40+1;
			r = (getX()+1+getW())/40;
			m = (getX()+getW()/2)/40;
			if((!GameContext.getManager().getMap().get("map1"+i).get(m).isAcrossPower()&& GameContext.getManager().getMap().get("map1"+i).get(m+1).isAcrossPower())|| GameContext.getManager().getMap().get("map1"+i).get(r).isAcrossPower())
				return 1;
			else 
				break;
		}
		return 0;
	}

	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getBombCount() {
		return bombCount;
	}
	public void setBombCount(int bombCount) {
		this.bombCount = bombCount;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public ImageIcon getImg() {
		return img;
	}
	public void setImg(ImageIcon img) {
		this.img = img;
	}
	public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}

	public int getCondition() {
		return condition;
	}
	public void setCondition(int condition) {
		this.condition = condition;
	}
	public int getSetedBonCount() {
		return setedBonCount;
	}
	public void setSetedBonCount(int setedBonCount) {
		this.setedBonCount = setedBonCount;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}


	@Override
	public void destroy() {
		if (!isVisible()){
			JOptionPane.showMessageDialog(DataProxy.jFrame,"<html><h1><span style=color:'orange'>��ϲ ��� 1 ���ʤ��!</span></h1></html>");
			GameBar.getGameBar().reName("Easy");
			DataProxy.jFrame.remove(DataProxy.mainPanel);
			DataProxy.jFrame.add(DataProxy.indexPanel);
			DataProxy.jFrame.removeKeyListener(DataProxy.gameListener);
			new UpdateInventroy().clear();
			DataProxy.jFrame.setSize(Main.JFRAME_WIDTH, Main.JFRAME_HEIGHT);
			DataProxy.jFrame.repaint();
		}
	}
	
	public void pick() {
		Map<String,List<Element>> map = GameContext.getManager().getMap();
		List<Element> list=map.get("items"+getLayer());
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getX()<=(getX()+getW()/2)&&(list.get(i).getX()+list.get(i).getW())>=(getX()+getW()/2)) {
				switch (list.get(i).getVariety()) {
				case 1://��ը��
					setBombCount(getBombCount()+1);
					new UpdateInventroy().updatePlayerOneBomb(getBombCount());
					break;
				case 2://������
					setPower(getPower()+1);
					new UpdateInventroy().updatePlayerOnePower(getPower());
					break;
				case 3://���ٶ�
					setSpeed(getSpeed()+1);
					new UpdateInventroy().updatePlayerOneSpeed(getSpeed());
					break;
				}
				
				list.get(i).setVisible(false);
			}
		}
		
	}
	
	public void burst() {
		if(isVisible()) {
			if(condition==1) { //����
				bursttime = 0;
				// ������Ա�ը��
				if(GameContext.getManager().getBubblelist().get((getLayer()-1)*15+(getX()+getW()/2)/40)==-1|| GameContext.getManager().getBubblelist().get((getLayer()-1)*15+(getX()+getW()/2)/40)==2|| GameContext.getManager().getBubblelist().get((getLayer()-1)*15+(getX()+getW()/2)/40)==3) {
					setCondition(0);
					setImg(LoadResource.getElementLoad().getMap().get("burst")); // ���õ�.png
				}
			}// ����Ѿ�ʱ����̬
			if(condition==0) {
				bursttime++;
				if(bursttime==50||(GameContext.getManager().getElementList("playerTwo").size()>0&&(GameContext.getManager().getElementList("playerTwo").get(0).getLayer()==getLayer()&& GameContext.getManager().getElementList("playerTwo").get(0).getX()==getX()))) {
					setVisible(false);
				}
			}
		}
	}
}
