package Minipackage.Service;

import Minipackage.Model.Package;
import Minipackage.Repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {
    @Autowired
    PackageRepository packageRepository;

    public Package createPackage(Package myPackage) {
        return packageRepository.save(myPackage);
    }

    public List<Package> getAllPackage() {
        return packageRepository.findAll();
    }
}
