
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

/**
 * BlokusGame class
 *
 */
public class Game extends Scene {

    private final Blokus blokusApp;
    private Piece activePiece;
    private final Board board;
    private final Board inventory;
    private final Player[] players;
    private int piece = (int) (Math.random() * 21);
    private final Color[] Colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};
    private int turn = 0;
    private Label statusLabel;

    /**
     * Initialize the game. Selects a random shape to act as the current piece.
     *
     *  @param blokusApp A reference to the application (use to set messages).
     *  @param root The root group that the game belongs to
     */
    public Game(Group root, Blokus blokusApp) {
        super(root);

        this.board = new Board();
        this.inventory = new Board(0.5);
        this.blokusApp = blokusApp;
        this.players = new Player[4];

        statusLabel = new Label("");
        statusLabel.setTextFill(Color.BLACK);



        BorderPane layout = new BorderPane();
        layout.setCenter(board);
        layout.setTop(statusLabel);
        layout.setRight(inventory);

        root.getChildren().add(layout);

        //set up event handling
        setUpKeyPresses();
        setUpMouseEvents();

        //Initializes the game with the piece.
        activePiece = new Piece(board, inventory, Colors[turn], Board.DIM_SQUARES / 2, 2, piece);

        this.setMessage("      BLOKUS     |  P1       0  |  P2       0  |  P3       0  |  P4       0  |  ");
        
        int i;
        for (i = 0; i < players.length; i++) {
        	Player player;
        	player = new Player();
        	players[i] = player;
        }
        
        this.populateInventory(players[0]);
    }

    /**
     * Anything that happens automatically can go here.
     */
    void update() {
    }

    /**
     * Rotate the current piece counter-clockwise.
     */
    void rotateLeft() {
        this.activePiece.rotate(0);
        System.out.println("rotate left key was pressed!");
    }

    /**
     * Rotate the current piece clockwise.
     */
    void rotateRight() {
        this.activePiece.rotate(1);
        System.out.println("rotate right key was pressed!");
    }

    /**
     * Mirror the current piece.
     */
    void mirror() {
        this.activePiece.rotate(2);
        System.out.println("mirror key was pressed!");
    }

    /**
     * Handle the mouse clicked event.
     */
    void click(double x, double y) {
    }

    void hover(double x, double y) {
        this.activePiece.move((int) x / Board.BLOCK_SIZE, (int) (y - 20) / Board.BLOCK_SIZE);
    }

    /**
     * Place the piece onto the board.
     */
    void placePiece() {
        if (turn < 4 && this.activePiece.firstAddPieceToBoard()) {
            turn++;
            piece = (int) (Math.random() * 21);
            activePiece = new Piece(board, inventory, Colors[turn % 4], Board.DIM_SQUARES / 2, 2, piece);

        } else if (this.activePiece.addPieceToBoard()) {
            turn++;
            piece = (int) (Math.random() * 21);
            this.activePiece = new Piece(board, inventory, Colors[turn % 4], Board.DIM_SQUARES / 2, 2, piece);
        }
    }

    void checkForMove() {
        if (this.activePiece.availableMove()) {
            System.out.println("Move is available");
        } else {
            System.out.println("No move available");
        }
    }
    
    // fills out inventory pane for player
    void populateInventory(Player player){
    	// update turn indicator
    	
    	// update player name and score
    	
    	// Add remaining pieces
    	int i;
    	for (i = 0; i < player.inventory.length; i++) {
    		if (player.inventory[i] == true) {
    			
    			Piece piece;
    			piece = new Piece(board, inventory, Colors[turn % 4], Board.DIM_SQUARES / 2, 2, i);
    			piece.addPieceToInventory();
    		}
    	}
    	
    }

    /**
     * Sets up key events for the arrow keys and space bar. All keys send
     * messages to the game, which should react appropriately.
     */
    private void setUpKeyPresses() {
        board.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                    this.rotateLeft();
                    break;
                case RIGHT:
                    this.rotateRight();
                    break;
                case DOWN:
                    break;
                case UP:
                    this.mirror();
                    break;
                case M:
                    this.mirror();
                    break;
                case SPACE:
                    this.checkForMove();
                    break;

            }
        });
        board.requestFocus(); // board is focused to receive key input

    }

    /**
     * Handles the mouse click.
     */
    private void setUpMouseEvents() {
        board.setOnMousePressed(e -> {
            this.placePiece();
        });
        board.setOnMouseMoved(e -> {
            this.hover(e.getSceneX(), e.getSceneY());
        });
        //inventory.setOnMousePressed(e -> {
        //    this.selectPiece();
        //});
        inventory.setOnMouseMoved(e -> {
            this.hover(e.getSceneX(), e.getSceneY());
        });

    }

    /**
     * Changes the message in the status label at the top of the screen.
     *
     * @param message
     */
    public void setMessage(String message) {
        statusLabel.setText(message);
    }
}
