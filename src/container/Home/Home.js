import React from "react";
import { useNavigate } from "react-router-dom";
import Button from "../../components/Button/Button";
import "./Home.css";

const Home = () => {
  const navigate = useNavigate(); 

  const handleClick = (route) => {
    navigate(route);
  };

  return (
    <div className="home-container">
      <h1>Cụm 1 - Apache Camel</h1>
      <div style={{ display: "flex" }}>
        <Button text="Sản phẩm" onClick={() => handleClick("/products")} />
        <Button text="Đơn hàng" onClick={() => handleClick("/orders")} />
        <Button text="Superset" onClick={() => handleClick("/superset")} />
      </div>
    </div>
  );
};

export default Home;
