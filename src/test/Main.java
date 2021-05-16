/**
 * D. Auber & P. Narbel
 * Solution TD Architecture Logicielle 2016 Universit� Bordeaux.
 */
package test;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import soldier.ages.AgeMiddleFactory;
import soldier.core.AgeAbstractFactory;
import soldier.core.DisplayBuilder;
import soldier.core.Unit;
import soldier.gameManagment.*;
import soldier.ui.JFXBuilder;
import javafx.scene.text.Font;

import javafx.animation.AnimationTimer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main extends Application {

	/**DECLARATION DES VARIABLES GLOBALES**/

	/** VARIABLES JFX **/
	private Scene scene;
	private AnimationTimer gameLoop;
	private Pane playfieldLayer;
	private Pane UILayer;
	private Group root;

	private Input input;

	/** GAME VARIABLES **/
	private List<Player> players = new ArrayList<>();
	private List<Loot> loots = new ArrayList<>();;
	private Player player1;
	private Map map;
	private DisplayBuilder builder;
	private boolean pauseState = false;


	/** IMAGES **/
	private Image centurionImage;
	private Image HorsemanImage;
	private Image RobotImage;
	private Image BikermanImage;
	private Image swordImage;
	private Image shieldImage;
	private Image potionImage;

	@Override
	public void start(Stage primaryStage) throws Exception{

		/** CREATION DE LA FENETRE ET DE L'ENVIRONEMENT JFX **/
		root = new Group();
		scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		playfieldLayer = new Pane();
		UILayer = new Pane();
		root.getChildren().add(playfieldLayer);
		root.getChildren().add(UILayer);

		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				player1.setDestination(new Position((int) event.getX(),(int) event.getY() ));
			}
		});

		/** INITIALIZING GAME **/
		loadGame();

		/** MAIN GAME LOOP **/
		gameLoop = new AnimationTimer(){

			@Override
			public void handle(long now) {
				processInput(input, now);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				//player1.displayPlayer(builder);
				//players.forEach(  player -> player.displayPlayer(builder));
				//players.forEach(  player -> player.setPosition(new Position(player.getPosition().getX()+1,player.getPosition().getY()+1)));

				/** PLAYER INPUT **/
				//player.processInput();

				/** PUPDATE ARMY COUNT **/
				//updateUnitsCount(true);

				/** MOVEMENTS **/
				players.forEach(player -> player.move());
				//enemies.forEach(sprite -> sprite.move());
				//missiles.forEach(sprite -> sprite.move());
				//units.forEach(sprite -> sprite.move());
				//osts.forEach(sprite -> sprite.move());

				/** AI **/
				//AI();

				/** CHECK COLLISIONS **/
				checkCollisions();

				//checkOrders();
				//checkSiege();
				//checkSieges();

				/** UPDATE SPRITES IN SCENE **/
				//players.forEach(sprite -> sprite.updateUI(builder));
				//player1.updateUI(builder);
				players.forEach(player -> player.updateUI(builder));
				loots.forEach(loot -> loot.updateUI(builder));

				players.forEach(player -> player.count());
				loots.forEach(loot -> loot.count());
				builder.updateUI(playfieldLayer,UILayer,players,loots,player1);
				//System.out.println("BOUCLE");
				//players.forEach(player -> System.out.println(player.getName()+" size: "+player.getSize()));
				//player.updateUI();
				//castles.forEach(sprite -> sprite.updateUI());
				//units.forEach(sprite -> sprite.updateUI());
				//osts.forEach(sprite -> sprite.updateUI());

				/** CHECK IF SPRITE CAN BE REMOVED **/
				//castles.forEach(sprite -> sprite.checkRemovability());
				//units.forEach(sprite -> sprite.checkRemovability());
				//osts.forEach(sprite -> sprite.checkRemovability());

				/** REMOVE REMOVABLES FROM LIST LAYER ETC **/
				removePlayers();
				removeLoots();
				//removeSprites(castles);
				//removeSprites(units);
				//removeSprites(osts);


				/** UPDATE SCORE HEALTH ETC**/
				//update();
				//castles.forEach(castle -> castle.update());
				//checkIfGameOver();
			}

			private void processInput(Input input, long now) {
				if (input.isExit()) {
					Platform.exit();
					//System.out.println("exited");
					System.exit(0);
				}
			}
		};
		gameLoop.start();
	}



	private void loadGame() {
		/* LOAD IMAGES */
		//humanWalkImage = new Image(getClass().getResource("/Human/Minifantasy_CreaturesHumanBaseWalk.png").toExternalForm(), Settings.UNIT_IMAGE_SIZE, Settings.UNIT_IMAGE_SIZE, true, true);
		RobotImage = new Image(getClass().getResource("/Human/Minifantasy_CreaturesHumanBaseWalk.png").toExternalForm(), Settings.UNIT_IMAGE_SIZE, Settings.UNIT_IMAGE_SIZE, true, true);
		centurionImage = new Image(getClass().getResource("/Human/Minifantasy_CreaturesHumanBaseWalk.png").toExternalForm(), Settings.UNIT_IMAGE_SIZE, Settings.UNIT_IMAGE_SIZE, true, true);
		HorsemanImage = new Image(getClass().getResource("/Orc/Minifantasy_CreaturesOrcBaseWalk.png").toExternalForm(), Settings.UNIT_IMAGE_SIZE, Settings.UNIT_IMAGE_SIZE, true, true);
		BikermanImage = new Image(getClass().getResource("/Orc/Minifantasy_CreaturesOrcRobotBaseWalk.png").toExternalForm(), Settings.UNIT_IMAGE_SIZE, Settings.UNIT_IMAGE_SIZE, true, true);

		swordImage =  new Image(getClass().getResource("/Equipment/sword.png").toExternalForm(), Settings.EQUIPMENT_IMAGE_SIZE, Settings.EQUIPMENT_IMAGE_SIZE, true, true);
		shieldImage = new Image(getClass().getResource("/Equipment/shield.png").toExternalForm(), Settings.EQUIPMENT_IMAGE_SIZE, Settings.EQUIPMENT_IMAGE_SIZE, true, true);
		potionImage = new Image(getClass().getResource("/Equipment/potion.png").toExternalForm(), Settings.EQUIPMENT_IMAGE_SIZE, Settings.EQUIPMENT_IMAGE_SIZE, true, true);


		/* INITIALIZING GAME */
		input = new Input(scene);
		input.addListeners();
		builder = new JFXBuilder();
		map = new Map(Settings.SCENE_WIDTH,Settings.SCENE_HEIGHT);

		/*MAKE PLAYER */
		AgeAbstractFactory age1 = new AgeMiddleFactory();
		//UnitGroup team1 =  new UnitGroup("TheArmy");
		//team1.addUnit(age1.infantryUnit("human"));
		//team1.addUnit(age1.infantryUnit("orc"));
		//player1.addImageView(new ImageView(humanImage));
		//player1.addImageView(new ImageView(orcImage));
		//player1.addToLayer(builder);

		player1 = new Player(playfieldLayer,"Patrick",5,new Position(100,100),new Position(100,100),true);
		player1.add(age1.infantryUnit("human"), centurionImage);
		player1.add(age1.infantryUnit("human"), centurionImage);
		player1.add(age1.infantryUnit("orc"), HorsemanImage);
		players.add(player1);
		player1.addToLayer(builder);

		/*Player player2 = new Player(playfieldLayer,"1orc",0,new Position(500,500),new Position(500,500),false);
		player2.add(age1.infantryUnit("orc"), HorsemanImage);
		player2.add(age1.infantryUnit("orc"), HorsemanImage);
		players.add(player2);
		player2.addToLayer(builder);

		Player player3 = new Player(playfieldLayer,"4humans",0,new Position(500,700),new Position(500,700),false);
		player3.add(age1.infantryUnit("human"), centurionImage);
		player3.add(age1.infantryUnit("human"), centurionImage);
		player3.add(age1.infantryUnit("human"), centurionImage);
		player3.add(age1.infantryUnit("human"), centurionImage);
		player3.add(age1.infantryUnit("human"), centurionImage);
		player3.add(age1.infantryUnit("human"), centurionImage);
		players.add(player3);
		player3.addToLayer(builder);*/

		Loot loot1 = new Loot(playfieldLayer,shieldImage,swordImage,potionImage);
		loot1.addToLayer(builder);
		loots.add(loot1);
		Loot loot2 = new Loot(playfieldLayer,shieldImage,swordImage,potionImage);
		loot2.addToLayer(builder);
		loots.add(loot2);

		Player player2 = new Player(playfieldLayer,BikermanImage, centurionImage,HorsemanImage,RobotImage);
		players.add(player2);
		player2.addToLayer(builder);

		Player player3 = new Player(playfieldLayer,BikermanImage, centurionImage,HorsemanImage,RobotImage);
		players.add(player3);
		player3.addToLayer(builder);

		Player player4 = new Player(playfieldLayer,BikermanImage, centurionImage,HorsemanImage,RobotImage);
		players.add(player4);
		player4.addToLayer(builder);



		scene.setOnKeyTyped(ke ->{
			if(input.isPaused()) {
				if(pauseState) {
					gameLoop.start();
					pauseState=false;
				}
				else {
					gameLoop.stop();
					pauseState=true;
				}
			}
		});
	}

	private void checkCollisions() {
		players.forEach(player -> checkCollisionPlayers(player));
		loots.forEach(loot -> checkCollisionLoot(loot));
	}

	private void checkCollisionLoot(Loot loot) {
		if (loot.getPosition().distance(player1.getPosition())<loot.getRadius()+ player1.getRadius())
			if(player1.getScore() - loot.getCost() >= 0){
				pickUpLoot(loot);
			}
	}

	private void pickUpLoot(Loot loot) {
		if (loot.getEquipment()==null)
			player1.heal();
		else {
			player1.addEquipment(loot);
			player1.setScore(player1.getScore() - loot.getCost());
		}
		loot.setIsRemovable(true);
	}

	private void checkCollisionPlayers(Player player) {
		if (player.equals(player1))
			return;
		else
			if (player.getPosition().distance(player1.getPosition())<player.getRadius()+ player1.getRadius())
				collide(player);
	}

	private void collide(Player player) {
		System.out.println("collision "+players.toString());
		if (player.isAlly())
			merge(player);
		else
			fight(player);
	}

	private void fight(Player player) {
		int round = 0;
		Unit team1 = player1.getArmy();
		Unit team2 = player.getArmy();
		while(team1.alive() && team2.alive()) {
			System.out.println("Round  #" + round++);
			float st1 = team1.strike();
			System.out.println(team1.getName() + " attack with force : " + st1);
			team2.parry(st1);
			float st2 = team2.strike();
			System.out.println(team2.getName() + " attack with force : " + st2);
			team1.parry(st2);
		}
		System.out.println("The end ... " + (team1.alive() ? team1.getName() : team2.getName()) + " won." );

		if(team1.alive()){
			player.removeFromLayer(builder);
			player.setIsRemovable(true);
			player1.updateArmy();
			player1.setScore(player1.getScore()+player.getSize());
		}else{
			player1.removeFromLayer(builder);
			player1.setIsRemovable(true);
			player.updateArmy();
			gameOver();
			//Ajout Image game over
		}
	}

	private void merge(Player player) {
		int rank = 0;
		player1.removeFromLayer(builder);
		player.removeFromLayer(builder);
		for (Iterator<Unit> it = player.getArmy().subUnits(); it.hasNext(); ) {
			Unit u = it.next();
			player1.add(u,player.getImageViews().get(rank));
			player.remove(u,player.getImageViews().get(rank));
			//players.remove(player);
			//player.removeFromLayer(builder);
		}
		player1.addToLayer(builder);
		player.setIsRemovable(true);
	}

	private void removePlayers() {
		Iterator<Player> iter = players.iterator();
		while (iter.hasNext()) {
			Player p = iter.next();

			if (p.isRemovable()) {
				// remove from layer
				// p.removeFromLayer();
				// remove from list
				System.out.println(p.toString()+" removed");
				iter.remove();
			}
		}
	}

	private void removeLoots() {
		Iterator<Loot> iter = loots.iterator();
		while (iter.hasNext()) {
			Loot loot = iter.next();

			if (loot.isRemovable()) {
				loot.removeFromLayer(builder);
				iter.remove();
			}
		}
	}

	private void gameOver(){
		HBox hbox = new HBox();
		hbox.setPrefSize(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
		//hbox.getStyleClass().add("message");
		Text message = new Text();
		message.getStyleClass().add("message");
		message.setText("Game over");
		message.setFont(Font.font ("Impact", FontWeight.BOLD, 200));
		message.setFill(Color.ORANGE);
		hbox.getChildren().add(message);
		root.getChildren().add(hbox);
		gameLoop.stop();
	}


}
