package view;

import Control.FloatField;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import model.Client;


public class PriceCell extends TableCell<Client, Number> {
    FloatField price;
    ObservableList<Client> clientList;
    Client client;

    public PriceCell(ObservableList<Client> clientList) {
        this.clientList = clientList;
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            price=new FloatField();
            client = getTableView().getSelectionModel().getSelectedItem();
            price.setText(client.priceProperty().getValue().toString());
            setGraphic(price);
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        int index=clientList.indexOf(client);
        float newPrice = Float.parseFloat(price.getText());
        Client newClient = new Client(client.getFIO(), client.getPas(), client.getDay(),client.getNum(), newPrice );
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
            setGraphic(price);
        else {
            setText(getItem().toString());
            setGraphic(null);
        }
    }
}

