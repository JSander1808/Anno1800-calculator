package FileManager;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class NormalConfig {

    private String PATH;

    public NormalConfig(String PATH){
        this.PATH=PATH;
    }

    public void init(){
        String[] path = PATH.split("/");

        StringBuilder data = new StringBuilder();
        for(int i = 0;i<path.length-1;i++){
            data.append(path[i]+"/");
        }
        File dic = new File(data.toString());
        dic.mkdirs();
        File file = new File(data+path[path.length-1]);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void set(String key, String value){

        File file = new File(PATH);
        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String> list = new ArrayList<String>();
            String temp = null;
            Boolean exist = false;
            while((temp = reader.readLine())!=null){
                String[] data = temp.split("--");
                if(data[0].equalsIgnoreCase(key)){
                    exist=true;
                    list.add(key+"--"+value+"\n");
                }else{
                    list.add(temp+"\n");
                }
            }
            if(!exist){
                list.add(key+"--"+value+"\n");
            }
            PrintWriter writer = new PrintWriter(file);
            for(String writedata : list){
                writer.write(writedata);
            }
            writer.flush();

        } catch (FileNotFoundException e) {
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
        } catch (IOException e) {
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

    public String get(String key){
        try {
            File file = new File(PATH);
            BufferedReader reader = null;
            reader = new BufferedReader(new FileReader(file));
            ArrayList<String> list = new ArrayList<String>();
            String temp = null;
            while((temp = reader.readLine())!=null){
                String[] data = temp.split("--");
                if(data[0].equalsIgnoreCase(key)){
                    return data[1];
                }else{
                    list.add(temp+"\n");
                }
            }
        } catch (FileNotFoundException e) {
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
            headline.setVisible(true);

            JTextArea content = new JTextArea(e.getMessage());
            content.setBounds(10, 40,280,100);
            content.setVisible(true);

            frame.add(headline);
            frame.add(content);
            frame.repaint();
        } catch (IOException e) {
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
            headline.setVisible(true);

            JTextArea content = new JTextArea(e.getMessage());
            content.setBounds(10, 40,280,100);
            content.setVisible(true);

            frame.add(headline);
            frame.add(content);
            frame.repaint();
        }
        return null;
    }

}
