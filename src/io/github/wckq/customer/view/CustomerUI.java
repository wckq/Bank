package io.github.wckq.customer.view;

import io.github.wckq.Main;
import io.github.wckq.customer.model.Customer;
import io.github.wckq.customer.controller.CustomerController;
import io.github.wckq.customer.CustomerRepository;
import io.github.wckq.customer.CustomerType;
import io.github.wckq.gui.CustomButton;
import io.github.wckq.gui.CustomLabel;
import io.github.wckq.gui.CustomTextField;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CustomerUI extends JFrame {

    private final static int X_LABEL_CONSTANT = 80;
    private final static int X_FIELD_CONSTANT = 560;

    private final static int Y_BUTTON_CONSTANT = 580;

    private final CustomerRepository customerRepository;
    private final CustomerController customerController;

    private final JPanel panel;

    private final JLabel titleLabel;
    private final JLabel idLabel;
    private final JLabel nameLabel;
    private final JLabel firstNameLabel;
    private final JLabel lastNameLabel;
    private final JLabel rfcLabel;
    private final JLabel addressLabel;
    private final JLabel typeLabel;
    private final JLabel salaryLabel;

    private final JTextField idTextField;
    private final JTextField nameTextField;
    private final JTextField firstNameTextField;
    private final JTextField lastNameTextField;
    private final JTextField rfcTextField;
    private final JTextField addressTextField;
    private final JComboBox<CustomerType> typeComboBox;
    private final JTextField salaryTextField;

    private final JButton newButton;
    private final JButton searchButton;
    private final JButton deleteButton;
    private final JButton exitButton;

    public CustomerUI(CustomerRepository customerRepository, CustomerController customerController) {
        this.customerRepository = customerRepository;
        this.customerController = customerController;

        this.setTitle("Customers");
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBounds(0, 0, 1000, 800);

        this.titleLabel = new CustomLabel("Clientes", 430, 20, 30);
        this.idLabel = new CustomLabel("ID:", X_LABEL_CONSTANT, 150, 20);
        this.nameLabel = new CustomLabel("Nombre:", X_LABEL_CONSTANT, 200, 20);
        this.firstNameLabel = new CustomLabel("Apellido Paterno:", X_LABEL_CONSTANT, 250, 20);
        this.lastNameLabel = new CustomLabel("Apellido Materno:", X_LABEL_CONSTANT, 300, 20);
        this.rfcLabel = new CustomLabel("RFC:", X_LABEL_CONSTANT, 350, 20);
        this.addressLabel = new CustomLabel("Direccion:", X_LABEL_CONSTANT, 400, 20);
        this.typeLabel = new CustomLabel("Tipo:", X_LABEL_CONSTANT, 450, 20);
        this.salaryLabel = new CustomLabel("Salario:", X_LABEL_CONSTANT, 500, 20);

        this.idTextField = new CustomTextField("", X_FIELD_CONSTANT, 160, 12);
        this.nameTextField = new CustomTextField("", X_FIELD_CONSTANT, 210, 12);
        this.firstNameTextField = new CustomTextField("", X_FIELD_CONSTANT, 260, 12);
        this.lastNameTextField = new CustomTextField("", X_FIELD_CONSTANT, 310, 12);
        this.rfcTextField = new CustomTextField("", X_FIELD_CONSTANT, 360, 12);
        this.addressTextField = new CustomTextField("", X_FIELD_CONSTANT, 410, 12);
        this.typeComboBox = new JComboBox<>();
        this.typeComboBox.addItem(CustomerType.NORMAL);
        this.typeComboBox.addItem(CustomerType.PREFERRED);
        this.typeComboBox.addItem(CustomerType.VIP);
        this.typeComboBox.setBounds(X_FIELD_CONSTANT, 460, 380, 30);
        this.salaryTextField = new CustomTextField("", X_FIELD_CONSTANT, 510, 12);

        this.newButton = new CustomButton("Nuevo", 225, Y_BUTTON_CONSTANT, this::newAction);
        this.searchButton = new CustomButton("Buscar", 375, Y_BUTTON_CONSTANT, this::searchAction);
        this.deleteButton = new CustomButton("Eliminar", 525, Y_BUTTON_CONSTANT, null);
        this.exitButton = new CustomButton("Salir", 675, Y_BUTTON_CONSTANT, (actionListener) -> Main.closeMenu(this));

        this.panel.add(titleLabel);
        this.panel.add(idLabel);
        this.panel.add(nameLabel);
        this.panel.add(firstNameLabel);
        this.panel.add(lastNameLabel);
        this.panel.add(rfcLabel);
        this.panel.add(addressLabel);
        this.panel.add(typeLabel);
        this.panel.add(salaryLabel);

        this.panel.add(nameTextField);
        this.panel.add(idTextField);
        this.panel.add(firstNameTextField);
        this.panel.add(lastNameTextField);
        this.panel.add(rfcTextField);
        this.panel.add(addressTextField);
        this.panel.add(typeComboBox);
        this.panel.add(salaryTextField);

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
        this.rfcTextField.setEnabled(enabled);
        this.addressTextField.setEnabled(enabled);
        this.typeComboBox.setEnabled(enabled);
        this.salaryTextField.setEnabled(enabled);
        this.deleteButton.setEnabled(enabled);
    }

    private void newAction(ActionEvent actionEvent) {
        if (this.deleteButton.isEnabled()) {
            this.saveAction(actionEvent);
            return;
        }
        this.clearAction(actionEvent);
        this.updateState(true);
        this.newButton.setText("Ingresar");
    }

    private void searchAction(ActionEvent actionEvent) {
        if (this.idTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo ID no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        final int id = Integer.parseInt(this.idTextField.getText());
        final Customer customer = this.customerRepository.findById(id);
        if (customer == null) {
            JOptionPane.showMessageDialog(this, "No existe el cliente con ese ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.idTextField.setText(Integer.toString(customer.getId()));
        this.nameTextField.setText(customer.getName());
        this.firstNameTextField.setText(customer.getFirstName());
        this.lastNameTextField.setText(customer.getLastName());
        this.rfcTextField.setText(customer.getRfc());
        this.addressTextField.setText(customer.getAddress());
        this.typeComboBox.setSelectedItem(customer.getType());
        this.salaryTextField.setText(Double.toString(customer.getSalary()));
        this.updateState(false);
    }

    private void saveAction(ActionEvent actionEvent) {
        if (this.idTextField.getText().isEmpty() || this.nameTextField.getText().isEmpty() ||
                this.firstNameTextField.getText().isEmpty() || this.lastNameTextField.getText().isEmpty() ||
                this.rfcTextField.getText().isEmpty() || this.addressTextField.getText().isEmpty() ||
                this.typeComboBox.getSelectedItem() == null || this.salaryTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los campos no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        final int id = Integer.parseInt(this.idTextField.getText());
        final String name = this.nameTextField.getText();
        final String firstName = this.firstNameTextField.getText();
        final String lastName = this.lastNameTextField.getText();
        final String rfc = this.rfcTextField.getText();
        final String address = this.addressTextField.getText();
        final CustomerType type = (CustomerType) this.typeComboBox.getSelectedItem();
        final double salary = Double.parseDouble(this.salaryTextField.getText());

        final Customer customer = new Customer(id, name, firstName, lastName, rfc);
        customer.setAddress(address);
        customer.setType(type);
        customer.setSalary(salary);

        this.customerRepository.addCustomer(customer);
        this.newButton.setText("Nuevo");
        this.clearAction(actionEvent);
        this.updateState(false);
    }

    private void clearAction(ActionEvent actionEvent) {
        this.idTextField.setText("");
        this.nameTextField.setText("");
        this.firstNameTextField.setText("");
        this.lastNameTextField.setText("");
        this.rfcTextField.setText("");
        this.addressTextField.setText("");
        this.typeComboBox.setSelectedItem(null);
        this.salaryTextField.setText("");
    }
}
