package pl.gui;

import java.text.DecimalFormat;
import pl.population.Character;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.board.Board;
import pl.population.Superhero;
import pl.population.Villain;
import pl.powerSource.Capability.CapabilityType;

/**
 * *
 * Window shows actual information about Character
 *
 * @author Krzysztof Olejniczak
 */
public class CharacterInfoWindow extends javax.swing.JFrame implements Runnable {

    private static final int TIME_TO_SLEEP = 500;
    private static final DecimalFormat DOUBLE_VALUES_FORMAT = new DecimalFormat("#.#");

    private final Character character;

    private boolean running;

    private final Board board;

    /**
     * *
     * Constructor of window
     *
     * @param character character described in window
     * @param board board of a game
     */
    public CharacterInfoWindow(Character character, Board board) {
        initComponents();
        this.character = character;
        running = true;
        this.board = board;

        if (character instanceof Villain) {
            setTitle("Villain");
            cityComboBox.setVisible(false);
            deleteButton.setVisible(false);
        } else if (character instanceof Superhero) {
            setTitle("Superhero");
            cityComboBox.setModel(new javax.swing.DefaultComboBoxModel(board.getCitiesNames()));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cityLabel = new javax.swing.JLabel();
        cityComboBox = new javax.swing.JComboBox();
        imageLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        intelligenceLabel = new javax.swing.JLabel();
        healthLabel = new javax.swing.JLabel();
        forceLabel = new javax.swing.JLabel();
        speedLabel = new javax.swing.JLabel();
        strengthLabel = new javax.swing.JLabel();
        energyLabel = new javax.swing.JLabel();
        fightingSkillsLabel = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();
        stateLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Character");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        cityLabel.setText("Destination: ");

        cityComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cityComboBoxActionPerformed(evt);
            }
        });

        nameLabel.setText("character");

        intelligenceLabel.setText("Intelligence: ");

        healthLabel.setText("Health: ");

        forceLabel.setText("Force: ");

        speedLabel.setText("Speed: ");

        strengthLabel.setText("Strength: ");

        energyLabel.setText("Energy: ");

        fightingSkillsLabel.setText("Fighting skills: ");

        deleteButton.setText("delete superhero");
        deleteButton.setToolTipText("");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        stateLabel.setText("State: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteButton)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cityLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(speedLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(forceLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(intelligenceLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(healthLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                        .addComponent(stateLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(strengthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(fightingSkillsLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                        .addComponent(energyLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cityLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(healthLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(intelligenceLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(forceLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(speedLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(strengthLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(energyLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fightingSkillsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(deleteButton)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cityComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cityComboBoxActionPerformed
        character.setTravelTo(board.findCity(this.cityComboBox.getSelectedItem().toString()));
    }//GEN-LAST:event_cityComboBoxActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        running = false;
        character.killCharacter();
        this.dispose();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        running = false;
        character.setInfoWindow(null);
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cityComboBox;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel energyLabel;
    private javax.swing.JLabel fightingSkillsLabel;
    private javax.swing.JLabel forceLabel;
    private javax.swing.JLabel healthLabel;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel intelligenceLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel speedLabel;
    private javax.swing.JLabel stateLabel;
    private javax.swing.JLabel strengthLabel;
    // End of variables declaration//GEN-END:variables

    /**
     * *
     * Thread function, which updates window's fields
     */
    @Override
    public void run() {
        while (running) {
            imageLabel.setIcon(new javax.swing.ImageIcon(character.getImage()));
            nameLabel.setText(character.getName());
            healthLabel.setText("Health: "
                    + DOUBLE_VALUES_FORMAT.format(character.getHealth()));
            intelligenceLabel.setText("Intelligence: "
                    + DOUBLE_VALUES_FORMAT.format(character.getPower(CapabilityType.INTELLIGENCE)));
            forceLabel.setText("Force: "
                    + DOUBLE_VALUES_FORMAT.format(character.getPower(CapabilityType.FORCE)));
            speedLabel.setText("Speed: "
                    + DOUBLE_VALUES_FORMAT.format(character.getPower(CapabilityType.SPEED)));
            strengthLabel.setText("Strength: "
                    + DOUBLE_VALUES_FORMAT.format(character.getPower(CapabilityType.STRENGTH)));
            energyLabel.setText("Energy: "
                    + DOUBLE_VALUES_FORMAT.format(character.getPower(CapabilityType.ENERGY)));
            fightingSkillsLabel.setText("Fighting skills: "
                    + DOUBLE_VALUES_FORMAT.format(character.getPower(CapabilityType.FIGHTING_SKILLS)));

            switch (character.getState()) {
                case TRAVELING:
                    stateLabel.setText("State: travelling");
                    break;
                case IN_CITY:
                    if (character instanceof Superhero) {
                        stateLabel.setText("State: in city");
                    } else {
                        stateLabel.setText("State: destroys city");
                    }
                    break;
                case FIGHT:
                    stateLabel.setText("State: Fight with " + character.getFightWith().getName());
                    break;
            }

            if (character instanceof Villain) {
                cityLabel.setText("Destination: " + character.getTravelTo().getName());
            } else if (character instanceof Superhero) {
                cityComboBox.setSelectedIndex(board.findCityIndex(character.getTravelTo()));
            }

            if (character.isKilled()) {
                break;
            }

            try {
                Thread.sleep(TIME_TO_SLEEP);
            } catch (InterruptedException ex) {
                Logger.getLogger(CharacterInfoWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.dispose();
    }
}
