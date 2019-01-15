package application;

import java.util.Optional;

import application.controlador.PersonaControlador;
import application.modelo.PersonaModelo;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class Alerta {

	public static void infoAlert(String title, String header, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	public static void warnningAlert(String title, String header, String content) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	public static void registrarDialog() {
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Nuevo");
		dialog.setHeaderText("Registra una nueva persona");

		ButtonType buttonTypeRegistrar = new ButtonType("Registrar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeRegistrar, ButtonType.CANCEL);

		VBox labels = new VBox(15);
		Label lblCedula = new Label("Cedula:");
		Label lblNombre = new Label("Nombre:");
		Label lblApellido = new Label("Apellido:");
		Label lblCiudad = new Label("Ciudad:");
		Label lblTelefono = new Label("Telefono:");
		labels.getChildren().addAll(lblCedula, lblNombre, lblApellido, lblCiudad, lblTelefono);

		VBox textFields = new VBox(5);
		TextField txtCedula = new TextField();
		TextField txtNombre = new TextField();
		TextField txtApellido = new TextField();
		TextField txtCiudad = new TextField();
		TextField txtTelefono = new TextField();
		textFields.getChildren().addAll(txtCedula, txtNombre, txtApellido, txtCiudad, txtTelefono);

		HBox content = new HBox();
		content.getChildren().addAll(labels, textFields);

		dialog.getDialogPane().setContent(content);
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == buttonTypeRegistrar) {
				if (!txtCedula.getText().isEmpty() && !txtNombre.getText().isEmpty() && !txtApellido.getText().isEmpty()
						&& !txtCiudad.getText().isEmpty() && !txtTelefono.getText().isEmpty()) {
					PersonaControlador controlador = new PersonaControlador();
					controlador.registrar(txtCedula.getText(), txtNombre.getText(), txtApellido.getText(),
							txtCiudad.getText(), txtTelefono.getText());
					Alerta.infoAlert("¡Exito!", "¡Registro Exitoso!", "Registro exitoso.");

					Main.pintarTabla();
				} else {
					Alerta.warnningAlert("¡Cuidado!", "Falta(n) algun(os) campos por completar.",
							"Verifique que todos los campos estèn llenos.");
				}
			}
			return null;
		});
		Optional<Pair<String, String>> result = dialog.showAndWait();
	}

	public static void editarDialog() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Eliminar");
		dialog.setHeaderText("Por favor digite la posiciòn de la persona a eliminar.");
		dialog.setContentText("Tenga en cuenta que la primera persona de la tabla es la posiciòn 1.");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {

			PersonaControlador controlador = new PersonaControlador();
			PersonaModelo modelo = controlador.consultarPorPosicion(Integer.parseInt(result.get()) - 1);

			Dialog<Pair<String, String>> dialog2 = new Dialog<>();
			dialog2.setTitle("Nuevo");
			dialog2.setHeaderText("Registra una nueva persona");

			ButtonType buttonTypeRegistrar = new ButtonType("Editar", ButtonData.OK_DONE);
			dialog2.getDialogPane().getButtonTypes().addAll(buttonTypeRegistrar, ButtonType.CANCEL);

			VBox labels = new VBox(15);
			Label lblCedula = new Label("Cedula:");
			Label lblNombre = new Label("Nombre:");
			Label lblApellido = new Label("Apellido:");
			Label lblCiudad = new Label("Ciudad:");
			Label lblTelefono = new Label("Telefono:");
			labels.getChildren().addAll(lblCedula, lblNombre, lblApellido, lblCiudad, lblTelefono);

			VBox textFields = new VBox(5);
			TextField txtCedula = new TextField(modelo.getCedula());
			txtCedula.setEditable(false);
			TextField txtNombre = new TextField(modelo.getNombre());
			TextField txtApellido = new TextField(modelo.getApellido());
			TextField txtCiudad = new TextField(modelo.getCiudad());
			TextField txtTelefono = new TextField(modelo.getTelefono());
			textFields.getChildren().addAll(txtCedula, txtNombre, txtApellido, txtCiudad, txtTelefono);

			HBox content = new HBox();
			content.getChildren().addAll(labels, textFields);

			dialog2.getDialogPane().setContent(content);
			dialog2.setResultConverter(dialogButton -> {
				if (dialogButton == buttonTypeRegistrar) {
					if (dialogButton == buttonTypeRegistrar) {
						if (!txtCedula.getText().isEmpty() && !txtNombre.getText().isEmpty()
								&& !txtApellido.getText().isEmpty() && !txtCiudad.getText().isEmpty()
								&& !txtTelefono.getText().isEmpty()) {
							controlador.editar(Integer.parseInt(result.get()) - 1, txtCedula.getText(),
									txtNombre.getText(), txtApellido.getText(), txtCiudad.getText(),
									txtTelefono.getText());
							Alerta.infoAlert("Editado", "Editado correctamente.", "Editado correctamente.");
							Main.pintarTabla();
						} else {
							Alerta.warnningAlert("¡Cuidado!", "Falta(n) algun(os) campos por completar.",
									"Verifique que todos los campos estèn llenos.");
						}

					}
				}
				return null;
			});
			Optional<Pair<String, String>> result2 = dialog2.showAndWait();
		}

	}

	public static void eliminarDialog() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Eliminar");
		dialog.setHeaderText(
				"Por favor digite la(s) posiciòn(es) de la persona(s) a eliminar separadas por comas ',', sin espacios.");
		dialog.setContentText("Tenga en cuenta que la primera persona de la tabla es la posiciòn 1.");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			PersonaControlador controlador = new PersonaControlador();
			String[] listaEliminar = result.get().split(",");
			try {
				int i = 1;
				for (String temp : listaEliminar) {
					controlador.eliminar(Integer.parseInt(temp) - i);
					i++;
				}
				Alerta.infoAlert("¡Eliminado!", "Persona eliminada satisfactoriamente.",
						"La persona en la posiciòn " + result.get() + " fue eliminada.");
			} catch (Exception ex) {
			}
			Main.pintarTabla();
		}
	}

}
