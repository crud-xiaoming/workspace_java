package com.example.scheduler.job;

import com.example.scheduler.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 定时任务类
 * 支持通过配置文件控制开关和cron表达式
 */
@Component
@ConditionalOnProperty(prefix = "scheduler.task", name = "enabled", havingValue = "true")
public class ScheduledTask {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private TaskService taskService;

    @Value("${scheduler.task.job-name}")
    private String jobName;

    // 任务执行状态标志，volatile保证线程可见性
    private volatile boolean isRunning = false;

    /**
     * 定时任务方法
     * 使用cron表达式从配置文件读取执行时间
     * 检测上一次任务是否执行完成，未完成则放弃本轮执行
     */
    @Scheduled(cron = "${scheduler.task.cron}")
    public void executeTask() {
        // 检查任务是否正在执行
        if (isRunning) {
            logger.info("{} 任务正在执行中，放弃本轮执行", jobName);
            return;
        }

        LocalDateTime startTime = LocalDateTime.now();
        logger.info("{} 任务开始执行，开始时间：{}", jobName, formatter.format(startTime));

        try {
            // 设置任务执行状态为正在执行
            isRunning = true;

            // 执行任务：查询Oracle表数据并打印记录数
            long count = taskService.countTableData();
            logger.info("{} 任务执行完成，处理数据量：{}条", jobName, count);
        } catch (Exception e) {
            logger.error("{} 任务执行失败：{}", jobName, e.getMessage(), e);
        } finally {
            // 无论任务执行成功还是失败，都设置任务执行状态为已完成
            isRunning = false;
            LocalDateTime endTime = LocalDateTime.now();
            logger.info("{} 任务结束执行，结束时间：{}", jobName, formatter.format(endTime));
        }
    }

}