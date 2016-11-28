package qust.gss.view;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import qust.gss.control.service.IUserService;
import qust.gss.factory.ServiceFactory;
import qust.gss.model.Combo;
import qust.gss.model.ComboSales;
import qust.gss.model.Food;
import qust.gss.model.FoodBoughtBean;
import qust.gss.util.DialogUtil;

public class RootLayoutController {
	public IUserService userService = ServiceFactory.getIUserServiceInstance();
	private MainApp mainApp;
	private String currentUno;
	@FXML
	private Label userName;
	@FXML
	private TableView<Food> stapleTable;
	@FXML
	private TableView<Food> vegetablesTable;
	@FXML
	private TableView<Food> meatTable;
	@FXML
	private TableView<Food> gruelTable;

	@FXML
	private TableColumn<Food, String> stapleIdTableCol;
	@FXML
	private TableColumn<Food, String> vegetablesIdTableCol;
	@FXML
	private TableColumn<Food, String> meatIdTableCol;
	@FXML
	private TableColumn<Food, String> gruelIdTableCol;

	@FXML
	private TableColumn<Food, String> stapleNameTableCol;
	@FXML
	private TableColumn<Food, String> vegetablesNameTableCol;
	@FXML
	private TableColumn<Food, String> meatNameTableCol;
	@FXML
	private TableColumn<Food, String> gruelNameTableCol;

	@FXML
	private TableColumn<Food, Double> staplePriceTableCol;
	@FXML
	private TableColumn<Food, Double> vegetablesPriceTableCol;
	@FXML
	private TableColumn<Food, Double> meatPriceTableCol;
	@FXML
	private TableColumn<Food, Double> gruelPriceTableCol;

	@FXML
	private TableColumn<Food, Integer> stapleCountTableCol;
	@FXML
	private TableColumn<Food, Integer> vegetablesCountTableCol;
	@FXML
	private TableColumn<Food, Integer> meatCountTableCol;
	@FXML
	private TableColumn<Food, Integer> gruelCountTableCol;

	@FXML
	private TableColumn<Food, String> stapleBtnTableCol;
	@FXML
	private TableColumn<Food, String> vegetablesBtnTableCol;
	@FXML
	private TableColumn<Food, String> meatBtnTableCol;
	@FXML
	private TableColumn<Food, String> gruelBtnTableCol;

	@FXML
	private TableView<FoodBoughtBean> boughtTable;
	public ObservableSet<String> boughtNameSet = FXCollections.observableSet();
	public ObservableSet<FoodBoughtBean> boughtFoodBeanSet = FXCollections.observableSet();
	public ObservableList<FoodBoughtBean> boughtFoodBeanList = FXCollections.observableArrayList(boughtFoodBeanSet);

	@FXML
	private TableColumn<FoodBoughtBean, String> boughtNameCol;
	@FXML
	private TableColumn<FoodBoughtBean, Double> boughtPriceCol;
	@FXML
	private TableColumn<FoodBoughtBean, Integer> boughtCountCol;
	@FXML
	private TableColumn<FoodBoughtBean, Double> boughtTotalCol;

	@FXML
	private Label sumPrice;
	private double sumPriceTemp;

	@FXML
	private Button decreaseBtn;
	@FXML
	private Button increaseBtn;
	@FXML
	private Button delBtn;
	@FXML
	private Button clearBtn;
	@FXML
	private Button payBtn;

	@FXML
	private TableView<Combo> comboTable;
	@FXML
	private TableColumn<Combo, String> comboCidCol;
	@FXML
	private TableColumn<Combo, String> comboNameCol;
	@FXML
	private TableColumn<Combo, Double> comboPriceCol;
	@FXML
	private TableColumn<Combo, String> comboChoiceCol;
	private StringProperty currentCid = new SimpleStringProperty();
	private ToggleGroup toggleGroup = new ToggleGroup();
	private Integer[] currentCombo;
	private Map<String, Integer> categoryDict;
	private boolean comboLock = false;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setName(String name) {
		this.userName.setText(name);
	}

	public void setUno(String uno) {
		this.currentUno = uno;
	}

	@FXML
	private void initialize() {
		categoryDict = new HashMap<>();
		categoryDict.put("主食", 0);
		categoryDict.put("素菜", 1);
		categoryDict.put("荤菜", 2);
		categoryDict.put("汤粥", 3);
		initFoodListCellValueFactory();
		initFoodBoughtCellValueFactory();
		initComboListCellValueFactory();
	}

	private void initFoodListCellValueFactory() {
		stapleIdTableCol.setCellValueFactory(cellData -> cellData.getValue().fidProperty());
		stapleNameTableCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		staplePriceTableCol.setCellValueFactory(new PropertyValueFactory<Food, Double>("price"));
		stapleCountTableCol.setCellValueFactory(new PropertyValueFactory<Food, Integer>("amount"));

		vegetablesIdTableCol.setCellValueFactory(cellData -> cellData.getValue().fidProperty());
		vegetablesNameTableCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		vegetablesPriceTableCol.setCellValueFactory(new PropertyValueFactory<Food, Double>("price"));
		vegetablesCountTableCol.setCellValueFactory(new PropertyValueFactory<Food, Integer>("amount"));

		meatIdTableCol.setCellValueFactory(cellData -> cellData.getValue().fidProperty());
		meatNameTableCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		meatPriceTableCol.setCellValueFactory(new PropertyValueFactory<Food, Double>("price"));
		meatCountTableCol.setCellValueFactory(new PropertyValueFactory<Food, Integer>("amount"));

		gruelIdTableCol.setCellValueFactory(cellData -> cellData.getValue().fidProperty());
		gruelNameTableCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		gruelPriceTableCol.setCellValueFactory(new PropertyValueFactory<Food, Double>("price"));
		gruelCountTableCol.setCellValueFactory(new PropertyValueFactory<Food, Integer>("amount"));

		Callback<TableColumn<Food, String>, TableCell<Food, String>> buyBtnFactory = new Callback<TableColumn<Food, String>, TableCell<Food, String>>() {
			@Override
			public TableCell<Food, String> call(TableColumn<Food, String> param) {
				final TableCell<Food, String> cell = new TableCell<Food, String>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (!empty) {
							final Button buyBtn = new Button("购买");
							buyBtn.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									param.getTableView().getSelectionModel().select(getIndex());
									Food item = null;
									if (stapleTable.getSelectionModel().getSelectedItem() != null)
										item = stapleTable.getSelectionModel().getSelectedItem();
									else if (vegetablesTable.getSelectionModel().getSelectedItem() != null)
										item = vegetablesTable.getSelectionModel().getSelectedItem();
									else if (meatTable.getSelectionModel().getSelectedItem() != null)
										item = meatTable.getSelectionModel().getSelectedItem();
									else
										item = gruelTable.getSelectionModel().getSelectedItem();

									if (item.getAmount() == 0)
										DialogUtil.getDialog("Error", "当前可购买数量不足").show();
									else {
										// 加入已买食品列表
										if (updateBoughtTable(item))
											item.setAmount(item.getAmount() - 1);
										updateSumPrice();
									}
									boughtTable.refresh();
								}
							});
							setGraphic(buyBtn);
							setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
						}
					};
				};
				return cell;
			}

		};
		stapleBtnTableCol.setCellFactory(buyBtnFactory);
		vegetablesBtnTableCol.setCellFactory(buyBtnFactory);
		meatBtnTableCol.setCellFactory(buyBtnFactory);
		gruelBtnTableCol.setCellFactory(buyBtnFactory);

		// boughtTable.getItems().add(new Food("1002","你好","素食",2,2.0));

		stapleTable.setItems(FXCollections.observableArrayList(userService.findAllFoodByCategory("主食")));
		vegetablesTable.setItems(FXCollections.observableArrayList(userService.findAllFoodByCategory("素菜")));
		meatTable.setItems(FXCollections.observableArrayList(userService.findAllFoodByCategory("荤菜")));
		gruelTable.setItems(FXCollections.observableArrayList(userService.findAllFoodByCategory("汤粥")));

	}

	private void initFoodBoughtCellValueFactory() {
		boughtNameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		boughtPriceCol.setCellValueFactory(new PropertyValueFactory<FoodBoughtBean, Double>("price"));
		boughtCountCol.setCellValueFactory(new PropertyValueFactory<FoodBoughtBean, Integer>("amount"));
		boughtTotalCol.setCellValueFactory(new PropertyValueFactory<FoodBoughtBean, Double>("total"));
		boughtTable.setItems(boughtFoodBeanList);
	}

	private void initComboListCellValueFactory() {
		comboCidCol.setCellValueFactory(cellData -> cellData.getValue().cidProperty());
		comboNameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		comboPriceCol.setCellValueFactory(new PropertyValueFactory<Combo, Double>("price"));
		Callback<TableColumn<Combo, String>, TableCell<Combo, String>> choiceColFactory = new Callback<TableColumn<Combo, String>, TableCell<Combo, String>>() {
			@Override
			public TableCell<Combo, String> call(TableColumn<Combo, String> param) {
				final TableCell<Combo, String> cell = new TableCell<Combo, String>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setText("");
							setGraphic(null);
						} else {
							final ToggleButton toggle = new ToggleButton("选择");
							toggle.setUserData(comboTable.getItems().get(getIndex()).getCid());
							toggle.setToggleGroup(toggleGroup);
							toggle.selectedProperty().addListener((observable, ov, nv) -> {
								if (nv != null) {
									param.getTableView().getSelectionModel().select(getIndex());
									Combo comboBean = comboTable.getSelectionModel().getSelectedItem();
									currentCid.set(comboBean.getCid());
									currentCombo = new Integer[] { comboBean.getStaple(), comboBean.getVegetables(),
											comboBean.getMeat(), comboBean.getGruel() };
									comboLock = true;
									sumPrice.setText(String.valueOf(comboBean.getPrice()));
								}
							});
							setGraphic(toggle);
							setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
						}

					}
				};
				return cell;
			}

		};
		comboChoiceCol.setCellFactory(choiceColFactory);

		comboTable.setItems(FXCollections.observableArrayList(userService.findAllCombo()));
	}

	// 找出不能选的套餐
	private List<String> findComboInBought() {
		if (boughtFoodBeanSet.size() == 0) {
			return null;
		}
		return userService.findUnchoicableCombo(findBoughtCategoryCount());
	}

	// 在套餐列表进行过滤
	private void comboListFilter() {
		if (toggleGroup.getSelectedToggle() != null)
			return;
		comboTable.setItems(FXCollections.observableArrayList(userService.findAllCombo()));
		if (findComboInBought() != null) {
			Iterator<String> itera = findComboInBought().iterator();

			while (itera.hasNext()) {
				String cid = itera.next();
				Iterator<Combo> iterb = comboTable.getItems().iterator();
				int i = 0, temp = -1;
				while (iterb.hasNext()) {
					if (iterb.next().getCid().equals(cid)) {
						temp = i;
						break;
					}
					i++;
				}
				if (temp != -1)
					comboTable.getItems().remove(temp);
			}
		}
	}

	// 购买食品之后对已买列表的更新
	private boolean updateBoughtTable(Food food) {
		boolean flag = true;
		boolean temp = true;
		if (toggleGroup.getSelectedToggle() == null)
			comboLock = false;
		int preSize = boughtNameSet.size();
		boughtNameSet.add(food.getName());
		String itemName = food.getName();
		if (boughtNameSet.size() == preSize) {
			if (boughtFoodBeanSet.size() != 0) {
				Iterator<FoodBoughtBean> iter = boughtFoodBeanSet.iterator();
				while (iter.hasNext()) {
					FoodBoughtBean item = iter.next();
					if (item.getName().equals(itemName)) {
						if (!comboLock) {
							item.setAmount(item.getAmount() + 1);
						} else if (findBoughtCategoryCount()
								.get(item.getCategory()) < currentCombo[categoryDict.get(item.getCategory())]) {
							item.setAmount(item.getAmount() + 1);
						} else
							flag = false;
					}
				}
			} else {
				flag = false;
			}
		} else {
			if (!comboLock) {
				boughtFoodBeanSet
						.add(new FoodBoughtBean(food.getFid(), food.getName(), food.getCategory(), 1, food.getPrice()));
			} else if (findBoughtCategoryCount()
					.get(food.getCategory()) < currentCombo[categoryDict.get(food.getCategory())]) {
				boughtFoodBeanSet
						.add(new FoodBoughtBean(food.getFid(), food.getName(), food.getCategory(), 1, food.getPrice()));
			} else {
				temp = false;
				flag = false;
			}
		}
		boughtFoodBeanList.clear();
		boughtFoodBeanList.addAll(boughtFoodBeanSet);
		updateSumPrice();
		comboListFilter();
		if (!temp)
			boughtNameSet.remove(food.getName());
		boughtTable.refresh();
		return flag;
	}

	private Map<String, Integer> findBoughtCategoryCount() {
		Map<String, Integer> map = new HashMap<>();
		map.put("主食", 0);
		map.put("素菜", 0);
		map.put("荤菜", 0);
		map.put("汤粥", 0);
		boughtTable.getItems().forEach(item -> {
			map.replace(item.getCategory(), map.get(item.getCategory()) + item.getAmount());
		});
		return map;
	}

	private void updateSumPrice() {
		if (!comboLock) {
			sumPriceTemp = 0;
			boughtTable.getItems().forEach(t -> sumPriceTemp += t.getTotal());
			sumPrice.setText(String.valueOf(sumPriceTemp));
			boughtTable.refresh();
		}
	}

	@FXML
	private void handleStapleMouseExits() {
		stapleTable.getSelectionModel().clearSelection();
	}

	@FXML
	private void handleVegetablesMouseExits() {
		vegetablesTable.getSelectionModel().clearSelection();
	}

	@FXML
	private void handleMeatMouseExits() {
		meatTable.getSelectionModel().clearSelection();
	}

	@FXML
	private void handleGruelMouseExits() {
		gruelTable.getSelectionModel().clearSelection();
	}

	// 处理按钮事件
	@FXML
	private void handleDecreaseBtn() {
		FoodBoughtBean foodBean = boughtTable.getSelectionModel().getSelectedItem();
		if (foodBean == null) {
			DialogUtil.getDialog("Error", "未选中任何食品！").show();
		} else {
			if (foodBean.getAmount() == 1) {
				boughtFoodBeanSet.remove(foodBean);
				boughtNameSet.remove(foodBean.getName());
				boughtFoodBeanList.clear();
				boughtFoodBeanList.addAll(boughtFoodBeanSet);
			} else
				foodBean.setAmount(foodBean.getAmount() - 1);
			backFoodUtil(foodBean, 1);
			updateSumPrice();
			comboListFilter();
		}
	}

	private boolean backFoodTable(FoodBoughtBean foodBean, TableView<Food> tableview, int num) {
		Iterator<Food> iter = tableview.getItems().iterator();
		while (iter.hasNext()) {
			Food food = iter.next();
			if (food.getName().equals(foodBean.getName())) {
				if (num < 0 && food.getAmount() == 0)
					return false;
				food.setAmount(food.getAmount() + num);
				return true;
			}
		}
		return false;
	}

	private void backFoodUtil(FoodBoughtBean foodBean, int num) {
		String category = foodBean.getCategory();
		if (category.equals("主食"))
			backFoodTable(foodBean, stapleTable, num);
		else if (category.equals("素菜"))
			backFoodTable(foodBean, vegetablesTable, num);
		else if (category.equals("荤菜"))
			backFoodTable(foodBean, meatTable, num);
		else
			backFoodTable(foodBean, gruelTable, num);
	}

	@FXML
	private void handleIncreaseBtn() {
		FoodBoughtBean foodBean = boughtTable.getSelectionModel().getSelectedItem();
		if (foodBean == null) {
			DialogUtil.getDialog("Error", "未选中任何食品！").show();
		} else {
			String category = foodBean.getCategory();
			boolean flag = false;
			if (category.equals("主食"))
				flag = backFoodTable(foodBean, stapleTable, 1);
			else if (category.equals("素菜"))
				flag = backFoodTable(foodBean, vegetablesTable, 1);
			else if (category.equals("荤菜"))
				flag = backFoodTable(foodBean, meatTable, 1);
			else
				flag = backFoodTable(foodBean, gruelTable, 1);
			if (flag)
				foodBean.setAmount(foodBean.getAmount() + 1);
			updateSumPrice();
			comboListFilter();
		}
	}

	@FXML
	private void handleDelBtn() {
		FoodBoughtBean foodBean = boughtTable.getSelectionModel().getSelectedItem();
		if (foodBean == null) {
			DialogUtil.getDialog("Error", "未选中任何食品！").show();
		} else {
			backFoodUtil(foodBean, foodBean.getAmount());
			boughtFoodBeanSet.remove(foodBean);
			boughtNameSet.remove(foodBean.getName());
			boughtFoodBeanList.clear();
			boughtFoodBeanList.addAll(boughtFoodBeanSet);
			updateSumPrice();
			comboListFilter();
		}
	}

	@FXML
	private void handleClearBtn() {
		Iterator<FoodBoughtBean> iter = boughtTable.getItems().iterator();
		while (iter.hasNext()) {
			FoodBoughtBean foodBean = iter.next();
			backFoodUtil(foodBean, foodBean.getAmount());
		}
		boughtNameSet.clear();
		boughtFoodBeanSet.clear();
		boughtFoodBeanList.clear();
		boughtTable.getItems().clear();
		updateSumPrice();
		comboListFilter();
	}

	@FXML
	private void handlePayBtn() {
		boolean isCombo = toggleGroup.getSelectedToggle() != null;
		boolean flag = true;
		if (!isCombo && boughtTable.getItems().size() == 0) {
			DialogUtil.getDialog("提示", "尚未选择任何食品！").show();
			return;
		}
		if (boughtTable.getItems().size() != 0) {
			Iterator<FoodBoughtBean> iter = boughtTable.getItems().iterator();
			while (iter.hasNext()) {
				FoodBoughtBean item = iter.next();
				if (!userService.saleFood(currentUno, isCombo ? 0.0 : Double.parseDouble(sumPrice.getText()),
						item.getFid(), item.getAmount())) {
					flag = false;
				}
			}
		}
		if (isCombo && !userService
				.saleCombo(new ComboSales(currentCid.get(), currentUno, 1, Double.parseDouble(sumPrice.getText())))) {
			flag = false;
		}
		if (flag) {
			DialogUtil.getDialog("提示", "结账成功！").show();
			boughtNameSet.clear();
			boughtFoodBeanSet.clear();
			boughtFoodBeanList.clear();
			updateSumPrice();
			comboListFilter();
			boughtTable.refresh();
		} else {
			DialogUtil.getDialog("提示", "结账失败！").show();
		}

	}

	@FXML
	private void handleUpdatePwdBtn() {
		mainApp.showUpdatePwdLayout(currentUno);
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
