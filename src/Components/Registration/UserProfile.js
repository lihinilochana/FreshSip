import React, { useState, useEffect } from "react";
import UserService from "../../Service/UserService";
import { Link } from "react-router-dom";
import Navbar from "../Navbar";
import './FormCss.css'

function UserProfile() {
    const [profileInfo, setProfileInfo] = useState(null); 
    const [loading, setLoading] = useState(true); 
    const [error, setError] = useState(null);

    useEffect(() => {
        
        fetchProfileInfo();
    }, []);

    const fetchProfileInfo = async () => {
        try {
            const token = localStorage.getItem("token");
            const email = localStorage.getItem("email");
            console.log(token)
            console.log(email)
            if (!email || !token) {
                throw new Error("Missing login credentials. Please log in again.");
            }

            const response = await UserService.getUserByEmail(email, token);
            if (response && response.user) {
                setProfileInfo(response.user);
            } else {
                throw new Error("Failed to fetch user profile. Please try again.");
            }
        } catch (err) {
            console.error("Error fetching profile information:", err);
            setError(err.message || "An error occurred.");
        } finally {
            setLoading(false); 
        }
    }

    const handleDeleteClick = (email) => {
        const confirmed = window.confirm("Do you want to delete your account?");
        
        if (confirmed) {
            const token = localStorage.getItem('token');
            UserService.deleteUser(email, token)
                .then(() => {
                    console.log("User deleted successfully");
                    localStorage.clear(); 
                    window.location.href = '/login';

                })
                .catch((error) => {
                    console.error('There was an error deleting the User!', error);
                });
        } else {
            console.log("User is not deleted");
        }
    };
    

    if (loading) {
        return (
            <div className="profilePageContainer">
                <p>Loading profile...</p>
            </div>
        );
    }

  
    if (error) {
        return (
            <div className="profilePageContainer">
                <p className="error">{error}</p>
            </div>
        );
    }

    

    
    return (
        <div>
            <Navbar></Navbar>
        <div className='Body1' style={{margin:'3%'}}>
        <div className='col-sm-8 py-2 px-5 offset-2 shadow' id='Body2'>
            <form class="formRe">
            <h2 className="heading" style={{textAlign: 'center'}}>User Profile</h2><br></br>
        <div>

        <div class="mb-3 row">
                <label for="fName" class="col-sm-3 col-form-label">First Name</label>
                <div class="col-sm-8">
                <input type="text" class="form-control" name="firstName" id="fName" value={profileInfo.firstName}/>
                </div>    
        </div>
      

        <div className='form-group row mb-2' >
                <label htmlFor='impofid' className='col-sm-3 col-form-label'>Last Name</label>
                <div className='col-sm-8'>
                <input className='form-control' type="text" name="lastName" id="lName" value={profileInfo.lastName}/>
                </div>
        </div>

        <div className='form-group row mb-2' >
                <label htmlFor='impofid' className='col-sm-3 col-form-label'>E mail</label>
                <div className='col-sm-8'>
                <input className='form-control' type="text" name="lastName" id="email" value={profileInfo.email} />
                </div>
        </div>

        <div className='form-group row mb-2' >
                <label htmlFor='impofid' className='col-sm-3 col-form-label'>Mobile Number</label>
                <div className='col-sm-8'>
                <input className='form-control' type="tel" name="mobileNumber" value={profileInfo.mobileNumber} />
                </div>
        </div>

        
        <button className="btn" id='btR1' type="submit"><Link to={`/UpdateProfile/${profileInfo.email}`} style={{textDecoration: 'none', color: 'black'}}>Update</Link></button>
        <button className="btn" id='btR2' type="submit" onClick={() => handleDeleteClick(localStorage.getItem('email')) }>Delete</button>
        
    </div>

     </form>
    
     </div>
     </div>
     </div>
    );
}

export default UserProfile;
