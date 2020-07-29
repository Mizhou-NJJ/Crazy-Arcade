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

public class PlayerTwo extends Element {
	private int speed;//人物速度
	private int bombCount;//泡泡数量
	private int setedBombCount;//已经放的泡泡数量
	private int power;//泡泡威力.
	private int condition;//人物状态1:正常 0：泡泡包围
	private ImageIcon img;
	private int life=1;
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;

	private int bursttime=0;
	public PlayerTwo(int x, int y, int w, int z, ImageIcon img){
		super(x,y,w,z);
		this.img=img;//就近原则
		speed=0;
		bombCount=1;
		setedBombCount=0;
		power=1;
		up=false;
		down=false;
		left=false;
		right=false;
		condition=1;
		list2.add(Listener.STOP);
	}
	private List<String> list2=new ArrayList<>();
	public List<String> getList2() {
		return list2;
	}
	public void setList2(List<String> list2) {
		this.list2 = list2;
	}
	public static PlayerTwo createPlayerB(String str){
//		playerA,bubble,300,150,40,40

		String [] arr=str.split(",");
		int x=Integer.parseInt(arr[2]);
		int y=Integer.parseInt(arr[3]);
		int w=Integer.parseInt(arr[4]);
		int h=Integer.parseInt(arr[5]);
		ImageIcon img=new ImageIcon(DataProxy.selectPlayerTwoImgPath);
				//LoadResource.getElementLoad().getMap().get(arr[0]);
		
		return new PlayerTwo(x,y,w,h,img);
	}

	public void update(){
		if(condition==1) {
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
		// cimg()
		pick();
		burst();
		destroy();
	}
	public void showElement(Graphics g) {
		if(condition==1) {

			g.drawImage(img.getImage(),getX(),getY(),getW(),getH(),null);
		}
		else if(condition==0) {
			g.drawImage(img.getImage(),getX(),getY(),getW(),getH(),null);
		}
	}
	public void addBomb() {
		List<Integer> bubblelist= GameContext.getManager().getBubblelist();
		if(setedBombCount<bombCount&&bubblelist.get((getX()+23)/40+(getY()+53)/40*15)!=1) {
			List<Element> list= GameContext.getManager().getElementList("nbubble");
			//23/ 53
			list.add(Bomb.createBomb(getX()+23, getY()+53, DataProxy.selectPlayerTwoBombImgPath, "twoplayer",power));
			setedBombCount++;
		}
	}
	public void move(){
		String moveType=list2.get(list2.size()-1);
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
		if(up&&getY()>0){
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
		if(down&&getY()<660){
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
		setLayer((getY()+getH())/40+1);
		return;
	}
	
	public int checkMap(String s) {
		int i = -1;
		int j = -1;
		int r = -1;
		int l = -1;
		int m = -1;
		switch (s) {
		case "up":
			i = ((getY()-20+getH())/40)+1;
			j = ((getY()+getH())/40)+1;
			r = (getX()+getW())/40;
			l = getX()/40;
			m = (getX()+getW()/2)/40;
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
					return 3;
				else if(GameContext.getManager().getMap().get("map1"+i).get(r).isAcrossPower()&& GameContext.getManager().getMap().get("map1"+i).get(m).isAcrossPower())
					return 2;
				else if(GameContext.getManager().getMap().get("map1"+i).get(l).isAcrossPower()&& GameContext.getManager().getMap().get("map1"+i).get(m).isAcrossPower())
					return 1;
			else 
				break;
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
	public int getBombCountm() {
		return bombCount;
	}
	public void setBombCount(int bubblenum) {
		this.bombCount = bubblenum;
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
	public int getSetedBombCount() {
		return setedBombCount;
	}
	public void setSetedBombCount(int addbubnum) {
		this.setedBombCount = addbubnum;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}


	@Override
	public void destroy() {
		if(!isVisible()) {
			JOptionPane.showMessageDialog(DataProxy.jFrame, "<html><h1><span style=color:'orange'>恭喜 玩家 2 获得胜利!</span></h1><html>");
//			DataProxy.jFrame.remove(DataProxy.gamePanel);
			DataProxy.jFrame.remove(DataProxy.mainPanel);
			GameBar.getGameBar().reName("Easy");
			DataProxy.jFrame.add(DataProxy.indexPanel);
			DataProxy.jFrame.removeKeyListener(DataProxy.gameListener);
			new UpdateInventroy().clear();
			DataProxy.jFrame.setSize(Main.JFRAME_WIDTH, Main.JFRAME_HEIGHT);
			DataProxy.jFrame.repaint();
		}
	}
	
	public void pick() {
		Map<String,List<Element>> map = GameContext.getManager().getMap();
		List<Element> itemsList=map.get("items"+getLayer());
		for(int i=0;i<itemsList.size();i++)
		{
			if(itemsList.get(i).getX()<=(getX()+getW()/2)&&(itemsList.get(i).getX()+itemsList.get(i).getW())>=(getX()+getW()/2)) { //是否碰到

				switch (itemsList.get(i).getVariety()) {
				case 1://加泡泡
					setBombCount(getBombCountm()+1);
					new UpdateInventroy().updatePlayerTwoBomb(getBombCountm());
					break;
				case 2://加威力
					setPower(getPower()+1);
					new UpdateInventroy().updatePlayerTwoPwer(getPower());
					break;
				case 3://加速度
					setSpeed(getSpeed()+1);
					new UpdateInventroy().updatePlayerTwoSpeed(speed);
					break;
				}
				itemsList.get(i).setVisible(false); //吃掉后消失
			}
		}
		
	}
	
	public void burst() {
		if(isVisible()) {
			if(condition==1) {
				bursttime = 0;
				if(GameContext.getManager().getBubblelist().get((getLayer()-1)*15+(getX()+getW()/2)/40)==-1|| GameContext.getManager().getBubblelist().get((getLayer()-1)*15+(getX()+getW()/2)/40)==2|| GameContext.getManager().getBubblelist().get((getLayer()-1)*15+(getX()+getW()/2)/40)==3) {
					setCondition(0);
					setImg(LoadResource.getElementLoad().getMap().get("burst"));
				}
			}
			if(condition==0) {
				bursttime++;
				if(bursttime==50|| GameContext.getManager().getElementList("playerOne").size()>0&&(GameContext.getManager().getElementList("playerOne").get(0).getLayer()==getLayer()&& GameContext.getManager().getElementList("playerOne").get(0).getX()==getX())) {
					setVisible(false);
				}
			}
		}
	}
}
