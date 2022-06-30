package gui;

import controller.Controller;
import javafx.application.Application;

import javafx.beans.value.ChangeListener;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Medarbejder;
import model.Vagt;

import java.io.*;
import java.util.Scanner;

public class StartWindow extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Kantines vagtplanlægning");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public void init(){
        Controller.init();
    }

    private final ListView<Medarbejder> lvwMedarbejdere = new ListView<>();
    private final ListView<Vagt> lvwVagter = new ListView<>();
    private TextArea txaVagtInfo = new TextArea();


    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(20));

        Label lblAlleMedarbejdere = new Label("Alle medarbejdere");
        pane.add(lblAlleMedarbejdere,0,0);
        pane.add(lvwMedarbejdere,0,1);
        lvwMedarbejdere.setPrefHeight(200);
        lvwMedarbejdere.setPrefWidth(200);
        lvwMedarbejdere.getItems().setAll(Controller.getMedarbejdere());


        Label lblAlleVagter = new Label("Alle Vagter");
        pane.add(lblAlleVagter,1,0);
        pane.add(lvwVagter,1,1);
        lvwVagter.setPrefWidth(200);
        lvwVagter.setPrefHeight(200);
        lvwVagter.getItems().setAll(Controller.getVagter());
        ChangeListener<Vagt> listenerVagt = (ov, oldVagt, newVagt) -> this.selectecVagtChanged();
        lvwVagter.getSelectionModel().selectedItemProperty().addListener(listenerVagt);

        Label lblValgtVagt = new Label("Valgt Vagt");
        pane.add(lblValgtVagt,2,0);
        pane.add(txaVagtInfo,2,1);
        txaVagtInfo.setPrefWidth(300);
        txaVagtInfo.setPrefHeight(200);

        Button btnTildelVagt = new Button("Tildel Vagt");
        pane.add(btnTildelVagt,1,2);
        btnTildelVagt.setOnAction(event -> this.tildelVagtTilMedarbejder());

        Button btnUdskrivTilFil = new Button("Udskriv til fil");
        pane.add(btnUdskrivTilFil,2,2);
        btnUdskrivTilFil.setOnAction(event -> this.udskrivTilFil());


    }

    private void udskrivTilFil() {
        try(PrintWriter pw = new PrintWriter(".\\L34-SemesterProve\\src\\controller\\udskrivTilFil.txt")){
            pw.println(txaVagtInfo.getText());
        }catch (FileNotFoundException fe){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No file exist");
            alert.setHeaderText("No file with that name exists");
        }
    }

    private void tildelVagtTilMedarbejder() {
        try{
            Controller.addMedarbejderToVagt(lvwVagter.getSelectionModel().getSelectedItem(),lvwMedarbejdere.getSelectionModel().getSelectedItem());
            updateControls();
        }catch (RuntimeException re){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Tildel vagt");
            alert.setHeaderText(lvwMedarbejdere.getSelectionModel().getSelectedItem() + " Har allerede en vagt på den dag");
            alert.setContentText("På den pågældende dag har " + lvwMedarbejdere.getSelectionModel().getSelectedItem().getNavn() + " vagten: " + lvwMedarbejdere.getSelectionModel().getSelectedItem().getVagter());
            alert.show();
        }

    }

    private void selectecVagtChanged() {
        updateControls();

    }

    private void updateControls() {
        Vagt vagt = lvwVagter.getSelectionModel().getSelectedItem();
        if(vagt != null){
            txaVagtInfo.setText(vagt.getNavn()+"\n"+vagt.getTidFra()+"\n"+vagt.getTidTil()+"\n"+vagt.status()+"\n"+vagt.getMedarbejdere());
        }
    }

}
