import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useNavigate, useParams } from 'react-router-dom';

const EditItem = () => {
    const{Id} =useParams();
    let navigate = useNavigate();
    
    const [item, setItem] = useState({
        item_id: '',
        item_name: '',
        description: '',
        item_prize: '',
        quantity:'',
        type:'',
        image: null 
    });

    const loadItem = async () => {
        
            const response = await axios.get(`http://localhost:8080/FreshSip/ItemStock/admin/ITEMItemById/${Id}`);
            console.log('Item loaded:', response.data); 
            setItem(response.data); 
        
    };

    console.log(item)
    useEffect(() => {
        
        if (Id) {
            loadItem();
        } else {
            console.error('No ID provided in the URL');
        }
        
    }, [Id]);

    
    const handleInputChange = (Id) => {
        setItem({ ...item, [Id.target.name]: Id.target.value });
    };

   
    

  
    const updateItem = async (event) => {
        event.preventDefault(); 

       
        const formData = new FormData();
        formData.append('item_id', item_id);
        formData.append('item_name', item_name);
        formData.append('description', description);
        formData.append('item_prize',item_prize);
        formData.append('quantity',1);
       

        
            const response = await axios.put(`http://localhost:8080/FreshSip/ItemStock/admin/item/${Id}`, formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });
            console.log('Item updated:', response.data);
            navigate("/AdminItem");
       
        
    };
    const { item_id,item_name, description, item_prize} = item;

    return (
        <div className='col-sm-8 py-2 px-5 offset-2 shadow'>
            <form onSubmit={updateItem}>
                <h1>Edit Item</h1>
                <div className='input-group mb-5'>
                    <label className='input-group-text' htmlFor='name'>Name</label>
                    <input className='form-control col-sm-6' type='text' name='item_name' id='name' defaultValue={item.item_name} onChange={handleInputChange} />
                </div>
                
                <div className='input-group mb-5'>
                    <label className='input-group-text' htmlFor='description'>Description</label>
                    <input className='form-control col-sm-6' type='text' name='description' id='description' defaultValue={item.description} onChange={handleInputChange} />
                </div>
                <div className='input-group mb-5'>
                    <label className='input-group-text' htmlFor='price'>Price</label>
                    <input className='form-control col-sm-6' type='text' name='item_prize' id='price' defaultValue={item.item_prize} onChange={handleInputChange} />
                </div>
               

                <div className='row mb-5'>
                    <div className='col-sm-2'>
                        <button type='submit' className='btn btn-outline-success btn-lg'>Save</button>
                    </div>
    
                    <div className='col-sm-5'>
                        <Link to='/ItemTable' className='btn btn-outline-warning btn-lg'>Cancel</Link>
                    </div>
                </div>
            </form>
        </div>
    );
};

export default EditItem;
