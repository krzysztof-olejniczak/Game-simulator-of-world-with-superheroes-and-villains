package pl.gui;

import pl.population.Civilian;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.board.Board;

/**
 * *
 * Window shows actual information about Civilian
 *
 * @author Krzysztof Olejniczak
 */
public class CivilianInfoWindow extends javax.swing.JFrame implements Runnable {

    private static final int TIME_TO_SLEEP = 500;

    private final Civilian civilian;

    private boolean running;

    private final Board board;

    /**
     * *
     * Constructor of window
     *
     * @param civilian civilian described in window
     * @param board board of a game
     */
    public CivilianInfoWindow(Civilian civilian, Board board) {
        initComponents();
        cityComboBox.setModel(new javax.swing.DefaultComboBoxModel(board.getCitiesNames()));
        this.civilian = civilian;
        running = true;
        this.board = board;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        stopButton = new javax.swing.JButton();
        cityComboBox = new javax.swing.JComboBox();
        cityLabel = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();
        nameLabel = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();
        surnameLabel = new javax.swing.JLabel();
        homeTownLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Civilian");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        stopButton.setText("go/stop");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        cityComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cityComboBoxActionPerformed(evt);
            }
        });

        cityLabel.setText("Destination city: ");

        deleteButton.setText("delete civilian");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        nameLabel.setText("civilian");

        surnameLabel.setText("civilian");

        homeTownLabel.setText("Home town: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(surnameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(homeTownLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stopButton)
                            .addComponent(deleteButton)
                            .addComponent(cityLabel)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(surnameLabel)
                .addGap(17, 17, 17)
                .addComponent(homeTownLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cityLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stopButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteButton)
                .addGap(24, 24, 24))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        running = false;
        civilian.killInhabitant();
        this.dispose();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        civilian.reverseStop();
        if (civilian.isStop()) {
            stopButton.setText("go");
        } else {
            stopButton.setText("stop");
        }
    }//GEN-LAST:event_stopButtonActionPerformed

    private void cityComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cityComboBoxActionPerformed
        civilian.setTravelTo(board.findCity(this.cityComboBox.getSelectedItem().toString()));
    }//GEN-LAST:event_cityComboBoxActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        running = false;
        civilian.setInfoWindow(null);
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cityComboBox;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel homeTownLabel;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton stopButton;
    private javax.swing.JLabel surnameLabel;
    // End of variables declaration//GEN-END:variables

    /**
     * *
     * Thread function, which updates window's fields
     */
    @Override
    public void run() {
        while (running) {
            imageLabel.setIcon(new javax.swing.ImageIcon(civilian.getImage()));
            nameLabel.setText(civilian.getName());
            surnameLabel.setText(civilian.getSurname());
            homeTownLabel.setText("Home town: " + civilian.getHomeTown().getName());
            cityComboBox.setSelectedIndex(board.findCityIndex(civilian.getTravelTo()));

            if (civilian.isStop()) {
                stopButton.setText("go");
            } else {
                stopButton.setText("stop");
            }
            if (civilian.isKilled()) {
                break;
            }

            try {
                Thread.sleep(TIME_TO_SLEEP);
            } catch (InterruptedException ex) {
                Logger.getLogger(CivilianInfoWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.dispose();
    }
}
