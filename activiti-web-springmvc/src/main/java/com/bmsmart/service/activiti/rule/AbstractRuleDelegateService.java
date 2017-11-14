package com.bmsmart.service.activiti.rule;

import com.bmsmart.service.local.ItmsCalculateService;
import org.activiti.engine.impl.LocalBusinessRuleTaskDelegateImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.impl.variable.StringType;

public class AbstractRuleDelegateService extends LocalBusinessRuleTaskDelegateImpl {



    @Override
    public void execute(ActivityExecution execution) throws Exception {
        getCalculateService().execute();
    }


    public void defaultExecute(ActivityExecution execution) throws Exception {

        before(execution);
        execute(execution);
        after(execution);

    }



    protected ItmsCalculateService getCalculateService() {

        return null;
    }

    protected void before(ActivityExecution execution) {

    }

    protected void after(ActivityExecution execution) {
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
