import React from "react";
import { useState } from "react";
import UserService from "../../Service/UserService";
import { useNavigate, Link } from "react-router-dom";

function UserLogin () {

    const[email, setEmail] = useState('');
    const[password, setPassword] = useState('');
    const[error, setError] = useState('');

    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault(); // Prevent the default form submission
        console.log('Form submitted'); // Add this line for debugging

        try {
            
            const UserData = await UserService.login(email, password);
            console.log(UserData); // Log the response from the login API
    
            if (UserData.token) {
                
                localStorage.setItem('token', UserData.token);
                localStorage.setItem('userType', UserData.userType);
                localStorage.setItem('email', UserData.email);
                console.log(UserData.email)
                alert("Login successful!");
                console.log("type",UserData.userType)
                if(UserData.userType==="USER"){
                    navigate(`/${email}`);
                   
                }
                else{navigate("/OrderAdminPage")}
            }

            else{
                alert("Inavlid Login")
                setError(UserData.error)
            }console.log(UserData)
        }
        catch(error){
            console.log(error)
            setError(error.message)
            setTimeout(()=>{
                setError('');
            }, 5000)
        }
        
    }
        

return(
<section className="vh-100" style={{ backgroundColor: "white" }}>
        <div className="container py-5 h-100">
            <div className="row d-flex justify-content-center align-items-center h-100">
                <div className="col col-xl-10">
                    <div className="card" style={{ borderRadius: '1rem', margin: '3% 10% 10% 10%' }}>
                        <div className="row g-0">
                            <div className="col-md-6 col-lg-5 d-none d-md-block">
                            <img src='/juice4.jpg' className="img-fluid" style={{ borderRadius: '1rem 0rem 0rem 1rem', borderColor: 'black' }} />
                            </div>

                        <div className="col-md-6 col-lg-7 d-flex align-items-center" >
                        <div className="card-body p-4 p-lg-5 text-black">

                        <form onSubmit={handleSubmit}>

                        <div className="d-flex align-items-center mb-3 pb-1">
                            <h1>FreshSip</h1>
                        </div>

                        <h5 className="fw-normal mb-3 pb-3" style={{ letterSpacing: '1px' }}>Login to your account</h5>

                        <div data-mdb-input-init className="form-outline mb-4">
                            <input type="text" id="form2Example17" className="form-control form-control-lg" placeholder="Email" value={email} onChange={e => setEmail(e.target.value)}/>
                        </div>

                        <div data-mdb-input-init className="form-outline mb-4">
                            <input type="password" id="form2Example27" className="form-control form-control-lg" placeholder="Password" value={password} onChange={e => setPassword(e.target.value)}/>
                        </div>

                        <div className="small text-muted" style={{ textAlign: 'right' }}>
                            <Link to={"/UserRegistration"}>Register Now</Link>
                        </div>

                        <div className="pt-1 mb-4" style={{marginTop:'5%'}}>
                            <button data-mdb-button-init data-mdb-ripple-init className="btn btn-dark btn-lg btn-block" id='btRe' type="submit" style={{width:'40%', alignContent: 'right'}}>Login</button>
                        </div>

                        </form>

                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </section>
);
}

export default UserLogin;