package com.servrock.rest;

import com.servrock.domain.CommentEntity;
import com.servrock.domain.CommitEntity;
import com.servrock.dto.GitCommit;
import com.servrock.dto.comment.GitComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.servrock.service.GitRepoMonService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GitRepoMonController {

    @Autowired
    private GitRepoMonService gitRepoMonService;

    @RequestMapping("/test")
    public String showTestMsg() {
        return"GitHub Repo Monitor APP";
    }

    @GetMapping("/loadcommits/{owner}/{repo}")
    public List<CommitEntity> getCommitsNComments(@PathVariable String owner, @PathVariable String repo) {
        return saveNbuildAllCommitsResp(gitRepoMonService.getAllCommits(owner,repo));
    }

    @GetMapping("/list_commits")
    public List<CommitEntity> getAllCommits() {
        return gitRepoMonService.getCommits();
    }

    @GetMapping("/search_commits/{comment_text}")
    public List<CommitEntity> searchCommitsByText(@PathVariable String comment_text) {
        boolean matched = false;
        List<CommitEntity>  responseList = new ArrayList<>();
        List<CommitEntity>  commitList =  gitRepoMonService.getCommits();
        for(CommitEntity commit: commitList) {
            List<CommentEntity> commentsList = commit.getCommentEntList();
            List<CommentEntity> commentsMatchedList = new ArrayList<>();
            for(CommentEntity commment : commentsList) {
                if(commment.getBody().contains(comment_text)) {
                    matched = true;
                    commentsMatchedList.add(commment);
                }
            }
            if (matched) {
                CommitEntity commitMatch = new CommitEntity();
                commitMatch.setCommitSHA(commit.getCommitSHA());
                commitMatch.setCommentsURL(commit.getCommentsURL());
                commitMatch.setCommentEntList(commentsMatchedList);
                responseList.add(commitMatch);
                matched = false;
            }
        }
        return responseList;
    }

    private List<CommitEntity> saveNbuildAllCommitsResp(List<GitCommit> gitCommitsList) {
        List<CommitEntity> responseList = new ArrayList<>();
        for (GitCommit gitCommit: gitCommitsList) {
            CommitEntity response = new CommitEntity();
            response.setCommitSHA(gitCommit.getSha());
            response.setCommentsURL(gitCommit.getCommentsUrl());
            List<GitComment> GitCommentsList  =  gitRepoMonService.getCommentsByURL(gitCommit.getCommentsUrl());
            List<CommentEntity> commentsList = new ArrayList<>();
            for (GitComment gitComment: GitCommentsList) {
                CommentEntity commInfo = new CommentEntity();
                commInfo.setId(gitComment.getId());
                commInfo.setUser(gitComment.getUser().getLogin());
                commInfo.setBody(gitComment.getBody());
                commentsList.add(commInfo);
            }
            response.setCommentEntList(commentsList);
            responseList.add(response);
            gitRepoMonService.saveCommitInfo(response);
        }
        return responseList;
    }
}
