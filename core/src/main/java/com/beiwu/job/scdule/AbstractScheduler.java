package com.beiwu.job.scdule;

import com.beiwu.utils.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author zhoubing
 * @date 2021-06-23 16:52
 */
public abstract class AbstractScheduler {
    private final static Logger LOG = LoggerFactory.getLogger(AbstractScheduler.class);

    public void schedule(List<Configuration> configurations){

    }

    public abstract void startAllTaskGroup(List<Configuration> configurations) ;
}
