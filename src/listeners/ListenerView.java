package listeners;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.control.ComboBox;
import model.BadInputException;

public interface ListenerView {

	 void addDepartmentFromView(String name, boolean isSync ,int prefernce );
	 void addRoleFromView(String name, boolean isSync ,int preference , int id);
	 void addEmployeeFromView(String name, boolean isSync, int preference, int id, int depIndex, int roleIndex,
			String workerType) throws BadInputException;
	 ArrayList<String> getRolesFromModel(int depIndex);
	 
}
