package com.beiwu.reader;

import com.beiwu.spi.Reader;
import com.beiwu.utils.Configuration;
import org.apache.commons.lang3.Validate;

import java.io.*;

/**
 * @author zhoubing
 * @date 2021-06-23 15:56
 */
public class CusFileReader extends Reader {


    public CusFileReader(Configuration configuration) {
        super(configuration);
    }

    @Override
    public void read() {
        String path = this.readerConfiguration.getString("parameter.path");

        Validate.isTrue(new File(path).exists(), "path is not exists.");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(path)));

            String line = null;
            while ((line = reader.readLine()) != null) {
                try {
                    getBlockingQueue().put(line);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
