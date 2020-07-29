package me.simpleppt.load;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class LoadResource {
	/*
	* map����� ��Ϸ��ͼ�� ����ͼƬ��Դ.toString()==·��
	* ��getter
	* */
	private Map<String,ImageIcon> map;
	private Map<String,List<String>> playmap; // has getter
	private List<String> gameList;
	private Map<String,List<String>> twoplaymap;//has getter ?
	
	private static LoadResource load;
	//	pro�ļ���ȡ����
	private Properties pro;
	
	private LoadResource(){
		map=new HashMap<>();
		playmap=new HashMap<>();//һ����� ��ͼ��ʼ����Ϣ���ڴ�map��
		twoplaymap=new HashMap<>(); //�������
		pro=new Properties();
	}
	public static synchronized LoadResource getElementLoad(){
		if(load==null){
			load=new LoadResource();
		}
		return load;
	}
	
//	��ȡһ���������
	public void readPlayPro(){
		InputStream in= LoadResource.class.getClassLoader()
				.getResourceAsStream("me/simpleppt/resmapping/player_a.pro");
		try {
			pro.clear();
			pro.load(in);
			List<String> list=new ArrayList<>();
			/*
			 * key Map1 value=list ���list
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
//	��ȡ�����������
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

//	��ȡÿһ��С���ӵ�ͼƬ
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
				* new ImageIcon(url) ���ص���ͼƬ����·��
				* o.toString()��ʲô? ����pro�ļ��Ŀ�ͷ����,���� 9=xxx.png,o.toString=9
				* ���� map.put(9,img/map.3.png)����������
				* */
				map.put(o.toString(), new ImageIcon(url));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	��ȡ���ͼƬ
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
//			System.out.println("----���ͼƬMap"+map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	��ȡ������ƷͼƬ��Դ
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
//			System.out.println("-----������ƷͼƬMap"+map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
// ��λ��Ƶ�ͼ ? ���ŵ�ͼ�Ļ��ƴ���
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
