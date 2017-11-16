package com.bmsmart.service.activiti.factory;

import com.bmsmart.service.activiti.factory.entities.ExecutedServicesEntities;
import com.bmsmart.service.activiti.factory.entities.CurrentServiceEntity;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;

public interface ParamsCreatingFactory {

    /**
     * 返回所有instance中涉及到的service
     *
     * @return 返回选中所有流程图中service的List
     */
    ExecutedServicesEntities createServiceEntityRepository(ActivityExecution execution,
                                                           CurrentServiceEntity currentServiceEntity);

    void appendServiceId(ActivityExecution execution,
                         ExecutedServicesEntities executedServicesEntities);

    void bindingNecessaryInputParams(ExecutedServicesEntities executedServicesEntities,
                                     CurrentServiceEntity currentServiceEntity);

    void mappingOutputParams();

    void createIn(ActivityExecution execution, CurrentServiceEntity executingServiceEntity);

    void createOut();

}
