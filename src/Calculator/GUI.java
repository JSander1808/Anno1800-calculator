package Calculator;

import FileManager.Config;
import FileManager.NormalConfig;
import Main.Main;
import org.w3c.dom.css.RGBColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class GUI {
    public static NormalConfig normalconfig = new NormalConfig("data/config.txt");
    public static JFrame frame;
    public static boolean dark = false;
    public static int[] importItems = new int[10];
    public static JCheckBox darkModeEnable = new JCheckBox();
    public static ArrayList<JComboBox> productComboBoxList = new ArrayList<JComboBox>();
    public static ArrayList<JTextArea> productTradeAreaList = new ArrayList<JTextArea>();
    public static ArrayList<JTextArea> productLevelList = new ArrayList<JTextArea>();
    public static ArrayList<JTextField> productPerMinuteList = new ArrayList<JTextField>();
    public static ArrayList<JLabel> productPerMinuteNotANumberList = new ArrayList<JLabel>();
    public static ArrayList<JTextField> productBuyList = new ArrayList<JTextField>();
    public static ArrayList<JButton> productSwitchMode = new ArrayList<JButton>();
    public static ArrayList<JLabel> productImage = new ArrayList<JLabel>();
    public static ArrayList<JPanel> importProductList = new ArrayList<JPanel>();
    public static int[] productSwitchModeIndex = new int[10];
    public static JComboBox productResultItemComboBox;
    public static JTextArea productResultItemTradeArea;
    public static JComboBox productResultItemLevel;
    public static JLabel productResultItemLevelNotANumber;
    public static JLabel productResultHeadline;
    public static JTextArea productResultProductList;
    public static JTextArea productResultProductsPerMinuteList;
    public static JTextArea productResultProductBuyList;
    public static JLabel productResultProductResultHeadline;
    public static JLabel productResultProductResult;
    public static JLabel productResultProductPerMinuteResult;
    public static JLabel productResultProductBuyResult;
    public static JLabel productResultProductHeadlineProducts;
    public static JLabel productResultProductHeadlinePerMinute;
    public static JLabel productResultProductHeadlineBuy;
    public static JLabel headline;
    public static ImageIcon settingsIcon;
    public static JButton settingsMenuButton;
    public static BufferedImage logoImage;
    public static ImageIcon logoIconResult;
    public static JLabel logoLabel;
    public static BufferedImage logoBufferedImage;
    public static JLabel headlineImportProduct;
    public static JButton addImportItem;
    public static JButton removeImportItem;
    public static JPanel importProductPanel;
    public static JLabel working;
    public static JLabel exportHeadline;
    public static JPanel resultItem;
    public static JPanel result;
    public static JPanel panel1;
    public static JLabel product;
    public static JLabel productTrade;
    public static JLabel productLevel;
    public static JLabel productPerMinute;
    public static JLabel productBuy;
    public static JButton clear;
    public static JButton settingsSubmitButton;
    public static JButton saveButton;
    public static JButton loadButton;
    public static JPanel saveMenu;
    public static JComboBox saves;
    public static JButton sessionLink;
    public static JComboBox importFileName;
    public static JPanel loadMenu;

    public GUI(){
        normalconfig.init();
        if(!(normalconfig.get("darkmode")==null)){
            if(normalconfig.get("darkmode").equalsIgnoreCase("true")){
                dark=true;
            }else{
                dark=false;
            }
        }else{
            normalconfig.set("darkmode","false");
            dark=false;
        }
        frame = new JFrame("Anno1800 - Calculator");
        frame.setSize(1620,820);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        if(dark){
            frame.getContentPane().setBackground(new Color(41, 41, 41));
            frame.getContentPane().setForeground(Color.white);
        }else{
            frame.getContentPane().setBackground(Color.white);
            frame.getContentPane().setForeground(Color.black);
        }

        headline = new JLabel("Anno1800 - Calculator");
        headline.setBounds(10,0,510,60);
        headline.setVisible(true);
        headline.setFont(new Font("TimesRoman",Font.PLAIN,40));
        if(dark){
            headline.setBackground(new Color(41, 41, 41));
            headline.setForeground(Color.white);
        }else{
            headline.setBackground(Color.white);
            headline.setForeground(Color.black);
        }

        JPanel settingsMenu = addSettingsPanel();
        settingsMenu.setBounds(1200,60,350,220);
        settingsMenu.setVisible(false);

        saveMenu = addSaveMenu();
        saveMenu.setBounds(1200,60,350,220);
        saveMenu.setVisible(false);

        loadMenu = addLoadMenu();
        loadMenu.setBounds(1200,60,350,220);
        loadMenu.setVisible(false);

        try {
             if(dark){
                 settingsIcon = new ImageIcon(resizeImage(ImageIO.read(new File("pictures/settingsLogodark.png")),45,45));
             }else{
                 settingsIcon = new ImageIcon(resizeImage(ImageIO.read(new File("pictures/settingsLogolight.png")),45,45));
             }
        } catch (IOException e) {
            getError(e);
        }
        settingsMenuButton = new JButton("",settingsIcon);
        settingsMenuButton.setBounds(1500,3,50,50);

        saveButton = new JButton();
        saveButton.setBounds(1440,3,50,50);

        try {
            if(dark){
                saveButton.setIcon(new ImageIcon(resizeImage(ImageIO.read(new File("pictures/savedark.png")),45,45)));
            }else{
                saveButton.setIcon(new ImageIcon(resizeImage(ImageIO.read(new File("pictures/savelight.png")),45,45)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        loadButton = new JButton();
        loadButton.setBounds(1380,3,50,50);
        try {
            if(dark){
                loadButton.setIcon(new ImageIcon(resizeImage(ImageIO.read(new File("pictures/loaddark.png")),45,45)));
            }else{
                loadButton.setIcon(new ImageIcon(resizeImage(ImageIO.read(new File("pictures/loadlight.png")),45,45)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loadMenu.isVisible()){
                    loadMenu.setVisible(false);
                    loadButton.setBounds(1380,3,50,50);
                }else{
                    loadButton.setBounds(1380,10,50,50);
                    loadMenu.setVisible(true);
                    settingsMenuButton.setBounds(1500,3,50,50);
                    saveButton.setBounds(1440,3,50,50);
                    settingsMenu.setVisible(false);
                    saveMenu.setVisible(false);
                    importFileName.removeAllItems();
                    NormalConfig nc = new NormalConfig("data/saves/config.txt");
                    nc.init();
                    File[] saveFiles = new File("data/saves/").listFiles();
                    for(int i = 0;i<saveFiles.length;i++){
                        if(!saveFiles[i].getName().equalsIgnoreCase("config.txt")){
                            importFileName.addItem(saveFiles[i].getName());
                        }
                    }
                }
            }
        });

        settingsMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(settingsMenu.isVisible()){
                    settingsMenu.setVisible(false);
                    settingsMenuButton.setBounds(1500,3,50,50);
                }else{
                    settingsMenu.setVisible(true);
                    settingsMenuButton.setBounds(1500,10,50,50);
                    saveButton.setBounds(1440,3,50,50);
                    loadButton.setBounds(1380,3,50,50);
                    saveMenu.setVisible(false);
                    loadMenu.setVisible(false);
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(saveMenu.isVisible()){
                    saveMenu.setVisible(false);
                    saveButton.setBounds(1440,3,50,50);
                }else{
                    saves.removeAllItems();
                    NormalConfig nc = new NormalConfig("data/saves/config.txt");
                    nc.init();
                    File[] saveFiles = new File("data/saves/").listFiles();
                    for(int i = 0;i<saveFiles.length;i++){
                        if(!saveFiles[i].getName().equalsIgnoreCase("config.txt")){
                            saves.addItem(saveFiles[i].getName());
                        }
                    }
                    saveMenu.setVisible(true);
                    saveButton.setBounds(1440,10,50,50);
                    settingsMenuButton.setBounds(1500,3,50,50);
                    loadButton.setBounds(1380,3,50,50);
                    settingsMenu.setVisible(false);
                    loadMenu.setVisible(false);
                }
            }
        });

        logoImage = null;
        try {
            if(dark){
                logoImage = ImageIO.read(new File("pictures/logodark.png"));
            }else{
                logoImage = ImageIO.read(new File("pictures/logolight.png"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logoBufferedImage = null;
        try {
            logoBufferedImage = resizeImage(logoImage,150,150);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logoIconResult = new ImageIcon(logoBufferedImage);
        logoLabel = new JLabel();
        logoLabel.setIcon(logoIconResult);
        logoLabel.setBounds(1400,625,150,150);

        JLabel version = new JLabel(Main.version);
        version.setBounds(1330,0,50,20);
        version.setForeground(Color.gray);

        headlineImportProduct = new JLabel();
        headlineImportProduct.setText("Import Produkte");
        headlineImportProduct.setBounds(20,60,400,40);
        headlineImportProduct.setFont(new Font("TimesRoman",Font.PLAIN,30));
        if(dark){
            headlineImportProduct.setBackground(new Color(41,41,41));
            headlineImportProduct.setForeground(Color.white);
        }else{
            headlineImportProduct.setBackground(Color.white);
            headlineImportProduct.setForeground(Color.black);
        }

        addImportItem = new JButton("Plus");
        addImportItem.setBounds(760,80,70,20);
        if(dark){
            addImportItem.setBackground(new Color(41,41,41));
            addImportItem.setForeground(Color.white);
        }else{
            addImportItem.setBackground(Color.white);
            addImportItem.setForeground(Color.black);
        }
        addImportItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0;i<10;i++){
                    if(importItems[i]==0){
                        importItems[i]=1;
                        break;
                    }
                }
            }
        });

        removeImportItem = new JButton("Minus");
        removeImportItem.setBounds(690,80,70,20);
        if(dark){
            removeImportItem.setBackground(new Color(41,41,41));
            removeImportItem.setForeground(Color.white);
        }else{
            removeImportItem.setBackground(Color.white);
            removeImportItem.setForeground(Color.black);
        }
        removeImportItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 9;i>=0;i--){
                    if(importItems[i]==1){
                        importItems[i]=0;
                        productComboBoxList.get(i).setSelectedItem("Nichts");
                        productPerMinuteList.get(i).setText("");
                        productBuyList.get(i).setText("");
                        productPerMinuteList.get(i).enable();
                        productBuyList.get(i).disable();
                        productSwitchModeIndex[i]=0;
                        frame.repaint();
                        break;
                    }
                }
            }
        });

        importProductPanel = new JPanel();
        importProductPanel.setBounds(10,100,820,650);
        importProductPanel.setLayout(null);
        if(dark){
            importProductPanel.setBackground(new Color(41,41,41));
            importProductPanel.setForeground(Color.white);
            importProductPanel.setBorder(new LineBorder(Color.white));
        }else{
            importProductPanel.setBackground(Color.white);
            importProductPanel.setForeground(Color.black);
            importProductPanel.setBorder(new LineBorder(Color.black));
        }

        importItems[0]=1;
        for(int i = 0;i<10;i++){
            importProductList.add(addImportProduct(i));
            importProductList.get(i).setBounds(0,65*i,820,65);
            importProductPanel.add(importProductList.get(i));
            if(importItems[i]==1){
                importProductList.get(i).setVisible(true);
            }else{
                importProductList.get(i).setVisible(false);
            }
        }

        working = new JLabel("❰");
        working.setBounds(850,346,150,150);
        working.setFont(new Font("TimesRoman",Font.PLAIN,150));
        if(dark){
            working.setBackground(new Color(41,41,41));
            working.setForeground(Color.white);
        }else{
            working.setBackground(Color.white);
            working.setForeground(Color.black);
        }

        exportHeadline = new JLabel("Export Produkte");
        exportHeadline.setBounds(950,240,400,50);
        exportHeadline.setFont(new Font("TimesRoman",Font.PLAIN,30));
        if(dark){
            exportHeadline.setBackground(new Color(41,41,41));
            exportHeadline.setForeground(Color.white);
        }else{
            exportHeadline.setBackground(Color.white);
            exportHeadline.setForeground(Color.black);
        }

        resultItem = addResultItem(10);
        resultItem.setBounds(950,290,600,65);
        if(dark){
            resultItem.setBackground(new Color(41,41,41));
            resultItem.setForeground(Color.white);
        }else{
            resultItem.setBackground(Color.white);
            resultItem.setForeground(Color.black);
        }

        result = addResult();
        result.setBounds(950,355,600,250);
        if(dark){
            result.setBackground(new Color(41,41,41));
            result.setForeground(Color.white);
            result.setBorder(new LineBorder(Color.white));
        }else{
            result.setBackground(Color.white);
            result.setForeground(Color.black);
            result.setBorder(new LineBorder(Color.black));
        }

        frame.add(settingsMenu);
        frame.add(saveMenu);
        frame.add(loadMenu);
        frame.add(headline);
        frame.add(logoLabel);
        frame.add(version);
        frame.add(loadButton);
        frame.add(saveButton);
        frame.add(settingsMenuButton);
        frame.add(headlineImportProduct);
        frame.add(addImportItem);
        frame.add(removeImportItem);
        frame.add(importProductPanel);
        frame.add(working);
        frame.add(resultItem);
        frame.add(result);
        frame.add(exportHeadline);
        frame.setVisible(true);
        new GUIUpdater();
    }

    public static JPanel addImportProduct(int i){
        panel1 = new JPanel();
        panel1.setVisible(true);
        panel1.setLayout(null);
        panel1.setBounds(0,0,255,70);
        if(dark){
            panel1.setBackground(new Color(41,41,41));
            panel1.setForeground(Color.white);
            panel1.setBorder(new LineBorder(Color.white));
        }else{
            panel1.setBackground(Color.white);
            panel1.setForeground(Color.black);
            panel1.setBorder(new LineBorder(Color.black));
        }

        product = new JLabel("Produkt:");
        product.setBounds(10,0,100,20);
        if(dark){
            product.setBackground(new Color(41,41,41));
            product.setForeground(Color.white);
        }else{
            product.setBackground(Color.white);
            product.setForeground(Color.black);
        }

        productComboBoxList.add(new JComboBox());
        productComboBoxList.get(i).setBounds(10,25,130,20);
        productComboBoxList.get(i).addItem("Nichts");
        File file = new File("Product.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while((temp = reader.readLine())!=null){
                String[] data = temp.split("--");
                if(Double.valueOf(data[2])>0){
                    productComboBoxList.get(i).addItem(data[0]);
                }
            }
        } catch (FileNotFoundException e) {
            getError(e);
        } catch (IOException e) {
            getError(e);
        }
        if(dark){
            productComboBoxList.get(i).setBackground(new Color(41,41,41));
            productComboBoxList.get(i).setForeground(Color.white);
        }else{
            productComboBoxList.get(i).setBackground(Color.white);
            productComboBoxList.get(i).setForeground(Color.black);
        }

        productTrade = new JLabel("Produkt Wert:");
        productTrade.setBounds(150,0,100,20);
        if(dark){
            productTrade.setBackground(new Color(41,41,41));
            productTrade.setForeground(Color.white);
        }else{
            productTrade.setBackground(Color.white);
            productTrade.setForeground(Color.black);
        }

        productTradeAreaList.add(new JTextArea());
        productTradeAreaList.get(i).setFocusable(false);
        productTradeAreaList.get(i).setBounds(150,25,100,20);
        productTradeAreaList.get(i).setText("0");
        if(dark){
            productTradeAreaList.get(i).setBackground(new Color(41,41,41));
            productTradeAreaList.get(i).setForeground(Color.white);
        }else{
            productTradeAreaList.get(i).setBackground(Color.white);
            productTradeAreaList.get(i).setForeground(Color.black);
        }

        productLevel = new JLabel("Produkt Level");
        productLevel.setBounds(260,0,100,20);
        if(dark){
            productLevel.setBackground(new Color(41,41,41));
            productLevel.setForeground(Color.white);
        }else{
            productLevel.setBackground(Color.white);
            productLevel.setForeground(Color.black);
        }

        productLevelList.add(new JTextArea());
        productLevelList.get(i).setBounds(260,25,100,20);
        productLevelList.get(i).setFocusable(false);
        productLevelList.get(i).setText("0");
        if(dark){
            productLevelList.get(i).setBackground(new Color(41,41,41));
            productLevelList.get(i).setForeground(Color.white);
        }else{
            productLevelList.get(i).setBackground(Color.white);
            productLevelList.get(i).setForeground(Color.black);
        }

        productPerMinute = new JLabel("Produkte pro Minute:");
        productPerMinute.setBounds(370,0,130,20);
        if(dark){
            productPerMinute.setBackground(new Color(41,41,41));
            productPerMinute.setForeground(Color.white);
        }else{
            productPerMinute.setBackground(Color.white);
            productPerMinute.setForeground(Color.black);
        }

        productPerMinuteList.add(new JTextField());
        productPerMinuteList.get(i).setBounds(370,25,130,20);
        if(dark){
            productPerMinuteList.get(i).setBackground(new Color(41,41,41));
            productPerMinuteList.get(i).setForeground(Color.white);
        }else{
            productPerMinuteList.get(i).setBackground(Color.white);
            productPerMinuteList.get(i).setForeground(Color.black);
        }

        productPerMinuteNotANumberList.add(new JLabel());
        productPerMinuteNotANumberList.get(i).setText("Hier dürfen nur Zahlen angegeben werden!");
        productPerMinuteNotANumberList.get(i).setForeground(Color.red);
        productPerMinuteNotANumberList.get(i).setBounds(385,45, 300,20);
        productPerMinuteNotANumberList.get(i).setVisible(false);
        if(dark){
            productPerMinuteNotANumberList.get(i).setBackground(new Color(41,41,41));
            productPerMinuteNotANumberList.get(i).setForeground(Color.white);
        }else{
            productPerMinuteNotANumberList.get(i).setBackground(Color.white);
            productPerMinuteNotANumberList.get(i).setForeground(Color.black);
        }

        productBuy = new JLabel();
        productBuy.setText("Produkte 1x");
        productBuy.setBounds(510,0,130,20);
        if(dark){
            productBuy.setBackground(new Color(41,41,41));
            productBuy.setForeground(Color.white);
        }else{
            productBuy.setBackground(Color.white);
            productBuy.setForeground(Color.black);
        }

        productBuyList.add(new JTextField());
        productBuyList.get(i).setBounds(510,25,130,20);
        productBuyList.get(i).disable();
        if(dark){
            productBuyList.get(i).setBackground(new Color(41,41,41));
            productBuyList.get(i).setForeground(Color.white);
        }else{
            productBuyList.get(i).setBackground(Color.white);
            productBuyList.get(i).setForeground(Color.black);
        }

        productSwitchMode.add(new JButton());
        productSwitchMode.get(i).setBounds(650,25,70,20);
        productSwitchMode.get(i).setText("Switch");
        productSwitchMode.get(i).setFont(new Font("TimesRoman",Font.PLAIN,11));
        productSwitchModeIndex[i]=0;
        if(dark){
            productSwitchMode.get(i).setBackground(new Color(41,41,41));
            productSwitchMode.get(i).setForeground(Color.white);
        }else{
            productSwitchMode.get(i).setBackground(Color.white);
            productSwitchMode.get(i).setForeground(Color.black);
        }
        productSwitchMode.get(i).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(productSwitchModeIndex[i]==0){
                    productSwitchModeIndex[i]=1;
                }else{
                    productSwitchModeIndex[i]=0;
                }
            }
        });

        clear = new JButton();
        clear.setText("🗑");
        clear.setBounds(740,15,50,30);
        clear.setFont(new Font("TimesRoman",Font.PLAIN,20));
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productComboBoxList.get(i).setSelectedItem("Nichts");
                productPerMinuteList.get(i).setText("");
                productBuyList.get(i).setText("");
                productPerMinuteList.get(i).enable();
                productBuyList.get(i).disable();
                productSwitchModeIndex[i]=0;
                frame.repaint();
            }
        });
        if(dark){
            clear.setBackground(new Color(41,41,41));
            clear.setForeground(Color.white);
        }else{
            clear.setBackground(Color.white);
            clear.setForeground(Color.black);
        }

        panel1.add(product);
        panel1.add(productComboBoxList.get(i));
        panel1.add(productTrade);
        panel1.add(productTradeAreaList.get(i));
        panel1.add(productLevel);
        panel1.add(productLevelList.get(i));
        panel1.add(productPerMinute);
        panel1.add(productPerMinuteList.get(i));
        panel1.add(productPerMinuteNotANumberList.get(i));
        panel1.add(productBuy);
        panel1.add(productBuyList.get(i));
        panel1.add(productSwitchMode.get(i));
        panel1.add(clear);
        return panel1;
    }

    public static JPanel addResultItem(int i){
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setLayout(null);
        if(dark){
            panel.setBorder(new LineBorder(Color.white));
        }else{
            panel.setBorder(new LineBorder(Color.black));
        }
        panel.setBounds(0,0,255,70);
        if(dark){
            panel.setBackground(new Color(41,41,41));
            panel.setForeground(Color.white);
        }else{
            panel.setBackground(Color.white);
            panel.setForeground(Color.black);
        }

        JLabel product = new JLabel("Produkt:");
        product.setBounds(10,0,100,20);
        if(dark){
            product.setBackground(new Color(41,41,41));
            product.setForeground(Color.white);
        }else{
            product.setBackground(Color.white);
            product.setForeground(Color.black);
        }

        productResultItemComboBox = new JComboBox();
        productResultItemComboBox.setBounds(10,25,130,20);
        productResultItemComboBox.addItem("Nichts");
        File file = new File("Product.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while((temp = reader.readLine())!=null){
                String[] data = temp.split("--");
                productResultItemComboBox.addItem(data[0]);
            }
        } catch (FileNotFoundException e) {
            getError(e);
        } catch (IOException e) {
            getError(e);
        }
        if(dark){
            productResultItemComboBox.setBackground(new Color(41,41,41));
            productResultItemComboBox.setForeground(Color.white);
        }else{
            productResultItemComboBox.setBackground(Color.white);
            productResultItemComboBox.setForeground(Color.black);
        }

        JLabel productTrade = new JLabel("Produkt Wert:");
        productTrade.setBounds(150,0,100,20);
        if(dark){
            productTrade.setBackground(new Color(41,41,41));
            productTrade.setForeground(Color.white);
        }else{
            productTrade.setBackground(Color.white);
            productTrade.setForeground(Color.black);
        }

        productResultItemTradeArea = new JTextArea();
        productResultItemTradeArea.setFocusable(false);
        productResultItemTradeArea.setBounds(150,25,100,20);
        productResultItemTradeArea.setText("0");
        if(dark){
            productResultItemTradeArea.setBackground(new Color(41,41,41));
            productResultItemTradeArea.setForeground(Color.white);
        }else{
            productResultItemTradeArea.setBackground(Color.white);
            productResultItemTradeArea.setForeground(Color.black);
        }

        JLabel productLevel = new JLabel("Produkt Level");
        productLevel.setBounds(260,0,100,20);
        if(dark){
            productLevel.setBackground(new Color(41,41,41));
            productLevel.setForeground(Color.white);
        }else{
            productLevel.setBackground(Color.white);
            productLevel.setForeground(Color.black);
        }

        productResultItemLevel = new JComboBox();
        productResultItemLevel.addItem("1.0 (Gewöhnlich)");
        productResultItemLevel.addItem("1.2 (Ungewöhnlich)");
        productResultItemLevel.addItem("1.4 (Selten)");
        productResultItemLevel.addItem("1.6 (Episch)");
        productResultItemLevel.addItem("2.0 (Legendär)");
        productResultItemLevel.setBounds(260,25,150,20);
        if(dark){
            productResultItemLevel.setBackground(new Color(41,41,41));
            productResultItemLevel.setForeground(Color.white);
        }else{
            productResultItemLevel.setBackground(Color.white);
            productResultItemLevel.setForeground(Color.black);
        }

        productResultItemLevelNotANumber = new JLabel();
        productResultItemLevelNotANumber.setText("Hier dürfen nur Zahlen angegeben werden!");
        productResultItemLevelNotANumber.setForeground(Color.red);
        productResultItemLevelNotANumber.setBounds(330,45, 300,20);
        productResultItemLevelNotANumber.setVisible(false);
        if(dark){
            productResultItemLevelNotANumber.setBackground(new Color(41,41,41));
            productResultItemLevelNotANumber.setForeground(Color.white);
        }else{
            productResultItemLevelNotANumber.setBackground(Color.white);
            productResultItemLevelNotANumber.setForeground(Color.black);
        }

        panel.add(product);
        panel.add(productResultItemComboBox);
        panel.add(productTrade);
        panel.add(productResultItemTradeArea);
        panel.add(productLevel);
        panel.add(productResultItemLevel);
        panel.add(productResultItemLevelNotANumber);
        return panel;
    }

    public static JPanel addResult(){
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setLayout(null);
        panel.setBorder(new LineBorder(Color.black));
        panel.setBounds(0,0,255,70);
        if(dark){
            panel.setBackground(new Color(41,41,41));
            panel.setForeground(Color.white);
        }else{
            panel.setBackground(Color.white);
            panel.setForeground(Color.black);
        }

        productResultProductHeadlineProducts = new JLabel();
        productResultProductHeadlineProducts.setText("Produkte:");
        productResultProductHeadlineProducts.setBounds(100,5,150,10);
        if(dark){
            productResultProductHeadlineProducts.setBackground(new Color(41,41,41));
            productResultProductHeadlineProducts.setForeground(Color.white);
        }else{
            productResultProductHeadlineProducts.setBackground(Color.white);
            productResultProductHeadlineProducts.setForeground(Color.black);
        }

        productResultProductHeadlinePerMinute = new JLabel();
        productResultProductHeadlinePerMinute.setText("Produkt pro Minute:");
        productResultProductHeadlinePerMinute.setBounds(250,5,150,10);
        if(dark){
            productResultProductHeadlinePerMinute.setBackground(new Color(41,41,41));
            productResultProductHeadlinePerMinute.setForeground(Color.white);
        }else{
            productResultProductHeadlinePerMinute.setBackground(Color.white);
            productResultProductHeadlinePerMinute.setForeground(Color.black);
        }

        productResultProductHeadlineBuy = new JLabel();
        productResultProductHeadlineBuy.setText("Einzutauschen ist:");
        productResultProductHeadlineBuy.setBounds(450,5,150,10);
        if(dark){
            productResultProductHeadlineBuy.setBackground(new Color(41,41,41));
            productResultProductHeadlineBuy.setForeground(Color.white);
        }else{
            productResultProductHeadlineBuy.setBackground(Color.white);
            productResultProductHeadlineBuy.setForeground(Color.black);
        }

        productResultHeadline = new JLabel();
        productResultHeadline.setText("Insgesamt:");
        productResultHeadline.setBounds(10,20,200,30);
        if(dark){
            productResultHeadline.setBackground(new Color(41,41,41));
            productResultHeadline.setForeground(Color.white);
        }else{
            productResultHeadline.setBackground(Color.white);
            productResultHeadline.setForeground(Color.black);
        }

        productResultProductResult = new JLabel();
        productResultProductResult.setText("---");
        productResultProductResult.setBounds(100,20,150,30);
        if(dark){
            productResultProductResult.setBackground(new Color(41,41,41));
            productResultProductResult.setForeground(Color.white);
        }else{
            productResultProductResult.setBackground(Color.white);
            productResultProductResult.setForeground(Color.black);
        }

        productResultProductPerMinuteResult = new JLabel();
        productResultProductPerMinuteResult.setText("0");
        productResultProductPerMinuteResult.setBounds(250,20,100,30);
        if(dark){
            productResultProductPerMinuteResult.setBackground(new Color(41,41,41));
            productResultProductPerMinuteResult.setForeground(Color.white);
        }else{
            productResultProductPerMinuteResult.setBackground(Color.white);
            productResultProductPerMinuteResult.setForeground(Color.black);
        }

        productResultProductBuyResult = new JLabel();
        productResultProductBuyResult.setText("0");
        productResultProductBuyResult.setBounds(450,20,100,30);
        if(dark){
            productResultProductBuyResult.setBackground(new Color(41,41,41));
            productResultProductBuyResult.setForeground(Color.white);
        }else{
            productResultProductBuyResult.setBackground(Color.white);
            productResultProductBuyResult.setForeground(Color.black);
        }

        JLabel line = new JLabel();
        line.setText("-----------------------------------------------------------------------------------------------------------------------------------------------");
        line.setBounds(0,37,640,15);
        line.setFont(new Font("TimesRoman",Font.PLAIN,20));
        if(dark){
            line.setBackground(new Color(41,41,41));
            line.setForeground(Color.white);
        }else{
            line.setBackground(Color.white);
            line.setForeground(Color.black);
        }

        productResultProductList = new JTextArea();
        productResultProductList.setBounds(100,50,150,170);
        productResultProductList.setFocusable(false);
        if(dark){
            productResultProductList.setBackground(new Color(41,41,41));
            productResultProductList.setForeground(Color.white);
        }else{
            productResultProductList.setBackground(Color.white);
            productResultProductList.setForeground(Color.black);
        }

        productResultProductsPerMinuteList = new JTextArea();
        productResultProductsPerMinuteList.setBounds(250,50,100,170);
        productResultProductsPerMinuteList.setFocusable(false);
        if(dark){
            productResultProductsPerMinuteList.setBackground(new Color(41,41,41));
            productResultProductsPerMinuteList.setForeground(Color.white);
        }else{
            productResultProductsPerMinuteList.setBackground(Color.white);
            productResultProductsPerMinuteList.setForeground(Color.black);
        }

        productResultProductBuyList = new JTextArea();
        productResultProductBuyList.setBounds(450,50,100,170);
        productResultProductBuyList.setFocusable(false);
        if(dark){
            productResultProductBuyList.setBackground(new Color(41,41,41));
            productResultProductBuyList.setForeground(Color.white);
        }else{
            productResultProductBuyList.setBackground(Color.white);
            productResultProductBuyList.setForeground(Color.black);
        }

        panel.add(productResultProductHeadlineProducts);
        panel.add(productResultProductHeadlinePerMinute);
        panel.add(productResultProductHeadlineBuy);
        panel.add(productResultHeadline);
        panel.add(productResultProductPerMinuteResult);
        panel.add(productResultProductResult);
        panel.add(productResultProductBuyResult);
        panel.add(productResultProductList);
        panel.add(productResultProductsPerMinuteList);
        panel.add(productResultProductBuyList);
        panel.add(line);

        return panel;
    }

    public static void getError(Exception e){
        e.printStackTrace();
        JFrame frame = new JFrame("error");
        frame.setSize(300,240);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel headline = new JLabel("Es ist ein Fehler aufgetreten...");
        headline.setBounds(10,10,280,20);
        headline.setFocusable(false);
        headline.setVisible(true);

        JTextArea content = new JTextArea(e.getMessage());
        content.setBounds(10, 40,280,100);
        content.setFocusable(false);
        content.setVisible(true);

        frame.add(headline);
        frame.add(content);
        frame.repaint();
    }
    public static JPanel addSettingsPanel(){
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setLayout(null);
        if(dark){
            panel.setBorder(new LineBorder(Color.white));
            panel.setBackground(new Color(41,41,41));
            panel.setForeground(Color.white);
        }else{
            panel.setBorder(new LineBorder(Color.black));
            panel.setBackground(Color.white);
            panel.setForeground(Color.black);
        }

        JLabel settingsHeadline = new JLabel("Einstellungen");
        settingsHeadline.setBounds(5,5,200,30);
        settingsHeadline.setFont(new Font("TimesRoman",Font.PLAIN,20));
        if(dark){
            settingsHeadline.setBackground(new Color(41,41,41));
            settingsHeadline.setForeground(Color.white);
        }else{
            settingsHeadline.setForeground(Color.black);
            settingsHeadline.setBackground(Color.white);
        }

        darkModeEnable = new JCheckBox("Dark Mode");
        darkModeEnable.setBounds(5,40,100,20);
        darkModeEnable.setSelected(true);
        if(dark){
            darkModeEnable.setBackground(new Color(41,41,41));
            darkModeEnable.setForeground(Color.white);
        }else{
            darkModeEnable.setForeground(Color.black);
            darkModeEnable.setBackground(Color.white);
        }

        if(normalconfig.get("darkmode").equalsIgnoreCase("true")){
            darkModeEnable.setSelected(true);
        }else{
            darkModeEnable.setSelected(false);
        }

        settingsSubmitButton = new JButton("Anwenden");
        settingsSubmitButton.setBounds(210,170,130,20);
        settingsSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                normalconfig.set("darkmode",""+darkModeEnable.isSelected());
                String tempSave = DataManager.saveData();
                frame.dispose();
                GUIUpdater.timer.cancel();
                importItems = new int[10];
                darkModeEnable = new JCheckBox();
                productComboBoxList = new ArrayList<JComboBox>();
                productTradeAreaList = new ArrayList<JTextArea>();
                productLevelList = new ArrayList<JTextArea>();
                productPerMinuteList = new ArrayList<JTextField>();
                productPerMinuteNotANumberList = new ArrayList<JLabel>();
                productBuyList = new ArrayList<JTextField>();
                productSwitchMode = new ArrayList<JButton>();
                productImage = new ArrayList<JLabel>();
                importProductList = new ArrayList<JPanel>();
                productSwitchModeIndex = new int[10];
                new GUI();
                DataManager.loadData(tempSave);
                frame.repaint();
            }
        });

        if(dark){
            settingsSubmitButton.setBackground(new Color(41,41,41));
            settingsSubmitButton.setForeground(Color.white);
            settingsSubmitButton.setBorder(new LineBorder(Color.white));
        }else{
            settingsSubmitButton.setBackground(Color.white);
            settingsSubmitButton.setForeground(Color.black);
            settingsSubmitButton.setBorder(new LineBorder(Color.black));
        }

        panel.add(settingsHeadline);
        panel.add(settingsSubmitButton);
        panel.add(darkModeEnable);
        return panel;
    }

    public static JPanel addSaveMenu(){
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setLayout(null);
        if(dark){
            panel.setBackground(new Color(41,41,41));
            panel.setForeground(Color.white);
            panel.setBorder(new LineBorder(Color.white));
        }else{
            panel.setBackground(Color.white);
            panel.setForeground(Color.black);
            panel.setBorder(new LineBorder(Color.black));
        }

        JLabel saveHeadline = new JLabel("Daten Speichern");
        saveHeadline.setBounds(5,5,200,30);
        saveHeadline.setFont(new Font("TimesRoman",Font.PLAIN,20));
        if(dark){
            saveHeadline.setBackground(new Color(41,41,41));
            saveHeadline.setForeground(Color.white);
        }else{
            saveHeadline.setForeground(Color.black);
            saveHeadline.setBackground(Color.white);
        }

        saves = new JComboBox();
        saves.setBounds(5,40,300,20);
        if(dark){
            saves.setBackground(new Color(41,41,41));
            saves.setForeground(Color.white);
            saves.setBorder(new LineBorder(Color.white));
        }else{
            saves.setBackground(Color.white);
            saves.setForeground(Color.black);
            saves.setBorder(new LineBorder(Color.black));
        }

        JButton saveAsOldSave = new JButton("Überschreiben");
        saveAsOldSave.setBounds(5,65,100,20);
        if(dark){
            saveAsOldSave.setBackground(new Color(41,41,41));
            saveAsOldSave.setForeground(Color.white);
            saveAsOldSave.setBorder(new LineBorder(Color.white));
        }else{
            saveAsOldSave.setBackground(Color.white);
            saveAsOldSave.setForeground(Color.black);
            saveAsOldSave.setBorder(new LineBorder(Color.black));
        }
        saveAsOldSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("data/saves/"+saves.getSelectedItem().toString());
                try {
                    PrintWriter writer = new PrintWriter(file);
                    writer.write(DataManager.saveData());
                    writer.flush();
                    saveMenu.setVisible(false);
                    saveButton.setBounds(1440,3,50,50);
                } catch (FileNotFoundException ex) {
                    getError(ex);
                }
            }
        });

        JLabel cut = new JLabel("---------------------------------------------------------");
        cut.setBounds(0,85,400,9);
        cut.setFont(new Font("TimesRoman",Font.PLAIN,30));
        if(dark){
            cut.setBackground(new Color(41,41,41));
            cut.setForeground(Color.white);
        }else{
            cut.setBackground(Color.white);
            cut.setForeground(Color.black);
        }

        JLabel headline = new JLabel("Neuen Speicherstand erstellen");
        headline.setBounds(5,105,400,20);
        if(dark){
            headline.setBackground(new Color(41,41,41));
            headline.setForeground(Color.white);
        }else{
            headline.setBackground(Color.white);
            headline.setForeground(Color.black);
        }

        JTextField saveAsNewFileName = new JTextField();
        saveAsNewFileName.setBounds(5,125,300,20);
        if(dark){
            saveAsNewFileName.setBackground(new Color(41,41,41));
            saveAsNewFileName.setForeground(Color.white);
            saveAsNewFileName.setBorder(new LineBorder(Color.white));
        }else{
            saveAsNewFileName.setBackground(Color.white);
            saveAsNewFileName.setForeground(Color.black);
            saveAsNewFileName.setBorder(new LineBorder(Color.black));
        }

        JButton saveAsNew = new JButton("Speichen als");
        saveAsNew.setBounds(5,155,120,20);
        if(dark){
            saveAsNew.setBackground(new Color(41,41,41));
            saveAsNew.setForeground(Color.white);
            saveAsNew.setBorder(new LineBorder(Color.white));
        }else{
            saveAsNew.setBackground(Color.white);
            saveAsNew.setForeground(Color.black);
            saveAsNew.setBorder(new LineBorder(Color.black));
        }
        saveAsNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File file = new File("data/saves/"+saveAsNewFileName.getText()+".txt");
                    if(file.exists()){
                        saveAsNewFileName.setText("Diese datei exestiert bereits.");
                    }else{
                        PrintWriter writer = new PrintWriter(file);
                        writer.write(DataManager.saveData()+"\n");
                        writer.flush();
                        saveAsNewFileName.setText("");
                        saveMenu.setVisible(false);
                        saveButton.setBounds(1440,3,50,50);
                    }
                } catch (IOException ex) {
                    getError(ex);
                }
            }
        });

        sessionLink = new JButton("Session Link Kopieren");
        sessionLink.setBounds(5,195,225,20);
        if(dark){
            sessionLink.setBackground(new Color(41,41,41));
            sessionLink.setForeground(Color.white);
            sessionLink.setBorder(new LineBorder(Color.white));
        }else{
            sessionLink.setBackground(Color.white);
            sessionLink.setForeground(Color.black);
            sessionLink.setBorder(new LineBorder(Color.black));
        }
        sessionLink.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(DataManager.saveData()),null);
            }
        });

        JButton openSavesDic = new JButton("Open Folder");
        openSavesDic.setBounds(245,195,100,20);
        openSavesDic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().open(new File("data/saves/"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        if(dark){
            openSavesDic.setBackground(new Color(41,41,41));
            openSavesDic.setForeground(Color.white);
            openSavesDic.setBorder(new LineBorder(Color.white));
        }else{
            openSavesDic.setBackground(Color.white);
            openSavesDic.setForeground(Color.black);
            openSavesDic.setBorder(new LineBorder(Color.black));
        }


        panel.add(saveAsNewFileName);
        panel.add(headline);
        panel.add(saveAsNew);
        panel.add(cut);
        panel.add(saveAsOldSave);
        panel.add(saves);
        panel.add(saveHeadline);
        panel.add(openSavesDic);
        panel.add(sessionLink);
        return panel;
    }

    public static JPanel addLoadMenu(){
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setLayout(null);
        if(dark){
            panel.setBackground(new Color(41,41,41));
            panel.setForeground(Color.white);
            panel.setBorder(new LineBorder(Color.white));
        }else{
            panel.setBackground(Color.white);
            panel.setForeground(Color.black);
            panel.setBorder(new LineBorder(Color.black));
        }

        JLabel loadHeadline = new JLabel("Daten Laden");
        loadHeadline.setBounds(5,5,200,30);
        loadHeadline.setFont(new Font("TimesRoman",Font.PLAIN,20));
        if(dark){
            loadHeadline.setBackground(new Color(41,41,41));
            loadHeadline.setForeground(Color.white);
        }else{
            loadHeadline.setForeground(Color.black);
            loadHeadline.setBackground(Color.white);
        }

        JLabel dataHeadline = new JLabel();
        dataHeadline.setText("Datei zum laden Auswählen.");
        dataHeadline.setBounds(5,35,300,20);
        if(dark){
            dataHeadline.setBackground(new Color(41,41,41));
            dataHeadline.setForeground(Color.white);
        }else{
            dataHeadline.setBackground(Color.white);
            dataHeadline.setForeground(Color.black);
        }

        importFileName = new JComboBox();
        importFileName.setBounds(5,60,300,20);
        if(dark){
            importFileName.setBackground(new Color(41,41,41));
            importFileName.setForeground(Color.white);
            importFileName.setBorder(new LineBorder(Color.white));
        }else{
            importFileName.setBackground(Color.white);
            importFileName.setForeground(Color.black);
            importFileName.setBorder(new LineBorder(Color.black));
        }

        JButton load = new JButton("Load Data");
        load.setBounds(5,85,100,20);
        if(dark){
            load.setBackground(new Color(41,41,41));
            load.setForeground(Color.white);
            load.setBorder(new LineBorder(Color.white));
        }else{
            load.setBackground(Color.white);
            load.setForeground(Color.black);
            load.setBorder(new LineBorder(Color.black));
        }
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File file = new File("data/saves/"+importFileName.getSelectedItem().toString());
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    DataManager.loadData(reader.readLine());
                    loadMenu.setVisible(false);
                    loadButton.setBounds(1380,3,50,50);
                } catch (FileNotFoundException ex) {
                    getError(ex);
                } catch (IOException ex) {
                    getError(ex);
                }
            }
        });

        JLabel cut = new JLabel("---------------------------------------------------------");
        cut.setBounds(0,105,400,9);
        cut.setFont(new Font("TimesRoman",Font.PLAIN,30));
        if(dark){
            cut.setBackground(new Color(41,41,41));
            cut.setForeground(Color.white);
        }else{
            cut.setBackground(Color.white);
            cut.setForeground(Color.black);
        }

        JLabel headline = new JLabel("Hier Session Link einfügen.");
        headline.setBounds(5,115,190,20);
        if(dark){
            headline.setBackground(new Color(41,41,41));
            headline.setForeground(Color.white);
        }else{
            headline.setBackground(Color.white);
            headline.setForeground(Color.black);
        }

        JTextField sessionlink = new JTextField();
        sessionlink.setBounds(5,145,200,20);
        if(dark){
            sessionlink.setBackground(new Color(41,41,41));
            sessionlink.setForeground(Color.white);
            sessionlink.setBorder(new LineBorder(Color.white));
        }else{
            sessionlink.setBackground(Color.white);
            sessionlink.setForeground(Color.black);
            sessionlink.setBorder(new LineBorder(Color.black));
        }

         JButton sessionLinkSubmit = new JButton("Session Link Einfügen");
        sessionLinkSubmit.setBounds(210,145,130,20);
        if(dark){
            sessionLinkSubmit.setBackground(new Color(41,41,41));
            sessionLinkSubmit.setForeground(Color.white);
            sessionLinkSubmit.setBorder(new LineBorder(Color.white));
        }else{
            sessionLinkSubmit.setBackground(Color.white);
            sessionLinkSubmit.setForeground(Color.black);
            sessionLinkSubmit.setBorder(new LineBorder(Color.black));
        }
        sessionLinkSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataManager.loadData(sessionlink.getText());
                loadMenu.setVisible(false);
                loadButton.setBounds(1380,3,50,50);
            }
        });

        panel.add(importFileName);
        panel.add(cut);
        panel.add(dataHeadline);
        panel.add(sessionlink);
        panel.add(headline);
        panel.add(load);
        panel.add(loadHeadline);
        panel.add(sessionLinkSubmit);
        return panel;
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }
}
