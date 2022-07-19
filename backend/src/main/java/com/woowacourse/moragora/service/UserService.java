package com.woowacourse.moragora.service;

import com.woowacourse.moragora.dto.UserRequest;
import com.woowacourse.moragora.dto.UserResponse2;
import com.woowacourse.moragora.entity.user.EncodedPassword;
import com.woowacourse.moragora.entity.user.User;
import com.woowacourse.moragora.exception.UserNotFoundException;
import com.woowacourse.moragora.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long create(final UserRequest userRequest) {
        final User user = new User(userRequest.getEmail(), EncodedPassword.fromRawValue(userRequest.getPassword()),
                userRequest.getNickname());
        final User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    public UserResponse2 findById(final Long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return new UserResponse2(user.getId(), user.getEmail(), user.getNickname());
    }
}
