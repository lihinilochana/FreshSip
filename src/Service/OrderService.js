import axios from 'axios';

const API_URL = 'http://localhost:8080/FreshSip/Order';
class OrderService {
    submitOrder(orderData) {
        return axios.post(`${API_URL}/submitOrder`, orderData);
    }
    updateOrder(cartId, orderData) {
        return axios.put(`${API_URL}/updateOrder/${cartId}`, orderData);
    }
    submitCart(cartData) {
        return axios.post(`${API_URL}/saveOrder`, cartData);
    }
    updateCartOrder(cartId, ordercartData) {
        return axios.put(`${API_URL}/updateOrderByOrderID/${cartId}`, ordercartData);
    }

    deleteOrderById(CartId) {
        return axios.delete(`${API_URL}/deleteOrderByOrderID/${CartId}`);
    }

}

export default new OrderService();