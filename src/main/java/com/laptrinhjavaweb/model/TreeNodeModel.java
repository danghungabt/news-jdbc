package com.laptrinhjavaweb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeNodeModel<T> {
    private T data;
    private List<TreeNodeModel<T>> children;

    public TreeNodeModel(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNodeModel<T> child) {
        this.children.add(child);
    }

    @JsonProperty("data")
    public T getData() {
        return data;
    }

    @JsonProperty("children")
    public List<TreeNodeModel<T>> getChildren() {
        return children;
    }
}
