import React, { useContext, useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { CartContext } from '../../Features/ContextProvider.js';
import CartProduct from '../../Components/Order/CartProduct.js';
import { totalItem, totalPrice } from '../../Features/CartUtils.js';
import OrderService from '../../Service/OrderService.js';
import '../../Components/Cart.css';


const Cart = () => {
  const {email }= useParams();
  const navigate = useNavigate();
  const { cart } = useContext(CartContext);
  const [selectedProducts, setSelectedProducts] = useState([]);
  const [carts, setCarts] = useState('');
  const [datetime, setDatetime] = useState(new Date().toISOString().slice(0, 16));
  const [date, setDate] = useState(new Date().toISOString().split('T')[0]);

  useEffect(() => {
    const fetchCart = async () => {
      try {
        const response = await fetch(`http://localhost:8080/FreshSip/Order/adminUser/${email}`);
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
  }, []);

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
  console.log(selectedProducts)
  const handleSubmit = async (event) => {
    event.preventDefault();

    const orderData = {
      cart_id: carts.cart_id,
      selectedProducts: selectedProducts.filter(product => product.quantity > 0),
    };


    //console.log('Submitting Order Data:', orderData);

    try {
      OrderService.submitOrder(orderData)
      alert("Successfully placed order");

      const ordercartData = {
        cart_id: carts.cart_id,
        userId: carts.userId,
        create_date: date,
        create_time: datetime,
        full_total: totalPrice(cart),
        status: 0,
        u_email: carts.u_email
      }

      console.log('Submitting Channeling Data:', ordercartData);
      OrderService.updateCartOrder(carts.cart_id, ordercartData)
      navigate(`/CartSuccess/${carts.cart_id}/${email}`);
    } catch (error) {
      console.error('There was an error submitting the order!', error);
      alert("Invalid inputs");
    }
  }


  return (
    <div>
      <h1 className='heading_a1'>Your Order</h1>
      <div className='container mt-3'>
        <div className="row">
          <div className="col-8">
            {cart.map(p => (
              <CartProduct key={p.id} product={p}
                selectedProducts={selectedProducts}
                setSelectedProducts={setSelectedProducts} />
            ))}
          </div>
          <div className="col-4">
            <div className="text-black" id='body5' >
              <h5>Total Items: {totalItem(cart)}</h5>
              <h5>Total Price: Rs.{totalPrice(cart)}</h5>
              <button className='btn' id='bt8' onClick={handleSubmit}>Checkout</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Cart;
