package appFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import Hierarchi.Animal;
import Hierarchi.Archaea;
import Hierarchi.Eubacteria;
import Hierarchi.Mushroom;
import Hierarchi.Organism;
import Hierarchi.Plant;
import Hierarchi.Virus;


public class Controller implements Initializable {
	
	private Organism organism;
	final ToggleGroup group = new ToggleGroup();
    private ArrayList<TextField> textFields = new ArrayList<TextField>();
    private ObservableList<Object> listOfOrganisms = FXCollections.observableArrayList();
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
        textFields.add(nameTextField);
        textFields.add(familyTextField);
        textFields.add(cellsAmountTextField);
        try {
            try {
				listOfOrganisms = FXCollections.observableArrayList(Arrays.asList(deserialize()));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch (IOException e) {
            listOfOrganisms.add(new Plant("Green", "low"));
            listOfOrganisms.add(new Mushroom("Mush", "short"));
            listOfOrganisms.add(new Virus("Vir", "3", "us"));
            listOfOrganisms.add(new Eubacteria("Bac", "3", "teria"));
        }

        plantButton.setToggleGroup(group);
        mushroomButton.setToggleGroup(group);
        archaeaButton.setToggleGroup(group);
        animalButton.setToggleGroup(group);
        virusButton.setToggleGroup(group);
        eubacteriaButton.setToggleGroup(group);


        listView.setItems(listOfOrganisms);

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    organism = (Organism) listView.getSelectionModel().getSelectedItem();
                    getFields(organism);
                }
        );
		
	}

    @FXML
    private TextField nameTextField = new TextField();
    @FXML
    private TextField familyTextField = new TextField();
    @FXML
    private TextField cellTextField = new TextField();
    @FXML
    private TextField cellsAmountTextField = new TextField();



    @FXML
    private ListView listView = new ListView();

    @FXML
    private Label firstLabel = new Label();
    @FXML
    private Label secondLabel = new Label();


    @FXML
    RadioButton animalButton = new RadioButton();
    @FXML
    RadioButton mushroomButton = new RadioButton();
    @FXML
    RadioButton plantButton = new RadioButton();
    @FXML
    RadioButton eubacteriaButton = new RadioButton();
    @FXML
    RadioButton archaeaButton = new RadioButton();
    @FXML
    RadioButton virusButton = new RadioButton();
    
	
    @FXML
    private void addToList() throws IllegalArgumentException, IllegalAccessException {
        boolean  IN_LIST_ALREADY = false;
    	if (organism != null) {
            setFields(organism);
            for (Object org : listOfOrganisms){
            	if (org == organism){
            		IN_LIST_ALREADY = true;
            	}
            }
            if (!IN_LIST_ALREADY)
            	listOfOrganisms.add(organism);
            getFields(organism);
        }

    }
    
    @FXML
    private void delete() {
        listOfOrganisms.remove(listView.getSelectionModel().getSelectedIndex());

    }
	
    @FXML
    private void clickPlant() {
        clearFields();
        organism = new Plant("default", "default");
        cellsAmountTextField.setDisable(true);
        }

    @FXML
    private void clickAnimal() {
        clearFields();
        organism = new Animal("default", "default");
        cellsAmountTextField.setDisable(true);
    }
    
    @FXML
    private void clickVirus() {
        clearFields();
        organism = new Virus("default", "0", "default");
        cellsAmountTextField.setDisable(false);
    }
    
    @FXML
    private void clickArchaea() {
        clearFields();
        organism = new Archaea("default", "0");
        cellsAmountTextField.setDisable(true);
    }
    
    @FXML
    private void clickMushroom() {
        clearFields();
        organism = new Mushroom("default", "default");
        cellsAmountTextField.setDisable(true);
    }
    
    @FXML
    private void clickEubacteria() {
        clearFields();
        organism = new Eubacteria("default", "0", "default");
        cellsAmountTextField.setDisable(false);
    }
    
    
    private void getFields(Organism old) {
        clearFields();
        Class reflectionClass = old.getClass();
        ArrayList<Field> fields = new ArrayList<Field>();
        fields.addAll(Arrays.asList(reflectionClass.getDeclaredFields()));

        while (reflectionClass.getSuperclass() != null) {
            reflectionClass = reflectionClass.getSuperclass();
            fields.addAll(Arrays.asList(reflectionClass.getDeclaredFields()));
        }
        int i = fields.size() - 1;
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                textFields.get(i).setText((String) field.get(old));
                i--;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void setFields(Organism old) throws IllegalArgumentException, IllegalAccessException {

        Class reflectionClass = old.getClass();
        ArrayList<Field> fields = new ArrayList<Field>();
        fields.addAll(Arrays.asList(reflectionClass.getDeclaredFields()));

        while (reflectionClass.getSuperclass() != null) {
            reflectionClass = reflectionClass.getSuperclass();
            fields.addAll(Arrays.asList(reflectionClass.getDeclaredFields()));
        }
        int i = fields.size() - 1;
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                field.set(old, (String) textFields.get(i).getText());
                i--;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        clearFields();
    }


    private void clearFields() {
        for (TextField field : textFields) {
            field.clear();
        }
    }
    
    
    private Object[] deserialize() throws IOException, ClassNotFoundException {
		  FileInputStream fis = new FileInputStream("E:/Java/HP/Serialization/temp.out");
		  Object[] list = null;
		  ObjectInputStream oin = new ObjectInputStream(fis);
		  list = (Object[]) oin.readObject();
		  return list;
    }

    @FXML
    private void serialize() throws IOException {
		  FileOutputStream fos = new FileOutputStream("E:/Java/HP/Serialization/temp.out");
		  ObjectOutputStream oos = new ObjectOutputStream(fos);
		  oos.writeObject(listOfOrganisms.toArray());
		  oos.flush();
		  oos.close();
    	
    }
    
}