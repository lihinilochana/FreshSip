package com.freshsip.freshsip.dto;



import com.freshsip.freshsip.dto.BillingDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class BillingDTOTest {

    private BillingDTO billingDTO;

    @BeforeEach
    public void setUp() {
        // Initialize BillingDTO before each test
        billingDTO = new BillingDTO();
    }

    @Test
    public void testGettersAndSetters() {
        // Set the specific test values
        Long expectedBillId = 1L;
        LocalDate expectedDate = LocalDate.now();
        LocalTime expectedTime = LocalTime.now();
        Double expectedCash = 500.0;      // Cash value
        Double expectedBalance = 100.0;   // Balance value
        Long expectedCartId = 8L;         // Cart ID value
        Double expectedFullTotal = 400.0; // Full Total value

        // Set values using setters
        billingDTO.setBillId(expectedBillId);
        billingDTO.setDate(expectedDate);
        billingDTO.setTime(expectedTime);
        billingDTO.setCash(expectedCash);
        billingDTO.setBalance(expectedBalance);
        billingDTO.setCartId(expectedCartId);
        billingDTO.setFullTotal(expectedFullTotal);

        // Assert that getters return the expected values
        assertEquals(expectedBillId, billingDTO.getBillId());
        assertEquals(expectedDate, billingDTO.getDate());
        assertEquals(expectedTime, billingDTO.getTime());
        assertEquals(expectedCash, billingDTO.getCash());
        assertEquals(expectedBalance, billingDTO.getBalance());
        assertEquals(expectedCartId, billingDTO.getCartId());
        assertEquals(expectedFullTotal, billingDTO.getFullTotal(), 0.001); // Use delta for floating point comparison
    }


}
