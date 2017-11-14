package com.bmsmart.service.activiti.rule;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.LocalBusinessRuleTaskDelegateImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.impl.variable.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class SelectRuleDelegateService extends LocalBusinessRuleTaskDelegateImpl {

    @Autowired
    private RepositoryService repositoryService;

    private static final Logger log = LoggerFactory.getLogger(LocalBusinessRuleTaskDelegateImpl.class);
    @Override
    public void execute(ActivityExecution execution) throws Exception {

        testLogging();

        //execution.setVariable("AnnRuleDelegateService", "AnnRuleDelegateServiceValue");

        if (execution instanceof ExecutionEntity) {
            System.out.println("YES");


            VariableInstanceEntity variableInstanceEntity =
                    VariableInstanceEntity.create(
                            "selectInput",
                            new StringType(100),
                            "SELECT");

            variableInstanceEntity.setId("inAndOut");

            ((ExecutionEntity) execution).getQueryVariables().add(variableInstanceEntity);

        }



    }
}
