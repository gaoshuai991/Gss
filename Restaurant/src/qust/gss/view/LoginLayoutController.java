package qust.gss.view;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import qust.gss.factory.ServiceFactory;
import qust.gss.util.DialogUtil;

public class LoginLayoutController {
	private MainApp mainApp;
	@FXML
	private TextField userName;
	@FXML
	private PasswordField userPwd;
	@FXML
	private RadioButton user;
	@FXML
	private RadioButton admin;
	@FXML
	private ToggleGroup idGroup;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void initialize() {
		idGroup = new ToggleGroup();
		idGroup.getToggles().addAll(user, admin);
		idGroup.selectToggle(user);
		userName.setFocusTraversable(false);
		userPwd.addEventFilter(KeyEvent.KEY_RELEASED, e -> {
			if (e.getCode().equals(KeyCode.ENTER))
				handleSubmitBtn();
		});
	}

	@FXML
	private void handleSubmitBtn() {
		String name = userName.getText();
		String pwd = userPwd.getText();
		if (name.equals("") || pwd.equals("")) {
			DialogUtil.getDialog("提示", "用户名或密码不能为空！").show();
		} else {
			if (idGroup.getSelectedToggle().equals(admin)) {
				String aname = null;
				if ((aname = ServiceFactory.getIAdminServiceInstance().login(name, pwd)) != null) {
					mainApp.showAdminLayout(aname);
				}else{
					DialogUtil.getDialog("提示", "用户名或密码错误！").show();
				}
			} else {
				String uname = null;
				if ((uname = ServiceFactory.getIUserServiceInstance().login(name, pwd)) != null) {
					mainApp.showRootLayout(uname,name);
				}else{
					DialogUtil.getDialog("提示", "用户名或密码错误！").show();
				}
			}
		}
	}
	@FXML
	private void handleExitBtn(){
		System.exit(0);
	}

}
