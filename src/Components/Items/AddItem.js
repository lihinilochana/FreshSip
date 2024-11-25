import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

const AddItem = () => {

    let navigate = useNavigate();

    const [Item, setItems] = useState({
        item_id:'',
        item_name: '',
        description: '',
        item_prize: '',
        image: null 
    });

    const handleInputChange = (e) => {
        setItems({...Item, [e.target.name]: e.target.value});
    }

    const handleFileChange = (e) => {
        setItems({...Item, image: e.target.files[0]});
    }

    const saveItem = async (e) => {
        e.preventDefault(); 

       
        const formData = new FormData();
        formData.append('item_id', Item.item_id);
        formData.append('item_name', Item.item_name);
        formData.append('description', Item.description);
        formData.append('item_prize', Item.item_prize);
        formData.append('quantity', 1);
        formData.append('image', Item.image);
        
        try {
            const response = await axios.post("http://localhost:8080/FreshSip/ItemStock/admin/ItemS", formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });
            console.log(response)
            console.log(response.data); 
        } catch (error) {
            console.error('Error saving item:', error);
        }
        navigate("/AdminItem")
    }

    const { item_id,item_name, description, item_prize } = Item;
    console.log(Item)

    return (
        <div className='col-sm-8 py-2 px-5 offset-2 shadow'>
            <form onSubmit={saveItem}>

                <h1>Item Registration</h1>
                <div className='input-group mb-5'>
                    <label className='input-group-text' htmlFor='itemid'>Item Id</label>
                    <input className='form-control col-sm-6' type='text' name='item_id' id='itemid' required value={item_id} onChange={handleInputChange}/>
                </div>

                <div className='input-group mb-5'>
                    <label className='input-group-text' htmlFor='name'>Name</label>
                    <input className='form-control col-sm-6' type='text' name='item_name' id='name' required value={item_name} onChange={handleInputChange}/>
                </div>
                <div className='input-group mb-5'>
                    <label className='input-group-text' htmlFor='image'>Image</label>
                    <input className='form-control col-sm-6' type='file' name='image' id='image' onChange={handleFileChange}/>
                </div>
                <div className='input-group mb-5'>     
                    
                    <div className='form-control col-sm-6'>
                    
                  </div>
                </div>
                <div className='input-group mb-5'>
                    <label className='input-group-text' htmlFor='description'>Description</label>
                    <input className='form-control col-sm-6' type='text' name='description' id='description' value={description} onChange={handleInputChange}/>
                </div>

                <div className='input-group mb-5'>     
                    <label className='input-group-text' htmlFor='price'>Price</label>
                    <input className='form-control col-sm-6' type='text' name='item_prize' id='price' required value={item_prize} onChange={handleInputChange}/>
                </div>
              
                <div className='row mb-5'>
                    <div className='col-sm-2'>
                        <button type='submit' className='btn btn-outline-success btn-lg'>Save</button>
                    </div>
    
                    <div className='col-sm-5'>
                        <Link to='/AdminItem' className='btn btn-outline-warning btn-lg'>Cancel</Link>
                    </div>
                </div>
                
            </form> 
        </div>
    );
}

export default AddItem;
