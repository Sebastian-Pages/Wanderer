package soldier.gameManagment;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import soldier.ages.AgeFutureFactory;
import soldier.ages.AgeMiddleFactory;
import soldier.core.AgeAbstractFactory;
import soldier.core.DisplayBuilder;
import soldier.core.Equipment;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Loot {
    private int cost;
    private String name;
    private Position position;
    private Circle hitbox;
    private boolean isRemovable = false;
    private int count=0;
    private int dir = 0;
    private Equipment equipment;

    private Pane layer;
    private ImageView imageView;

    public Loot(Pane layer, int cost, String name, Position position, int count, int dir,ImageView imageView, Equipment equipment) {
        this.cost = cost;
        this.name = name;
        this.position = position;
        this.hitbox = this.hitbox = new Circle(40);
        this.hitbox.setFill(Color.BEIGE);
        this.isRemovable = false;
        this.count = count;
        this.dir = dir;
        this.imageView = imageView;
        this.equipment = equipment;
    }

    public  Loot(Pane layer, Image shieldImage, Image swordImage){
        AgeAbstractFactory fact;
        this.name = "Random Equipment";
        this.layer=layer;
        int rand = ThreadLocalRandom.current().nextInt(0, 1 + 1);
        int x = ThreadLocalRandom.current().nextInt(0, Settings.SCENE_WIDTH + 1);
        int y= ThreadLocalRandom.current().nextInt(0, Settings.SCENE_HEIGHT + 1);
        this.cost = ThreadLocalRandom.current().nextInt(1, 5 + 1);
        this.count = ThreadLocalRandom.current().nextInt(0, 3 + 1);
        this.dir = ThreadLocalRandom.current().nextInt(0, 3 + 1);
        if ( (count & 1) == 0 )
            fact = new AgeMiddleFactory();
        else
            fact = new AgeFutureFactory();

        if ( (rand & 1) == 0 ){
            this.equipment=fact.attackWeapon();
            this.imageView = new ImageView(swordImage);
        }
        else{
            this.equipment=fact.defenseWeapon();
            this.imageView = new ImageView(shieldImage);
        }
        this.hitbox = new Circle(40);
        this.hitbox.setFill(Color.BEIGE);
        this.isRemovable = false;
        this.position = new Position(x,y);
    }

    public void addToLayer(DisplayBuilder builder) {
        builder.addEquipmentToLayer(layer,imageView,hitbox);
        //this.layer.getChildren().add(this.imageView);
    }

    public void removeFromLayer(DisplayBuilder builder) {
        builder.removeEquipmentFromLayer(layer,imageView,hitbox);
        //this.layer.getChildren().remove(this.imageView);
    }

    public void updateUI(DisplayBuilder builder) {
        builder.updateEquipment(position, layer,imageView,hitbox,count,dir);

        //imageView.relocate(position.x, position.y);
    }

    public Position getPosition() {
        return position;
    }

    public int getRadius(){
        return (int) this.hitbox.getRadius();
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public int getCost() {
        return cost;
    }

    public boolean isRemovable() {
        return isRemovable;
    }

    public void setIsRemovable(boolean removable) {
        this.isRemovable = removable;
    }

    public void count(){
        this.count+=1;
        if (count > 39)
            count =0;
    }
}
