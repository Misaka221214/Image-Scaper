import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter output path:");
        String path = in.nextLine() + "\\";
        System.out.println("Please enter url:");
        String sourceLink = in.nextLine();
        int count = 0;
        try {
            final Document document = Jsoup.connect(sourceLink).timeout(3000).get();
            for (Element s : document.select("img")) {
//                System.out.println(s.attr("abs:src"));
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
