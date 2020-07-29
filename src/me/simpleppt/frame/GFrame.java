package me.simpleppt.frame;

import me.simpleppt.Main;
import me.simpleppt.lg.WorkThread;
import me.simpleppt.proxy.DataProxy;

import javax.swing.*;

public class GFrame extends JFrame{
	private JPanel jp;
	public GFrame(String appName){
		this.setTitle(appName);
		this.setSize(Main.JFRAME_WIDTH, Main.JFRAME_HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);//居中
	}


	public void open(){
		this.add(DataProxy.indexPanel);
		this.setVisible(true);
	}
	public void startGame(){
		//		线程启动。。。
		WorkThread workThread=new WorkThread();
		workThread.start();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		new Thread((Runnable) jp).start();
		Thread s=new Thread((Runnable) jp);
		s.start();
	}



	public void setJp(JPanel jp) {
		this.jp = jp;
	}

}
