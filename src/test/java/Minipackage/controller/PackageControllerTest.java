package Minipackage.controller;

import Minipackage.Controller.PackageController;
import Minipackage.Model.Package;
import Minipackage.Service.PackageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PackageController.class)
@ActiveProfiles(profiles = "test")
public class PackageControllerTest {
    @MockBean
    PackageService packageService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void should_create_a_package() throws Exception {
        Package myPackage = new Package();
        myPackage.setPackageNum(12312312);
        myPackage.setReceiver("Woody");
        myPackage.setPhone(1231231231);

        when(packageService.createPackage(any())).thenReturn(myPackage);

        ResultActions result = mockMvc.perform(post("/minipackage")
                .content(objectMapper.writeValueAsString(myPackage))
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.packageNum",is(myPackage.getPackageNum())))
                .andExpect(jsonPath("$.receiver",is(myPackage.getReceiver())));
    }


}
