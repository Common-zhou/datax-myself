package com.beiwu.taskgroup.runner;

import com.beiwu.spi.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

/**
 * @author zhoubing
 * @date 2021-06-23 18:10
 */
public class WriteRunner implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(WriteRunner.class);
    Writer writer;

    public WriteRunner(Writer reader) {
        this.writer = reader;
    }

    @Override
    public void run() {
        BlockingQueue<String> queue = writer.getBlockingQueue();

        while (true) {
            String line = null;
            try {
                line = queue.take();
            } catch (InterruptedException e) {
                LOG.error("got an interrupted exception {}", e.toString());
                e.printStackTrace();
            }
            if (line == null || "---end flag---".equals(line)) {
                break;
            }
            writer.write(line);
        }


    }
}
