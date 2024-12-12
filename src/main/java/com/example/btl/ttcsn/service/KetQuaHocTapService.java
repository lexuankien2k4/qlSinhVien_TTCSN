package com.example.btl.ttcsn.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.btl.ttcsn.entity.KetQuaHocTap;
import com.example.btl.ttcsn.entity.MonHoc;
import com.example.btl.ttcsn.entity.SinhVien;
import com.example.btl.ttcsn.entityDTO.KetQuaHocTapDTO;
import com.example.btl.ttcsn.repository.KetQuaHocTapRepository;
import com.example.btl.ttcsn.repository.MonHocRepository;
import com.example.btl.ttcsn.repository.SinhVienRepository;

@Service
public class KetQuaHocTapService {
	@Autowired
    private KetQuaHocTapRepository ketQuaHocTapRepository;

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Autowired
    private MonHocRepository monHocRepository;

    // Lấy danh sách tất cả kết quả học tập
    public List<KetQuaHocTapDTO> getAllKetQuaHocTap() {
        return ketQuaHocTapRepository.findAll().stream().map(ketQua -> {
            KetQuaHocTapDTO dto = new KetQuaHocTapDTO();
            dto.setMaKetQua(ketQua.getMaKetQua());
            dto.setMaSinhVien(ketQua.getSinhVien().getMaSinhVien());
            dto.setTenSinhVien(ketQua.getSinhVien().getHoTen());
            dto.setMaMonHoc(ketQua.getMonHoc().getMaMonHoc());
            dto.setTenMonHoc(ketQua.getMonHoc().getTenMonHoc());
            dto.setDiem(ketQua.getDiem());
            return dto;
        }).collect(Collectors.toList());
    }

    // Lấy thông tin kết quả học tập theo ID
    public KetQuaHocTapDTO getKetQuaHocTapById(Integer id) {
        KetQuaHocTap ketQuaHocTap = ketQuaHocTapRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy kết quả học tập với ID: " + id));

        KetQuaHocTapDTO dto = new KetQuaHocTapDTO();
        dto.setMaKetQua(ketQuaHocTap.getMaKetQua());
        dto.setMaSinhVien(ketQuaHocTap.getSinhVien().getMaSinhVien());
        dto.setTenSinhVien(ketQuaHocTap.getSinhVien().getHoTen());
        dto.setMaMonHoc(ketQuaHocTap.getMonHoc().getMaMonHoc());
        dto.setTenMonHoc(ketQuaHocTap.getMonHoc().getTenMonHoc());
        dto.setDiem(ketQuaHocTap.getDiem());
        return dto;
    }

    // Thêm mới kết quả học tập
    public KetQuaHocTap addKetQuaHocTap(KetQuaHocTapDTO dto) {
        KetQuaHocTap ketQuaHocTap = new KetQuaHocTap();
        ketQuaHocTap.setDiem(dto.getDiem());

        SinhVien sinhVien = sinhVienRepository.findById(dto.getMaSinhVien())
                .orElseThrow(() -> new IllegalArgumentException("Sinh viên không tồn tại với ID: " + dto.getMaSinhVien()));
        ketQuaHocTap.setSinhVien(sinhVien);

        MonHoc monHoc = monHocRepository.findById(dto.getMaMonHoc())
                .orElseThrow(() -> new IllegalArgumentException("Môn học không tồn tại với ID: " + dto.getMaMonHoc()));
        ketQuaHocTap.setMonHoc(monHoc);

        return ketQuaHocTapRepository.save(ketQuaHocTap);
    }

    // Cập nhật kết quả học tập
    public KetQuaHocTap updateKetQuaHocTap(KetQuaHocTapDTO dto) {
        KetQuaHocTap ketQuaHocTap = ketQuaHocTapRepository.findById(dto.getMaKetQua())
                .orElseThrow(() -> new IllegalArgumentException("Kết quả học tập không tồn tại với ID: " + dto.getMaKetQua()));

        ketQuaHocTap.setDiem(dto.getDiem());

        SinhVien sinhVien = sinhVienRepository.findById(dto.getMaSinhVien())
                .orElseThrow(() -> new IllegalArgumentException("Sinh viên không tồn tại với ID: " + dto.getMaSinhVien()));
        ketQuaHocTap.setSinhVien(sinhVien);

        MonHoc monHoc = monHocRepository.findById(dto.getMaMonHoc())
                .orElseThrow(() -> new IllegalArgumentException("Môn học không tồn tại với ID: " + dto.getMaMonHoc()));
        ketQuaHocTap.setMonHoc(monHoc);

        return ketQuaHocTapRepository.save(ketQuaHocTap);
    }

    // Xóa kết quả học tập theo ID
    public void deleteKetQuaHocTapById(Integer id) {
        ketQuaHocTapRepository.deleteById(id);
    }
}
