package qust.gss.view;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import qust.gss.factory.ServiceFactory;
import qust.gss.util.DialogUtil;

public class UpdatePwdLayoutController {
	private String uno;
	private Stage stage;
	@FXML
	private PasswordField oldPwd;
	@FXML
	private PasswordField newPwd;
	@FXML
	private PasswordField confirmPwd;

	@FXML
	private void initialize() {
		confirmPwd.addEventFilter(KeyEvent.KEY_RELEASED, e -> {
			if (e.getCode().equals(KeyCode.ENTER))
				handleSubmitBtn();
		});
	}

	public void setUno(String uno) {
		this.uno = uno;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	private void handleSubmitBtn() {
		if (oldPwd.getText().equals("") || newPwd.getText().equals("") || confirmPwd.getText().equals("")) {
			DialogUtil.getDialog("提示", "请把内容填写完整！").show();
		} else {
			if (ServiceFactory.getIUserServiceInstance().login(uno, oldPwd.getText()) != null) {
				if (newPwd.getText().equals(confirmPwd.getText())
						&& ServiceFactory.getIUserServiceInstance().updatePwd(uno, newPwd.getText())) {
					DialogUtil.getDialog("提示", "密码修改成功！").showAndWait();
					this.stage.close();
				}else{
					DialogUtil.getDialog("提示", "两次密码不相同！").show();
				}
			} else {
				DialogUtil.getDialog("提示", "旧密码错误，修改失败！").show();
			}
		}
	}

	@FXML
	private void handleExitBtn() {
		this.stage.close();
	}
}
