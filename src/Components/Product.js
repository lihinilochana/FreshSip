import React, { useContext } from "react";
import { CartContext } from "../Features/ContextProvider.js";
import './Cart.css';

const Product = ({ product }) => {
    const { dispatch } = useContext(CartContext);
    return (
        <div>
            <div className="col">
                <div className="card h-100">
                    <img src={product.thumbnail} className="card-img-top h-75" alt="..." />
                    <div className="card-body">
                        <h4 className="card-title">{product.title}</h4>
                        <h5 className="">Rs.{product.price}</h5>
                        <button
                            className="btn"
                            id='bt15'
                            onClick={() => dispatch({ type: "Add", product: product })}
                        >
                            Add To Cart
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Product;
