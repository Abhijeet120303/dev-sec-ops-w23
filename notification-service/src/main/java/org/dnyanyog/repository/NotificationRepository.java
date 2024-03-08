package org.dnyanyog.repository;

import org.dnyanyog.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface NotificationRepository extends JpaRepository<Notification,Long> {
	
	

}
