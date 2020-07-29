package me.simpleppt.element;

import me.simpleppt.man.GameContext;
import me.simpleppt.proxy.DataProxy;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Bomb extends Element {
	private ImageIcon img;//���ݵ�����
	private int power;
	private String name;//�ĸ�����ŵ�����

	private int time;
	private int i=0;
	private float boomtime;
	private boolean toppass;
	private boolean downpass;
	private boolean rightpass;
	private boolean leftpass;
	private List<Integer> bubblelist;
//	private ImageIcon img0,img1,img2,img3,img4,img5,img6,img7,img8;
	
	public Bomb() {}
	
	public Bomb(int x, int y, int w, int h, ImageIcon img, String name, int power) {
		super(x, y, w, h);
		this.img=img;
		this.power=power;
		this.name=name;
	}
	
	public static Bomb createBomb(int x, int y, String url, String name, int power) {
		ImageIcon img=new ImageIcon(url);
		x=realx(x);//ը��Ӧ�÷ŵ���λ��
		y=realy(y);
		GameContext.getManager().getBubblelist().set(x/40+y/40*15, 1);//
		GameContext.getManager().getMap().get("map1"+(y/40+1)).get(x/40).setAcrossPower(false);
		return new Bomb(x,y,40,40,img,name,power);//ը������Ϊ1 ʱըһ��һ������
	}
	
	public void showElement(Graphics g) {
		toppass=true;
		downpass=true;
		rightpass=true;
		leftpass=true;
		bubblelist= GameContext.getManager().getBubblelist();
		boomtime=8/power;
		if(time<=33&&bubblelist.get(getX()/40+getY()/40*15)==1) {//����δ������ը������ʱ
			g.drawImage(img.getImage(),getX(),getY(),getW(),getH(),null);
		}
		else if(time<=33&&bubblelist.get(getX()/40+getY()/40*15)!=1) {
			for(;i<=power;i++) {
				if(i==0) {
					if (name.equals("twoplayer")) {
						g.drawImage((new ImageIcon(DataProxy.mappingXGOne[0])).getImage(), getX(), getY(), getBW(), getBH(), null);
					}else {
						g.drawImage((new ImageIcon(DataProxy.mappingXgTwo[0])).getImage(), getX(), getY(), getBW(), getBH(), null);
					}
					bubblelist.set(getX()/40+getY()/40*15, 3);
				}
				else if(time>=boomtime*i) {
					if(getY()/40-i>=0&&bubblelist.get(getX()/40+(getY()/40-i)*15)==-1) {//�ø����Ƿ��Ѿ���ը��ը�������ݻٵ����ӣ�
						toppass=false;
						if(time==37)
							bubblelist.set(getX()/40+(getY()/40-i)*15,0);
					}
					if(toppass&&!PK(getX()/40,getY()/40-i)) {//�����ը��û������
							if(i!=power)
								//�����ϵĻ���
								if (name.equals("twoplayer"))
								g.drawImage((new ImageIcon(DataProxy.mappingXGOne[5])).getImage(), getX(), getY()-i*getBH(), getBW(), getBH(), null);
								else g.drawImage((new ImageIcon(DataProxy.mappingXgTwo[5])).getImage(), getX(), getY()-i*getBH(), getBW(), getBH(), null);
							else
								//���µĻ���
								if (name.equals("twoplayer"))
								g.drawImage((new ImageIcon(DataProxy.mappingXGOne[3])).getImage(), getX(), getY()-i*getBH(), getBW(), getBH(), null);
								else
						        g.drawImage((new ImageIcon(DataProxy.mappingXgTwo[3])).getImage(), getX(), getY()-i*getBH(), getBW(), getBH(), null);
							bubblelist.set(getX()/40+(getY()/40-i)*15, 2);
							if(bubblelist.get(getX()/40+(getY()/40-i)*15)==1)
								bubblelist.set(getX()/40+(getY()/40-i)*15, -1);					
					}
					else {
						toppass=false;
					}
					if(getY()/40+i<13&&bubblelist.get(getX()/40+(getY()/40+i)*15)==-1) {//�ø����Ƿ��Ѿ�������ը�������ݻٵ����ӣ�
						downpass=false;
						if(time==37)
							bubblelist.set(getX()/40+(getY()/40+i)*15,0);
					}
					if(downpass&&!PK(getX()/40,getY()/40+i)) {//���������
							if(i!=power)
								//down
								if (name.equals("twoplayer"))
								g.drawImage((new ImageIcon(DataProxy.mappingXGOne[6])).getImage(), getX(), getY()+i*getBH(), getBW(), getBH(), null);
								else g.drawImage((new ImageIcon(DataProxy.mappingXgTwo[6])).getImage(), getX(), getY()+i*getBH(), getBW(), getBH(), null);
							else
								//down
								if (name.equals("twoplayer"))
								g.drawImage((new ImageIcon(DataProxy.mappingXGOne[4])).getImage(), getX(), getY()+i*getBH(), getBW(), getBH(), null);
								else g.drawImage((new ImageIcon(DataProxy.mappingXgTwo[4])).getImage(), getX(), getY()+i*getBH(), getBW(), getBH(), null);
							bubblelist.set(getX()/40+(getY()/40+i)*15, 2);
						
					}
					else {
						downpass=false;
					}
					if((getX()/40+i)<195&&bubblelist.get(getX()/40+i+getY()/40*15)==-1) {//�ø����Ƿ��Ѿ�������ը�������ݻٵ����ӣ�
						rightpass=false;
						if(time==37)
							bubblelist.set(getX()/40+i+getY()/40*15,0);
					}
					if(rightpass&&!PK(getX()/40+i,getY()/40)) {//�ұ�
							if(i!=power)
								// to right
								if (name.equals("twoplayer"))
								g.drawImage((new ImageIcon(DataProxy.mappingXGOne[7])).getImage(), getX()+i*getBW(), getY(), getBW(), getBH(), null);
								else g.drawImage((new ImageIcon(DataProxy.mappingXgTwo[7])).getImage(), getX()+i*getBW(), getY(), getBW(), getBH(), null);
							else
								//to left
								if (name.equals("twoplayer"))
								g.drawImage((new ImageIcon(DataProxy.mappingXGOne[1])).getImage(), getX()+i*getBW(), getY(), getBW(), getBH(), null);
								else g.drawImage((new ImageIcon(DataProxy.mappingXgTwo[1])).getImage(), getX()+i*getBW(), getY(), getBW(), getBH(), null);
							bubblelist.set(getX()/40+i+getY()/40*15, 2);
						
					}
					else {
						rightpass=false;
					}
					if((getX()/40-i)>=0&&bubblelist.get(getX()/40-i+getY()/40*15)==-1) {//�ø����Ƿ��Ѿ�������ը�������ݻٵ����ӣ�
						leftpass=false;
						if(time==37)
							bubblelist.set(getX()/40-i+getY()/40*15,0);
					}
					if(leftpass&&!PK(getX()/40-i,getY()/40)) {//���
							if(i!=power)
								if (name.equals("twoplayer"))
								g.drawImage((new ImageIcon(DataProxy.mappingXGOne[8])).getImage(), getX()-i*getBW(), getY(), getBW(), getBH(), null);//�����pp
								else g.drawImage((new ImageIcon(DataProxy.mappingXgTwo[8])).getImage(), getX()-i*getBW(), getY(), getBW(), getBH(), null);//�����pp
							else
								if (name.equals("twoplayer"))
								g.drawImage((new ImageIcon(DataProxy.mappingXGOne[2])).getImage(), getX()-i*getBW(), getY(), getBW(), getBH(), null);//�����pp
								else g.drawImage((new ImageIcon(DataProxy.mappingXgTwo[2])).getImage(), getX()-i*getBW(), getY(), getBW(), getBH(), null);//�����pp
							bubblelist.set(getX()/40-i+getY()/40*15, 2);
						
					}
					else {
						leftpass=false;
					}
				}
			}
			i=0;
			time=34;
		}
		else if((time-33)<6){
			if((time-33)<5) {
				for(;i<=power;i++) {
					if(i==0) {
						if (name.equals("twoplayer"))
						g.drawImage((new ImageIcon(DataProxy.mappingXGOne[0])).getImage(), getX(), getY(), getBW(), getBH(), null);
						else g.drawImage((new ImageIcon(DataProxy.mappingXgTwo[0])).getImage(), getX(), getY(), getBW(), getBH(), null);
						bubblelist.set(getX()/40+getY()/40*15, 3);
					}
					else if(time>=boomtime*i) {
						if(toppass&&!PK(getX()/40,getY()/40-i)) {//���������û�����Ӻ�����ʱ
								if(i!=power)
									if (name.equals("twoplayer"))
									g.drawImage((new ImageIcon(DataProxy.mappingXGOne[5])).getImage(), getX(), getY()-i*getBH(), getBW(), getBH(), null);//���ϵ�pp
									else g.drawImage((new ImageIcon(DataProxy.mappingXgTwo[5])).getImage(), getX(), getY()-i*getBH(), getBW(), getBH(), null);//���ϵ�pp
								else
									if (name.equals("twoplayer"))
									g.drawImage((new ImageIcon(DataProxy.mappingXGOne[3])).getImage(), getX(), getY()-i*getBH(), getBW(), getBH(), null);//���ϵ�pp
									else g.drawImage((new ImageIcon(DataProxy.mappingXgTwo[3])).getImage(), getX(), getY()-i*getBH(), getBW(), getBH(), null);//���ϵ�pp
								bubblelist.set(getX()/40+(getY()/40-i)*15, 2);
								if(bubblelist.get(getX()/40+(getY()/40-i)*15)==1)
									bubblelist.set(getX()/40+(getY()/40-i)*15, -1);					
						}
						else if(toppass) {
							toppass=false;
						}
						else {
							toppass=false;
						}
						if(downpass&&!PK(getX()/40,getY()/40+i)) {//���������
								if(i!=power)
									if (name.equals("twoplayer"))
									g.drawImage((new ImageIcon(DataProxy.mappingXGOne[6])).getImage(), getX(), getY()+i*getBH(), getBW(), getBH(), null);//���µ�pp
									else g.drawImage((new ImageIcon(DataProxy.mappingXgTwo[6])).getImage(), getX(), getY()+i*getBH(), getBW(), getBH(), null);//���µ�pp
								else
									if (name.equals("twoplayer"))
									g.drawImage((new ImageIcon(DataProxy.mappingXGOne[4])).getImage(), getX(), getY()+i*getBH(), getBW(), getBH(), null);//���µ�pp
									else g.drawImage((new ImageIcon(DataProxy.mappingXgTwo[4])).getImage(), getX(), getY()+i*getBH(), getBW(), getBH(), null);//���µ�pp
								bubblelist.set(getX()/40+(getY()/40+i)*15, 2);
							
						}
						else if(downpass) {
							downpass=false;
						}
						else {
							downpass=false;
						}
						if(rightpass&&!PK(getX()/40+i,getY()/40)) {//�ұ�
								if(i!=power)
									if (name.equals("twoplayer"))
									g.drawImage((new ImageIcon(DataProxy.mappingXGOne[7])).getImage(), getX()+i*getBW(), getY(), getBW(), getBH(), null);//���ҵ�pp
									else g.drawImage((new ImageIcon(DataProxy.mappingXgTwo[7])).getImage(), getX()+i*getBW(), getY(), getBW(), getBH(), null);//���ҵ�pp
								else
									if (name.equals("twoplayer"))
									g.drawImage((new ImageIcon(DataProxy.mappingXGOne[1])).getImage(), getX()+i*getBW(), getY(), getBW(), getBH(), null);//���ҵ�pp
									else g.drawImage((new ImageIcon(DataProxy.mappingXgTwo[1])).getImage(), getX()+i*getBW(), getY(), getBW(), getBH(), null);//���ҵ�pp
								bubblelist.set(getX()/40+i+getY()/40*15, 2);
							
						}
						else if(rightpass) {
							rightpass=false;
						}
						else {
							rightpass=false;
						}
						if(leftpass&&!PK(getX()/40-i,getY()/40)) {//���
								if(i!=power)
									if (name.equals("twoplayer"))
									g.drawImage((new ImageIcon(DataProxy.mappingXGOne[8])).getImage(), getX()-i*getBW(), getY(), getBW(), getBH(), null);//�����pp
									else g.drawImage((new ImageIcon(DataProxy.mappingXgTwo[8])).getImage(), getX()-i*getBW(), getY(), getBW(), getBH(), null);//�����pp
								else
									if (name.equals("twoplayer"))
									g.drawImage((new ImageIcon(DataProxy.mappingXGOne[2])).getImage(), getX()-i*getBW(), getY(), getBW(), getBH(), null);//�����pp
									else g.drawImage((new ImageIcon(DataProxy.mappingXgTwo[2])).getImage(), getX()-i*getBW(), getY(), getBW(), getBH(), null);//�����pp
								bubblelist.set(getX()/40-i+getY()/40*15, 2);
							
						}
						else if(leftpass) {
							leftpass=false;
						}
						else {
							leftpass=false;
						}
					}
				}
				i=0;
			}
			else {
				for(int i=0;i<=power;i++) {
					if(i==0) {
						bubblelist.set(getX()/40+getY()/40*15,0);
					}
					else {
						if(getY()/40-i>=0)
							bubblelist.set(getX()/40+(getY()/40-i)*15, 0);	
						if(getY()/40+i<13)
							bubblelist.set(getX()/40+(getY()/40+i)*15, 0);
						if(getX()/40+i<195)
							bubblelist.set(getX()/40+i+getY()/40*15, 0);
						if(getX()/40-i>=0)
							bubblelist.set(getX()/40-i+getY()/40*15, 0);
					}
				}
			}
		}
		else {
			GameContext.getManager().getMap().get("map1"+(getY()/40+1)).get(getX()/40).setAcrossPower(true);
			this.setVisible(false);
			if(name.equals("oneplayer")) {
				PlayerOne playerOne=(PlayerOne) GameContext.getManager().getMap().get("playerOne").get(0);
				playerOne.setSetedBonCount(playerOne.getSetedBonCount()-1);
			}
			else if(name.equals("twoplayer")) {
				PlayerTwo playerTwo=(PlayerTwo)GameContext.getManager().getMap().get("playerTwo").get(0);
				playerTwo.setSetedBombCount(playerTwo.getSetedBombCount()-1);
			}
		}
	}
	
	private static int realx(int x) {//���ݵ�ͼ�ĸ������޸�ը����λ��
		x=x/40*40;
		return x;
	}
	
	private static int realy(int y) {
		y=y/40*40;
		return y;
	}
	
	public boolean PK(int r,int i) {//i:�ڼ���(i+1��ʾ��map1�е�����)��r���ڼ���
		i+=1;
		bubblelist= GameContext.getManager().getBubblelist();
		if(GameContext.getManager().getMap().get("map1"+i).get(r).getBurstPower()==1) {//������
			GameContext.getManager().getMap().get("map1"+i).get(r).setVisible(false);
			bubblelist.set(r+(i-1)*15, -1);
			if(time==37) {
				bubblelist.set(r+(i-1)*15, 0);
			}
			return true;
		}
		else if(GameContext.getManager().getMap().get("map1"+i).get(r).getBurstPower()==0) {//����
			bubblelist.set(r+(i-1)*15, -1);
			return true;
		}
		else if(bubblelist.get(r+(i-1)*15)==3||bubblelist.get(r+(i-1)*15)==1||bubblelist.get(r+(i-1)*15)==-1) {
			bubblelist.set(r+(i-1)*15, -1);
			return true;
		}
		return false;//������
	}
	
	
	public void update() {
		time++;
	}


	public int getPower() {
		return power;
	}
	
	public int getBW() {
		return 40;
	}
	public int getBH() {
		return 40;
	}

	
	@Override
	public void move() {
		// pass
		
	}

	@Override
	public void destroy() {
	// pass
		
	}
	
	
}
