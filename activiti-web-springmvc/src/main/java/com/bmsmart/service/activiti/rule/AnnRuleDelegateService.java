package com.bmsmart.service.activiti.rule;

import com.bmsmart.service.local.ItmsCalculateService;
import org.activiti.engine.impl.LocalBusinessRuleTaskDelegateImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.impl.variable.StringType;
import org.activiti.engine.impl.variable.ValueFields;
import org.activiti.engine.impl.variable.VariableType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Yanglu
 */
public class AnnRuleDelegateService extends LocalBusinessRuleTaskDelegateImpl {

    private static final Logger log = LoggerFactory.getLogger(LocalBusinessRuleTaskDelegateImpl.class);

    @Autowired
    private ItmsCalculateService itmsCalculateServiceAnn;

    @Override
    public void execute(ActivityExecution execution) throws Exception {

        testLogging();


        itmsCalculateServiceAnn.execute();

        execution.setVariable("AnnRuleDelegateService", "AnnRuleDelegateServiceValue");

        if (execution instanceof ExecutionEntity) {
            System.out.println("YES");


            VariableInstanceEntity variableInstanceEntity =
                    VariableInstanceEntity.create(
                            "intput",
            new StringType(100),
            "ANN");

            variableInstanceEntity.setId("inAndOut");

            ((ExecutionEntity) execution).getQueryVariables().add(variableInstanceEntity);

        } else {
            System.out.println("NO");
        }


    }
}
