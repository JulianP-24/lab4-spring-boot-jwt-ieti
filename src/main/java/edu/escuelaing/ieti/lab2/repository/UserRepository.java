package edu.escuelaing.ieti.lab2.repository;

import edu.escuelaing.ieti.lab2.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{$or: [{name: ?0}, {lastName: ?0}]}")
    List<User> findUsersWithNameOrLastNameLike(String queryText);

    @Query("{createdAt : {$gt : ?0 }}")
    List<User> findUsersCreatedAfter(Date startDate);
}
