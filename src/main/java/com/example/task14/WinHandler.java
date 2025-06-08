package com.example.task14;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class WinHandler extends Handler {
    public static final int WIN = 1;
    private static final int WIN_REWARD = 5;

    public WinHandler(Handler next) {
        super(next);
    }

    @Override
    public boolean process(int requestType, Player player) {
        if (requestType != WIN) {
            return super.process(requestType, player);
        } else {
            if (!player.pay(1)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Нет монет");
                alert.setHeaderText("Недостаточно монет для участия!");
                alert.showAndWait();
                return false;
            }
            player.addWin();
            player.addCoins(WIN_REWARD);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Поздравляем!");
            alert.setHeaderText("Вы выиграли! +" + WIN_REWARD + " монет. Забрать выигрыш или продолжить?");
            ButtonType take = new ButtonType("Забрать", ButtonBar.ButtonData.NO);
            ButtonType playMore = new ButtonType("Продолжить", ButtonBar.ButtonData.YES);
            alert.getButtonTypes().setAll(playMore, take);
            Optional<ButtonType> option = alert.showAndWait();
            return option.isPresent() && option.get().getButtonData() == ButtonBar.ButtonData.YES;
        }
    }
}