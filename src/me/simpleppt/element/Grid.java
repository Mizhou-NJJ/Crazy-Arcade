package me.simpleppt.element;

import me.simpleppt.load.LoadResource;
import me.simpleppt.man.GameContext;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class Grid extends Element {//һ����ͼ��15*13=195������
	private int variety;//����
	private ImageIcon img;//ͼƬ
	private int times = 0;
	
	public Grid(int x, int y, int w, int h, int variety, ImageIcon img) {
		super(x,y,w,h);
		this.variety = variety; //��ʲô�����ͼƬ��?  ex: 1 �� Ĭ�ϲ�ƺ
		this.img = img;
		setLayer((y+40)/40);
		switch (variety) {
		case 0:
			setAcrossPower(true);
			setBurstPower(2);
			break;
		case 1:
			setAcrossPower(true);
			setBurstPower(0);
			break;
		case 2:
			setAcrossPower(true);
			setBurstPower(0);
			break;
		case 3:
			setAcrossPower(false);
			setBurstPower(1);
			break;
		case 4:
			setAcrossPower(true);
			setBurstPower(0);
			break;
		case 5:
			setAcrossPower(true);
			setBurstPower(1);
			break;
		case 6:
			setAcrossPower(false);
			setBurstPower(1);
			break;
		case 7:
			setAcrossPower(false);
			setBurstPower(1);
			break;
		case 8:
			setAcrossPower(false);
			setBurstPower(1);
			break;
		case 9:
			setAcrossPower(false);
			setBurstPower(0);
			break;
		case 10:
			setAcrossPower(false);
			setBurstPower(0);
			break;
		}
	}
	
	public static Grid createMapSquare(int x, int y, String str){
		/*
		*
		* ��ʼ�� ÿ��С����� width �� height
		* ���� �Ѿ���ʼ����ɵ�Element����
		* */
		int variety = Integer.parseInt(str);
		int w;
		int h;
		if(variety==0) {
			w = 0;
			h = 0;
			return new Grid(x,y,w,h,variety, LoadResource.getElementLoad().getMap().get("1"));
		}
		else if(variety==3){// ����ը���� img/map 3 6 8
			w = 40;
			h = 44;
		}

		else if(variety==9){ // �� img/map/9.png
			w = 40;
			h = 67;
		}
		else {
			w = 40;
			h = 40;
		}
		ImageIcon img = LoadResource.getElementLoad().getMap().get(str);
		return new Grid(x,y,w,h,variety,img);
	}
	
	public int getVariety() {
		return variety;
	}
	
	public void setVariety(int variety) {
		this.variety = variety;
	}

	public ImageIcon getImg() {
		return img;
	}
	
	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public void showElement(Graphics g) {
		g.drawImage(img.getImage(), 
				getX(), getY()-getH()+40,
				getX()+getW(), getY()+40,
				0, 0,
				getW(), getH(),
				null);
	}

	public void move() {
		//pass
	}

	public void destroy() {
		if(!isVisible()&&times==0) {
			addProp();
			setW(0);
			setH(0);
			setAcrossPower(true);
			setBurstPower(2);
			times = 1;
		}
	}
	// ��� ����
	public void addProp() {
		Random r=new Random();
		int what = r.nextInt(4);
		if(what!=0) {
			Prop prop = Prop.createProp(getX(), getY(),what); //���س�ʼ���˵�Prop����
			List<Element> list= GameContext.getManager().getElementList("items"+prop.getLayer());// ��EM �е�map�л�ȡ���� ĳ�㣨�У���item
			list.add(prop);
		}
	}

}
