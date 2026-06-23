package com.nracademy.metapostanalyzer.controller;

import com.nracademy.metapostanalyzer.model.Post;
import com.nracademy.metapostanalyzer.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {

    private final PostService postService;

    public AnalysisController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/report")
    public Map<String, Object> getReport() {
        List<Post> allPosts = postService.getSavedOrMockPosts();

        List<Post> top3Posts = postService.getTop3EngagedPosts(allPosts);
        Map<String, Integer> likesByDay = postService.getLikesByDayOfWeek(allPosts);

        Map<String, Object> report = new HashMap<>();
        report.put("1. Top 3 Engaged Posts", top3Posts);
        report.put("2. Total Likes by Day of Week", likesByDay);
        report.put("3. Brief Conclusion", "Ən yüksək engagement alan postlar həftə sonuna yaxın paylaşılanlardır. " +
                "İstifadəçilər şərh yazmaqdansa like etməyə daha çox meyllidirlər.");

        return report;
    }
}