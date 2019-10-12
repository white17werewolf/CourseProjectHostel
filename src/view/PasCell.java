/*package view;

import Control.FloatField;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import model.Client;


public class PasCell extends TableCell<Client, Number> {
    FloatField pas;
    ObservableList<Client> clientList;
    Client client;

    public PasCell(ObservableList<Client> clientList) {
        this.clientList = clientList;
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            pas=new FloatField();
            client = getTableView().getSelectionModel().getSelectedItem();
            pas.setText(client.pasProperty().getValue().toString());
            setGraphic(pas);
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        int index=clientList.indexOf(client);
        int newPas = Integer.parseInt(pas.getText());
        Client newClien = new Client(client.getFIO(), newPas, client.getDay(),client.getNum(), client.getPrice());
        clientList.set(index, newClien);
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
            setGraphic(pas);
        else {
            setText(getItem().toString());
            setGraphic(null);
        }
    }
}

*/