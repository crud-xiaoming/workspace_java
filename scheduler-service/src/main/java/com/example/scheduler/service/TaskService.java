package com.example.scheduler.service;

import com.example.scheduler.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 任务业务服务层
 * 负责处理定时任务的业务逻辑
 */
@Service
public class TaskService {

    @Autowired
    private TaskMapper taskMapper;

    /**
     * 统计Oracle表数据量
     * 示例：查询USER_TABLES表的记录数
     * @return 数据量
     */
    public long countTableData() {
        return taskMapper.countTables();
    }

}