package io.github.wckq;

import io.github.wckq.cashier.queue.CashierQueueController;
import io.github.wckq.cashier.queue.CashierQueueRepository;
import io.github.wckq.cashier.queue.view.QueueCashierUI;
import io.github.wckq.cashier.user.CashierUserController;
import io.github.wckq.cashier.user.CashierUserRepository;
import io.github.wckq.customer.CustomerController;
import io.github.wckq.customer.CustomerRepository;
import io.github.wckq.gui.Menu;

import javax.swing.*;

public class Main {
    public static Menu menu;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CustomerRepository customerRepository = new CustomerRepository();
            CustomerController customerController = new CustomerController(customerRepository);
            CashierUserRepository cashierRepository = new CashierUserRepository();
            CashierUserController cashierController = new CashierUserController(cashierRepository);
            CashierQueueRepository cashierQueueRepository = new CashierQueueRepository();
            CashierQueueController cashierQueueController = new CashierQueueController(cashierQueueRepository);

            menu = new Menu(customerRepository, cashierRepository, customerController, cashierController, cashierQueueController, cashierQueueRepository);
            menu.setVisible(true);
        });
    }

    public static void closeMenu(JFrame panel) {
        panel.setVisible(false);
        menu.setVisible(true);
    }
}