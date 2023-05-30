package ma.digency.gov.amc.utils;

import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class LoadResourceFile {

    @Autowired
    ResourceLoader resourceLoader;

    public String loadFile(String fileName) {
        Resource resource = resourceLoader.getResource("classpath:template/" + fileName);
        try {
            InputStream strm = resource.getInputStream();
            return new String(strm.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_TECHNICAL_ERROR);
        }
    }

    public String loadFile(String path,String fileName) {
        Resource resource = resourceLoader.getResource("classpath:"+path+ fileName);
        try {
            InputStream strm = resource.getInputStream();
            return new String(strm.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_TECHNICAL_ERROR);
        }
    }
}
