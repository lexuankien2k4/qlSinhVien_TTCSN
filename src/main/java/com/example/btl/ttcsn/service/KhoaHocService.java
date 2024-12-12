package com.example.btl.ttcsn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.btl.ttcsn.entity.KhoaHoc;
import com.example.btl.ttcsn.repository.KhoaHocRepository;

@Service
public class KhoaHocService {
	 @Autowired
	    private KhoaHocRepository khoaHocRepository;

	    public List<KhoaHoc> getAllKhoaHoc() {
	        return khoaHocRepository.findAll();
	    }

	    public KhoaHoc getKhoaHocById(Integer id) {
	        return khoaHocRepository.findById(id).orElse(null);
	    }

	    public KhoaHoc saveKhoaHoc(KhoaHoc khoaHoc) {
	        return khoaHocRepository.save(khoaHoc);
	    }

	    public void deleteKhoaHocById(Integer id) {
	        khoaHocRepository.deleteById(id);
	    }
}
