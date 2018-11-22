package pl.gui;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.resources.Files;
import pl.result.Result;

/**
 * Window presents best results of game and result of current player
 *
 * @author Krzysztof Olejniczak
 */
public class GameOverWindow extends javax.swing.JFrame {

    private static final int NUMBER_OF_BEST_SCORES = 5;

    private final List<Result> results = new LinkedList<>();
    private final Result lastResult;

    /**
     * Window's constructor
     *
     * @param villainsKilled number of villains killed in ended game
     * @param timeOfGame total time of ended game
     */
    public GameOverWindow(int villainsKilled, int timeOfGame) {
        initComponents();
        lastResult = new Result("", villainsKilled, timeOfGame);

        readResults();
        resultsLabel.setText(getResultsText());
        timeLabel.setText("Game time: " + (int) (timeOfGame / 60) + " minutes "
                + (timeOfGame % 60) + " seconds");
        killedLabel.setText("Villains killed: " + villainsKilled);

        if (results.get(results.size() - 1).isBetterThan(lastResult)) {
            yourNameLabel.setVisible(false);
            nameTextField.setVisible(false);
            saveButton.setVisible(false);
        } else {
            closeButton.setVisible(false);
        }
    }

    private void readResults() {
        XMLDecoder input = null;
        try {
            input = new XMLDecoder(new FileInputStream(Files.RESULTS_FILE));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameOverWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {
            try {
                results.add((Result) input.readObject());
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        input.close();
    }

    private void writeResults() {
        XMLEncoder output = null;
        try {
            output = new XMLEncoder(new FileOutputStream(Files.RESULTS_FILE));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameOverWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i = 0;
        for (Result result : results) {
            if (i >= NUMBER_OF_BEST_SCORES) {
                break;
            }
            output.writeObject(result);
            i++;
        }
        output.close();
    }

    private String getResultsText() {
        String htmlText = "<html>Best scores:<br/>";
        for (Result result : results) {
            if (result != null) {
                htmlText = htmlText + result.getPlayerName() + "<br/>"
                        + "&nbsp;&nbsp;&nbsp;&nbsp;villains killed: " + result.getKilledVillains() + "<br/>"
                        + "&nbsp;&nbsp;&nbsp;&nbsp;time: " + (int) (result.getTime() / 60) + " minutes "
                        + (int) (result.getTime() % 60) + " seconds<br/>";
            }
        }
        return htmlText + "</html>";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        killedLabel = new javax.swing.JLabel();
        yourNameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        oldResultsLabel = new javax.swing.JLabel();
        resultsLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        timeLabel = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Game over");

        killedLabel.setText("Villains killed: ");

        yourNameLabel.setText("Your name: ");

        oldResultsLabel.setText("Best scores:");

        resultsLabel.setText("results");
        resultsLabel.setToolTipText("");
        resultsLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        saveButton.setText("save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        timeLabel.setText("Time: ");

        closeButton.setText("close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(resultsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(yourNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(oldResultsLabel)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(closeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton))
                    .addComponent(killedLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(timeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(killedLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeLabel)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yourNameLabel)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(oldResultsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resultsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(closeButton))
                .addGap(30, 30, 30))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        lastResult.setPlayerName(nameTextField.getText());
        for (Result result : results) {
            if (lastResult.isBetterThan(result)) {
                results.add(results.indexOf(result), lastResult);
                break;
            }
        }
        writeResults();
        this.dispose();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel killedLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel oldResultsLabel;
    private javax.swing.JLabel resultsLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel yourNameLabel;
    // End of variables declaration//GEN-END:variables
}
