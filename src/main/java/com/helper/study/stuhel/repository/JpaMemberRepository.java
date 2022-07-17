package com.helper.study.stuhel.repository;

import com.helper.study.stuhel.member.to.MemberTO;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em;//JPA는 entityManager가 모든것을 동작시킨다.
    // datajpa 라이브러리를 받으면, 스프링부트가 엔티티메니저를 자동으로 연결시켜준다.
    // 현재 DB랑 다 연결해서 만들어준걸 우리는 인젝션 받으면 된다.
    // properties정보랑 DB커넥션 정보 등을 다 짬뽕해서 스프링부트가 엔티티메니저라는걸 만들어준다.
    // 얘는 데이터소스를 내부적으로 다 들고 있어서 DB랑 통신도 내부에서 다 해준다.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }
    private static Map<String, Integer> map=new HashMap<>();

    public Optional<MemberTO> findById(Long id) {
        MemberTO member = em.find(MemberTO.class, id);//타입이랑 식별자 넣어주면 조회가 된다.
        return Optional.ofNullable(member); //값이 없을 경우 ofNullable
    }
}
