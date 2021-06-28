package com.servrock.dto.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "url",
        "html_url",
        "id",
        "node_id",
        "user",
        "position",
        "line",
        "path",
        "commit_id",
        "created_at",
        "updated_at",
        "author_association",
        "body"
})

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitComment {

    @JsonProperty("url")
    private String url;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("user")
    private User user;
    @JsonProperty("position")
    private Object position;
    @JsonProperty("line")
    private Object line;
    @JsonProperty("path")
    private Object path;
    @JsonProperty("commit_id")
    private String commitId;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("author_association")
    private String authorAssociation;
    @JsonProperty("body")
    private String body;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
