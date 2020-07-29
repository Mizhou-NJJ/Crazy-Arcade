package me.simpleppt.lg;

import me.simpleppt.element.PlayerOne;
import me.simpleppt.element.PlayerTwo;
import me.simpleppt.frame.GameBar;
import me.simpleppt.frame.InventoryComponent;
import me.simpleppt.man.GameContext;
import me.simpleppt.proxy.DataProxy;

import javax.swing.*;

public class UpdateInventroy {
    /*
    *  PlayerOne : bomb: 0 ,power 1,speed:2 ,life:3
    * PlayerTwo : bomb 10, power 11,speed 12 ,life :13
    * */
    public UpdateInventroy(){}
    // 跟新UI 可能需要花费时间 ,开启线程
    private void update(int upWho,int v){
        if (v>9) return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                switch (upWho){
                    case 0:
                        InventoryComponent.BOMB_A_J.setText(String.valueOf(v));break;
                    case 1:
                        InventoryComponent.POWER_A_J.setText(String.valueOf(v));break;
                    case 2:
                        InventoryComponent.SPEED_A_J.setText(String.valueOf(v+1)); break;
                    case 10:
                        InventoryComponent.BOMB_B_J.setText(String.valueOf(v)); break;
                    case 11:
                        InventoryComponent.POWER_B_J.setText(String.valueOf(v)); break;
                    case 12:
                        InventoryComponent.SPEED_B_J.setText(String.valueOf(v+1)); break;
                    case 3:
                        InventoryComponent.LIFE_A_J.setText(String.valueOf(v)); break;
                    case 13:
                        InventoryComponent.LIFE_B_J.setText(String.valueOf(v)); break;
                    default: break;
                }
            }
        }).start();
    }


    public void updatePlayerOneBomb(int bombCount){
        update(0,bombCount);
    }
    public void updatePlayerTwoBomb(int bombCount){
        update(10,bombCount);
    }
    public void  updatePlayerOnePower(int power){
        update(1,power);
    }
    public void updatePlayerTwoPwer(int power){
        update(11,power);
    }
    public void updatePlayerOneSpeed(int speed){
        update(2,speed);
    }
    public void  updatePlayerTwoSpeed(int speed){
        update(12,speed);
    }
    public void updatePlayerOneLife(){
//        update(3,life);
        PlayerOne playerOne=(PlayerOne) GameContext.getManager().getMap().get("playerOne").get(0);
        if (playerOne.getCondition()==0&&playerOne.getLife()>0){
            playerOne.setCondition(1);
            playerOne.setLife(playerOne.getLife()-1);
            update(3,playerOne.getLife());
            playerOne.setImg(new ImageIcon(DataProxy.selectPlayerOneImgPath));
        }
    }
    public void updatePlayerTwoLife(){
//        update(13,life);
        PlayerTwo playerTwo=(PlayerTwo) GameContext.getManager().getMap().get("playerTwo").get(0);
        if (playerTwo.getCondition()==0&&playerTwo.getLife()>0){
            playerTwo.setCondition(1);
            playerTwo.setLife(playerTwo.getLife()-1);
            playerTwo.setImg(new ImageIcon(DataProxy.selectPlayerTwoImgPath));
            update(13,playerTwo.getLife());
        }
    }
    public  void clear(){
        /*
        *  bomb =1  speed=0 power =1 life=1
        *    0      2        1        13
        * */

        PlayerOne playerOne=(PlayerOne)GameContext.getManager().getMap().get("playerOne").get(0);
        PlayerTwo playerTwo=(PlayerTwo) GameContext.getManager().getMap().get("playerTwo").get(0);
        playerOne.setPower(1);
        playerOne.setLife(1);
        playerOne.setBombCount(1);
        playerOne.setSpeed(0);
        playerOne.setSetedBonCount(0);
        playerTwo.setPower(1);
        playerTwo.setLife(1);
        playerTwo.setBombCount(1);
        playerTwo.setSpeed(0);
        playerTwo.setSetedBombCount(0);
        update(0,1);
        update(2,0);
        update(1,1);
        update(3,1);
        update(10,1);
        update(12,0);
        update(13,1);
        update(11,1);

    }
}
