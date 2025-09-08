package com.example.turistguide2.Controller;

import com.example.turistguide2.Service.TuristService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(TuristController.class)
class TuristControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TuristService turistService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldShowOrderForm()throws Exception {
        mockMvc.perform(get("/coffee"))
                .andExpect(status().isOk())
                .andExpect(view().name("order-form"));
    }

//    @Test
//    void shouldPlaceOrder() throws Exception {
//        CoffeeOrder coffeeOrder = new CoffeeOrder("Flat White", "Medium", "Skim");
//        when(coffeeOrderService.placeOrder(any(CoffeeOrder.class))).thenReturn(coffeeOrder);
//
//        mockMvc.perform(post("/coffee/order")
//                        .param("coffeeType", "Flat White")
//                        .param("coffeeSize", "Medium")
//                        .param("milkType", "Skim"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/coffee/show-order"));
//
//
//
//        ArgumentCaptor<CoffeeOrder> captor = ArgumentCaptor.forClass(CoffeeOrder.class);
//        verify(coffeeOrderService).placeOrder(captor.capture());
//
//        CoffeeOrder captured = captor.getValue();
//        assertEquals("Flat White", captured.getCoffeeType());
//        assertEquals("Medium", captured.getCoffeeSize());
//        assertEquals("Skim", captured.getMilkType());
//        assertNotNull(captured.getOrderId()); // UUID generated as expected
//    }

}