import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.jena.ontology.*;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.jena.update.UpdateAction;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;
import java.io.FileOutputStream;

//Оголошення класу та об’єктів, які будуть використовуватись в програмі:
public class OntologyApplication extends Application {
  private TextArea outputTextArea; // Оголошення об'єкту для виводу тексту
  private TextField addClassTextField; // Оголошення поля для введення нового класу
  private TextField addIndividualClassTextField; // Оголошення поля для введення класу, до якого буде доданий індивід
  private TextField addIndividualTextField; // Оголошення поля для введення нового індивіда
  private TextField individualTextField; // Оголошення поля для введення імені індивіда
  private TextField objectiveTextField; // Оголошення поля для введення hasObjective
  private TextField methodologyTextField; // Оголошення поля для введення hasMethodology
  private TextField dateTextField; // Оголошення поля для введення hasYear
  private OntModel model; // Оголошення онтологічної моделі
  
public static void main(String[] args) { launch(args); } // Запуск додатку

// Розробка інтерфейсу, кнопок та текстових полей:
public void start(Stage primaryStage) {
  primaryStage.setTitle("ЛАБ2_ДІ_Онтологія"); // Встановлює заголовок головного вікна додатку
  
  // Ініціалізація OntModel (онтологічна модель)
  model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
  
  // Створення компонентів користувацького інтерфейсу
  outputTextArea = new TextArea();// Текстова область для виводу інформації, недоступна для редагування
  outputTextArea.setEditable(false);
  
  // Завдання 1
  addClassTextField = new TextField();// Поле введення для додавання нового класу
  Button addClassButton = new Button("Add Class");// Кнопка для виклику методу додавання класу при натисканні
  addClassButton.setOnAction(e -> addClass());
  
  // Завдання 2
  addIndividualClassTextField = new TextField();// Поле введення для класу, до якого буде доданий індивід
  addIndividualTextField = new TextField();// Поле введення для додавання нового індивіда
  Button addIndividualButton = new Button("Add Individual");// Кнопка для виклику методу додавання індивіда при натисканні
  addIndividualButton.setOnAction(e -> addIndividual(addIndividualClassTextField.getText(), addIndividualTextField.getText()));
  
  // Завдання 3
  individualTextField = new TextField();// Поле введення для імені індивіда
  objectiveTextField = new TextField();// Поле введення для мети
  methodologyTextField = new TextField();// Поле введення для методології
  dateTextField = new TextField();// Поле введення для дати
  Button addDataButton = new Button("INSERT DATA");// Кнопка для виклику методу вставки даних при натисканні
  addDataButton.setOnAction(e -> executeInsertDataQuery(individualTextField.getText(), objectiveTextField.getText(),  methodologyTextField.getText(), dateTextField.getText()));
  
  // Налаштування розмітки (GridPane) для розташування компонентів
  GridPane gridPane = new GridPane();
  gridPane.setHgap(10);
  gridPane.setVgap(10);
  gridPane.setPadding(new Insets(10, 10, 10, 10));
  gridPane.add(outputTextArea, 0, 0, 2, 1);
  gridPane.add(new Label("Add Class:"), 0, 1);
  gridPane.add(addClassTextField, 1, 1);
  gridPane.add(addClassButton, 2, 1);
  gridPane.add(new Label("Add Individual to Class:"), 0, 2);
  gridPane.add(addIndividualClassTextField, 1, 2);
  gridPane.add(addIndividualTextField, 2, 2);
  gridPane.add(addIndividualButton, 3, 2);
  gridPane.add(new Label("Individual:"), 0, 4);
  gridPane.add(individualTextField, 1, 4);
  gridPane.add(new Label("Objective:"), 0, 5);
  gridPane.add(objectiveTextField, 1, 5);
  gridPane.add(new Label("Methodology:"), 0, 6);
  gridPane.add(methodologyTextField, 1, 6);
  gridPane.add(new Label("Date:"), 0, 7);
  gridPane.add(dateTextField, 1, 7);
  gridPane.add(addDataButton, 3, 3);
 
  // Налаштування сцени з використанням створеної розмітки
  Scene scene = new Scene(gridPane, 600, 400); // Розміри сцени 600x400 пікселів
  
  // Завантаження онтології
  loadOntology();
 
  // Налаштування головного вікна
  primaryStage.setScene(scene);
  primaryStage.show();
}  

//Завантаження онтології
private void loadOntology() {
  // Шлях до файлу онтології
  String ontologyFilePath = "C:/Users/Den4ik/Desktop/LAB1.rdf";
  try {
  // Завантаження онтології з файлу
  model.read(ontologyFilePath);
  
  // Виведення повідомлення про успішне завантаження
  outputTextArea.appendText("Ontology Loaded Successfully.\n");
  
  // Налаштування GenericRuleReasoner
  String rules = "[rule1: (?a rdf:type ?b) (?b rdf:type ?c) -> (?a rdf:type ?c)]"; // Додавання інших правил за необхідності
  GenericRuleReasoner reasoner = new GenericRuleReasoner(Rule.parseRules(rules));
  InfModel infModel = ModelFactory.createInfModel(reasoner, model);
 
  // Виведення інформації про резонер
  outputTextArea.appendText("Reasoner Information: GenericRuleReasoner\n");
  
  // Виклик validate() для перевірки онтології
  ValidityReport validityReport = infModel.validate();
  if (validityReport == null || validityReport.isValid()) {
 
  // Виведення повідомлення про успішну перевірку
  outputTextArea.appendText("Your ontology has been checked by the reasoner, and everything is okay.\n");} else {
  // Виведення повідомлення про невірну онтологію та відображення помилок валідації
  outputTextArea.appendText("Ontology is not valid.\n");
  outputTextArea.appendText("Validation Errors:\n" + validityReport);
 }
 } catch (Exception e) {
  // Виведення повідомлення про помилку при завантаженні
  outputTextArea.appendText("Failed to load ontology.\n");
  e.printStackTrace();
 }
}

//Кнопка «Add Class» (Завдання 1):
private void addClass(String className) {
  // Оновлення простору імен онтології
  String ontologyNamespace = "http://www.semanticweb.org/den4ik/ontologies/2023/11/lab1_di";
 // SPARQL-запит для додавання класу
  String sparqlQuery = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + "INSERT DATA {\n" + " <" /*+ ontologyNamespace*/ + className + "> rdf:type rdfs:Class\n" + "}";
  try {
    // Створення об'єкта для виконання запиту
    UpdateAction.parseExecute(sparqlQuery, model);
    // Збереження моделі у файл
    model.write(new FileOutputStream("C:/Users/Den4ik/Desktop/LAB1.rdf"));
    // Виведення повідомлення про успішне додавання класу та оновлення онтології
    outputTextArea.appendText("Class '" + className + "' successfully added and ontology updated.\n");
  } catch (Exception e) {
    // Виведення повідомлення про помилку
    outputTextArea.appendText("Failed to add class '" + className + "'.\n");
    e.printStackTrace();
   }
}

private void addClass() {
  String className = addClassTextField.getText(); // Отримання назви класу з текстового поля
  addClass(className); // Додавання класу до онтології за допомогою SPARQL
}

//Кнопка «Add Individual» (Завдання 2):
private void addIndividual(String className, String individualName) {
  // Оновлення простору імен онтології
  String ontologyNamespace = "http://www.semanticweb.org/den4ik/ontologies/2023/11/lab1_di";

  // SPARQL-запит для додавання індивіда до існуючого класу
  String sparqlQuery = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + "INSERT DATA {\n" + " <" + individualName + "> rdf:type <" + className + ">\n" + "}";
  try {
    // Створення об'єкта для виконання запиту
    UpdateAction.parseExecute(sparqlQuery, model);
    // Збереження моделі у файл
    model.write(new FileOutputStream("C:/Users/Den4ik/Desktop/LAB1.rdf"));
    // Виведення повідомлення про успішне додавання індивіда до класу та оновлення онтології
    outputTextArea.appendText("Individual '" + individualName + "' added to class '" + className + "' and ontology updated.\n");
   } catch (Exception e) {
    // Виведення повідомлення про помилку
    outputTextArea.appendText("Failed to add individual '" + individualName + "' to class '" + className + "'.\n");
    e.printStackTrace();
  }
}

//  Кнопка «INSERT DATA» (Завдання 3):
private void executeInsertDataQuery(String individual,String objective, String methodology, String date) {
  // Перевірка, чи всі поля заповнені
  if (individual.isEmpty() || objective.isEmpty() || methodology.isEmpty() || date.isEmpty()) {
    outputTextArea.appendText("Будь ласка, заповніть всі поля.\n");
    return;
  }
 
  // Підготовка SPARQL INSERT DATA запиту
  String insertDataQuery = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntaxns#>\n" + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + "PREFIX ex: <http://www.semanticweb.org/den4ik/ontologies/2023/11/lab1_di>\n" + "INSERT DATA {\n" + " ex:" + individual + " rdf:type ex:MarketResearchStudy ;\n" + " ex:hasObjective \"" + objective + "\" ;\n" + " ex:hasMethodology \"" + methodology + "\" ;\n" + " ex:hasYear \"" + date + "\" .\n" + "}";
  try {
    // Виконання SPARQL INSERT DATA запиту
    UpdateAction.parseExecute(insertDataQuery, model);
    // Збереження моделі у файл (за бажанням)
    model.write(new FileOutputStream("C:/Users/Den4ik/Desktop/LAB1.rdf"))
    // Виведення повідомлення про успішне виконання
    outputTextArea.appendText("Дані успішно додані до онтології.\n");
  } catch (Exception e) {
    // Виведення повідомлення про помилку
    outputTextArea.appendText("Не вдалося виконати запит INSERT DATA.\n");
    e.printStackTrace();
  }
}
