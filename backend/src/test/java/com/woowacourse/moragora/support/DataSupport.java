package com.woowacourse.moragora.support;

import com.woowacourse.moragora.entity.Attendance;
import com.woowacourse.moragora.entity.Event;
import com.woowacourse.moragora.entity.Meeting;
import com.woowacourse.moragora.entity.Participant;
import com.woowacourse.moragora.entity.Status;
import com.woowacourse.moragora.entity.user.User;
import com.woowacourse.moragora.repository.AttendanceRepository;
import com.woowacourse.moragora.repository.EventRepository;
import com.woowacourse.moragora.repository.MeetingRepository;
import com.woowacourse.moragora.repository.ParticipantRepository;
import com.woowacourse.moragora.repository.UserRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class DataSupport {

    private static final LocalTime ENTRANCE_TIME = LocalTime.of(10, 0);
    private static final LocalTime LEAVE_TIME = LocalTime.of(18, 0);

    private final UserRepository userRepository;

    private final MeetingRepository meetingRepository;

    private final ParticipantRepository participantRepository;

    private final AttendanceRepository attendanceRepository;

    private final EventRepository eventRepository;

    public DataSupport(final UserRepository userRepository,
                       final MeetingRepository meetingRepository,
                       final ParticipantRepository participantRepository,
                       final AttendanceRepository attendanceRepository,
                       final EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.meetingRepository = meetingRepository;
        this.participantRepository = participantRepository;
        this.attendanceRepository = attendanceRepository;
        this.eventRepository = eventRepository;
    }

    public Participant saveParticipant(final User user, final Meeting meeting) {
        final User savedUser = userRepository.save(user);
        final Meeting savedMeeting = meetingRepository.save(meeting);
        final Participant participant = participantRepository.save(new Participant(savedUser, savedMeeting, false));
        participant.mapMeeting(savedMeeting);
        return participant;
    }

    public Participant saveParticipant(final User user, final Meeting meeting, final boolean isMaster) {
        final User savedUser = userRepository.save(user);
        final Meeting savedMeeting = meetingRepository.save(meeting);
        final Participant participant = participantRepository.save(new Participant(savedUser, savedMeeting, isMaster));
        participant.mapMeeting(savedMeeting);
        return participant;
    }

    public Attendance saveAttendance(final Participant participant, final LocalDate attendanceDate, final
    Status status) {
        final Event event = eventRepository
                .save(new Event(attendanceDate, ENTRANCE_TIME, LEAVE_TIME, participant.getMeeting()));
        return attendanceRepository.save(new Attendance(status, false, participant, event));
    }

    public Attendance saveAttendance(final Participant participant, final LocalDate attendanceDate,
                                     final boolean disabled, final Status status) {
        final Event event = eventRepository
                .save(new Event(attendanceDate, ENTRANCE_TIME, LEAVE_TIME, participant.getMeeting()));
        return attendanceRepository.save(new Attendance(status, disabled, participant, event));
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public List<Long> saveUsers(final List<User> users) {
        for (User user : users) {
            userRepository.save(user);
        }

        return users.stream()
                .map(User::getId)
                .collect(Collectors.toList());
    }

    public Meeting saveMeeting(final Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    public Event saveEvent(final Meeting meeting) {
        return eventRepository.save(new Event())
    }
}
