package me.simpleppt;

import me.simpleppt.frame.*;
import me.simpleppt.lg.Listener;
import me.simpleppt.proxy.DataProxy;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {
	public final static int JFRAME_WIDTH=610;
	public final static int JFRAME_HEIGHT=550;
	public static void main(String[] args) {

		GFrame gf=new GFrame("≈›≈›Ã√_2016011841");
		DataProxy.jFrame=gf;
		IndexComponent indexComponent=new IndexComponent();
		DataProxy.indexPanel=indexComponent;

		Listener listener=new Listener();
		DataProxy.gameListener=listener;
		DataProxy.inventoryComponent=InventoryComponent.getInventoryComponent();

		gf.open();
	}

}
