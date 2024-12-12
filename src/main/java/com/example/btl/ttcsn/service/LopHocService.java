package com.example.btl.ttcsn.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.btl.ttcsn.entity.KhoaHoc;
import com.example.btl.ttcsn.entity.LopHoc;
import com.example.btl.ttcsn.entityDTO.LopHocDTO;
import com.example.btl.ttcsn.repository.KhoaHocRepository;
import com.example.btl.ttcsn.repository.LopHocRepository;

@Service
public class LopHocService {
	@Autowired
    private LopHocRepository lopHocRepository;

    @Autowired
    private KhoaHocRepository khoaHocRepository;

    public List<LopHocDTO> getAllLopHoc() {
        return lopHocRepository.findAll().stream().map(lopHoc -> {
            LopHocDTO dto = new LopHocDTO();
            dto.setMaLopHoc(lopHoc.getMaLopHoc());
            dto.setTenLopHoc(lopHoc.getTenLopHoc());
            dto.setDiaChi(lopHoc.getDiaChi());
            dto.setMaKhoaHoc(lopHoc.getKhoaHoc().getMaKhoaHoc());
            return dto;
        }).collect(Collectors.toList());
    }

    public LopHocDTO getLopHocById(Integer id) {
        LopHoc lopHoc = lopHocRepository.findById(id).orElse(null);
        if (lopHoc == null) return null;

        LopHocDTO dto = new LopHocDTO();
        dto.setMaLopHoc(lopHoc.getMaLopHoc());
        dto.setTenLopHoc(lopHoc.getTenLopHoc());
        dto.setDiaChi(lopHoc.getDiaChi());
        dto.setMaKhoaHoc(lopHoc.getKhoaHoc().getMaKhoaHoc());
        return dto;
    }

    public LopHoc saveLopHoc(LopHocDTO dto) {
        LopHoc lopHoc = new LopHoc();
        if (dto.getMaLopHoc() != null) {
            lopHoc.setMaLopHoc(dto.getMaLopHoc());
        }
        lopHoc.setTenLopHoc(dto.getTenLopHoc());
        lopHoc.setDiaChi(dto.getDiaChi());
        KhoaHoc khoaHoc = khoaHocRepository.findById(dto.getMaKhoaHoc()).orElse(null);
        lopHoc.setKhoaHoc(khoaHoc);
        return lopHocRepository.save(lopHoc);
    }

    public void deleteLopHocById(Integer id) {
        lopHocRepository.deleteById(id);
    }
 // Thêm mới lớp học
    public LopHoc addLopHoc(LopHocDTO lopHocDTO) {
        LopHoc lopHoc = new LopHoc();
        lopHoc.setTenLopHoc(lopHocDTO.getTenLopHoc());
        lopHoc.setDiaChi(lopHocDTO.getDiaChi());

        // Liên kết với KhoaHoc
        KhoaHoc khoaHoc = khoaHocRepository.findById(lopHocDTO.getMaKhoaHoc())
                .orElseThrow(() -> new IllegalArgumentException("Khoa học không tồn tại"));
        lopHoc.setKhoaHoc(khoaHoc);

        return lopHocRepository.save(lopHoc);
    }

    // Sửa lớp học
    public LopHoc updateLopHoc(LopHocDTO lopHocDTO) {
        LopHoc lopHoc = lopHocRepository.findById(lopHocDTO.getMaLopHoc())
                .orElseThrow(() -> new IllegalArgumentException("Lớp học không tồn tại với ID: " + lopHocDTO.getMaLopHoc()));

        lopHoc.setTenLopHoc(lopHocDTO.getTenLopHoc());
        lopHoc.setDiaChi(lopHocDTO.getDiaChi());

        // Liên kết lại với KhoaHoc (nếu có thay đổi)
        KhoaHoc khoaHoc = khoaHocRepository.findById(lopHocDTO.getMaKhoaHoc())
                .orElseThrow(() -> new IllegalArgumentException("Khoa học không tồn tại"));
        lopHoc.setKhoaHoc(khoaHoc);

        return lopHocRepository.save(lopHoc);
    }
}
