package com.test.test.service;

import com.test.test.Model.Member;
import com.test.test.Model.Role;
import com.test.test.Model.User;
import com.test.test.repository.MemberRepository;
import com.test.test.repository.RoleRepository;
import com.test.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected MemberRepository memberRepository;

    @Autowired
    protected RoleRepository roleRepository;

    private static final String MEMBER = "member";

    @Transactional(readOnly = true, noRollbackFor = RuntimeException.class)
    public User findById(long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            return userOpt.get();
        }
        throw new RuntimeException("XXX");
    }

    @Transactional(readOnly = true)
    public Role findRoleByName(String roleName) {
        Optional<Role> roleOpt = roleRepository.findByName(roleName);
        if (roleOpt.isPresent()) {
            return roleOpt.get();
        }
        throw new RuntimeException("XXX");
    }

    public void addUser(String name) {
        Optional<User> userOpt = userRepository.findByName(name);
        if (userOpt.isPresent()) {
            User user = User
                    .builder()
                    .username(name)
                    .age(10)
                    .build();
            userRepository.save(user);
            addMemberForUser(MEMBER, user.getId());
        }
    }

    public void addMemberForUser(String roleName, long userId) {
        User user = findById(userId);
        Role role = findRoleByName(roleName);
        Member member = Member.builder()
                .roleId(role.getId())
                .userId(userId)
                .build();
        memberRepository.save(member);
    }
}
