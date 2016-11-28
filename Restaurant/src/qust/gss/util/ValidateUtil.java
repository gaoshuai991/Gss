package qust.gss.util;

import javafx.scene.control.Label;

public class ValidateUtil {
	public static boolean validateEmpty(String text, Label label) {
		if (text.equals("") || text == null) {
			label.setText("内容不能为空！");
			return false;
		}
		label.setText("");
		return true;
	}

	public static boolean validateRegex(String text, String regex, Label label) {
		if (!text.matches(regex)) {
			label.setText("内容格式不正确！");
			return false;
		}
		label.setText("");
		return true;
	}
}
