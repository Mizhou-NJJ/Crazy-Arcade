package me.simpleppt.load;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class LoadResource {
	/*
	* map里面放 游戏地图中 所有图片资源.toString()==路径
	* 有getter
	* */
	private Map<String,ImageIcon> map;
	private Map<String,List<String>> playmap; // has getter
	private List<String> gameList;
	private Map<String,List<String>> twoplaymap;//has getter ?
	
	private static LoadResource load;
	//	pro文件读取对象
	private Properties pro;
	
	private LoadResource(){
		map=new HashMap<>();
		playmap=new HashMap<>();//一号玩家 地图初始化信息存在此map中
		twoplaymap=new HashMap<>(); //二号玩家
		pro=new Properties();
	}
	public static synchronized LoadResource getElementLoad(){
		if(load==null){
			load=new LoadResource();
		}
		return load;
	}
	
//	读取一号玩家配置
	public void readPlayPro(){
		InputStream in= LoadResource.class.getClassLoader()
				.getResourceAsStream("me/simpleppt/resmapping/player_a.pro");
		try {
			pro.clear();
			pro.load(in);
			List<String> list=new ArrayList<>();
			/*
			 * key Map1 value=list 这个list
			 */
			for(Object o:pro.keySet()){
				String str=pro.getProperty(o.toString());
				list.add(str);
				playmap.put(o.toString(), list);
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	读取二号玩家配置
	public void readtwoPlayPro(){
		InputStream in= LoadResource.class.getClassLoader()
				.getResourceAsStream("me/simpleppt/resmapping/player_b.pro");
		try {
			pro.clear();
			pro.load(in);
			
			for(Object o:pro.keySet()){
				List<String> list=new ArrayList<>();
				String str=pro.getProperty(o.toString());
				list.add(str);
				twoplaymap.put(o.toString(), list);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	读取每一个小格子的图片
	public void readImgPro(){
		InputStream in= LoadResource.class.getClassLoader()
			.getResourceAsStream("me/simpleppt/resmapping/grid_img_path.pro");
		try {
			pro.clear();
			pro.load(in);
			Set<?> set=pro.keySet();
			for(Object o:set){
				String url=pro.getProperty(o.toString());
				/*
				* new ImageIcon(url) 返回的是图片所在路径
				* o.toString()是什么? 他是pro文件的开头数字,例如 9=xxx.png,o.toString=9
				* 所以 map.put(9,img/map.3.png)就是这样了
				* */
				map.put(o.toString(), new ImageIcon(url));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	读取玩家图片
	public void readImgPro2(){
		InputStream in= LoadResource.class.getClassLoader()
			.getResourceAsStream("me/simpleppt/resmapping/player_img_path.pro");
		try {
			pro.clear();
			pro.load(in);
//			System.out.println(pro.keys());
//			System.out.println(pro.keySet());
			Set<?> set=pro.keySet();
			for(Object o:set){
//				pro.getProperty((String)o);
				String url=pro.getProperty(o.toString());
//				System.out.println((String)o+":"+o.toString());
//				System.out.println(o+":"+url);
				map.put(o.toString(), new ImageIcon(url));
			}
//			System.out.println("----玩家图片Map"+map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	读取掉落物品图片资源
	public void readImgPro3(){
		InputStream in= LoadResource.class.getClassLoader()
			.getResourceAsStream("me/simpleppt/resmapping/prop_img_path.pro");
		try {
			pro.clear();
			pro.load(in);
//			System.out.println(pro.keys());
//			System.out.println(pro.keySet());
			Set<?> set=pro.keySet();
			for(Object o:set){
//				pro.getProperty((String)o);
				String url=pro.getProperty(o.toString());
//				System.out.println((String)o+":"+o.toString());
//				System.out.println(o+":"+url);
				map.put(o.toString(), new ImageIcon(url));
			}
//			System.out.println("-----掉落物品图片Map"+map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
// 如何绘制地图 ? 整张地图的绘制代码
	public void readMapPro(){
		InputStream in= LoadResource.class.getClassLoader()
				.getResourceAsStream("me/simpleppt/resmapping/map_grid_codepoint.pro");
		try {
			pro.clear();
			pro.load(in);
			for(Object o:pro.keySet()){
				List<String> list=new ArrayList<>();
				String str=pro.getProperty(o.toString());
				list.add(str);
				playmap.put(o.toString(), list);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println(playmap);
	}

	public Map<String, ImageIcon> getMap() {

		return map;
	}
	public Map<String, List<String>> getPlaymap() {
		return playmap;
	}
	public Map<String, List<String>> getTwoplaymap() {
		return twoplaymap;
	}

}
