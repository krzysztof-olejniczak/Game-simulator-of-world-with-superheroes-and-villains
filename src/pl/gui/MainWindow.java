package pl.gui;

import java.awt.Dimension;
import java.util.Date;
import pl.Main.Main;
import pl.board.Board;
import pl.population.Population;
import pl.resources.maps.MapDefinition;

/**
 * *
 * Game's main window
 *
 * @author Krzysztof Olejniczak
 */
public class MainWindow extends javax.swing.JFrame {

    private static final int TOP_PANEL_HEIGHT = 60;
    private static final int MINIMUM_SIZE_OF_WINDOW = 160;
    private static final int MESSAGE_TEXT_AREA_WIDTH = 60;
    private static final int MESSAGE_TEXT_AREA_HEIGHT = 10;

    private final ObjectsPanel objectsPanel;

    private final Board board;
    private final Population population;

    /**
     * Main Window constructor
     *
     * @param map game's map definiton
     * @param board game's board
     * @param population game's board
     */
    public MainWindow(MapDefinition map, Board board, Population population) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info
                    : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameOverWindow.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }

        Dimension sizeOfWindow;
        if (board.getWidthInPixels() < MINIMUM_SIZE_OF_WINDOW) {
            sizeOfWindow = new java.awt.Dimension(MINIMUM_SIZE_OF_WINDOW,
                    board.getHeightInPixels() + TOP_PANEL_HEIGHT);
        } else {
            sizeOfWindow = new java.awt.Dimension(board.getWidthInPixels(),
                    board.getHeightInPixels() + TOP_PANEL_HEIGHT);
        }

        this.setMinimumSize(sizeOfWindow);
        this.setMaximumSize(sizeOfWindow);
        initComponents();
        panelOfButtons.setSize(board.getWidthInPixels(), TOP_PANEL_HEIGHT);
        messageTextArea.setBounds(messageTextArea.getX(), messageTextArea.getY(),
                MESSAGE_TEXT_AREA_WIDTH, MESSAGE_TEXT_AREA_HEIGHT);

        this.board = board;
        this.population = population;

        objectsPanel = new ObjectsPanel(map.getBackgroundImage(), this, board, population);
        getContentPane().add(objectsPanel);
        objectsPanel.setBounds(0, TOP_PANEL_HEIGHT,
                board.getWidthInPixels(), board.getHeightInPixels());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelOfButtons = new javax.swing.JPanel();
        gameRunningButton = new javax.swing.JButton();
        addSuperheroButton = new javax.swing.JButton();
        addCivilianButton = new javax.swing.JButton();
        messageTextArea = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Game simulator of world with superheroes and villains");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(null);

        panelOfButtons.setBackground(new java.awt.Color(157, 174, 174));

        gameRunningButton.setText("stop game");
        gameRunningButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameRunningButtonActionPerformed(evt);
            }
        });

        addSuperheroButton.setText("Add superhero");
        addSuperheroButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSuperheroButtonActionPerformed(evt);
            }
        });

        addCivilianButton.setText("Add civilian");
        addCivilianButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCivilianButtonActionPerformed(evt);
            }
        });

        messageTextArea.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout panelOfButtonsLayout = new javax.swing.GroupLayout(panelOfButtons);
        panelOfButtons.setLayout(panelOfButtonsLayout);
        panelOfButtonsLayout.setHorizontalGroup(
            panelOfButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOfButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gameRunningButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addSuperheroButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addCivilianButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(messageTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        panelOfButtonsLayout.setVerticalGroup(
            panelOfButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOfButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOfButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelOfButtonsLayout.createSequentialGroup()
                        .addGroup(panelOfButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gameRunningButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addSuperheroButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addCivilianButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(messageTextArea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(panelOfButtons);
        panelOfButtons.setBounds(0, 0, 670, 60);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addCivilianButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCivilianButtonActionPerformed
        NewCivilianWindow newCivilianWindow = new NewCivilianWindow(board, population);
        newCivilianWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        newCivilianWindow.setVisible(true);
    }//GEN-LAST:event_addCivilianButtonActionPerformed

    private void gameRunningButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gameRunningButtonActionPerformed
        Main.reverseGameRunning();
        if (Main.isGameRunning()) {
            gameRunningButton.setText("stop game");
            Main.increaseTotalTimeOfBreaks((int) (((new Date()).getTime() - Main.getTimeOfStartBreak().getTime()) / 1000.0));
        } else {
            gameRunningButton.setText("run game");
            Main.setTimeOfStartBreak(new Date());
        }
    }//GEN-LAST:event_gameRunningButtonActionPerformed

    private void addSuperheroButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSuperheroButtonActionPerformed
        NewSuperheroWindow newSuperheroWindow = new NewSuperheroWindow(board, population);
        newSuperheroWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        newSuperheroWindow.setVisible(true);
    }//GEN-LAST:event_addSuperheroButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCivilianButton;
    private javax.swing.JButton addSuperheroButton;
    private javax.swing.JButton gameRunningButton;
    private javax.swing.JLabel messageTextArea;
    private javax.swing.JPanel panelOfButtons;
    // End of variables declaration//GEN-END:variables

    /**
     * Sets all buttons as not visible
     */
    public void setButtonsNotVisible() {
        gameRunningButton.setVisible(false);
        addCivilianButton.setVisible(false);
        addSuperheroButton.setVisible(false);
    }

    /**
     * Gets objects panel
     *
     * @return objects panel
     */
    public ObjectsPanel getObjectsPanel() {
        return objectsPanel;
    }

    /**
     * Sets message text in top panel of main window
     *
     * @param message message to set
     */
    public void setMessageText(String message) {
        messageTextArea.setText(message);
    }

}
