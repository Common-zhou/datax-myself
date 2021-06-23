package com.beiwu.spi;

import com.beiwu.utils.Configuration;

import java.util.concurrent.BlockingQueue;

/**
 * @author zhoubing
 * @date 2021-06-23 10:21
 */
public abstract class Writer {
    private Configuration writerConfiguration;
    private BlockingQueue<String> blockingQueue;

    public Writer(Configuration configuration) {
        this.writerConfiguration = configuration;
    }

    public BlockingQueue<String> getBlockingQueue() {
        return blockingQueue;
    }

    public void setBlockingQueue(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public abstract void write(String record);
}
