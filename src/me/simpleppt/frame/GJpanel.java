package me.simpleppt.frame;

import me.simpleppt.element.Element;
import me.simpleppt.man.GameContext;
import me.simpleppt.proxy.DataProxy;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GJpanel extends JPanel implements Runnable{
	@Override
	public void run(){
		/*
		 *  需要不断刷新Jpanel
		 * */
		while(DataProxy.isContinue){
			try {
				Thread.sleep(100);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			this.repaint();
		}
	}


	private void initMap(Graphics g) {
		Map<String,List<Element>> map=
				GameContext.getManager().getMap();
		Set<String> set=map.keySet();
		int playerlayer=1;
		int player2layer=1;
		if(!map.get("playerOne").isEmpty()) {
			playerlayer = map.get("playerOne").get(0).getLayer();
		}
		if(!map.get("playerTwo").isEmpty()) {
			player2layer = map.get("playerTwo").get(0).getLayer();
		}
		for(String key:set){
			if(key.indexOf("playerOne")==0||key.indexOf("items")==0) {
				
			}
			else if(key.indexOf("map1")==0) {
				List<Element> list=map.get(key);
//				System.out.println(list.size());  15
				for(int i=0;i<list.size();i++){
					list.get(i).showElement(g);
				}
				
				if(!map.get(key).isEmpty()) {
					List<Element> list1=map.get("items"+map.get(key).get(0).getLayer());
					for(int i=0;i<list1.size();i++){
						list1.get(i).showElement(g);
					}
					
					if(map.get(key).get(0).getLayer()==playerlayer&&map.get(key).get(0).getLayer()==player2layer) {
						if(map.get("playerOne").get(0).getY()>map.get("playerTwo").get(0).getY()) {
							List<Element> list2=map.get("playerOne");
//							System.out.println(list2.size());
							for(int i=0;i<list2.size();i++){
								list2.get(i).showElement(g);
							}
							list2=map.get("playerOne");
							for(int i=0;i<list2.size();i++){
								list2.get(i).showElement(g);
							}
						}
						else {
							List<Element> list2=map.get("playerOne");
							for(int i=0;i<list2.size();i++){
								list2.get(i).showElement(g);
							}
							list2=map.get("playerTwo");
							for(int i=0;i<list2.size();i++){
								list2.get(i).showElement(g);
							}
						}
					}
					else {
						if(map.get(key).get(0).getLayer()==playerlayer) {
							List<Element> list2=map.get("playerOne");
							for(int i=0;i<list2.size();i++){
								list2.get(i).showElement(g);
							}
						}
						if(map.get(key).get(0).getLayer()==player2layer) {
							List<Element> list2=map.get("playerTwo");
							for(int i=0;i<list2.size();i++){
								list2.get(i).showElement(g);
							}
						}
					}
				}
			}
			else {
				List<Element> list=map.get(key);
				for(int i=0;i<list.size();i++){
					list.get(i).showElement(g);
				}
			}
		}
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		initMap(g);
	}
	

}
