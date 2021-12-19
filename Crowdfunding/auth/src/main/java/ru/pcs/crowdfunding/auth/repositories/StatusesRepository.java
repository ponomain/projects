package ru.pcs.crowdfunding.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.pcs.crowdfunding.auth.domain.Status;

public interface StatusesRepository extends JpaRepository<Status, Long> {
    Status getByName(Status.StatusEnum name);

    @Query(value = "select * from status where name = ?1", nativeQuery=true)
    Status getStatusByName(String name);
}
