package com.example.task14;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class SecondChanceHandler extends Handler {
    public static final int SECOND_CHANCE = 3;

    public SecondChanceHandler(Handler next) {
        super(next);
    }

    @Override
    public boolean process(int requestType, Player player) {
        if (requestType != SECOND_CHANCE) {
            return super.process(requestType, player);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Второй шанс!");
            alert.setHeaderText("Вам выпал второй шанс! Попробуйте еще раз бесплатно.");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
            return true; // Бесплатная попытка, монета не списывается
        }
    }
}