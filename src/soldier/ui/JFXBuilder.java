package soldier.ui;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import soldier.core.DisplayBuilder;
import soldier.core.Unit;
import soldier.core.UnitGroup;
import soldier.VisualObjects.Loot;
import soldier.VisualObjects.Player;
import soldier.gameManagment.Position;
import soldier.gameManagment.Settings;

import java.util.Iterator;
import java.util.List;

public class JFXBuilder implements DisplayBuilder {
    @Override
    public void updateUnit(Unit u, Position p, int rank, Pane layer, ImageView imageView, int size,int count,int dir) {
        int offsetx = (count/10)*128;
        int offsety = dir*128;
        imageView.setViewport(new Rectangle2D(offsetx, offsety, 128, 128));

        if (size == 1) {
            imageView.relocate(p.getX() - Settings.UNIT_SIZE / 2 - Settings.UNIT_SPRITE_OFFSET, p.getY() - Settings.UNIT_SIZE / 2 - Settings.UNIT_SPRITE_OFFSET);
        }

        if (size == 2)
            imageView.relocate(p.getX()- Settings.UNIT_OFFSET + rank*Settings.UNIT_OFFSET- Settings.UNIT_SPRITE_OFFSET, p.getY()- Settings.UNIT_SIZE/2 - Settings.UNIT_SPRITE_OFFSET);

        if (size == 3){
            if (rank <2)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET + rank*Settings.UNIT_OFFSET - Settings.UNIT_SPRITE_OFFSET, p.getY()- Settings.UNIT_OFFSET - Settings.UNIT_SPRITE_OFFSET);

            if (rank == 2)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET/2 - Settings.UNIT_SPRITE_OFFSET, p.getY() - Settings.UNIT_SPRITE_OFFSET);
        }

        if (size == 4){
            if (rank < 2)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET + rank*Settings.UNIT_OFFSET - Settings.UNIT_SPRITE_OFFSET, p.getY()- Settings.UNIT_OFFSET - Settings.UNIT_SPRITE_OFFSET);
            if (rank > 1)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET + (rank-2)*Settings.UNIT_OFFSET - Settings.UNIT_SPRITE_OFFSET, p.getY() - Settings.UNIT_SPRITE_OFFSET);
        }
        if (size == 5){
            if (rank < 3)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET-Settings.UNIT_OFFSET/2 + rank*Settings.UNIT_OFFSET - Settings.UNIT_SPRITE_OFFSET, p.getY()- Settings.UNIT_OFFSET - Settings.UNIT_SPRITE_OFFSET);
            if (rank > 2)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET + (rank-3)*Settings.UNIT_OFFSET - Settings.UNIT_SPRITE_OFFSET, p.getY() - Settings.UNIT_SPRITE_OFFSET);
        }

        if (size == 6){
            if (rank < 3)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET-Settings.UNIT_OFFSET/2 + rank*Settings.UNIT_OFFSET - Settings.UNIT_SPRITE_OFFSET, p.getY()- Settings.UNIT_OFFSET - Settings.UNIT_SPRITE_OFFSET);
            if (rank > 2)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET -Settings.UNIT_OFFSET/2+ (rank-3)*Settings.UNIT_OFFSET - Settings.UNIT_SPRITE_OFFSET, p.getY() - Settings.UNIT_SPRITE_OFFSET);
        }

        if (size == 7){
            if (rank < 3)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET-Settings.UNIT_OFFSET/2 + rank*Settings.UNIT_OFFSET - Settings.UNIT_SPRITE_OFFSET, p.getY()- Settings.UNIT_OFFSET*1.5 - Settings.UNIT_SPRITE_OFFSET);
            if (6 > rank && rank > 2)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET -Settings.UNIT_OFFSET/2+ (rank-3)*Settings.UNIT_OFFSET - Settings.UNIT_SPRITE_OFFSET, p.getY()- Settings.UNIT_OFFSET/2 - Settings.UNIT_SPRITE_OFFSET);
            if (rank > 5)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET -Settings.UNIT_OFFSET/2+ (rank-5)*Settings.UNIT_OFFSET - Settings.UNIT_SPRITE_OFFSET, p.getY()+ Settings.UNIT_OFFSET/2 - Settings.UNIT_SPRITE_OFFSET);
        }

        if (size == 8){
            if (rank < 3)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET-Settings.UNIT_OFFSET/2 + rank*Settings.UNIT_OFFSET - Settings.UNIT_SPRITE_OFFSET, p.getY()- Settings.UNIT_OFFSET*1.5 - Settings.UNIT_SPRITE_OFFSET);
            if (6 > rank && rank > 2)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET -Settings.UNIT_OFFSET/2+ (rank-3)*Settings.UNIT_OFFSET - Settings.UNIT_SPRITE_OFFSET, p.getY()- Settings.UNIT_OFFSET/2 - Settings.UNIT_SPRITE_OFFSET);
            if (rank > 5)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET + (rank-6)*Settings.UNIT_OFFSET - Settings.UNIT_SPRITE_OFFSET, p.getY()+ Settings.UNIT_OFFSET/2 - Settings.UNIT_SPRITE_OFFSET);
        }
        if (size == 9){
            if (rank < 3)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET-Settings.UNIT_OFFSET/2 + rank*Settings.UNIT_OFFSET - Settings.UNIT_SPRITE_OFFSET, p.getY()- Settings.UNIT_OFFSET*1.5 - Settings.UNIT_SPRITE_OFFSET);
            if (6 > rank && rank > 2)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET -Settings.UNIT_OFFSET/2+ (rank-3)*Settings.UNIT_OFFSET - Settings.UNIT_SPRITE_OFFSET, p.getY()- Settings.UNIT_OFFSET/2 - Settings.UNIT_SPRITE_OFFSET);
            if (rank > 5)
                imageView.relocate(p.getX() - Settings.UNIT_OFFSET -Settings.UNIT_OFFSET/2 + (rank-6)*Settings.UNIT_OFFSET - Settings.UNIT_SPRITE_OFFSET, p.getY()+ Settings.UNIT_OFFSET/2 - Settings.UNIT_SPRITE_OFFSET);
        }
        if (size > 9){
            imageView.relocate(p.getX()+32*rank  - Settings.UNIT_SPRITE_OFFSET, p.getY()  - Settings.UNIT_SPRITE_OFFSET);
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
    public void updateUnitGroup(UnitGroup a, Position p, Pane layer, List<ImageView> imageViews, Circle hitbox, int size, int count, int dir) {
        hitbox.relocate(p.getX()-hitbox.getRadius(),p.getY()-hitbox.getRadius());

        int rank = 0;
        for (Iterator<Unit> it = a.subUnits(); it.hasNext(); ) {
            updateUnit(it.next(),p,rank,layer, imageViews.get(rank),size,count,dir);
            ++rank;
        }
    }

    @Override
    public void addUnitGroupToLayer(UnitGroup a, Pane layer, List<ImageView> imageViews, Circle hitbox) {
        layer.getChildren().add(hitbox);
        int rank = 0;
        for (Iterator<Unit> it = a.subUnits(); it.hasNext(); ) {
            it.next();
            layer.getChildren().add(imageViews.get(rank));
            ++rank;
        }
    }

    @Override
    public void removeUnitGroupFromLayer(UnitGroup a, Pane layer, List<ImageView> imageViews,Circle hitbox) {
        layer.getChildren().remove(hitbox);
        int rank = 0;
        for (Iterator<Unit> it = a.subUnits(); it.hasNext(); ) {
            it.next();
            layer.getChildren().remove(imageViews.get(rank));
            ++rank;
        }
    }

    @Override
    public void addEquipmentToLayer(Pane layer, ImageView imageView, Circle hitbox) {
        layer.getChildren().add(hitbox);
        layer.getChildren().add(imageView);
    }

    @Override
    public void removeEquipmentFromLayer(Pane layer, ImageView imageView, Circle hitbox) {
        layer.getChildren().remove(imageView);
        layer.getChildren().remove(hitbox);
    }

    @Override
    public void updateEquipment(Position p, Pane layer, ImageView imageView, Circle hitbox, int count, int dir) {
        hitbox.relocate(p.getX()-hitbox.getRadius(),p.getY()-hitbox.getRadius());
        int offsetx = (count/10)*128/2;
        int offsety = dir*128/2;
        imageView.setViewport(new Rectangle2D(offsetx, offsety, 64, 64));
        imageView.relocate(p.getX()  - Settings.EQUIPMENT_SPRITE_OFFSET - Settings.EQUIPMENT_SPRITE_OFFSET/2, p.getY() - Settings.EQUIPMENT_SPRITE_OFFSET - Settings.EQUIPMENT_SPRITE_OFFSET/2);

    }

    @Override
    public void updateUI(Pane playfieldLayer, Pane uiLayer, List<Player> players, List<Loot> loots, Player player1) {

        /** Display score **/
        uiLayer.getChildren().clear();
        Text scoreUI = new Text();
        scoreUI.setText("score: " + player1.getScore());
        scoreUI.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        scoreUI.setX(Settings.SCENE_WIDTH - 300);
        scoreUI.setY(100);
        scoreUI.setFill(Color.ORANGE);
        uiLayer.getChildren().add(scoreUI);

        /** Display costs **/
        for (Loot loot : loots) {
            updateCost(uiLayer,loot);
        }

        /** Display life **/
        for (Player p : players) {
            updatelife(uiLayer,p);
        }
    }

    private void updatelife(Pane uiLayer, Player p) {
        Text costUI = new Text();
        costUI.setText("" + Math.round(p.getArmy().getHealthPoints()));
        costUI.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        costUI.setX(p.getPosition().getX()-30);
        costUI.setY(p.getPosition().getY()-45);
        costUI.setFill(Color.PALEGREEN);
        uiLayer.getChildren().add(costUI);
    }

    public void updateCost(Pane uiLayer,Loot loot){
        Text costUI = new Text();
        costUI.setText("" + loot.getCost());
        costUI.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        costUI.setX(loot.getPosition().getX()-5);
        costUI.setY(loot.getPosition().getY()-45);
        costUI.setFill(Color.DARKGREEN);
        uiLayer.getChildren().add(costUI);
    }

}
