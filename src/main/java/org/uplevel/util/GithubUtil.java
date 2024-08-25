package org.uplevel.util;

import org.kohsuke.github.*;

import java.util.ArrayList;
import java.util.List;

public class GithubUtil {

    public static List<GHPullRequest> getPullRequests(String githubRepo, String oAuthToken, String state){
        List<GHPullRequest> pullRequests = new ArrayList<>();
        try {
            GitHub github = new GitHubBuilder().withOAuthToken(oAuthToken).build();
            GHRepository ghRepository = github.getRepository(githubRepo);
            pullRequests = ghRepository.getPullRequests(GHIssueState.valueOf(state)); //this does https://docs.github.com/en/rest/pulls/pulls?apiVersion=2022-11-28#list-pull-requests
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }
        return pullRequests;
    }
}
