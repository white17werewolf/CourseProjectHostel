import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Client;
import model.Room;
import view.MainClientController;


public class Main extends Application {

    ObservableList<Client> clientList = FXCollections.observableArrayList();
    ObservableList<Room> roomList = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        testInit();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/mainWin.fxml"));
        Parent root = loader.load();
        MainClientController controller = loader.getController();
        controller.init(clientList, roomList, primaryStage);
        primaryStage.setTitle("Хостел");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    private void testInit(){
        roomList.add(new Room("1"));
        roomList.add(new Room("2"));
        roomList.add(new Room("3"));
        roomList.add(new Room("4"));
        roomList.add(new Room("5"));
        roomList.add(new Room("6"));

        clientList.add(new Client("Иванов", new Integer(5210), new Integer(10), "1", new Float(1000)));
        clientList.add(new Client("Петров", new Integer(5211), new Integer(10), "2", new Float(1000)));
        clientList.add(new Client("Сидоров", new Integer(5214), new Integer(10), "3", new Float(1000)));
        clientList.add(new Client("Пушкин", new Integer(5251), new Integer(10), "4", new Float(1000)));

    }
}
