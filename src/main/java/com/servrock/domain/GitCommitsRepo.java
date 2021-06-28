package com.servrock.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GitCommitsRepo extends JpaRepository<CommitEntity, String> {

}
