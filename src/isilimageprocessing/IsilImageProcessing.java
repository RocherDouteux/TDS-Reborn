package isilimageprocessing;

import Application.Application;
import CImage.*;
import CImage.Exceptions.*;
import CImage.Observers.Events.*;
import ImageProcessing.Contours.ContoursLineaire;
import ImageProcessing.Contours.ContoursNonLineaire;
import ImageProcessing.Complexe.MatriceComplexe;
import ImageProcessing.Fourier.Fourier;
import ImageProcessing.Histogramme.Histogramme;
import ImageProcessing.Lineaire.FiltrageLineaireGlobal;
import ImageProcessing.Lineaire.FiltrageLineaireLocal;
import ImageProcessing.NonLineaire.MorphoElementaire;
import ImageProcessing.NonLineaire.MorphoComplexe;
import ImageProcessing.Seuillage.Seuillage;
import ImageProcessing.Utils.Utils;
import isilimageprocessing.Dialogues.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import java.awt.event.ActionEvent;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;


public class IsilImageProcessing extends javax.swing.JFrame implements ClicListener,SelectLigneListener,SelectRectListener,SelectRectFillListener,SelectCercleListener,SelectCercleFillListener 
{

    private CImage liftedImage = null;
    private int liftedFromIndex = -1;
    
    private enum GridMode { SELECT, MOVE }
    private GridMode currentGridMode = GridMode.SELECT;
    private CImage resultImage;
    
    private final Logger logger;
    
    public IsilImageProcessing() 
    {
        initComponents();
        
        logger = Logger.getLogger(IsilImageProcessing.class.getName());
        
        panelResult.setLayout(new BorderLayout());
        panelResult.add(resultPanel, BorderLayout.CENTER);
        panelResult.revalidate();

        setupImageGridSlots();
        
        resultPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (currentGridMode == GridMode.MOVE && resultImage != null) {
                    clearAllSelections();
                    liftedImage = resultImage;
                    liftedFromIndex = -1;
                    resultPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));
                }
            }
        });

        
        // Add move/select toggle manually
        JCheckBoxMenuItem toggleMoveMode = new JCheckBoxMenuItem("Mode Déplacement");
        toggleMoveMode.addActionListener(e -> {
            currentGridMode = toggleMoveMode.isSelected() ? GridMode.MOVE : GridMode.SELECT;
        });
        setupModeToggleUI();
        
        // DELETE THE SELECTED IMAGES
        InputMap inputMap = panelGrid.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panelGrid.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke('d'), "deleteSelectedImages");
        actionMap.put("deleteSelectedImages", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedImages();
                System.out.print("DELETE");
            }
        });
        
        // IT'S OVER
        
        jMenuDessiner.setEnabled(false);
        jMenuFourier.setEnabled(false);
        jMenuHistogramme.setEnabled(false);
        jMenuLineaire.setEnabled(false);
        jMenuTraitement.setEnabled(false);
        jMenuContours.setEnabled(false);
        jMenuSeuillage.setEnabled(false);
        jMenuUtils.setEnabled(false);
        jMenuApplication.setEnabled(true);
    }
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupDessiner = new javax.swing.ButtonGroup();
        panelResult = new javax.swing.JPanel();
        resultPanel = new javax.swing.JLabel();
        panelGrid = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuImage = new javax.swing.JMenu();
        jMenuNouvelle = new javax.swing.JMenu();
        jMenuItemNouvelleRGB = new javax.swing.JMenuItem();
        jMenuItemNouvelleNG = new javax.swing.JMenuItem();
        jMenuOuvrir = new javax.swing.JMenu();
        jMenuItemOuvrirRGB = new javax.swing.JMenuItem();
        jMenuItemOuvrirNG = new javax.swing.JMenuItem();
        jMenuItemEnregistrerSous = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuQuitter = new javax.swing.JMenuItem();
        jMenuDessiner = new javax.swing.JMenu();
        jMenuItemCouleurPinceau = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jCheckBoxMenuItemDessinerPixel = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDessinerLigne = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDessinerRectangle = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDessinerRectanglePlein = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDessinerCercle = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemDessinerCerclePlein = new javax.swing.JCheckBoxMenuItem();
        jMenuFourier = new javax.swing.JMenu();
        jMenuFourierAfficher = new javax.swing.JMenu();
        jMenuItemFourierAfficherModule = new javax.swing.JMenuItem();
        jMenuItemFourierAfficherPhase = new javax.swing.JMenuItem();
        jMenuItemFourierAfficherPartieReelle = new javax.swing.JMenuItem();
        jMenuItemFourierAfficherPartieImaginaire = new javax.swing.JMenuItem();
        jMenuHistogramme = new javax.swing.JMenu();
        jMenuHistogrammeAfficher = new javax.swing.JMenuItem();
        jMenuAfficherParametresImage = new javax.swing.JMenuItem();
        jMenuItemCourbeTonaleLineaireSansSaturation = new javax.swing.JMenuItem();
        jMenuItemCourbeTonaleLineaireAvecSaturation = new javax.swing.JMenuItem();
        jMenuItemCourbeTonaleGamma = new javax.swing.JMenuItem();
        jMenuItemCourbeTonaleNegatif = new javax.swing.JMenuItem();
        jMenuItemCourbeTonaleEgalisation = new javax.swing.JMenuItem();
        jMenuUtils = new javax.swing.JMenu();
        jMenuItemUtilsAddition = new javax.swing.JMenuItem();
        jMenuItemUtilsSoustraction = new javax.swing.JMenuItem();
        jMenuItemUtilsRGBToNG = new javax.swing.JMenuItem();
        jMenuItemUtilsNGToRGB = new javax.swing.JMenuItem();
        jMenuItemUtilsAdditionRGB = new javax.swing.JMenuItem();
        jMenuItemAndNGEtRGB = new javax.swing.JMenuItem();
        jMenuLineaire = new javax.swing.JMenu();
        jMenuLineaireGlobal = new javax.swing.JMenu();
        jMenuItemFiltrageLineaireGlobalPasseBas = new javax.swing.JMenuItem();
        jMenuItemFiltrageLineaireGlobalPasseHaut = new javax.swing.JMenuItem();
        jMenuItemFiltrageLineaireGlobalPasseBasButterworth = new javax.swing.JMenuItem();
        jMenuItemFiltrageLineaireGlobalPasseHautButterworth = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemFiltrageLineaireLocalFiltrageConvolution = new javax.swing.JMenuItem();
        jMenuItemFiltrageLineaireLocalFiltrageMoyenneur = new javax.swing.JMenuItem();
        jMenuTraitement = new javax.swing.JMenu();
        jMenuTraitementElementaire = new javax.swing.JMenu();
        jMenuItemTraitementLineaireMorphologieElementaireErosion = new javax.swing.JMenuItem();
        jMenuItemTraitementLineaireMorphologieElementaireDilatation = new javax.swing.JMenuItem();
        jMenuItemTraitementLineaireMorphologieElementaireOuverture = new javax.swing.JMenuItem();
        jMenuItemTraitementLineaireMorphologieElementaireFermeture = new javax.swing.JMenuItem();
        jMenuTraitementComplexe = new javax.swing.JMenu();
        jMenuItemTraitementLineaireMorphologieComplexeDilatationGeodesique = new javax.swing.JMenuItem();
        jMenuItemTraitementLineaireMorphologieComplexeReconstructionGeodesique = new javax.swing.JMenuItem();
        jMenuItemTraitementLineaireMorphologieComplexeFiltreMedian = new javax.swing.JMenuItem();
        jMenuContours = new javax.swing.JMenu();
        jMenuContoursLineaire = new javax.swing.JMenu();
        jMenuItemContoursLineairePrewitt = new javax.swing.JMenuItem();
        jMenuItemContoursLineaireSobel = new javax.swing.JMenuItem();
        jMenuItemContoursLineaireLaplacien4 = new javax.swing.JMenuItem();
        jMenuItemContoursLineaireLaplacien8 = new javax.swing.JMenuItem();
        jMenuContoursNonLineaire = new javax.swing.JMenu();
        jMenuItemContoursNonLineaireErosion = new javax.swing.JMenuItem();
        jMenuItemContoursNonLineaireDilatation = new javax.swing.JMenuItem();
        jMenuItemContoursNonLineaireBeucher = new javax.swing.JMenuItem();
        jMenuItemContoursNonLineaireLaplacien = new javax.swing.JMenuItem();
        jMenuSeuillage = new javax.swing.JMenu();
        jMenuItemSeuillageSimple = new javax.swing.JMenuItem();
        jMenuItemSeuillageDouble = new javax.swing.JMenuItem();
        jMenuItemSeuillageAutomatique = new javax.swing.JMenuItem();
        jMenuApplication = new javax.swing.JMenu();
        jMenuApplication1 = new javax.swing.JMenu();
        jMenuItemQuestion1A = new javax.swing.JMenuItem();
        jMenuItemQuestion1B = new javax.swing.JMenuItem();
        jMenuApplication2 = new javax.swing.JMenu();
        jMenuItemQuestion2A = new javax.swing.JMenuItem();
        jMenuItemQuestion2B = new javax.swing.JMenuItem();
        jMenuItemQuestion3 = new javax.swing.JMenuItem();
        jMenuItemQuestion4 = new javax.swing.JMenuItem();
        jMenuItemQuestion5 = new javax.swing.JMenuItem();
        jMenu2Question6 = new javax.swing.JMenu();
        jMenuItemQuestion6A = new javax.swing.JMenuItem();
        jMenuItemQuestion6B = new javax.swing.JMenuItem();
        jMenuItemQuestion7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Isil Image Processing");

        panelResult.setBackground(new java.awt.Color(255, 51, 51));
        panelResult.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelResult.setPreferredSize(new java.awt.Dimension(150, 100));
        panelResult.add(resultPanel);

        getContentPane().add(panelResult, java.awt.BorderLayout.EAST);

        panelGrid.setBackground(new java.awt.Color(102, 51, 255));
        panelGrid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelGrid.setLayout(new java.awt.GridLayout(3, 3));

        jLabel9.setText("jLabel9");
        panelGrid.add(jLabel9);

        jLabel8.setText("jLabel8");
        panelGrid.add(jLabel8);

        jLabel7.setText("jLabel7");
        panelGrid.add(jLabel7);

        jLabel6.setText("jLabel6");
        panelGrid.add(jLabel6);

        jLabel5.setText("jLabel5");
        panelGrid.add(jLabel5);

        jLabel4.setText("jLabel4");
        panelGrid.add(jLabel4);

        jLabel3.setText("jLabel3");
        panelGrid.add(jLabel3);

        jLabel2.setText("jLabel2");
        panelGrid.add(jLabel2);

        jLabel1.setText("jLabel1");
        panelGrid.add(jLabel1);

        getContentPane().add(panelGrid, java.awt.BorderLayout.CENTER);

        jMenuImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Net_13_p1.jpg"))); // NOI18N
        jMenuImage.setText("Image");

        jMenuNouvelle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/File_65_p3.jpg"))); // NOI18N
        jMenuNouvelle.setText("Nouvelle");

        jMenuItemNouvelleRGB.setText("Image RGB");
        jMenuItemNouvelleRGB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNouvelleRGBActionPerformed(evt);
            }
        });
        jMenuNouvelle.add(jMenuItemNouvelleRGB);

        jMenuItemNouvelleNG.setText("Image NG");
        jMenuItemNouvelleNG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNouvelleNGActionPerformed(evt);
            }
        });
        jMenuNouvelle.add(jMenuItemNouvelleNG);

        jMenuImage.add(jMenuNouvelle);

        jMenuOuvrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Folder_036_p3.jpg"))); // NOI18N
        jMenuOuvrir.setText("Ouvrir");

        jMenuItemOuvrirRGB.setText("Image RGB");
        jMenuItemOuvrirRGB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOuvrirRGBActionPerformed(evt);
            }
        });
        jMenuOuvrir.add(jMenuItemOuvrirRGB);

        jMenuItemOuvrirNG.setText("Image NG");
        jMenuItemOuvrirNG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOuvrirNGActionPerformed(evt);
            }
        });
        jMenuOuvrir.add(jMenuItemOuvrirNG);

        jMenuImage.add(jMenuOuvrir);

        jMenuItemEnregistrerSous.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/DD_27_p3.jpg"))); // NOI18N
        jMenuItemEnregistrerSous.setText("Enregistrer sous...");
        jMenuItemEnregistrerSous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEnregistrerSousActionPerformed(evt);
            }
        });
        jMenuImage.add(jMenuItemEnregistrerSous);
        jMenuImage.add(jSeparator1);

        jMenuQuitter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/CP_59_p3.jpg"))); // NOI18N
        jMenuQuitter.setText("Quitter");
        jMenuQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuQuitterActionPerformed(evt);
            }
        });
        jMenuImage.add(jMenuQuitter);

        jMenuBar1.add(jMenuImage);

        jMenuDessiner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Display_28_p1.jpg"))); // NOI18N
        jMenuDessiner.setText("Dessiner");

        jMenuItemCouleurPinceau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/Display_14_p3.jpg"))); // NOI18N
        jMenuItemCouleurPinceau.setText("Couleur");
        jMenuItemCouleurPinceau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCouleurPinceauActionPerformed(evt);
            }
        });
        jMenuDessiner.add(jMenuItemCouleurPinceau);
        jMenuDessiner.add(jSeparator2);

        jCheckBoxMenuItemDessinerPixel.setText("Pixel");
        jCheckBoxMenuItemDessinerPixel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemDessinerPixelActionPerformed(evt);
            }
        });
        jMenuDessiner.add(jCheckBoxMenuItemDessinerPixel);

        jCheckBoxMenuItemDessinerLigne.setText("Ligne");
        jCheckBoxMenuItemDessinerLigne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemDessinerLigneActionPerformed(evt);
            }
        });
        jMenuDessiner.add(jCheckBoxMenuItemDessinerLigne);

        jCheckBoxMenuItemDessinerRectangle.setText("Rectangle");
        jCheckBoxMenuItemDessinerRectangle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemDessinerRectangleActionPerformed(evt);
            }
        });
        jMenuDessiner.add(jCheckBoxMenuItemDessinerRectangle);

        jCheckBoxMenuItemDessinerRectanglePlein.setText("Rectangle plein");
        jCheckBoxMenuItemDessinerRectanglePlein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemDessinerRectanglePleinActionPerformed(evt);
            }
        });
        jMenuDessiner.add(jCheckBoxMenuItemDessinerRectanglePlein);

        jCheckBoxMenuItemDessinerCercle.setText("Cercle");
        jCheckBoxMenuItemDessinerCercle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemDessinerCercleActionPerformed(evt);
            }
        });
        jMenuDessiner.add(jCheckBoxMenuItemDessinerCercle);

        jCheckBoxMenuItemDessinerCerclePlein.setText("Cercle plein");
        jCheckBoxMenuItemDessinerCerclePlein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemDessinerCerclePleinActionPerformed(evt);
            }
        });
        jMenuDessiner.add(jCheckBoxMenuItemDessinerCerclePlein);

        jMenuBar1.add(jMenuDessiner);

        jMenuFourier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/CP_51_p1.jpg"))); // NOI18N
        jMenuFourier.setText("Fourier");

        jMenuFourierAfficher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/CP_51_p3.jpg"))); // NOI18N
        jMenuFourierAfficher.setText("Afficher");

        jMenuItemFourierAfficherModule.setText("Module");
        jMenuItemFourierAfficherModule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFourierAfficherModuleActionPerformed(evt);
            }
        });
        jMenuFourierAfficher.add(jMenuItemFourierAfficherModule);

        jMenuItemFourierAfficherPhase.setText("Phase");
        jMenuItemFourierAfficherPhase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFourierAfficherPhaseActionPerformed(evt);
            }
        });
        jMenuFourierAfficher.add(jMenuItemFourierAfficherPhase);

        jMenuItemFourierAfficherPartieReelle.setText("Partie Reelle");
        jMenuItemFourierAfficherPartieReelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFourierAfficherPartieReelleActionPerformed(evt);
            }
        });
        jMenuFourierAfficher.add(jMenuItemFourierAfficherPartieReelle);

        jMenuItemFourierAfficherPartieImaginaire.setText("Partie Imaginaire");
        jMenuItemFourierAfficherPartieImaginaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFourierAfficherPartieImaginaireActionPerformed(evt);
            }
        });
        jMenuFourierAfficher.add(jMenuItemFourierAfficherPartieImaginaire);

        jMenuFourier.add(jMenuFourierAfficher);

        jMenuBar1.add(jMenuFourier);

        jMenuHistogramme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/report_48_hot.jpg"))); // NOI18N
        jMenuHistogramme.setText("Histogramme");

        jMenuHistogrammeAfficher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/report_32_hot.jpg"))); // NOI18N
        jMenuHistogrammeAfficher.setText("Afficher l'histogramme");
        jMenuHistogrammeAfficher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuHistogrammeAfficherActionPerformed(evt);
            }
        });
        jMenuHistogramme.add(jMenuHistogrammeAfficher);

        jMenuAfficherParametresImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/report_32_hot.jpg"))); // NOI18N
        jMenuAfficherParametresImage.setText("Afficher les paramètres de l'image");
        jMenuAfficherParametresImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAfficherParametresImageActionPerformed(evt);
            }
        });
        jMenuHistogramme.add(jMenuAfficherParametresImage);

        jMenuItemCourbeTonaleLineaireSansSaturation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/courbe_32.png"))); // NOI18N
        jMenuItemCourbeTonaleLineaireSansSaturation.setText("CT Linéaire sans saturation");
        jMenuItemCourbeTonaleLineaireSansSaturation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCourbeTonaleLineaireSansSaturationActionPerformed(evt);
            }
        });
        jMenuHistogramme.add(jMenuItemCourbeTonaleLineaireSansSaturation);

        jMenuItemCourbeTonaleLineaireAvecSaturation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/courbe_32.png"))); // NOI18N
        jMenuItemCourbeTonaleLineaireAvecSaturation.setText("CT Linéaire avec saturation");
        jMenuItemCourbeTonaleLineaireAvecSaturation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCourbeTonaleLineaireAvecSaturationActionPerformed(evt);
            }
        });
        jMenuHistogramme.add(jMenuItemCourbeTonaleLineaireAvecSaturation);

        jMenuItemCourbeTonaleGamma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/courbe_32.png"))); // NOI18N
        jMenuItemCourbeTonaleGamma.setText("CT Gamma");
        jMenuItemCourbeTonaleGamma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCourbeTonaleGammaActionPerformed(evt);
            }
        });
        jMenuHistogramme.add(jMenuItemCourbeTonaleGamma);

        jMenuItemCourbeTonaleNegatif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/courbe_32.png"))); // NOI18N
        jMenuItemCourbeTonaleNegatif.setText("CT Negatif");
        jMenuItemCourbeTonaleNegatif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCourbeTonaleNegatifActionPerformed(evt);
            }
        });
        jMenuHistogramme.add(jMenuItemCourbeTonaleNegatif);

        jMenuItemCourbeTonaleEgalisation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/courbe_32.png"))); // NOI18N
        jMenuItemCourbeTonaleEgalisation.setText("CT Egalisation");
        jMenuItemCourbeTonaleEgalisation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCourbeTonaleEgalisationActionPerformed(evt);
            }
        });
        jMenuHistogramme.add(jMenuItemCourbeTonaleEgalisation);

        jMenuBar1.add(jMenuHistogramme);

        jMenuUtils.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/utils_48.png"))); // NOI18N
        jMenuUtils.setText("Utils");

        jMenuItemUtilsAddition.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/plus_32.png"))); // NOI18N
        jMenuItemUtilsAddition.setText("Addition");
        jMenuItemUtilsAddition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUtilsAdditionActionPerformed(evt);
            }
        });
        jMenuUtils.add(jMenuItemUtilsAddition);

        jMenuItemUtilsSoustraction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/moins_32.png"))); // NOI18N
        jMenuItemUtilsSoustraction.setText("Soustraction");
        jMenuItemUtilsSoustraction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUtilsSoustractionActionPerformed(evt);
            }
        });
        jMenuUtils.add(jMenuItemUtilsSoustraction);

        jMenuItemUtilsRGBToNG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/utils_rgb_to_ng_32.png"))); // NOI18N
        jMenuItemUtilsRGBToNG.setText("Canaux RGB");
        jMenuItemUtilsRGBToNG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUtilsRGBToNGActionPerformed(evt);
            }
        });
        jMenuUtils.add(jMenuItemUtilsRGBToNG);

        jMenuItemUtilsNGToRGB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/utils_ng_to_rgb_32.png"))); // NOI18N
        jMenuItemUtilsNGToRGB.setText("NG -> RGB");
        jMenuItemUtilsNGToRGB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUtilsNGToRGBActionPerformed(evt);
            }
        });
        jMenuUtils.add(jMenuItemUtilsNGToRGB);

        jMenuItemUtilsAdditionRGB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/addition_rgb_32.png"))); // NOI18N
        jMenuItemUtilsAdditionRGB.setText("Addition RGB");
        jMenuItemUtilsAdditionRGB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUtilsAdditionRGBActionPerformed(evt);
            }
        });
        jMenuUtils.add(jMenuItemUtilsAdditionRGB);

        jMenuItemAndNGEtRGB.setText("And");
        jMenuItemAndNGEtRGB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAndNGEtRGBActionPerformed(evt);
            }
        });
        jMenuUtils.add(jMenuItemAndNGEtRGB);

        jMenuBar1.add(jMenuUtils);

        jMenuLineaire.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/filtrage_48.png"))); // NOI18N
        jMenuLineaire.setText("Filtrage Lineaire");

        jMenuLineaireGlobal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/filtrage_32.png"))); // NOI18N
        jMenuLineaireGlobal.setText("Global");

        jMenuItemFiltrageLineaireGlobalPasseBas.setText("Filtrage passe-bas");
        jMenuItemFiltrageLineaireGlobalPasseBas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFiltrageLineaireGlobalPasseBasActionPerformed(evt);
            }
        });
        jMenuLineaireGlobal.add(jMenuItemFiltrageLineaireGlobalPasseBas);

        jMenuItemFiltrageLineaireGlobalPasseHaut.setText("Filtrage passe-haut");
        jMenuItemFiltrageLineaireGlobalPasseHaut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFiltrageLineaireGlobalPasseHautActionPerformed(evt);
            }
        });
        jMenuLineaireGlobal.add(jMenuItemFiltrageLineaireGlobalPasseHaut);

        jMenuItemFiltrageLineaireGlobalPasseBasButterworth.setText("Filtrage passe-bas butterworth");
        jMenuItemFiltrageLineaireGlobalPasseBasButterworth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFiltrageLineaireGlobalPasseBasButterworthActionPerformed(evt);
            }
        });
        jMenuLineaireGlobal.add(jMenuItemFiltrageLineaireGlobalPasseBasButterworth);

        jMenuItemFiltrageLineaireGlobalPasseHautButterworth.setText("Filtrage passe-haut butterworth");
        jMenuItemFiltrageLineaireGlobalPasseHautButterworth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFiltrageLineaireGlobalPasseHautButterworthActionPerformed(evt);
            }
        });
        jMenuLineaireGlobal.add(jMenuItemFiltrageLineaireGlobalPasseHautButterworth);

        jMenuLineaire.add(jMenuLineaireGlobal);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/filtrage_32.png"))); // NOI18N
        jMenu1.setText("Local");

        jMenuItemFiltrageLineaireLocalFiltrageConvolution.setText("Filtrage convolution");
        jMenuItemFiltrageLineaireLocalFiltrageConvolution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFiltrageLineaireLocalFiltrageConvolutionActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemFiltrageLineaireLocalFiltrageConvolution);

        jMenuItemFiltrageLineaireLocalFiltrageMoyenneur.setText("Filtrage moyenneur");
        jMenuItemFiltrageLineaireLocalFiltrageMoyenneur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFiltrageLineaireLocalFiltrageMoyenneurActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemFiltrageLineaireLocalFiltrageMoyenneur);

        jMenuLineaire.add(jMenu1);

        jMenuBar1.add(jMenuLineaire);

        jMenuTraitement.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/traitement_48.png"))); // NOI18N
        jMenuTraitement.setText("Traitement Non-Lineaire");
        jMenuTraitement.setToolTipText("");

        jMenuTraitementElementaire.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/traitement_32.png"))); // NOI18N
        jMenuTraitementElementaire.setText("Morphologie Elementaire");

        jMenuItemTraitementLineaireMorphologieElementaireErosion.setText("Erosion");
        jMenuItemTraitementLineaireMorphologieElementaireErosion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTraitementLineaireMorphologieElementaireErosionActionPerformed(evt);
            }
        });
        jMenuTraitementElementaire.add(jMenuItemTraitementLineaireMorphologieElementaireErosion);

        jMenuItemTraitementLineaireMorphologieElementaireDilatation.setText("Dilatation");
        jMenuItemTraitementLineaireMorphologieElementaireDilatation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTraitementLineaireMorphologieElementaireDilatationActionPerformed(evt);
            }
        });
        jMenuTraitementElementaire.add(jMenuItemTraitementLineaireMorphologieElementaireDilatation);

        jMenuItemTraitementLineaireMorphologieElementaireOuverture.setText("Ouverture");
        jMenuItemTraitementLineaireMorphologieElementaireOuverture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTraitementLineaireMorphologieElementaireOuvertureActionPerformed(evt);
            }
        });
        jMenuTraitementElementaire.add(jMenuItemTraitementLineaireMorphologieElementaireOuverture);

        jMenuItemTraitementLineaireMorphologieElementaireFermeture.setText("Fermeture");
        jMenuItemTraitementLineaireMorphologieElementaireFermeture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTraitementLineaireMorphologieElementaireFermetureActionPerformed(evt);
            }
        });
        jMenuTraitementElementaire.add(jMenuItemTraitementLineaireMorphologieElementaireFermeture);

        jMenuTraitement.add(jMenuTraitementElementaire);

        jMenuTraitementComplexe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/traitement_32.png"))); // NOI18N
        jMenuTraitementComplexe.setText("Morphologie Complexe");
        jMenuTraitementComplexe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuTraitementComplexeActionPerformed(evt);
            }
        });

        jMenuItemTraitementLineaireMorphologieComplexeDilatationGeodesique.setText("Dilatation Geodesique");
        jMenuItemTraitementLineaireMorphologieComplexeDilatationGeodesique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTraitementLineaireMorphologieComplexeDilatationGeodesiqueActionPerformed(evt);
            }
        });
        jMenuTraitementComplexe.add(jMenuItemTraitementLineaireMorphologieComplexeDilatationGeodesique);

        jMenuItemTraitementLineaireMorphologieComplexeReconstructionGeodesique.setText("Reconstruction Geodesique");
        jMenuItemTraitementLineaireMorphologieComplexeReconstructionGeodesique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTraitementLineaireMorphologieComplexeReconstructionGeodesiqueActionPerformed(evt);
            }
        });
        jMenuTraitementComplexe.add(jMenuItemTraitementLineaireMorphologieComplexeReconstructionGeodesique);

        jMenuItemTraitementLineaireMorphologieComplexeFiltreMedian.setText("Filtre Median");
        jMenuItemTraitementLineaireMorphologieComplexeFiltreMedian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTraitementLineaireMorphologieComplexeFiltreMedianActionPerformed(evt);
            }
        });
        jMenuTraitementComplexe.add(jMenuItemTraitementLineaireMorphologieComplexeFiltreMedian);

        jMenuTraitement.add(jMenuTraitementComplexe);

        jMenuBar1.add(jMenuTraitement);

        jMenuContours.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/contours_48.png"))); // NOI18N
        jMenuContours.setText("Contours");

        jMenuContoursLineaire.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/contours_32.png"))); // NOI18N
        jMenuContoursLineaire.setText("Linéaire");

        jMenuItemContoursLineairePrewitt.setText("Prewitt");
        jMenuItemContoursLineairePrewitt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemContoursLineairePrewittActionPerformed(evt);
            }
        });
        jMenuContoursLineaire.add(jMenuItemContoursLineairePrewitt);

        jMenuItemContoursLineaireSobel.setText("Sobel");
        jMenuItemContoursLineaireSobel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemContoursLineaireSobelActionPerformed(evt);
            }
        });
        jMenuContoursLineaire.add(jMenuItemContoursLineaireSobel);

        jMenuItemContoursLineaireLaplacien4.setText("Laplacien 4");
        jMenuItemContoursLineaireLaplacien4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemContoursLineaireLaplacien4ActionPerformed(evt);
            }
        });
        jMenuContoursLineaire.add(jMenuItemContoursLineaireLaplacien4);

        jMenuItemContoursLineaireLaplacien8.setText("Laplacien 8");
        jMenuItemContoursLineaireLaplacien8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemContoursLineaireLaplacien8ActionPerformed(evt);
            }
        });
        jMenuContoursLineaire.add(jMenuItemContoursLineaireLaplacien8);

        jMenuContours.add(jMenuContoursLineaire);

        jMenuContoursNonLineaire.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/contours_32.png"))); // NOI18N
        jMenuContoursNonLineaire.setText("Non-linéaire");

        jMenuItemContoursNonLineaireErosion.setText("Erosion");
        jMenuItemContoursNonLineaireErosion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemContoursNonLineaireErosionActionPerformed(evt);
            }
        });
        jMenuContoursNonLineaire.add(jMenuItemContoursNonLineaireErosion);

        jMenuItemContoursNonLineaireDilatation.setText("Dilatation");
        jMenuItemContoursNonLineaireDilatation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemContoursNonLineaireDilatationActionPerformed(evt);
            }
        });
        jMenuContoursNonLineaire.add(jMenuItemContoursNonLineaireDilatation);

        jMenuItemContoursNonLineaireBeucher.setText("Beucher");
        jMenuItemContoursNonLineaireBeucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemContoursNonLineaireBeucherActionPerformed(evt);
            }
        });
        jMenuContoursNonLineaire.add(jMenuItemContoursNonLineaireBeucher);

        jMenuItemContoursNonLineaireLaplacien.setText("Laplacien");
        jMenuItemContoursNonLineaireLaplacien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemContoursNonLineaireLaplacienActionPerformed(evt);
            }
        });
        jMenuContoursNonLineaire.add(jMenuItemContoursNonLineaireLaplacien);

        jMenuContours.add(jMenuContoursNonLineaire);

        jMenuBar1.add(jMenuContours);

        jMenuSeuillage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/seuillage_48.png"))); // NOI18N
        jMenuSeuillage.setText("Seuillage");

        jMenuItemSeuillageSimple.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/seuillage_32.png"))); // NOI18N
        jMenuItemSeuillageSimple.setText("Simple");
        jMenuItemSeuillageSimple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSeuillageSimpleActionPerformed(evt);
            }
        });
        jMenuSeuillage.add(jMenuItemSeuillageSimple);

        jMenuItemSeuillageDouble.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/seuillage_32.png"))); // NOI18N
        jMenuItemSeuillageDouble.setText("Double");
        jMenuItemSeuillageDouble.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSeuillageDoubleActionPerformed(evt);
            }
        });
        jMenuSeuillage.add(jMenuItemSeuillageDouble);

        jMenuItemSeuillageAutomatique.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/seuillage_32.png"))); // NOI18N
        jMenuItemSeuillageAutomatique.setText("Automatique");
        jMenuItemSeuillageAutomatique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSeuillageAutomatiqueActionPerformed(evt);
            }
        });
        jMenuSeuillage.add(jMenuItemSeuillageAutomatique);

        jMenuBar1.add(jMenuSeuillage);

        jMenuApplication.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/application_48.png"))); // NOI18N
        jMenuApplication.setText("Application");

        jMenuApplication1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/1_32.png"))); // NOI18N
        jMenuApplication1.setText("Question 1");

        jMenuItemQuestion1A.setText("A");
        jMenuItemQuestion1A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemQuestion1AActionPerformed(evt);
            }
        });
        jMenuApplication1.add(jMenuItemQuestion1A);

        jMenuItemQuestion1B.setText("B");
        jMenuItemQuestion1B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemQuestion1BActionPerformed(evt);
            }
        });
        jMenuApplication1.add(jMenuItemQuestion1B);

        jMenuApplication.add(jMenuApplication1);

        jMenuApplication2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/2_32.png"))); // NOI18N
        jMenuApplication2.setText("Question 2");

        jMenuItemQuestion2A.setText("A");
        jMenuItemQuestion2A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemQuestion2AActionPerformed(evt);
            }
        });
        jMenuApplication2.add(jMenuItemQuestion2A);

        jMenuItemQuestion2B.setText("B");
        jMenuItemQuestion2B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemQuestion2BActionPerformed(evt);
            }
        });
        jMenuApplication2.add(jMenuItemQuestion2B);

        jMenuApplication.add(jMenuApplication2);

        jMenuItemQuestion3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/3_32.png"))); // NOI18N
        jMenuItemQuestion3.setText("Question 3");
        jMenuItemQuestion3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemQuestion3ActionPerformed(evt);
            }
        });
        jMenuApplication.add(jMenuItemQuestion3);

        jMenuItemQuestion4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/4_32.png"))); // NOI18N
        jMenuItemQuestion4.setText("Question 4");
        jMenuItemQuestion4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemQuestion4ActionPerformed(evt);
            }
        });
        jMenuApplication.add(jMenuItemQuestion4);

        jMenuItemQuestion5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/5_32.png"))); // NOI18N
        jMenuItemQuestion5.setText("Question 5");
        jMenuItemQuestion5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemQuestion5ActionPerformed(evt);
            }
        });
        jMenuApplication.add(jMenuItemQuestion5);

        jMenu2Question6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/6_32.png"))); // NOI18N
        jMenu2Question6.setText("Question 6");

        jMenuItemQuestion6A.setText("A");
        jMenuItemQuestion6A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemQuestion6AActionPerformed(evt);
            }
        });
        jMenu2Question6.add(jMenuItemQuestion6A);

        jMenuItemQuestion6B.setText("B");
        jMenuItemQuestion6B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemQuestion6BActionPerformed(evt);
            }
        });
        jMenu2Question6.add(jMenuItemQuestion6B);

        jMenuApplication.add(jMenu2Question6);

        jMenuItemQuestion7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/7_32.png"))); // NOI18N
        jMenuItemQuestion7.setText("Question 7");
        jMenuItemQuestion7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemQuestion7ActionPerformed(evt);
            }
        });
        jMenuApplication.add(jMenuItemQuestion7);

        jMenuBar1.add(jMenuApplication);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(1302, 782));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // ANTOINE WAS HERE
    private void showResultImage(CImage result) {
        this.resultImage = result;

        Image img = result.getImage();
        Image scaled = img.getScaledInstance(resultPanel.getWidth(), resultPanel.getHeight(), Image.SCALE_SMOOTH);
        resultPanel.setIcon(new ImageIcon(scaled));
        resultPanel.revalidate();
        resultPanel.repaint();
    }

    private void jMenuHistogrammeAfficherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuHistogrammeAfficherActionPerformed
        int histo[];
        try 
        {
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            int[][] f_int = selectedNG.getMatrice();
            histo = Histogramme.Histogramme256(f_int);
        } 
        catch (CImageNGException ex) 
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
            return;
        }
        
        XYSeries serie = new XYSeries("Histo");
        for(int i=0 ; i<256 ; i++) serie.add(i,histo[i]);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(serie);
        
        JFreeChart chart = ChartFactory.createHistogram("Histogramme","Niveaux de gris","Nombre de pixels",dataset,PlotOrientation.VERTICAL,false,false,false);

        XYPlot plot = (XYPlot)chart.getXYPlot();
        ValueAxis axeX = plot.getDomainAxis();
        axeX.setRange(0,255);
        plot.setDomainAxis(axeX);
        
        ChartFrame frame = new ChartFrame("Histogramme de l'image",chart);
        frame.pack();
        frame.setVisible(true);
    }//GEN-LAST:event_jMenuHistogrammeAfficherActionPerformed

    private void activeMenus()
    {
        jMenuDessiner.setEnabled(false);
        jMenuFourier.setEnabled(true);
        jMenuHistogramme.setEnabled(true);
        jMenuLineaire.setEnabled(true);
        jMenuTraitement.setEnabled(true);
        jMenuContours.setEnabled(true);
        jMenuSeuillage.setEnabled(true);
        jMenuUtils.setEnabled(true);
        jMenuApplication.setEnabled(true);
    }
   
    
    private void desactiveMenus(){
        jMenuDessiner.setEnabled(false);
        jMenuFourier.setEnabled(false);
        jMenuHistogramme.setEnabled(false);
        jMenuLineaire.setEnabled(false);
        jMenuTraitement.setEnabled(false);
        jMenuContours.setEnabled(false);
        jMenuSeuillage.setEnabled(false);
        jMenuUtils.setEnabled(false);
        jMenuApplication.setEnabled(true);
    }
    
    private void jCheckBoxMenuItemDessinerCerclePleinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDessinerCerclePleinActionPerformed
        
    }//GEN-LAST:event_jCheckBoxMenuItemDessinerCerclePleinActionPerformed

    private void jCheckBoxMenuItemDessinerCercleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDessinerCercleActionPerformed
        
    }//GEN-LAST:event_jCheckBoxMenuItemDessinerCercleActionPerformed

    private void jMenuItemFourierAfficherPartieImaginaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFourierAfficherPartieImaginaireActionPerformed
        try 
        {
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            int[][] f_int = selectedNG.getMatrice();
            
            double[][] f = new double[selectedNG.getLargeur()][selectedNG.getHauteur()];
            for(int i=0 ; i<selectedNG.getLargeur() ; i++)
                for(int j=0 ; j<selectedNG.getHauteur() ; j++) f[i][j] = (double)(f_int[i][j]);
            
            System.out.println("Debut Fourier");
            MatriceComplexe fourier = Fourier.Fourier2D(f);
            System.out.println("Fin Fourier");
            fourier = Fourier.decroise(fourier);
            double partieImaginaire[][] = fourier.getPartieImaginaire();
            
            JDialogAfficheMatriceDouble dialog = new JDialogAfficheMatriceDouble(this,true,partieImaginaire,"Fourier : Affichage de la partie imaginaire");
            dialog.setVisible(true);
        } 
        catch (CImageNGException ex) 
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }
    }//GEN-LAST:event_jMenuItemFourierAfficherPartieImaginaireActionPerformed

    private void jMenuItemFourierAfficherPartieReelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFourierAfficherPartieReelleActionPerformed
        try 
        {
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            int[][] f_int = selectedNG.getMatrice();
            
            double[][] f = new double[selectedNG.getLargeur()][selectedNG.getHauteur()];
            for(int i=0 ; i<selectedNG.getLargeur() ; i++)
                for(int j=0 ; j<selectedNG.getHauteur() ; j++) f[i][j] = (double)(f_int[i][j]);
            
            System.out.println("Debut Fourier");
            MatriceComplexe fourier = Fourier.Fourier2D(f);
            System.out.println("Fin Fourier");
            fourier = Fourier.decroise(fourier);
            double partieReelle[][] = fourier.getPartieReelle();
            
            JDialogAfficheMatriceDouble dialog = new JDialogAfficheMatriceDouble(this,true,partieReelle,"Fourier : Affichage de la partie reelle");
            dialog.setVisible(true);
        } 
        catch (CImageNGException ex) 
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }

    }//GEN-LAST:event_jMenuItemFourierAfficherPartieReelleActionPerformed

    private void jMenuItemFourierAfficherPhaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFourierAfficherPhaseActionPerformed
        try 
        {
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            int[][] f_int = selectedNG.getMatrice();
            
            double[][] f = new double[selectedNG.getLargeur()][selectedNG.getHauteur()];
            for(int i=0 ; i<selectedNG.getLargeur() ; i++)
                for(int j=0 ; j<selectedNG.getHauteur() ; j++) f[i][j] = (double)(f_int[i][j]);
            
            System.out.println("Debut Fourier");
            MatriceComplexe fourier = Fourier.Fourier2D(f);
            System.out.println("Fin Fourier");
            fourier = Fourier.decroise(fourier);
            double phase[][] = fourier.getPhase();
            
            JDialogAfficheMatriceDouble dialog = new JDialogAfficheMatriceDouble(this,true,phase,"Fourier : Affichage de la phase");
            dialog.setVisible(true);
        } 
        catch (CImageNGException ex) 
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }

    }//GEN-LAST:event_jMenuItemFourierAfficherPhaseActionPerformed

    private void jMenuItemFourierAfficherModuleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFourierAfficherModuleActionPerformed
        try 
        {
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            int[][] f_int = selectedNG.getMatrice();
            
            double[][] f = new double[selectedNG.getLargeur()][selectedNG.getHauteur()];
            for(int i=0 ; i<selectedNG.getLargeur() ; i++)
                for(int j=0 ; j<selectedNG.getHauteur() ; j++) f[i][j] = (double)(f_int[i][j]);
            
            System.out.println("Debut Fourier");
            MatriceComplexe fourier = Fourier.Fourier2D(f);
            System.out.println("Fin Fourier");
            fourier = Fourier.decroise(fourier);
            double module[][] = fourier.getModule();
            
            JDialogAfficheMatriceDouble dialog = new JDialogAfficheMatriceDouble(this,true,module,"Fourier : Affichage du module");
            dialog.setVisible(true);
        } 
        catch (CImageNGException ex) 
        {
            System.out.println("Erreur CImageNG : " + ex.getMessage());
        }
    }//GEN-LAST:event_jMenuItemFourierAfficherModuleActionPerformed

    private void jCheckBoxMenuItemDessinerPixelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDessinerPixelActionPerformed

    }//GEN-LAST:event_jCheckBoxMenuItemDessinerPixelActionPerformed

    private void jMenuItemEnregistrerSousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEnregistrerSousActionPerformed
        JFileChooser choix = new JFileChooser();
        File fichier;
        
        choix.setCurrentDirectory(new File ("."));
        if (choix.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            fichier = choix.getSelectedFile();
            if (fichier != null)
            {
                try 
                {
                    
                    CImage selected = getSelectedImage();
                    if (selected == null) {
                        JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image.");
                        return;
                    }
                    
                    if(selected instanceof CImageNG selectedNG){
                        selectedNG.enregistreFormatPNG(fichier);
                    }
                    
                    if(selected instanceof CImageRGB selectedRGB){
                        selectedRGB.enregistreFormatPNG(fichier);
                    }

                    
                } 
                catch (IOException ex) 
                {
                    System.err.println("Erreur I/O : " + ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemEnregistrerSousActionPerformed

    
    
    private void jMenuItemOuvrirNGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOuvrirNGActionPerformed
        JFileChooser choix = new JFileChooser();
        File fichier;

        choix.setCurrentDirectory(new File("."));
        if (choix.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            fichier = choix.getSelectedFile();
            if (fichier != null) {
                try {
                    CImageNG imageNG = new CImageNG(fichier);

                    int targetSlot = getSelectedSlotOrFirstEmpty();

                    if (targetSlot != -1) {
                        setImageToSlot(targetSlot, imageNG);
                        clearAllSelections();
                    } else {
                        JOptionPane.showMessageDialog(this, "Aucune case disponible !", "Erreur", JOptionPane.WARNING_MESSAGE);
                    }

                    activeMenus();
                } catch (IOException ex) {
                    System.err.println("Erreur I/O : " + ex.getMessage());
                }
            }
        }

    }//GEN-LAST:event_jMenuItemOuvrirNGActionPerformed

    private void jMenuItemNouvelleNGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNouvelleNGActionPerformed

    }//GEN-LAST:event_jMenuItemNouvelleNGActionPerformed

    private void jMenuItemCouleurPinceauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCouleurPinceauActionPerformed

    }//GEN-LAST:event_jMenuItemCouleurPinceauActionPerformed

    private void jCheckBoxMenuItemDessinerRectanglePleinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDessinerRectanglePleinActionPerformed

    }//GEN-LAST:event_jCheckBoxMenuItemDessinerRectanglePleinActionPerformed

    private void jCheckBoxMenuItemDessinerRectangleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDessinerRectangleActionPerformed

    }//GEN-LAST:event_jCheckBoxMenuItemDessinerRectangleActionPerformed

    private void jCheckBoxMenuItemDessinerLigneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemDessinerLigneActionPerformed

    }//GEN-LAST:event_jCheckBoxMenuItemDessinerLigneActionPerformed

    private void jMenuItemNouvelleRGBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNouvelleRGBActionPerformed

    }//GEN-LAST:event_jMenuItemNouvelleRGBActionPerformed

    private void jMenuQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuQuitterActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuQuitterActionPerformed

    private void jMenuItemOuvrirRGBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOuvrirRGBActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (file != null) {
                try {
                    CImageRGB imageRGB = new CImageRGB(file);
                    int targetSlot = getSelectedSlot();
                    if (targetSlot == -1) targetSlot = getFirstAvailableSlot();
                    if (targetSlot != -1) {
                        setImageToSlot(targetSlot, imageRGB);
                        clearAllSelections();
                    }
                    activeMenus();
                } catch (IOException ex) {
                    System.err.println("Erreur I/O : " + ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemOuvrirRGBActionPerformed

    private void jMenuItemFiltrageLineaireGlobalPasseBasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFiltrageLineaireGlobalPasseBasActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            
            JDialogFiltrageLineaireGlobal dialog = new JDialogFiltrageLineaireGlobal(this, true, false);
            dialog.setVisible(true);
            
            if(!dialog.isFilled())
                return;
            
            int frequenceDeCoupure = dialog.getFrequenceDeCoupure();

            // Apply filter
            int[][] data = FiltrageLineaireGlobal.filtrePasseBasIdeal(selectedNG.getMatrice(), frequenceDeCoupure);
            data = Utils.normaliserImage(data, 0, 255);
            
            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);

        } catch (CImageNGException | HeadlessException e) {
            System.out.print("Erreur filtre passe-bas: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemFiltrageLineaireGlobalPasseBasActionPerformed

    private void jMenuItemFiltrageLineaireGlobalPasseHautActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFiltrageLineaireGlobalPasseHautActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            
            JDialogFiltrageLineaireGlobal dialog = new JDialogFiltrageLineaireGlobal(this, true, false);
            dialog.setVisible(true);
            
            if(!dialog.isFilled())
                return;
            
            int frequenceDeCoupure = dialog.getFrequenceDeCoupure();
            
            // Apply filter
            int[][] data = FiltrageLineaireGlobal.filtrePasseHautIdeal(selectedNG.getMatrice(), frequenceDeCoupure);
            data = Utils.normaliserImage(data, 0, 255);

            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);
            
        }catch (CImageNGException | HeadlessException e){
            System.out.print("Erreur filtre passe-haut: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemFiltrageLineaireGlobalPasseHautActionPerformed

    private void jMenuItemFiltrageLineaireGlobalPasseBasButterworthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFiltrageLineaireGlobalPasseBasButterworthActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            
            JDialogFiltrageLineaireGlobal dialog = new JDialogFiltrageLineaireGlobal(this, true, true);
            dialog.setVisible(true);
            
            if(!dialog.isFilled())
                return;
            
            int frequenceDeCoupure = dialog.getFrequenceDeCoupure();
            int ordre = dialog.getOrdre();
            
            // Apply filter
            int[][] data = FiltrageLineaireGlobal.filtrePasseHautButterworth(selectedNG.getMatrice(), frequenceDeCoupure, ordre);
            data = Utils.normaliserImage(data, 0, 255);

            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);
            
        }catch (CImageNGException | HeadlessException e){
            System.out.print("Erreur filtre passe-haut butterworth: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemFiltrageLineaireGlobalPasseBasButterworthActionPerformed

    private void jMenuItemFiltrageLineaireGlobalPasseHautButterworthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFiltrageLineaireGlobalPasseHautButterworthActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            
            JDialogFiltrageLineaireGlobal dialog = new JDialogFiltrageLineaireGlobal(this, true, true);
            dialog.setVisible(true);
            
            if(!dialog.isFilled())
                return;
            
            int frequenceDeCoupure = dialog.getFrequenceDeCoupure();
            int ordre = dialog.getOrdre();
            
            // Apply filter
            int[][] data = FiltrageLineaireGlobal.filtrePasseBasButterworth(selectedNG.getMatrice(), frequenceDeCoupure, ordre);
            data = Utils.normaliserImage(data, 0, 255);

            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);
            
        }catch (CImageNGException | HeadlessException e){
            System.out.print("Erreur filtre passe-bas butterworth: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemFiltrageLineaireGlobalPasseHautButterworthActionPerformed

    private void jMenuItemFiltrageLineaireLocalFiltrageConvolutionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFiltrageLineaireLocalFiltrageConvolutionActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            
            JDialogFiltrageLineaireLocal choixMasque = new JDialogFiltrageLineaireLocal(this, true, false);
            choixMasque.setVisible(true);
            
            if(!choixMasque.isFilled())
                return;
            
            double[][] masque = choixMasque.getMasque();
            
            int[][] data = FiltrageLineaireLocal.filtreMasqueConvolution(selectedNG.getMatrice(), masque);
            data = Utils.normaliserImage(data, 0, 255);

            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);
            
        }catch (CImageNGException | HeadlessException e){
            System.out.print("Erreur filtre convolution: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemFiltrageLineaireLocalFiltrageConvolutionActionPerformed

    private void jMenuItemFiltrageLineaireLocalFiltrageMoyenneurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFiltrageLineaireLocalFiltrageMoyenneurActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            
            JDialogFiltrageLineaireLocal choixMasque = new JDialogFiltrageLineaireLocal(this, true, true);
            choixMasque.setVisible(true);
            
            if(!choixMasque.isFilled())
                return;
            
            int tailleMasque = choixMasque.getTailleMasque();
            
            int[][] data = FiltrageLineaireLocal.filtreMoyenneur(selectedNG.getMatrice(), tailleMasque);
            data = Utils.normaliserImage(data, 0, 255);

            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);
            
        }catch (CImageNGException | HeadlessException e){
            System.out.print("Erreur filtre moyenneur: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemFiltrageLineaireLocalFiltrageMoyenneurActionPerformed

    private void jMenuItemTraitementLineaireMorphologieElementaireErosionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTraitementLineaireMorphologieElementaireErosionActionPerformed
        try{
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            int[][] image = selectedNG.getMatrice();
            
            if(Utils.estImageBinaire(image)){
                image = Utils.convertirBinaireVersNiveauxDeGris(image);
            }
            
            JDialogMorpho morpho = new JDialogMorpho(this, true, true);
            morpho.setVisible(true);
            
            if(!morpho.isFilled())
                return;
            
            int tailleMasque = morpho.getTailleMasque();
            
            int[][] data = MorphoElementaire.erosion(image, tailleMasque);
            data = Utils.normaliserImage(data, 0, 255);

            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);            
            
        }catch (CImageNGException | HeadlessException e){
            System.out.print("Erreur morphologie elementaire erosion: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemTraitementLineaireMorphologieElementaireErosionActionPerformed

    private void jMenuItemTraitementLineaireMorphologieElementaireDilatationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTraitementLineaireMorphologieElementaireDilatationActionPerformed
        try{
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            int[][] image = selectedNG.getMatrice();
            
            if(Utils.estImageBinaire(image)){
                image = Utils.convertirBinaireVersNiveauxDeGris(image);
            }
            
            JDialogMorpho morpho = new JDialogMorpho(this, true, true);
            morpho.setVisible(true);
            
            if(!morpho.isFilled())
                return;
            
            int tailleMasque = morpho.getTailleMasque();
            
            int[][] data = MorphoElementaire.dilatation(image, tailleMasque);
            data = Utils.normaliserImage(data, 0, 255);

            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);
            
        }catch (CImageNGException | HeadlessException e){
            System.out.print("Erreur morphologie elementaire dilatation: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemTraitementLineaireMorphologieElementaireDilatationActionPerformed

    private void jMenuItemTraitementLineaireMorphologieElementaireOuvertureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTraitementLineaireMorphologieElementaireOuvertureActionPerformed
        try{
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            int[][] image = selectedNG.getMatrice();
            
            if(Utils.estImageBinaire(image)){
                image = Utils.convertirBinaireVersNiveauxDeGris(image);
            }
            
            JDialogMorpho morpho = new JDialogMorpho(this, true, true);
            morpho.setVisible(true);
            
            if(!morpho.isFilled())
                return;
            
            int tailleMasque = morpho.getTailleMasque();
            
            int[][] data = MorphoElementaire.ouverture(image, tailleMasque);
            data = Utils.normaliserImage(data, 0, 255);

            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);
            
        }catch (CImageNGException | HeadlessException e){
            System.out.print("Erreur morphologie elementaire ouverture: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemTraitementLineaireMorphologieElementaireOuvertureActionPerformed

    private void jMenuItemTraitementLineaireMorphologieElementaireFermetureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTraitementLineaireMorphologieElementaireFermetureActionPerformed
        try{
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            int[][] image = selectedNG.getMatrice();
            
            if(Utils.estImageBinaire(image)){
                image = Utils.convertirBinaireVersNiveauxDeGris(image);
            }
            
            JDialogMorpho morpho = new JDialogMorpho(this, true, true);
            morpho.setVisible(true);
            
            if(!morpho.isFilled())
                return;
            
            int tailleMasque = morpho.getTailleMasque();
            
            int[][] data = MorphoElementaire.fermeture(image, tailleMasque);
            data = Utils.normaliserImage(data, 0, 255);

            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);
            
        }catch (CImageNGException | HeadlessException e){
            System.out.print("Erreur morphologie elementaire fermeture: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemTraitementLineaireMorphologieElementaireFermetureActionPerformed

    private void jMenuItemTraitementLineaireMorphologieComplexeDilatationGeodesiqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTraitementLineaireMorphologieComplexeDilatationGeodesiqueActionPerformed
        try {
            CImage[] selectedImages = getAllSelectedImages();

            if (selectedImages == null || selectedImages.length != 2) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner exactement deux images NG.");
                return;
            }

            for (CImage selectedImage : selectedImages) {
                if (!(selectedImage instanceof CImageNG)) {
                    JOptionPane.showMessageDialog(this, "Les deux images doivent être de type NG.");
                    return;
                }
            }

        // Extract images
        CImageNG imgInit = (CImageNG) selectedImages[0]; // Image à dilater
        CImageNG imgMasque = (CImageNG) selectedImages[1]; // Masque géodésique

        int[][] image = imgInit.getMatrice();
        int[][] masqueGeodesique = imgMasque.getMatrice();
        
        JDialogMorpho morpho = new JDialogMorpho(this, true, true);
        morpho.setVisible(true);
        
        if(!morpho.isFilled())
            return;
        
        int nbIter = morpho.getIter();

        int[][] data = MorphoComplexe.dilatationGeodesique(image, masqueGeodesique, nbIter);
        data = Utils.normaliserImage(data, 0, 255);

        // Display result
        CImageNG updatedImage = new CImageNG(data);
        showResultImage(updatedImage);

    } catch (CImageNGException | HeadlessException e) {
        System.out.println("Erreur Dilatation Géodésique: " + e.getMessage());
    }
    
    }//GEN-LAST:event_jMenuItemTraitementLineaireMorphologieComplexeDilatationGeodesiqueActionPerformed

    private void jMenuItemTraitementLineaireMorphologieComplexeReconstructionGeodesiqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTraitementLineaireMorphologieComplexeReconstructionGeodesiqueActionPerformed
        try {
            CImage[] selectedImages = getAllSelectedImages();

            if (selectedImages == null || selectedImages.length != 2) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner exactement deux images NG.");
                return;
            }

            for (CImage selectedImage : selectedImages) {
                if (!(selectedImage instanceof CImageNG)) {
                    JOptionPane.showMessageDialog(this, "Les deux images doivent être de type NG.");
                    return;
                }
            }

            // Extract images
            CImageNG imgInit = (CImageNG) selectedImages[0]; // Image à dilater
            CImageNG imgMasque = (CImageNG) selectedImages[1]; // Masque géodésique

            int[][] image = imgInit.getMatrice();
            int[][] masqueGeodesique = imgMasque.getMatrice();

            int[][] data = MorphoComplexe.reconstructionGeodesique(image, masqueGeodesique);
            data = Utils.normaliserImage(data, 0, 255);

            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);

        } catch (CImageNGException | HeadlessException e) {
            System.out.println("Erreur Dilatation Géodésique: " + e.getMessage());
        }
        
    }//GEN-LAST:event_jMenuItemTraitementLineaireMorphologieComplexeReconstructionGeodesiqueActionPerformed
    
    private void jMenuTraitementComplexeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuTraitementComplexeActionPerformed
        //
    }//GEN-LAST:event_jMenuTraitementComplexeActionPerformed

    private void jMenuItemTraitementLineaireMorphologieComplexeFiltreMedianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTraitementLineaireMorphologieComplexeFiltreMedianActionPerformed
        try {
            CImage selectedImage = getSelectedImage();


            if (!(selectedImage instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "L'images doit être de type NG.");
                return;
            }

            // Change selectedImage -> CImageNG
            CImageNG selectedImageNG = (CImageNG) selectedImage;
            int[][] image = selectedImageNG.getMatrice();

            JDialogMorpho morpho = new JDialogMorpho(this, true, true);
            morpho.setVisible(true);

            if(!morpho.isFilled())
                return;

            int tailleMasque = morpho.getTailleMasque();

            int[][] data = MorphoComplexe.filtreMedian(image, tailleMasque);
            data = Utils.normaliserImage(data, 0, 255);

            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);

        } catch (CImageNGException | HeadlessException e) {
          System.out.println("Erreur Dilatation Géodésique: " + e.getMessage());
      }
    }//GEN-LAST:event_jMenuItemTraitementLineaireMorphologieComplexeFiltreMedianActionPerformed

    private void jMenuAfficherParametresImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAfficherParametresImageActionPerformed
        try {
            CImage selectedImage = getSelectedImage();

            if (!(selectedImage instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "L'images doit être de type NG.");
                return;
            }

            // Change selectedImage -> CImageNG
            CImageNG selectedImageNG = (CImageNG) selectedImage;
            int[][] image = selectedImageNG.getMatrice();

            int min = Histogramme.minimum(image);
            int max = Histogramme.maximum(image);
            int lum = Histogramme.luminance(image);
            
            double contraste1 = Histogramme.contraste1(image);
            double contraste2 = Histogramme.contraste2(image);
            
            JDialogAfficheInformationImage dialog = new JDialogAfficheInformationImage(this, true, min, max, lum, contraste1, contraste2);
            dialog.setVisible(true);

        } catch (CImageNGException | HeadlessException e) {
            System.out.println("Erreur Dilatation Géodésique: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuAfficherParametresImageActionPerformed

    private void jMenuItemContoursLineairePrewittActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemContoursLineairePrewittActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            
            JDialogContoursLineaire dialog = new JDialogContoursLineaire(this, true);
            dialog.setVisible(true);
            
            if(!dialog.isFilled())
                return;
            
            int dir = dialog.getDir();

            // Apply filter
            int[][] data = ContoursLineaire.gradientPrewitt(selectedNG.getMatrice(), dir);
            data = Utils.normaliserImage(data, 0, 255);
            
            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);

        } catch (CImageNGException | HeadlessException e) {
            System.out.print("Erreur Gradient Prewitt: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemContoursLineairePrewittActionPerformed

    private void jMenuItemContoursLineaireSobelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemContoursLineaireSobelActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            
            JDialogContoursLineaire dialog = new JDialogContoursLineaire(this, true);
            dialog.setVisible(true);
            
            if(!dialog.isFilled())
                return;
            
            int dir = dialog.getDir();

            // Apply filter
            int[][] data = ContoursLineaire.gradientSobel(selectedNG.getMatrice(), dir);
            data = Utils.normaliserImage(data, 0, 255);
            
            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);

        } catch (CImageNGException | HeadlessException e) {
            System.out.print("Erreur Gradient Sobel: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemContoursLineaireSobelActionPerformed

    private void jMenuItemContoursLineaireLaplacien4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemContoursLineaireLaplacien4ActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;

            // Apply filter
            int[][] data = ContoursLineaire.laplacien4(selectedNG.getMatrice());
            data = Utils.normaliserImage(data, 0, 255);
            
            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);

        } catch (CImageNGException | HeadlessException e) {
            System.out.print("Erreur Laplacien 4: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemContoursLineaireLaplacien4ActionPerformed

    private void jMenuItemContoursLineaireLaplacien8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemContoursLineaireLaplacien8ActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;

            // Apply filter
            int[][] data = ContoursLineaire.laplacien8(selectedNG.getMatrice());
            data = Utils.normaliserImage(data, 0, 255);
            
            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);

        } catch (CImageNGException | HeadlessException e) {
            System.out.print("Erreur Laplacien 8: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemContoursLineaireLaplacien8ActionPerformed

    private void jMenuItemContoursNonLineaireErosionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemContoursNonLineaireErosionActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;

            // Apply filter
            int[][] data = ContoursNonLineaire.gradientErosion(selectedNG.getMatrice());
            data = Utils.normaliserImage(data, 0, 255);
            
            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);

        } catch (CImageNGException | HeadlessException e) {
            System.out.print("Erreur Erosion: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemContoursNonLineaireErosionActionPerformed

    private void jMenuItemContoursNonLineaireLaplacienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemContoursNonLineaireLaplacienActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            
            JDialogContoursNonLineaire dialog = new JDialogContoursNonLineaire(this, true);
            dialog.setVisible(true);
            
            if(!dialog.isFilled())
                return;
            
            boolean inverse = dialog.getInverse();

            // Apply filter
            int[][] data = ContoursNonLineaire.laplacienNonLineaire(selectedNG.getMatrice(), inverse);
            data = Utils.normaliserImage(data, 0, 255);
            
            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);

        } catch (CImageNGException | HeadlessException e) {
            System.out.print("Erreur Laplacien: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemContoursNonLineaireLaplacienActionPerformed

    private void jMenuItemContoursNonLineaireDilatationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemContoursNonLineaireDilatationActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;

            // Apply filter
            int[][] data = ContoursNonLineaire.gradientDilatation(selectedNG.getMatrice());
            data = Utils.normaliserImage(data, 0, 255);
            
            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);

        } catch (CImageNGException | HeadlessException e) {
            System.out.print("Erreur Dilatation: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemContoursNonLineaireDilatationActionPerformed

    private void jMenuItemContoursNonLineaireBeucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemContoursNonLineaireBeucherActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            
            JDialogContoursNonLineaire dialog = new JDialogContoursNonLineaire(this, true);
            dialog.setVisible(true);
            
            if(!dialog.isFilled())
                return;
            
            boolean inverse = dialog.getInverse();

            // Apply filter
            int[][] data = ContoursNonLineaire.gradientBeucher(selectedNG.getMatrice(), inverse);
            data = Utils.normaliserImage(data, 0, 255);
            
            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);

        } catch (CImageNGException | HeadlessException e) {
            System.out.print("Erreur Beucher: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemContoursNonLineaireBeucherActionPerformed

    private void jMenuItemSeuillageDoubleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSeuillageDoubleActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            
            JDialogSeuillageDouble dialog = new JDialogSeuillageDouble(this, true);
            dialog.setVisible(true);
            
            if(!dialog.isFilled())
                return;
            
            int seuil1 = dialog.getSeuil1();
            int seuil2 = dialog.getSeuil2();

            // Apply filter
            int[][] data = Seuillage.seuillageDouble(selectedNG.getMatrice(), seuil1, seuil2);
            data = Utils.normaliserImage(data, 0, 255);
            
            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);

        } catch (CImageNGException | HeadlessException e) {
            System.out.print("Erreur Seuillage Double: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemSeuillageDoubleActionPerformed

    private void jMenuItemSeuillageAutomatiqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSeuillageAutomatiqueActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;

            // Apply filter
            int[][] data = Seuillage.seuillageAutomatique(selectedNG.getMatrice());
            data = Utils.normaliserImage(data, 0, 255);
            
            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);

        } catch (CImageNGException | HeadlessException e) {
            System.out.print("Erreur Seuillage Automatique: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemSeuillageAutomatiqueActionPerformed

    private void jMenuItemSeuillageSimpleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSeuillageSimpleActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            
            JDialogSeuillageSimple dialog = new JDialogSeuillageSimple(this, true);
            dialog.setVisible(true);
            
            if(!dialog.isFilled())
                return;
            
            int seuil = dialog.getSeuil();

            // Apply filter
            int[][] data = Seuillage.seuillageSimple(selectedNG.getMatrice(), seuil);
            data = Utils.normaliserImage(data, 0, 255);
            
            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);

        } catch (CImageNGException | HeadlessException e) {
            System.out.print("Erreur Seuillage Simple: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemSeuillageSimpleActionPerformed

    private void jMenuItemCourbeTonaleLineaireSansSaturationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCourbeTonaleLineaireSansSaturationActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            
            int[][] image = selectedNG.getMatrice();
            int min = Histogramme.minimum(image);
            int max = Histogramme.maximum(image);
            int[] courbeTonale = Histogramme.creeCourbeTonaleLineaireSaturation(min, max);
            
            // Apply filter
            int[][] data = Histogramme.rehaussement(image, courbeTonale);
            data = Utils.normaliserImage(data, 0, 255);
            
            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);

        } catch (CImageNGException | HeadlessException e) {
            System.out.print("Erreur Courbe Tonale Linéaire sans Saturation: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemCourbeTonaleLineaireSansSaturationActionPerformed

    private void jMenuItemCourbeTonaleLineaireAvecSaturationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCourbeTonaleLineaireAvecSaturationActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            
            int[][] image = selectedNG.getMatrice();
            
            JDialogCourbeTonaleLineaire dialog = new JDialogCourbeTonaleLineaire(this, true);
            dialog.setVisible(true);
            
            if(!dialog.isFilled())
                return;
            
            int min = dialog.getSeuilMin();
            int max = dialog.getSeuilMax();
            int[] courbeTonale = Histogramme.creeCourbeTonaleLineaireSaturation(min, max);
            
            // Apply filter
            int[][] data = Histogramme.rehaussement(image, courbeTonale);
            data = Utils.normaliserImage(data, 0, 255);
            
            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);

        } catch (CImageNGException | HeadlessException e) {
            System.out.print("Erreur Courbe Tonale Linéaire avec Saturation: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemCourbeTonaleLineaireAvecSaturationActionPerformed

    private void jMenuItemCourbeTonaleGammaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCourbeTonaleGammaActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            
            int[][] image = selectedNG.getMatrice();
            
            JDialogCourbeTonaleGamma dialog = new JDialogCourbeTonaleGamma(this, true);
            dialog.setVisible(true);
            
            if(!dialog.isFilled())
                return;
            
            double gamma = dialog.getGamma();
            int[] courbeTonale = Histogramme.creeCourbeTonaleGamma(gamma);
            
            // Apply filter
            int[][] data = Histogramme.rehaussement(image, courbeTonale);
            data = Utils.normaliserImage(data, 0, 255);
            
            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);

        } catch (CImageNGException | HeadlessException e) {
            System.out.print("Erreur Courbe Tonale Gamma: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemCourbeTonaleGammaActionPerformed

    private void jMenuItemCourbeTonaleNegatifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCourbeTonaleNegatifActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            
            int[][] image = selectedNG.getMatrice();
            int[] courbeTonale = Histogramme.creeCourbeTonaleNegatif();
            
            // Apply filter
            int[][] data = Histogramme.rehaussement(image, courbeTonale);
            data = Utils.normaliserImage(data, 0, 255);
            
            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);

        } catch (CImageNGException | HeadlessException e) {
            System.out.print("Erreur Courbe Tonale Negatif: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemCourbeTonaleNegatifActionPerformed

    private void jMenuItemCourbeTonaleEgalisationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCourbeTonaleEgalisationActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG selectedNG = (CImageNG) selected;
            
            int[][] image = selectedNG.getMatrice();
            int[] courbeTonale = Histogramme.creeCourbeTonaleEgalisation(image);
            
            // Apply filter
            int[][] data = Histogramme.rehaussement(image, courbeTonale);
            data = Utils.normaliserImage(data, 0, 255);
            
            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);

        } catch (CImageNGException | HeadlessException e) {
            System.out.print("Erreur Courbe Tonale Egalisation: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemCourbeTonaleEgalisationActionPerformed

    private void jMenuItemUtilsAdditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUtilsAdditionActionPerformed
        try {
            CImage[] selectedImages = getAllSelectedImages();

            if (selectedImages == null || selectedImages.length != 2) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner exactement deux images NG.");
                return;
            }
            
            for (CImage selectedImage : selectedImages) {
                if (!(selectedImage instanceof CImageNG)) {
                    JOptionPane.showMessageDialog(this, "Les deux images doivent être de type NG.");
                    return;
                }
            }

            // Extract images
            CImageNG image1 = (CImageNG) selectedImages[0];
            CImageNG image2 = (CImageNG) selectedImages[1];

            int[][] data = Utils.addition(image1.getMatrice(), image2.getMatrice());
            data = Utils.normaliserImage(data, 0, 255);

            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);

        } catch (CImageNGException | HeadlessException e) {
            System.out.println("Erreur Addition de 2 images: " + e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemUtilsAdditionActionPerformed

    private void jMenuItemUtilsSoustractionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUtilsSoustractionActionPerformed
        try {
            CImage[] selectedImages = getAllSelectedImages();

            if (selectedImages == null || selectedImages.length != 2) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner exactement deux images NG.");
                return;
            }
            
            for (CImage selectedImage : selectedImages) {
                if (!(selectedImage instanceof CImageNG)) {
                    JOptionPane.showMessageDialog(this, "Les deux images doivent être de type NG.");
                    return;
                }
            }

        // Extract images
        CImageNG image1 = (CImageNG) selectedImages[0];
        CImageNG image2 = (CImageNG) selectedImages[1];

        int[][] data = Utils.soustraction(image1.getMatrice(), image2.getMatrice());
        data = Utils.normaliserImage(data, 0, 255);

        // Display result
        CImageNG updatedImage = new CImageNG(data);
        showResultImage(updatedImage);

    } catch (CImageNGException | HeadlessException e) {
        logger.log(Level.SEVERE, "Erreur Soustraction de 2 images: {0}", e.getMessage());
    }
    }//GEN-LAST:event_jMenuItemUtilsSoustractionActionPerformed

    private void jMenuItemQuestion1AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemQuestion1AActionPerformed
        try{
            
            LoadingDialog loadingDialog = new LoadingDialog(this, "Question 1A");
            
            Callable<Queue<CImage>> task = () -> {
                clearAllImages();
                CImage source = new CImageNG(new File("res/Images/ImagesEtape5/imageBruitee1.png"));
                return Application.question1A(source);
            };
            
            Queue<CImage> sequence = loadingDialog.executeTask(task);
            
            CImage current = sequence.poll();
            while (current != null){
                
                int firstAvailableSlot = getFirstAvailableSlot();
                setImageToSlot(firstAvailableSlot, current);
                current = sequence.poll();
            }
            
        }catch(Exception ex){
            logger.log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jMenuItemQuestion1AActionPerformed

    private void jMenuItemUtilsRGBToNGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUtilsRGBToNGActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageRGB)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image RGB valide.");
                return;
            }

            CImageRGB selectedRGB = (CImageRGB) selected;
            
            JDialogUtils dialog = new JDialogUtils(this, true);
            dialog.setVisible(true);
            
            if(!dialog.isFilled())
                return;
            
            String couleur = dialog.getCouleur();
            
            // Apply filter
            int[][] data = Utils.extraireCanal(selectedRGB, couleur);
            data = Utils.normaliserImage(data, 0, 255);
            
            // Display result
            CImageNG updatedImage = new CImageNG(data);
            showResultImage(updatedImage);

        } catch (CImageNGException | CImageRGBException | HeadlessException e) {
            logger.log(Level.SEVERE, "Erreur Conversion RGB to NG : {0}", e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemUtilsRGBToNGActionPerformed

    private void jMenuItemUtilsNGToRGBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUtilsNGToRGBActionPerformed
        try {
            // Get the selected image from the grid
            CImage selected = getSelectedImage();
            if (selected == null || !(selected instanceof CImageNG)) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une image NG valide.");
                return;
            }

            CImageNG image = (CImageNG) selected;
            
            JDialogUtils dialog = new JDialogUtils(this, true);
            dialog.setVisible(true);
            
            if(!dialog.isFilled())
                return;
            
            String couleur = dialog.getCouleur();
            
            // Apply filter
            CImageRGB data = Utils.convertionNGToRGB(image.getMatrice(), couleur);            
            
            // Display result
            showResultImage(data);

        } catch (CImageNGException | CImageRGBException | HeadlessException e) {
            logger.log(Level.SEVERE, "Erreur Conversion RGB to NG : {0}", e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemUtilsNGToRGBActionPerformed

    private void jMenuItemUtilsAdditionRGBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUtilsAdditionRGBActionPerformed
        try {
            CImage[] selectedImages = getAllSelectedImages();

            if (selectedImages == null || selectedImages.length != 2) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner exactement deux images RGB.");
                return;
            }
            
            for (CImage selectedImage : selectedImages) {
                if (!(selectedImage instanceof CImageRGB)) {
                    JOptionPane.showMessageDialog(this, "Les deux images doivent être de type RGB.");
                    return;
                }
            }

            // Extract images
            CImageRGB image1 = (CImageRGB) selectedImages[0];
            CImageRGB image2 = (CImageRGB) selectedImages[1];

            CImageRGB data = Utils.additionRGB(image1, image2);

            // Display result
            showResultImage(data);

        } catch (CImageRGBException | HeadlessException e) {
            System.out.println("Erreur Addition de 2 images: " + e.getMessage());
            logger.log(Level.SEVERE, "Erreur Addition de 2 images RGB: {0}", e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemUtilsAdditionRGBActionPerformed

    private void jMenuItemQuestion3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemQuestion3ActionPerformed
        try{
            
            LoadingDialog loadingDialog = new LoadingDialog(this, "Question 3");
            
            Callable<Queue<CImage>> task = () -> {
                clearAllImages();
                CImage source = new CImageRGB(new File("res/Images/ImagesEtape5/petitsPois.png"));
                return Application.question3(source);
            };
            
            Queue<CImage> sequence = loadingDialog.executeTask(task);
            
            CImage current = sequence.poll();
            while (current != null){
                
                int firstAvailableSlot = getFirstAvailableSlot();
                setImageToSlot(firstAvailableSlot, current);
                current = sequence.poll();
            }
            
        }catch(Exception ex){
            logger.log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemQuestion3ActionPerformed

    private void jMenuItemQuestion4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemQuestion4ActionPerformed
        try {
            LoadingDialog loadingDialog = new LoadingDialog(this, "Question 4");

            Callable<Queue<CImage>> task = () -> {
                clearAllImages();
                CImage source = new CImageNG(new File("res/Images/ImagesEtape5/balanes.png"));
                return Application.question4(source);
            };

            Queue<CImage> sequence = loadingDialog.executeTask(task);
            CImage current = sequence.poll();
            while (current != null) {
                int firstAvailableSlot = getFirstAvailableSlot();
                setImageToSlot(firstAvailableSlot, current);
                current = sequence.poll();
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemQuestion4ActionPerformed

    private void jMenuItemQuestion5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemQuestion5ActionPerformed
        try {
            LoadingDialog loadingDialog = new LoadingDialog(this, "Question 5");

            Callable<Queue<CImage>> task = () -> {
                clearAllImages();
                CImage source = new CImageNG(new File("res/Images/ImagesEtape5/tools.png"));
                return Application.question5(source);
            };

            Queue<CImage> sequence = loadingDialog.executeTask(task);
            CImage current = sequence.poll();
            while (current != null) {
                int firstAvailableSlot = getFirstAvailableSlot();
                setImageToSlot(firstAvailableSlot, current);
                current = sequence.poll();
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemQuestion5ActionPerformed

    private void jMenuItemQuestion1BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemQuestion1BActionPerformed
        try {
            LoadingDialog loadingDialog = new LoadingDialog(this, "Question 1B");

            Callable<Queue<CImage>> task = () -> {
                clearAllImages();
                CImage source = new CImageNG(new File("res/Images/ImagesEtape5/imageBruitee2.png"));
                return Application.question1B(source);
            };

            Queue<CImage> sequence = loadingDialog.executeTask(task);
            CImage current = sequence.poll();
            while (current != null) {
                int firstAvailableSlot = getFirstAvailableSlot();
                setImageToSlot(firstAvailableSlot, current);
                current = sequence.poll();
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemQuestion1BActionPerformed

    private void jMenuItemQuestion7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemQuestion7ActionPerformed
        try {
            LoadingDialog loadingDialog = new LoadingDialog(this, "Question 7");

            Callable<Queue<CImage>> task = () -> {
                clearAllImages();
                CImage source = new CImageNG(new File("res/Images/ImagesEtape5/Tartines.jpg"));
                CImage second = new CImageRGB(new File("res/Images/ImagesEtape5/Tartines.jpg"));
                return Application.question7(source, second);
            };

            Queue<CImage> sequence = loadingDialog.executeTask(task);
            CImage current = sequence.poll();
            while (current != null) {
                int firstAvailableSlot = getFirstAvailableSlot();
                setImageToSlot(firstAvailableSlot, current);
                current = sequence.poll();
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemQuestion7ActionPerformed


    private void jMenuItemQuestion6AActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        try {
            LoadingDialog loadingDialog = new LoadingDialog(this, "Question 6A");

            Callable<Queue<CImage>> task = () -> {
                clearAllImages();
                CImage source = new CImageNG(new File("res/Images/ImagesEtape5/vaisseaux.jpg"));
                CImage sourceColor = new CImageRGB(new File("res/Images/ImagesEtape5/vaisseaux.jpg"));
                CImage second = new CImageRGB(new File("res/Images/ImagesEtape5/planete.jpg"));
                return Application.question6A(source, sourceColor, second);
            };

            Queue<CImage> sequence = loadingDialog.executeTask(task);
            
            this.activeMenus();
            CImage current = sequence.poll();
            while (current != null){
                int firstAvailableSlot =  getFirstAvailableSlot();
                setImageToSlot(firstAvailableSlot, current);
                current = sequence.poll();
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }    
                                                 

    private void jMenuItemQuestion2AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemQuestion2AActionPerformed
        try {
            LoadingDialog loadingDialog = new LoadingDialog(this, "Question 2A");

            Callable<Queue<CImage>> task = () -> {
                clearAllImages();
                CImage source = new CImageRGB(new File("res/Images/ImagesEtape5/lenaAEgaliser.jpg"));
                return Application.question2A(source);
            };

            Queue<CImage> sequence = loadingDialog.executeTask(task);
            
            this.activeMenus();
            
            CImage current = sequence.poll();
            while (current != null) {
                int firstAvailableSlot = getFirstAvailableSlot();
                setImageToSlot(firstAvailableSlot, current);
                current = sequence.poll();
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemQuestion2AActionPerformed

    private void jMenuItemAndNGEtRGBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAndNGEtRGBActionPerformed
        try {
            CImage[] selectedImages = getAllSelectedImages();

            if (selectedImages == null || selectedImages.length != 2) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner exactement deux images.");
                return;
            }
            
            // Extract images
            CImageRGB image1 = (CImageRGB) selectedImages[0];
            CImageNG image2 = (CImageNG) selectedImages[1];

            CImageRGB data = Utils.andRGBWithMask(image1, image2);

            // Display result
            showResultImage(data);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erreur Addition de 2 images: {0}", e.getMessage());
        }
    }//GEN-LAST:event_jMenuItemAndNGEtRGBActionPerformed

    private void jMenuItemQuestion6BActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        try {
            LoadingDialog loadingDialog = new LoadingDialog(this, "Question 6B");

            Callable<Queue<CImage>> task = () -> {
                clearAllImages();
                CImage source = new CImageNG(new File("res/Images/ImagesEtape5/vaisseaux.jpg"));
                CImage sourceColor = new CImageRGB(new File("res/Images/ImagesEtape5/vaisseaux.jpg"));
                CImage second = new CImageRGB(new File("res/Images/ImagesEtape5/planete.jpg"));
                return Application.question6B(source, sourceColor, second);
            };
            
            Queue<CImage> sequence = loadingDialog.executeTask(task);
            
            this.activeMenus();
            CImage current = sequence.poll();
            while (current != null){
                int firstAvailableSlot =  getFirstAvailableSlot();
                setImageToSlot(firstAvailableSlot, current);
                current = sequence.poll();
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }    

    private void jMenuItemQuestion2BActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        try {
            LoadingDialog loadingDialog = new LoadingDialog(this, "Question 2B");

            Callable<Queue<CImage>> task = () -> {
                clearAllImages();
                CImageNG source = new CImageNG(new File("res/Images/ImagesEtape5/lenaAEgaliser.jpg"));
                CImage sourceColor = new CImageRGB(new File("res/Images/ImagesEtape5/lenaAEgaliser.jpg"));
                return Application.question2B(sourceColor, source);
            };

            Queue<CImage> sequence = loadingDialog.executeTask(task);
            CImage current = sequence.poll();
            while (current != null) {
                int firstAvailableSlot = getFirstAvailableSlot();
                setImageToSlot(firstAvailableSlot, current);
                current = sequence.poll();
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
                                                
    }                                                

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new IsilImageProcessing().setVisible(true);
        });
    }

    @Override
    public void ClicDetected(UnClicEvent e) 
    {

    }

    @Override
    public void SelectLigneDetected(DeuxClicsEvent e) 
    {

    }

    @Override
    public void SelectRectDetected(DeuxClicsEvent e) 
    {

    }

    @Override
    public void SelectCercleDetected(DeuxClicsEvent e) 
    {

    }

    @Override
    public void SelectCercleFillDetected(DeuxClicsEvent e) 
    {

    }

    @Override
    public void SelectRectFillDetected(DeuxClicsEvent e) 
    {

    }
    
    // ANTOINE WAS HERE
    private void setupImageGridSlots() {
        panelGrid.removeAll();

        panelGrid.setLayout(new GridLayout(3, 3, 5, 5)); // 3x3 grid with spacing

        for (int i = 0; i < 9; i++) {
            final int index = i;

            JLabel slot = new JLabel("Slot " + (i + 1), SwingConstants.CENTER);
            slot.setPreferredSize(new Dimension(150, 150));
            slot.setOpaque(true);
            slot.setBackground(Color.WHITE);
            slot.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            // Click to select/deselect
            slot.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (currentGridMode == GridMode.MOVE) {
                        if (liftedImage == null && imagesInSlots[index] != null) {
                            // LIFT
                            clearAllSelections();

                            liftedImage = imagesInSlots[index];
                            liftedFromIndex = index;
                            imageSlots[index].setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));


                        } else if (liftedImage != null) {
                            // DROP
                            clearAllSelections();
                            if (liftedFromIndex >= 0) {
                                // Swap images
                                CImage temp = imagesInSlots[index];
                                setImageToSlot(index, liftedImage);
                                setImageToSlot(liftedFromIndex, temp);

                            } else if (liftedFromIndex == -1) {
                                // Move from result to grid
                                setImageToSlot(index, liftedImage);
                                resultPanel.setIcon(null);
                                resultImage = null;
                                resultPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                            }


                            liftedImage = null;
                            liftedFromIndex = -2;

                            clearAllSelections(); // also clear after drop
                        }

                    } else if (currentGridMode == GridMode.SELECT) {
                        // Toggle selection
                        selectedSlots[index] = !selectedSlots[index];
                        if (selectedSlots[index]) {
                            slot.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 4));
                        } else {
                            slot.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                        }
                    }
                }
            });


            imageSlots[i] = slot;
            panelGrid.add(slot);
        }

        panelGrid.revalidate();
        panelGrid.repaint();
    }

    private void setImageToSlot(int index, CImage image) {
        if (index < 0 || index >= 9 || image == null) return;

        imagesInSlots[index] = image;

        Image raw = image.getImage();
        Image scaled = raw.getScaledInstance(imageSlots[index].getWidth(), imageSlots[index].getHeight(), Image.SCALE_SMOOTH);
        imageSlots[index].setIcon(new ImageIcon(scaled));
        imageSlots[index].setText(""); // Clear label text

        selectedSlots[index] = false;
        imageSlots[index].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }


    // SELECT PARTS
    private int getSelectedSlotOrFirstEmpty() {
        for (int i = 0; i < selectedSlots.length; i++) {
            if (selectedSlots[i]) {
                return i; // Use first selected slot
            }
        }

        // If no selection, find first empty
        for (int i = 0; i < imagesInSlots.length; i++) {
            if (imagesInSlots[i] == null) {
                return i;
            }
        }

        return -1; // No slot available
    }
    
    private void clearAllSelections() {
        for (int i = 0; i < selectedSlots.length; i++) {
            selectedSlots[i] = false;
            imageSlots[i].setBackground(Color.WHITE);
            imageSlots[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        }

        resultPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }


    private int getSelectedSlot() {
        for (int i = 0; i < selectedSlots.length; i++) {
            if (selectedSlots[i]) return i;
        }
        return -1;
    }
    
    private CImage[] getAllSelectedImages() {
        int count = 0;
        for (int i = 0; i < selectedSlots.length; i++) {
            if (selectedSlots[i] && imagesInSlots[i] != null) {
                count++;
            }
        }

        if (count == 0) {
            return null; // No valid image selected
        }

        CImage[] selectedImages = new CImage[count];
        int index = 0;

        for (int i = 0; i < selectedSlots.length; i++) {
            if (selectedSlots[i] && imagesInSlots[i] != null) {
                selectedImages[index++] = imagesInSlots[i];
            }
        }

        return selectedImages;
    }
    


    private int getFirstAvailableSlot() {
        for (int i = 0; i < imagesInSlots.length; i++) {
            if (imagesInSlots[i] == null) return i;
        }
        return -1;
    }

    private CImage getSelectedImage() {
        for (int i = 0; i < selectedSlots.length; i++) {
            if (selectedSlots[i] && imagesInSlots[i] != null) {
                return imagesInSlots[i];
            }
        }
        return null;
    }

    // DELETE PARTS
    private void clearAllImages() {
        desactiveMenus();
        for (int i = 0; i < imagesInSlots.length; i++) {
            imagesInSlots[i] = null;
            selectedSlots[i] = false;
            imageSlots[i].setIcon(null);
            imageSlots[i].setText("Slot " + (i + 1)); // Restore default label
            imageSlots[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        }

        resultImage = null;
        resultPanel.setIcon(null);
        resultPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelGrid.repaint();
    }


    private void deleteSelectedImages() {
        for (int i = 0; i < selectedSlots.length; i++) {
            if (selectedSlots[i]) {
                imagesInSlots[i] = null;
                selectedSlots[i] = false;
                imageSlots[i].setIcon(null);
                imageSlots[i].setText("Slot " + (i + 1)); // Restore label text
                imageSlots[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            }
        }

        panelGrid.repaint();
    }


    // Button UI
    // Add a button and a keybind (m) to swap between move and select mode
    // Add a button and to delete all the images in the grid
    private void setupModeToggleUI() {
        toolBar = new JToolBar();
        JToggleButton toggleButton = new JToggleButton("Mode Déplacement");
        toggleButton.setFocusable(false);

        currentGridMode = GridMode.SELECT;

        toggleButton.addActionListener(e -> {
            currentGridMode = toggleButton.isSelected() ? GridMode.MOVE : GridMode.SELECT;
            clearAllSelections();
        });

        toolBar.add(toggleButton);
        toolBar.add(Box.createHorizontalStrut(10));
        
        JButton clearGridButton = new JButton("Effacer toutes les images");
        clearGridButton.setFocusable(false);
        clearGridButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Voulez-vous vraiment effacer toutes les images de la grille ?",
                "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                clearAllImages();
            }
        });
        toolBar.add(clearGridButton);

        
        getContentPane().add(toolBar, BorderLayout.NORTH);

        KeyStroke keyStroke = KeyStroke.getKeyStroke("M");
        panelGrid.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "toggleMode");
        panelGrid.getActionMap().put("toggleMode", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAllSelections();
                toggleButton.setSelected(!toggleButton.isSelected());
                currentGridMode = toggleButton.isSelected() ? GridMode.MOVE : GridMode.SELECT;
            }
        });
        
    }


    // MY Variable declaration - Antoine
    private final boolean[] selectedSlots = new boolean[9];
    private final CImage[] imagesInSlots = new CImage[9];
    private final JLabel[] imageSlots = new JLabel[9];
    private JToolBar toolBar;

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupDessiner;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDessinerCercle;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDessinerCerclePlein;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDessinerLigne;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDessinerPixel;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDessinerRectangle;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemDessinerRectanglePlein;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2Question6;
    private javax.swing.JMenuItem jMenuAfficherParametresImage;
    private javax.swing.JMenu jMenuApplication;
    private javax.swing.JMenu jMenuApplication1;
    private javax.swing.JMenu jMenuApplication2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuContours;
    private javax.swing.JMenu jMenuContoursLineaire;
    private javax.swing.JMenu jMenuContoursNonLineaire;
    private javax.swing.JMenu jMenuDessiner;
    private javax.swing.JMenu jMenuFourier;
    private javax.swing.JMenu jMenuFourierAfficher;
    private javax.swing.JMenu jMenuHistogramme;
    private javax.swing.JMenuItem jMenuHistogrammeAfficher;
    private javax.swing.JMenu jMenuImage;
    private javax.swing.JMenuItem jMenuItemAndNGEtRGB;
    private javax.swing.JMenuItem jMenuItemContoursLineaireLaplacien4;
    private javax.swing.JMenuItem jMenuItemContoursLineaireLaplacien8;
    private javax.swing.JMenuItem jMenuItemContoursLineairePrewitt;
    private javax.swing.JMenuItem jMenuItemContoursLineaireSobel;
    private javax.swing.JMenuItem jMenuItemContoursNonLineaireBeucher;
    private javax.swing.JMenuItem jMenuItemContoursNonLineaireDilatation;
    private javax.swing.JMenuItem jMenuItemContoursNonLineaireErosion;
    private javax.swing.JMenuItem jMenuItemContoursNonLineaireLaplacien;
    private javax.swing.JMenuItem jMenuItemCouleurPinceau;
    private javax.swing.JMenuItem jMenuItemCourbeTonaleEgalisation;
    private javax.swing.JMenuItem jMenuItemCourbeTonaleGamma;
    private javax.swing.JMenuItem jMenuItemCourbeTonaleLineaireAvecSaturation;
    private javax.swing.JMenuItem jMenuItemCourbeTonaleLineaireSansSaturation;
    private javax.swing.JMenuItem jMenuItemCourbeTonaleNegatif;
    private javax.swing.JMenuItem jMenuItemEnregistrerSous;
    private javax.swing.JMenuItem jMenuItemFiltrageLineaireGlobalPasseBas;
    private javax.swing.JMenuItem jMenuItemFiltrageLineaireGlobalPasseBasButterworth;
    private javax.swing.JMenuItem jMenuItemFiltrageLineaireGlobalPasseHaut;
    private javax.swing.JMenuItem jMenuItemFiltrageLineaireGlobalPasseHautButterworth;
    private javax.swing.JMenuItem jMenuItemFiltrageLineaireLocalFiltrageConvolution;
    private javax.swing.JMenuItem jMenuItemFiltrageLineaireLocalFiltrageMoyenneur;
    private javax.swing.JMenuItem jMenuItemFourierAfficherModule;
    private javax.swing.JMenuItem jMenuItemFourierAfficherPartieImaginaire;
    private javax.swing.JMenuItem jMenuItemFourierAfficherPartieReelle;
    private javax.swing.JMenuItem jMenuItemFourierAfficherPhase;
    private javax.swing.JMenuItem jMenuItemNouvelleNG;
    private javax.swing.JMenuItem jMenuItemNouvelleRGB;
    private javax.swing.JMenuItem jMenuItemOuvrirNG;
    private javax.swing.JMenuItem jMenuItemOuvrirRGB;
    private javax.swing.JMenuItem jMenuItemQuestion1A;
    private javax.swing.JMenuItem jMenuItemQuestion1B;
    private javax.swing.JMenuItem jMenuItemQuestion2A;
    private javax.swing.JMenuItem jMenuItemQuestion2B;
    private javax.swing.JMenuItem jMenuItemQuestion3;
    private javax.swing.JMenuItem jMenuItemQuestion4;
    private javax.swing.JMenuItem jMenuItemQuestion5;
    private javax.swing.JMenuItem jMenuItemQuestion6A;
    private javax.swing.JMenuItem jMenuItemQuestion6B;
    private javax.swing.JMenuItem jMenuItemQuestion7;
    private javax.swing.JMenuItem jMenuItemSeuillageAutomatique;
    private javax.swing.JMenuItem jMenuItemSeuillageDouble;
    private javax.swing.JMenuItem jMenuItemSeuillageSimple;
    private javax.swing.JMenuItem jMenuItemTraitementLineaireMorphologieComplexeDilatationGeodesique;
    private javax.swing.JMenuItem jMenuItemTraitementLineaireMorphologieComplexeFiltreMedian;
    private javax.swing.JMenuItem jMenuItemTraitementLineaireMorphologieComplexeReconstructionGeodesique;
    private javax.swing.JMenuItem jMenuItemTraitementLineaireMorphologieElementaireDilatation;
    private javax.swing.JMenuItem jMenuItemTraitementLineaireMorphologieElementaireErosion;
    private javax.swing.JMenuItem jMenuItemTraitementLineaireMorphologieElementaireFermeture;
    private javax.swing.JMenuItem jMenuItemTraitementLineaireMorphologieElementaireOuverture;
    private javax.swing.JMenuItem jMenuItemUtilsAddition;
    private javax.swing.JMenuItem jMenuItemUtilsAdditionRGB;
    private javax.swing.JMenuItem jMenuItemUtilsNGToRGB;
    private javax.swing.JMenuItem jMenuItemUtilsRGBToNG;
    private javax.swing.JMenuItem jMenuItemUtilsSoustraction;
    private javax.swing.JMenu jMenuLineaire;
    private javax.swing.JMenu jMenuLineaireGlobal;
    private javax.swing.JMenu jMenuNouvelle;
    private javax.swing.JMenu jMenuOuvrir;
    private javax.swing.JMenuItem jMenuQuitter;
    private javax.swing.JMenu jMenuSeuillage;
    private javax.swing.JMenu jMenuTraitement;
    private javax.swing.JMenu jMenuTraitementComplexe;
    private javax.swing.JMenu jMenuTraitementElementaire;
    private javax.swing.JMenu jMenuUtils;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel panelGrid;
    private javax.swing.JPanel panelResult;
    private javax.swing.JLabel resultPanel;
    // End of variables declaration//GEN-END:variables
    
}
