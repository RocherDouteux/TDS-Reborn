package isilimageprocessing.Dialogues;

import CImage.CImage;
import javax.swing.*;
import java.awt.*;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadingDialog extends JDialog {

    private JLabel gearLabel;
    private static final Logger logger = Logger.getLogger(LoadingDialog.class.getName());

    public LoadingDialog(Frame parent, String title) {
        super(parent, title, true);
        initUI();
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setSize(200, 200);
        setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel("Traitement en cours", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(0x4A4A4A));
        gbc.gridy = 0;
        add(titleLabel, gbc);

        ImageIcon gearIcon = new ImageIcon("src/Icones/gear_spinning_100.gif");
        gearLabel = new JLabel(gearIcon);
        gbc.gridy = 2;
        gbc.weighty = 0.1;
        add(gearLabel, gbc);

        getContentPane().setBackground(Color.WHITE);
    }


    public Queue<CImage> executeTask(Callable<Queue<CImage>> task) {
        FutureTask<Queue<CImage>> futureTask = new FutureTask<>(task);

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                Thread backgroundThread = new Thread(futureTask);
                backgroundThread.start();
                try {
                    futureTask.get();
                } catch (InterruptedException | ExecutionException e) {
                    logger.log(Level.SEVERE, "Error during task execution", e);
                    JOptionPane.showMessageDialog(LoadingDialog.this, 
                        "Error processing images: " + e.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }

            @Override
            protected void done() {
                dispose();
            }
        };

        worker.execute();
        setVisible(true);

        try {
            return futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            logger.log(Level.SEVERE, "Error getting task result", e);
            return null;
        }
    }
}