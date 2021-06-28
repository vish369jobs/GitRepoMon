package com.servrock.service;

import com.servrock.domain.CommentEntity;
import com.servrock.domain.CommitEntity;
import com.servrock.domain.GitCommitsRepo;
import com.servrock.dto.GitCommit;
import com.servrock.dto.comment.GitComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class GitRepoMonService {
    private static final String BASE_URL = "https://api.github.com/repos/";
    private static final String COMMITS_PATH = "/commits";

    @Autowired
    private GitCommitsRepo commitsRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    public List<GitCommit> getAllCommits(String owner, String repo) {
        String url = BASE_URL + owner + "/" + repo + COMMITS_PATH;
        GitCommit GitCommitObj[]  = restTemplate.getForObject(url, GitCommit[].class);
        return Arrays.asList(GitCommitObj);
    }

    public List<GitComment> getCommentsByURL(String commentsURL) {
        GitComment GitCommenObj[]  = restTemplate.getForObject(commentsURL, GitComment[].class);
        return Arrays.asList(GitCommenObj);
    }

    public void saveCommitInfo(CommitEntity gitCommit) {
        commitsRepo.save(gitCommit);
    }

    public List<CommitEntity> getCommits() {
        return commitsRepo.findAll();
    }

}
