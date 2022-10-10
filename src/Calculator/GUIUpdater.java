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
                for(int i = 0;i<11;i++){
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
                        GUI.productPerMinuteNotANumberList.get(i).setVisible(true);
                        if(GUI.productPerMinuteList.get(i).getText().replace(" ","").equalsIgnoreCase("")||GUI.productPerMinuteList.get(i).getText()==null){
                            GUI.productPerMinuteNotANumberList.get(i).setVisible(false);
                        }
                    }
                }
            }
        },250,1000);
    }
}
