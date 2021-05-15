/**
 * D. Auber & P. Narbel
 * Solution TD Architecture Logicielle 2016 Universit� Bordeaux.
 */
package test;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import soldier.ages.AgeFutureFactory;
import soldier.ages.AgeMiddleFactory;
import soldier.core.AgeAbstractFactory;
import soldier.core.DisplayBuilder;
import soldier.core.UnitGroup;
import soldier.gameManagment.*;
import soldier.ui.CLBuilder;
import soldier.ui.JFXBuilder;

import javafx.animation.AnimationTimer;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

	/**DECLARATION DES VARIABLES GLOBALES**/

	/** VARIABLES JFX **/
	private Scene scene;
	private AnimationTimer gameLoop;
	private Pane playfieldLayer;
	private Group root;

	private Input input;

	/** GAME VARIABLES **/
	private List<Player> players = new ArrayList<>();;
	private Player player1;
	private Map map;
	private DisplayBuilder builder;
	private boolean pauseState = false;


	/** IMAGES **/
	private Image humanImage;
	private Image orcImage;

	@Override
	public void start(Stage primaryStage) throws Exception{

		/** CREATION DE LA FENETRE ET DE L'ENVIRONEMENT JFX **/
		root = new Group();
		scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		playfieldLayer = new Pane();
		root.getChildren().add(playfieldLayer);

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
				System.out.println("boucle");
				try {
					Thread.sleep(50);
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
				//checkCollisions();
				//checkOrders();
				//checkSiege();
				//checkSieges();

				/** UPDATE SPRITES IN SCENE **/
				//players.forEach(sprite -> sprite.updateUI(builder));
				player1.updateUI(builder);
				//player.updateUI();
				//castles.forEach(sprite -> sprite.updateUI());
				//units.forEach(sprite -> sprite.updateUI());
				//osts.forEach(sprite -> sprite.updateUI());

				/** CHECK IF SPRITE CAN BE REMOVED **/
				//castles.forEach(sprite -> sprite.checkRemovability());
				//units.forEach(sprite -> sprite.checkRemovability());
				//osts.forEach(sprite -> sprite.checkRemovability());

				/** REMOVE REMOVABLES FROM LIST LAYER ETC **/
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
		humanImage = new Image(getClass().getResource("/Human/Minifantasy_CreaturesHumanBaseFixe.png").toExternalForm(), Settings.UNIT_SIZE, Settings.UNIT_SIZE, true, true);
		orcImage = new Image(getClass().getResource("/Orc/Minifantasy_CreaturesOrcBaseFixe.png").toExternalForm(), Settings.UNIT_SIZE, Settings.UNIT_SIZE, true, true);

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

		player1 = new Player(playfieldLayer,"Patrick",0,new Position(100,100),new Position(100,100));
		player1.add(age1.infantryUnit("human"),humanImage);
		player1.add(age1.infantryUnit("orc"),orcImage);
		players.add(player1);
		player1.addToLayer(builder);



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


}
