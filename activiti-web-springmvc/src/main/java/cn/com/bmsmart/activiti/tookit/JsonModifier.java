package cn.com.bmsmart.activiti.tookit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class JsonModifier {


    /**
     * @param parent      Json节点
     * @param fieldName   节点名称
     * @param oldValues   需要替换的节点的内容（如为null则无条件替换内容，有值则按需求替换）
     * @param newValue    替换后新内容
     * @param isRecursive 是否递归调用
     */
    public static void changeNodeValue(JsonNode parent, String fieldName, List<String> oldValues, String newValue, boolean isRecursive) {
        if (parent.has(fieldName)) {
            if (null == oldValues && null != newValue) {
                ((ObjectNode) parent).put(fieldName, newValue);
            } else {
                for (String s : oldValues) {
                    if (null != (parent.get(fieldName))) {
                        if ((parent.get(fieldName).asText().equals(s))) {
                            ((ObjectNode) parent).put(fieldName, newValue);
                            break;
                        }
                    }
                }
            }

            if (!isRecursive) {
                return;
            }
        }

        // 递归调用
        for (JsonNode child : parent) {
            changeNodeValue(child, fieldName, oldValues, newValue, isRecursive);
        }
    }
}
