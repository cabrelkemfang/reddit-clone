package io.grow2gether.redditclone.service;

import io.grow2gether.redditclone.model.Subreddit;
import io.grow2gether.redditclone.repository.SubredditRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService {
    private final SubredditRepository subredditRepository;

    public void exportReport(String reportFormat, OutputStream outputStream) throws FileNotFoundException, JRException {
        List<Subreddit> subredditList = this.subredditRepository.findAll();

        try {
            //load file and compile it
            File file = ResourceUtils.getFile("classpath:employees.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(subredditList);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "Java Techie");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            if (reportFormat.equalsIgnoreCase("pdf")) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException("An Error occur");
        }
    }

}
