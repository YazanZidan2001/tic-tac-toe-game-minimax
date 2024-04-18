//package com.example.algo_pro4;
//
//import java.util.Optional;
//import java.util.Random;
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.ButtonType;
//import javafx.scene.control.Label;
//import javafx.scene.control.RadioButton;
//import javafx.scene.control.Spinner;
//import javafx.scene.control.SpinnerValueFactory;
//import javafx.scene.control.TextField;
//import javafx.scene.control.ToggleGroup;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.effect.BlurType;
//import javafx.scene.effect.DropShadow;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.stage.Stage;
//
//public class Test extends Application {
//	private char[][] field = { { ' ', ' ', ' ' }, { ' ', ' ', ' ', }, { ' ', ' ', ' ' } }; // game field
//	private Button[][] buttonsArray = new Button[3][3]; // for easy acces to the game squares
//	private Stage stage;// main stage for this program
//	private Random random = new Random();// for easy mode
//	private boolean isPlayerOneTurn = true;// this for trans between human turn and ai turn
//	private char player1, player2; // player 1 for human and player 2 for ( ai or human )
//	private int totalGames;// to keep tracking of game rounds (Counter)
//	private int playerOneWins, playerTwoWins; // to keep tracking of game to know who wins
//	private int mode = -1; // 0 for 2 players , 1 for random , 2 for minMax
//	private TextField firstName, secondName;// get the name of the first and the seconde player
//	private int totalRounds; // save the number of rounds chosed by the player
//	private Label orderLabel, Rounds, SecondPlayer, playerTwoWinsLapel, playerOneWinsLapel;
//	private Spinner<Integer> spinner;
//	private RadioButton XSymbolButton;
//	private Button[][] hints = new Button[3][3];
//
//	public static void main(String[] args) {
//		launch(args);
//	}
//
//	@Override
//	public void start(Stage stage) throws Exception {
//		this.stage = stage;
//		stage.setTitle("Tic Tac Toe Game");
//		Scene scene = new Scene(getMainPage(), 600, 600);
//		stage.setResizable(true);
//		stage.setScene(scene);
//		stage.show();
//	}
//
//	// Gui pages methods
//
//	// for Easy mode move
//	// ****************************************************************************
//	// ************************************************************************
//	// ************************************************************************
//	// ************************************************************************
//	// ************************************************************************
//
//	private void randomMove() {
//		for (int i = 0; i < 1000; i++) {
//			int row = random.nextInt(3);
//			int col = random.nextInt(3);
//
//			if (field[row][col] == ' ') {
//				makeRandomMove(row, col);
//				return;
//			}
//		}
//
//		for (int i = 0; i < 3; i++) {
//			for (int j = 0; j < 3; j++) {
//				if (field[i][j] == ' ') {
//					makeRandomMove(j, i);
//					return;
//				}
//			}
//		}
//	}
//
//	private void makeRandomMove(int row, int col) {
//		field[row][col] = player2;
//		buttonsArray[row][col].setText(player2 + "");
//
//		if (player2 == 'X') {
//			buttonsArray[row][col].setTextFill(Color.RED);
//		} else {
//			buttonsArray[row][col].setTextFill(Color.GREEN);
//		}
//
//		isPlayerOneTurn = true;
//	}
//
//	// MinMax Algorthim and AI moves
//	// ****************************************************************************
//	// ****************************************************************************
//	// ****************************************************************************
//	// ****************************************************************************
//	// ****************************************************************************
//	// ****************************************************************************
//
//	private void AiMove() {
//
//		int[] aiMove = minMax(player2);
//		System.out.println(aiMove[0] + "," + aiMove[1]);
//
//		new Thread(() -> {
//			try {
//				Thread.sleep(1500);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			Platform.runLater(() -> {
//				for (int i = 0; i < 3; i++) {
//					for (int j = 0; j < 3; j++) {
//						if (field[i][j] == ' ') {
//////							hints[i][j].setText("");
//							buttonsArray[i][j].setText("");
//						} else if (field[i][j] == 'X' || field[i][j] == 'O')
//							hints[i][j].setText("---");
//						hints[i][j].setTextFill(Color.RED);
//
//					}
//				}
//			});
//		}).start();
//
//		hints[aiMove[0]][aiMove[1]].setTextFill(Color.CYAN);
//
//		new Thread(() -> {
//
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			Platform.runLater(() -> {
//
//				Button buttonGame = buttonsArray[aiMove[0]][aiMove[1]];
//				if (buttonGame != null) {
//					buttonGame.setText(player2 + "");
//					if (player2 == 'X')
//						buttonGame.setTextFill(Color.RED);
//					else
//						buttonGame.setTextFill(Color.GREEN);
//
//				}
//
//				field[aiMove[0]][aiMove[1]] = player2;
//
//				if (checkWin(field, player2)) {
//					showResult("AI wins!");
//					playerTwoWins++;
//					totalGames++;
//					updateScore();
//					resetGame();
//				} else if (checkDraw(field)) {
//					showResult("It's a draw!");
//					totalGames++;
//					updateScore();
//					resetGame();
//
//				}
//
//				isPlayerOneTurn = true;
//			});
//		}).start();
//
//	}
//
//	// *********************************************************************************
//
//	private int[] minMax(char player) {
//
//		int[] bestMove = new int[] { -1, -1 };
//		int bestScore = -1;
//
//		for (int i = 0; i < 3; i++) {
//			for (int j = 0; j < 3; j++) {
//				if (field[i][j] == ' ') {
//					field[i][j] = player;
//
//					int score = Move(false);
//
//					if (score == 1) {
//						hints[i][j].setText("Win");
////						buttonsArray[i][j].setText("W");
//					} else if (score == 0) {
//						hints[i][j].setText("Draw");
////						buttonsArray[i][j].setText("D");
//					} else {
//						hints[i][j].setText("Lose");
////						buttonsArray[i][j].setText("L");
//					}
//
//					field[i][j] = ' ';
//
//					if (score > bestScore) {
//						bestScore = score;
//						bestMove[0] = i;
//						bestMove[1] = j;
//					}
//				}
//			}
//		}
//
//		return bestMove;
//
//	}
//
//	// ************************************************************
//	private int Move(boolean isMaximizing) {
//		if (checkWin(field, player1))
//			return -1;
//		if (checkWin(field, player2))
//			return 1;
//		if (checkDraw(field))
//			return 0;
//
//		if (isMaximizing) {
//			int maxScore = -1;
//			for (int i = 0; i < 3; i++) {
//				for (int j = 0; j < 3; j++) {
//					if (field[i][j] == ' ') {
//						field[i][j] = player2;
//						maxScore = Math.max(maxScore, Move(false));
//						field[i][j] = ' ';
//					}
//				}
//			}
//			return maxScore;
//		} else {
//			int minScore = 1;
//			for (int i = 0; i < 3; i++) {
//				for (int j = 0; j < 3; j++) {
//					if (field[i][j] == ' ') {
//						field[i][j] = player1;
//						minScore = Math.min(minScore, Move(true));
//						field[i][j] = ' ';
//					}
//				}
//			}
//			return minScore;
//		}
//	}
//
//	// for handling user turn
//	// ***********************************************************************************
//	// ***********************************************************************************
//	// ***********************************************************************************
//	// ***********************************************************************************
//	// ***********************************************************************************
//	// ***********************************************************************************
//	// ***********************************************************************************
//
//	private void handleButtonClick(int row, int col) {
//		Button button = buttonsArray[row][col];
//
//		if (button.getText().isBlank() && field[row][col] == ' ') {
//			char currentPlayer = isPlayerOneTurn ? player1 : player2;
//
//			button.setText(String.valueOf(currentPlayer));
//			button.setTextFill(currentPlayer == 'X' ? Color.RED : Color.GREEN);
//
//			field[row][col] = currentPlayer;
//
//			if (checkWin(field, currentPlayer)) {
//				showResult((isPlayerOneTurn ? firstName : secondName).getText().trim() + " wins!");
//				playerOneWins++;
//				totalGames++;
//				updateScore();
//				resetGame();
//				return;
//			}
//
//			// Check for a draw
//			if (checkDraw(field)) {
//				showResult("It's a draw!");
//				totalGames++;
//				updateScore();
//				resetGame();
//				return;
//			}
//
//			isPlayerOneTurn = !isPlayerOneTurn;
//
//			if (!isPlayerOneTurn && mode == 2)
//				AiMove();
//
//			// ********************************************************
//			else if (!isPlayerOneTurn && mode == 1) {
//				randomMove();
//				if (checkWin(field, player2)) {
//					showResult("AI wins!");
//					playerTwoWins++;
//					totalGames++;
//					updateScore();
//					resetGame();
//				} else if (checkDraw(field)) {
//					showResult("It's a draw!");
//					totalGames++;
//					updateScore();
//					resetGame();
//				}
//			}
//
//			// **************************************************************
//		}
//
//	}
//
//	// The Start game method
//	// ***********************************************************************************
//	// ***********************************************************************************
//	// ***********************************************************************************
//	// ***********************************************************************************
//	// ***********************************************************************************
//
//	public void startGame() {
//		if (mode == -1)
//			return;
//
//		if (mode == 2 || mode == 1) {
//			if (firstName.getText().isBlank()) {
//				printError("Don't leave the name empty");
//				return;
//			}
//
//			totalRounds = spinner.getValue();
//			player1 = XSymbolButton.isSelected() ? 'X' : 'O';
//			player2 = (player1 == 'X') ? 'O' : 'X';
//
//			stage.setScene(new Scene(getBoardPage(), 900, 700));
//
//			if (choseFirst() == 1) {
//				if (mode == 2)
//					AiMove();
//				else
//					randomMove();
//			}
//		} else {
//			if (firstName.getText().isBlank() || secondName.getText().isBlank()) {
//				printError("Don't leave the name empty");
//				return;
//			}
//			totalRounds = spinner.getValue();
//			player1 = XSymbolButton.isSelected() ? 'X' : 'O';
//			player2 = (player1 == 'X') ? 'O' : 'X';
//			stage.setScene(new Scene(getBoardPage(), 900, 700));
//
//			if (choseFirst() == 1)
//				isPlayerOneTurn = false;
//		}
//	}
//
//	// this method updates the score and check for wins
//	// ***********************************************************************************
//	// ***********************************************************************************
//	// ***********************************************************************************
//
//	private void updateScore() {
//		Rounds.setText(totalGames + "/" + totalRounds);
//		playerOneWinsLapel.setText(playerOneWins + "");
//		playerTwoWinsLapel.setText(playerTwoWins + "");
//
//		boolean finish = false;
//		boolean playerP1 = false;
//		if (totalRounds % 2 == 0) {
//			if (totalGames > totalRounds / 2) {
//				if (playerOneWins > playerTwoWins) {
//					finish = true;
//					playerP1 = true;
//				} else {
//					finish = true;
//
//				}
//			}
//		} else {
//			if (totalGames >= (totalRounds / 2)+1 ) {
//				if (playerOneWins > playerTwoWins) {
//					finish = true;
//					playerP1 = true;
//				} else {
//					finish = true;
//				}
//			}
//		}
//		if (finish == true) {
//			Alert alert = new Alert(AlertType.INFORMATION);
//			alert.setTitle("The game is over!!");
//			alert.setHeaderText(null);
//
//			String playerName = playerP1 == true ? firstName.getText().trim() : secondName.getText().trim();
//
//			alert.setContentText("Choose If " + "Continue" + "  Or " + "Skip");
//
//			ButtonType p1 = new ButtonType("Continue");
//			ButtonType p2 = new ButtonType("Skip");
//			alert.getButtonTypes().addAll(p1, p2);
//
//			Optional<ButtonType> result = alert.showAndWait();
//			if (result.get() == p2) {
//				stage.setScene(new Scene(getSecondPage(), 600, 600));
//			}
//		}
//
//		if (totalGames == totalRounds) {
//			String winnerMessage;
//
//			if (playerOneWins > playerTwoWins) {
//				winnerMessage = firstName.getText().trim() + " wins the match!";
//			} else if (playerTwoWins > playerOneWins) {
//				winnerMessage = (mode == 0) ? secondName.getText().trim() + " wins the match!" : "AI wins the match!";
//			} else {
//				winnerMessage = "It's a Draw!";
//			}
//
//			showResult(winnerMessage);
//			stage.setScene(new Scene(getSecondPage(), 600, 600));
//			playerOneWins = 0;
//			playerTwoWins = 0;
//
//		}
//	}
//
//	// This method for reseting the game for the next round
//	// ***********************************************************************************
//	// ***********************************************************************************
//
//	private void resetGame() {
//		field = new char[][] { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };
//
//		for (int i = 0; i < 3; i++) {
//			for (int j = 0; j < 3; j++) {
//				buttonsArray[i][j].setText("");
//			}
//		}
//		int result = 0;
//		if (totalGames != totalRounds) {
//			result = choseFirst();
//
//			if (result == 1 && mode == 2) {
//				isPlayerOneTurn = false;
//				AiMove();
//			} else if (result == 1 && mode == 1) {
//				isPlayerOneTurn = false;
//				// make random computer
//			} else if (result == 1 && mode == 0) {
//				isPlayerOneTurn = false;
//			} else
//				isPlayerOneTurn = true;
//		} else
//			totalGames = 0;
//
//	}
//
//	private boolean checkWin(char[][] board, char player) {
//		// Check rows and columns
//		for (int i = 0; i < 3; i++) {
//			if ((board[i][0] == player && board[i][1] == player && board[i][2] == player)
//					|| (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
//				return true;
//			}
//		}
//
//		// Check diagonals
//		return (board[0][0] == player && board[1][1] == player && board[2][2] == player)
//				|| (board[0][2] == player && board[1][1] == player && board[2][0] == player);
//	}
//
//	private boolean checkDraw(char[][] board) {
//		for (int i = 0; i < 3; i++) {
//			for (int j = 0; j < 3; j++) {
//				if (board[i][j] == ' ') {
//					return false;
//				}
//			}
//		}
//		return true;
//	}
//
//	// Alerts for Game experinces
//	// ***********************************************************************************
//	// ***********************************************************************************
//	// ***********************************************************************************
//
//	private void showResult(String message) {
//		Alert alert = new Alert(AlertType.INFORMATION);
//		alert.setTitle("Game Over");
//		alert.setHeaderText(null);
//		alert.setContentText(message);
//		alert.showAndWait();
//	}
//
//	private void printError(String message) {
//		Alert alert = new Alert(AlertType.INFORMATION);
//		alert.setTitle("Wait!");
//		alert.setHeaderText(null);
//		alert.setContentText(message);
//		alert.show();
//	}
//
//	// 0 for player1 and 1 for player 2
//	// this alert that resive the user choise of who starts the game firsts
//	// ***********************************************************************************
//	// ***********************************************************************************
//	// ***********************************************************************************
//
//	private int choseFirst() {
//		Alert alert = new Alert(AlertType.INFORMATION);
//		alert.setTitle("Choose Who Starts First!!");
//		alert.setHeaderText(null);
//
//		String player1Name = firstName.getText().trim();
//		String player2Name = (mode == 0) ? secondName.getText().trim() : "AI";
//
//		alert.setContentText("Choose If " + player1Name + " Starts Or " + player2Name);
//
//		ButtonType p1 = new ButtonType(player1Name);
//		ButtonType p2 = new ButtonType(player2Name);
//		alert.getButtonTypes().addAll(p1, p2);
//
//		Optional<ButtonType> result = alert.showAndWait();
//
//		// This handles the case where the user closes the dialog without making a
//		// choice.
//		return (result.orElse(null) == p1) ? 0 : 1;
//	}
//
//	// mainPage
//	// **************************************************************************************************
//	// **************************************************************************************************
//	// **************************************************************************************************
//	// **************************************************************************************************
//
//	private BorderPane getMainPage() {
//		Label label = new Label("Tic Tac Toe Game");
//		label.setStyle("-fx-font-size:40;-fx-text-fill:aquamarine;-fx-font-family:italy;");
//		label.setEffect(new DropShadow(15, Color.AQUAMARINE));
//		Button startButton = customButton("Start", 100, 60);
//		BorderPane bp = new BorderPane();
//		Image image = new Image("file:XOpic.png");
//		ImageView imageView = new ImageView(image);
//		imageView.setFitHeight(300);
//		imageView.setFitWidth(300);
//		imageView.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.AQUAMARINE, 15, 0.3, 15, 15));
//		bp.setPadding(new Insets(25, 25, 25, 25));
//		bp.setCenter(imageView);
//		bp.setTop(label);
//		bp.setBottom(startButton);
//		BorderPane.setAlignment(startButton, Pos.CENTER);
//		BorderPane.setAlignment(label, Pos.CENTER);
//		bp.setStyle("-fx-background-color:linear-gradient(to bottom,red,crimson,darkred);");
//
//		startButton.setOnAction(e -> {
//			stage.setScene(new Scene(getSecondPage(), 600, 600));
//		});
//
//		return bp;
//
//	}
//
//	// secondPage
//	// **************************************************************************************************
//	// **************************************************************************************************
//	// **************************************************************************************************
//	// **************************************************************************************************
//
//	private Pane getSecondPage() {
//		Button PlayersModeBT = customButton("Two Players", 300, 60);
//		Button easyModeBT = customButton("AI Easy", 300, 60);
//		Button hardModeBT = customButton("AI Hard", 300, 60);
//		Button start = customButton("Start", 200, 40);
//
//		spinner = new Spinner<Integer>(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10));
//		Label label = new Label("Choose Game Mood:");
//		label.setStyle("-fx-font-size:40;-fx-text-fill:aquamarine;-fx-font-family:italy;");
//		label.setEffect(new DropShadow(15, Color.AQUAMARINE));
//		Label FirstPlayer = new Label("First player Name:");
//		FirstPlayer.setStyle("-fx-font-size:20;-fx-text-fill:cyan;");
//		SecondPlayer = new Label("Sconde Player Name:");
//		SecondPlayer.setStyle("-fx-font-size:20;-fx-text-fill:cyan;");
//		XSymbolButton = new RadioButton();
//		Label XSymbol = new Label("X");
//		XSymbol.setStyle("-fx-font-size:35;-fx-text-fill:red");
//		RadioButton OSymbolButton = new RadioButton();
//		XSymbolButton.setSelected(true);
//		OSymbolButton.setStyle("-fx-font-size:10;-fx-text-fill:Green");
//		Label OSymbol = new Label("O");
//		OSymbol.setStyle("-fx-font-size:35;-fx-text-fill:green");
//		ToggleGroup tg = new ToggleGroup();
//		tg.getToggles().addAll(XSymbolButton, OSymbolButton);
//		firstName = new TextField();
//		firstName.setPromptText("Enter your name:");
//		firstName.setStyle("-fx-prompt-text-fill:green");
//		secondName = new TextField();
//		secondName.setPromptText("Enter your name:");
//		secondName.setStyle("-fx-prompt-text-fill:green;");
//		HBox hb1 = new HBox(10);
//		hb1.setPadding(new Insets(10, 10, 10, 10));
//		hb1.setAlignment(Pos.CENTER);
//		hb1.getChildren().addAll(FirstPlayer, firstName, XSymbolButton, XSymbol, OSymbolButton, OSymbol);
//		HBox hb2 = new HBox(10, SecondPlayer, secondName);
//		hb2.setPadding(new Insets(10, 10, 10, 10));
//		hb2.setAlignment(Pos.CENTER);
//		VBox vb = new VBox(15);
//		vb.setAlignment(Pos.CENTER);
//		vb.setPadding(new Insets(10, 10, 10, 10));
//		vb.getChildren().addAll(PlayersModeBT, easyModeBT, hardModeBT);
//		orderLabel = new Label("Mood:");
//		orderLabel.setStyle("-fx-font-size:20;-fx-text-fill:cyan;");
//		orderLabel.setEffect(new DropShadow(15, Color.CYAN));
//		HBox hbButtonsAndOrder = new HBox(60, vb, orderLabel);
//		hbButtonsAndOrder.setAlignment(Pos.CENTER);
//		hbButtonsAndOrder.setPadding(new Insets(20, 20, 20, 20));
//		// vb.setStyle("-fx-background-color:linear-gradient(to
//		// bottom,red,crimson,darkred);");
//		BorderPane bpPage2 = new BorderPane();
//		bpPage2.setTop(label);
//		BorderPane.setAlignment(label, Pos.CENTER);
//		bpPage2.setCenter(hbButtonsAndOrder);
//		bpPage2.setStyle("-fx-background-color:linear-gradient(to bottom,red,crimson,darkred);");
//
//		Label rounds = new Label("Rounds:");
//		rounds.setStyle("-fx-font-size:20;-fx-text-fill:cyan;");
//		rounds.setStyle("-fx-font-size:20;-fx-text-fill:cyan;");
//
////		start.setAlignment(Pos.CENTER_RIGHT);
////		start.setVisible(false);
//		HBox hb3 = new HBox(20, rounds, spinner, start);
//		hb3.setPadding(new Insets(10, 10, 10, 10));
//		hb3.setAlignment(Pos.CENTER);
//		VBox vb2 = new VBox(10, hb1, hb2, hb3);
//		vb2.setAlignment(Pos.CENTER);
//		vb2.setPadding(new Insets(10, 10, 10, 10));
//
//		bpPage2.setBottom(vb2);
//		BorderPane.setAlignment(vb2, Pos.CENTER);
//		bpPage2.setPadding(new Insets(30, 30, 30, 30));
//
////		hardModeBT.setOnAction(e -> {
////			stage.setScene(new Scene(getBoardPage(), 600, 600));
////		});
//		start.setOnAction(e -> {
//			startGame();
//		});
//		return bpPage2;
//	}
//
//	// getTheredPage
//	// **************************************************************************************************
//	// **************************************************************************************************
//	// **************************************************************************************************
//	// **************************************************************************************************
//	private Pane getBoardPage() {
//
//		BorderPane pane = new BorderPane();
//		pane.setStyle("-fx-background-color:linear-gradient(to bottom,lightgray,gray,black);");
//		pane.setPadding(new Insets(25, 25, 25, 25));
//
//		GridPane gridPane = new GridPane();
//		gridPane.setAlignment(Pos.CENTER);
//
//		for (int i = 0; i < 3; i++) {
//			for (int j = 0; j < 3; j++) {
//				Button buttonGrid = new Button();
//				int ii = i;
//				int jj = j;
//				buttonGrid.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
//				buttonGrid.setStyle("-fx-border-color:black;-fx-border-width:2;-fx-font-size:45;");
//				buttonGrid.setPrefSize(100, 100);
//				buttonGrid.setOnAction(e -> {
//					handleButtonClick(ii, jj);
//				});
//				buttonGrid.setOnMouseEntered(e -> {
//					buttonGrid.setStyle(
//							"-fx-border-color:black;-fx-border-width:2;-fx-background-color:lightgray;-fx-font-size:45;");
//					buttonGrid.setTextFill(buttonGrid.getTextFill());
//				});
//				buttonGrid.setOnMouseExited(e -> {
//					buttonGrid.setStyle(
//							"-fx-border-color:black;-fx-border-width:2;-fx-background-color:gray;-fx-font-size:45;");
//					buttonGrid.setTextFill(buttonGrid.getTextFill());
//
//				});
//				gridPane.add(buttonGrid, jj, ii);
//				buttonsArray[i][j] = buttonGrid;
//
//			}
//		}
//
////		pane.setCenter(gridPane);
//		Rounds = new Label(totalGames + "/" + totalRounds);
//		Rounds.setStyle("-fx-font-size:30;-fx-text-fill:cyan;");
//		Rounds.setEffect(new DropShadow(10, Color.CYAN));
//		pane.setBottom(Rounds);
//		BorderPane.setAlignment(Rounds, Pos.BOTTOM_RIGHT);
//		Label p1 = new Label(firstName.getText().trim());
//		p1.setStyle("-fx-font-size:25;-fx-text-fill:cyan");
//		Label p1Symbol = new Label(player1 + "");
//		if (player1 == 'X')
//			p1Symbol.setStyle("-fx-font-size:30;-fx-text-fill:red");
//		else
//			p1Symbol.setStyle("-fx-font-size:30;-fx-text-fill:green");
//
//		Label p2 = new Label();
//		if (secondName.getText().isBlank())
//			p2.setText("AI");
//		else
//			p2.setText(secondName.getText().trim());
//		p2.setStyle("-fx-font-size:25;-fx-text-fill:cyan");
//		p2.setEffect(new DropShadow(15, Color.CYAN));
//		p1.setEffect(new DropShadow(15, Color.CYAN));
//
//		Label p2Symbol = new Label(player2 + "");
//		if (player2 == 'O')
//			p2Symbol.setStyle("-fx-font-size:30;-fx-text-fill:Green");
//		else
//			p2Symbol.setStyle("-fx-font-size:30;-fx-text-fill:red");
//
//		HBox hb = new HBox(100);
//		HBox hb1 = new HBox(10, p1, p1Symbol);
//		playerOneWinsLapel = new Label();
//		playerOneWinsLapel.setStyle("-fx-font-size:30;-fx-text-fill:red");
//		playerOneWinsLapel.setEffect(new DropShadow(15, Color.RED));
//		VBox vb1 = new VBox(10, hb1, playerOneWinsLapel);
//		vb1.setAlignment(Pos.CENTER);
//		vb1.setPadding(new Insets(10, 10, 10, 10));
//		HBox hb2 = new HBox(10, p2, p2Symbol);
//		playerTwoWinsLapel = new Label();
//		playerTwoWinsLapel.setStyle("-fx-font-size:30;-fx-text-fill:red");
//		playerTwoWinsLapel.setEffect(new DropShadow(15, Color.RED));
//		VBox vb2 = new VBox(10, hb2, playerTwoWinsLapel);
//		vb2.setAlignment(Pos.CENTER);
//		vb2.setPadding(new Insets(10, 10, 10, 10));
//		hb.setAlignment(Pos.CENTER);
//		hb.setPadding(new Insets(10, 10, 10, 10));
//		hb.getChildren().addAll(vb1, vb2);
//		pane.setTop(hb);
//
//		GridPane gp = new GridPane();
//		gp.setAlignment(Pos.CENTER);
//		for (int i = 0; i < 3; i++) {
//			for (int j = 0; j < 3; j++) {
//				Button buttonBlack = new Button();
//				buttonBlack.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
//				buttonBlack.setStyle("-fx-border-color:white;-fx-border-width:2;-fx-font-size:20;-fx-text-fill:red");
//				buttonBlack.setPrefSize(100, 100);
//				gp.add(buttonBlack, j, i);
//				hints[i][j] = buttonBlack;
//			}
//		}
//		HBox hbb = null;
//		if (mode == 2) {
//			hbb = new HBox(50, gridPane, gp);
//			hbb.setAlignment(Pos.CENTER);
//			pane.setCenter(hbb);
//			BorderPane.setAlignment(hbb, Pos.CENTER);
//
//		} else
//			pane.setCenter(gridPane);
//
//		BorderPane.setAlignment(hb, Pos.TOP_CENTER);
//
//		return pane;
//	}
//
//	// CustomButtons
//	// **************************************************************************************************
//	// **************************************************************************************************
//	// **************************************************************************************************
//	// **************************************************************************************************
//
//	public Button customButton(String name, int width, int height) {
//		Button button = new Button(name);
//		button.setPrefSize(width, height);
//		button.setStyle(
//				"-fx-background-color: gray; -fx-text-fill: white; -fx-font-size: 25px;-fx-background-radius:20;-fx-padding:10");
//
//		// Change color on mouse enter (hover)
//		button.setOnMouseEntered(e -> {
//			button.setStyle(
//					"-fx-background-color: aquamarine; -fx-text-fill: red; -fx-font-size: 25px;-fx-background-radius:20;-fx-padding:10");
//
//		});
//
//		// Change color back on mouse exit
//		button.setOnMouseExited(e -> {
//			button.setStyle(
//					"-fx-background-color: gray; -fx-text-fill: white; -fx-font-size: 25px;-fx-background-radius:20;-fx-padding:10");
//
//		});
//		button.setOnMouseClicked(e -> {
//			button.setStyle(
//					"-fx-background-color: gray; -fx-text-fill: cyan; -fx-font-size: 25px;-fx-background-radius:20;-fx-padding:10");
//
//			if (button.getText().equals("Two Players")) {
//				mode = 0;
//				orderLabel.setText("Mode:\nTwo Players");
//				secondName.setVisible(true);
//				SecondPlayer.setVisible(true);
//
//			} else if (button.getText().equals("AI Easy")) {
//				mode = 1;
//				orderLabel.setText("Mode:\nAI Easy");
//				secondName.setVisible(false);
//				SecondPlayer.setVisible(false);
//
//			} else {
//				mode = 2;
//				orderLabel.setText("Mode:\nAI Hard");
//				secondName.setVisible(false);
//				SecondPlayer.setVisible(false);
//			}
//
//		});
//		return button;
//	}
//
//}
