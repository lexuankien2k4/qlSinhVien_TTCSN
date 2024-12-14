package com.example.btl.ttcsn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.btl.ttcsn.entity.KetQuaHocTap;
@Repository
public interface KetQuaHocTapRepository  extends JpaRepository<KetQuaHocTap, Integer>{

     
     @Query("SELECT kq.diem, COUNT(kq.diem) " +
             "FROM KetQuaHocTap kq " +
             "GROUP BY kq.diem " +
             "ORDER BY COUNT(kq.diem) DESC")
      List<Object[]> getMostFrequentScores();
      @Query("SELECT kt.diem, COUNT(kt) FROM KetQuaHocTap kt WHERE kt.diem BETWEEN 0 AND 10 GROUP BY kt.diem")
      List<Object[]> countScores();
      @Query("SELECT kq.diem AS diem, COUNT(kq.diem) AS count " +
              "FROM KetQuaHocTap kq " +
              "GROUP BY kq.diem " +
              "ORDER BY kq.diem")
       List<Object[]> countDiemGroupByDiem();
}
