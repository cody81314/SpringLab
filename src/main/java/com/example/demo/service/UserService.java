package com.example.demo.service;

import com.example.demo.dao.RoleDao;
import com.example.demo.dao.UserDao;
import com.example.demo.domain.User;
import com.example.demo.vo.RoleVO;
import com.example.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserVO> getAllUser() {
        return userDao.getAllUser();
    }

    @Cacheable(value = "userCache", key = "#userSeq")
    public UserVO getUser(int userSeq) {
        return userDao.getUserBySeq(userSeq)
                .orElseThrow(() -> new RuntimeException("Can't found user"));
    }

    @Transactional
    public void addUser(User user) {
        UserVO userVO = user.getUserVO();
        userVO.setPcode(passwordEncoder.encode(user.getPcode()));

        userDao.save(userVO);

        RoleVO roleVO = new RoleVO();
        roleVO.setRoleId("ROLE_" + user.getRoleId().toUpperCase());

        roleDao.save(roleVO);

//        CompletableFuture<Void> saveUserFuture = CompletableFuture.runAsync(() -> userDao.save(userVO));
//        CompletableFuture<Void> saveRoleFuture = CompletableFuture.runAsync(() -> roleDao.save(roleVO));
//        saveRoleFuture.runAfterBoth(saveUserFuture, () -> System.out.println("Save User & Role done."));
//
//        CompletableFuture.allOf(saveUserFuture, saveRoleFuture)
//                .join();
    }

    public void updateUserByDomain(User user) {
        updateUser(user.getUserSeq(), userVO -> {
            if (user.getUserCode() != null)
                userVO.setUserCode(user.getUserCode());
            if (user.getPcode() != null)
                userVO.setPcode(user.getPcode());
            if (user.getRoleId() != null)
                userVO.setRoleId(user.getRoleId());
        });
    }

    public void updateUser(int userSeq, Consumer<UserVO> setter) {
        UserVO userVO = getUser(userSeq);
        setter.accept(userVO);
        userDao.update(userVO);
    }

    @CacheEvict(value = "userCache", key = "#userSeq")
    public void deleteUser(int userSeq) {
        userDao.delete(userSeq);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userDao.getUserByUserCode(username)
                .orElseThrow(() -> new RuntimeException("Can't found user by username."));
    }
}
