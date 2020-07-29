package me.simpleppt.man;

import me.simpleppt.element.Element;
import me.simpleppt.element.Grid;
import me.simpleppt.load.LoadResource;

import java.util.*;

public class GameContext {
	Map<String,List<Element>> map;
	/*
	*  map的value都是 list<MapSquare extends SuperElement>
	* */
	List<Integer> bubblelist;//全地图泡泡list,0表示无泡泡，1是有泡泡，2是泡泡爆炸的，-1是被炸过的
//	private MoveType moveType;
	
	//	初始化
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
		*  13 个items 村在 13行中 有什么用呢？ 我也不知道，反正代码是抄的
		* */
		for(int i=1;i<=13;i++) {
			List<Element> list4=new ArrayList<>();
			map.put("items"+i, list4);
		}
	}
//	得到一个完整的 map集合
	public Map<String, List<Element>> getMap() {
		return map;
	}
//	得到一个元素的集合
	public List<Element> getElementList(String key){
		return map.get(key);
	}
//	得到全地图的泡泡list
	public List<Integer> getBubblelist() {
		return bubblelist;
	}
	
//	单例
	private static GameContext elementManager;
//	构造方法私有化，就只有在本类中可以 new
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
//	加载需要的资源
	public void load(int g) {
		/*
		*  what is g? 至少现在 它等于 1 或着 2
		*  就是选择哪张地图 在 ResourcetLoad中，我们把所有地图信息保存在了 第一位玩家的map中
		* 玩家 map 中的 key 是 Map+g
		* */
		LoadResource.getElementLoad().readImgPro();
		LoadResource.getElementLoad().readMapPro();
		LoadResource.getElementLoad().readImgPro2();
		LoadResource.getElementLoad().readPlayPro();
		LoadResource.getElementLoad().readtwoPlayPro();
		LoadResource.getElementLoad().readImgPro3();
//		ElementLoad.getElementLoad().readGamepro();

		
		Set<String> set=map.keySet(); //--获取map中的所有key
		for(String key:set){//迭代器在遍历的过程中，迭代器内的元素不可以 增加或者删除
			map.get(key).clear();
		}
		
		Map<String, List<String>> map1=
				LoadResource.getElementLoad().getPlaymap();//玩家1的 地图信息
//		List<String> list1=map1.get("firstMapBG");//初始化背景 也就是全是草坪 list1.size()=1 -
//		String s1=list1.get(list1.size()-1);
//		String[] arr1=s1.split(",");
		String[] arr1=new String[195];
		for (int i=0;i<195;i++){
			arr1[i]="1";
		}
		/*
		* arr1 里面保存了 地图格子背景图片代号 例如: 1 ,1 ,1
		* 将 所有 初始化完成的Element 放经list
		* */
		for(int i=0;i<arr1.length;i++) {
			map.get("bgmap1").add(Grid.createMapSquare((i%15)*40, (i/15)*40, arr1[i]));
		}
		
		map.get("playerOne").add(ElementFactory.elementFactory("onePlayer")); // 返回 初始化完成的 PlayerOne对象 size=1
		map.get("playerTwo").add(ElementFactory.elementFactory("twoPlayer"));// 返回初始化完成的 PlayerTwo对象 size=1
		
		List<String> list2=map1.get("Map"+g); //获取全地图每个方块的代码list list的值就是 (所对应的img代码 ex:1 2 3...) // size==1
		String s2=list2.get(list2.size()-1);
		String[] arr2=s2.split(","); // 地图1中的 全局方块代码
		// 共有 15 列 13 行
		for(int i=0;i<13;i++) { //行
			for(int j=0;j<15;j++) { //列
				int k=i+1; //表示第几列
				/*
				*
				* 共有 13个map1+k 保存在this.map中
				* 每个map1+k 保存一行所对应的地图方块对象
				* */
				map.get("map1"+k).add(Grid.createMapSquare(j*40, i*40, arr2[i*15+j]));
			}
		
		}
	}

}
