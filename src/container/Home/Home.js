import React from 'react';
import Button from '../../components/Button/Button'; 

import './Home.css'; 

const Home = () => {
  return (
    <div className="home-container">
      <h1>Cụm 1 - Apache Camel</h1>
      <Button text="Sản phẩm" />
      <Button text="Đơn hàng" />
      <Button text="Superset" />
    </div>
  );
};

export default Home;
