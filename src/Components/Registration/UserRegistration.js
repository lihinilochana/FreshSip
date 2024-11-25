import { useState } from "react";
import React from "react";
import './FormCss.css'
import { useNavigate } from "react-router-dom";
import UserService from "../../Service/UserService";

function UserRegistration(){

    const navigate = useNavigate();
    const[formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        nic: '',
        dob: '',
        mobileNumber: '',
        email: '',
        userType: 'USER',
        password: '',
        confirmPassword: '',
    }) 

    const handleInputChange = (e) => {
        const {name, value} = e.target;
        setFormData({...formData, [name]: value});
    }

    const handleSubmit = async (event) => {
        event.preventDefault();

        if (formData.password !== formData.confirmPassword) {
                alert("Passwords do not match!");
                return;
        }

        console.log(formData);
        
        try{
                await UserService.register(formData);

                setFormData({
                        firstName: '',
                        lastName: '',
                        nic: '',
                        dob: '',
                        mobileNumber: '',
                        email: '',
                        userType: '',
                        password: '',
                        confirmPassword: '',
                });

                alert("User Registered Successfully");
                navigate('/Login');
        }

        catch(error){
                console.error('Error Registering User: ', error);
                alert("An Error Occurred While Registering User");
        }
};
        
    

return (
        <div className='Body1' style={{margin:'3%'}}>
        <div className='col-sm-8 py-2 px-5 offset-2 shadow' id='Body2'>
            <form onSubmit={handleSubmit} class="formRe">
            <h2 className="heading" style={{textAlign: 'center'}}>User Registration Form</h2><br></br>
        <div>

        <div class="mb-3 row">
                <label for="fName" class="col-sm-3 col-form-label">First Name</label>
                <div class="col-sm-8">
                <input type="text" class="form-control" name="firstName" id="fName" value={formData.firstName} onChange={handleInputChange} required/>
                </div>    
        </div>
      

        <div className='form-group row mb-2' >
                <label htmlFor='impofid' className='col-sm-3 col-form-label'>Last Name</label>
                <div className='col-sm-8'>
                <input className='form-control' type="text" name="lastName" id="lName" value={formData.lastName} onChange={handleInputChange} required/>
                </div>
        </div>
        
        <div className='form-group row mb-2' >
                <label htmlFor='impofid' className='col-sm-3 col-form-label'>NIC</label>
                <div className='col-sm-8'>
                <input className='form-control' type="text" name="nic" value={formData.nic} onChange={handleInputChange} required/>
                </div>

        </div>

        <div className='form-group row mb-2' >
                <label htmlFor='impofid' className='col-sm-3 col-form-label'>Date of Birth</label>
                <div className='col-sm-8'>
                <input className='form-control' type="date" name="dob" value={formData.dob} onChange={handleInputChange} />
                </div>
        </div>

        <div className='form-group row mb-2' >
                <label htmlFor='impofid' className='col-sm-3 col-form-label'>Email</label>
                <div className='col-sm-8'>
                <input className='form-control' type="text" name="email" value={formData.email} onChange={handleInputChange} required/>
                </div>
        </div>

        <div className='form-group row mb-2' >
                <label htmlFor='impofid' className='col-sm-3 col-form-label'>Password</label>
                <div className='col-sm-8'>
                <input className='form-control' type="password" name="password" value={formData.password} onChange={handleInputChange} pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" 
                title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required/>
                </div>
        </div>
        

        <div className='form-group row mb-2' >
                <label htmlFor='impofid' className='col-sm-3 col-form-label'>Confirm Password</label>
                <div className='col-sm-8'>
                <input className='form-control' type="password" name="confirmPassword" value={formData.confirmPassword} onChange={handleInputChange} pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" 
                title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required/>
                </div>
        </div>

        <div className='form-group row mb-2' >
                <label htmlFor='impofid' className='col-sm-3 col-form-label'>Mobile Number</label>
                <div className='col-sm-8'>
                <input className='form-control' type="tel" name="mobileNo" value={formData.mobileNo} onChange={handleInputChange} />
                </div>
        </div>

        <div className='form-group row mb-2' >
                <label htmlFor='impofid' className='col-sm-3 col-form-label'>User Type</label>
                <div className='col-sm-8'>
                <select className='form-select'  name="userType" value={formData.userType} onChange={handleInputChange}>
                        <option value="USER">USER</option>
                </select>
                </div>
        </div>
        
        <button className="btn" id='bt4' type="submit">Register</button>
        
</div>

     </form>
    
     </div>
     </div>
     
  );
}

export default UserRegistration;
