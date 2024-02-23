package com.benjaminfan.flowable;

import org.flowable.engine.*;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.task.api.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LeaveProcess {

    public static void main(String[] args) {
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
                .setJdbcUsername("benjaminfan")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        ProcessEngine processEngine = cfg.buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment()
                .addClasspathResource("leave-process.bpmn20.xml")
                .deploy();

        Scanner scanner= new Scanner(System.in);

        System.out.println("请假人：");
        String employee = scanner.nextLine();

        System.out.println("请多少天假：");
        Integer days = Integer.valueOf(scanner.nextLine());

        System.out.println("请假事由：");
        String description = scanner.nextLine();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employee", employee);
        variables.put("days", days);
        variables.put("description", description);
        runtimeService.startProcessInstanceByKey("leaveProcess", variables);

        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
        System.out.println("你有 " + tasks.size() + " 个待办事项");
        for (int i=0; i<tasks.size(); i++) {
            System.out.println((i+1) + ") " + tasks.get(i).getName());
        }
        System.out.println("请选择要处理的事项：");
        int taskIndex = Integer.valueOf(scanner.nextLine());
        Task task = tasks.get(taskIndex - 1);
        Map<String, Object> processVariables = taskService.getVariables(task.getId());
        System.out.println(processVariables.get("employee") + "，想要请 " +
                processVariables.get("days") + " 天假，你同意吗？同意(Y)，不同意(N)");
        boolean approved = scanner.nextLine().toLowerCase().equals("y");
        variables = new HashMap<String, Object>();
        variables.put("approved", approved);
        if (!approved) {
            System.out.println("拒绝的理由：");
            String rejectReason = scanner.nextLine();
            variables.put("rejectReason", rejectReason);
        }
        taskService.complete(task.getId(), variables);
    }
}
