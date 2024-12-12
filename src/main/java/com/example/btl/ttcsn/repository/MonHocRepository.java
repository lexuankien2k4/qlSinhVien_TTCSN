package com.example.btl.ttcsn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.btl.ttcsn.entity.MonHoc;
@Repository
public interface MonHocRepository extends JpaRepository<MonHoc, Integer> {

}
