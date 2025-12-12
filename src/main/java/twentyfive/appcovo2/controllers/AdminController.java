package twentyfive.appcovo2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twentyfive.appcovo2.models.Admin;
import twentyfive.appcovo2.services.AdminService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("{id}")
    public ResponseEntity<Admin> getAdmin(@PathVariable Long id) {
        Optional<Admin> admin = adminService.getById(id);
        if (admin.isPresent()) {
            return ResponseEntity.ok(admin.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAll();
        return ResponseEntity.ok(admins);
    }

    @PostMapping("/save")
    public ResponseEntity<Admin> saveAdmin(@RequestBody Admin admin) {
        Admin savedAdmin = adminService.save(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAdmin);
    }

    @PutMapping("{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin adminDetails) {
        try{
            Admin updatedAdmin = adminService.update(id, adminDetails);
            return ResponseEntity.ok(updatedAdmin);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        try {
            adminService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

}
