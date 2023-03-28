package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.entity.Request;
import ru.practicum.entity.Status;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAllByRequesterId(Long id);

    List<Request> findAllByEventId(Long eventId);

    @Query("SELECT  count(r.id) " +
            "FROM Request AS r " +
            "WHERE r.event.id = :eventId " +
            "AND r.status = :status")
    Optional<Integer> findCountRequestByEventIdAndStatus(Long eventId, Status status);

    @Query("select r.event.id, count(r) from Request r where r.event.id in ?1 and r.status = ?2 group by r.event.id")
    Map<Long, Integer> findAllConfirmedRequestsByEventIds(List<Long> ids, Status status);
}