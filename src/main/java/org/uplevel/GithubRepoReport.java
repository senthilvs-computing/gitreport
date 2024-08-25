package org.uplevel;

import org.kohsuke.github.GHPullRequest;
import org.uplevel.util.GithubUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GithubRepoReport {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Github repo in the owner/repo format . For Eg enter FasterXML/jackson for the repo https://github.com/FasterXML/jackson:");
        String githubRepo = scanner.nextLine();

        System.out.println("Enter your Github personal access token :");
        String oAuthToken = scanner.nextLine();
        boolean continueAsking = true;
        do {
            System.out.println("What more insights are you looking for ?");
            System.out.println("1)Open PRs in last one week");
            System.out.println("2)Closed PRs in last one week");
            System.out.println("3)Stuck in review PRs for long time");
            System.out.println("4)Large complex PRs with many changes");
            System.out.println("5)small PRs with minimal changes");
            System.out.println("6)No Thanks");
            System.out.println("Enter the number of your choice .For eg. to quit , press 6 :");
            int userOption = scanner.nextInt();
            switch (userOption) {
                case 1 -> getPullRequests(githubRepo, oAuthToken, "OPEN");
                case 2 -> getPullRequests(githubRepo, oAuthToken, "CLOSED");
                case 3 -> {
                    //TODO
                    //fetch open PRs and filter against the configurable age that means long time for created_at field
                    System.out.println("Not implemented due to time challenge of 1 hour ");
                    System.out.println("logic will be - fetch open PRs and filter against the configurable age that means long time for created_at field ");
                    System.out.println("-----------------------------------");
                }
                case 4 -> {
                    //TODO
                    //fetch PRs and filter pr's commits or changedfiles count exceeds a configurable threshold
                    System.out.println("Not implemented due to time challenge of 1 hour ");
                    System.out.println("logic will be - fetch PRs and filter pr's commits or changedfiles count exceeds a configurable threshold");
                    System.out.println("-----------------------------------");
                }
                case 5 -> {
                    //TODO
                    //fetch PRs and filter pr's commits count (more ) and  changedfiles count (less)
                    System.out.println("Not implemented due to time challenge of 1 hour ");
                    System.out.println("logic will be - fetch PRs and filter pr's commits count (more ) and  changedfiles count (less)");
                    System.out.println("-----------------------------------");
                }
                case 6 -> {
                    System.out.println("Thanks for using the git report tool !!! Have a great day !!!");
                    System.out.println("-----------------------------------");
                    continueAsking = false;
                }
                default -> {
                    System.out.println("Sorry invalid input.");
                    System.out.println("-----------------------------------");
                }
            }
        }
        while(continueAsking);
    }

    private static void getPullRequests(String githubRepo, String oAuthToken,String state) {
        List<GHPullRequest> pullRequests = GithubUtil.getPullRequests(githubRepo, oAuthToken, state);
        System.out.println("We got "+pullRequests.size()+" "+state+" PRs in the last 1 week");
        if(pullRequests.size() > 0){
            System.out.println("They are ");
            try {
                LocalDate today = LocalDate.now();
                LocalDate localStartDate = today.minusDays(7);
                Date startDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                pullRequests.stream().filter(ghPullRequest -> {
                    try {
                        Date datefield = "CLOSED".equalsIgnoreCase(state) ? ghPullRequest.getClosedAt() : ghPullRequest.getCreatedAt();
                        return datefield.after(startDate);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).forEach(ghPullRequest -> {
                    try {
                        String operation = "CLOSED".equalsIgnoreCase(state) ? "closed" : "created";
                        Date datefield = "CLOSED".equalsIgnoreCase(state) ? ghPullRequest.getClosedAt() : ghPullRequest.getCreatedAt();
                        System.out.println(ghPullRequest.getHtmlUrl() + " "+operation+" on "+datefield+";  from user "
                                + ghPullRequest.getUser().getLogin());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            catch(Exception exception){
                System.out.println(exception.getMessage());
            }
        }
        System.out.println("-----------------------------------");
    }


}