package Calculator;

import FileManager.Config;

import java.util.Timer;
import java.util.TimerTask;

public class GUIUpdater {
    public GUIUpdater(){
        Timer timer = new Timer();
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
                        int temp = Integer.parseInt(GUI.productPerMinuteList.get(i).getText());
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
                }
                for(int j = 0;j<10;j++){
                    if(!GUI.productResultItemComboBox.getSelectedItem().toString().equalsIgnoreCase("Nichts")){
                        if(!GUI.productComboBoxList.get(j).getSelectedItem().toString().equalsIgnoreCase("Nichts")){
                            if(!GUI.productPerMinuteList.get(j).getText().replace(" ","").equalsIgnoreCase("")){
                                try{
                                    productsList.append(GUI.productComboBoxList.get(j).getSelectedItem().toString()).append("\n");
                                    int productsPerMinute = Integer.valueOf(GUI.productPerMinuteList.get(j).getText());
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
                                        complettitem=(productsPerMinute/trade);
                                        System.out.println("debug2 "+complettitem+" "+trade);
                                        itemsComplett=itemsComplett+complettitem;
                                    }else{
                                        double trade = (I/E)*(in/e);
                                        complettitem=(trade*productsPerMinute);
                                        System.out.println("debug1 "+complettitem+" "+trade);
                                        itemsComplett=itemsComplett+complettitem;
                                    }
                                    productPerMinute.append(complettitem).append("\n");
                                    productBuy.append(complettitem*30).append("\n");
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
                GUI.productResultProductPerMinuteResult.setText(String.valueOf(itemsComplett));
                GUI.productResultProductBuyResult.setText(String.valueOf(itemsComplett*30));
            }
        },100,100);
    }
}
