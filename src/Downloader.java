import java.io.*;
import java.net.*;

public class Downloader implements Runnable {
    String link;
    File outPath;

    public Downloader(String link, File outPath) {
        this.link = link;
        this.outPath = outPath;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(link);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            BufferedInputStream bin = new BufferedInputStream(http.getInputStream());
            FileOutputStream fos = new FileOutputStream(this.outPath);
            BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
            byte[] buffer = new byte[1024];
            int read = 0;
            while ((read = bin.read(buffer, 0, 1024)) >= 0) {
                bout.write(buffer, 0, read);
                System.out.println("Downloading in progress...");
            }
            bout.close();
            bin.close();
            System.out.println("Download complete!");
        } catch (IOException e) {
            System.out.println("Error Caught!");
            e.printStackTrace();
        }
    }
}
