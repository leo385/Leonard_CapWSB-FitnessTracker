package com.capgemini.wsb.fitnesstracker.user.internal.repository;

import com.capgemini.wsb.fitnesstracker.user.api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
//    default Optional<UserEntity> findByEmail(String email) {
//        return findAll().stream()
//                        .peek(user -> System.out.println("Checking user: " + user.getEmail()))
//                        .filter(user -> Objects.equals(user.getEmail(), email))
//                        .findFirst();
//    }

//    @Query("SELECT u FROM UserEntity u WHERE LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%'))")
//    Optional<UserEntity> findByEmail(String email);


    /* I preffered to use JPQL query in view of the performance */


    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     */

    @Query("SELECT u FROM UserEntity u WHERE LOWER(u.email) LIKE CONCAT('%', LOWER(:email), '%')")
    Optional<UserEntity> findByEmail(String email);

    /**
     * Query searching users by age above that defined.
     * It calculates difference between CURRENT_DATE and `birthdate` from UserEntity
     *
     * @param age age of the user to search
     */

    @Query("SELECT u FROM UserEntity u WHERE FUNCTION('timestampdiff', YEAR, u.birthdate, CURRENT_DATE) > :age")
    List<UserEntity> findByAgeGreaterThan(@Param("age") int age);


    default List<UserEntity> findUsersByTheirGreaterAge(LocalDate time) {
        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(time))
                .toList();
    }

}
