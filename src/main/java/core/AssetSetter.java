package core;

import graphics.Game;
import variables.Constant;

public class AssetSetter {
    Game game;
    public AssetSetter(Game game){
        this.game = game;
    }
    public void setObject(){
        //set Port in
        Game.Object[0] = new OBJ_TelePort_In(4,8);
        Game.Object[1] = new OBJ_TelePort_In(15,6);
        Game.Object[6] = new OBJ_TelePort_In(11,6);
        Game.Object[7] = new OBJ_TelePort_In(8,8);

        //set Port out
        Game.Object[2] = new OBJ_TelePort_Out(2,2);
        Game.PortList[0] = Game.Object[2];
        Game.Object[3] = new OBJ_TelePort_Out(17,2);
        Game.PortList[1] = Game.Object[3];
        Game.Object[4] = new OBJ_TelePort_Out(2,12);
        Game.PortList[2] = Game.Object[4];
        Game.Object[5] = new OBJ_TelePort_Out(17,12);
        Game.PortList[3] = Game.Object[5];
    }
}
