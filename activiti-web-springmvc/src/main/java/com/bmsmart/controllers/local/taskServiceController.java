package com.bmsmart.controllers.local;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class taskServiceController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    // <process id="my-process" isExecutable="true">
    private final static String ACTIVITI_PROCESS_ID = "my-process";

    private final static String UEL_TANSIT_XML = "testxml/serviceTask-activiti-expression-xml-data-transit-test.bpmn20.xml";

    private final static String VARIABLE_TANSIT_XML = "testxml/serviceTask-activiti-calss-variable-data-transit-test.bpmn20.xml";

    private final static String VARIABLE_EX_GATEWAY_TANSIT_XML = "testxml/serviceTask-activiti-class-gateway-exclusive-nelExpression-test.bpmn20.xml";

    private final static String VARIABLE_PA_GATEWAY_TANSIT_XML = "testxml/serviceTask-activiti-class-gateway-parallel-nelExpression-test.bpmn20.xml";

    private final static String RULE_SERVICE_XML = "testxml/businessRuleTask-activiti-class.bpmn20.xml";

    private final static String RULE_ONE_BY_ONE_SERVICE_XML = "testxml/businessRuleTask-onebyone-activiti-class.bpmn20.xml";

    private final static String TASK_XML = "testxml/javaTask.xml";

    /**
     * 测试使用在xml中使用UEL来传递参数的 TaskService运行入口
     * ignore "POST" and "GET"
     *
     * @return
     */
    @RequestMapping(value = "local/taskServiceXmlTransit")
    public String executeTaskServiceByUel() {

        repositoryService.createDeployment()
                //.addClasspathResource("org/com.bmsmart/test/vacationRequest.bpmn20.xml")
                .addClasspathResource(UEL_TANSIT_XML)
                //.addClasspathResource("org/com.bmsmart/test/process.bpmn20-2.xml")
                .deploy();

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(ACTIVITI_PROCESS_ID, "");

        return "modelList";

    }

    /**
     * 测试使用在实现Delegate的class中使用variable来传递参数的 TaskService运行入口
     * ignore "POST" and "GET"
     *
     * @return
     */
    @RequestMapping(value = "local/taskServiceVariableTransit")
    public String executeTaskServiceByVariable() {

        repositoryService.createDeployment()
                //.addClasspathResource("org/com.bmsmart/test/vacationRequest.bpmn20.xml")
                .addClasspathResource(VARIABLE_EX_GATEWAY_TANSIT_XML)
                //.addClasspathResource("org/com.bmsmart/test/process.bpmn20-2.xml")
                .deploy();

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(ACTIVITI_PROCESS_ID, "");


        return "modelList";

    }

    /**
     * 测试使用在实现Delegate的class中使用variable来传递参数的 TaskService运行入口
     * 并且加入网关的条件选择路径，测试传递条件值${input}是否正确
     * 测试使用Exclusive网关
     * <p>
     * ignore "POST" and "GET"
     *
     * @return
     */
    @RequestMapping(value = "local/taskServiceExclusiveGatewayVariableTransit")
    public String executeTaskServiceByVariableAndExculsiveGateway() {

        repositoryService.createDeployment()
                //.addClasspathResource("org/com.bmsmart/test/vacationRequest.bpmn20.xml")
                .addClasspathResource(VARIABLE_EX_GATEWAY_TANSIT_XML)
                //.addClasspathResource("org/com.bmsmart/test/process.bpmn20-2.xml")
                .deploy();


        taskService.getClass();


        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(ACTIVITI_PROCESS_ID, "");



        return "modelList";

    }

    /**
     * 测试使用在实现Delegate的class中使用variable来传递参数的 TaskService运行入口
     * 并且加入网关的条件选择路径，测试传递条件值${input}是否正确
     * 测试使用Parallel网关
     * <p>
     * ignore "POST" and "GET"
     *
     * @return
     */
    @RequestMapping(value = "local/taskServiceParallelGatewayVariableTransit")
    public String executeTaskServiceByVariableAndParallelGateway() {

        repositoryService.createDeployment()
                //.addClasspathResource("org/com.bmsmart/test/vacationRequest.bpmn20.xml")
                .addClasspathResource(VARIABLE_PA_GATEWAY_TANSIT_XML)
                //.addClasspathResource("org/com.bmsmart/test/process.bpmn20-2.xml")
                .deploy();


        taskService.getClass();


        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(ACTIVITI_PROCESS_ID, "");

        return "modelList";

    }


    /**
     * 测试使用在实现Delegate的class中使用variable来传递参数的 TaskService运行入口
     * 并且加入网关的条件选择路径，测试传递条件值${input}是否正确
     * 测试使用Parallel网关
     * <p>
     * ignore "POST" and "GET"
     *
     * @return
     */
    @RequestMapping(value = "local/ruleService")
    public String executeRuleService() {

        repositoryService.createDeployment()
                //.addClasspathResource("org/com.bmsmart/test/vacationRequest.bpmn20.xml")
                .addClasspathResource(RULE_ONE_BY_ONE_SERVICE_XML)
                //.addClasspathResource("org/com.bmsmart/test/process.bpmn20-2.xml")
                .deploy();


        taskService.getClass();


        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("自定义_BMS_", "");


        List<Task> tasks = taskService.createTaskQuery().list();

        // List<Execution> executions = runtimeService.createExecutionQuery().list();

        return "modelList";

    }


}
