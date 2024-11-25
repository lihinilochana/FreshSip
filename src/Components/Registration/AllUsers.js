import UserService from "../../Service/UserService";
import React,{useState, useEffect} from "react";

function GetAllUsers() {
    const [userInfo, setUserInfo] = useState([]); 
    const [error, setError] = useState();

    useEffect(() => {
        
        fetchUserInfo();
    }, []);

    const fetchUserInfo = async () => {
        try {
            const token = localStorage.getItem("token");
            if (!token) {
                throw new Error("Missing login credentials. Please log in again.");
            }
    
            const response = await UserService.getAllUsers(token);
            console.log("API Response:", response);
    
            if (response?.userList && Array.isArray(response.userList)) {
                setUserInfo(response.userList); 
            } else {
                throw new Error("Unexpected API response structure.");
            }
        } catch (err) {
            console.error("Error fetching user information:", err);
            setError(err.message || "An error occurred.");
        }
    };

    return(
        <div className="table" style={{margin:'3%'}}>
            {error && <p style={{ color: "red" }}>{error}</p>}
            <h2 style={{textAlign:'center', marginBottom: '3%'}}>User Details</h2>
            <table className="table">
                <thead className="thead-dark">
                <tr>
                        <th scope="col">User ID</th>
                        <th scope="col">First Name</th>
                        <th scope="col">Last Name</th>
                        <th scope="col">DOB</th>
                        <th scope="col">NIC</th>
                        <th scope="col">Email</th>
                        <th scope="col">Mobile Number</th>
                        <th scope="col">User Type</th>
                    </tr>
                </thead>

                <tbody>
                {userInfo && userInfo.length > 0 ? (
                    userInfo.map((user, index) => (
                        <tr key={user.userId || index}>
                            <th scope="row">{user.userId}</th>
                                <td>{user.firstName}</td>
                                <td>{user.lastName}</td>
                                <td>{user.dob}</td>
                                <td>{user.nic}</td>
                                <td>{user.email}</td>
                                <td>{user.mobileNumber}</td>
                                <td>{user.userType}</td>
                        </tr>
                    ))
                    ) : (
                        <tr>
                            <td colSpan="8" style={{ textAlign: "center" }}>
                                No users found.
                            </td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
}

export default GetAllUsers;