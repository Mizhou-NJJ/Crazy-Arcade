package me.simpleppt.proxy;

import me.simpleppt.frame.GFrame;
import me.simpleppt.frame.InventoryComponent;
import me.simpleppt.lg.Listener;

import javax.swing.*;

public class DataProxy  {
    public static GFrame jFrame;
    public static JPanel gamePanel;
    public static JPanel indexPanel;
    public static int whatMap;
    public static Listener gameListener;
    public static boolean isContinue=true;
    public static InventoryComponent inventoryComponent;
    public static JPanel mainPanel;
    public static String selectPlayerOneImgPath=Skin.PLAYERS[1];
    public static String selectPlayerTwoImgPath=Skin.PLAYERS[0];
    public static String selectPlayerOneBombImgPath=Skin.BOMBS[0];
    public static String selectPlayerTwoBombImgPath=Skin.BOMBS[0];
    public static String [] mappingXGOne=Skin.BOMB_BLUE_XG;
    public static String [] mappingXgTwo=Skin.BOMB_BLUE_XG;
    public static boolean isS=true;
}
