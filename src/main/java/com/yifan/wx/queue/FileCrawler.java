package com.yifan.wx.queue;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author: wuyifan
 * @date: 2018年06月04日 16:52
 */
public class FileCrawler implements Runnable{

    private final BlockingQueue<File> fileQueue;

    private final FileFilter fileFilter;

    private final File root;

    public FileCrawler(BlockingQueue<File> fileQueue, FileFilter fileFilter, File root) {
        this.fileQueue = fileQueue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    @Override
    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void crawl(File root) throws InterruptedException {
        File[] entries = root.listFiles(fileFilter);

        if (entries != null) {
            for(File entry : entries) {
                if (entry.isDirectory()) {
                    crawl(entry);
                } else if (!fileQueue.contains(entry)) {
                    fileQueue.put(entry);
                }
            }
        }
    }
}
