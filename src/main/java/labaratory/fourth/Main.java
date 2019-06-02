package labaratory.fourth;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private PhysicalMemoryTable physicalMemoryTable = PhysicalMemoryTable.getInstance();

    public Main() {
        setSize(1000, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        var jPanel = new JPanel();
        setContentPane(jPanel);

        jPanel.setLayout(null);

        var listModel = new DefaultListModel();

        int rows = (physicalMemoryTable.getCountBlocks() * 15 / this.getWidth()) + 1;
        int columns = physicalMemoryTable.getCountBlocks() / rows;

        var tablePanel = new TablePanel(physicalMemoryTable.getCountBlocks(), getWidth());
        tablePanel.setBounds(10, 10, columns * 15, rows * 15);
        tablePanel.setBackground(new Color(255, 255, 255));
        jPanel.add(tablePanel);

        var panel = new JPanel();
        panel.setBounds(0, 260, 1300, 590);
        jPanel.add(panel);
        panel.setLayout(null);

        var labelName = new JLabel("Имя файла: ");
        labelName.setBounds(450, 0, 200, 23);
        panel.add(labelName);

        var textFieldName = new JTextField();
        textFieldName.setBounds(550, 0, 200, 23);
        panel.add(textFieldName);

        var labelSize = new JLabel("Размер файла: ");
        labelSize.setBounds(450, 40, 200, 23);
        panel.add(labelSize);

        var textFieldSize = new JTextField();
        textFieldSize.setBounds(550, 40, 200, 23);
        panel.add(textFieldSize);

        var list = new JList(listModel);
        list.addListSelectionListener(e -> {
            String fileName = (String) list.getSelectedValue();
            if (fileName == null) {
                return;
            }
            physicalMemoryTable.selectFile(fileName);
            tablePanel.repaint();
        });
        list.setBounds(10, 0, 400, 150);
        panel.add(list);

        var button = new JButton("Создать");
        button.addActionListener(e -> {
            try {
                int sizeFile = Integer.parseInt(textFieldSize.getText());

                String fileName = textFieldName.getText();

                if (fileName.isEmpty()) {
                    throw new Exception("Заполните имя файла!");
                }

                physicalMemoryTable.addFile(new File(fileName, sizeFile));
                listModel.addElement(textFieldName.getText());

                textFieldName.setText("");
                textFieldSize.setText("");
                tablePanel.repaint();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                System.out.println(ex.getMessage());
            }
        });
        button.setBounds(550, 80, 200, 23);
        panel.add(button);

        var button_1 = new JButton("Удалить");
        button_1.addActionListener(e -> {
            try {
                String fileName = (String) list.getSelectedValue();
                listModel.removeElement(list.getSelectedValue());
                physicalMemoryTable.deleteFile(fileName);
                tablePanel.repaint();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                System.out.println(ex.getMessage());
            }
        });
        button_1.setBounds(550, 120, 200, 23);
        panel.add(button_1);
    }

    public static void main(String[] args) {
        Main gr = new Main();
        gr.setVisible(true);
    }
}
