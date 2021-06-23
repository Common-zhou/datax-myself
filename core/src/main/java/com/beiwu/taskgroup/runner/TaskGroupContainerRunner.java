package com.beiwu.taskgroup.runner;

import com.beiwu.spi.Reader;

/**
 * @author zhoubing
 * @date 2021-06-23 17:29
 */
public class TaskGroupContainerRunner implements Runnable {

    Reader reader;

    public TaskGroupContainerRunner(Reader reader) {
        this.reader = reader;
    }

    @Override
    public void run() {
        reader.read();
    }
}
