package org.jetbrains.assignment.repository;

import org.jetbrains.assignment.dto.MovementLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementLogRepository extends JpaRepository<MovementLog, Long> {
}