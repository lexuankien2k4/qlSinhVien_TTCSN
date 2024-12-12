package com.example.btl.ttcsn.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.btl.ttcsn.entity.GiangVien;
import com.example.btl.ttcsn.entity.LichHoc;
import com.example.btl.ttcsn.entity.LopHoc;
import com.example.btl.ttcsn.entity.MonHoc;
import com.example.btl.ttcsn.entityDTO.LichHocDTO;
import com.example.btl.ttcsn.repository.GiangVienRepository;
import com.example.btl.ttcsn.repository.LichHocRepository;
import com.example.btl.ttcsn.repository.LopHocRepository;
import com.example.btl.ttcsn.repository.MonHocRepository;

@Service
public class LichHocService {
	@Autowired
    private LichHocRepository lichHocRepository;

    @Autowired
    private LopHocRepository lopHocRepository;

    @Autowired
    private MonHocRepository monHocRepository;

    @Autowired
    private GiangVienRepository giangVienRepository;
    
    // Lấy danh sách tất cả Lịch Học
    public List<LichHocDTO> getAllLichHoc() {
        List<LichHoc> lichHocList = lichHocRepository.findAll();
        return lichHocList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Thêm hoặc cập nhật Lịch Học
    public LichHocDTO saveLichHoc(LichHocDTO lichHocDTO) {
        LichHoc lichHoc = convertToEntity(lichHocDTO);
        LichHoc savedLichHoc = lichHocRepository.save(lichHoc);
        return convertToDTO(savedLichHoc);
    }

    // Xóa Lịch Học
    public void deleteLichHoc(int maLichHoc) {
        lichHocRepository.deleteById(maLichHoc);
    }

    // Ánh xạ từ Entity sang DTO
    private LichHocDTO convertToDTO(LichHoc lichHoc) {
        LichHocDTO dto = new LichHocDTO();
        dto.setMaLichHoc(lichHoc.getMaLichHoc());
        dto.setMaLopHoc(lichHoc.getLopHoc().getMaLopHoc());
        dto.setMaMonHoc(lichHoc.getMonHoc().getMaMonHoc());
        dto.setMaGiangVien(lichHoc.getGiangVien().getMaGiangVien());
        dto.setThoiGianBatDau(lichHoc.getThoiGianBatDau().toString());
        dto.setThoiGianKetThuc(lichHoc.getThoiGianKetThuc().toString());
        return dto;
    }

    // Ánh xạ từ DTO sang Entity
    private LichHoc convertToEntity(LichHocDTO lichHocDTO) {
        LichHoc lichHoc = new LichHoc();
        if (lichHocDTO.getMaLichHoc() != 0) {
            lichHoc.setMaLichHoc(lichHocDTO.getMaLichHoc());
        }
        LopHoc lopHoc = lopHocRepository.findById(lichHocDTO.getMaLopHoc())
                                        .orElseThrow(() -> new RuntimeException("LopHoc not found"));
        MonHoc monHoc = monHocRepository.findById(lichHocDTO.getMaMonHoc())
                                        .orElseThrow(() -> new RuntimeException("MonHoc not found"));
        GiangVien giangVien = giangVienRepository.findById(lichHocDTO.getMaGiangVien())
                                                 .orElseThrow(() -> new RuntimeException("GiangVien not found"));

        lichHoc.setLopHoc(lopHoc);
        lichHoc.setMonHoc(monHoc);
        lichHoc.setGiangVien(giangVien);
        lichHoc.setThoiGianBatDau(LocalDateTime.parse(lichHocDTO.getThoiGianBatDau()));
        lichHoc.setThoiGianKetThuc(LocalDateTime.parse(lichHocDTO.getThoiGianKetThuc()));
        return lichHoc;
    }
    // Phương thức để lấy lịch học theo ID
    public LichHocDTO getLichHocById(int id) {
        // Tìm kiếm lịch học trong cơ sở dữ liệu
        LichHoc lichHoc = lichHocRepository.findById(id).orElseThrow(() -> new RuntimeException("Lịch học không tồn tại!"));

        // Chuyển đổi LichHoc thành LichHocDTO (DTO có thể chứa các thông tin khác ngoài đối tượng gốc)
        LichHocDTO lichHocDTO = new LichHocDTO();
        lichHocDTO.setMaLichHoc(lichHoc.getMaLichHoc());
        lichHocDTO.setMaLopHoc(lichHoc.getLopHoc().getMaLopHoc());
        lichHocDTO.setMaMonHoc(lichHoc.getMonHoc().getMaMonHoc());
        lichHocDTO.setMaGiangVien(lichHoc.getGiangVien().getMaGiangVien());
        lichHocDTO.setThoiGianBatDau(lichHoc.getThoiGianBatDau());
        lichHocDTO.setThoiGianKetThuc(lichHoc.getThoiGianKetThuc());

        // Trả về DTO chứa thông tin của lịch học
        return lichHocDTO;
    }
    
}
