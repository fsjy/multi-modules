package com.bmsmart.service.activiti.rule;

import com.bmsmart.service.activiti.factory.entities.CurrentServiceEntity;
import com.bmsmart.service.activiti.factory.entities.ExecutedServicesEntities;
import com.bmsmart.service.activiti.factory.entities.RuleDelegateOutputParams;
import com.bmsmart.service.activiti.factory.impl.CalculateParamsCreatingFactoryImpl;
import com.bmsmart.service.activiti.rule.entity.ActivitiInputParams;
import com.bmsmart.service.local.ItmsCalculateService;
import com.bmsmart.service.local.entities.CalculateInputParams;
import com.bmsmart.service.local.entities.CalculateOutputParams;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.impl.LocalBusinessRuleTaskDelegateImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.impl.variable.CustomObjectType;
import org.codehaus.groovy.util.ListHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class AbstractRuleDelegateService extends LocalBusinessRuleTaskDelegateImpl {

    private static final Logger log = LoggerFactory.getLogger(AbstractRuleDelegateService.class);

    abstract public String getSimpleName();

    abstract public String getNativeName();


    @Override
    public void execute(ActivityExecution execution) throws Exception {
        testLogging();
        before(execution);
        defaultExecute(execution);
        after(execution);
    }


    /**
     * Sample Default处理
     *
     * @param execution
     * @throws Exception
     */
    public void defaultExecute(ActivityExecution execution) throws Exception {

        CalculateParamsCreatingFactoryImpl factory = new CalculateParamsCreatingFactoryImpl();

        CurrentServiceEntity currentServiceEntity = CurrentServiceEntity.create(getSimpleName(),
                convertParams(getRuleVariableInputIdExpressions()));

        // 传入RuleDelegate的参数绑定
        ExecutedServicesEntities executedServicesEntities = factory.createIn(execution, currentServiceEntity);


        // 向最终计算服务中 传入参数准备
        CalculateInputParams calculateInputParams = CalculateInputParams.create();
        calculateInputParams.setActivitiID(currentServiceEntity.getCurrentActivityID());
        calculateInputParams.setSimpleName(currentServiceEntity.getSimpleName());
        calculateInputParams.setIntputServiceIDandContentMapping(currentServiceEntity
                .getRuleDelegateInputParams()
                .getIntputServiceIDandContentMapping());

        // 暂时不使用所有的参数作为input 现阶段如果不填写input则使用全部
        // calculateInputParams.setAllServiceIDandConetentMapping(executedServicesEntities.getExecutedServiceContentLinkedHashMap());

        CalculateOutputParams outputParams = (CalculateOutputParams) getCalculateService().execute(calculateInputParams);

        RuleDelegateOutputParams ruleDelegateOutputParams = new RuleDelegateOutputParams();

        ruleDelegateOutputParams.setContent(outputParams.getContent());
        ruleDelegateOutputParams.setSize(outputParams.getSize());
        ruleDelegateOutputParams.setStatus(outputParams.getStatus());

        currentServiceEntity.setRuleDelegateOutputParams(ruleDelegateOutputParams);

        factory.createOut(currentServiceEntity, executedServicesEntities, execution);

    }

    private ActivitiInputParams convertParams(List<Expression> expressions) {

        ActivitiInputParams params = new ActivitiInputParams();
        List<String> list = new ArrayList<>();
        if (expressions != null) {
            expressions.forEach(expression -> list.add(expression.getExpressionText()));
            params.setInputs(list);
        }

        if (list.size() == 0) {
            params.setUsing(false);
        } else {
            params.setUsing(true);
        }

        return params;
    }


    abstract protected ItmsCalculateService getCalculateService();

    protected void before(ActivityExecution execution) {

    }

    protected void after(ActivityExecution execution) {



    }


    protected void testLogging() {
        log.warn("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.warn("【" + getClass().getSimpleName() + "】:");
        log.warn("   ---------------------------------------------------------------------------------------------");
        log.warn("  | " + getSimpleName());
        log.warn("  | " + getNativeName());
        log.warn("   ---------------------------------------------------------------------------------------------");
        log.warn("【Using input values】: ");
        log.warn("   ---------------------------------------------------------------------------------------------");
        getRuleVariableInputIdExpressions().forEach(new Consumer<Expression>() {
            @Override
            public void accept(Expression expression) {
                log.warn("  | " + expression.getExpressionText());
            }
        });
        log.warn("   ---------------------------------------------------------------------------------------------");
        log.warn("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();
    }

}
