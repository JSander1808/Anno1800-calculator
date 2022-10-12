package Calculator;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class GUI {

    public static JFrame frame;
    public static ArrayList<JComboBox> productComboBoxList = new ArrayList<JComboBox>();
    public static ArrayList<JTextArea> productTradeAreaList = new ArrayList<JTextArea>();
    public static ArrayList<JTextArea> productLevelList = new ArrayList<JTextArea>();
    public static ArrayList<JTextField> productPerMinuteList = new ArrayList<JTextField>();
    public static ArrayList<JLabel> productPerMinuteNotANumberList = new ArrayList<JLabel>();
    public static ArrayList<JTextField> productBuyList = new ArrayList<JTextField>();
    public static ArrayList<JButton> productSwitchMode = new ArrayList<JButton>();
    public static ArrayList<JLabel> productImage = new ArrayList<JLabel>();
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

    public GUI(){
        frame = new JFrame("Anno1800 - Calculator");
        frame.setSize(1620,820);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel headline = new JLabel("Anno1800 - Calculator");
        headline.setBounds(10,0,510,60);
        headline.setVisible(true);
        headline.setFont(new Font("TimesRoman",Font.PLAIN,40));

        JLabel headlineImportProduct = new JLabel();
        headlineImportProduct.setText("Import Produkte");
        headlineImportProduct.setBounds(20,60,400,40);
        headlineImportProduct.setFont(new Font("TimesRoman",Font.PLAIN,30));

        JPanel importProductPanel = new JPanel();
        importProductPanel.setBounds(10,100,820,700);
        importProductPanel.setLayout(null);

        ArrayList<JPanel> importProductList = new ArrayList<JPanel>();

        for(int i = 0;i<10;i++){
            importProductList.add(addImportProduct(i));
            importProductList.get(i).setBounds(0,65*i,820,65);
            importProductPanel.add(importProductList.get(i));
        }

        JLabel working = new JLabel("❰");
        working.setBounds(840,346,150,150);
        working.setFont(new Font("TimesRoman",Font.PLAIN,150));

        JLabel exportHeadline = new JLabel("Export Produkte");
        exportHeadline.setBounds(950,300,400,50);
        exportHeadline.setFont(new Font("TimesRoman",Font.PLAIN,30));

        JPanel resultItem = addResultItem(10);
        resultItem.setBounds(950,350,600,65);

        JPanel result = addResult();
        result.setBounds(950,415,600,250);


        frame.add(headline);
        frame.add(headlineImportProduct);
        frame.add(importProductPanel);
        frame.add(working);
        frame.add(resultItem);
        frame.add(result);
        frame.add(exportHeadline);
        frame.setVisible(true);
        new GUIUpdater();
    }

    public static JPanel addImportProduct(int i){
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setLayout(null);
        panel.setBorder(new LineBorder(Color.black));
        panel.setBounds(0,0,255,70);

        JLabel product = new JLabel("Produkt:");
        product.setBounds(10,0,100,20);

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

        JLabel productTrade = new JLabel("Produkt Wert:");
        productTrade.setBounds(150,0,100,20);

        productTradeAreaList.add(new JTextArea());
        productTradeAreaList.get(i).setFocusable(false);
        productTradeAreaList.get(i).setBounds(150,25,100,20);
        productTradeAreaList.get(i).setText("0");

        JLabel productLevel = new JLabel("Produkt Level");
        productLevel.setBounds(260,0,100,20);

        productLevelList.add(new JTextArea());
        productLevelList.get(i).setBounds(260,25,100,20);
        productLevelList.get(i).setFocusable(false);
        productLevelList.get(i).setText("0");

        JLabel productPerMinute = new JLabel("Produkte pro Minute:");
        productPerMinute.setBounds(370,0,130,20);

        productPerMinuteList.add(new JTextField());
        productPerMinuteList.get(i).setBounds(370,25,130,20);

        productPerMinuteNotANumberList.add(new JLabel());
        productPerMinuteNotANumberList.get(i).setText("Hier dürfen nur Zahlen angegeben werden!");
        productPerMinuteNotANumberList.get(i).setForeground(Color.red);
        productPerMinuteNotANumberList.get(i).setBounds(385,45, 300,20);
        productPerMinuteNotANumberList.get(i).setVisible(false);

        JLabel productBuy = new JLabel();
        productBuy.setText("Produkte 1x");
        productBuy.setBounds(510,0,130,20);

        productBuyList.add(new JTextField());
        productBuyList.get(i).setBounds(510,25,130,20);
        productBuyList.get(i).disable();

        productSwitchMode.add(new JButton());
        productSwitchMode.get(i).setBounds(650,25,70,20);
        productSwitchMode.get(i).setText("Switch");
        productSwitchMode.get(i).setFont(new Font("TimesRoman",Font.PLAIN,11));
        productSwitchModeIndex[i]=0;
        productSwitchMode.get(i).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(productSwitchModeIndex[i]==0){
                    productPerMinuteList.get(i).disable();
                    productBuyList.get(i).enable();
                    productSwitchModeIndex[i]=1;
                    frame.repaint();
                }else{
                    productPerMinuteList.get(i).enable();
                    productBuyList.get(i).disable();
                    productSwitchModeIndex[i]=0;
                    frame.repaint();
                }
            }
        });

        JButton clear = new JButton();
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

        panel.add(product);
        panel.add(productComboBoxList.get(i));
        panel.add(productTrade);
        panel.add(productTradeAreaList.get(i));
        panel.add(productLevel);
        panel.add(productLevelList.get(i));
        panel.add(productPerMinute);
        panel.add(productPerMinuteList.get(i));
        panel.add(productPerMinuteNotANumberList.get(i));
        panel.add(productBuy);
        panel.add(productBuyList.get(i));
        panel.add(productSwitchMode.get(i));
        panel.add(clear);
        return panel;
    }

    public static JPanel addResultItem(int i){
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setLayout(null);
        panel.setBorder(new LineBorder(Color.black));
        panel.setBounds(0,0,255,70);

        JLabel product = new JLabel("Produkt:");
        product.setBounds(10,0,100,20);

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

        JLabel productTrade = new JLabel("Produkt Wert:");
        productTrade.setBounds(150,0,100,20);

        productResultItemTradeArea = new JTextArea();
        productResultItemTradeArea.setFocusable(false);
        productResultItemTradeArea.setBounds(150,25,100,20);
        productResultItemTradeArea.setText("0");

        JLabel productLevel = new JLabel("Produkt Level");
        productLevel.setBounds(260,0,100,20);

        productResultItemLevel = new JComboBox();
        productResultItemLevel.addItem("1.0 (Gewöhnlich)");
        productResultItemLevel.addItem("1.2 (Ungewöhnlich)");
        productResultItemLevel.addItem("1.4 (Selten)");
        productResultItemLevel.addItem("1.6 (Episch)");
        productResultItemLevel.addItem("2.0 (Legendär)");
        productResultItemLevel.setBounds(260,25,150,20);

        productResultItemLevelNotANumber = new JLabel();
        productResultItemLevelNotANumber.setText("Hier dürfen nur Zahlen angegeben werden!");
        productResultItemLevelNotANumber.setForeground(Color.red);
        productResultItemLevelNotANumber.setBounds(330,45, 300,20);
        productResultItemLevelNotANumber.setVisible(false);

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

        productResultProductHeadlineProducts = new JLabel();
        productResultProductHeadlineProducts.setText("Produkte:");
        productResultProductHeadlineProducts.setBounds(100,5,150,10);

        productResultProductHeadlinePerMinute = new JLabel();
        productResultProductHeadlinePerMinute.setText("Produkt pro Minute:");
        productResultProductHeadlinePerMinute.setBounds(250,5,150,10);

        productResultProductHeadlineBuy = new JLabel();
        productResultProductHeadlineBuy.setText("Einzutauschen ist:");
        productResultProductHeadlineBuy.setBounds(450,5,150,10);

        productResultHeadline = new JLabel();
        productResultHeadline.setText("Insgesamt:");
        productResultHeadline.setBounds(10,20,200,30);

        productResultProductResult = new JLabel();
        productResultProductResult.setText("---");
        productResultProductResult.setBounds(100,20,150,30);

        productResultProductPerMinuteResult = new JLabel();
        productResultProductPerMinuteResult.setText("0");
        productResultProductPerMinuteResult.setBounds(250,20,100,30);

        productResultProductBuyResult = new JLabel();
        productResultProductBuyResult.setText("0");
        productResultProductBuyResult.setBounds(450,20,100,30);

        JLabel line = new JLabel();
        line.setText("-----------------------------------------------------------------------------------------------------------------------------------------------");
        line.setBounds(0,37,640,15);
        line.setFont(new Font("TimesRoman",Font.PLAIN,20));

        productResultProductList = new JTextArea();
        productResultProductList.setBounds(100,50,150,170);
        productResultProductList.setFocusable(false);

        productResultProductsPerMinuteList = new JTextArea();
        productResultProductsPerMinuteList.setBounds(250,50,100,170);
        productResultProductsPerMinuteList.setFocusable(false);

        productResultProductBuyList = new JTextArea();
        productResultProductBuyList.setBounds(450,50,100,170);
        productResultProductBuyList.setFocusable(false);

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

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

}
