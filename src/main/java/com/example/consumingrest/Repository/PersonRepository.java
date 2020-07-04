package com.example.consumingrest.Repository;

import com.example.consumingrest.Object.Person;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends MongoRepository<Person, String> {

    List<Person> findByLastName(@Param("name") String name);

    // Prevents POST /people and PATCH /people/:id
    @Override
    @RestResource(exported = false)
    public Person save(Person s);

    // Prevents DELETE /people/:id
    @Override
    @RestResource(exported = false)
    public void delete(Person t);

}