import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "/node_modules/bootstrap/dist/js/bootstrap.min.js";
import Products from './Pages/Products.js';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Cart from './Pages/Cart.js';
import ContextProvider from './Features/ContextProvider.js';
import CartSuccess from './Pages/CartSuccess.js';
import CartUpdated from './Pages/CartUpdated.js';
import OrderAdminPage from './Pages/OrderAdminPage.js';
import OrderTokens from './Pages/OrderTokens.js';

function App() {
    return (
        <ContextProvider>
            <BrowserRouter>

                <Routes>
                    <Route path="/" element={<Products />} />
                    <Route path="/cart" element={<Cart />} />
                    <Route path="/CartSuccess/:CartID/:userID" element={<CartSuccess />} />
                    <Route path="/OrderTokens/:CartID/:userID" element={<OrderTokens />} />
                    <Route path="/CartUpdated/:CartID/:userID" element={<CartUpdated />} />
                    <Route path="/OrderAdminPage" element={<OrderAdminPage />} />
                </Routes>
            </BrowserRouter>
        </ContextProvider>
    );
}

export default App;
