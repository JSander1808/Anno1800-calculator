package FileManager;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Config {

    private String PATH;

    public Config(String PATH){
        this.PATH=PATH;
    }

    public ProductDetail get(String product){
        try {
            File file = new File(PATH);
            BufferedReader reader = null;
            reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while((temp = reader.readLine())!=null){
                String[] data = temp.split("--");
                if(data[0].equalsIgnoreCase(product)){
                    return new ProductDetail(Double.valueOf(data[1]),Double.valueOf(data[2]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
