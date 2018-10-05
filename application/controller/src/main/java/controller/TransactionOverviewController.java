package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Transaction;
import service.TransactionService;
import util.DateUtil;

public class TransactionOverviewController {
    
    @FXML
    private TableView<Transaction> transactionTable;
    @FXML
    private TableColumn<Transaction, String> transactionColumn;
    @FXML
    private TableColumn<Transaction, String> salesRepColumn;



    @FXML
    private Label transactionNoLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Label salesRepLabel;
    @FXML
    private Label amountLabel;

    private TransactionService transactionService = new TransactionService();

    // Reference to the main application.
    private Main mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public TransactionOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the transaction table with the two columns.
        transactionColumn.setCellValueFactory(cellData -> cellData.getValue().transactionIdProperty());
        salesRepColumn.setCellValueFactory(cellData -> cellData.getValue().salesRepProperty());

        showTransactionDetails(null);

        transactionTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showTransactionDetails(newValue));
    }


    private void showTransactionDetails(Transaction transaction) {
        if (transaction != null) {
            // Fill the labels with info from the transaction object.
            transactionNoLabel.setText((transaction.getTransactionId()));
            //dateLabel.setText(DateUtil.format(transaction.getDate()));
            typeLabel.setText(transaction.getType());
            statusLabel.setText(transaction.getStatus());
            amountLabel.setText(Double.toString(transaction.getAmount()));
            salesRepLabel.setText(transaction.getSalesRep());


        } else {
            transactionNoLabel.setText("");
            dateLabel.setText("");
            typeLabel.setText("");
            statusLabel.setText("");
            amountLabel.setText("");
            salesRepLabel.setText("");
        }
    }

    @FXML
    private void handleDeleteTransaction() {
        int selectedIndex = transactionTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            transactionService.deleteTransaction(transactionTable.getItems().get(selectedIndex));
            transactionTable.getItems().remove(selectedIndex);
        }else if(transactionTable.getItems().isEmpty()) {
            // Nothing in table
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Transactions in Table");
            alert.setContentText("Table is Empty");

            alert.showAndWait();
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Transaction Selected");
            alert.setContentText("Please select a transaction in the table.");

            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new transaction.
     */
    @FXML
    private void handleNewTransaction() {
        Transaction tempTransaction = new Transaction(String.valueOf(transactionTable.getItems().size()+1));
        boolean okClicked = mainApp.showTransactionEditDialog(tempTransaction, "new");
        if (okClicked) {
            mainApp.getTransactionData().add(tempTransaction);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected transaction.
     */
    @FXML
    private void handleEditTransaction() {
        Transaction selectedTransaction = transactionTable.getSelectionModel().getSelectedItem();
        if (selectedTransaction != null) {
            boolean okClicked = mainApp.showTransactionEditDialog(selectedTransaction, "edit");
            if (okClicked) {
                showTransactionDetails(selectedTransaction);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Transaction Selected");
            alert.setContentText("Please select a transaction in the table.");

            alert.showAndWait();
        }
    }



    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        transactionTable.setItems(mainApp.getTransactionData());
    }
}

