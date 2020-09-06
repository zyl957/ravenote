package org.zhaoyangli.ravenote.service;

import org.springframework.stereotype.Service;
import org.zhaoyangli.ravenote.mapper.ReportMapper;
import org.zhaoyangli.ravenote.model.Report;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReportService {

    @Resource
    private ReportMapper reportMapper;

    public void insertReport(Report report) {
        reportMapper.insertReport(report);
    }

    public List<Report> getReports() {

        return reportMapper.getReports();

    }

    public void deleteReport(int reportId) {
        reportMapper.deleteReport(reportId);
    }
}
