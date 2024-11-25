import React, { useContext, useState, useEffect } from "react";
import { CartContext } from '../../Features/ContextProvider.js';
import { useNavigate, useParams } from 'react-router-dom';
import '../Cart.css';
import CartProduct from './CartProduct.js';
import { totalItem, totalPrice } from '../../Features/CartUtils.js';
import OrderService from "../../Service/OrderService.js";

export default function CartUpdate() {
  const { CartID, userID } = useParams();
  const navigate = useNavigate();
  const { cart, dispatch } = useContext(CartContext);
  const [carts, setCarts] = useState([]);
  const [detail, setDetail] = useState([]);
  const [selectedProducts, setSelectedProducts] = useState([]);
  const [datetime, setDatetime] = useState(new Date().toISOString().slice(0, 16));
  const [date, setDate] = useState(new Date().toISOString().split('T')[0]);


  useEffect(() => {
    const fetchCart = async () => {
      try {
        const response = await fetch(`http://localhost:8080/FreshSip/Order/adminUser/OrderItemsByOrderItems/${CartID}`);
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

  console.log("T1",carts)

  useEffect(() => {
    const fetchCarts = async () => {
      try {
        const response = await fetch(`http://localhost:8080/FreshSip/Order/adminUser/CartById/${CartID}`);
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
console.log("T2",detail)

const Increase = (id) => {
  const Index = cart.findIndex((p) => p.item_id === id);
  if (Index !== -1) {
    dispatch({ type: "Increase", id });
  } else {
    console.error("Item not found in cart:", id);
  }
};

const Decrease = (id) => {
  const Index = cart.findIndex((p) => p.item_id === id);
  if (Index !== -1 && cart[Index].quantity > 1) {
    dispatch({ type: "Decrease", id });
  } else if (Index === -1) {
    console.error("Item not found in cart:", id);
  }
};



  useEffect(() => {
    const updatedProducts = cart.map(item => ({
      id: item.item_id,
      quantity: item.quantity,
      price: item.item_prize * item.quantity,
      item_name: item.item_name,
      item_price: item.item_prize,

    }));
    setSelectedProducts(updatedProducts);
  }, [cart]);
console.log("Test1",selectedProducts)
  const handleSubmit = async (event) => {
    event.preventDefault();

    const orderData = {
      cart_id: CartID,
      selectedProducts: selectedProducts.filter(product => product.quantity > 0),
    };


    console.log('Submitting Channeling Data:', orderData);

    try {
      OrderService.updateOrder(CartID, orderData)
      alert("Successfully Updated the order");

      const ordercartData = {
        cart_id: detail.cart_id,
        userId: detail.userId,
        create_date: date,
        create_time: datetime,
        full_total: totalPrice(cart),
        status: 0,
        u_email: detail.u_email
      }


      console.log('Updated Your Order Data:', ordercartData);
      OrderService.updateCartOrder(CartID, ordercartData)
      navigate(`/CartSuccess/${CartID}/${userID}`);
    } catch (error) {
      console.error('There was an error updatting the order!', error);
      alert("Invalid inputs");
    }
  }
console.log("T4",cart)
  return (
    <div>
      <div className='heading_a4'>Update Order</div>
      {cart.map((cartItem) => (
        <div className="d-flex border mb-3 body5" key={cartItem.item_id}>

          <div className="detail ms-4">
            <h4>{cartItem.item_name}</h4>
            <h5>Rs.{cartItem.item_prize}</h5>
            <div className="buttons">
              <button
                className="rounded-circle px-2"
                onClick={() => Decrease(cartItem.item_id)}
              >
                <b>-</b>
              </button>
              <button className="rounded">{cartItem.quantity}</button>
              <button
                className="rounded-circle px-2"
                onClick={() => Increase(cartItem.item_id)}
              >
                <b>+</b>
              </button>
            </div>

          </div>

        </div>
      ))}
       <div  className="top-right-corner">
            <div className="text-black" id='body5' >
              <h5>Total Items: {totalItem(cart)}</h5>
              <h5>Total Price: Rs.{totalPrice(cart)}</h5>
              
              <button className='btn' id='bt8' onClick={handleSubmit}>
        Submit
      </button>
            </div>
          </div>
     
    </div>
  );
}