import { useEffect, useState } from "react";
import UserService from "../../Service/UserService";
import { useNavigate, useParams } from "react-router-dom";

function UpdateProfile(){ 
    const navigate = useNavigate();
    const { email: emailFromParams } = useParams();
    const email = emailFromParams || localStorage.getItem('email');
    console.log("Email from useParams:", email);

    const[UserData, setUserData] = useState({
        firstName: '',
        lastName: '',
        mobileNumber: ''
    });

    useEffect(() => {
        fetchUserDataByEmail(email);
    },[email]);

    const fetchUserDataByEmail = async (email) => {
        try{
            const token = localStorage.getItem('token');
            const response = await UserService.getUserByEmail(email, token);
            console.log('API Response:', response); // Debugging log
            if (response?.user) {
                const { firstName, lastName, mobileNumber} = response.user;
                setUserData({ firstName, lastName, mobileNumber });
            } else {
                console.error('User not found:', response.message || 'No additional details.');
                alert('User not found. Please check the email and try again.');
                navigate('/Profile'); // Redirect to profile or a relevant page
            }
        }

        catch(error){
            console.error('Error fetching user data: ', error);
        }
    };

    const handleInputChange = (event) => {
        const {name, value} = event.target;
        setUserData ((previousUserData) => ({
            ...previousUserData,
            [name]: value
        }));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        try{
            const token = localStorage.getItem('token');
            await UserService.updateUser(email, UserData, token);
            alert("You have successfully update your details")
            navigate(`/Profile/${email}`)
        }
        catch(error){
            console.error('Error updating user: ', error);
        }
    };

    return(
        <div className='Body1' style={{margin:'3%'}}>
        <div className='col-sm-8 py-2 px-5 offset-2 shadow' id='Body2'>
            <form onSubmit={handleSubmit} class="formRe">
            <h2 className="heading" style={{textAlign: 'center'}}>Update Profile</h2><br></br>
        <div>

        <div class="mb-3 row">
                <label for="fName" class="col-sm-3 col-form-label">First Name</label>
                <div class="col-sm-8">
                <input type="text" class="form-control" name="firstName" id="fName" value={UserData.firstName} onChange={handleInputChange} required/>
                </div>    
        </div>
      

        <div className='form-group row mb-2' >
                <label htmlFor='impofid' className='col-sm-3 col-form-label'>Last Name</label>
                <div className='col-sm-8'>
                <input className='form-control' type="text" name="lastName" id="lName" value={UserData.lastName} onChange={handleInputChange} required/>
                </div>
        </div>

        <div className='form-group row mb-2' >
                <label htmlFor='impofid' className='col-sm-3 col-form-label'>Mobile Number</label>
                <div className='col-sm-8'>
                <input className='form-control' type="tel" name="mobileNumber" value={UserData.mobileNumber} onChange={handleInputChange} />
                </div>
        </div>

        
        <button className="btn" id='bt4' type="submit">Update</button>
        
</div>

     </form>
    
     </div>
     </div>
    );
}

export default UpdateProfile;