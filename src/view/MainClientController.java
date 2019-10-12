package view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Client;
import model.ClientXML;
import model.Room;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;


public class MainClientController {
    Stage primaryStage;
    ObservableList<Client> clientList;

    @FXML
    TableView<Client> table;

    @FXML
    TextField FIO_add;

    @FXML
    TextField pas_add;
    @FXML
    TextField day_add;
    @FXML
    TextField room_add;
    @FXML
    TextField pr_add;

    @FXML
    TextArea rez;


    @FXML
    TableColumn<Client,String> FIOColumn;

    @FXML
    TableColumn<Client,Number> pasColumn;

    @FXML
    TableColumn<Client,Number> dayColumn;
    @FXML
    TableColumn<Client,String> numColumn;

    @FXML
    TableColumn<Client,Number> priceColumn;

    ObservableList<Room> roomList;

    private Stage dialogStage ;

    @FXML
    private void handleChange(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("editWin.fxml"));
            Pane page = loader.load();
            dialogStage = new Stage();
            dialogStage.setTitle("Редактирование данных");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            rez.setText("Запись готова для редактирования.");

            EditWinController controller = loader.getController();
            int editingIndex = table.getSelectionModel().getFocusedIndex();
            controller.initt(clientList,editingIndex, this, roomList);
            dialogStage.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
            rez.setText("Произошла ошибка: загрузка не выполнена.");
        }

    }

    public void close(){dialogStage.close();
        rez.setText("Редактирование завершено");}
    

    public void init(ObservableList<Client> clientList, ObservableList<Room> categoryList, Stage primaryStage){
        this.primaryStage = primaryStage;
        this.clientList = clientList;
        roomList = categoryList;
        table.setItems(clientList);
        FIOColumn.setCellValueFactory(cellData -> cellData.getValue().FIOProperty());
        pasColumn.setCellValueFactory(cellData -> cellData.getValue().pasProperty());
        dayColumn.setCellValueFactory(cellData -> cellData.getValue().DayProperty());
        numColumn.setCellValueFactory(cellData -> cellData.getValue().NumProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        priceColumn.setCellFactory(cellData -> new PriceCell(clientList));
    }

   @FXML
    private void handleSave(){
        ClientXML xml = new ClientXML(clientList);
        try {
            JAXBContext context = JAXBContext.newInstance(ClientXML.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(xml, new File("saveFile.xml"));
            rez.setText("Данные успешно сохранены");
        } catch (Exception e) { e.printStackTrace();
            rez.setText("Произошла ошибка: сохранение не выполнено. Проверьте наличие файла");}
    }
    @FXML
    private void handleLoad(){
        try {
            JAXBContext context = JAXBContext.newInstance(ClientXML.class);
            Unmarshaller um = context.createUnmarshaller();
            ClientXML xml = (ClientXML) um.unmarshal(new File("loadFile.xml"));
            clientList.clear();
            clientList.addAll(xml.getList());
            rez.setText("Данные успешно загружены");
        } catch (Exception e) { e.printStackTrace();
            rez.setText("Произошла ошибка: загрузка не выполнена. Проверьте наличие файла");}
    }

    @FXML
    private void handleAdd2(){
        try{

            String a = FIO_add.getText();
            Integer b = Integer.parseInt(pas_add.getText());
            Integer c = Integer.parseInt(day_add.getText());
            String d = room_add.getText();
            Float e = Float.parseFloat(pr_add.getText());
            clientList.add(new Client(a, b, c, d, e));
            rez.setText("Новый клиент добавлен");
        }
        catch (Exception e){
            e.printStackTrace();
            rez.setText("Новый клиент не добавлен. Возможно, вы заполнили не все поля. Попробуйте еще раз");
        }
    }

    @FXML
    private void handleDel() {
        try{
        if (table.isFocusTraversable()) {
            int index = table.getSelectionModel().getSelectedIndex();
            clientList.remove(clientList.get(index));
            rez.setText("Данные клиента успешно удалены");
        }
    }
         catch (Exception e){
            e.printStackTrace();
            rez.setText("Не удалось выселить клиента. Выберите нужную запись и попробуйте еще раз");
        }
    }

    @FXML
    private void handlePrint(){
        try {
            PrinterJob job = PrinterJob.createPrinterJob();
            Printer printer = job.getPrinter();
            PageLayout pageLayout = printer.getDefaultPageLayout();
            PageLayout newpageLayout = printer.createPageLayout(pageLayout.getPaper(), PageOrientation.REVERSE_LANDSCAPE, Printer.MarginType.DEFAULT);
            TableView g = new TableView();
            g.setItems(clientList);
            g.getColumns().addAll(FIOColumn, pasColumn, dayColumn, numColumn, priceColumn);
            VBox t = new VBox();
            Label cat = new Label("Список гостей");
            t.getChildren().addAll(cat, g);

            if (job != null)
                if (job.printPage(newpageLayout, t))
                    job.endJob();
            rez.setText("Документ для печати готов");
        }
        catch (Exception e){
            e.printStackTrace();
            rez.setText("Не удалось отправить на печать. Попробуйте еще раз");
        }

    }
}


