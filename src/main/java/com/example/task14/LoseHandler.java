package com.example.task14;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class LoseHandler extends Handler {
    public static final int LOSE = 2;
    private static final int LOSE_PENALTY = 3;

    public LoseHandler(Handler next) {
        super(next);
    }

    @Override
    public boolean process(int requestType, Player player) {
        if (requestType != LOSE) {
            return super.process(requestType, player);
        } else {
            if (!player.pay(1)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Нет монет");
                alert.setHeaderText("Недостаточно монет для участия!");
                alert.showAndWait();
                return false;
            }
            player.addLoss();
            // Списываем еще 2 монеты (итого -3), если хватает
            int penalty = Math.min(LOSE_PENALTY - 1, player.getCoins());
            player.pay(penalty);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Вы проиграли!");
            alert.setHeaderText("С вас списано 3 монеты. Попробовать снова?");
            ButtonType replay = new ButtonType("Да", ButtonBar.ButtonData.YES);
            ButtonType stop = new ButtonType("Нет", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(replay, stop);
            Optional<ButtonType> option = alert.showAndWait();
            return option.isPresent() && option.get().getButtonData() == ButtonBar.ButtonData.YES;
        }
    }
}