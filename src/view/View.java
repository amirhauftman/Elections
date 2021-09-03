package view;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;
import controller.Controller;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import listeners.ListenerView;
import model.BadInputException;
import model.Company;
import model.Department;
import model.Employee;
import model.Role;

public class View implements AbstractView {

	private ArrayList<ListenerView> allListener = new ArrayList<>();
	private ArrayList<Department> allDepartmentList = new ArrayList<>();

	private final Stage stage;
	private Scene theScene ;

	private BorderPane window;
	private Group root;
	private Menu departmentMenu, roleMenu, employeeMenu, showCompanyMenu;
	private MenuBar bar;
	private ObservableList<MenuItem> listMenu;
	private TableView<Department> departmentTable;
	private ComboBox<String> clistOfeemp;
	private ComboBox<String> clistdep;
	private ComboBox<String> clistOfroles;
	private ComboBox<String> clistOfPref;
	private ComboBox<String> clistOfworkerType;
	private Button submit;
	private CheckBox syncronize;
	private TextField tfID;
	private TextField tfName;
	private BarChart<String, Number> barGraph;
	private BorderPane bp,secondaryScreen;


	public View(Stage primaryStage) { //menu 
		this.stage = primaryStage;
		root = new Group();
		bar = new MenuBar();
		departmentMenu = (new Menu("department"));
		roleMenu = new Menu("role");
		employeeMenu = new Menu("employee");
		showCompanyMenu = new Menu("show company");
		listMenu = departmentMenu.getItems(); // get the items from the depmenu and save to the list
		// department
		MenuItem miAddDepartment = new MenuItem("add Department"); // add dep
		miAddDepartment.setOnAction(e -> addDepartmentView());
		listMenu.add(miAddDepartment);
		clistdep = new ComboBox<>();

		MenuItem miShowDepartment = new MenuItem("show department"); // show dep
		miShowDepartment.setOnAction(e -> showDepartmentView());
		listMenu.add(miShowDepartment);
		// listMenu.get(1).setDisable(true);

		MenuItem miShowDepartmentProfit = new MenuItem("show profit"); // show dep profit
		miShowDepartmentProfit.setOnAction(e -> showDepartmentProfitView());
		listMenu.add(miShowDepartmentProfit);
		listMenu = roleMenu.getItems();
		// role
		MenuItem miAddrole = new MenuItem("add role"); // add to role
		miAddrole.setOnAction(e -> addRoleView());
		listMenu.add(miAddrole);

		MenuItem miShowrole = new MenuItem("show role"); // show role
		miShowrole.setOnAction(e -> showRoleView());
		listMenu.add(miShowrole);

		MenuItem miShowProfit = new MenuItem("show profit"); // show role profit
		miShowProfit.setOnAction(e -> showRoleProfittoView());
		listMenu.add(miShowProfit);
		listMenu = employeeMenu.getItems();
		// employee;
		MenuItem miAddemployee = new MenuItem("add employee"); // add to employee
		miAddemployee.setOnAction(e -> addEmployeeView());
		listMenu.add(miAddemployee);

		MenuItem miShowemployee = new MenuItem("show employee"); // show employee
		miShowemployee.setOnAction(e -> showEmployeeView());
		listMenu.add(miShowemployee);

		MenuItem miShowemployeeProfit = new MenuItem("show profit"); // show employee profit
		miShowemployeeProfit.setOnAction(e -> showEmployeeProfitToView());
		listMenu.add(miShowemployeeProfit);
		listMenu = showCompanyMenu.getItems();

		ObservableList<Menu> barMenus = bar.getMenus();
		barMenus.addAll(departmentMenu, roleMenu, employeeMenu);
		window = new BorderPane();
		window.setCenter(root);
		window.setTop(bar);
		theScene = new Scene(window, 640, 480);
		this.stage.setScene(theScene);
		this.stage.setTitle("my company");
		this.stage.show();
	}

	public void exitButtonScreen(Company company) {
		bp = new BorderPane();
		VBox vbExit = new VBox();
		vbExit.getChildren().addAll();
		bp.setCenter(vbExit);
		this.stage.setScene(new Scene(bp, 400, 400));
		this.stage.show();
	}

	public void addDepartmentView() {
		root.getChildren().clear();
		Text name = new Text("Name: ");
		tfName = new TextField();
		syncronize = new CheckBox("syncronize");
		
		clistOfPref = new ComboBox<>();
		clistOfPref.setValue("Choose perference");
		clistOfPref.getItems().addAll("Change Nothing", "Work From Home", "Start Early", "Start Late");

		submit = new Button("Submit");

		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				for (ListenerView l : allListener)
					l.addDepartmentFromView(tfName.getText(), syncronize.isSelected(), clistOfPref.getItems().indexOf(clistOfPref.getValue()));

				tfName.setText("");
			}
		});
		HBox hbName = new HBox();
		hbName.getChildren().addAll(name, tfName);
		HBox hbSyncronize = new HBox();
		hbSyncronize.getChildren().add(syncronize);

		VBox vb = new VBox();
		vb.getChildren().addAll(hbName, hbSyncronize, clistOfPref, submit);
		root.getChildren().add(vb);
	}

	private void checkSubmitReady() {
		submit.setDisable(clistdep.getItems().indexOf(clistdep.getValue()) == -1);
	}

	public void addEventToSyncronizeCheckBox(EventHandler<ActionEvent> event) {
		syncronize.setOnAction(event);
	}

	public void addEventToAddDepartment(EventHandler<ActionEvent> event) {
		departmentMenu.getItems().get(0).setOnAction(event);
	}

	public void addEventToShowDepartment(EventHandler<ActionEvent> event) {
		departmentMenu.getItems().get(1).setOnAction(event);
	}

	@Override
	public void registerListener(Controller controller) {
		allListener.add(controller);
	}

	public TextField getIDField() {
		return tfID;
	}

	public TextField getTfName() {
		return tfName;
	}

	@Override
	public void addDeprtmantToView(Department department) {
		allDepartmentList.add(department);
		addDepartmentToCombobox(department);
	}

	private void addDepartmentToCombobox(Department department) {
		clistdep.getItems().add(department.getId() + "," + department.getName()); 
	}
	public void addEventToAddCDep(EventHandler <ActionEvent> event   ) {
		clistdep.setOnAction(event);

	} 

	public void addRoleView() {
		root.getChildren().clear();
		Text name = new Text("Name: ");
		tfName = new TextField();
		syncronize = new CheckBox("syncronize");
		clistOfroles = new ComboBox<>();
		clistOfPref.setValue("Choose perference");
		//clistdep.getItems();
	
		submit = new Button("Submit");
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				for (ListenerView l : allListener)
					l.addRoleFromView(tfName.getText(), syncronize.isSelected(), clistOfPref.getItems().indexOf(clistOfPref.getValue()), clistdep.getItems().indexOf(clistdep.getValue()));
				tfName.setText("");
			}
		});
		HBox hbName = new HBox();
		hbName.getChildren().addAll(name, tfName);
		HBox hbSyncronize = new HBox();
		hbSyncronize.getChildren().add(syncronize);

		//clistdep = new ComboBox<>();
		HBox hbdep = new HBox();
		Label depL = new Label("Choose department: ");
		//for (Department department : allDepartmentList) {
		//		clistdep.getItems().add(department.getId(), department.getName());
		//}
		// String depID=clistPdep.getValue().split(",")[0];
		hbdep.getChildren().addAll(depL, clistdep);
		VBox vb = new VBox();
		vb.getChildren().addAll(hbName, hbSyncronize, clistOfPref, hbdep, submit);
		root.getChildren().add(vb);
	}

	private void checkSubmitReadyDepartment() {
		submit.setDisable(clistdep.getItems().indexOf(clistdep.getValue()) == -1);
	}

	private void checkSubmitReadyroles() {
		submit.setDisable(clistOfroles.getItems().indexOf(clistOfroles.getValue()) == -1);
	}

	public void addEventToSyncronizeCheckBoxroles(EventHandler<ActionEvent> event) {
		syncronize.setOnAction(event);
	}

	@Override
	public void addRoleToView(Role role) {
		allDepartmentList.get(0).addRole(role);
		addRoleToCombobox(role);
	}

	private void addRoleToCombobox(Role role) {
		clistOfroles.getItems().add(role.getName());
	}

	public void addEmployeeView() {
		root.getChildren().clear();
		Text id = new Text("ID: ");
		tfID = new TextField();
		Text name = new Text("Name: ");
		tfName = new TextField();
		syncronize = new CheckBox("syncronize");
		submit = new Button("Submit");

		//preferce
		Label hbpref = new Label("Choose prefernce: ");
		clistOfPref.getItems();

		//employee
		clistOfeemp = new ComboBox<>();

		// listoftype
		clistOfworkerType = new ComboBox<>();
		clistOfworkerType.setValue("Choose kind of worker");
		clistOfworkerType.getItems().addAll("EmployeeBaseRate", "EmployeeHourly", "EmployeeSales");

		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				try {
					for (ListenerView l : allListener)
						l.addEmployeeFromView(tfName.getText(), syncronize.isSelected(), clistOfPref.getItems().indexOf(clistOfPref.getValue()),
								Integer.parseInt(tfID.getText()),clistdep.getItems().indexOf(clistdep.getValue()) ,clistOfroles.getItems().indexOf(clistOfroles.getValue()),clistOfworkerType.getValue());
				} catch (NumberFormatException nfe) {
					wrongInputMessage("id must cotntain only numbers");
				} catch (BadInputException badInput) {
					wrongInputMessage("id wrong!");
					return;
				}
				// String depID=clistdep.getValue().split(",")[0];

				// l.addEmployeeFromView(tfName.getText(), syncronize.isSelected(),
				// clistOfPref.getValue(), Integer.parseInt(depID));
				tfID.setText("");
				tfName.setText("");
			}
		});
		HBox hbName = new HBox();
		HBox hbID = new HBox();
		hbID.getChildren().addAll(id, tfID);
		hbName.getChildren().addAll(name, tfName);

		HBox hbSyncronize = new HBox();// no need
		hbSyncronize.getChildren().add(syncronize);// no need

		HBox hbdep = new HBox();
		Label depL = new Label("Choose department: "); // list of dep
		clistdep.getItems();
		hbdep.getChildren().addAll(depL, clistdep);
		/*
		 * for (Department department : allDepartmentList) {
		 * clistdep.getItems().add(department.getId(),department.getName()); }
		 */

		//role
		HBox hbrole = new HBox();
		Label roleL = new Label("Choose role: ");
		hbrole.getChildren().addAll(roleL, clistOfroles);


		clistdep.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				for (ListenerView l : allListener) {
					clistOfroles.getItems().clear();
					clistOfroles.getItems().addAll(l.getRolesFromModel(clistdep.getItems().indexOf(clistdep.getValue())));
				}
			}
		});
		clistdep.fireEvent(new ActionEvent());


		/*for (Role role : allDepartmentList.get(0).getRoles()) { // clistofrole
			clistOfroles.getItems().add(role.getIdRole(), role.getName());
		}*/
		VBox vb = new VBox();
		vb.getChildren().addAll(hbID, hbName, hbSyncronize, hbpref, clistOfPref, clistOfworkerType, hbdep, clistdep,
				hbrole, clistOfroles, submit);
		root.getChildren().add(vb);
	}

	public void wrongInputMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	private void checkSubmitReadyeemp() {
		submit.setDisable(clistOfeemp.getItems().indexOf(clistOfeemp.getValue()) == -1);
	}

	public void addEventToSyncronizeCheckBoxeemp(EventHandler<ActionEvent> event) {
		syncronize.setOnAction(event);
	}
	//	@Override
	//	public void addEmployeeToView(Role<? extends Employee> role ,Employee employee) {
	//			allDepartmentList.get(0).addRole(null);
	//	}

	public Stage getStage() {
		return stage;
	}

	public Scene getTheScene() {
		return theScene;
	}

	public BorderPane getWindow() {
		return window;
	}

	public Group getRoot() {
		return root;
	}

	public Menu getDepartmentMenu() {
		return departmentMenu;
	}

	public Menu getRoleMenu() {
		return roleMenu;
	}

	public Menu getEmployeeMenu() {
		return employeeMenu;
	}

	public Menu getShowCompanyMenu() {
		return showCompanyMenu;
	}

	public MenuBar getBar() {
		return bar;
	}

	public ObservableList<MenuItem> getListMenu() {
		return listMenu;
	}

	public TableView<Department> getDepartmentTable() {
		return departmentTable;
	}

	public Button getSubmit() {
		return submit;
	}

	public CheckBox getSyncronize() {
		return syncronize;
	}

	public TextField getTfID() {
		return tfID;
	}

	public BarChart<String, Number> getBarGraph() {
		return barGraph;
	}

	public void showDepartmentView() {
		root.getChildren().clear();
		submit = new Button("Submit");
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				for (ListenerView l : allListener)
					l.addRoleFromView(null, false, 0, 0);;

					tfName.setText("");
			}
		});
		Label titleL = new Label("the departments are: \n " + allDepartmentList.toString());
		// Label showL = new Label("the departments are: ");
		VBox vb = new VBox();
		vb.getChildren().addAll(titleL);
		root.getChildren().add(vb);
	}

	public void showRoleView() {
		root.getChildren().clear();
		submit = new Button("Submit");
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				for (ListenerView l : allListener)
					l.addRoleFromView(null, false, 0, 0);

				tfName.setText("");
			}
		});

		Label[] label = new Label[allDepartmentList.size()];
		for (int i = 0; i < allDepartmentList.size(); i++) {
			if (allDepartmentList.get(i)!=null) {
				label[i] = new Label();
				label[i].setText(allDepartmentList.get(i).getRoles().toString());

			}
		}

		//Label titleL = new Label("the roles are: " + allDepartmentList.toString());

		VBox vb = new VBox();
		vb.getChildren().addAll(label);
		root.getChildren().add(vb);
	}

	public void showEmployeeView() {
		root.getChildren().clear();
		submit = new Button("Submit");
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				for (ListenerView l : allListener)
					l.addRoleFromView(null, false, 0, 0);

				tfName.setText("");
			}
		});

		ArrayList<Label> label = new ArrayList<Label>(allDepartmentList.size());
		for (int i = 0; i < allDepartmentList.size(); i++) {
			if (allDepartmentList.get(i)!=null) {
				label.add(new Label());
				for (int j = 0; j < allDepartmentList.get(i).getRoles().size(); j++) {
					if (allDepartmentList.get(i).getRoles()!=null) {
						label.add(new Label(allDepartmentList.get(i).getRoles().get(j).getEmployees().toString()));
	
					}
				}
			}
		}
		
		VBox vb = new VBox();
		vb.getChildren().addAll(label);
		root.getChildren().add(vb);
	}

	public void showDepartmentProfitView() {
		root.getChildren().clear();
		submit = new Button("Submit");
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				for (ListenerView l : allListener)
					l.addRoleFromView(null, false, 0, 0);

				tfName.setText("");
			}
		});

		Label[] label = new Label[allDepartmentList.size()];
		for (int i = 0; i < allDepartmentList.size(); i++) {
			if (allDepartmentList.get(i)!=null) {
				label[i] = new Label();
				label[i].setText(allDepartmentList.get(i).printDRes());
			}
		}

		
		VBox vb = new VBox();
		vb.getChildren().addAll(label);
		root.getChildren().add(vb);
	}

	public void showRoleProfittoView() {
		root.getChildren().clear();
		submit = new Button("Submit");
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				for (ListenerView l : allListener)
					l.addRoleFromView(null, false, 0, 0);

				tfName.setText("");
			}
		});
		ArrayList<Label> label = new ArrayList<Label>(allDepartmentList.size());
		for (int i = 0; i < allDepartmentList.size(); i++) {
			if (allDepartmentList.get(i)!=null) {
				label.add(new Label());
				for (int j = 0; j < allDepartmentList.get(i).getRoles().size(); j++) {
					if (allDepartmentList.get(i).getRoles()!=null) {
						label.add(new Label(allDepartmentList.get(i).getRoles().get(j).printRRes()));
	
					}
				}
			}
		}


		VBox vb = new VBox();
		vb.getChildren().addAll(label);
		root.getChildren().addAll(vb);
	}

	public void showEmployeeProfitToView() {
		root.getChildren().clear();
		submit = new Button("Submit");
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				for (ListenerView l : allListener)
					l.addRoleFromView(null, false, 0, 0);

				tfName.setText("");
			}
		});
	//	Label titleL = new Label(
		//		"the employees results are: " );
		ArrayList<Label> label = new ArrayList<Label>(allDepartmentList.size());
		for (int i = 0; i < allDepartmentList.size(); i++) {
			if (allDepartmentList.get(i)!=null) {
				label.add(new Label());
				for (int j = 0; j < allDepartmentList.get(i).getRoles().size(); j++) {	
					if (allDepartmentList.get(i).getRoles()!=null) {
						label.add(new Label());
						for (int k = 0; k <  allDepartmentList.get(i).getRoles().get(j).getEmployees().size(); k++) {
							if (allDepartmentList.get(i).getRoles().get(j).getEmployees()!=null) {
								label.add(new Label(allDepartmentList.get(i).getRoles().get(j).getEmployees().get(k).printERes()));
								//label.setText(allDepartmentList.get(i).getRoles().get(j).getEmployees().get(k).printERes());
							}
						}
					}
				}
			}
		}

		VBox vb = new VBox();
		vb.getChildren().addAll(label);
		root.getChildren().add(vb);
	}

	@Override
	public void addEmployeeToView(Employee employee) {
		allDepartmentList.get(0).getRoles().addAll(0, null);
		addEmployeeToCombobox(employee);
	}

	private void addEmployeeToCombobox(Employee employee) {
		clistOfeemp.getItems().addAll(employee.getName());
	}


	/*@Override
	public FileOutputStream getStg() {		
		return stage;
	}*/
}