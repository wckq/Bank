package io.github.wckq.cashier.queue.view;

import io.github.wckq.Main;
import io.github.wckq.cashier.queue.CashierQueueController;
import io.github.wckq.cashier.queue.CashierQueueRepository;
import io.github.wckq.customer.Customer;
import io.github.wckq.customer.CustomerRepository;
import io.github.wckq.gui.CustomButton;
import io.github.wckq.gui.CustomLabel;
import io.github.wckq.gui.CustomTextField;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class QueueCashierUI extends JFrame {

    private final static int X_FIELD_CONSTANT = 560;

    private final static int Y_BUTTON_CONSTANT = 170;

    private final CustomerRepository customerRepository;
    private final CashierQueueRepository cashierQueueRepository;
    private final CashierQueueController cashierQueueController;

    private final JPanel panel;

    private final JLabel titleLabel;
    private final JLabel idLabel;
    private final JLabel basicLabel;
    private final JLabel preferredLabel;
    private final JLabel vipLabel;

    private final JLabel cashierAmountLabel;

    private final JTextField idTextField;

    private final DefaultListModel<Customer> basicListModel;
    private final DefaultListModel<Customer> preferredListModel;
    private final DefaultListModel<Customer> vipListModel;
    private final DefaultListModel<Customer> attendListModel;

    private final JSpinner cashierAmountSpinner;

    private final JList<Customer> basicList;
    private final JList<Customer> preferredList;
    private final JList<Customer> vipList;
    private final JList<Customer> attendList;

    private final JButton newButton;
    private final JButton attendButton;
    private final JButton addButton;

    public QueueCashierUI(CashierQueueRepository cashierQueueRepository,
                          CashierQueueController cashierQueueController,
                          CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.cashierQueueRepository = cashierQueueRepository;
        this.cashierQueueController = cashierQueueController;

        this.basicListModel = new DefaultListModel<>();
        this.preferredListModel = new DefaultListModel<>();
        this.vipListModel = new DefaultListModel<>();
        this.attendListModel = new DefaultListModel<>();

        this.basicList = new JList<>(this.basicListModel);
        this.preferredList = new JList<>(this.preferredListModel);
        this.vipList = new JList<>(this.vipListModel);
        this.attendList = new JList<>(this.attendListModel);

        this.setTitle("Cashiers");
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBounds(0, 0, 1000, 800);

        this.titleLabel = new CustomLabel("Cajeros", 450, 20, 30);
        this.idLabel = new CustomLabel("ID:", 260, 95, 20);
        this.cashierAmountLabel = new CustomLabel("Cantidad de Cajeros:", 100, 450, 16);
        this.basicLabel = new CustomLabel("Normal", 140, Y_BUTTON_CONSTANT, 16);
        this.preferredLabel = new CustomLabel("Preferido", 450, Y_BUTTON_CONSTANT, 16);
        this.vipLabel = new CustomLabel("VIP", 810, Y_BUTTON_CONSTANT, 16);

        this.cashierAmountSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        this.cashierAmountSpinner.setBounds(270, 465, 40, 30);

        this.basicList.setBounds(100, 225, 150, 200);
        this.attendList.setBounds(440, 465, 120, 150);
        this.preferredList.setBounds(410, 225, 150, 200);
        this.vipList.setBounds(750, 225, 150, 200);

        this.idTextField = new CustomTextField("", 310, 110, 12);

        this.attendButton = new CustomButton("Asistir", 100, 500, this::attendUser);

        this.addButton = new CustomButton("Agregar", 710, 105, this::addUser);
        this.newButton = new CustomButton("Salir", 800, 580, (actionListener) -> Main.closeMenu(this));

        this.panel.add(titleLabel);
        this.panel.add(idLabel);

        this.panel.add(basicLabel);
        this.panel.add(preferredLabel);
        this.panel.add(vipLabel);
        this.panel.add(idTextField);

        this.panel.add(attendButton);

        this.panel.add(cashierAmountLabel);

        this.panel.add(basicList);
        this.panel.add(attendList);
        this.panel.add(preferredList);
        this.panel.add(vipList);

        this.panel.add(cashierAmountSpinner);

        this.panel.add(addButton);
        this.panel.add(newButton);

        this.add(panel);
    }

    public void addUser(ActionEvent actionEvent) {
        final int id = Integer.parseInt(this.idTextField.getText());
        final Customer customer = this.customerRepository.findById(id);
        if (customer == null) {
            JOptionPane.showMessageDialog(this, "No existe el cliente con ese ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.cashierQueueController.addCustomer(customer, customer.getType());

        switch (customer.getType()) {
            case NORMAL -> this.basicListModel.addElement(customer);
            case PREFERRED -> this.preferredListModel.addElement(customer);
            case VIP -> this.vipListModel.addElement(customer);
            default -> throw new IllegalArgumentException("Customer type no valido.");
        }
    }

    public void attendUser(ActionEvent actionEvent) {
        if (this.isEmptyCustomers()) {
            JOptionPane.showMessageDialog(this, "No hay clientes en la cola", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        final Customer customer = this.obtainCustomer();
        if (customer == null) {
            return;
        }
        this.attendListModel.addElement(customer);
        switch (customer.getType()) {
            case NORMAL -> this.basicListModel.removeElement(customer);
            case PREFERRED -> this.preferredListModel.removeElement(customer);
            case VIP -> this.vipListModel.removeElement(customer);
            default -> throw new IllegalArgumentException("Customer type no valido.");
        }
        cashierQueueController.removeCustomer(customer, customer.getType());

        final int cashierCount = (int) cashierAmountSpinner.getValue();
        final int attendTime = 5000 / cashierCount;

        final Timer timer = new Timer(attendTime, e -> attendListModel.removeElement(customer));

        timer.setRepeats(false);
        timer.start();
    }

    public Customer obtainCustomer() {
        if (this.isEmptyCustomers()) {
            JOptionPane.showMessageDialog(this, "No hay clientes en la cola", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (!this.cashierQueueRepository.getVipCustomers().isEmpty()) {
            return this.cashierQueueRepository.getVipCustomers().poll();
        } else if (!this.cashierQueueRepository.getPreferredCustomers().isEmpty()) {
            return this.cashierQueueRepository.getPreferredCustomers().poll();
        } else if (!this.cashierQueueRepository.getBasicCustomers().isEmpty()) {
            return this.cashierQueueRepository.getBasicCustomers().poll();
        }

        return null;
    }

    public boolean isEmptyCustomers() {
        return this.cashierQueueRepository.getBasicCustomers().isEmpty() &&
                this.cashierQueueRepository.getPreferredCustomers().isEmpty() &&
                this.cashierQueueRepository.getVipCustomers().isEmpty();
    }
}
