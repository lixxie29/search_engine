package gui;

import domain.Document;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import service.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @FXML
    private Button Button;

    @FXML
    private CheckBox checkbox;

    @FXML
    private TextArea contentField;

    @FXML
    private TextField keyField;

    @FXML
    private ListView<String> listView;

    @FXML
    private TextField textField;


    @FXML
    protected void populateList() {
        try {
            ArrayList<Document> Documents = service.getAll();
            var sortedDocuments = Documents.stream().sorted(Comparator.comparing(report-> report.getName())).collect(Collectors.toList());
            for (Document Document : sortedDocuments) {
                listView.getItems().add(Document.toString());
            }
        }catch (Exception exception) { exception.printStackTrace();}
    }



    @FXML
    void searchText(ActionEvent event) {
        listView.getItems().clear();
        String filter_term2 = textField.getText();
        List<Document> filteredDocuments;
        try {
            ArrayList<Document> Documents = service.getAll();
            if(checkbox.isSelected()){
                filteredDocuments = Documents.stream().filter(w -> w.getName().contains(filter_term2)).collect(Collectors.toList());
            }else {
                filteredDocuments = Documents.stream().filter(w -> w.getKeywords().contains(filter_term2)).collect(Collectors.toList());
            }
            for(Document Document : filteredDocuments){listView.getItems().add(Document.toString());}
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void getListViewValue(MouseEvent event) {
        String[] descr = listView.getSelectionModel().getSelectedItem().split("'");
        keyField.setText(descr[3]);
        contentField.setText(descr[5]);
    }

    @FXML
    void updateDocument(ActionEvent event) {
        String[] descr = listView.getSelectionModel().getSelectedItem().split("'");
        String name = descr[1];
        listView.getItems().clear();

        String newKey = keyField.getText();
        String newContent = contentField.getText();

        service.UpdateSchema(newKey, newContent,name);
        populateList();
    }


    @FXML
    public void initialize(){
        try {
//            service.createSchema();
//            service.AddinSchema();
            populateList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
