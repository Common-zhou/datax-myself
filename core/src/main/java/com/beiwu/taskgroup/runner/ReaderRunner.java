package com.beiwu.taskgroup.runner;

import com.beiwu.spi.Reader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhoubing
 * @date 2021-06-23 18:12
 */
public class ReaderRunner implements Runnable {
    private final static Logger LOG = LoggerFactory.getLogger(ReaderRunner.class);
    Reader reader;

    public ReaderRunner(Reader reader) {
        this.reader = reader;
    }

    @Override
    public void run() {
        reader.read();
        try {
            reader.getBlockingQueue().put("---end flag---");
        } catch (InterruptedException e) {
            LOG.error("got an interrupted exception {}", e.toString());
            e.printStackTrace();
        }
    }
}
