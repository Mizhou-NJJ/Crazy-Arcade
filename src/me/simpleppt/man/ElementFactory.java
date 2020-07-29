package me.simpleppt.man;

import me.simpleppt.element.Element;
import me.simpleppt.element.PlayerOne;
import me.simpleppt.element.PlayerTwo;
import me.simpleppt.load.LoadResource;

import java.util.List;
import java.util.Map;

public class ElementFactory {
	public static Element elementFactory(String name){
		Map<String, List<String>> map=
			LoadResource.getElementLoad().getPlaymap();
		Map<String, List<String>> map1=
				LoadResource.getElementLoad().getTwoplaymap();
		switch(name){
		case "onePlayer":
			List<String> list=map.get(name);
			String s=list.get(0);//player,0,0,40,40
			return PlayerOne.createPlayer(s);
		case "twoPlayer":
			List<String> list1=map1.get(name);
			String s1=list1.get(0);//player,0,0,40,40
			return PlayerTwo.createPlayerB(s1);
		}
		return null;
	}
}
