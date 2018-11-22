package pl.gui;

import pl.Main.Main;
import pl.board.Board;
import pl.population.Civilian;
import pl.board.District;
import pl.population.Population;
import pl.population.fieldGenerators.CivilianFieldsGenerator;

/**
 * *
 * Window used to add new Civilian
 *
 * @author Krzysztof Olejniczak
 */
public class NewCivilianWindow extends javax.swing.JFrame {

    private final Board board;
    private final Population population;

    /**
     * New cywilian window constructor
     *
     * @param board board of game
     * @param population population of humans
     */
    public NewCivilianWindow(Board board, Population population) {
        initComponents();
        this.board = board;
        this.population = population;

        cityComboBox.setModel(new javax.swing.DefaultComboBoxModel(board.getCitiesNames()));
        this.nameField.setText(CivilianFieldsGenerator.generateName());
        this.surnameField.setText(CivilianFieldsGenerator.generateSurname());
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addButton = new javax.swing.JButton();
        cityComboBox = new javax.swing.JComboBox();
        cityLabel = new javax.swing.JLabel();
        surnameLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        surnameField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("New civilian");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        addButton.setText("add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        cityLabel.setText("Home town:");

        surnameLabel.setText("Surname:");

        nameLabel.setText("Name:");

        nameField.setText("jTextField1");

        surnameField.setText("jTextField2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nameLabel)
                    .addComponent(surnameLabel)
                    .addComponent(cityLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nameField)
                    .addComponent(surnameField)
                    .addComponent(cityComboBox, 0, 133, Short.MAX_VALUE)
                    .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(surnameLabel)
                    .addComponent(surnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cityLabel)
                    .addComponent(cityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addButton)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleParent(addButton);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        District district = board.findCity(this.cityComboBox.getSelectedItem().toString())
                .findEmptyNeighbourhood();
        if (district == null) {
            Main.getMainWindow().setMessageText("This city is full");
        } else if (district.getCity().isDestroyed()) {
            Main.getMainWindow().setMessageText("This city has been destroyed");
        } else {
            Civilian civilian = new Civilian(
                    this.nameField.getText(),
                    this.surnameField.getText(),
                    CivilianFieldsGenerator.generateImage(),
                    district, board, population
            );
            population.getCivilians().add(civilian);

            Thread thread = new Thread(civilian);
            thread.start();

            Main.getMainWindow().setMessageText("");
        }
        this.dispose();
    }//GEN-LAST:event_addButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JComboBox cityComboBox;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField surnameField;
    private javax.swing.JLabel surnameLabel;
    // End of variables declaration//GEN-END:variables

}
