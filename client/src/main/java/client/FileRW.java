package client;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileRW {
    private static PrintWriter out;

    public static void start(String login){
        try{
            out = new PrintWriter(new FileOutputStream(getHistory(login), true),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getHistory(String login){
        return "history/history_"+login+".txt";
    }

    public static void writeLine(String message){
        out.println(message);
    }

    public static String getLast100Lines(String login) throws IOException {
        if(!Files.exists(Paths.get(getHistory(login)))){
            return "";
        }

        StringBuilder sb = new StringBuilder();

        List<String> strings = Files.readAllLines(Paths.get(getHistory(login)));
        int first = 0;
        if(strings.size()>100){
            first =  strings.size()-100;
        }
        for (int i = first ; i < strings.size(); i++) {
            sb.append(strings.get(i)).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public static void stop(){
        out.close();
    }
}
