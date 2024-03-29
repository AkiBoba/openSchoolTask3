package com.example.openschooltask3.service;

import com.example.openschooltask3.dto.UserDTO;
import com.example.openschooltask3.entity.User;
import com.example.openschooltask3.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    UserRepository repository;

    @Override
    public User create(UserDTO userDTO) {
        return repository.save(userDTO.toUser());
    }

    @Override
    public User update(User user) {
        return repository.save(user);
    }

    @Override
    public Optional<User> get(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.delete(User.builder()
                        .id(id)
                .build());
    }
}
