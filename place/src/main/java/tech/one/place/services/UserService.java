package tech.one.place.services;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tech.one.place.model.User;
import tech.one.place.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserService {
    private final UserRepository userRepo;
    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    public String CreateUserInfo(User user){
        userRepo.save(user);
        return "User Created Successfully ";
    }
    public User register(User registerUser) {
        if (userRepo.existsByEmail(registerUser.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }




        User user = new User();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(registerUser.getPassword()));
        user.setFirstName(registerUser.getFirstName());
        user.setLastName(registerUser.getLastName());
        user.setEmail(registerUser.getEmail());
        user.setType(registerUser.getType());

        user.setVerified(registerUser.isVerified());


        User registeredUser = userRepo.save(user);



        return registeredUser;
    }
    public User getUserInfo(long userID){
        return userRepo.findById(userID).orElse(null);
    }
    public User DeleteUserInfo(long userId){
        User user = userRepo.findById(userId).get();
        userRepo.delete(user);
        return user;
    }
    public List<User> getALLUserInfo(){return userRepo.findAll();}
    public User updateUserInfo(User user, long id) {
       User exuser = userRepo.findById(id).get();
        exuser.setFirstName(user.getFirstName());
        exuser.setLastName(user.getLastName());
        exuser.setType(user.getType());
        exuser.setPassword(user.getPassword());
        exuser.setEmail(user.getEmail());
        exuser.setVerified(user.isVerified());
        return userRepo.save(exuser);
    }


    public User login(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User registeredUser = userRepo.findByEmail(user.getEmail());
        if(registeredUser != null){
            if(encoder.matches(user.getPassword(), registeredUser.getPassword())){
                return registeredUser;
            }else{
                throw new IllegalArgumentException("Invalid Password");
            }
        }
        throw new IllegalArgumentException("User not found");
    }



}
