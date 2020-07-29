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
			loadElement(DataProxy.whatMap); // ��Ҫ�����ĸ���ͼ?
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
						GameContext.getManager().getMap(); //  ǰ��˵�� ���map ������ ������ͼ �����з�����Ϣ map�е�ֵ����list<Element>
				if (map.get("playerOne").size() == 0 || map.get("playerTwo").size() == 0)//  or size!=1
					break;
				Set<String> set = map.keySet();
				for (String key : set) {
					List<Element> list = map.get(key);
//				for(int i=list.size()-1;i>=0;i--){
					for (int i = 0; i < list.size(); i++) {
						list.get(i).update();
						if (!list.get(i).isVisible() && key.indexOf("map1") != 0) {
							// ���ĳ�� Element ���ɼ��� ���Ƴ�
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
