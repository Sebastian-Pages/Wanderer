package soldier.core;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import soldier.gameManagment.Map;
import soldier.gameManagment.Player;
import soldier.gameManagment.Position;

import java.util.List;

public interface DisplayBuilder {
    void updateUnit(Unit u, Position p, int rank, Pane layer, ImageView imageview);
    void addUnitToLayer(Pane layer, ImageView imageView);
    void removeUnitFromLayer(Pane layer, ImageView imageView);

    void updateUnitGroup(UnitGroup a, Position p, Pane layer, List<ImageView> imageViews, Circle hitbox);
    void addUnitGroupToLayer(UnitGroup a,Pane layer, List<ImageView> imageViews, Circle hitbox);
    void removeUnitGroupFromLayer(UnitGroup a,Pane layer, List<ImageView> imageViews, Circle hitbox);


    //void displayUnitGroup(UnitGroup a, Position p);


}
