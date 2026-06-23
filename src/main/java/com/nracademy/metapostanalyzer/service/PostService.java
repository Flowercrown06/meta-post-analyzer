package com.nracademy.metapostanalyzer.service;

import com.nracademy.metapostanalyzer.model.Post;
import com.nracademy.metapostanalyzer.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;


    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getSavedOrMockPosts() {
        List<Post> dbPosts = postRepository.findAll();

         if (dbPosts.isEmpty()) {
            List<Post> mockPosts = new ArrayList<>();
            LocalDate today = LocalDate.now();

            for (int i = 1; i <= 20; i++) {
                mockPosts.add(new Post(
                        "id_" + i,
                        "Bu bizim test məqsədli paylaşılan " + i + " nömrəli postumuzdur. #meta",
                        today.minusDays(i % 7),
                        15 + (i * 12) % 150,
                        5 + (i * 4) % 40
                ));
            }

            return postRepository.saveAll(mockPosts);
        }

        return dbPosts;
    }


    public List<Post> getTop3EngagedPosts(List<Post> posts) {
        return posts.stream()
                .sorted(Comparator.comparingInt(Post::getEngagement).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }


    public Map<String, Integer> getLikesByDayOfWeek(List<Post> posts) {
        return posts.stream()
                .collect(Collectors.groupingBy(
                        post -> post.getCreatedTime().getDayOfWeek().toString(),
                        Collectors.summingInt(Post::getLikesCount)
                ));
    }
}