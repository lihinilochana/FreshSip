import React, { useContext, useState, useEffect } from "react";
import { CartContext } from "../../Features/ContextProvider";
import '../Cart.css';

const CartProduct = ({ product }) => {
    const { cart, dispatch } = useContext(CartContext);
    const [selectedProducts, setSelectedProducts] = useState([]);

   
    useEffect(() => {
        const updatedProducts = cart.map((item) => ({
            id: item.item_id,
            quantity: item.quantity,
            price: item.item_prize * item.quantity, 
            item_name: item.item_name
        }));
        setSelectedProducts(updatedProducts);
    }, [cart]);

    console.log(selectedProducts);

    const Increase = (id) => {
        
        const Index = cart.findIndex((p) => p.item_id === id); 
        if (Index !== -1 && cart[Index].quantity < 10) {
            dispatch({ type: "Increase", id }); 
        }
    };

    const Decrease = (id) => {
        const Index = cart.findIndex((p) => p.item_id === id); 
        if (Index !== -1 && cart[Index].quantity > 1) {
            dispatch({ type: "Decrease", id }); 
        }
    };

    console.log("Cart in CartProduct:", cart);
    const imageSrc = `data:image/jpeg;base64,${product.image}`;
    return (
        <div>
            <div className="d-flex border mb-3">
                
                <img 
                        src={imageSrc} 
                        className="w-25 h-25" 
                        alt={product.item_name || "Product"} 
                        onError={(e) => {
                            e.target.src = "https://via.placeholder.com/150";
                        }}
                    />
                <div className="detail ms-4">
                    <h4>{product.item_name}</h4>
                    <h5>Rs.{product.item_prize}</h5>
                    <div className="buttons">
                        <button className="rounded-circle px-2" onClick={() => Decrease(product.item_id)}>
                            <b>-</b>
                        </button>
                        <button className="rounded">{product.quantity}</button>
                        <button className="rounded-circle px-2" onClick={() => Increase(product.item_id)}>
                            <b>+</b>
                        </button>
                    </div>
                    <button
                        className="btn btn-sm "
                        id="bt14"
                        onClick={() => dispatch({ type: "Remove", id: product.item_id })} 
                    >
                        Remove
                    </button>
                </div>
            </div>
        </div>
    );
};

export default CartProduct;
