package com.example.btl.ttcsn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.btl.ttcsn.entity.GiangVien;
import com.example.btl.ttcsn.repository.GiangVienRepository;

@Service
public class GiangVienService {
	@Autowired
	private GiangVienRepository giangVienRepository;
	
    public List<GiangVien> getAllGiangVien() {
        return giangVienRepository.findAll();
    }

    public GiangVien getGiangVienById(Integer id) {
        return giangVienRepository.findById(id).orElse(null);
    }

    public GiangVien addGiangVien(GiangVien giangVien) {
        return giangVienRepository.save(giangVien);
    }

    public void deleteGiangVien(Integer id) {
        giangVienRepository.deleteById(id);
    }
}
