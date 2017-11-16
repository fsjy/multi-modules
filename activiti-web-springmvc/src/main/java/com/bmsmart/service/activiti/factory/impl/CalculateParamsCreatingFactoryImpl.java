package com.bmsmart.service.activiti.factory.impl;

import com.bmsmart.constant.CONST;
import com.bmsmart.service.activiti.factory.ParamsCreatingFactory;
import com.bmsmart.service.activiti.factory.entities.ExecutedServicesEntities;
import com.bmsmart.service.activiti.factory.entities.CurrentServiceEntity;
import com.bmsmart.service.activiti.rule.entity.ServiceInputParams;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.omg.CORBA.Current;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

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

            executedServicesEntities = ExecutedServicesEntities
                    .create()
                    .addService(CONST.ACT_TITLE, (String) objectTitle)
                    .addService(CONST.ACT_CONTENT, (String) objectContent)
                    .addService(execution.getCurrentActivityId(),
                            currentServiceEntity.getSimpleName());
            // TODO 修改插入map的内容

        } else {
            // 不是第一个处理service
            if (object instanceof ExecutedServicesEntities) {

                executedServicesEntities = (ExecutedServicesEntities) object;

                executedServicesEntities
                        .addService(execution.getCurrentActivityId(),
                                currentServiceEntity.getSimpleName());
                // TODO 修改插入map的内容

            } else {
                throw new IllegalArgumentException("can not use '_BMS_EXECUTION_SERVICE_LIST' to get service list from ActivityExecution");
            }
        }

        return executedServicesEntities;

    }

    @Override
    public void createIn(ActivityExecution execution, CurrentServiceEntity currentServiceEntity) {

        currentServiceEntity.setCurrentActivityID(execution.getCurrentActivityId());

        // 获取当前处理过的所有services
        ExecutedServicesEntities executedServicesEntities = createServiceEntityRepository(execution, currentServiceEntity);

        // 加入当前的service ID
        appendServiceId(execution, executedServicesEntities);

        // 绑定需要的数据到当前输入params
        bindingNecessaryInputParams(executedServicesEntities, currentServiceEntity);

    }

    @Override
    public void bindingNecessaryInputParams(ExecutedServicesEntities executedServicesEntities,
                                            CurrentServiceEntity currentServiceEntity) {

        Map<String, String> mapping = executedServicesEntities.getServiceMapping();
        ServiceInputParams inputParams = new ServiceInputParams();

        // 如果当前服务需要指定参数
        if (currentServiceEntity.getActivityInputVariables().isUsing()) {


            currentServiceEntity.getActivityInputVariables().getInputs().forEach(s -> {

                if (null != mapping.get(s)) {

                    inputParams.addParams(s, mapping.get(s));
                } else {
                    throw new IllegalArgumentException("can not fetch " + s + "from ServiceMapping");
                }
            });

        } else {

            // 不需要指定参数则输入为之前所有的数据
            mapping.forEach((k, v) -> {

                inputParams.addParams(k, v);

            });

        }

        currentServiceEntity.setServiceInputParams(inputParams);

    }

    @Override
    public void mappingOutputParams() {


    }

    @Override
    public void appendServiceId(ActivityExecution execution, ExecutedServicesEntities executedServicesEntities) {
        execution.setVariable(EXCUTED_SERVICES_ENTITY, executedServicesEntities);
    }


    @Override
    public void createOut() {

    }
}
