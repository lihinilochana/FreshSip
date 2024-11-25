import React, { useContext, useState, useEffect } from "react";
import { BsCart } from "react-icons/bs";
import { Link } from "react-router-dom";
import { CartContext } from "../Features/ContextProvider.js";
import './Cart.css'
import { useParams, useNavigate } from 'react-router-dom';
import OrderService from '../Service/OrderService.js';

const Navbar = () => {
  const {email}=useParams();
  const navigate = useNavigate();
  const { cart } = useContext(CartContext);
  const [user, setUser] = useState('');

console.log(email)
  useEffect(() => {
    const fetchCart = async () => {
      try {
        const response = await fetch(`http://localhost:8080/FreshSip/adminUser/${email}`);
        if (!response.ok) {
          throw new Error('Failed to fetch cart');
        }
        const userData = await response.json();
        setUser(userData);

      } catch (error) {
        console.error('Error fetching cart:', error);
      }
    };

    fetchCart();
  }, [email]);
  console.log(user)

  const handleSubmit = async (event) => {
    event.preventDefault();

    const currenttime = new Date().toISOString().slice(0, 16);
    const currentdate = new Date().toISOString().split('T')[0];

    const cartData = {
      id: user.user_id,
      create_date: currentdate,
      create_time: currenttime,
      full_total: 0.00,
      status: 0,
      u_email: email
    };
    console.log('Submitting Channeling Data:', cartData);

    OrderService.submitCart(cartData)
      .then(() => {
        navigate(`/cart/${email}`);
        console.log(cartData);
      })
      .catch((error) => {
        console.error('There was an error submitting the order!', error);
        
      });
  };

  return (
    <div className="d-flex justify-content-between py-3 px-5  text-black nav1">
      <Link to="/" className="navbar-brand fs-4 fw-bolder">FreshSip</Link>
      <div className="d-flex ">
        <Link to={`/${email}`} className="navbar-link fs-5 text-black text-decoration-none ms-3"  >Home</Link>
        <Link to={`/About/${email}`} className="navbar-link fs-5 text-black text-decoration-none ms-3" >About Us</Link>
        <Link to={`/Reviewuser/${email}`} className="navbar-link fs-5 text-black text-decoration-none ms-3" >Review</Link>
        <Link to={`/Profile/${email}`} className="navbar-link fs-5 text-black text-decoration-none ms-3" >Profile</Link>
        <Link className="navbar-link fs-5 text-black text-decoration-none ms-3" onClick={handleSubmit}>
          <BsCart />{cart.length}
        </Link>
      </div>
    </div>
  );
};

export default Navbar;
