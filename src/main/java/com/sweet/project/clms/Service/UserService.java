package com.sweet.project.clms.Service;

import com.sweet.project.clms.Entity.User;
import com.sweet.project.clms.Repository.UserRepository;
import com.sweet.project.clms.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    @Autowired
    private UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public void saveUser(User user){
        User user1=findById(user.getUid());
        user.setPassword(user1.getPassword());
        this.userRepository.save(user);
    }
    public User findById(long user_id){
        User user=this.userRepository.findById(user_id).orElseThrow(()->new IllegalArgumentException("Invalid Student id:"+user_id));
        return user;
    }
    public void deleteUser(User user){
        this.userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(userName);

        user.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));

        return user.map(UserDetailsImpl::new).get();
    }
}
