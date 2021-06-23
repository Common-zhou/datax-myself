package com.beiwu.spi;

import com.beiwu.utils.Configuration;

import java.util.concurrent.BlockingQueue;

/**
 * @author zhoubing
 * @date 2021-06-23 10:18
 */
public abstract class Reader {
    protected Configuration readerConfiguration;

    public Reader(Configuration configuration) {
        this.readerConfiguration = configuration;
    }

    private BlockingQueue<String> blockingQueue;

    public BlockingQueue<String> getBlockingQueue() {
        return blockingQueue;
    }

    public void setBlockingQueue(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public abstract void read();
}
