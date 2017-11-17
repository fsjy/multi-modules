package com.bmsmart.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CONST {

    public enum RULE_TASKS {
        WST_FMM("BusinessRule_BMS_WST_FMM", "分词任务——正向字典匹配"),
        WST_CRF("BusinessRule_BMS_WST_CRF", "分词任务——条件随机场CRF"),

        TCC_MMSEG("BusinessRule_BMS_TCC_MMSEG", "文本分类任务——字典匹配"),
        TCC_CRF("BusinessRule_BMS_TCC_CRF", "文本分类任务——条件随机场CRF"),
        TCC_NAVIEB("BusinessRule_BMS_TCC_NAVIEB", "文本分类任务——朴素贝叶斯"),

        PTR_CRF("BusinessRule_BMS_PTR_CRF", "属性识别任务——条件随机场"),

        NER_MMSEG("BusinessRule_BMS_NER_MMSEG", "命名实体识别任务——字典匹配"),
        NER_CRF("BusinessRule_BMS_NER_CRF", "命名实体识别任务——条件随机场");

        private String sName;
        private String nName;

        RULE_TASKS(String simpleName, String nativeName) {
            this.sName = simpleName;
            this.nName = nativeName;
        }

        public String simpleName() {
            return this.sName;
        }

        public String nativeName() {
            return this.nName;
        }

    }


    public static final Map<String, String> businessRuleNameMapping = new HashMap<>();

    /* 文章的title */
    public static final String TITLE = "title";

    /* 文章的content内容 */
    public static final String ZZCONTENT = "content";

    /* 文章的title 标记为ActivitiID */
    public static final String ACT_TITLE = "title";

    /* 文章的content内容 标记为ActivitiID*/
    public static final String ACT_CONTENT = "content";


    /* xml定义 */
    public static final String PROPERTY_PROCESS_ID = "process_id";
    public static final String PROPERTY_RESOURCE_ID = "resourceId";
    public static final String PROPERTY_ID = "id";

    /* 不用修改 只做保存 */
    public static final String _JSON_PROCESS_ID = "iasdjf345L34a_sE34Qggdsf3_";

    /* 复原json中新定义的serviceID */
    public static final String BUSINESSRULE_BASE = "BusinessRule";

    public static final List<String> businessRuleReplaceValues = new ArrayList<>();

    static {
        businessRuleReplaceValues.add(RULE_TASKS.WST_FMM.simpleName());
        businessRuleReplaceValues.add(RULE_TASKS.WST_CRF.simpleName());
        businessRuleReplaceValues.add(RULE_TASKS.TCC_MMSEG.simpleName());
        businessRuleReplaceValues.add(RULE_TASKS.TCC_CRF.simpleName());
        businessRuleReplaceValues.add(RULE_TASKS.TCC_NAVIEB.simpleName());
        businessRuleReplaceValues.add(RULE_TASKS.PTR_CRF.simpleName());
        businessRuleReplaceValues.add(RULE_TASKS.NER_MMSEG.simpleName());
        businessRuleReplaceValues.add(RULE_TASKS.NER_CRF.simpleName());
    }

    /**
     ******************************************************************************************************************
     * ruleTaskService服务进行params input output设定操作使用参数
     ******************************************************************************************************************
     */
    public static final String OK = "OK";
    public static final String NG = "NG";

    public static final String APPEND_STATUS = "_status";
    public static final String APPEND_SIZE = "_size";

}