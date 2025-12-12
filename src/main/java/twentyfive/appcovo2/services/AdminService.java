package twentyfive.appcovo2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twentyfive.appcovo2.models.Admin;
import twentyfive.appcovo2.repositories.AdminRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Optional<Admin> getById(Long id) {
        return adminRepository.findById(id);
    }

    public Optional<Admin> getByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin update(Long id, Admin admin) {
        //TODO
        return new Admin();
    }

    public Admin patch(Long id, Admin admin) {
        //TODO
        return new Admin();
    }

    public void delete(Long id) {
        if(!adminRepository.existsById(id)) {
            throw new IllegalArgumentException("Admin con ID: "+id+" non trovato");
        }
        adminRepository.deleteById(id);
    }





}
