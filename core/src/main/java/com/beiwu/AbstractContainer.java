package com.beiwu;

import com.beiwu.utils.Configuration;
import org.apache.commons.lang3.Validate;

/**
 * 执行容器的抽象类，持有该容器全局的配置 configuration
 * @author zhoubing
 * @date 2021-06-23 10:39
 */
public abstract class AbstractContainer {
    protected Configuration configuration;

    public AbstractContainer(Configuration configuration) {
        Validate.notNull(configuration, "Configuration can not be null.");

        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public abstract void start();

}
