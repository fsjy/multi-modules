package cn.com.bmsmart.activiti.service.activiti.factory.impl;

import cn.com.bmsmart.activiti.service.activiti.factory.entities.*;
import cn.com.bmsmart.activiti.constant.CONST;
import cn.com.bmsmart.activiti.service.activiti.factory.ParamsCreatingFactory;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.impl.variable.ServiceType;

import java.util.LinkedHashMap;

public class CalculateParamsCreatingFactoryImpl implements ParamsCreatingFactory {


    public static final String EXCUTED_SERVICES_ENTITY = "_BMS_EXECUTION_SERVICE_ENTITY";

    /**
     * 返回所有instance中涉及到的service
     *
     * @param execution
     * @return 返回选中所有流程图中service的List
     */
    @Override
    public ExecutedServicesEntities createServiceEntityRepository(ActivityExecution execution,
                                                                  CurrentServiceEntity currentServiceEntity) {

        Object object = execution.getVariable(EXCUTED_SERVICES_ENTITY);
        ExecutedServicesEntities executedServicesEntities = null;

        // 第一次进入service
        if (null == object) {

            Object objectTitle = execution.getVariable(CONST.ACT_TITLE);
            Object objectContent = execution.getVariable(CONST.ACT_CONTENT);

            if (objectTitle == null || (!(objectTitle instanceof String))) {
                throw new IllegalArgumentException("can not get title from execution, variable title is not String type");
            }

            if (objectContent == null || (!(objectContent instanceof String))) {
                throw new IllegalArgumentException("can not get content from execution, variable title is not String type");
            }

            executedServicesEntities = ExecutedServicesEntities.create();

            executedServicesEntities.addExecutedServiceContent(ServiceReturnContent.createTitle(objectTitle.toString()));

            executedServicesEntities.addExecutedServiceContent(ServiceReturnContent.createContent(objectContent.toString()));


        } else {
            // 不是第一个处理service
            if (object instanceof ExecutedServicesEntities) {

                executedServicesEntities = (ExecutedServicesEntities) object;

            } else {
                throw new IllegalArgumentException("can not use '_BMS_EXECUTION_SERVICE_LIST' to get service list from ActivityExecution");
            }
        }

        return executedServicesEntities;

    }

    @Override
    public ExecutedServicesEntities createIn(ActivityExecution execution, CurrentServiceEntity currentServiceEntity) {

        currentServiceEntity.setCurrentActivityID(execution.getCurrentActivityId());

        // 获取当前处理过的所有services
        ExecutedServicesEntities executedServicesEntities = createServiceEntityRepository(execution, currentServiceEntity);

        // 加入当前的service ID 处理加入
        //appendServiceId(execution, executedServicesEntities);

        // 绑定需要的数据到当前输入params
        bindingNecessaryInputParams(executedServicesEntities, currentServiceEntity);

        return executedServicesEntities;
    }

    @Override
    public void bindingNecessaryInputParams(ExecutedServicesEntities executedServicesEntities,
                                            CurrentServiceEntity currentServiceEntity) {

        LinkedHashMap<String, ServiceReturnContent> returnContentLinkedHashMap =
                executedServicesEntities.getExecutedServiceContentLinkedHashMap();

        RuleDelegateInputParams inputParams = new RuleDelegateInputParams();

        // 如果当前服务需要指定参数
        if (currentServiceEntity.getActivityInputVariables().isUsing()) {


            currentServiceEntity.getActivityInputVariables().getInputs().forEach(s -> {

                if (null != returnContentLinkedHashMap.get(s)) {
                    inputParams.addParams(s, returnContentLinkedHashMap.get(s).getContent());
                } else {
                    throw new IllegalArgumentException("can not fetch " + s + "from ServiceMapping");
                }
            });

        } else {

            // 不需要指定参数则输入为之前所有的数据
            returnContentLinkedHashMap.forEach((k, v) -> {

                inputParams.addParams(k, v.getContent());

            });

        }

        currentServiceEntity.setRuleDelegateInputParams(inputParams);

    }

    @Override
    public void bindingOutputContent(CurrentServiceEntity currentServiceEntity,
                                     ActivityExecution execution) {

        ExposeServiceEntity exposeServiceEntity = ExposeServiceEntity.create();
        exposeServiceEntity.setContent(currentServiceEntity.getRuleDelegateOutputParams().getContent());
        exposeServiceEntity.setSize(currentServiceEntity.getRuleDelegateOutputParams().getSize());
        exposeServiceEntity.setStatus(currentServiceEntity.getRuleDelegateOutputParams().getStatus());

        execution.setVariable(currentServiceEntity.getCurrentActivityID(), exposeServiceEntity);


        if (execution instanceof ExecutionEntity) {

            VariableInstanceEntity variableInstanceEntity =
                    VariableInstanceEntity.create(
                            execution.getActivity().getId(),
                            new ServiceType("exposeServiceType", ExposeServiceEntity.class),
                            exposeServiceEntity);

            variableInstanceEntity.setId("inAndOut");

            ((ExecutionEntity) execution).getQueryVariables().add(variableInstanceEntity);

        } else {
            System.out.println("NO");
        }

    }

    @Override
    public void appendServiceId(ActivityExecution execution, ExecutedServicesEntities executedServicesEntities) {
        execution.setVariable(EXCUTED_SERVICES_ENTITY, executedServicesEntities);
    }


    @Override
    public void createOut(CurrentServiceEntity currentServiceEntity,
                          ExecutedServicesEntities executedServicesEntities,
                          ActivityExecution execution) {


        executedServicesEntities.addExecutedServiceContent(
                ServiceReturnContent.createDefaultService(
                        currentServiceEntity.getCurrentActivityID(),
                        currentServiceEntity.getSimpleName(),
                        currentServiceEntity.getRuleDelegateOutputParams().getStatus(),
                        currentServiceEntity.getRuleDelegateOutputParams().getSize(),
                        currentServiceEntity.getRuleDelegateOutputParams().getContent()
                )
        );


        // 更新
        appendServiceId(execution, executedServicesEntities);

        // 更新外层参数
        bindingOutputContent(currentServiceEntity, execution);


    }
}
