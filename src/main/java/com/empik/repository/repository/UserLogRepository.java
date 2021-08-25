package com.empik.repository.repository;

import org.springframework.data.repository.CrudRepository;

import com.empik.repository.bean.UserLogEntity;

public interface UserLogRepository extends CrudRepository<UserLogEntity, String> {
 
 
}