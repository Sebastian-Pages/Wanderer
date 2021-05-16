package soldier.gameManagment;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import soldier.core.DisplayBuilder;
import soldier.core.Unit;
import soldier.core.UnitGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player {
    private String name;
    private UnitGroup army;
    private int score;
    private Position position;
    private Position destination;
    private float speed= 2;
    private int size = 0;
    private Circle hitbox;
    private boolean isRemovable = false;
    private int count=0;
    private int dir = 0;



    private boolean isAlly;

    private Pane layer;
    private List<ImageView> imageViews;

    public Player(Pane layer, String name, int score, Position position, Position destination,boolean isAlly) {
        this.name = name;
        this.army = new UnitGroup(name);
        this.score = score;
        this.position = position;
        this.destination = destination;
        this.hitbox = new Circle(20);
        this.isAlly = isAlly;
        if(isAlly)
            hitbox.setFill(Color.PALEGREEN);
        else
            hitbox.setFill(Color.SALMON);
        this.layer = layer;
        this.imageViews = new ArrayList<>();

    }

    public void updateArmy(){
        int rank = 0;
        for (Iterator<Unit> it = this.getArmy().subUnits(); it.hasNext(); ) {
            Unit u = it.next();
            if(!u.alive()){
                this.remove(u,this.getImageViews().get(rank));
            }
        }
    }

    public void move(){
        if (position.getX() < destination.getX())
            position.setX(position.getX()+(int)speed);
        if (position.getX() > destination.getX())
            position.setX(position.getX()-(int)speed);
        if (position.getY() < destination.getY())
            position.setY(position.getY()+(int)speed);
        if (position.getY() > destination.getY())
            position.setY(position.getY()-(int)speed);
    }

    public void add(Unit u ,Image image) {
        add(u, new ImageView(image));
    }

    public void add(Unit u ,ImageView imageView){
        if (size<9) {
            this.army.addUnit(u);
            this.imageViews.add(imageView);
            this.hitbox.setRadius(hitbox.getRadius() + Settings.UNIT_SIZE / 4);
            this.size += 1;
        }
    }

    public void remove(Unit u,ImageView imageView){
        this.army.removeUnit(u);
        this.imageViews.remove(imageView);
        this.hitbox.setRadius(hitbox.getRadius()-Settings.UNIT_SIZE/2);
        this.size -= 1;
    }

    public void addToLayer(DisplayBuilder builder) {
        builder.addUnitGroupToLayer(army,layer,imageViews,hitbox);
        //this.layer.getChildren().add(this.imageView);
    }

    public void removeFromLayer(DisplayBuilder builder) {
        builder.removeUnitGroupFromLayer(army,layer,imageViews,hitbox);
        //this.layer.getChildren().remove(this.imageView);
    }

    public void updateUI(DisplayBuilder builder) {
        builder.updateUnitGroup(army,position, layer,imageViews,hitbox,size,count,getDir());

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

    public int getRadius(){
        return (int) this.hitbox.getRadius();
    }

    public List<ImageView> getImageViews() {
        return imageViews;
    }

    public int getSize() {
        return size;
    }

    public boolean isAlly() {
        return isAlly;
    }

    public boolean isRemovable() {
        return isRemovable;
    }

    public void setIsRemovable(boolean removable) {
        this.isRemovable = removable;
    }

    public int getDir(){
        if (position.getX() < destination.getX()){
            if(position.getY() <= destination.getY()){
                dir=0;
            }
            else{
                dir=2;
            }
        }
        else{
            if(position.getY() <= destination.getY()){
                dir=1;
            }
            else{
                dir=3;
            }
        }
        return dir;
    }

    public void count(){
        this.count+=1;
        if (count > 39)
            count =0;
    }
}
