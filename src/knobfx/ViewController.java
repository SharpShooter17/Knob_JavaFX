package knobfx;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Canvas canvas;

    @FXML
    private GridPane grid;
    
    @FXML
    private Button button;

    private boolean bOn = true;
    private ImageView image1;
    private ImageView image2;
    
    
    @FXML
    void click(ActionEvent event) {
        bOn = !bOn;
        if (bOn){
            button.setGraphic(image1);
            calculatePoints(freqA.getValue(), freqB.getValue(), phaseA.getValue(), phaseB.getValue(), gc);
        }else {
            button.setGraphic(image2);
        }
    }

    private Knob freqA = new Knob();
    private Knob freqB = new Knob();
    private Knob phaseA = new Knob();
    private Knob phaseB = new Knob();

    GraphicsContext gc;
    
    @FXML
    void initialize() {
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'view.fxml'.";
        assert grid != null : "fx:id=\"grid\" was not injected: check your FXML file 'view.fxml'.";
        assert button != null : "fx:id=\"button\" was not injected: check your FXML file 'view.fxml'.";
        
       // String img1 = getClass().getResource("on.png").getPath();
      //  System.out.println("Img1: "+  img1);
        image1 = new ImageView("on.png");
        image2 = new ImageView("off.png");
        
        image1.setFitHeight(50);
        image1.setFitWidth(100);
        
        image2.setFitHeight(50);
        image2.setFitWidth(100);
        click(null);
        int w = 90;
        int h = 90;
       
        freqA.setPrefHeight(h);
        freqA.setPrefWidth(w);
        freqA.setMaxValue(360);
        
        freqB.setPrefHeight(h);
        freqB.setPrefWidth(w);
        freqB.setMaxValue(360);
        
        phaseA.setPrefHeight(h);
        phaseA.setPrefWidth(w);
        phaseA.setMinValue(1);
        phaseA.setMaxValue(6);
        
        phaseB.setPrefHeight(h);
        phaseB.setPrefWidth(w);
        phaseB.setMinValue(1);
        phaseB.setMaxValue(6);
        
        freqA.setOnMouseReleased(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent event) {
             calculatePoints(freqA.getValue(), freqB.getValue(), phaseA.getValue(), phaseB.getValue(), gc);
        }});
        
        freqB.setOnMouseReleased(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent event) {
             calculatePoints(freqA.getValue(), freqB.getValue(), phaseA.getValue(), phaseB.getValue(), gc);
        }});
        
        phaseA.setOnMouseReleased(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent event) {
             calculatePoints(freqA.getValue(), freqB.getValue(), phaseA.getValue(), phaseB.getValue(), gc);
        }});
        
        phaseB.setOnMouseReleased(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent event) {
             calculatePoints(freqA.getValue(), freqB.getValue(), phaseA.getValue(), phaseB.getValue(), gc);
        }});

        grid.add(freqA, 0, 0);
        grid.add(freqB, 0, 1);
        grid.add(phaseA, 1, 0);
        grid.add(phaseB, 1, 1);

        gc = canvas.getGraphicsContext2D();
    }

    public void calculatePoints(double freqX, double freqY, double phaseX, double phaseY, GraphicsContext gc) {
        if (!bOn){
            return;
        }
        
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        double ampliX = gc.getCanvas().getWidth() / 2;
        double ampliY = gc.getCanvas().getHeight() / 2;
        double phaseXrad;
        double phaseYrad;

        phaseXrad = Math.toRadians(phaseX);
        phaseYrad = Math.toRadians(phaseY);

        double step = gc.getCanvas().getWidth() / (360 / 0.01);
        
        for (double t = 0.0; t < gc.getCanvas().getWidth(); t += step) {
            double x = (ampliX * (Math.sin(freqX * t + phaseXrad)) + ampliX);
            double y = (ampliY * (Math.sin(freqY * t + phaseYrad)) - ampliY);
            gc.strokeLine(x, (y * -1), x, (y * -1));
        }
    }

}
