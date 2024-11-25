import axios from "axios";

class UserService{
    static BASE_URL = "http://localhost:8080/FreshSip"

    static async login(email, password){
        try{
            const response = await axios.post(`${UserService.BASE_URL}/adminUser/login`, {email, password})
            return response.data ;
        }

        catch(err){
            throw err;
        }
    }

    static async register(formData){
        try{
            const response = await axios.post(`${UserService.BASE_URL}/adminUser/user`, formData,
                {
                    headers: {
                        "Content-Type": "application/json"
                    },
                })
                return response.data;
        }

        catch(err){
            throw err;
        }
    }

    static async getAllUsers(token){
        try{
            const response = await axios.get(`${UserService.BASE_URL}/admin/users`,  
                {
                    headers: { Authorization: `Bearer ${token}` },
                     "Content-Type": "application/json"
                })
                return response.data;
        }

        catch(err){
            throw err;
        }
    }

    static async getUserByEmail(email, token){
        try{
            const response = await axios.get(`${UserService.BASE_URL}/adminUser/${email}`, 
                {
                    headers: { Authorization: `Bearer ${token}` },
                })
                return response.data;
        }

        catch(err){
            throw err;
        }
    }

    static async deleteUser(email, token){
        try{
            const response = await axios.delete(`${UserService.BASE_URL}/adminUser/${email}`, 
                {
                    headers: { Authorization: `Bearer ${token}` },
                })
                return response.data;
        }

        catch(err){
            throw err;
        }
    }

    static async updateUser(email, UserData, token){
        try{
            const response = await axios.put(`${UserService.BASE_URL}/adminUser/${email}`, UserData, 
                {
                    headers: { Authorization: `Bearer ${token}` },
                })
                return response.data;
        }

        catch(err){
            throw err;
        }
    }

    static logout(){
        localStorage.removeItem('token')
        localStorage.removeItem('userType')
        localStorage.removeItem('email')
    }

    static isAuthenticated(){
        const token = localStorage.getItem('token')
        return !!token
    }

    static isAdmin(){
        const userType = localStorage.getItem('userType')
        return userType === 'ADMIN'
    }

    static isUser(){
        const userType = localStorage.getItem('userType')
        return userType === 'USER'
    }

    static adminOnly(){
        return this.isAuthenticated() && this.isAdmin();
    }

}

export default UserService;