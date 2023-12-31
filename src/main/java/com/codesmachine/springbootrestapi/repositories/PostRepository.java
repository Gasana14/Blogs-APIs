package com.codesmachine.springbootrestapi.repositories;

import com.codesmachine.springbootrestapi.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post,String> {

    List<Post> findByCategoryId(String id);
}
