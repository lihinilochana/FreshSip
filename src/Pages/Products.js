import React, { useState,useEffect } from 'react';
import Product from '../Components/Order/Product.js';
import home from '../Components/Pictures/image6.png';
import '../Components/Cart.css';
import Navbar from '../Components/Navbar.js';
import Footer from '../Components/Footer.js';
import { useNavigate, useParams } from 'react-router-dom';
import UserService from '../Service/UserService.js';

const Products = () => {
  const{email}=useParams();
  const navigate = useNavigate();
    const [product, setProduct] = useState([]);

    useEffect(() => {
        const fetchProducts = async () => {
          try {
            const response = await fetch("http://localhost:8080/FreshSip/ItemStock/admin/ITEM");
            if (!response.ok) {
              throw new Error('Failed to fetch product');
            }
            const productData = await response.json();
            setProduct(productData);
    
          } catch (error) {
            console.error('Error fetching Product:', error);
          }
        };
    
        fetchProducts();
      }, []);

      const handleLogin = () => navigate(`/Login`);
     


      const isAuthenticated = UserService.isAuthenticated();

      const handleLogOut = () => {
	    const confirmLogout = window.confirm("Are you sure you want to logout?");
	      if(confirmLogout){
		      UserService.logout();
          navigate('/')
	      }
      };





      console.log(product)
    return (
        <div>
            <div>
                <img className="home-img" src={home} alt="home" />
                <Navbar></Navbar>
                <p className='heading_a1'>FreshSip</p>
                <div className='"set1'>
                {email ? ( 
                        <button className="btn" id="bt21" onClick={handleLogOut}>
                            Log Out
                        </button>
                    ) : (
                        <button className="btn" id="bt20" onClick={handleLogin}>
                            Log In
                        </button>
                    )}
            </div>
            <div className="container mt-5">
                <div className='row row-cols-1 row-cols-md-5 g-4'>
                    {
                        product.map(p => (
                            <Product key={p.id} product={p} />
                        ))
                    }
                </div>
            </div>
            <Footer></Footer>
        </div>
        </div>
    );
}

export default Products;
