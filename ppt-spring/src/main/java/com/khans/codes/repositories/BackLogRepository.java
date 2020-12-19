package com.khans.codes.repositories;

import com.khans.codes.model.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackLogRepository extends CrudRepository<Backlog,Long> {

    Backlog findByProjectIdentifier(String projectIdentifier);
}
