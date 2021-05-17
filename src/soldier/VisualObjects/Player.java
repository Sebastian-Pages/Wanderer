package soldier.VisualObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import soldier.ages.AgeFutureFactory;
import soldier.ages.AgeMiddleFactory;
import soldier.core.AgeAbstractFactory;
import soldier.ui.DisplayBuilder;
import soldier.core.Unit;
import soldier.core.UnitGroup;
import soldier.gameManagment.Position;
import soldier.gameManagment.Settings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Player {
    private String name;
    private UnitGroup army;
    private int score;
    private Position position;
    private Position destination;
    private float speed= Settings.SPEED_PLAYER;
    private int size = 0;
    protected Circle hitbox;
    private boolean isRemovable = false;
    protected int count=0;
    protected int dir = 0;
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
            hitbox.setFill(Color.rgb(152,251,152,0.4));
        else
            hitbox.setFill(Color.rgb(250,128,114,0.4));
        this.layer = layer;
        this.imageViews = new ArrayList<>();

    }


    public Player(Pane layer,Image BikermanImage,Image CenturionImage,Image HorsemanImage,Image RobotImage) {
        int rand = ThreadLocalRandom.current().nextInt(0, 2 + 1);
        int x = ThreadLocalRandom.current().nextInt(Settings.SCENE_PADDING_X, Settings.SCENE_WIDTH - Settings.SCENE_PADDING_X);
        int y= ThreadLocalRandom.current().nextInt(Settings.SCENE_PADDING_Y, Settings.SCENE_HEIGHT- Settings.SCENE_PADDING_Y);
        this.name = "Random Player";
        this.score = 10;
        this.layer=layer;
        this.count = ThreadLocalRandom.current().nextInt(0, 3 + 1);
        this.dir = ThreadLocalRandom.current().nextInt(0, 3 + 1);
        this.hitbox = new Circle(40);
        if ( rand == 0 ){
            this.isAlly = true;
        }
        else{
            this.isAlly = false;
        }
        if(isAlly)
            hitbox.setFill(Color.rgb(152,251,152,0.4));
        else
            hitbox.setFill(Color.rgb(250,128,114,0.4));
        this.isRemovable = false;
        this.position = new Position(x,y);
        this.destination = this.position;
        this.imageViews = new ArrayList<>();
        this.army = new UnitGroup("randomArmy");
        this.randomAdd(BikermanImage,CenturionImage,HorsemanImage,RobotImage);
    }

    public void randomAdd(Image BikermanImage,Image CenturionImage,Image HorsemanImage,Image RobotImage){
        int randNbSoldier = ThreadLocalRandom.current().nextInt(2, 5 + 1);
        for(int i=0;i<randNbSoldier;++i){
            AgeAbstractFactory fact1 = new AgeMiddleFactory();
            AgeAbstractFactory fact2 = new AgeFutureFactory();

            int randSoldier = ThreadLocalRandom.current().nextInt(0, 3 + 1);

            if (randSoldier  == 0 ){
                this.add(fact2.riderUnit("rider"),BikermanImage);
            } else if(randSoldier == 1 ){
                this.add(fact1.infantryUnit("centurion"),CenturionImage);
            } else if(randSoldier == 2 ){
                this.add(fact1.riderUnit("horseman"),HorsemanImage);
            }else /*if (randSoldier == 3 )*/{
                this.add(fact2.infantryUnit("Robot"),RobotImage);
            }

        }
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
        int delta_x = position.getX() - destination.getX();
        int delta_y = position.getY() - destination.getY();
        double theta_radians = Math.toDegrees(Math.atan2(delta_y, delta_x));

        double speedX = Math.abs(Math.cos(Math.toRadians(theta_radians)) * speed);
        double speedY = Math.abs(Math.sin(Math.toRadians(theta_radians)) * speed);

        if (position.getX() < destination.getX())
            position.setX(position.getX()+(int)speedX);
        if (position.getX() > destination.getX())
            position.setX(position.getX()-(int)speedX);
        if (position.getY() < destination.getY())
            position.setY(position.getY()+(int)speedY);
        if (position.getY() > destination.getY())
            position.setY(position.getY()-(int)speedY);
    }

    public void add(Unit u ,Image image) {
        add(u, new ImageView(image));
    }

    public void add(Unit u ,ImageView imageView){
        if ((size<9)&(score > 0)) {
            this.score-=1;
            this.army.addUnit(u);
            this.imageViews.add(imageView);
            this.hitbox.setRadius(hitbox.getRadius() + Settings.UNIT_SIZE /3);
            this.size += 1;
        }
    }

    public void remove(Unit u,ImageView imageView){
        this.army.removeUnit(u);
        this.imageViews.remove(imageView);
        this.hitbox.setRadius(hitbox.getRadius()-Settings.UNIT_SIZE/3);
        this.size -= 1;
    }

    public void addToLayer(DisplayBuilder builder) {
        builder.addUnitGroupToLayer(army,layer,imageViews,hitbox);
    }

    public void removeFromLayer(DisplayBuilder builder) {
        builder.removeUnitGroupFromLayer(army,layer,imageViews,hitbox);
    }

    public void updateUI(DisplayBuilder builder) {
        builder.updateUnitGroup(army,position, layer,imageViews,hitbox,size,count,getDir());
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

    public void addEquipment(Loot loot) {
        army.addEquipment(loot.getEquipment());
    }

    public void heal() {
        army.heal();
    }

    public void setDir(int i){
        this.dir = i;
    }
    public void resetCount(){
        this.count = 0;
    }
}
