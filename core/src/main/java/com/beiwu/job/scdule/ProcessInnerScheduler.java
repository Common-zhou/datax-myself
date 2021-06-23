package com.beiwu.job.scdule;

import com.beiwu.utils.Configuration;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhoubing
 * @date 2021-06-23 17:25
 */
public class ProcessInnerScheduler extends AbstractScheduler {
    private ExecutorService executorService;

    @Override
    public void startAllTaskGroup(List<Configuration> configurations) {

        Validate.notNull(configurations, "configurations must not null.");

        this.executorService = Executors.newFixedThreadPool(configurations.size());

        for (Configuration configuration : configurations) {

        }
    }
}
