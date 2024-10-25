import React, { useContext, useState, useEffect } from "react";
import { CartContext } from '../Features/ContextProvider.js';
import { useNavigate, useParams } from 'react-router-dom';
import './Cart.css';
import CartProduct from './CartProduct.js';
import { totalItem, totalPrice } from '../Features/CartUtils.js';
import OrderService from '../Service/OrderService.js';

export default function CartUpdate() {
  const { CartID, userId } = useParams();
  const navigate = useNavigate();
  const userID = 200273201475;
  const { cart, dispatch } = useContext(CartContext);
  const [carts, setCarts] = useState([]);
  const [detail, setDetail] = useState([]);
  const [selectedProducts, setSelectedProducts] = useState([]);
  const [datetime, setDatetime] = useState(new Date().toISOString().slice(0, 16));
  const [date, setDate] = useState(new Date().toISOString().split('T')[0]);


  useEffect(() => {
    const fetchCart = async () => {
      try {
        const response = await fetch(`http://localhost:8080/FreshSip/Order/getOrderByOrder/${CartID}`);
        if (!response.ok) {
          throw new Error('Failed to fetch cart');
        }
        const cartData = await response.json();
        setCarts(cartData);

      } catch (error) {
        console.error('Error fetching cart:', error);
      }
    };

    fetchCart();
  }, [CartID]);

  useEffect(() => {
    const fetchCarts = async () => {
      try {
        const response = await fetch(`http://localhost:8080/FreshSip/Order/getCartById/${CartID}`);
        if (!response.ok) {
          throw new Error('Failed to fetch cart');
        }
        const cartData = await response.json();
        setDetail(cartData);

      } catch (error) {
        console.error('Error fetching cart:', error);
      }
    };

    fetchCarts();
  }, [CartID]);

  const Increase = (id) => {
    const Index = cart.findIndex((p) => p.id === id);
    if (Index !== -1 && cart[Index].quantity < 10) {
      dispatch({ type: "Increase", id });
    }
  };

  const Decrease = (id) => {
    const Index = cart.findIndex((p) => p.id === id);
    if (Index !== -1 && cart[Index].quantity > 1) {
      dispatch({ type: "Decrease", id });
    }
  };


  useEffect(() => {
    const updatedProducts = cart.map(item => ({
      id: item.id,
      quantity: item.quantity,
      price: item.price * item.quantity,
      item_name: item.title,
      item_price: item.price,

    }));
    setSelectedProducts(updatedProducts);
  }, [cart]);

  const handleSubmit = async (event) => {
    event.preventDefault();

    const orderData = {
      cart_id: CartID,
      selectedProducts: selectedProducts.filter(product => product.quantity > 0),
    };


    console.log('Submitting Channeling Data:', orderData);

    try {
      OrderService.updateOrder(CartID, orderData)
      alert("Successfully placed order");

      const ordercartData = {
        cart_id: detail.cart_id,
        user_id: detail.id,
        create_date: date,
        create_time: datetime,
        full_total: totalPrice(selectedProducts),
        status: 0,
        u_email: detail.u_email
      }


      console.log('Updated Your Order Data:', ordercartData);
      OrderService.updateCartOrder(CartID, ordercartData)
      navigate(`/CartSuccess/${CartID}/${userID}`);
    } catch (error) {
      console.error('There was an error submitting the order!', error);
      alert("Invalid inputs");
    }
  }

  return (
    <div>
      <div className='heading_a4'>Update Order</div>
      {cart.map((cartItem) => (
        <div className="d-flex border mb-3 body5" key={cartItem.id}>

          <div className="detail ms-4">
            <h4>{cartItem.title}</h4>
            <h5>Rs.{cartItem.price}</h5>
            <div className="buttons">
              <button
                className="rounded-circle px-2"
                onClick={() => Decrease(cartItem.id)}
              >
                <b>-</b>
              </button>
              <button className="rounded">{cartItem.quantity}</button>
              <button
                className="rounded-circle px-2"
                onClick={() => Increase(cartItem.id)}
              >
                <b>+</b>
              </button>
            </div>

          </div>

        </div>
      ))}
      <button className='btn' id='bt5' onClick={handleSubmit}>
        Submit
      </button>
    </div>
  );
}