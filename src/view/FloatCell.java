package view;

import Control.FloatField;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import model.Client;

public class FloatCell extends TableCell<Client, Number> {
    FloatField number;
    ObservableList<Client> clientList;
    Client Client;

    public FloatCell(ObservableList<Client> clientList) {
        this.clientList = clientList;
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            number=new FloatField();
            Client = getTableView().getSelectionModel().getSelectedItem();
            number.setText(Client.priceProperty().getValue().toString());
            setGraphic(number);
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        int index=clientList.indexOf(Client);
        float newCost = Float.parseFloat(number.getText());
        Client newClient = new Client(Client.getFIO(),Client.getPas(), Client.getDay(), Client.getNum(), newCost);
        clientList.set(index, newClient);
        setGraphic(null);
    }

    @Override
    public void updateItem(Number item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else
        if (isEditing())
            setGraphic(number);
        else {
            setText(getItem().toString());
            setGraphic(null);
        }
    }
}

