package demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import demo.domain.Account;

public interface AccountRepository extends MongoRepository<Account, String> {
	  
	  public Account findByUsername(String username);

	}
