package com.spring.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spring.event.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
	
}