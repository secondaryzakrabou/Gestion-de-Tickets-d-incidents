package ma.emsi.tickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.emsi.tickets.models.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long>{

	AppUser getByUsername(String username);
}
