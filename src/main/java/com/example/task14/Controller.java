package com.example.task14;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.util.Duration;

public class Controller {
    @FXML
    private Button ticket1Button;
    @FXML
    private Button ticket2Button;
    @FXML
    private Button ticket3Button;
    @FXML
    private Button addCoinButton;
    @FXML
    private Label coinsLabel;
    @FXML
    private Label winsLabel;
    @FXML
    private Label lossesLabel;
    @FXML
    private Canvas backgroundCanvas;

    private Timeline animation;

    private Player player;
    private ActionChain actionChain;

    @FXML
    public void initialize() {
        player = new Player("Игрок", 3); // стартовое количество монет
        actionChain = new ActionChain();
        updateLabels();
        startBackgroundAnimation();
        // Tooltips
        ticket1Button.setTooltip(new Tooltip("Выбрать билет №1"));
        ticket2Button.setTooltip(new Tooltip("Выбрать билет №2"));
        ticket3Button.setTooltip(new Tooltip("Выбрать билет №3"));
    }

    @FXML
    private void onTicket1() { handleTicket(); }
    @FXML
    private void onTicket2() { handleTicket(); }
    @FXML
    private void onTicket3() { handleTicket(); }
    @FXML
    private void onAddCoin() {
        player.addCoins(1);
        updateLabels();
        ticket1Button.setDisable(false);
        ticket2Button.setDisable(false);
        ticket3Button.setDisable(false);
    }

    private void handleTicket() {
        // Монета не списывается заранее
        int eventType = actionChain.draw();
        boolean playAgain = actionChain.process(eventType, player);
        updateLabels();
        // Кнопки блокируются только если монет нет
        boolean outOfCoins = player.getCoins() <= 0;
        ticket1Button.setDisable(outOfCoins);
        ticket2Button.setDisable(outOfCoins);
        ticket3Button.setDisable(outOfCoins);
    }

    private void updateLabels() {
        coinsLabel.setText("Монеты: " + player.getCoins());
        winsLabel.setText("Выигрыши: " + player.getWins());
        lossesLabel.setText("Проигрыши: " + player.getLosses());
    }

    private void startBackgroundAnimation() {
        GraphicsContext gc = backgroundCanvas.getGraphicsContext2D();
        animation = new Timeline(new KeyFrame(Duration.millis(33), e -> {
            double w = backgroundCanvas.getWidth();
            double h = backgroundCanvas.getHeight();
            double time = (System.currentTimeMillis() % 4000) / 4000.0;

            // Движущийся градиент
            gc.setFill(new LinearGradient(time, 0, 1, 1, true, CycleMethod.REFLECT,
                    new Stop(0, Color.LIGHTSKYBLUE), new Stop(1, Color.LIGHTGOLDENRODYELLOW)));
            gc.fillRect(0, 0, w, h);

            // Анимированные круги
            for (int i = 0; i < 5; i++) {
                double x = w * ((i * 0.2 + time) % 1.0);
                double y = h * (0.3 + 0.2 * Math.sin(time * 2 * Math.PI + i));
                gc.setFill(Color.rgb(255, 255, 255, 0.2 + 0.15 * i));
                gc.fillOval(x, y, 80, 80);
            }
        }));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
}