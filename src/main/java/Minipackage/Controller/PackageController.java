package Minipackage.Controller;

import Minipackage.Model.Package;
import Minipackage.Service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/minipackage")
public class PackageController {
    @Autowired
    PackageService packageService;

    @PostMapping(headers = {"Content-type=application/json"})
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    public Package createPackage(@RequestBody Package myPackage){
        return packageService.createPackage(myPackage);
    }

    @GetMapping(headers = {"Content-type=application/json"})
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public List<Package> getAllPackage(){
        return packageService.getAllPackage();
    }
}
