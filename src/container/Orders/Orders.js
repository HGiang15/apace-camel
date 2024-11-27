import React from 'react';
import './Orders.css';

const Orders = () => {
  const orders = [
    { id: 1, date: '26/11/2024', total: '200.000vnd' },
    { id: 2, date: '26/11/2024', total: '200.000vnd' },
    { id: 3, date: '26/11/2024', total: '200.000vnd' },
    { id: 4, date: '26/11/2024', total: '200.000vnd' },
    { id: 5, date: '26/11/2024', total: '200.000vnd' },
    { id: 6, date: '26/11/2024', total: '200.000vnd' },
    { id: 7, date: '26/11/2024', total: '200.000vnd' },
    { id: 8, date: '26/11/2024', total: '200.000vnd' },
  ];

  return (
    <div className="orders-container">
      <div className="orders-grid">
        {orders.map((order) => (
          <div className="order-card" key={order.id}>
            <h3>Đơn hàng #{order.id}</h3>
            <p>Ngày Order: {order.date}</p>
            <p>Thành tiền: {order.total}</p>
            <button className="order-delete">Xóa</button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Orders;
