package soldier.ui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import soldier.core.DisplayBuilder;
import soldier.core.Unit;
import soldier.core.UnitGroup;
import soldier.gameManagment.Position;

import java.util.Iterator;
import java.util.List;

public class JFXBuilder implements DisplayBuilder {
    @Override
    public void updateUnit(Unit u, Position p, int rank, Pane layer, ImageView imageView) {
        imageView.relocate(p.getX()+36*rank, p.getY());
    }

    @Override
    public void addUnitToLayer(Pane layer, ImageView imageView) {
        layer.getChildren().add(imageView);
    }

    @Override
    public void removeUnitFromLayer(Pane layer, ImageView imageView) {
        layer.getChildren().remove(imageView);
    }

    @Override
    public void updateUnitGroup(UnitGroup a, Position p, Pane layer, List<ImageView> imageViews) {
        int rank = 0;
        for (Iterator<Unit> it = a.subUnits(); it.hasNext(); ) {
            updateUnit(it.next(),p,rank,layer, imageViews.get(rank));
            ++rank;
        }
    }

    @Override
    public void addUnitGroupToLayer(UnitGroup a, Pane layer, List<ImageView> imageViews) {
        int rank = 0;
        //System.out.println(imageViews.toString());
        for (Iterator<Unit> it = a.subUnits(); it.hasNext(); ) {
            //System.out.println("rank: "+rank);
            it.next();
            layer.getChildren().add(imageViews.get(rank));
            ++rank;
        }
    }

    @Override
    public void removeUnitGroupFromLayer(UnitGroup a, Pane layer, List<ImageView> imageViews) {

    }

}
