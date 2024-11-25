package com.freshsip.freshsip.service;
import com.freshsip.freshsip.dto.OrderItemsDTO;
import com.freshsip.freshsip.dto.ProductDTO;
import com.freshsip.freshsip.entity.Items;
import com.freshsip.freshsip.entity.Order;
import com.freshsip.freshsip.entity.OrderItems;
import com.freshsip.freshsip.repository.ItemRepo;
import com.freshsip.freshsip.repository.OrderItemsRepository;
import com.freshsip.freshsip.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderItemsServiceTest {

    @Mock
    private OrderItemsRepository orderItemsRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ItemRepo itemsRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private OrderItemsService orderItemsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveOrder() {

        ProductDTO product1 = new ProductDTO(1L,1500.00,15, "Product1", 100.0);
        ProductDTO product2 = new ProductDTO(2L, 1500.00,10,"Product2", 150.0);
        List<ProductDTO> selectedProducts = Arrays.asList(product1, product2);

        OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
        orderItemsDTO.setCart_id(1L);
        orderItemsDTO.setSelectedProducts(selectedProducts);

        Order order = new Order();
        order.setCart_id(1L);

        Items item1 = new Items();
        item1.setItem_id(1L);
        Items item2 = new Items();
        item2.setItem_id(2L);


        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(itemsRepository.findById(1L)).thenReturn(Optional.of(item1));
        when(itemsRepository.findById(2L)).thenReturn(Optional.of(item2));


        orderItemsService.saveOrder(orderItemsDTO);


        verify(orderRepository, times(1)).findById(1L);
        verify(itemsRepository, times(1)).findById(1L);
        verify(itemsRepository, times(1)).findById(2L);
        verify(orderItemsRepository, times(2)).save(any(OrderItems.class));
    }

    @Test
    public void testGetAllOrderItems() {

        OrderItems orderItem = new OrderItems();
        List<OrderItems> orderItemsList = Arrays.asList(orderItem);

        when(orderItemsRepository.findAll()).thenReturn(orderItemsList);
        when(modelMapper.map(orderItemsList, new TypeToken<List<OrderItemsDTO>>() {}.getType())).thenReturn(Arrays.asList(new OrderItemsDTO()));


        List<OrderItemsDTO> result = orderItemsService.getAllOrderItems();


        assertNotNull(result);
        assertEquals(1, result.size());
        verify(orderItemsRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteOrderByOrderID() {
        Long cartId = 1L;


        orderItemsService.deleteOrderByOrderID(cartId);


        verify(orderItemsRepository, times(1)).deleteByCartId(cartId);
    }

    @Test
    public void testGetChannelingByChannelingDate() {
        Long cartId = 1L;
        List<OrderItems> orderItemsList = Arrays.asList(new OrderItems());

        when(orderItemsRepository.getChannelingByChannelingDate(cartId)).thenReturn(orderItemsList);
        when(modelMapper.map(orderItemsList, new TypeToken<List<OrderItemsDTO>>() {}.getType())).thenReturn(Arrays.asList(new OrderItemsDTO()));

        List<OrderItemsDTO> result = orderItemsService.getChannelingByChannelingDate(cartId);

        assertNotNull(result);
        verify(orderItemsRepository, times(1)).getChannelingByChannelingDate(cartId);
    }

    @Test
    public void testGetOrderByOrderDate() {
        LocalTime createTime = LocalTime.now();
        List<OrderItems> orderItemsList = Arrays.asList(new OrderItems());

        when(orderItemsRepository.getOrderByOrderTime(createTime)).thenReturn(orderItemsList);

        List<OrderItems> result = orderItemsService.getOrderByOrderDate(createTime);

        assertNotNull(result);
        verify(orderItemsRepository, times(1)).getOrderByOrderTime(createTime);
    }

    @Test
    public void testGetOrderByOrders() {

        LocalDate createDate = LocalDate.now();

        Object[] orderData1 = {1L, "Item1", 10, 100.0};
        Object[] orderData2 = {2L, "Item2", 5, 50.0};

        List<Object[]> orderDataList = Arrays.asList(orderData1, orderData2);


        when(orderItemsRepository.getOrderByOrders(createDate)).thenReturn(orderDataList);


        List<Object[]> result = orderItemsService.getOrderByOrders(createDate);


        assertNotNull(result);
        assertEquals(2, result.size(), "The result should contain two items");
        assertEquals("Item1", result.get(0)[1], "The first item's name should be 'Item1'");
        assertEquals(100.0, result.get(0)[3], "The first item's price should be 100.0");


        verify(orderItemsRepository, times(1)).getOrderByOrders(createDate);
    }

    @Test
    public void testGetOrderByOrder() {
        Long cartId = 1L;
        List<OrderItems> orderItemsList = Arrays.asList(new OrderItems());

        when(orderItemsRepository.getOrderByOrder(cartId)).thenReturn(orderItemsList);

        List<OrderItems> result = orderItemsService.getOrderByOrder(cartId);

        assertNotNull(result);
        verify(orderItemsRepository, times(1)).getOrderByOrder(cartId);
    }

    @Test
    public void testUpdateOrder() {

        ProductDTO product1 = new ProductDTO(1L,1000,10, "Product1", 100.0);
        List<ProductDTO> selectedProducts = Arrays.asList(product1);

        OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
        orderItemsDTO.setCart_id(1L);
        orderItemsDTO.setSelectedProducts(selectedProducts);

        Order order = new Order();
        order.setCart_id(1L);
        Items item1 = new Items();
        item1.setItem_id(1L);


        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(itemsRepository.findById(1L)).thenReturn(Optional.of(item1));


        orderItemsService.updateOrder(1L, orderItemsDTO);

        verify(orderRepository, times(1)).findById(1L);
        verify(itemsRepository, times(1)).findById(1L);
        verify(orderItemsRepository, times(1)).save(any(OrderItems.class));
    }
}
