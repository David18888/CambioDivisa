package dad.javafx.cambiodivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application {

	TextField origenText;
	TextField destinoText;
	
	
	ComboBox  <Divisa>origenCombo;
	ComboBox  <Divisa>destinoCombo;
	Button cambiarButton;

		Divisa euro  = new Divisa("Euro",1.00);
		Divisa dolar = new Divisa("Dólar",1.17);
		Divisa yen = new Divisa("Yen",124.17);
		Divisa libra  = new Divisa("Libra",0.9);
	
	  Divisa[] divisas= {euro,libra,dolar,yen};
	
	public void start(Stage primaryStage) throws Exception {
		origenText= new TextField();
		origenText.setMaxWidth(100);
		 
		destinoText= new TextField();
		destinoText.setMaxWidth(100);
		destinoText.setEditable(false);
		
		origenCombo=new ComboBox <Divisa> ();
		origenCombo.getItems().addAll(divisas);
		origenCombo.getSelectionModel().selectFirst();
		
		destinoCombo= new ComboBox <Divisa>();
		destinoCombo.getItems().addAll(divisas);
		destinoCombo.getSelectionModel().selectNext();
		
		cambiarButton= new Button();
		cambiarButton.setText("Cambiar");
		cambiarButton.setDefaultButton(true);
		cambiarButton.setOnAction(e-> onCambiarButtonAction(e));
		
		HBox origenBox = new HBox();
		origenBox.getChildren().addAll(origenText,origenCombo);
		origenBox.setAlignment(Pos.CENTER);
		origenBox.setSpacing(5);
		
		HBox destinoBox= new HBox();
		destinoBox.getChildren().addAll(destinoText,destinoCombo);
		destinoBox.setAlignment(Pos.CENTER);
		destinoBox.setSpacing(5);
		
		
		VBox contenedorBox = new VBox();
		contenedorBox.getChildren().addAll(origenBox,destinoBox,cambiarButton);
		contenedorBox.setAlignment(Pos.CENTER);
		contenedorBox.setSpacing(10);
		
		
		
		Scene scene= new Scene(contenedorBox,350,200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Cambio de Divisa");
		primaryStage.show();
		
		
		

	}

	private void onCambiarButtonAction(ActionEvent e) {
		
		try {
		
		Double cantOrigen= Double.parseDouble(origenText.getText());
		
		Divisa  divisaOrigen= origenCombo.getSelectionModel().getSelectedItem();
		Divisa  divisaDestino=destinoCombo.getSelectionModel().getSelectedItem();
		
		destinoText.setText(" "+ divisaDestino.fromEuro(divisaOrigen.toEuro(cantOrigen)));
		
		
		} catch(NumberFormatException n) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Debes escribir un número válido!");
			alert.showAndWait();
			
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
