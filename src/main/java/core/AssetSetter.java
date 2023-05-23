package core;

import graphics.Game;
import variables.Constant;

public class AssetSetter {
    Game game;
    public AssetSetter(Game game){
        this.game = game;
    }
    public void setObject(){
        game.obj[0] = new OBJ_TelePort_In();
        game.obj[0].setPosition(14,1);

        game.obj[1] = new OBJ_TelePort_In();
        game.obj[1].setPosition(18,10);

        game.obj[2] = new OBJ_TelePort_Out();
        game.obj[2].setPosition(10,5);

        game.obj[3] = new OBJ_TelePort_Out();
        game.obj[3].setPosition(9,9);
    }
}
