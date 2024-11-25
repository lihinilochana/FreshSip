import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "/node_modules/bootstrap/dist/js/bootstrap.min.js";
import Products from './Pages/Products.js';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Cart from './Pages/OrderPages/Cart.js';
import ContextProvider from './Features/ContextProvider.js';
import CartSuccess from './Pages/OrderPages/CartSuccess.js';
import CartUpdated from './Pages/OrderPages/CartUpdated.js';
import OrderAdminPage from './Pages/OrderPages/OrderAdminPage.js';
import OrderTokens from './Pages/OrderPages/OrderTokens.js';
import About from './Pages/About.js';


import AddItem from './Components/Items/AddItem'; 
import EditItems from './Pages/ItemPages/EditItems';
import AdminItem from './Pages/ItemPages/AdminItem';
import ItemTable from './Components/Items/ItemTable';


import UserRegistration from "./Components/Registration/UserRegistration";
import UserLogin from "./Components/Registration/UserLogin";
import UserProfile from "./Components/Registration/UserProfile";
import UpdateProfile from "./Components/Registration/UpdateProfile";
import AdminRegistration from "./Components/Registration/AdminRegistration";
import GetAllUsers from "./Components/Registration/AllUsers";

import BillingForm from './Components/Billing/BillingForm';
import BillingTable from './Components/Billing/BillingTable';

import Review from "./Components/Review/Review";
import Reviewuser from "./Pages/ReviewPage/Reviewuser";
import ReviewEdit from "./Components/Review/ReviewEdit";



function App() {
    return (
        <ContextProvider>
            <BrowserRouter>

                <Routes>
                    <Route path="/" element={<Products />} />
                    <Route path="/:email" element={<Products />} />
                    <Route path="/cart/:email" element={<Cart />} />
                    <Route path="/CartSuccess/:CartID/:userID" element={<CartSuccess />} />
                    <Route path="/OrderTokens/:CartID/:userID" element={<OrderTokens />} />
                    <Route path="/CartUpdated/:CartID/:userID" element={<CartUpdated />} />
                    <Route path="/OrderAdminPage" element={<OrderAdminPage />} />
                    <Route path="/About/:email" element={<About />} />


                    <Route path="/EditItems/:Id" element={<EditItems />} />
                    <Route path="/AdminItem" element={<AdminItem/>}/>
                    <Route path="/AddItem" element={<AddItem />} />
                    <Route path="/" element={<AddItem/>}/>
                    <Route path="/ItemTable" element={<ItemTable/>}/>


                    <Route path="/UserRegistration" element={<UserRegistration />} />
                    <Route path="/AdminRegistration" element={<AdminRegistration />} />
                    <Route path="/Login" element={<UserLogin/>}/>
                    <Route path="/Profile/:email" element={<UserProfile/>}/>
                    <Route path="/UpdateProfile/:email" element={<UpdateProfile/>}/>
                    <Route path="/AllUsers" element={<GetAllUsers/>}/>

                    <Route path="/billform" element={<BillingForm />} />
                    <Route path="/billrecords" element={<BillingTable />} />


                    <Route path="/Review/:email" element={<Review/>}/>
                    <Route path="/ReviewUser" element={<Reviewuser/>}/>
                    <Route path="/ReviewUser/:email" element={<Reviewuser/>}/>
                    <Route path="/Review_edit/:email/:reviewID" element={<ReviewEdit/>}/>

                </Routes>
            </BrowserRouter>
        </ContextProvider>
    );
}

export default App;
