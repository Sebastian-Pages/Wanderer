/**
 * D. Auber & P. Narbel
 * Solution TD Architecture Logicielle 2016 Universit� Bordeaux.
 */
package test;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	private List<Player> players;
	private Player player1;
	private Map map;
	DisplayBuilder builder;
	/** IMAGES **/

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

		//Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
		//primaryStage.setTitle("Hello World");
		//primaryStage.setScene(new Scene(root, 300, 275));
		//primaryStage.show();

		/** INITIALIZING GAME **/
		loadGame();

		/** MAIN GAME LOOP **/
		gameLoop = new AnimationTimer(){

			@Override
			public void handle(long now) {
				processInput(input, now);
				System.out.println("boucle");
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				player1.displayPlayer(builder);

				/** PLAYER INPUT **/
				//player.processInput();

				/** PUPDATE ARMY COUNT **/
				//updateUnitsCount(true);

				/** MOVEMENTS **/
				//player.move();
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
		System.out.println("laoding game");

		/* INITIALIZING GAME */
		input = new Input(scene);
		builder = new CLBuilder();
		map = new Map(Settings.SCENE_WIDTH,Settings.SCENE_HEIGHT);

		AgeAbstractFactory age1 = new AgeMiddleFactory();
		UnitGroup team1 =  new UnitGroup("Mammals");
		team1.addUnit(age1.infantryUnit("mouse"));
		team1.addUnit(age1.infantryUnit("cat"));
		player1 = new Player("Patrick",team1,0,new Position(0,0),new Position(0,0));

	}


}
