package pl.gui;

import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.board.City;
import pl.powerSource.Capability;
import pl.powerSource.PowerSource;
import pl.resources.Files;

/**
 * *
 * Window shows actual information about City
 *
 * @author Krzysztof Olejniczak
 */
public class CityInfoWindow extends javax.swing.JFrame implements Runnable {

    private static final int TIME_TO_SLEEP = 500;
    private static final DecimalFormat DOUBLE_VALUES_FORMAT = new DecimalFormat("#.#");

    private final City city;

    private boolean running;

    /**
     * *
     * Constructor of window
     *
     * @param city city described in window
     */
    public CityInfoWindow(City city) {
        initComponents();
        this.city = city;
        running = true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        numberOfCitizensLabel = new javax.swing.JLabel();
        destroyedLabel = new javax.swing.JLabel();
        capitalCityLabel = new javax.swing.JLabel();
        sideLengthLabel = new javax.swing.JLabel();
        powerSourcesLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("City");
        setIconImages(null);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        nameLabel.setText("City: ");

        numberOfCitizensLabel.setText("Number of citizens: ");

        destroyedLabel.setText("Destroyed: ");

        capitalCityLabel.setText("Capital city: ");

        sideLengthLabel.setText("Size: ");

        powerSourcesLabel.setText("Power sources: ");
        powerSourcesLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(destroyedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imageLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(numberOfCitizensLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(capitalCityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sideLengthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(powerSourcesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(numberOfCitizensLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sideLengthLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(capitalCityLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(destroyedLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(powerSourcesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        running = false;
        city.setInfoWindow(null);
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel capitalCityLabel;
    private javax.swing.JLabel destroyedLabel;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel numberOfCitizensLabel;
    private javax.swing.JLabel powerSourcesLabel;
    private javax.swing.JLabel sideLengthLabel;
    // End of variables declaration//GEN-END:variables

    /**
     * *
     * Thread function, which updates window's fields
     */
    @Override
    public void run() {
        while (running) {
            nameLabel.setText(city.getName());
            numberOfCitizensLabel.setText("Number of citizens: " + city.getNumberOfCitizens());
            sideLengthLabel.setText("Size: " + city.getSideLength());

            if (city.isDestroyed()) {
                destroyedLabel.setText("The city has been destroyed");
                imageLabel.setIcon(new javax.swing.ImageIcon(Files.getImage(Files.DESTROYED_CITY_IMAGE_FILE)));
            } else {
                imageLabel.setIcon(new javax.swing.ImageIcon(Files.getImage(Files.CITY_IMAGE_FILE)));
                destroyedLabel.setText("The city is alive");
            }

            if (city.isCapital()) {
                capitalCityLabel.setText("Capital city");
            } else {
                capitalCityLabel.setText("");
            }

            String powerSourcesText = "<html>Power sources:<br/>";
            for (PowerSource powerSource : city.getPowerSources()) {
                powerSourcesText = powerSourcesText + "-&nbsp;" + powerSource.getName() + "<br/>"
                        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;capability: "
                        + Capability.capabilityToString(powerSource.getCapabilityType()) + "<br/>"
                        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;potential: "
                        + DOUBLE_VALUES_FORMAT.format(powerSource.getPotential()) + "<br/>";
            }
            powerSourcesText = powerSourcesText + "</html>";
            powerSourcesLabel.setText(powerSourcesText);

            try {
                Thread.sleep(TIME_TO_SLEEP);
            } catch (InterruptedException ex) {
                Logger.getLogger(CityInfoWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.dispose();
    }
}
