package com.JavaSchool.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.JavaSchool.jpamodel.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
	
	List<Card> findByCardNo(long cardNo);
	
    @Query("SELECT c FROM Card c ORDER BY c.cardNo ASC")
    List<Card> findTop10Cards(Pageable pageable);
}