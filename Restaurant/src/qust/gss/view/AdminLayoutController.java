package qust.gss.view;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import qust.gss.control.service.IAdminService;
import qust.gss.factory.ServiceFactory;
import qust.gss.model.Combo;
import qust.gss.model.Food;
import qust.gss.model.User;
import qust.gss.util.DialogUtil;
import qust.gss.util.ValidateUtil;

public class AdminLayoutController {
	private IAdminService adminService = ServiceFactory.getIAdminServiceInstance();
	private MainApp mainApp;
	@FXML
	private Label adminName;
	@FXML
	private BorderPane userListPane;
	@FXML
	private TableView<User> userTable;
	@FXML
	private TableColumn<User, String> userUnoCol;
	@FXML
	private TableColumn<User, String> userNameCol;
	@FXML
	private TableColumn<User, String> userPwdCol;
	@FXML
	private TableColumn<User, String> userRegDateCol;
	@FXML
	private Button userListBtn;

	@FXML
	private BorderPane userInsertPane;
	@FXML
	private Button userInsertBtn;
	@FXML
	private TextField userNameText;
	@FXML
	private Label unoMsg;
	@FXML
	private Label userNameMsg;
	@FXML
	private Label userPwdMsg;
	@FXML
	private TextField unoText;
	@FXML
	private TextField userPwdText;
	@FXML
	private Button userResetBtn;
	@FXML
	private Button userSubmitBtn;

	@FXML
	private BorderPane foodListPane;
	@FXML
	private Button foodListBtn;
	@FXML
	private TableView<Food> foodListTable;
	@FXML
	private TableColumn<Food, String> foodFidCol;
	@FXML
	private TableColumn<Food, String> foodNameCol;
	@FXML
	private TableColumn<Food, String> foodCategoryCol;
	@FXML
	private TableColumn<Food, Double> foodPriceCol;
	@FXML
	private TableColumn<Food, Integer> foodAmountCol;

	@FXML
	private BorderPane foodInsertPane;
	@FXML
	private Button foodInsertBtn;
	@FXML
	private TextField fidText;
	@FXML
	private TextField fnameText;
	@FXML
	private ChoiceBox<String> fcategoryChoice;
	@FXML
	private TextField fpriceText;
	@FXML
	private TextField famountText;
	@FXML
	private Label fidMsg;
	@FXML
	private Label fnameMsg;
	@FXML
	private Label fpriceMsg;
	@FXML
	private Label famountMsg;

	@FXML
	private BorderPane comboListPane;
	@FXML
	private Button comboListBtn;
	@FXML
	private TableView<Combo> comboListTable;
	@FXML
	private TableColumn<Combo, String> cidCol;
	@FXML
	private TableColumn<Combo, String> cnameCol;
	@FXML
	private TableColumn<Combo, Integer> stapleCol;
	@FXML
	private TableColumn<Combo, Integer> vegetablesCol;
	@FXML
	private TableColumn<Combo, Integer> meatCol;
	@FXML
	private TableColumn<Combo, Integer> gruelCol;
	@FXML
	private TableColumn<Combo, Double> cpriceCol;
	@FXML
	private BorderPane comboInsertPane;
	@FXML
	private Button comboInsertBtn;
	@FXML
	private TextField cidText;
	@FXML
	private ChoiceBox<Integer> stapleChoice;
	@FXML
	private ChoiceBox<Integer> vegetablesChoice;
	@FXML
	private ChoiceBox<Integer> meatChoice;
	@FXML
	private ChoiceBox<Integer> gruelChoice;
	@FXML
	private TextField cpriceText;
	@FXML
	private Label cidMsg;
	@FXML
	private Label cpriceMsg;
	@FXML
	private Button comboSubmitBtn;

	@FXML
	private BorderPane foodStatPane;
	@FXML
	private Button foodStatBtn;
	@FXML
	private TableView<Food> foodStatTable;
	@FXML
	private TableColumn<Food, String> fidStatCol;
	@FXML
	private TableColumn<Food, String> fnameStatCol;
	@FXML
	private TableColumn<Food, String> fcategoryStatCol;
	@FXML
	private TableColumn<Food, Integer> famountStatCol;
	@FXML
	private TableColumn<Food, Double> fpriceStatCol;
	@FXML
	private TableColumn<Food, Double> ftotalStatCol;
	@FXML
	private DatePicker beginDate;
	@FXML
	private DatePicker endDate;
	@FXML
	private ToggleButton dateQueryBtn;
	@FXML
	private ToggleButton dayQueryBtn;
	@FXML
	private ToggleButton monthQueryBtn;
	@FXML
	private ToggleButton yearQueryBtn;
	private ToggleGroup queryGroup;

	@FXML
	private BorderPane userStatPane;
	@FXML
	private Button userStatBtn;
	@FXML
	private TableView<User> userStatTable;
	@FXML
	private TableColumn<User, String> unoStatCol;
	@FXML
	private TableColumn<User, String> unameStatCol;
	@FXML
	private TableColumn<User, Double> upriceStatCol;

	@FXML
	private BorderPane comboStatPane;
	@FXML
	private Button comboStatBtn;
	@FXML
	private TableView<Combo> comboStatTable;
	@FXML
	private TableColumn<Combo, String> cidStatCol;
	@FXML
	private TableColumn<Combo, String> cnameStatCol;
	@FXML
	private TableColumn<Combo, Integer> camountStatCol;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setName(String name) {
		this.adminName.setText(name);
	}

	@FXML
	private void initialize() {

	}

	private void initUserInsert() {
		unoText.focusedProperty().addListener((observable, ov, nv) -> {
			if (!nv)
				validateUno();
		});
		userNameText.focusedProperty().addListener((observable, ov, nv) -> {
			if (!nv)
				validateUserName();

		});
		userPwdText.focusedProperty().addListener((observable, ov, nv) -> {
			if (!nv)
				validateUserPwd();
		});
	}

	private boolean validateUno() {
		if (ValidateUtil.validateEmpty(unoText.getText(), unoMsg)
				&& (ValidateUtil.validateRegex(unoText.getText(), "\\w+", unoMsg)))
			return true;
		return false;
	}

	private boolean validateUserName() {
		if (ValidateUtil.validateEmpty(userNameText.getText(), userNameMsg)
				&& ValidateUtil.validateRegex(userNameText.getText(), "[a-zA-Z\\u4e00-\\u9fa5]+", userNameMsg))
			return true;
		return false;

	}

	private boolean validateUserPwd() {
		if (ValidateUtil.validateEmpty(userPwdText.getText(), userPwdMsg)) {
			return true;
		}
		return false;
	}

	private boolean validateUser() {
		return validateUno() && validateUserName() && validateUserPwd();
	}

	private void initFoodInsert() {
		fidText.focusedProperty().addListener((observable, ov, nv) -> {
			if (!nv)
				validateFid();
		});
		fnameText.focusedProperty().addListener((observable, ov, nv) -> {
			if (!nv)
				validateFoodName();
		});
		famountText.focusedProperty().addListener((observable, ov, nv) -> {
			if (!nv)
				validateFoodAmount();
		});
		fpriceText.focusedProperty().addListener((observable, ov, nv) -> {
			if (!nv)
				validateFoodPrice();
		});
		fcategoryChoice.setItems(FXCollections.observableArrayList("主食", "素菜", "荤菜", "汤粥"));
	}

	class FoodCellEditCommit<T> implements EventHandler<CellEditEvent<Food, T>> {
		private String type;

		public FoodCellEditCommit(String type) {
			this.type = type;
		}

		@Override
		public void handle(CellEditEvent<Food, T> e) {
			Food food = e.getRowValue();
			String msg = null;
			switch (type) {
			case "name":
				msg = "食品名称";
				food.setName((String) e.getNewValue());
				break;
			case "amount":
				msg = "可售数量";
				food.setAmount((Integer) e.getNewValue());
				break;
			case "price":
				msg = "食品价格";
				food.setPrice((Double) e.getNewValue());
			}
			if (adminService.updateFood(food)) {
				DialogUtil.getDialog("提示", msg + "修改成功！").show();
			} else {
				DialogUtil.getDialog("提示", msg + "修改失败！").show();
			}
		}
	}

	private boolean validateFid() {
		if (ValidateUtil.validateEmpty(fidText.getText(), fidMsg)
				&& ValidateUtil.validateRegex(fidText.getText(), "\\d+", fidMsg))
			return true;
		return false;
	}

	private boolean validateFoodName() {
		if (ValidateUtil.validateEmpty(fnameText.getText(), fnameMsg)
				&& ValidateUtil.validateRegex(fnameText.getText(), "[a-zA-Z\\u4e00-\\u9fa5]+", fnameMsg))
			return true;
		return false;
	}

	private boolean validateFoodAmount() {
		if (ValidateUtil.validateEmpty(famountText.getText(), famountMsg)
				&& ValidateUtil.validateRegex(famountText.getText(), "\\d+", famountMsg))
			return true;
		return false;
	}

	private boolean validateFoodPrice() {
		if (ValidateUtil.validateEmpty(fpriceText.getText(), fpriceMsg)
				&& ValidateUtil.validateRegex(fpriceText.getText(), "\\d+(\\.\\d+)?", fpriceMsg))
			return true;
		return false;
	}

	private boolean validateFood() {
		return validateFid() && validateFoodName() && validateFoodAmount() && validateFoodPrice();
	}

	private void initComboInsert() {
		cidText.focusedProperty().addListener((o, ov, nv) -> {
			if (!nv)
				validateCid();
		});
		cpriceText.focusedProperty().addListener((o, ov, nv) -> {
			if (!nv)
				validateComboPrice();
		});
		stapleChoice.setItems(FXCollections.observableArrayList(0, 1, 2, 3));
		stapleChoice.setValue(0);
		vegetablesChoice.setItems(FXCollections.observableArrayList(0, 1, 2, 3));
		vegetablesChoice.setValue(0);
		meatChoice.setItems(FXCollections.observableArrayList(0, 1, 2, 3));
		meatChoice.setValue(0);
		gruelChoice.setItems(FXCollections.observableArrayList(0, 1, 2, 3));
		gruelChoice.setValue(0);
	}

	class ComboCellEditCommit<T> implements EventHandler<CellEditEvent<Combo, T>> {
		private String type;

		public ComboCellEditCommit(String type) {
			this.type = type;
		}

		@Override
		public void handle(CellEditEvent<Combo, T> e) {
			Combo combo = e.getRowValue();
			String msg = null;
			switch (type) {
			case "price":
				msg = "套餐价格";
				combo.setPrice((Double) e.getNewValue());
				break;
			case "staple":
				combo.setStaple((Integer) e.getNewValue());
				break;
			case "vegetables":
				combo.setVegetables((Integer) e.getNewValue());
				break;
			case "meat":
				combo.setMeat((Integer) e.getNewValue());
				break;
			case "gruel":
				combo.setGruel((Integer) e.getNewValue());
			}
			if (msg == null)
				msg = "套餐内容";
			if (adminService.updateCombo(combo)) {
				DialogUtil.getDialog("提示", msg + "更新成功！").show();
			} else {
				DialogUtil.getDialog("提示", msg + "更新失败！").show();
			}
		}
	}

	private boolean validateCid() {
		if (ValidateUtil.validateEmpty(cidText.getText(), cidMsg)
				&& ValidateUtil.validateRegex(cidText.getText(), "\\d+", cidMsg))
			return true;
		return false;
	}

	private boolean validateComboPrice() {
		if (ValidateUtil.validateEmpty(cpriceText.getText(), cpriceMsg)
				&& ValidateUtil.validateRegex(cpriceText.getText(), "\\d+(\\.\\d+)?", cpriceMsg))
			return true;
		return false;
	}

	private boolean validateCombo() {
		return validateCid() && validateComboPrice() && stapleChoice.getValue() != null
				&& vegetablesChoice.getValue() != null && meatChoice.getValue() != null
				&& gruelChoice.getValue() != null;
	}

	private void initUserTableFactory() {
		userUnoCol.setCellValueFactory(data -> data.getValue().unoProperty());
		userNameCol.setCellValueFactory(data -> data.getValue().nameProperty());
		userPwdCol.setCellValueFactory(data -> data.getValue().passwordProperty());
		userPwdCol.setCellFactory(TextFieldTableCell.forTableColumn());
		userPwdCol.setOnEditCommit(e -> {
			if (adminService.resetUserPwd(e.getRowValue().getUno(), e.getNewValue())) {
				DialogUtil.getDialog("提示", "员工密码修改成功！").show();
			} else {
				DialogUtil.getDialog("提示", "员工密码修改失败！").show();
			}
		});
		userRegDateCol.setCellValueFactory(new PropertyValueFactory<User, String>("regDate"));
		userTable.setItems(FXCollections.observableArrayList(adminService.findAllUser()));
	}

	private void initUserStatTableFactory() {
		unoStatCol.setCellValueFactory(data -> data.getValue().unoProperty());
		unameStatCol.setCellValueFactory(data -> data.getValue().nameProperty());
		upriceStatCol.setCellValueFactory(new PropertyValueFactory<User, Double>("price"));
		List<User> list = adminService.findSalesStatisticsByUser();
		if (list != null)
			userStatTable.setItems(FXCollections.observableArrayList(list));
	}

	private void initFoodTableFactory() {
		foodFidCol.setCellValueFactory(data -> data.getValue().fidProperty());
		foodNameCol.setCellValueFactory(data -> data.getValue().nameProperty());
		foodNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		foodNameCol.setOnEditCommit(new FoodCellEditCommit<String>("name"));
		foodCategoryCol.setCellValueFactory(data -> data.getValue().categoryProperty());
		foodPriceCol.setCellValueFactory(new PropertyValueFactory<Food, Double>("price"));
		foodPriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
			@Override
			public Double fromString(String string) {
				return Double.parseDouble(string);
			}

			@Override
			public String toString(Double object) {
				return String.valueOf(object);
			}
		}));
		foodPriceCol.setOnEditCommit(new FoodCellEditCommit<Double>("price"));
		foodAmountCol.setCellValueFactory(new PropertyValueFactory<Food, Integer>("amount"));
		foodAmountCol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
			@Override
			public Integer fromString(String string) {
				return Integer.parseInt(string);
			}

			@Override
			public String toString(Integer object) {
				return String.valueOf(object);
			}
		}));
		foodAmountCol.setOnEditCommit(new FoodCellEditCommit<Integer>("amount"));
		foodListTable.setItems(FXCollections.observableArrayList(adminService.findAllFood()));
	}

	private void initFoodStatTableFactory() {
		queryGroup = new ToggleGroup();
		queryGroup.getToggles().addAll(dateQueryBtn, dayQueryBtn, monthQueryBtn, yearQueryBtn);
		dayQueryBtn.setSelected(true);
		endDate.setValue(LocalDate.now());

		fidStatCol.setCellValueFactory(data -> data.getValue().fidProperty());
		fnameStatCol.setCellValueFactory(data -> data.getValue().nameProperty());
		fcategoryStatCol.setCellValueFactory(data -> data.getValue().categoryProperty());
		fpriceStatCol.setCellValueFactory(new PropertyValueFactory<Food, Double>("price"));
		famountStatCol.setCellValueFactory(new PropertyValueFactory<Food, Integer>("amount"));
		ftotalStatCol.setCellValueFactory(new PropertyValueFactory<Food, Double>("total"));
		// DateTimeFormatter.ofPattern("yyyy-MM-dd").format(beginDate.getValue())
		List<Food> list = adminService.findFoodStatistics(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
				new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		if (list != null)
			foodStatTable.setItems(FXCollections.observableArrayList(list));
	}

	private void initComboTableFactory() {
		cidCol.setCellValueFactory(data -> data.getValue().cidProperty());
		cnameCol.setCellValueFactory(data -> data.getValue().nameProperty());

		stapleCol.setCellValueFactory(new PropertyValueFactory<Combo, Integer>("staple"));
		stapleCol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
			@Override
			public String toString(Integer object) {
				return String.valueOf(object);
			}

			@Override
			public Integer fromString(String string) {
				return Integer.parseInt(string);
			}

		}));
		stapleCol.setOnEditCommit(new ComboCellEditCommit<Integer>("staple"));

		vegetablesCol.setCellValueFactory(new PropertyValueFactory<Combo, Integer>("vegetables"));
		vegetablesCol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
			@Override
			public String toString(Integer object) {
				return String.valueOf(object);
			}

			@Override
			public Integer fromString(String string) {
				return Integer.parseInt(string);
			}

		}));
		vegetablesCol.setOnEditCommit(new ComboCellEditCommit<Integer>("vegetables"));

		meatCol.setCellValueFactory(new PropertyValueFactory<Combo, Integer>("meat"));
		meatCol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
			@Override
			public String toString(Integer object) {
				return String.valueOf(object);
			}

			@Override
			public Integer fromString(String string) {
				return Integer.parseInt(string);
			}

		}));
		meatCol.setOnEditCommit(new ComboCellEditCommit<Integer>("meat"));

		gruelCol.setCellValueFactory(new PropertyValueFactory<Combo, Integer>("gruel"));
		gruelCol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
			@Override
			public String toString(Integer object) {
				return String.valueOf(object);
			}

			@Override
			public Integer fromString(String string) {
				return Integer.parseInt(string);
			}

		}));
		gruelCol.setOnEditCommit(new ComboCellEditCommit<Integer>("gruel"));

		cpriceCol.setCellValueFactory(new PropertyValueFactory<Combo, Double>("price"));
		cpriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
			@Override
			public String toString(Double object) {
				return String.valueOf(object);
			}

			@Override
			public Double fromString(String string) {
				return Double.parseDouble(string);
			}

		}));
		cpriceCol.setOnEditCommit(new ComboCellEditCommit<Double>("price"));

		comboListTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		List<Combo> list = adminService.findAllCombo();
		if (list != null)
			comboListTable.setItems(FXCollections.observableArrayList(list));
	}

	private void initComboStatTableFactory() {
		cidStatCol.setCellValueFactory(data -> data.getValue().cidProperty());
		cnameStatCol.setCellValueFactory(data -> data.getValue().nameProperty());
		camountStatCol.setCellValueFactory(new PropertyValueFactory<Combo, Integer>("amount"));

		List<Combo> list = adminService.findComboStatistics();
		if (list != null)
			comboStatTable.setItems(FXCollections.observableArrayList(list));
	}

	private void hideAllPane() {
		userListPane.setVisible(false);
		userInsertPane.setVisible(false);
		userStatPane.setVisible(false);

		foodListPane.setVisible(false);
		foodInsertPane.setVisible(false);
		foodStatPane.setVisible(false);

		comboListPane.setVisible(false);
		comboInsertPane.setVisible(false);
		comboStatPane.setVisible(false);
	}

	@FXML
	private void handleUserListBtn() {
		initUserTableFactory();
		hideAllPane();
		userListPane.setVisible(true);
	}

	@FXML
	private void handleUserInsertBtn() {
		initUserInsert();
		hideAllPane();
		userInsertPane.setVisible(true);
	}

	@FXML
	private void handleUserResetBtn() {
		unoText.setText("");
		userNameText.setText("");
		userPwdText.setText("");
	}

	@FXML
	private void handleUserSubmitBtn() {
		if (!validateUser()) {
			DialogUtil.getDialog("提示", "输入有误，请重新输入！").show();
			return;
		}
		if (adminService.insertUser(new User(unoText.getText(), userNameText.getText(), userPwdText.getText()))) {
			DialogUtil.getDialog("提示", "员工信息增加成功！").showAndWait();
			handleUserListBtn();
		} else {
			DialogUtil.getDialog("提示", "员工信息增加失败！").show();
		}
	}

	@FXML
	private void handleUserStatBtn() {
		initUserStatTableFactory();
		hideAllPane();
		userStatPane.setVisible(true);
	}

	@FXML
	private void handleFoodListBtn() {
		initFoodTableFactory();
		hideAllPane();
		foodListPane.setVisible(true);
	}

	@FXML
	private void handleFoodInsertBtn() {
		initFoodInsert();
		hideAllPane();
		foodInsertPane.setVisible(true);
	}

	@FXML
	private void handleFoodResetBtn() {
		fidText.setText("");
		fnameText.setText("");
		famountText.setText("");
		fpriceText.setText("");
	}

	@FXML
	private void handleFoodSubmitBtn() {
		if (!validateFood()) {
			DialogUtil.getDialog("提示", "输入有误，请重新输入！").show();
			return;
		}
		if (adminService.insertFood(new Food(fidText.getText(), fnameText.getText(), fcategoryChoice.getValue(),
				Integer.parseInt(famountText.getText()), Double.parseDouble(fpriceText.getText())))) {
			DialogUtil.getDialog("提示", "食品信息增加成功！").showAndWait();
			handleFoodListBtn();
		} else {
			DialogUtil.getDialog("提示", "食品信息增加失败！").show();
		}
	}

	@FXML
	private void handleFoodStatBtn() {
		initFoodStatTableFactory();
		hideAllPane();
		foodStatPane.setVisible(true);
	}

	@FXML
	private void handleFoodStatDateQueryBtn() {
		List<Food> list = adminService.findFoodStatistics(
				DateTimeFormatter.ofPattern("yyyy-MM-dd").format(beginDate.getValue()),
				DateTimeFormatter.ofPattern("yyyy-MM-dd").format(endDate.getValue()));
		if (list != null)
			foodStatTable.setItems(FXCollections.observableArrayList(list));
	}

	@FXML
	private void handleFoodStatDayQueryBtn() {
		List<Food> list = adminService.findFoodStatistics(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
				new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		if (list != null)
			foodStatTable.setItems(FXCollections.observableArrayList(list));
	}

	@FXML
	private void handleFoodStatMonthQueryBtn() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		List<Food> list = adminService.findFoodStatistics(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()),
				new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		if (list != null)
			foodStatTable.setItems(FXCollections.observableArrayList(list));
	}

	@FXML
	private void handleFoodStatYearQueryBtn() {
		List<Food> list = adminService.findFoodStatistics(
				(LocalDate.now().getYear() - 1) + "-" + new SimpleDateFormat("MM-dd").format(new Date()),
				new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		if (list != null)
			foodStatTable.setItems(FXCollections.observableArrayList(list));
	}

	@FXML
	private void handleComboListBtn() {
		initComboTableFactory();
		hideAllPane();
		comboListPane.setVisible(true);
	}

	@FXML
	private void handleComboDelBatch(KeyEvent e) {
		if (!e.getCode().equals(KeyCode.DELETE))
			return;
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("提示");
		dialog.setContentText("确定删除选中的套餐？");
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
		dialog.showAndWait();
		if (dialog.getResult().equals(ButtonType.YES)) {
			if (comboListTable.getSelectionModel().getSelectedItems().size() == 0) {
				DialogUtil.getDialog("提示", "您还未选中任何套餐！").show();
				return;
			}
			Set<String> cids = new HashSet<>();
			comboListTable.getSelectionModel().getSelectedItems().forEach(v -> cids.add(v.getCid()));
			if (adminService.removeBatch(cids)) {
				DialogUtil.getDialog("提示", "删除成功！").show();
				handleComboListBtn();
			} else
				DialogUtil.getDialog("提示", "删除失败！").show();
		}
	}

	@FXML
	private void handleComboInsertBtn() {
		initComboInsert();
		hideAllPane();
		comboInsertPane.setVisible(true);
	}

	@FXML
	private void handleComboSubmit() {
		if (!validateCombo()) {
			DialogUtil.getDialog("提示", "输入有误，请重新输入！").show();
			return;
		}
		if (adminService.insertCombo(new Combo(cidText.getText(), stapleChoice.getValue(), vegetablesChoice.getValue(),
				meatChoice.getValue(), gruelChoice.getValue(), Double.parseDouble(cpriceText.getText())))) {
			DialogUtil.getDialog("提示", "套餐添加成功！").showAndWait();
			handleComboListBtn();
		} else {
			DialogUtil.getDialog("提示", "套餐添加失败！").show();
		}
	}

	@FXML
	private void handleComboStatBtn() {
		initComboStatTableFactory();
		hideAllPane();
		comboStatPane.setVisible(true);
	}

	@FXML
	private void handleExitBtn() {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("提示");
		dialog.setContentText("确定退出本程序？");
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
		dialog.showAndWait();
		if (dialog.getResult().equals(ButtonType.YES))
			mainApp.showLoginLayout();
	}
}