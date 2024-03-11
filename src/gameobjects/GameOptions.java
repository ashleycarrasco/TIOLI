package gameobjects;

import game.DealerArea;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GameOptions extends VBox {
	
	private CheckBox cardBack = new CheckBox("Red Card Back");
	private CheckBox showTimer = new CheckBox("Timer Visible");
	private CheckBox useTimer = new CheckBox("Use Timer");
	
	private Label lblSetTime = new Label("Set Timer To:");
	private TextField txtSetTime = new TextField();
	
	private VBox setTimeContainer = new VBox();
	
	private Text headerText = new Text("Game Options");
	
	private DealerArea dealerAreaObj;
	private GameTimer timerObj;
	
	public GameOptions(DealerArea dealerArea, GameTimer timer) {
		
		this.dealerAreaObj = dealerArea;
		this.timerObj = timer;
		
		createCardBackOption();
		
		createShowTimerOption();
		
		createUseTimerOption();
		
		addSetTimerObjectsToHBox();
		
		createSetTimerText();
		
		addObjectsToVBox();
		
		optionalStyling();
		
	}
	
	private void createCardBackOption() {
		
		cardBack.setSelected(true);
		
		cardBack.setOnAction(e -> {
			if (cardBack.isSelected()) {
				dealerAreaObj.setCardBack("Red");
			} else {
				dealerAreaObj.setCardBack("Blue");
			}
		});
	}
	
	private void createShowTimerOption() {
		
		showTimer.setSelected(true);
		
		showTimer.setOnAction(e -> {
			if (showTimer.isSelected()) {
				timerObj.setVisible(true);
			} else {
				timerObj.setVisible(false);
			}
		});
		
	}
	
	private void createUseTimerOption() {
		
		useTimer.setSelected(true);
		
		useTimer.setOnAction(e -> {
			useTimerUpdates(useTimer.isSelected());
		});
	}
	
	private void useTimerUpdates(boolean useTimerSelected) {
		
		timerObj.setUseTimer(useTimerSelected);
		if(useTimerSelected) {
			timerObj.setVisible(true);
			setTimeContainer.setVisible(true);
			showTimer.setSelected(true);
			showTimer.setDisable(false);
		} else {
			timerObj.setVisible(false);
			setTimeContainer.setVisible(false);
			showTimer.setSelected(false);
			showTimer.setDisable(true);
		}
		
	}
	
	private void addSetTimerObjectsToHBox() {
		
		setTimeContainer.getChildren().addAll(lblSetTime,txtSetTime);
		
	}
	
	private void createSetTimerText() {
		
		String startSeconds = String.valueOf(timerObj.getStartSeconds());
		
		txtSetTime.setText(startSeconds);
		txtSetTime.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.TAB) {
				codeExecutedBySetTimerListener();
			}
		});
	}
	
	private void codeExecutedBySetTimerListener() {
		
		Integer valStartSeconds = Integer.valueOf(txtSetTime.getText());
		
		timerObj.setStartSeconds(valStartSeconds);
		timerObj.refreshTimerDisplay();
		
	}
	
	private void addObjectsToVBox() {
		
		this.getChildren().addAll(headerText,cardBack, showTimer,useTimer,setTimeContainer);
	}
	
	private void optionalStyling() {
	}
}
