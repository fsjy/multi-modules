package com.bmsmart.service.activiti.java;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 朴素贝叶斯训练模型服务
 * Add by Yanglu 2017.10.26
 */
public class NbmDelegateService extends DelegateService implements JavaDelegate {


    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    /**
     * 需要实现activiti的execute接口
     *
     * @param execution
     * @throws Exception
     */
    @Override
    public void execute(DelegateExecution execution) throws Exception {


        // 获得本service所取得的variable
        execution.getVariableInstancesLocal();

        // 获得包括parent的所有的variable
        execution.getVariableInstances();

        ex(execution);

    }
}
