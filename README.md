# GitRepoMon
Git Hub Repositor Monitor Application


The program can be run as java application with Main class - GitHub_Repo_Monitor/src/main/java/com/servrock/GitRepoMonApplication.java

Once the springboot application starts up , one can make the following REST API calls using Postman tool or browser

1) To load the commit info in to the in-memory H2 DB, specify the owner (octocat) and the repo name (hello-world) as path variables

                  GET http://localhost:8080/loadcommits/octocat/hello-world
Sample Response
                  [
                      {
                          "commitSHA": "7fd1a60b01f91b314f59955a4e4d4e80d8edf11d",
                          "commentsURL": "https://api.github.com/repos/octocat/Hello-World/commits/7fd1a60b01f91b314f59955a4e4d4e80d8edf11d/comments",
                          "commentEntList": [
                              {
                                  "id": 1467023,
                                  "user": "rinakhan",
                                  "body": "i do loving my fame  more then anything in this world \n"
                              },
                              {
                                  "id": 2982942,
                                  "user": "outao",
                                  "body": "lets start a new journey!!!\n"
                              },.....

2) To list the commit info loaded in the DB

                GET http://localhost:8080/list_commits
Sample response

[
    {
        "commitSHA": "7fd1a60b01f91b314f59955a4e4d4e80d8edf11d",
        "commentsURL": "https://api.github.com/repos/octocat/Hello-World/commits/7fd1a60b01f91b314f59955a4e4d4e80d8edf11d/comments",
        "commentEntList": [
            {
                "id": 1467023,
                "user": "rinakhan",
                "body": "i do loving my fame  more then anything in this world \n"
            },
            {
                "id": 2982942,
                "user": "outao",
                "body": "lets start a new journey!!!\n"
            },...


 3) If we want to search for a text "here we go !" in the comments, use the search text as part of the path variable, as shown below

[
    {
        "commitSHA": "7fd1a60b01f91b314f59955a4e4d4e80d8edf11d",
        "commentsURL": "https://api.github.com/repos/octocat/Hello-World/commits/7fd1a60b01f91b314f59955a4e4d4e80d8edf11d/comments",
        "commentEntList": [
            {
                "id": 2982956,
                "user": "outao",
                "body": "here we go !\n"
            },
            {
                "id": 2983866,
                "user": "nkxiaochuan",
                "body": "Ok\n\nOn Wed, Apr 10, 2013 at 7:44 PM, outao notifications@github.com wrote:\n\n> here we go !\n> \n> —\n> Reply to this email directly or view it on GitHubhttps://github.com/octocat/Hello-World/commit/7fd1a60b01f91b314f59955a4e4d4e80d8edf11d#commitcomment-2982956\n> .\n"
            }
        ]
    }
]
