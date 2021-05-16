package soldier.ui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import soldier.core.DisplayBuilder;
import soldier.core.Unit;
import soldier.core.UnitGroup;
import soldier.gameManagment.Position;
import soldier.gameManagment.Settings;

import java.util.Iterator;
import java.util.List;

public class JFXBuilder implements DisplayBuilder {
    @Override
    public void updateUnit(Unit u, Position p, int rank, Pane layer, ImageView imageView, int size) {
        int rows = 3;
        int cols = size/rows +1;

        if (size == 1)
            imageView.relocate(p.getX()- Settings.UNIT_SIZE/2, p.getY()- Settings.UNIT_SIZE/2);

        if (size == 2)
            imageView.relocate(p.getX()- Settings.UNIT_OFFSET + rank*Settings.UNIT_OFFSET, p.getY()- Settings.UNIT_SIZE/2);

        if (size == 3){
            if (rank <2)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET + rank*Settings.UNIT_OFFSET, p.getY()- Settings.UNIT_OFFSET);

            if (rank == 2)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET/2, p.getY());
        }

        if (size == 4){
            if (rank < 2)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET + rank*Settings.UNIT_OFFSET, p.getY()- Settings.UNIT_OFFSET);
            if (rank > 1)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET + (rank-2)*Settings.UNIT_OFFSET, p.getY());
        }
        if (size == 5){
            if (rank < 3)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET-Settings.UNIT_OFFSET/2 + rank*Settings.UNIT_OFFSET, p.getY()- Settings.UNIT_OFFSET);
            if (rank > 2)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET + (rank-3)*Settings.UNIT_OFFSET, p.getY());
        }

        if (size == 6){
            if (rank < 3)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET-Settings.UNIT_OFFSET/2 + rank*Settings.UNIT_OFFSET, p.getY()- Settings.UNIT_OFFSET);
            if (rank > 2)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET -Settings.UNIT_OFFSET/2+ (rank-3)*Settings.UNIT_OFFSET, p.getY());
        }

        if (size == 7){
            if (rank < 3)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET-Settings.UNIT_OFFSET/2 + rank*Settings.UNIT_OFFSET, p.getY()- Settings.UNIT_OFFSET*1.5);
            if (6 > rank && rank > 2)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET -Settings.UNIT_OFFSET/2+ (rank-3)*Settings.UNIT_OFFSET, p.getY()- Settings.UNIT_OFFSET/2);
            if (rank > 5)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET -Settings.UNIT_OFFSET/2+ (rank-5)*Settings.UNIT_OFFSET, p.getY()+ Settings.UNIT_OFFSET/2);
        }

        if (size == 8){
            if (rank < 3)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET-Settings.UNIT_OFFSET/2 + rank*Settings.UNIT_OFFSET, p.getY()- Settings.UNIT_OFFSET*1.5);
            if (6 > rank && rank > 2)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET -Settings.UNIT_OFFSET/2+ (rank-3)*Settings.UNIT_OFFSET, p.getY()- Settings.UNIT_OFFSET/2);
            if (rank > 5)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET + (rank-6)*Settings.UNIT_OFFSET, p.getY()+ Settings.UNIT_OFFSET/2);
        }
        if (size == 9){
            if (rank < 3)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET-Settings.UNIT_OFFSET/2 + rank*Settings.UNIT_OFFSET, p.getY()- Settings.UNIT_OFFSET*1.5);
            if (6 > rank && rank > 2)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET -Settings.UNIT_OFFSET/2+ (rank-3)*Settings.UNIT_OFFSET, p.getY()- Settings.UNIT_OFFSET/2);
            if (rank > 5)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET -Settings.UNIT_OFFSET/2 + (rank-6)*Settings.UNIT_OFFSET, p.getY()+ Settings.UNIT_OFFSET/2);
        }
        if (size > 9){
            imageView.relocate(p.getX()+32*rank , p.getY() );
        }
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
    public void updateUnitGroup(UnitGroup a, Position p, Pane layer, List<ImageView> imageViews, Circle hitbox,int size) {
        hitbox.relocate(p.getX()-hitbox.getRadius(),p.getY()-hitbox.getRadius());
        int rank = 0;
        for (Iterator<Unit> it = a.subUnits(); it.hasNext(); ) {
            updateUnit(it.next(),p,rank,layer, imageViews.get(rank),size);
            ++rank;
        }
    }

    @Override
    public void addUnitGroupToLayer(UnitGroup a, Pane layer, List<ImageView> imageViews, Circle hitbox) {
        layer.getChildren().add(hitbox);
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
    public void removeUnitGroupFromLayer(UnitGroup a, Pane layer, List<ImageView> imageViews,Circle hitbox) {
        layer.getChildren().remove(hitbox);
        int rank = 0;
        //System.out.println(imageViews.toString());
        for (Iterator<Unit> it = a.subUnits(); it.hasNext(); ) {
            //System.out.println("rank: "+rank);
            it.next();
            layer.getChildren().remove(imageViews.get(rank));
            ++rank;
        }
    }

}
