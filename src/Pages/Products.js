import React, { useState } from 'react';
import Data from '../Data.json';
import Product from '../Components/Product.js';
import home from '../Components/Pictures/imges5.png';
import '../Components/Cart.css';
import Navbar from '../Components/Navbar.js';
import Footer from '../Components/Footer.js';

const Products = () => {
    const [products, setProducts] = useState(Data.products);
    return (
        <div>
            <div>
                <img className="home-img" src={home} alt="home" />
                <Navbar></Navbar>
                <p className='heading_a1'>FreshSip</p>
                <div className='set1'><button className='btn' id='bt20'>Log In</button> <button className='btn' id='bt21'>Log Out</button></div>
                <h3 className='heading_a8'>Juice Items</h3>
            </div>
            <div className="container mt-5">
                <div className='row row-cols-1 row-cols-md-5 g-4'>
                    {
                        products.map(p => (
                            <Product key={p.id} product={p} />
                        ))
                    }
                </div>
            </div>
            <Footer></Footer>
        </div>
    );
}

export default Products;
