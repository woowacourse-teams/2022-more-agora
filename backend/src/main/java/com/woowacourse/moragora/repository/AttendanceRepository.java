package com.woowacourse.moragora.repository;

import com.woowacourse.moragora.entity.Attendance;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class AttendanceRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public AttendanceRepository(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Attendance save(final Attendance attendance) {
        entityManager.persist(attendance);
        return attendance;
    }

    public List<Attendance> findByParticipantId(final Long participantId) {
        return entityManager.createQuery("select a from Attendance a where a.participant.id = :participantId",
                        Attendance.class)
                .setParameter("participantId", participantId)
                .getResultList();
    }

    public long findAttendanceCountById(final Long participantId) {
        return entityManager.createQuery(
                        "select count(distinct a.attendanceDate) from Attendance a "
                                + "where a.participant.id = :id", Long.class)
                .setParameter("id", participantId)
                .getSingleResult();
    }
}
