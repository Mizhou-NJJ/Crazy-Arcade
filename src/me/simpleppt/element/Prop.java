package me.simpleppt.element;

import me.simpleppt.load.LoadResource;
import me.simpleppt.man.GameContext;

import javax.swing.*;
import java.awt.*;

// ������Ʒ
/*
* ������Ʒ�ĸ�Ϊ 48 ��Ϊ  32
* */
public class Prop extends Element {
	
	private int variety;//����
	private ImageIcon img;//ͼƬ

	public Prop(int x, int y, int w, int h, int variety, ImageIcon img) {
		super(x,y,w,h);
		this.variety = variety;
		this.img = img;
		setLayer((y+h)/40);
	}
	
	public static Prop createProp(int x, int y, int whatProp){
		ImageIcon img = LoadResource.getElementLoad().getMap().get("item"+whatProp);
		return new Prop(x,y,32,48,whatProp,img);
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

	@Override
	public void move() {
		
	}

	@Override
	public void destroy() {
		
	}

	public void update(){
		super.update();
		burst();
	}
	


	// ������Ʒ
	@Override
	public void showElement(Graphics g) {
		g.drawImage(img.getImage(),getX()+4,getY(),null);
	}
	
	
	public void burst() {
		if(isVisible()) {
				if(GameContext.getManager().getBubblelist().get((getLayer()-1)*15+(getX()+getW()/2)/40)==2||GameContext.getManager().getBubblelist().get((getLayer()-1)*15+(getX()+getW()/2)/40)==3) {
					setVisible(false);
				}
		}
	}
}
