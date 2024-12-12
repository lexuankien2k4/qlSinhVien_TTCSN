package com.example.btl.ttcsn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.btl.ttcsn.entity.KetQuaHocTap;
@Repository
public interface KetQuaHocTapRepository  extends JpaRepository<KetQuaHocTap, Integer>{
    @Query("SELECT k.monHoc.tenMonHoc, AVG(k.diem) as avgDiem " +
            "FROM KetQuaHocTap k " +
            "GROUP BY k.monHoc.tenMonHoc " +
            "HAVING AVG(k.diem) >= :minScore")
     List<Object[]> findMonHocWithHighScores(@Param("minScore") float minScore);
}
