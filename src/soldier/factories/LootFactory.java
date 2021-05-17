package soldier.factories;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import soldier.VisualObjects.Loot;
import soldier.core.Equipment;
import soldier.gameManagment.Position;

public class LootFactory {

    public Loot Loot(Pane layer, int cost, String name, Position position, int count, int dir, ImageView imageView, Equipment equipment) {
            return new Loot( layer,  cost,  name,  position,  count,  dir, imageView,  equipment) ;
        }
    public Loot RandomLoot(Pane layer, Image shieldImage, Image swordImage, Image potionImage) {
            return new Loot(layer, shieldImage, swordImage, potionImage) ;
        }
}
