import React, { useContext } from "react";
import { CartContext } from "../../Features/ContextProvider";
import '../Cart.css';


const Product = ({ product }) => {
    const { dispatch } = useContext(CartContext);

    const imageSrc = `data:image/jpeg;base64,${product.image}`;

    return (
        <div>
            <div className="col">
                <div className="card h-100">
                
                    <img 
                        src={imageSrc} 
                        className="card-img-top " 
                        id="imaP1"
                        alt={product.item_name || "Product"} 
                        onError={(e) => {
                            e.target.src = "https://via.placeholder.com/150";
                        }}
                    />
                    <div className="card-body">
                        <h4 className="card-title">{product.item_name}</h4>
                        <h5 className="">Rs.{product.item_prize}</h5>
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
