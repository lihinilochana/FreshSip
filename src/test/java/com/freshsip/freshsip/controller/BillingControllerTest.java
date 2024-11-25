package com.freshsip.freshsip.controller;



import com.freshsip.freshsip.dto.BillingDTO;
import com.freshsip.freshsip.service.BillingService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BillingControllerTest {

    private BillingService billingService = mock(BillingService.class);
    private BillingController billingController = new BillingController();



    @Test
    public void testCreateBill_Failure() {
        // Given
        BillingDTO billingDTO = new BillingDTO();
        billingDTO.setCartId(8L);

        when(billingService.createBill(billingDTO)).thenThrow(new RuntimeException("Error"));

        // When
        ResponseEntity<BillingDTO> response = billingController.createBill(billingDTO);

        // Then
        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void testUpdateBill_NotFound() {
        // Given
        Long billId = 999L;
        BillingDTO updatedBill = new BillingDTO();

        when(billingService.updateBill(billId.intValue(), updatedBill)).thenThrow(new RuntimeException("Bill not found"));

        // When
        ResponseEntity<BillingDTO> response = billingController.updateBill(billId.intValue(), updatedBill);

        // Then
        assertEquals(404, response.getStatusCodeValue());
    }

}
