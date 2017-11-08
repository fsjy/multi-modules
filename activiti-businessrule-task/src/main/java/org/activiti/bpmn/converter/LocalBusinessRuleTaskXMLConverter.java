/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.bpmn.converter;

import org.activiti.bpmn.model.LocalBusinessRuleTask;
import org.activiti.bpmn.converter.util.BpmnXMLUtil;
import org.activiti.bpmn.model.BaseElement;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.BusinessRuleTask;
import org.activiti.bpmn.model.ImplementationType;
import org.apache.commons.lang3.StringUtils;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/**
 * @author Tijs Rademakers
 */
public class LocalBusinessRuleTaskXMLConverter extends BaseBpmnXMLConverter {

    public Class<? extends BaseElement> getBpmnElementType() {
        return BusinessRuleTask.class;
    }

    @Override
    protected String getXMLElementName() {
        return ELEMENT_TASK_BUSINESSRULE;
    }

    @Override
    protected BaseElement convertXMLToElement(XMLStreamReader xtr, BpmnModel model) throws Exception {
        LocalBusinessRuleTask localBusinessRuleTask = new LocalBusinessRuleTask();
        BpmnXMLUtil.addXMLLocation(localBusinessRuleTask, xtr);

        // Add by Yanglu 2017.11.3 增加对implement的读取支持
        // Modified by Yanglu 2017.11.7 需要动态判断是Class模式还是DelegateExpression模式 参考 代码{link ServiceTaskParseHandler}
        // 增加对DelegateExpression Type的追加 如果是Class Type以及Rule Type维持原有逻辑不变
        if (StringUtils.isNotEmpty(xtr.getAttributeValue(ACTIVITI_EXTENSIONS_NAMESPACE, ATTRIBUTE_TASK_SERVICE_DELEGATEEXPRESSION))) {
            localBusinessRuleTask.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_DELEGATEEXPRESSION);
            localBusinessRuleTask.setImplementation(xtr.getAttributeValue(ACTIVITI_EXTENSIONS_NAMESPACE, ATTRIBUTE_TASK_SERVICE_DELEGATEEXPRESSION));
        }

        localBusinessRuleTask.setInputVariables(parseDelimitedList(xtr.getAttributeValue(ACTIVITI_EXTENSIONS_NAMESPACE, ATTRIBUTE_TASK_RULE_VARIABLES_INPUT)));
        localBusinessRuleTask.setRuleNames(parseDelimitedList(xtr.getAttributeValue(ACTIVITI_EXTENSIONS_NAMESPACE, ATTRIBUTE_TASK_RULE_RULES)));
        localBusinessRuleTask.setResultVariableName(xtr.getAttributeValue(ACTIVITI_EXTENSIONS_NAMESPACE, ATTRIBUTE_TASK_RULE_RESULT_VARIABLE));
        localBusinessRuleTask.setClassName(xtr.getAttributeValue(ACTIVITI_EXTENSIONS_NAMESPACE, ATTRIBUTE_TASK_RULE_CLASS));
        String exclude = xtr.getAttributeValue(ACTIVITI_EXTENSIONS_NAMESPACE, ATTRIBUTE_TASK_RULE_EXCLUDE);
        if (ATTRIBUTE_VALUE_TRUE.equalsIgnoreCase(exclude)) {
            localBusinessRuleTask.setExclude(true);
        }
        parseChildElements(getXMLElementName(), localBusinessRuleTask, model, xtr);
        return localBusinessRuleTask;
    }

    @Override
    protected void writeAdditionalAttributes(BaseElement element, BpmnModel model, XMLStreamWriter xtw) throws Exception {
        BusinessRuleTask businessRuleTask = (BusinessRuleTask) element;
        String inputVariables = convertToDelimitedString(businessRuleTask.getInputVariables());
        if (StringUtils.isNotEmpty(inputVariables)) {
            writeQualifiedAttribute(ATTRIBUTE_TASK_RULE_VARIABLES_INPUT, inputVariables, xtw);
        }
        String ruleNames = convertToDelimitedString(businessRuleTask.getRuleNames());
        if (StringUtils.isNotEmpty(ruleNames)) {
            writeQualifiedAttribute(ATTRIBUTE_TASK_RULE_RULES, ruleNames, xtw);
        }
        if (StringUtils.isNotEmpty(businessRuleTask.getResultVariableName())) {
            writeQualifiedAttribute(ATTRIBUTE_TASK_RULE_RESULT_VARIABLE, businessRuleTask.getResultVariableName(), xtw);
        }
        if (StringUtils.isNotEmpty(businessRuleTask.getClassName())) {
            writeQualifiedAttribute(ATTRIBUTE_TASK_RULE_CLASS, businessRuleTask.getClassName(), xtw);
        }
        if (businessRuleTask.isExclude()) {
            writeQualifiedAttribute(ATTRIBUTE_TASK_RULE_EXCLUDE, ATTRIBUTE_VALUE_TRUE, xtw);
        }
    }

    @Override
    protected void writeAdditionalChildElements(BaseElement element, BpmnModel model, XMLStreamWriter xtw) throws Exception {
    }
}
