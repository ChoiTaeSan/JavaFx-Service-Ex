package com.example.javafx_service_ex;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML ProgressBar pb;
    @FXML Label lb_num, lb_result;
    @FXML Button btn_start, btn_stop;

    private CustomService customService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customService = new CustomService();

        btn_start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                customService.restart();
            }
        });

        btn_stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                customService.cancel();
            }
        });
    }

    public class CustomService extends Service<Integer> {
        @Override
        protected Task<Integer> createTask() {

            Task<Integer> task = new Task<Integer>() {
                @Override
                protected Integer call() throws Exception {

                    int result = 0;
                    for (int i = 0; i<= 100; i++){

                        if(isCancelled()) {
                            System.out.println("isCanclled() test." );
                            break;
                        }
                        result += i;

                        updateProgress(i,100);
                        updateMessage(String.valueOf(i));

                        Thread.sleep(100);
                    }
                    return result;
                }
            };
            pb.progressProperty().bind(task.progressProperty());
            lb_num.textProperty().bind(task.messageProperty());
            return task;
        }
    }
}

