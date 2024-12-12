package com.example.btl.ttcsn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.btl.ttcsn.entity.SinhVien;
import com.example.btl.ttcsn.repository.SinhVienRepository;

@Service
public class SinhVienService {
	@Autowired
    private SinhVienRepository sinhVienRepository;

    public List<SinhVien> getAllSinhVien() {
        return sinhVienRepository.findAll();
    }

    public SinhVien getSinhVienById(Integer id) {
    	
    	Optional<SinhVien>optional = sinhVienRepository.findById(id);
    	SinhVien sinhVien = null;
    	if(optional.isPresent()) {
    		sinhVien = optional.get();
    	}else {
    		throw new RuntimeException("khong co mon hoc nao dc tim thay !");
    	}
        return sinhVien;
    }

    public SinhVien saveSinhVien(SinhVien sinhVien) {
    	
       return sinhVienRepository.save(sinhVien);
    }

    public void deleteSinhVien(Integer id) {
        sinhVienRepository.deleteById(id);
    }
}
