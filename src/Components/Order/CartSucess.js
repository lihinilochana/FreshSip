import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import OrderService from '../../Service/OrderService';
import '../Cart.css';



export default function CartSucess() {
    const navigate = useNavigate();
    const { CartID, userID } = useParams();
    const [channelingId, setChannelingId] = useState(null);
    const [isDisabled, setIsDisabled] = useState(false);


    useEffect(() => {
        const timer = setTimeout(() => {
            setIsDisabled(true);
        }, 80000);
        return () => clearTimeout(timer);
    }, []);

    const handleEditClick = () => navigate(`/CartUpdated/${CartID}/${userID}`);

    const handleDeleteClick = (CartID) => {
        const confirmed = window.confirm("Do you want to delete?");
        if (confirmed) {
            OrderService.deleteOrderById(CartID)
                .then(() => {
                    console.log("Order deleted");
                    navigate(`/`);

                })
                .catch((error) => {
                    console.error('There was an error deleting the Order!', error);
                });
        } else {
            console.log("Order is not deleted");
        }
    };

    const handleDoneClick = () => {
        navigate(`/OrderTokens/${CartID}/${userID}`);

    };

    return (
        <div >
            <div class="card" id='body1'>
                <div class="card-body">
                    <p className='heading_a1'>Ordering</p>
                    <p className="heading_s2">You have submitted your Order Successfully!</p>

                    <div class="d-grid gap-2 col-6 mx-auto">
                        <button onClick={handleEditClick} className="btn" id='bt31' type="button" disabled={isDisabled}>Edit Order</button>
                        <button onClick={() => handleDeleteClick(CartID)} className="btn" id='bt2' type="button" disabled={isDisabled}>Delete Order</button>
                        <button onClick={() => handleDoneClick(CartID, userID)} className="btn" id='bt3' type="button">Done Order</button>
                    </div>
                </div>
            </div>
        </div>
    );
}
