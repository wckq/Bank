package io.github.wckq.cashier.user.gui;

import io.github.wckq.Main;
import io.github.wckq.cashier.user.CashierUser;
import io.github.wckq.cashier.user.CashierUserController;
import io.github.wckq.cashier.user.CashierUserRepository;
import io.github.wckq.gui.CustomButton;
import io.github.wckq.gui.CustomLabel;
import io.github.wckq.gui.CustomTextField;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CashierUserUI extends JFrame {

    private final static int X_LABEL_CONSTANT = 80;
    private final static int X_FIELD_CONSTANT = 560;

    private final static int Y_BUTTON_CONSTANT = 580;

    private final CashierUserRepository cashierRepository;
    private final CashierUserController cashierController;

    private final JPanel panel;

    private final JLabel titleLabel;
    private final JLabel idLabel;
    private final JLabel nameLabel;
    private final JLabel firstNameLabel;
    private final JLabel lastNameLabel;
    private final JLabel initialCapitalLabel;
    private final JLabel finalCapitalLabel;

    private final JTextField idTextField;
    private final JTextField nameTextField;
    private final JTextField firstNameTextField;
    private final JTextField lastNameTextField;
    private final JTextField initialCapitalTextField;
    private final JTextField finalCapitalTextField;

    private final JButton newButton;
    private final JButton searchButton;
    private final JButton deleteButton;
    private final JButton exitButton;

    public CashierUserUI(CashierUserRepository cashierRepository, CashierUserController cashierController) {
        this.cashierRepository = cashierRepository;
        this.cashierController = cashierController;

        this.setTitle("Cashiers");
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBounds(0, 0, 1000, 800);

        this.titleLabel = new CustomLabel("Cajeros", 430, 20, 30);
        this.idLabel = new CustomLabel("ID:", X_LABEL_CONSTANT, 150, 20);
        this.nameLabel = new CustomLabel("Nombre:", X_LABEL_CONSTANT, 200, 20);
        this.firstNameLabel = new CustomLabel("Apellido Paterno:", X_LABEL_CONSTANT, 250, 20);
        this.lastNameLabel = new CustomLabel("Apellido Materno:", X_LABEL_CONSTANT, 300, 20);
        this.initialCapitalLabel = new CustomLabel("Fondo Inicial:", X_LABEL_CONSTANT, 350, 20);
        this.finalCapitalLabel = new CustomLabel("Fondo Final:", X_LABEL_CONSTANT, 400, 20);

        this.idTextField = new CustomTextField("", X_FIELD_CONSTANT, 160, 12);
        this.nameTextField = new CustomTextField("", X_FIELD_CONSTANT, 210, 12);
        this.firstNameTextField = new CustomTextField("", X_FIELD_CONSTANT, 260, 12);
        this.lastNameTextField = new CustomTextField("", X_FIELD_CONSTANT, 310, 12);
        this.initialCapitalTextField = new CustomTextField("", X_FIELD_CONSTANT, 360, 12);
        this.finalCapitalTextField = new CustomTextField("", X_FIELD_CONSTANT, 410, 12);

        this.newButton = new CustomButton("Nuevo", 225, Y_BUTTON_CONSTANT, this::newAction);
        this.searchButton = new CustomButton("Buscar", 375, Y_BUTTON_CONSTANT, this::searchAction);
        this.deleteButton = new CustomButton("Eliminar", 525, Y_BUTTON_CONSTANT, null);
        this.exitButton = new CustomButton("Salir", 675, Y_BUTTON_CONSTANT, actionListener -> Main.closeMenu(this));

        this.panel.add(titleLabel);
        this.panel.add(idLabel);
        this.panel.add(nameLabel);
        this.panel.add(firstNameLabel);
        this.panel.add(lastNameLabel);
        this.panel.add(initialCapitalLabel);
        this.panel.add(finalCapitalLabel);

        this.panel.add(nameTextField);
        this.panel.add(idTextField);
        this.panel.add(firstNameTextField);
        this.panel.add(lastNameTextField);
        this.panel.add(initialCapitalTextField);
        this.panel.add(finalCapitalTextField);

        this.panel.add(newButton);
        this.panel.add(searchButton);
        this.panel.add(deleteButton);
        this.panel.add(exitButton);

        this.updateState(false);

        this.add(panel);
    }

    private void updateState(boolean enabled) {
        this.nameTextField.setEnabled(enabled);
        this.firstNameTextField.setEnabled(enabled);
        this.lastNameTextField.setEnabled(enabled);
        this.initialCapitalTextField.setEnabled(enabled);
        this.finalCapitalTextField.setEnabled(enabled);
        this.deleteButton.setEnabled(enabled);
    }

    private void newAction(ActionEvent actionEvent) {
        if (this.deleteButton.isEnabled()) {
            this.saveAction(actionEvent);
            return;
        }
        this.updateState(true);
        this.newButton.setText("Ingresar");
    }

    private void searchAction(ActionEvent actionEvent) {
        if (this.idTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo ID no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        final int id = Integer.parseInt(this.idTextField.getText());
        final CashierUser customer = this.cashierRepository.findById(id);
        if (customer == null) {
            JOptionPane.showMessageDialog(this, "No existe el cliente con ese ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.idTextField.setText(Integer.toString(customer.getId()));
        this.nameTextField.setText(customer.getName());
        this.firstNameTextField.setText(customer.getFirstName());
        this.lastNameTextField.setText(customer.getLastName());
        this.initialCapitalTextField.setText(customer.getInitialCapital() + "");
        this.finalCapitalTextField.setText(customer.getFinalCapital() + "");
        this.updateState(false);
    }

    private void saveAction(ActionEvent actionEvent) {
        if (this.idTextField.getText().isEmpty() || this.nameTextField.getText().isEmpty() ||
                this.firstNameTextField.getText().isEmpty() || this.lastNameTextField.getText().isEmpty() ||
                this.initialCapitalTextField.getText().isEmpty() || this.finalCapitalTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los campos no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        final int id = Integer.parseInt(this.idTextField.getText());
        final String name = this.nameTextField.getText();
        final String firstName = this.firstNameTextField.getText();
        final String lastName = this.lastNameTextField.getText();
        final String initialCapital = this.initialCapitalTextField.getText();
        final String finalCapital = this.finalCapitalTextField.getText();

        final CashierUser cashier = new CashierUser(id, name, firstName, lastName);
        cashier.setInitialCapital(Integer.parseInt(initialCapital));
        cashier.setFinalCapital(Integer.parseInt(finalCapital));

        this.cashierRepository.addCashier(cashier);
        this.updateState(false);
        this.clearAction(actionEvent);
    }

    private void clearAction(ActionEvent actionEvent) {
        this.idTextField.setText("");
        this.nameTextField.setText("");
        this.firstNameTextField.setText("");
        this.lastNameTextField.setText("");
        this.initialCapitalTextField.setText("");
        this.finalCapitalTextField.setText("");
        this.updateState(true);
    }
}
