package application;

import application.controlador.PersonaControlador;
import application.modelo.PersonaModelo;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Main extends Application implements EventHandler<ActionEvent> {

	private static GridPane tabla;
	private static Button registrar;
	private static Button editar;
	private static Button eliminar;

	private static PersonaControlador controlador = new PersonaControlador();

	@Override
	public void start(Stage primaryStage) {
		try {
			tabla = new GridPane();

			HBox botones = new HBox(5);
			botones.setAlignment(Pos.CENTER);
			registrar = new Button("Nuevo");
			registrar.setOnAction(this);
			editar = new Button("Editar");
			editar.setOnAction(this);
			eliminar = new Button("Eliminar");
			eliminar.setOnAction(this);
			botones.getChildren().addAll(registrar, editar, eliminar);

			VBox root = new VBox();
			root.setPadding(new Insets(10));
			
			pintarTabla();
			
			root.getChildren().addAll(tabla, botones);

			StackPane canvas = new StackPane();
			canvas.getChildren().add(root);

			Scene scene = new Scene(canvas);

			primaryStage.setScene(scene);
			primaryStage.setTitle("Prueba_SergioRodrìguez");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void handle(ActionEvent event) {
		Button evt = (Button) event.getSource();
		switch (evt.getText()) {
		case "Nuevo":
			Alerta.registrarDialog();
			break;
		case "Editar":
			Alerta.editarDialog();
			break;
		case "Eliminar":
			Alerta.eliminarDialog();
			break;
		}
	}

	public static void pintarTabla() {
		tabla.getChildren().clear();
		tabla.setGridLinesVisible(true);
		
		Label lblCedula = new Label("Cedula");
		Label lblNombre = new Label("Nombre");
		Label lblApellido = new Label("Apellido");
		Label lblCiudad = new Label("Ciudad");
		Label lblTelefono = new Label("Telefono");

		tabla.add(lblCedula, 0, 0);
		tabla.add(lblNombre, 1, 0);
		tabla.add(lblApellido, 2, 0);
		tabla.add(lblCiudad, 3, 0);
		tabla.add(lblTelefono, 4, 0);

		for (int i = 0; i < controlador.consultar().size(); i++) {
			PersonaModelo temp = controlador.consultarPorPosicion(i);

			Text txtCedula = new Text(temp.getCedula());
			Text txtNombre = new Text(temp.getNombre());
			Text txtApellido = new Text(temp.getApellido());
			Text txtCiudad = new Text(temp.getCiudad());
			Text txtTelefono = new Text(temp.getTelefono());

			tabla.add(txtCedula, 0, i + 1);
			tabla.add(txtNombre, 1, i + 1);
			tabla.add(txtApellido, 2, i + 1);
			tabla.add(txtCiudad, 3, i + 1);
			tabla.add(txtTelefono, 4, i + 1);
		}
		
		if(controlador.consultar().size() == 0) {
			editar.setDisable(true);
			eliminar.setDisable(true);
		} else {
			editar.setDisable(false);
			eliminar.setDisable(false);
		}
		
	}

}
