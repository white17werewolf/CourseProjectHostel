package view;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import model.Client;
import model.Room;

public class RoomCell extends TableCell<Client, String> {
    ObservableList<Room> catList;
    ObservableList<Client> exList;
    ComboBox box;
    Client exp;

    public RoomCell(ObservableList<Room> catList, ObservableList<Client> exList) {
        this.catList = catList;
        this.exList = exList;
        this.box = new ComboBox(catList);
    }

    @Override
    public void startEdit() {
        super.startEdit();
        exp = getTableView().getSelectionModel().getSelectedItem();
        box.setValue(catList.get(0));
        setGraphic(box);
    }

    @Override
    public void cancelEdit() {

        super.cancelEdit();
        int index = exList.indexOf(exp);
        try {
            exp.setNum(box.getValue().toString());
            exList.set(index, exp);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Ошибка");
        }

        setGraphic(null);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else
        if (isEditing()) {

            setGraphic(box);
        }
        else {

            setText(getItem().toString());
            setGraphic(null);

        }
    }
}