package Calculator;

import FileManager.Config;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import Calculator.GUI;

import static Calculator.GUI.dark;
import static Calculator.GUI.logoBufferedImage;

public class GUIUpdater {

    public static Timer timer;
    public GUIUpdater(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Config config = new Config("Product.txt");
                StringBuilder productsList = new StringBuilder();
                StringBuilder productPerMinute = new StringBuilder();
                StringBuilder productBuy = new StringBuilder();
                double itemsComplett = 0;
                for(int i = 0;i<10;i++){
                    if(!GUI.productComboBoxList.get(i).getSelectedItem().toString().equalsIgnoreCase("Nichts")){
                        GUI.productTradeAreaList.get(i).setText(String.valueOf(config.get(GUI.productComboBoxList.get(i).getSelectedItem().toString()).getTrade()));
                        GUI.productLevelList.get(i).setText(String.valueOf(config.get(GUI.productComboBoxList.get(i).getSelectedItem().toString()).getLevel()));
                    }else{
                        GUI.productTradeAreaList.get(i).setText("0");
                        GUI.productLevelList.get(i).setText("0");
                    }
                    try{
                        double temp = Double.parseDouble(GUI.productPerMinuteList.get(i).getText());
                        GUI.productPerMinuteNotANumberList.get(i).setVisible(false);
                    }catch(Exception e){
                        if(GUI.productPerMinuteList.get(i).getText().replace(" ","").equalsIgnoreCase("")||GUI.productPerMinuteList.get(i).getText()==null){
                            GUI.productPerMinuteNotANumberList.get(i).setVisible(false);
                        }else{
                            GUI.productPerMinuteNotANumberList.get(i).setVisible(true);
                        }
                    }


                    if(!GUI.productResultItemComboBox.getSelectedItem().toString().equalsIgnoreCase("Nichts")){
                        GUI.productResultItemTradeArea.setText(String.valueOf(config.get(GUI.productResultItemComboBox.getSelectedItem().toString()).getTrade()));
                    }else{
                        GUI.productResultItemTradeArea.setText("0");
                    }
                    if(GUI.importItems[i]==1){
                        GUI.importProductList.get(i).setVisible(true);
                    }else{
                        GUI.importProductList.get(i).setVisible(false);
                    }
                    if(GUI.productSwitchModeIndex[i]==1){
                        GUI.productPerMinuteList.get(i).disable();
                        GUI.productBuyList.get(i).enable();
                        GUI.productSwitchModeIndex[i]=1;
                        GUI.frame.repaint();
                    }else{
                        GUI.productPerMinuteList.get(i).enable();
                        GUI.productBuyList.get(i).disable();
                        GUI.productSwitchModeIndex[i]=0;
                        GUI.frame.repaint();
                    }
                }
                for(int j = 0;j<10;j++){
                    if(!GUI.productResultItemComboBox.getSelectedItem().toString().equalsIgnoreCase("Nichts")){
                        if(!GUI.productComboBoxList.get(j).getSelectedItem().toString().equalsIgnoreCase("Nichts")){
                            if(!GUI.productPerMinuteList.get(j).getText().replace(" ","").equalsIgnoreCase("")||!GUI.productBuyList.get(j).getText().replace(" ","").equalsIgnoreCase("")){
                                try{
                                    productsList.append(GUI.productComboBoxList.get(j).getSelectedItem().toString()).append("\n");
                                    int products = 0;
                                    if(GUI.productSwitchModeIndex[j]==0){
                                        products = Integer.valueOf(GUI.productPerMinuteList.get(j).getText());
                                    }else if(GUI.productSwitchModeIndex[j]==1){
                                        products = Integer.valueOf(GUI.productBuyList.get(j).getText());
                                    }
                                    double productTrade = Double.valueOf(config.get(GUI.productComboBoxList.get(j).getSelectedItem().toString()).getTrade());
                                    double productLevel = Double.valueOf(config.get(GUI.productComboBoxList.get(j).getSelectedItem().toString()).getLevel());
                                    double E = Double.valueOf(config.get(GUI.productResultItemComboBox.getSelectedItem().toString()).getTrade());
                                    double I = productTrade;
                                    double e = 0;
                                    if(GUI.productResultItemLevel.getSelectedItem().toString().equalsIgnoreCase("1.0 (Gewöhnlich)")){
                                        e=1.0;
                                    }else if(GUI.productResultItemLevel.getSelectedItem().toString().equalsIgnoreCase("1.2 (Ungewöhnlich)")){
                                        e=1.2;
                                    }else if(GUI.productResultItemLevel.getSelectedItem().toString().equalsIgnoreCase("1.4 (Selten)")){
                                        e=1.4;
                                    }else if(GUI.productResultItemLevel.getSelectedItem().toString().equalsIgnoreCase("1.6 (Episch)")){
                                        e=1.6;
                                    }else if(GUI.productResultItemLevel.getSelectedItem().toString().equalsIgnoreCase("2.0 (Legendär)")){
                                        e=2.0;
                                    }
                                    double in = productLevel;
                                    double complettitem = 0;
                                    if(((E/I)*(e/in))>1){
                                        double trade = (E/I)*(e/in);
                                        if(GUI.productSwitchModeIndex[j]==0){
                                            complettitem=(products/trade);
                                        }else if(GUI.productSwitchModeIndex[j]==1){
                                            complettitem=((products)/trade)/30;
                                        }
                                        itemsComplett=itemsComplett+complettitem;
                                    }else{
                                        double trade = (I/E)*(in/e);
                                        if(GUI.productSwitchModeIndex[j]==0){
                                            complettitem=(products*trade);
                                        }else if(GUI.productSwitchModeIndex[j]==1){
                                            complettitem=(products*trade)/30;
                                        }
                                        itemsComplett=itemsComplett+complettitem;
                                    }
                                        productPerMinute.append(round(complettitem,3)).append("\n");
                                        productBuy.append(round(complettitem*30, 3)).append("\n");
                                }catch(Exception e){
                                }
                            }
                        }
                    }
                }
                GUI.productResultProductList.setText(productsList.toString());
                GUI.productResultProductsPerMinuteList.setText(productPerMinute.toString());
                GUI.productResultProductBuyList.setText(productBuy.toString());
                if(!GUI.productResultItemComboBox.getSelectedItem().toString().equalsIgnoreCase("Nichts")){
                    GUI.productResultProductResult.setText(GUI.productResultItemComboBox.getSelectedItem().toString());
                }else{
                    GUI.productResultProductResult.setText("---");
                }
                GUI.productResultProductPerMinuteResult.setText(String.valueOf(round(itemsComplett,3)));
                GUI.productResultProductBuyResult.setText(String.valueOf(round(itemsComplett*30,3)));
            }
        },100,100);
    }
    public double round(double value, int decimalPoints) {
        double d = Math.pow(10, decimalPoints);
        return Math.round(value * d) / d;
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }
}
