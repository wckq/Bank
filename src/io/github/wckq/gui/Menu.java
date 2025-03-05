package io.github.wckq.gui;

import io.github.wckq.cashier.queue.CashierQueueController;
import io.github.wckq.cashier.queue.CashierQueueRepository;
import io.github.wckq.cashier.queue.view.QueueCashierUI;
import io.github.wckq.cashier.user.controller.CashierUserController;
import io.github.wckq.cashier.user.CashierUserRepository;
import io.github.wckq.cashier.user.view.CashierUserUI;
import io.github.wckq.customer.controller.CustomerController;
import io.github.wckq.customer.CustomerRepository;
import io.github.wckq.customer.view.CustomerUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The main menu of the application.
 */
public class Menu extends JFrame {

    private final JPanel panel;

    private final JLabel titleLabel;

    private final JButton cashierButton;
    private final JButton customerButton;
    private final JButton cashierQueueButton;
    private final JButton exitButton;

    private final CustomerRepository customerRepository;
    private final CashierUserRepository cashierRepository;
    private final CustomerController customerController;
    private final CashierUserController cashierController;
    private final CashierQueueController cashierQueueController;
    private final CashierQueueRepository cashierQueueRepository;

    public Menu(CustomerRepository customerRepository,
                CashierUserRepository cashierRepository,
                CustomerController customerController,
                CashierUserController cashierController,
                CashierQueueController cashierQueueController,
                CashierQueueRepository cashierQueueRepository) {
        this.customerRepository = customerRepository;
        this.cashierRepository = cashierRepository;
        this.customerController = customerController;
        this.cashierController = cashierController;
        this.cashierQueueController = cashierQueueController;
        this.cashierQueueRepository = cashierQueueRepository;

        this.setTitle("Menu");
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBounds(0, 0, 500, 400);

        this.titleLabel = new CustomLabel("Banco de Hyrule", 150, 20, 24);

        this.cashierButton = new JButton("Cajeros");
        this.customerButton = new JButton("Clientes");
        this.cashierQueueButton = new JButton("Turnos");
        this.exitButton = new JButton("Salir");

        this.cashierButton.setBounds(100, 100, 100, 50);
        this.customerButton.setBounds(290, 100, 100, 50);
        this.cashierQueueButton.setBounds(100, 200, 100, 50);
        this.exitButton.setBounds(290, 200, 100, 50);

        this.cashierButton.addActionListener(this::cashierAction);
        this.customerButton.addActionListener(this::customerAction);
        this.cashierQueueButton.addActionListener(this::cashierQueueAction);
        this.cashierButton.addActionListener(this::exitAction);

        this.panel.add(titleLabel);
        this.panel.add(cashierButton);
        this.panel.add(customerButton);
        this.panel.add(cashierQueueButton);
        this.panel.add(exitButton);

        this.add(panel);

        this.setVisible(true);
    }

    private void cashierAction(ActionEvent actionEvent) {
        final CashierUserUI cashierGUI = new CashierUserUI(this.cashierRepository, this.cashierController);
        cashierGUI.setVisible(true);
    }

    private void customerAction(ActionEvent actionEvent) {
        final CustomerUI customerGUI = new CustomerUI(this.customerRepository, this.customerController);
        customerGUI.setVisible(true);
    }

    private void cashierQueueAction(ActionEvent actionEvent) {
        final QueueCashierUI cashierQueueGUI = new QueueCashierUI(this.cashierQueueRepository, this.cashierQueueController, customerRepository);
        cashierQueueGUI.setVisible(true);
    }

    private void exitAction(ActionEvent actionEvent) {
        this.dispose();
    }
}
