import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { FaEdit, FaTrash } from 'react-icons/fa';
import { Link } from 'react-router-dom';
import '../Cart.css';
import { useNavigate, useParams } from 'react-router-dom';

const ItemTable = () => {
    const navigate = useNavigate();
    const [items, setItem] = useState([]);

    useEffect(() => {
        loadItems();
    }, []);

    const loadItems = async () => {
        const result = await axios.get("http://localhost:8080/FreshSip/ItemStock/admin/ITEM", {
            validateStatus: () => {
                return true;
            },
        });
        if (result.status === 200) {
            setItem(result.data);
        }
    };

    const handleDelete = async (id) => {
        await axios.delete(`http://localhost:8080/FreshSip/ItemStock/admin/items/${id}`);
        loadItems();
        navigate("/AdminItem")
    };
    

    return (
        <section>
            
            
            <h1 className='headinfItem1'>Item Stock</h1>
            <Link to={"/AddItem"} className="btn " id='bti2' type="add">AddItem</Link>
            <table className='table table-bordered table-hover shadow'>
                <thead>
                    <tr className="ItemTableHeader">
                        <th>Item Id</th>
                        <th>Name</th>
                        <th>Image</th>
                        <th>Description</th>
                        <th>Price</th>
                        
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    {items.map((item) => (
                        <tr key={item.item_id}>
                            <th scope='row'>{item.item_id}</th>
                            <td>{item.item_name}</td>
                            <td><img src={`data:image/jpeg;base64,${item.image}`} alt={item.name} style={{ maxWidth: '100px' }} /></td>
                            <td>{item.description}</td>
                            <td>{item.item_prize}</td>
                            
                            <td>
                                <Link to={`/EditItems/${item.item_id}`} className="ItemBtnEdit"  ><FaEdit /></Link>
                            </td>
                            <td>
                                <Link className="ItemBtnDelete" onClick={() => handleDelete(item.item_id)}><FaTrash /></Link>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </section>
    );
}

export default ItemTable;
