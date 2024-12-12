package com.example.btl.ttcsn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.btl.ttcsn.entityDTO.LichHocDTO;
import com.example.btl.ttcsn.service.GiangVienService;
import com.example.btl.ttcsn.service.LichHocService;
import com.example.btl.ttcsn.service.LopHocService;
import com.example.btl.ttcsn.service.MonHocService;

@Controller
@RequestMapping("/lich-hoc")
public class LichHocController {
	@Autowired
	private LichHocService lichHocService;
	@Autowired
	private MonHocService monHocService;
	@Autowired
	private GiangVienService giangVienService;
	@Autowired
	private LopHocService lopHocService;
	
    @GetMapping
    public String getAllLichHoc(Model model) {
        List<LichHocDTO> lichHocList = lichHocService.getAllLichHoc();
        model.addAttribute("lichHocList", lichHocList);
        return "lichhoc/lhList";
    }

    @GetMapping("/add")
    public String addLichHocForm(Model model) {
        model.addAttribute("lichHocDTO", new LichHocDTO());
        model.addAttribute("lopHocs", lopHocService.getAllLopHoc());
        model.addAttribute("monHocs", monHocService.getAllMonHoc());
        model.addAttribute("giangViens", giangVienService.getAllGiangVien());
        return "lichhoc/lhAdd";  // Trả về trang add.html
    }

    // Xử lý thêm lịch học
    @PostMapping("/add")
    public String addLichHoc(@ModelAttribute LichHocDTO lichHocDTO, Model model) {
        try {
            // Gọi service để thêm lịch học
            lichHocService.saveLichHoc(lichHocDTO);

            // Thêm thành công, chuyển hướng về trang danh sách lịch học hoặc trang thành công
            return "redirect:/lich-hoc";  // Redirect về danh sách lịch học
        } catch (Exception e) {
            // Nếu có lỗi, trả lại thông báo lỗi
            model.addAttribute("error", "Có lỗi xảy ra khi thêm lịch học.");
            model.addAttribute("lichHocDTO", lichHocDTO);
            model.addAttribute("lopHocs", lopHocService.getAllLopHoc());
            model.addAttribute("monHocs", monHocService.getAllMonHoc());
            model.addAttribute("giangViens", giangVienService.getAllGiangVien());
            return "lichhoc/lhAdd";
        }
    }

    @GetMapping("/edit/{id}")
    public String editLichHocForm(@PathVariable("id") int id, Model model) {
    	LichHocDTO lichHocDTO = lichHocService.getLichHocById(id);
//        LichHocDTO lichHocDTO = lichHocService.getAllLichHoc()
//                                              .stream()
//                                              .filter(dto -> dto.getMaLichHoc() == id)
//                                              .findFirst()
//                                              .orElseThrow(() -> new RuntimeException("LichHoc not found"));
        model.addAttribute("lichHocDTO", lichHocDTO);
        model.addAttribute("lopHocs", lichHocService.getAllLichHoc());
        model.addAttribute("monHocs", monHocService.getAllMonHoc());
        model.addAttribute("giangViens", giangVienService.getAllGiangVien());
        return "lichhoc/lhEdit";
    }

    @PostMapping("/edit")
    public String updateLichHoc(@ModelAttribute("lichHocDTO") LichHocDTO lichHocDTO) {
        lichHocService.saveLichHoc(lichHocDTO);
        return "redirect:/lich-hoc";
    }

    @GetMapping("/delete/{id}")
    public String deleteLichHoc(@PathVariable("id") int id) {
        lichHocService.deleteLichHoc(id);
        return "redirect:/lich-hoc";
    }
}
