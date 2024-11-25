package com.freshsip.freshsip.controller;

import com.freshsip.freshsip.dto.BillingDTO;
import com.freshsip.freshsip.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/FreshSip")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @GetMapping("/admin/bill")
    public List<BillingDTO> getAllBills() {
        List<BillingDTO> bills = billingService.getAllBills();

        bills.forEach(bill -> System.out.println("Full Total: " + bill.getFullTotal()));

        return bills;
    }

    @GetMapping("admin/{id}")
    public ResponseEntity<BillingDTO> getBillById(@PathVariable int id) {
        return billingService.getBillById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/admin/bill")
    public ResponseEntity<BillingDTO> createBill(@RequestBody BillingDTO billingDTO) {
        try {
            BillingDTO createdBill = billingService.createBill(billingDTO);
            return ResponseEntity.ok(createdBill);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<BillingDTO> updateBill(@PathVariable int id, @RequestBody BillingDTO updatedBillDTO) {
        try {
            BillingDTO updatedBill = billingService.updateBill(id, updatedBillDTO);
            return ResponseEntity.ok(updatedBill);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // If bill not found
        }
    }


    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable int id) {
        billingService.deleteBill(id);
        return ResponseEntity.noContent().build();
    }
}
