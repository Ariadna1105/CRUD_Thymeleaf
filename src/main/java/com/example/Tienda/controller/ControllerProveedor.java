package com.example.Tienda.controller;

import com.example.Tienda.Entity.Proveedor;
import com.example.Tienda.Service.GestorProveedor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/tienda")
public class ControllerProveedor {
    GestorProveedor gp = new GestorProveedor();

    @GetMapping("/list")
    public String listSupplier(Model model) {
        try {
            model.addAttribute("list", gp.getAll());
        } catch (SQLException e) {
            return "./alerts/error";
        }
        return "./proveedor/list";
    }

    @GetMapping("/form-insert")
    public String formInsert(Model model) {
        Proveedor supplier = new Proveedor();
            model.addAttribute("suppliers", supplier);
        return "./proveedor/formInsert";
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute Proveedor supplier) {
        try {
            gp.insert(supplier);
        } catch (SQLException e) {
            return "./alerts/error";
        }
        return "redirect:/tienda/list";
    }

    @GetMapping("/formUpdate/{id}")
    public String formUpdate(@PathVariable int id, Model model) {
        try {
            Proveedor proveedor = gp.getById(id);
            if (proveedor != null) {
                model.addAttribute("updates", proveedor);
                return "./proveedor/formUpdate";
            } else {
                return "redirect:/tienda/list";
            }
        } catch (SQLException e) {
            return "./alerts/error";
        }
    }

    @PostMapping("/Update")
    public String update(Proveedor supplier){
        try {
            gp.update(supplier);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/tienda/list";
    }

    @GetMapping ("/delete/{id}")
    public String delete(@PathVariable int id){
        try {
            gp.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/tienda/list";
    }

    @GetMapping("/")
    public String home(){
        return "./proveedor/Home";
    }


}
