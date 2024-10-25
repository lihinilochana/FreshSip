import React, { useContext, useState, useEffect } from "react";
import { CartContext } from "../Features/ContextProvider.js";
import './Cart.css';

const CartProduct = ({ product }) => {
    const { cart, dispatch } = useContext(CartContext);
    const [selectedProducts, setSelectedProducts] = useState([]);


    useEffect(() => {
        const updatedProducts = cart.map((item) => ({
            id: item.id,
            quantity: item.quantity,
            price: item.price * item.quantity
        }))
        setSelectedProducts(updatedProducts)
    }, [cart]);

    const Increase = (id) => {
        const Index = cart.findIndex((p) => p.id === id);
        if (cart[Index].quantity < 10) {
            dispatch({ type: "Increase", id });
        }
    };

    const Decrease = (id) => {
        const Index = cart.findIndex((p) => p.id === id);
        if (cart[Index].quantity > 1) {
            dispatch({ type: "Decrease", id });
        }
    };
    console.log(selectedProducts)
    return (
        <div>
            <div className="d-flex border mb-3">
                <img src={product.thumbnail} className="w-25 h-25" alt="" />
                <div className="detail ms-4">
                    <h4>{product.title}</h4>
                    <h5>${product.price}</h5>
                    <div className="buttons">
                        <button className="rounded-circle px-2" onClick={() => Decrease(product.id)}>
                            <b>-</b>
                        </button>
                        <button className="rounded">{product.quantity}</button>
                        <button className="rounded-circle px-2" onClick={() => Increase(product.id)}>
                            <b>+</b>
                        </button>
                    </div>
                    <button
                        className="btn btn-sm "
                        id='bt14'
                        onClick={() => dispatch({ type: "Remove", id: product.id })}
                    >
                        Remove
                    </button>

                </div>
            </div>

        </div>
    );
};

export default CartProduct;
