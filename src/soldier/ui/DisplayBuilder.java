package soldier.ui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import soldier.VisualObjects.Loot;
import soldier.VisualObjects.Player;
import soldier.core.Unit;
import soldier.core.UnitGroup;
import soldier.gameManagment.Position;

import java.util.List;

public interface DisplayBuilder {
    void updateUnit(Unit u, Position p, int rank, Pane layer, ImageView imageview, int size, int count, int dir);
    void addUnitToLayer(Pane layer, ImageView imageView);
    void removeUnitFromLayer(Pane layer, ImageView imageView);

    void updateUnitGroup(UnitGroup a, Position p, Pane layer, List<ImageView> imageViews, Circle hitbox, int size, int count, int dir);
    void addUnitGroupToLayer(UnitGroup a,Pane layer, List<ImageView> imageViews, Circle hitbox);
    void removeUnitGroupFromLayer(UnitGroup a,Pane layer, List<ImageView> imageViews, Circle hitbox);

    void addEquipmentToLayer(Pane layer, ImageView imageView, Circle hitbox);

    void removeEquipmentFromLayer(Pane layer, ImageView imageView, Circle hitbox);

    void updateEquipment(Position position, Pane layer, ImageView imageView, Circle hitbox, int count, int dir);

    void updateUI(Pane playfieldLayer, Pane uiLayer, List<Player> players, List<Loot> loots, Player player1);
}
