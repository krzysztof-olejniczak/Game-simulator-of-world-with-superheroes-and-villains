package pl.gui;

import pl.Main.Main;
import pl.board.Board;
import pl.board.District;
import pl.population.Population;
import pl.population.Superhero;
import pl.population.fieldGenerators.SuperheroFieldsGenerator;

/**
 * *
 * Window used to add new Superhero
 *
 * @author Krzysztof Olejniczak
 */
public class NewSuperheroWindow extends javax.swing.JFrame {

    private final Board board;
    private final Population population;

    /**
     * New superhero window constructor
     *
     * @param board board of game
     * @param population population of humans
     */
    public NewSuperheroWindow(Board board, Population population) {
        initComponents();
        this.board = board;
        this.population = population;

        this.nameField.setText(SuperheroFieldsGenerator.generateName());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("New superhero");
        setResizable(false);

        nameLabel.setText("Name: ");

        nameField.setText("jTextField1");

        addButton.setText("add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addButton)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addGap(18, 18, 18)
                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addButton)
                .addGap(18, 18, 18))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        int maxNumberOfSuperheroes = board.calcMaxNumberOfSuperheroes();
        if (population.getSuperheroes().size() < maxNumberOfSuperheroes) {
            District district = board.getCapitalCity().findEmptyNeighbourhood();
            if (district == null) {
                Main.getMainWindow().setMessageText("This city is full");
            } else {

                Superhero superhero = new Superhero(
                        this.nameField.getText(),
                        SuperheroFieldsGenerator.generateImage(),
                        district, board, population
                );
                population.getSuperheroes().add(superhero);

                Thread thread = new Thread(superhero);
                thread.start();

                Main.getMainWindow().setMessageText("");
            }
        } else {
            Main.getMainWindow().setMessageText(
                    "<html>Actually you cannot add more than<br/>"
                    + maxNumberOfSuperheroes
                    + " superheroes</html>");
        }
        this.dispose();
    }//GEN-LAST:event_addButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    // End of variables declaration//GEN-END:variables

}
