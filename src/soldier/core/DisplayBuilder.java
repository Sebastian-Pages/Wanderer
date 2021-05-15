package soldier.core;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import soldier.gameManagment.Map;
import soldier.gameManagment.Player;
import soldier.gameManagment.Position;

import java.util.List;

public interface DisplayBuilder {
    void updateUnit(Unit u, Position p, int rank, Pane layer, ImageView imageview);
    void addUnitToLayer(Pane layer, ImageView imageView);
    void removeUnitFromLayer(Pane layer, ImageView imageView);

    void updateUnitGroup(UnitGroup a, Position p, Pane layer, List<ImageView> imageViews);
    void addUnitGroupToLayer(UnitGroup a,Pane layer, List<ImageView> imageViews);
    void removeUnitGroupFromLayer(UnitGroup a,Pane layer, List<ImageView> imageViews);


    //void displayUnitGroup(UnitGroup a, Position p);


}
