package qust.gss.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	private Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		this.stage.setTitle("餐厅点餐系统");
		this.stage.getIcons().add(new Image("file:" + MainApp.class.getResource("logo.png").getPath()));
		showLoginLayout();
	}

	public void showLoginLayout() {
		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("LoginLayout.fxml"));
			AnchorPane page = loader.load();
			((LoginLayoutController) loader.getController()).setMainApp(this);
			stage.setScene(new Scene(page));
			stage.setResizable(false);
			stage.sizeToScene();
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showUpdatePwdLayout(String uno) {
		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("UpdatePwdLayout.fxml"));
			AnchorPane page = loader.load();
			Stage s = new Stage();
			s.setScene(new Scene(page));
			UpdatePwdLayoutController controller = loader.getController();
			controller.setUno(uno);
			controller.setStage(s);
			s.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showRootLayout(String name,String uno) {
		stage.close();
		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("RootLayout.fxml"));
			AnchorPane page = loader.load();
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
			controller.setName(name);
			controller.setUno(uno);
			stage.setScene(new Scene(page));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showAdminLayout(String name) {
		stage.close();
		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("AdminLayout.fxml"));
			AnchorPane page = loader.load();
			AdminLayoutController controller = loader.getController();
			controller.setMainApp(this);
			controller.setName(name);
			stage.setScene(new Scene(page));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
