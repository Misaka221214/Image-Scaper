import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        String path = "C:\\Users\\Administrator.SC-201912271441\\Desktop\\test\\";
        String sourceLink = "https://warthunder.com/en";
        int count = 0;
        try {
            final Document document = Jsoup.connect(sourceLink).timeout(3000).get();
            for (Element s : document.select("img")) {
//                System.out.println(s);
                System.out.println(s.attr("abs:src"));
                File out = new File(path + count + ".png");
                new Thread(new Downloader(s.attr("abs:src"), out)).start();
                count++;
            }

        } catch (Exception e) {
            System.out.println("Error!");
            e.printStackTrace();
        }
    }
}
