package com.example.scheduler.mapper;

/**
 * 任务数据访问层
 * 负责与数据库交互
 */
public interface TaskMapper {

    /**
     * 统计数据库表数量
     * @return 表数量
     */
    long countTables();

}