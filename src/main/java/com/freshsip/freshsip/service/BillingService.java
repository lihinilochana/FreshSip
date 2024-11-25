package com.freshsip.freshsip.service;


import com.freshsip.freshsip.entity.Billing;
import com.freshsip.freshsip.dto.BillingDTO;
import com.freshsip.freshsip.repository.BillingRepo;
import com.freshsip.freshsip.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillingService {

    @Autowired
    private BillingRepo billingRepository;

    @Autowired
    private OrderRepository ordersRepository;

    @Autowired
    private ModelMapper modelMapper;

    private BillingDTO convertToDTO(Billing billing) {
        return modelMapper.map(billing, BillingDTO.class);
    }

    private Billing convertToEntity(BillingDTO billingDTO) {
        return modelMapper.map(billingDTO, Billing.class);
    }

    public List<BillingDTO> getAllBills() {
        return billingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<BillingDTO> getBillById(int id) {
        return billingRepository.findById(id).map(this::convertToDTO);
    }

    public BillingDTO createBill(BillingDTO billingDTO) {
        Billing billing = convertToEntity(billingDTO);

        Double fullTotal = ordersRepository.findFullTotalByCartId(billingDTO.getCartId());

        billing.setFullTotal(fullTotal);

        Billing savedBilling = billingRepository.save(billing);
        return convertToDTO(savedBilling);
    }


    public BillingDTO updateBill(int id, BillingDTO updatedBillDTO) {
        return billingRepository.findById(id).map(existingBill -> {
            // Update the cash and balance
            existingBill.setCash(updatedBillDTO.getCash());
            existingBill.setBalance(updatedBillDTO.getBalance());

            // Save the updated bill
            Billing updatedBill = billingRepository.save(existingBill);

            return convertToDTO(updatedBill);
        }).orElseThrow(() -> new RuntimeException("Bill not found with id " + id));
    }


    public void deleteBill(int id) {
        billingRepository.deleteById(id);
    }
}
