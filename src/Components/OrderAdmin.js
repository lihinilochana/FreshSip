import React, { useState, useEffect } from 'react';
import OrderService from '../Service/OrderService';
import './Cart.css';

function OrderAdmin() {
  const [orders, setOrders] = useState([]);
  const [date, setDate] = useState(new Date().toISOString().split('T')[0]);
  const [quantities, setQuantities] = useState({});
  const [orderc, setOrderc] = useState([]);
  const [cart, setCart] = useState('')

  useEffect(() => {
    const fetchOrderDetails = async () => {
      try {
        const response = await fetch(`http://localhost:8080/FreshSip/Order/getOrderByOrders/${date}`);
        if (!response.ok) {
          throw new Error('Failed to fetch order data');
        }
        const orderData = await response.json();
        const sortedOrders = orderData.sort((a, b) => new Date(b.date) - new Date(a.date));

        setOrders(sortedOrders);

      } catch (error) {
        console.error('Error fetching order data:', error);
      }
    };

    fetchOrderDetails();
  }, []);


  const handleSubmitClick = async (orderid) => {
    console.log(orderid);
    const fetchOrderDetails = async (orderid) => {
      console.log(orderid);
      try {
        const response = await fetch(`http://localhost:8080/FreshSip/Order/getCartById/${orderid}`);
        if (!response.ok) {
          throw new Error('Failed to fetch order data');
        }
        const orderData = await response.json();
        setCart(orderData);

        return orderData;
      } catch (error) {
        console.error('Error fetching order data:', error);
        return null;
      }
    };


    const orderDetails = await fetchOrderDetails(orderid);


    if (orderDetails) {
      const orderingData = {
        cart_id: orderid,
        user_id: orderDetails.user_id,
        create_date: orderDetails.create_date,
        create_time: orderDetails.create_time,
        full_total: orderDetails.full_total,
        status: 1,
        u_email: orderDetails.u_email,
      };
      console.log(orderingData);

      if (orderid) {
        try {
          await OrderService.updateCartOrder(orderid, orderingData);
          const orderResponse = await fetch(`http://localhost:8080/FreshSip/Order/getCartById/${orderid}`);

          if (!orderResponse.ok) {
            throw new Error('Failed to fetch updated order data');
          }

          const updatedOrder = await orderResponse.json();
          setOrderc(updatedOrder);
          setOrders(prevOrders =>
            prevOrders.map(o => o.id === orderid ? updatedOrder : o)
          );
        } catch (error) {
          console.error('Error updating order:', error);
        }
      }
    }
  };


  return (
    <div>
      <h1 className="heading_a1">Order Stock</h1>
      <div>
        <h2 className="heading_2">Order History</h2>

        <div>
          <table className='table table-bordered table-hover shadow'>
            <thead>
              <tr>
                <th className="th_1">Cart Id</th>
                <th className="th_1">Cart Items Id</th>
                <th className="th_1">Item Name</th>
                <th className="th_2">Item Quantity</th>
              </tr>
            </thead>
            <tbody>
              {orders.map(order => (
                <tr key={order[7]}>
                  <td className='th_3'>{order[0]}</td>
                  <td>{order[7]}</td>
                  <td>{order[2]}</td>
                  <td>{order[1]}</td>
                  <td><button id="bt1" onClick={() => handleSubmitClick(order[0])}>{order[6] === 1 ? "Done" : "Pending"}</button></td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

    </div>
  );

}
export default OrderAdmin;
