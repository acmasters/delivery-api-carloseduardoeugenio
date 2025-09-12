package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.ClientDTO;
import com.deliverytech.delivery_api.entity.Client;
import com.deliverytech.delivery_api.service.ClientServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClientControllerTest {
    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientServiceImpl clientServiceImplMock;

    private MockMvc mockMvc;

    @BeforeAll
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

//    @Test
//    @DisplayName("Post - Method user to add clients - Return 201")
//    void testCreateClientWithReturn201() throws Exception {
//        var client = new Client(1L,"Ana Beatriz","anabeatriz@gmail.com","1199999999","Rua dos Sonhos,55",true,or);
//

  //  }
}
