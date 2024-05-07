package fit.se2.APlusBook.controller;

import fit.se2.APlusBook.model.Voucher;
import fit.se2.APlusBook.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class VoucherController {
    @Autowired
    private VoucherRepository voucherRepository;
    
    @RequestMapping(value = "/Voucher/list")
    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/Voucher/detail/{id}")
    public String getVoucherById(@PathVariable(value = "id") Long id, Model model) {
        Voucher Voucher = voucherRepository.getById(id);
        model.addAttribute("Voucher", Voucher);
        return "VoucherDetail";
    }

    @RequestMapping(value = "/admin/Voucher/add")
    public String addVoucher(Model model) {
        Voucher Voucher = new Voucher();
        model.addAttribute("Voucher", Voucher);
        return "VoucherAdd";
    }

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/admin/Voucher/update/{id}")
    public String updateVoucher(@PathVariable(value = "id") Long id, Model model) {
        Voucher Voucher = voucherRepository.getById(id);
        model.addAttribute("Voucher", Voucher);
        return "VoucherUpdate";
    }

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/admin/Voucher/delete/{id}")
    public String deleteVoucher(@PathVariable(value = "id") Long id) {
        if(voucherRepository.existsById(id)) {
            Voucher Voucher = voucherRepository.getById(id);
            voucherRepository.delete(Voucher);
        }
        return "VoucherDelete";
    }

    @RequestMapping(value = "/admin/Voucher/save")
    public String saveVoucher(Voucher Voucher, BindingResult result) {
        voucherRepository.save(Voucher);
        return "redirect:/Voucher/detail/" + Voucher.getId();
    }

}
