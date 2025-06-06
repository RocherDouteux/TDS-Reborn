package isilimageprocessing.Dialogues;

import java.awt.Color;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class JDialogMorpho extends javax.swing.JDialog {

    private final boolean lockTable;

    private int[][] masque;
    private boolean isFilled;
    
    private int tailleMasque;
    private int iter;
    
    /**
     * Creates new form JDialogMasque
     * @param parent
     * @param modal
     * @param lockTable
     */
    public JDialogMorpho(java.awt.Frame parent, boolean modal, boolean lockTable) {
        super(parent, modal);
        initComponents();
        
        SpinnerNumberModel oddModel = new SpinnerNumberModel(3, 1, 21, 2);
        jSpinnerDimension.setModel(oddModel);
        
        SpinnerNumberModel iterModel = new SpinnerNumberModel(10, 1, 100, 1);
        jSpinnerIteration.setModel(iterModel);
        
        this.masque = null;
        this.isFilled = false;
        
        this.lockTable = lockTable;
        this.jLabelMode.setText(lockTable ? "Table désactivée" : "Table activée");
        this.jLabelMode.setForeground(lockTable ? new Color(100, 0, 0) : new Color(0, 100, 0));
        this.tailleMasque = 3;
        
        this.jTableMasque.setEnabled(!lockTable);
        this.resetTable();
    }
    
    private void resetTable(){
        if(this.lockTable){
            return;
        }
        
        DefaultTableModel model = new DefaultTableModel(this.tailleMasque, this.tailleMasque);
        
        String[] names = new String[this.tailleMasque];
        for (int i = 0; i < this.tailleMasque; i++) {
            names[i] = String.valueOf(i);
        }
        
        model.setColumnIdentifiers(names);
        
        this.jTableMasque.setModel(model);
    }
    
    public int[][] getMasque(){
        return this.masque;
    }
    
    public boolean isFilled(){
        return this.isFilled;
    }
    
    public int getTailleMasque(){
        return this.tailleMasque;
    }
    
    public int getIter(){
        return this.iter;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBase = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMasque = new javax.swing.JTable();
        jSpinnerDimension = new javax.swing.JSpinner();
        jLabelDimension = new javax.swing.JLabel();
        jButtonSubmitDimension = new javax.swing.JButton();
        jLabelMode = new javax.swing.JLabel();
        jLabelIteration = new javax.swing.JLabel();
        jSpinnerIteration = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jTableMasque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                " 0", " 1", " 2"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableMasque);
        if (jTableMasque.getColumnModel().getColumnCount() > 0) {
            jTableMasque.getColumnModel().getColumn(0).setResizable(false);
            jTableMasque.getColumnModel().getColumn(1).setResizable(false);
            jTableMasque.getColumnModel().getColumn(2).setResizable(false);
        }

        jSpinnerDimension.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerDimensionStateChanged(evt);
            }
        });

        jLabelDimension.setText("Taille masque :");

        jButtonSubmitDimension.setText("OK");
        jButtonSubmitDimension.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubmitDimensionActionPerformed(evt);
            }
        });

        jLabelMode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMode.setText("MODE");

        jLabelIteration.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelIteration.setText("Iteration :");

        jSpinnerIteration.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerIterationStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelBaseLayout = new javax.swing.GroupLayout(jPanelBase);
        jPanelBase.setLayout(jPanelBaseLayout);
        jPanelBaseLayout.setHorizontalGroup(
            jPanelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBaseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanelBaseLayout.createSequentialGroup()
                        .addGroup(jPanelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelIteration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelDimension, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBaseLayout.createSequentialGroup()
                                .addComponent(jSpinnerDimension, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonSubmitDimension))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBaseLayout.createSequentialGroup()
                                .addComponent(jSpinnerIteration, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelMode, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(172, 172, 172)))))
                .addContainerGap())
        );
        jPanelBaseLayout.setVerticalGroup(
            jPanelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBaseLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinnerDimension, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDimension)
                    .addComponent(jButtonSubmitDimension))
                .addGap(2, 2, 2)
                .addGroup(jPanelBaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMode)
                    .addComponent(jLabelIteration)
                    .addComponent(jSpinnerIteration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSpinnerDimensionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerDimensionStateChanged
        this.tailleMasque = (int)this.jSpinnerDimension.getValue();
        this.resetTable();
    }//GEN-LAST:event_jSpinnerDimensionStateChanged

    private void jButtonSubmitDimensionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubmitDimensionActionPerformed

        if(!this.lockTable){
            int rows = this.jTableMasque.getRowCount();
            int cols = this.jTableMasque.getColumnCount();

            this.masque = new int[rows][cols];

            for(int i = 0; i < rows; i++){
                for(int j = 0; j < cols; j++){
                    Object value = this.jTableMasque.getValueAt(i, j);
                    try {
                        if(value != null){
                            masque[i][j] = Integer.parseInt(value.toString());
                        } else {
                            masque[i][j] = 0;
                        }
                    } catch(NumberFormatException e){
                        masque[i][j] = 0;
                    }
                }
            }
        }
        
        this.tailleMasque = (int)this.jSpinnerDimension.getValue();
        this.isFilled = true;
        this.dispose();
    }//GEN-LAST:event_jButtonSubmitDimensionActionPerformed

    private void jSpinnerIterationStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerIterationStateChanged
        this.iter = (int)this.jSpinnerIteration.getValue();
    }//GEN-LAST:event_jSpinnerIterationStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDialogMorpho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogMorpho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogMorpho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogMorpho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            JDialogMorpho dialog = new JDialogMorpho(new javax.swing.JFrame(), true, true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSubmitDimension;
    private javax.swing.JLabel jLabelDimension;
    private javax.swing.JLabel jLabelIteration;
    private javax.swing.JLabel jLabelMode;
    private javax.swing.JPanel jPanelBase;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerDimension;
    private javax.swing.JSpinner jSpinnerIteration;
    private javax.swing.JTable jTableMasque;
    // End of variables declaration//GEN-END:variables
}
