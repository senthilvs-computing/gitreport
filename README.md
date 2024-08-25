Github Report Tool for Engineering Manager
-------------------------------------------

Requirements from Panelist:
---------------------------
Scenario
As an engineering manager of a 10 person team, I would like a tool to visualize how pull requests are moving in a GitHub repository that our team works in. 
I am interested to understand:
* How many we open or close in a week
* Pull requests that are stuck in review for a long time
* Knowing if we are creating large complex pull requests with many changes, or if we are creating small pull requests with minimal changes

Prompt
In a coding language of your choice, implement a program that will create this tool.

Requirements
* Runnable at the terminal and output information to the console - it does not need a UI - but it should be clear and obvious what information it is providing to me
* Should allow me to provide the GitHub repository, so that i can run it for several of them
* Documentation on how I should provide credentials
* At least one of the scenarios mentioned above should be implemented
* Should directly call GitHub REST APIs
* Should be code of your own creation


This quick command line based tool was built using artifact https://github-api.kohsuke.org/index.html in Java. (which is nothing but a convenient artifact to fetch via Github REST API i.e. https://docs.github.com/en/rest/pulls/pulls?apiVersion=2022-11-28#list-pull-requests)

Given it was time challenged  , the tool was built as a simple util with no focus on low level or OOPS design and unit tests. 
Also we solved quick two  scenario in code  to fetch open / close PRS in a week () and left the placeholder in code for the other scenarios but called out the idea on which field we can infer 
FYI

Tool usage instructions :
------------------------


#option 1

mvn clean package

java -jar target/gitreport-1.0-SNAPSHOT.jar

or 

#option 2

Import the project in IDE of your choice 

Run the GithubRepoReport.main()


This will be as sample cli run capture as below

```
bash-3.2$ java -jar target/gitreport-1.0-SNAPSHOT.jar
Enter Github repo in the owner/repo format . For Eg enter FasterXML/jackson for the repo https://github.com/FasterXML/jackson:
senthilvs-computing/github-api
Enter your Github personal access token :
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
What insights you are looking for ?
1)Open PRs in last one week
2)Closed PRs in last one week
3)Stuck in review PRs for long time
4)Large complex PRs with many changes
5)small PRs with minimal changes
6)No Thanks
Enter the number of your choice .For eg. to quit , press 6 :
1
We got 1 OPEN PRs in the last 1 week
They are 
https://github.com/senthilvs-computing/github-api/pull/1 created on Sat Aug 24 22:21:11 EDT 2024 by user senthilvs-computing
-----------------------------------
What insights you are looking for ?
1)Open PRs in last one week
2)Closed PRs in last one week
3)Stuck in review PRs for long time
4)Large complex PRs with many changes
5)small PRs with minimal changes
6)No Thanks
Enter the number of your choice .For eg. to quit , press 6:
6
Thanks for using the git report tool !!! Have a great day !!!
bash-3.2$ 
```
