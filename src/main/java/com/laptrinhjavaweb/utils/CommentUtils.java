package com.laptrinhjavaweb.utils;

import com.laptrinhjavaweb.model.TreeNodeModel;
import com.laptrinhjavaweb.model.response.CommentsResponseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentUtils {
    public static List<TreeNodeModel<CommentsResponseModel>> buildTrees(List<CommentsResponseModel> comments) {
        Map<Long, TreeNodeModel<CommentsResponseModel>> map = new HashMap<>();
        List<TreeNodeModel<CommentsResponseModel>> roots = new ArrayList<>();

        for (CommentsResponseModel comment : comments) {
            TreeNodeModel<CommentsResponseModel> node = new TreeNodeModel<>(comment);
            map.put(comment.getId(), node);

            if (comment.getParentId() == null) {
                roots.add(node);
            } else {
                TreeNodeModel<CommentsResponseModel> parentNode = map.get(comment.getParentId());
                if (parentNode != null) {
                    parentNode.addChild(node);
                } else {
                    // Handle the case where parent comment is missing or not found
                }
            }
        }

        return roots;
    }
}
