package yaremko.yaromyr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yaremko.yaromyr.dto.UserRequestResponse;
import yaremko.yaromyr.entity.Role;
import yaremko.yaromyr.entity.User;
import yaremko.yaromyr.exception.WrongInputData;
import yaremko.yaromyr.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserRequestResponse save(UserRequestResponse userRequest) {
        return new UserRequestResponse(userRepository.save(userRequestToUser(null, userRequest)));
    }

    public User findOne(Long id) throws WrongInputData {
        return userRepository.findById(id).orElseThrow(() -> new WrongInputData("User with id " + id + " not found"));
    }

    public User findOne(UserRequestResponse userRequest) throws WrongInputData {
       return userRequest.getId() == null
               ? userRepository.save(userRequestToUser(null, userRequest))
               : findOne(userRequest.getId());
    }

    private User userRequestToUser(User user, UserRequestResponse userRequest) {
        if (user == null) {
            user = new User();
            user.setRole(Role.ROLE_UNREGISTERED_USER);
        } else {
            user.setRole(Role.valueOf(userRequest.getRole()));
        }

        user.setEmail(userRequest.getEmail());
        user.setName(userRequest.getName());
        user.setPassword(userRequest.getPassword());
        user.setRegistered(userRequest.getRegistered());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        return user;
    }
}
