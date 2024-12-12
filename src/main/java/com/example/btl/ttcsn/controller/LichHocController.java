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
@RequestMapping("/lichhoc")
public class LichHocController {
    @Autowired
    private LichHocService lichHocService;

    // Hiển thị danh sách lịch học
    @GetMapping()
    public String listLichHoc(Model model) {
        List<LichHocDTO> lichHocList = lichHocService.getAllLichHoc();
        model.addAttribute("lichHocList", lichHocList);
        return "LichHoc/lhList"; // Đường dẫn tới file Thymeleaf hiển thị danh sách lịch học
    }

    // Hiển thị form thêm mới lịch học
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("lichHocDTO", new LichHocDTO());
        return "LichHoc/lhAdd"; // Đường dẫn tới file Thymeleaf cho form thêm lịch học
    }

    // Xử lý thêm mới lịch học
    @PostMapping("/add")
    public String addLichHoc(@ModelAttribute("lichHocDTO") LichHocDTO lichHocDTO) {
        lichHocService.addLichHoc(lichHocDTO);
        return "redirect:/lichhoc"; // Chuyển hướng về danh sách lịch học
    }

    // Hiển thị form sửa lịch học
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        LichHocDTO lichHocDTO = lichHocService.getLichHocById(id);
        model.addAttribute("lichHocDTO", lichHocDTO);
        return "LichHoc/lhEdit"; // Đường dẫn tới file Thymeleaf cho form sửa lịch học
    }

    // Xử lý sửa lịch học
    @PostMapping("/edit")
    public String editLichHoc(@ModelAttribute("lichHocDTO") LichHocDTO lichHocDTO) {
        lichHocService.updateLichHoc(lichHocDTO);
        return "redirect:/lichhoc"; // Chuyển hướng về danh sách lịch học
    }

    // Xóa lịch học
    @GetMapping("/delete/{id}")
    public String deleteLichHoc(@PathVariable("id") int id) {
        lichHocService.deleteLichHoc(id);
        return "redirect:/lichhoc"; // Chuyển hướng về danh sách lịch học
    }
}
