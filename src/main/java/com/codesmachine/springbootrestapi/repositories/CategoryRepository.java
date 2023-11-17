package com.codesmachine.springbootrestapi.repositories;

import com.codesmachine.springbootrestapi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
}
