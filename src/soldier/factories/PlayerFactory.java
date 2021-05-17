package soldier.factories;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import soldier.VisualObjects.Player;
import soldier.gameManagment.Position;

public class PlayerFactory {

    public Player RandomPlayer(Pane layer,Image BikermanImage,Image CenturionImage,Image HorsemanImage,Image RobotImage) {
        return new Player( layer, BikermanImage, CenturionImage, HorsemanImage, RobotImage);
    }

    public Player Player(Pane layer, String name, int score, Position position, Position destination, boolean isAlly) {
        return new Player( layer,  name,  score,  position,  destination, isAlly);
    }


}
