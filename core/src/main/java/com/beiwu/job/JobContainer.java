package com.beiwu.job;

import com.beiwu.AbstractContainer;
import com.beiwu.reader.CusFileReader;
import com.beiwu.spi.Reader;
import com.beiwu.spi.Writer;
import com.beiwu.taskgroup.runner.ReaderRunner;
import com.beiwu.taskgroup.runner.WriteRunner;
import com.beiwu.utils.Configuration;
import com.beiwu.writer.FileWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author zhoubing
 * @date 2021-06-23 10:49
 */
public class JobContainer extends AbstractContainer {

    private Reader reader;
    private Writer writer;

    private String readerPluginName;
    private String writerPluginName;

    private long startTimeStamp;

    private long endTimeStamp;

    private long startTransferTimeStamp;

    private long endTransferTimeStamp;

    private int needChannelNumber;

    private static final Logger LOG = LoggerFactory.getLogger(JobContainer.class);

    public JobContainer(Configuration configuration) {
        super(configuration);

        this.startTimeStamp = System.currentTimeMillis();
    }

    @Override
    public void start() {
        // 首先干什么？
        // 1.配置文件
        // init 方法 初始化reader和writer
        LOG.info("begin start method");

        this.init();
        this.schedule();

    }

    public void init() {
        // 初始化方法 主要用于读取配置文件里的 pluginName

        String readerName = configuration.getString("job.content.reader.name");
        String writerName = configuration.getString("job.content.writer.name");

        this.readerPluginName = readerName;
        this.writerPluginName = writerName;

        System.out.println(readerName);
        System.out.println(writerName);

        // 之后新建Reader Writer
        this.reader = new CusFileReader(configuration.getConfiguration("job.content.reader"));

        LinkedBlockingDeque<String> queue = new LinkedBlockingDeque<>();
        this.reader.setBlockingQueue(queue);

        this.writer = new FileWriter(configuration.getConfiguration("job.content.writer"));
        this.writer.setBlockingQueue(queue);

    }

    public void schedule() {
        ExecutorService service = Executors.newFixedThreadPool(2);

        ReaderRunner readerRunner = new ReaderRunner(this.reader);
        service.execute(readerRunner);

        WriteRunner writeRunner = new WriteRunner(this.writer);
        service.execute(writeRunner);

        service.shutdown();
    }
}
