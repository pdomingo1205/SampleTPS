package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Transaction;
import util.DateUtil;

/**
 * Dialog to edit details of a transaction.
 *
 * @author Marco Jakob
 */
public class TransactionEditDialogController {


    @FXML
    private TextField transactionNoField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField statusField;
    @FXML
    private TextField salesRepField;
    @FXML
    private TextField amountField;


    private Stage dialogStage;
    private Transaction transaction;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the transaction to be edited in the dialog.
     *
     * @param transaction
     */
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
        try {
            transactionNoField.setText(String.valueOf(transaction.getTransactionId()));
            typeField.setText(transaction.getType());
            statusField.setText(transaction.getStatus());
            salesRepField.setText((transaction.getSalesRep()));
            amountField.setText(Double.toString(transaction.getAmount()));
            dateField.setText(DateUtil.format(transaction.getDate()));
            dateField.setPromptText("dd.mm.yyyy");
        }catch(Exception e){

        }
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            transaction.setTransactionId(Long.valueOf(transactionNoField.getText()));
            transaction.setType(typeField.getText());
            transaction.setStatus(statusField.getText());
            transaction.setAmount(Double.valueOf(amountField.getText()));
            transaction.setSalesRep(salesRepField.getText());
            transaction.setDate(DateUtil.parse(dateField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (transactionNoField.getText() == null || Long.valueOf(transactionNoField.getText()) < 1) {
            errorMessage += "Invalid ID!\n";
        }else {
            // try to parse the Id into an int.
            try {
                //Long.valueOf(amountField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid Id (must be an number)!\n";
            }
        }

        if (typeField.getText() == null || typeField.getText().length() == 0) {
            errorMessage += "Not a valid type\n";
        }
        if (statusField.getText() == null || statusField.getText().length() == 0) {
            errorMessage += "Not a valid status!\n";
        }

        if (amountField.getText() == null || amountField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Double.parseDouble(amountField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an number)!\n";
            }
        }

        if (salesRepField.getText() == null || salesRepField.getText().length() == 0) {
            errorMessage += "Not a valid name!\n";
        }

        if (dateField.getText() == null || dateField.getText().length() == 0) {
            errorMessage += "Not a valid date!\n";
        } else {
            if (!DateUtil.validDate(dateField.getText())) {
                errorMessage += "No valid date. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
