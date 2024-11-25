import React from 'react'
import { Link } from 'react-router-dom';
import './Cart.css'
import UserService from '../Service/UserService';
import { useNavigate, useParams } from 'react-router-dom';

const AdminNav = () => {
    const navigate = useNavigate();
    const isAuthenticated = UserService.isAuthenticated();

      const handleLogOut = () => {
	    const confirmLogout = window.confirm("Are you sure you want to logout?");
	      if(confirmLogout){
		      UserService.logout();
          navigate('/')
	      }
      };
    return (
        <nav className="navbar navbar-expand-lg bg-body-secondary">
            <div className="container-fluid">
                <a className="navbar-brand">
                    FreshSip
                </a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                        <li className="nav-item">
                            <Link className="nav-link active" aria-current="page" to={"/OrderAdminPage"}>Order Stock</Link>
                        </li>

                        <li className="nav-item">
                            <Link className="nav-link active" aria-current="page" to={"/AdminItem"}>Item Stock</Link>
                        </li>

                        <li className="nav-item">
                            <Link className="nav-link active" aria-current="page" to={"/billrecords"}>Billing</Link>
                        </li>

                        <li className="nav-item">
                            <Link className="nav-link active" aria-current="page" to={"/AdminRegistration"}>Admin Registration</Link>
                        </li>

                        <li className="nav-item">
                            <button className="nav-link active" style={{fontWeight:'650'}} aria-current="page" onClick={handleLogOut}>Logout</button>
                        </li>

                    </ul>


                </div>

            </div>
        </nav>

    )
}

export default AdminNav;
