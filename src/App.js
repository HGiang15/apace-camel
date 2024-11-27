import './App.css';
import Home from './container/Home/Home'; 
import { Routes, Route } from "react-router-dom";
import Products from "./container/Products/Products";
import Orders from "./container/Orders/Orders";
import OrderDetail from "./container/OrderDetail/OrderDetail";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/products" element={<Products />} />
      <Route path="/orders" element={<Orders />} />
      <Route path="/superset" element={<OrderDetail />} />
    </Routes>
  );
}

export default App;
