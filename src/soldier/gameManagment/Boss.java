package soldier.gameManagment;

import javafx.scene.layout.Pane;

public class Boss extends  Player{

    public Boss(Pane layer, String name, int score, Position position, Position destination, boolean isAlly) {
        super(layer, name, score, position, destination, isAlly);
        this.hitbox.setRadius(60);
    }

    @Override
    public void count(){
        super.count+=1;
        if ((count > 159)&&(dir!=5)) {
            count = 0;
            System.out.println("DEBUG: "+dir);
            this.dir +=1;
            if (dir == 4)
                dir=0;
        }
    }
    @Override
    public int getDir(){
        return this.dir;
    }
}
