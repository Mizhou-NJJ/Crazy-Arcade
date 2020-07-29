package me.simpleppt.lg;

import me.simpleppt.element.Element;
import me.simpleppt.man.GameContext;
import me.simpleppt.proxy.DataProxy;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class WorkThread extends Thread{
	public WorkThread(){}
	@Override
	public void run() {
			loadElement(DataProxy.whatMap); // 需要加载哪个地图?
			runGame();
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		//}
	}
	
	
	public void runGame() {
			while (DataProxy.isContinue) {
				Map<String, List<Element>> map =
						GameContext.getManager().getMap(); //  前面说过 这个map 保存了 整个地图 的所有方块信息 map中的值都是list<Element>
				if (map.get("playerOne").size() == 0 || map.get("playerTwo").size() == 0)//  or size!=1
					break;
				Set<String> set = map.keySet();
				for (String key : set) {
					List<Element> list = map.get(key);
//				for(int i=list.size()-1;i>=0;i--){
					for (int i = 0; i < list.size(); i++) {
						list.get(i).update();
						if (!list.get(i).isVisible() && key.indexOf("map1") != 0) {
							// 如果某个 Element 不可见了 就移除
							list.remove(i--);
						}
					}

				}
				try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	private void loadElement(int i) {
		GameContext.getManager().load(i);

	}
}
