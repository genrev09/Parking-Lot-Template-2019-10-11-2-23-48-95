package Minipackage.Service;

import Minipackage.Model.Package;
import Minipackage.Repository.PackageRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PackageService.class)
@ActiveProfiles(profiles = "test")
public class PackageServiceTest {

    @MockBean
    private PackageRepository packageRepository;

    @Autowired
    PackageService packageService;

    @Test
    public void should_create_a_package() {
        Package myPackage = new Package();
        myPackage.setPackageNum(12312312);
        myPackage.setReceiver("Woody");
        myPackage.setPhone(1231231231);

        when(packageRepository.save(myPackage)).thenReturn(myPackage);

        Package actualPackage = packageService.createPackage(myPackage);

        Assertions.assertThat(actualPackage.getPackageNum()).isEqualTo(myPackage.getPackageNum());
        Assertions.assertThat(actualPackage.getReceiver()).isEqualTo(myPackage.getReceiver());
    }
}
