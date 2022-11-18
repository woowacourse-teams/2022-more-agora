package com.woowacourse.moragora.application;

import com.woowacourse.moragora.domain.attendance.AttendanceRepository;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class AttendanceScheduler {

    private static final Logger log = LoggerFactory.getLogger(AttendanceScheduler.class);

    private final AttendanceRepository attendanceRepository;
    private final ServerTimeManager serverTimeManager;

    public AttendanceScheduler(final AttendanceRepository attendanceRepository,
                               final ServerTimeManager serverTimeManager) {
        this.attendanceRepository = attendanceRepository;
        this.serverTimeManager = serverTimeManager;
    }

    @Scheduled(cron = "5 */5 * * * *")
    @Transactional
    public void updateToTardyAtAttendanceClosingTime() {
        final LocalDateTime dateTime = serverTimeManager.getDateAndTime();
        attendanceRepository.updateByEventDateTimeAndStatus(dateTime.toLocalDate(), dateTime.toLocalTime());
        log.info("출석 마감 처리 완료");
    }
}
