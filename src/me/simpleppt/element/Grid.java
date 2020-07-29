package me.simpleppt.element;

import me.simpleppt.load.LoadResource;
import me.simpleppt.man.GameContext;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class Grid extends Element {//一个地图有15*13=195个格子
	private int variety;//种类
	private ImageIcon img;//图片
	private int times = 0;
	
	public Grid(int x, int y, int w, int h, int variety, ImageIcon img) {
		super(x,y,w,h);
		this.variety = variety; //是什么种类的图片呢?  ex: 1 是 默认草坪
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
		* 初始化 每个小方块的 width 和 height
		* 返回 已经初始化完成的Element对象
		* */
		int variety = Integer.parseInt(str);
		int w;
		int h;
		if(variety==0) {
			w = 0;
			h = 0;
			return new Grid(x,y,w,h,variety, LoadResource.getElementLoad().getMap().get("1"));
		}
		else if(variety==3){// 可以炸掉的 img/map 3 6 8
			w = 40;
			h = 44;
		}

		else if(variety==9){ // 树 img/map/9.png
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
	// 添加 道具
	public void addProp() {
		Random r=new Random();
		int what = r.nextInt(4);
		if(what!=0) {
			Prop prop = Prop.createProp(getX(), getY(),what); //返回初始化了的Prop对象
			List<Element> list= GameContext.getManager().getElementList("items"+prop.getLayer());// 从EM 中的map中获取集合 某层（行）的item
			list.add(prop);
		}
	}

}
