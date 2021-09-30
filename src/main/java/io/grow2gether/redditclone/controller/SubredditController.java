package io.grow2gether.redditclone.controller;

import io.grow2gether.redditclone.dto.DataResponse;
import io.grow2gether.redditclone.dto.PageableResult;
import io.grow2gether.redditclone.dto.SubredditDto;
import io.grow2gether.redditclone.model.Subreddit;
import io.grow2gether.redditclone.service.ReportService;
import io.grow2gether.redditclone.service.SubredditService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subreddit")
public class SubredditController {
    private final SubredditService subredditService;
    private final ReportService reportService;

    @PostMapping
    public DataResponse<Void> createSubreddit(@RequestBody SubredditDto subredditDto) {
        return subredditService.save(subredditDto);
    }

    @GetMapping
    public PageableResult<SubredditDto> getAllSubreddit(@RequestParam(required = false, defaultValue = "1") int page,
                                                        @RequestParam(required = false, defaultValue = "10") int size) {
        return subredditService.getAllSubreddit(page, size);
    }

    @GetMapping("/{id}")
    public DataResponse<SubredditDto> getSubreddit(@PathVariable Long id) {
        return new DataResponse<>("Subreddit", HttpStatus.OK.value(), this.subredditService.getSubreddit(id));
    }

    @GetMapping("/report/{reportFormat}")
    public void getSubredditReport(HttpServletResponse response, @PathVariable String reportFormat) throws IOException, JRException {
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", String.format("attachment, filename=\"report.pdf\""));

        reportService.exportReport(reportFormat, response.getOutputStream());
    }

}
