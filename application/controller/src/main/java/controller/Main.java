package controller;

import java.io.IOException;

import dao.TransactionRepository;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Transaction;
import util.DBaseUtil;

public class Main extends Application {

    private ObservableList<Transaction> transactionData = FXCollections.observableArrayList();
    private Stage primaryStage;
    private BorderPane rootLayout;
    TransactionRepository transRepo = new TransactionRepository(DBaseUtil.getConnection());

    public Main() {
        transRepo.getAll().stream().forEach(transaction -> transactionData.add(transaction));
        // Add some sample data
   }

    public ObservableList<Transaction> getTransactionData() {
        return transactionData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showTransactionOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the transaction overview inside the root layout.
     */


    public boolean showTransactionEditDialog(Transaction transaction, String button) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/TransactionEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Transaction");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the transaction into the controller.
            TransactionEditDialogController controller = loader.getController();
            controller.setButtonPressed(button);
            controller.setDialogStage(dialogStage);
            controller.setTransaction(transaction);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showTransactionOverview() {
        try {
            // Load transaction overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/TransactionOverview.fxml"));
            AnchorPane transactionOverview = (AnchorPane) loader.load();

            // Set transaction overview into the center of root layout.
            rootLayout.setCenter(transactionOverview);

            // Give the controller access to the main app.
            TransactionOverviewController controller = loader.getController();

            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}