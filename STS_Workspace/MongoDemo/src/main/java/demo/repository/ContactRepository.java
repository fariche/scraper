package demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import demo.domain.Contact;

public interface ContactRepository extends MongoRepository<Contact, String> {}
