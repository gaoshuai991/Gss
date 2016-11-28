package qust.gss.util;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class DialogUtil {
	/**
	 * 创建一个简单的对话框，只有确定按钮
	 * @param title
	 * @param contentText
	 * @param buttonTypes
	 * @return
	 */
	public static Dialog<?> getDialog(String title,String contentText){
		Dialog<?> dialog = new Dialog<>();
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
		dialog.setTitle(title);
		dialog.setContentText(contentText);
		return dialog;
	}
}
