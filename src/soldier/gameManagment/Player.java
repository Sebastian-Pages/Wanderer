package soldier.gameManagment;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import soldier.core.DisplayBuilder;
import soldier.core.Unit;
import soldier.core.UnitGroup;
import soldier.ui.JFXBuilder;
import soldier.visitor.GetterVisitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player {
    private String name;
    private UnitGroup army;
    private int score;
    private Position position;
    private Position destination;

    private Pane layer;
    private List<ImageView> imageViews;

    public Player(Pane layer, String name, UnitGroup army, int score, Position position, Position destination) {
        this.name = name;
        this.army = army;
        this.score = score;
        this.position = position;
        this.destination = destination;

        this.layer = layer;
        //this.imageView = new ImageView(image);
        this.imageViews = new ArrayList<>();
        //this.imageView.relocate(position.x, position.y);

    }
    /*
    public void displayPlayer(DisplayBuilder builder){
        //GetterVisitor gv = new GetterVisitor();
        int rank = 0;
        builder.displayUnitGroup(army, position);
        for (Iterator<Unit> it = this.army.subUnits(); it.hasNext(); ) {
            //builder.displayIdleUnit(it.next(),position,rank);
            ++rank;
        }
    }*/

    public void addToLayer(DisplayBuilder builder) {
        builder.addUnitGroupToLayer(army,layer,imageViews);
        //this.layer.getChildren().add(this.imageView);
    }

    public void removeFromLayer(DisplayBuilder builder) {
        builder.removeUnitGroupFromLayer(army,layer,imageViews);
        //this.layer.getChildren().remove(this.imageView);
    }

    public void updateUI(DisplayBuilder builder) {
        builder.updateUnitGroup(army,position, layer,imageViews);
        //imageView.relocate(position.x, position.y);
    }

    public void removeImageView(ImageView imageView) {
        this.imageViews.remove(imageView);
    }

    public void addImageView(ImageView imageView) {
        this.imageViews.add(imageView);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UnitGroup getArmy() {
        return army;
    }

    public void setArmy(UnitGroup army) {
        this.army = army;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getDestination() {
        return destination;
    }

    public void setDestination(Position destination) {
        this.destination = destination;
    }
}
