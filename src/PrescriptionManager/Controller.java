package PrescriptionManager;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class Controller {

///////// Text Fields /////////////////////////
    @FXML
    TextField patientSearch;
    @FXML
    TextField patientLast;
    @FXML
    TextField patientFirst;
    @FXML
    TextField patientDOB;
    @FXML
    TextField drugSearch;
    @FXML
    TextField drugName;
    @FXML
    TextField drugStrength;
    @FXML
    TextField drugCount;

///////// TextAreas //////////////////////////
    @FXML
    TextArea drugInfo;

///////// List Views //////////////////////////
    @FXML
    ListView patientList;
    @FXML
    ListView drugList;

////////// Buttons //////////////////////////////
    @FXML
    Button patientAdd;
    @FXML
    Button patientDel;
    @FXML
    Button pscripAdd;
    @FXML
    Button pscripDel;
    @FXML
    Button drugAdd;
    @FXML
    Button drugDel;

    PatientRepository patientRepo;
    DrugRepository drugRepo;
    ObservableList<String> drugListContents;
    ObservableList<String> patientListContents;


    @FXML
    public void initialize() {
        try {
            drugRepo = new DrugRepository();
            drugListContents = drugRepo.getDrugList();
            drugList.setItems(drugListContents);
            patientRepo = new PatientRepository();
            patientListContents = patientRepo.getPatientList();
            patientList.setItems(patientListContents);
        } catch (ClassNotFoundException e) {
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setContentText(e.getMessage());
            err.show();
            e.printStackTrace();
        } catch (SQLException e) {
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setContentText(e.getMessage());
            err.show();
            e.printStackTrace();
        }
    }


    @FXML
    public void drugAdd() {
        // Create the custom dialog.
        Dialog<ArrayList<String>> dialog = new Dialog<>();
        dialog.setHeaderText("Add A Drug");

// Set the button types.
        ButtonType loginButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField name = new TextField();
        name.setPromptText("Atorvastatin");
        TextField strength = new TextField();
        strength.setPromptText("10mg");
        TextField count = new TextField();
        count.setPromptText("500");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(new Label("Strength:"), 0, 1);
        grid.add(strength, 1, 1);
        grid.add(new Label("Count:"), 0, 2);
        grid.add(count, 1, 2);

// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });
        strength.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });
        count.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the button by default.


// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                ArrayList<String> res = new ArrayList<>();
                res.add(name.getText());
                res.add(strength.getText());
                res.add(count.getText());
                return res;
            }
            return null;
        });

        Optional<ArrayList<String>> result = dialog.showAndWait();

        result.ifPresent(results -> {
            try {
                drugRepo.addDrug(results.get(0), results.get(1), Integer.parseInt(results.get(2)));
            } catch (SQLException e) {
                Alert err = new Alert(Alert.AlertType.ERROR);
                err.setContentText(e.getMessage());
                err.show();
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void patientAdd() {
        // Create the custom dialog.
        Dialog<ArrayList<String>> dialog = new Dialog<>();
        dialog.setHeaderText("Add A Patient");

// Set the button types.
        ButtonType loginButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField firstname = new TextField();
        firstname.setPromptText("John");
        TextField lastname = new TextField();
        lastname.setPromptText("Doe");
        TextField dob = new TextField();
        dob.setPromptText("MM/DD/YYYY");

        grid.add(new Label("First Name:"), 0, 0);
        grid.add(firstname, 1, 0);
        grid.add(new Label("Last Name:"), 0, 1);
        grid.add(lastname, 1, 1);
        grid.add(new Label("Date Of Birth:"), 0, 2);
        grid.add(dob, 1, 2);

// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        firstname.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });
        lastname.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });
        dob.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the button by default.


// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                ArrayList<String> res = new ArrayList<>();
                res.add(firstname.getText());
                res.add(lastname.getText());
                res.add(dob.getText());
                return res;
            }
            return null;
        });

        Optional<ArrayList<String>> result = dialog.showAndWait();

        result.ifPresent(results -> {
            try {
            	System.out.println(results.get(0));
            	System.out.println(results.get(1));
            	System.out.println(results.get(2));
                patientRepo.addPatient(results.get(0), results.get(1), results.get(2));
            } catch (SQLException e) {
                Alert err = new Alert(Alert.AlertType.ERROR);
                err.setContentText(e.getMessage());
                err.show();
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void drugSelect() {
        String selected = (String) drugList.getSelectionModel().getSelectedItem();
        String[] nameAndStrength = selected.split(" - ");
        if (selected != null) {
            try {
                int id = drugRepo.getDrugID(nameAndStrength[0], nameAndStrength[1]);
                drugCount.setText(Integer.toString(drugRepo.getCount(id)));
                drugName.setText(nameAndStrength[0]);
                drugStrength.setText(drugRepo.getStrength(id));
                drugInfo.setText(DrugDataFetcher.getUrlContents(nameAndStrength[0]));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            drugInfo.clear();
            drugCount.clear();
            drugName.clear();
            drugStrength.clear();
        }
    }


    @FXML
    public void drugDelete() {
        String selected = (String) drugList.getSelectionModel().getSelectedItem();
        String[] nameAndStrength = selected.split(" - ");
        if (selected != null) {
            try {
                int id = drugRepo.getDrugID(nameAndStrength[0], nameAndStrength[1]);
                drugInfo.clear();
                drugRepo.removeDrug(id);
                drugListContents.remove(selected);
                drugCount.clear();
                drugName.clear();
                drugStrength.clear();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
   
}
