import axios from 'axios';

const API_URL = 'http://localhost:8080/FreshSip/Order';
class OrderService {
    submitOrder(orderData) {
        return axios.post(`${API_URL}/adminUser/OrderItems`, orderData);
    }
    updateOrder(cartId, orderData) {
        return axios.put(`${API_URL}/adminUser/OrderItemsOrder/${cartId}`, orderData);
    }
    submitCart(cartData) {
        return axios.post(`${API_URL}/adminUser/Order`, cartData);
    }
    updateCartOrder(cartId, ordercartData) {
        return axios.put(`${API_URL}/adminUser/OrderByOrderID/${cartId}`, ordercartData);
    }
    
    deleteOrderById(CartId) {
        return axios.delete(`${API_URL}/adminUser/OrderItemsByOrderItemsID/${CartId}`);
    }

}

export default new OrderService();