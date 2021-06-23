package com.beiwu.writer;

import com.beiwu.spi.Writer;
import com.beiwu.utils.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhoubing
 * @date 2021-06-23 15:57
 */
public class FileWriter extends Writer {
    private final static Logger LOG = LoggerFactory.getLogger(FileWriter.class);

    public FileWriter(Configuration configuration) {
        super(configuration);
    }

    @Override
    public void write(String record) {
        LOG.info("got message {}", record);
    }
}
