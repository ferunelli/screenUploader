package com.screenUploader;

public class Main {
    public static void main(String[] args) {
        com.screenUploader.ThreadMain th = new com.screenUploader.ThreadMain();
        try{
            th.t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
