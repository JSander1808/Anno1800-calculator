package Calculator;

import FileManager.Config;
import FileManager.ProductDetail;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GUI {

    public static ArrayList<JComboBox> productComboBoxList = new ArrayList<JComboBox>();
    public static ArrayList<JTextArea> productTradeAreaList = new ArrayList<JTextArea>();
    public static ArrayList<JTextArea> productLevelList = new ArrayList<JTextArea>();
    public static ArrayList<JTextField> productPerMinuteList = new ArrayList<JTextField>();
    public static ArrayList<JLabel> productPerMinuteNotANumberList = new ArrayList<JLabel>();

    public GUI(){
        JFrame frame = new JFrame("Anno1800 - Calculator");
        frame.setSize(1420,820);
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
        importProductPanel.setBounds(10,100,600,700);
        importProductPanel.setLayout(null);

        ArrayList<JPanel> importProductList = new ArrayList<JPanel>();

        for(int i = 0;i<10;i++){
            importProductList.add(addImportProduct(i));
            importProductList.get(i).setBounds(0,65*i,600,65);
            importProductPanel.add(importProductList.get(i));
        }

        JLabel working = new JLabel("➜");
        working.setBounds(620,346,150,150);
        working.setFont(new Font("TimesRoman",Font.PLAIN,150));

        JPanel resultItem = addResultItem(10);
        resultItem.setBounds(800,350,600,65);


        frame.add(headline);
        frame.add(headlineImportProduct);
        frame.add(importProductPanel);
        frame.add(working);
        frame.add(resultItem);
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
        productPerMinuteNotANumberList.get(i).setBounds(330,45, 300,20);
        productPerMinuteNotANumberList.get(i).setVisible(false);

        panel.add(product);
        panel.add(productComboBoxList.get(i));
        panel.add(productTrade);
        panel.add(productTradeAreaList.get(i));
        panel.add(productLevel);
        panel.add(productLevelList.get(i));
        panel.add(productPerMinute);
        panel.add(productPerMinuteList.get(i));
        panel.add(productPerMinuteNotANumberList.get(i));
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

        productComboBoxList.add(new JComboBox());
        productComboBoxList.get(i).setBounds(10,25,130,20);
        productComboBoxList.get(i).addItem("Nichts");
        File file = new File("Product.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while((temp = reader.readLine())!=null){
                String[] data = temp.split("--");
                productComboBoxList.get(i).addItem(data[0]);
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
        productPerMinuteNotANumberList.get(i).setBounds(330,45, 300,20);
        productPerMinuteNotANumberList.get(i).setVisible(false);

        panel.add(product);
        panel.add(productComboBoxList.get(i));
        panel.add(productTrade);
        panel.add(productTradeAreaList.get(i));
        panel.add(productLevel);
        panel.add(productLevelList.get(i));
        panel.add(productPerMinute);
        panel.add(productPerMinuteList.get(i));
        panel.add(productPerMinuteNotANumberList.get(i));
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

}
