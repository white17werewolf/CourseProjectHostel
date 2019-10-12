package view;

import Control.FloatField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import model.Room;
import model.Client;

public class EditWinController {
    ObservableList<Client> clientList;
    int editingIndex;

    @FXML
    TextField FIO;
    @FXML
    FloatField pas;
    @FXML
    FloatField day;
    @FXML
    FloatField price;
    @FXML
    ComboBox room;
    @FXML
    TextArea rez2;
    @FXML
    Label itog;


    private MainClientController controller;

    @FXML
    public void save_edit(){
        try {
            Client cl = new Client(FIO.getText(), Integer.parseInt(pas.getText()), Integer.parseInt(day.getText()), room.getValue().toString(), Float.parseFloat(price.getText()));
            clientList.set(editingIndex, cl);
            rez2.setText("Изменения сохранены.");
        }
        catch (Exception a)
        {
            a.printStackTrace();
            rez2.setText("Изменения не сохранены. Проверьте все ли поля заполнены и попробуйте еще раз.");
        }
        

    }
    @FXML
    public void cancel(){controller.close();
        rez2.setText("Изменения отменены");}

    @FXML
    public void cl(){controller.close();
        rez2.setText("Редактирование завершено");}

    public void initt(ObservableList<Client> clientList, int editingIndex, MainClientController controller, ObservableList<Room>roomList)
    {
        this.controller = controller;
        this.clientList = clientList;
        this.editingIndex = editingIndex;
        Client cl = clientList.get(editingIndex);
        FIO.setText(cl.getFIO());
        pas.setText(new Integer(cl.getPas()).toString());
        day.setText(new Integer(cl.getDay()).toString());
        room.setItems(roomList);
        price.setText(new Float(cl.getPrice()).toString());
        ch();
    }
    public void ch(){
        float all = 0;
        Float a = Float.parseFloat(day.getText());
        Float b = Float.parseFloat(price.getText());
        all=a*b;
        itog.setText("Счёт: "+ all + " руб.");
    }

    @FXML
    private void handlePrint_pers(){
try{
        PrinterJob job = PrinterJob.createPrinterJob();
        Printer printer = job.getPrinter();
        PageLayout pageLayout = printer.getDefaultPageLayout();
        PageLayout newpageLayout = printer.createPageLayout(pageLayout.getPaper(), PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
        String a = FIO.getText();
        String b = pas.getText();
        String c = day.getText();
        String e = price.getText();
        VBox t = new VBox();
        Label title = new Label("Персональный чек");
        title.setFont(Font.font(30));

        Label a1 = new Label("ФИО клиента: "+a);
        Label b1 = new Label("Паспортные данные: "+b);
        Label c1 = new Label("Количество дней: "+c);
        Label e1 = new Label("Цена: "+e);
        t.getChildren().addAll(title,a1,b1,c1,e1, itog);

        if (job != null)
            if (job.printPage(newpageLayout,t))
                job.endJob();
        rez2.setText("Чек для печати готов");
    }
        catch (Exception e){
        e.printStackTrace();
        rez2.setText("Не удалось отправить на печать. Попробуйте еще раз");
    }
    }
}
