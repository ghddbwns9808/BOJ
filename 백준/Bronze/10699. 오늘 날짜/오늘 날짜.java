import java.io.*;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) throws IOException{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(System.currentTimeMillis()));
    }
}