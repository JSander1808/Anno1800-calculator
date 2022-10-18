package Calculator;

import java.util.Arrays;
import java.util.Scanner;

public class DataManager {

    public static String saveData(){
        StringBuilder importProductData = new StringBuilder();
        if(GUI.importItems.length>0){
            importProductData.append(GUI.productComboBoxList.get(0).getSelectedItem().toString()).append("!").append(GUI.productPerMinuteList.get(0).getText()).append("!").append(GUI.productBuyList.get(0).getText()).append("!").append(GUI.productSwitchModeIndex[0]);
            for(int i = 1;i<10;i++){
                if(GUI.importItems[i]==1){
                    importProductData.append("/");
                    importProductData.append(GUI.productComboBoxList.get(i).getSelectedItem().toString()).append("!").append(GUI.productPerMinuteList.get(i).getText()).append("!").append(GUI.productBuyList.get(i).getText()).append("!").append(GUI.productSwitchModeIndex[i]);
                }
            }
        }
        String exportProductData = GUI.productResultItemComboBox.getSelectedItem().toString()+"!"+GUI.productResultItemLevel.getSelectedItem().toString();
        String result = importProductData+"--"+exportProductData;
        return result;
    }

    public static void loadData(String data){
        for(int i = 0;i<10;i++){
            GUI.importItems[i]=0;
            GUI.productComboBoxList.get(i).setSelectedItem("Nichts");
            GUI.productPerMinuteList.get(i).setText("");
            GUI.productBuyList.get(i).setText("");
            GUI.productPerMinuteList.get(i).enable();
            GUI.productBuyList.get(i).disable();
            GUI.productSwitchModeIndex[i]=0;
        }
        String[] mainData = data.split("--");
        String[] importData = mainData[0].split("/");
        for(int i = 0;i< importData.length;i++){
            String[] importProduct = importData[i].split("!");
            GUI.productComboBoxList.get(i).setSelectedItem(importProduct[0]);
            GUI.productPerMinuteList.get(i).setText(importProduct[1]);
            GUI.productBuyList.get(i).setText(importProduct[2]);
            GUI.productSwitchModeIndex[i]=Integer.valueOf(importProduct[3]);
            GUI.importItems[i]=1;
        }
        String[] exportProduct = mainData[1].split("!");
        GUI.productResultItemComboBox.setSelectedItem(exportProduct[0]);
        GUI.productResultItemLevel.setSelectedItem(exportProduct[1]);
        GUI.frame.repaint();
    }
}
