package com.beiwu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.beiwu.exception.CusException;
import com.beiwu.job.JobContainer;
import com.beiwu.utils.Configuration;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

/**
 * @author zhoubing
 * @date 2021-06-22 22:20
 */
public class Engine {

    private static final Logger LOG = LoggerFactory.getLogger(Engine.class);

    public static void main(String[] args) {
        System.out.println("com.beiwu.Engine begin...");

        try {
            entry(args);
        } catch (ParseException e) {
            throw new CusException("parse error" + e);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void entry(String[] args) throws ParseException, IOException {

        Options options = new Options();
        options.addOption("config", true, "config path");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        String path = cmd.getOptionValue("config");

        LOG.debug("got config path : {}", path);


        Configuration configuration = Configuration.from(new File(path));

        AbstractContainer container = new JobContainer(configuration);

        container.start();



    }

    private static Map readConfig(String path) throws IOException {

        List<String> strings = Files.readAllLines(new File(path).toPath());
        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            sb.append(string).append("\n");
        }

        Map jsonObject = JSON.parseObject(sb.toString());
        LOG.debug(((JSONObject) jsonObject).toJSONString());

        return jsonObject;
    }
}
