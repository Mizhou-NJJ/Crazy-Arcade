package me.simpleppt.man;

import me.simpleppt.element.Element;
import me.simpleppt.element.Grid;
import me.simpleppt.load.LoadResource;

import java.util.*;

public class GameContext {
	Map<String,List<Element>> map;
	/*
	*  map��value���� list<MapSquare extends SuperElement>
	* */
	List<Integer> bubblelist;//ȫ��ͼ����list,0��ʾ�����ݣ�1�������ݣ�2�����ݱ�ը�ģ�-1�Ǳ�ը����
//	private MoveType moveType;
	
	//	��ʼ��
	protected void init(){
		bubblelist=new ArrayList<>();
		for(int i=0;i<195;i++) {
			bubblelist.add(0); 
		}
		map=new LinkedHashMap<>();
		List<Element> list=new ArrayList<>();
		map.put("bgmap1", list);
		List<Element> list1=new ArrayList<>();
		map.put("playerOne", list1);
		List<Element> listA=new ArrayList<>();
		map.put("playerTwo", listA);
		List<Element> list2=new ArrayList<>();
		map.put("nbubble", list2);
		for(int i=1;i<=13;i++) {
			List<Element> list3=new ArrayList<>();
			map.put("map1"+i, list3);
		}
		/*
		*  13 ��items ���� 13���� ��ʲô���أ� ��Ҳ��֪�������������ǳ���
		* */
		for(int i=1;i<=13;i++) {
			List<Element> list4=new ArrayList<>();
			map.put("items"+i, list4);
		}
	}
//	�õ�һ�������� map����
	public Map<String, List<Element>> getMap() {
		return map;
	}
//	�õ�һ��Ԫ�صļ���
	public List<Element> getElementList(String key){
		return map.get(key);
	}
//	�õ�ȫ��ͼ������list
	public List<Integer> getBubblelist() {
		return bubblelist;
	}
	
//	����
	private static GameContext elementManager;
//	���췽��˽�л�����ֻ���ڱ����п��� new
	private GameContext(){
		init();
	}
	static{
		if(elementManager ==null){
			elementManager=new GameContext();
		}
	}
	public static GameContext getManager(){
		return elementManager;
	}
//	������Ҫ����Դ
	public void load(int g) {
		/*
		*  what is g? �������� ������ 1 ���� 2
		*  ����ѡ�����ŵ�ͼ �� ResourcetLoad�У����ǰ����е�ͼ��Ϣ�������� ��һλ��ҵ�map��
		* ��� map �е� key �� Map+g
		* */
		LoadResource.getElementLoad().readImgPro();
		LoadResource.getElementLoad().readMapPro();
		LoadResource.getElementLoad().readImgPro2();
		LoadResource.getElementLoad().readPlayPro();
		LoadResource.getElementLoad().readtwoPlayPro();
		LoadResource.getElementLoad().readImgPro3();
//		ElementLoad.getElementLoad().readGamepro();

		
		Set<String> set=map.keySet(); //--��ȡmap�е�����key
		for(String key:set){//�������ڱ����Ĺ����У��������ڵ�Ԫ�ز����� ���ӻ���ɾ��
			map.get(key).clear();
		}
		
		Map<String, List<String>> map1=
				LoadResource.getElementLoad().getPlaymap();//���1�� ��ͼ��Ϣ
//		List<String> list1=map1.get("firstMapBG");//��ʼ������ Ҳ����ȫ�ǲ�ƺ list1.size()=1 -
//		String s1=list1.get(list1.size()-1);
//		String[] arr1=s1.split(",");
		String[] arr1=new String[195];
		for (int i=0;i<195;i++){
			arr1[i]="1";
		}
		/*
		* arr1 ���汣���� ��ͼ���ӱ���ͼƬ���� ����: 1 ,1 ,1
		* �� ���� ��ʼ����ɵ�Element �ž�list
		* */
		for(int i=0;i<arr1.length;i++) {
			map.get("bgmap1").add(Grid.createMapSquare((i%15)*40, (i/15)*40, arr1[i]));
		}
		
		map.get("playerOne").add(ElementFactory.elementFactory("onePlayer")); // ���� ��ʼ����ɵ� PlayerOne���� size=1
		map.get("playerTwo").add(ElementFactory.elementFactory("twoPlayer"));// ���س�ʼ����ɵ� PlayerTwo���� size=1
		
		List<String> list2=map1.get("Map"+g); //��ȡȫ��ͼÿ������Ĵ���list list��ֵ���� (����Ӧ��img���� ex:1 2 3...) // size==1
		String s2=list2.get(list2.size()-1);
		String[] arr2=s2.split(","); // ��ͼ1�е� ȫ�ַ������
		// ���� 15 �� 13 ��
		for(int i=0;i<13;i++) { //��
			for(int j=0;j<15;j++) { //��
				int k=i+1; //��ʾ�ڼ���
				/*
				*
				* ���� 13��map1+k ������this.map��
				* ÿ��map1+k ����һ������Ӧ�ĵ�ͼ�������
				* */
				map.get("map1"+k).add(Grid.createMapSquare(j*40, i*40, arr2[i*15+j]));
			}
		
		}
	}

}
