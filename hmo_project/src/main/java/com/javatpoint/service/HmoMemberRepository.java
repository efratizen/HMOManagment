package com.javatpoint.service;

import com.javatpoint.model.HmoMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HmoMemberRepository extends JpaRepository<HmoMember,Long> {
    HmoMember findByPhone(String phone);
    List<HmoMember> findAllByMobilePhoneContains(String prefix);
    boolean existsById(Long id);
}
