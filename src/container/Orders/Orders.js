import React, { useState, useEffect } from "react";
import { useNavigate, Link } from "react-router-dom";
import axios from "axios";
import "./Orders.css";

const Orders = () => {
    const navigate = useNavigate();
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // Fix cứng tạm
    const fixedOrders = [
        { id: 1, date: "26/11/2024", total: "200.000" },
        { id: 2, date: "27/12/2024", total: "250.000" },
        { id: 3, date: "28/10/2024", total: "300.000" },
        { id: 4, date: "29/09/2024", total: "150.000" },
        { id: 5, date: "20/07/2024", total: "500.000" },
        { id: 6, date: "21/06/2024", total: "700.000" },
        { id: 7, date: "22/03/2024", total: "100.000" },
        { id: 8, date: "23/06/2024", total: "350.000" },
        { id: 9, date: "23/06/2024", total: "350.000" },
    ];

    // Get API
    useEffect(() => {
        const fetchOrders = async () => {
            try {
                setLoading(true);

                setTimeout(() => {
                    // const response = await axios.get("api/orders");
                    // setOrders(response.data);

                    setOrders(fixedOrders);

                    setLoading(false);
                }, 500);
            } catch (error) {
                setError("Không thể lấy dữ liệu đơn hàng");
                setLoading(false);
            }
        };

        fetchOrders();
    }, []);

    const handleGoBack = () => {
        navigate(-1);
    };

    const handleDelete = (orderId) => {
        setOrders(orders.filter((order) => order.id !== orderId));
    };

    if (loading) {
        return (
            <div className="loading-container">
                <div className="loading-text">Đang tải...</div>
            </div>
        );
    }

    if (error) {
        return <div>{error}</div>;
    }

    return (
        <div className="orders-container">
            <div className="orders-grid">
                {orders.map((order) => (
                    <div className="order-card" key={order.id}>
                        <h3>Đơn hàng #{order.id}</h3>
                        <p>Ngày Order: {order.date}</p>
                        <p>Thành tiền: {order.total} VNĐ</p>
                        <div className="order-actions">
                            <Link to={`/orderdetail/${order.id}`}>
                                <button className="order-detail-btn">Xem chi tiết</button>
                            </Link>
                            <button className="order-delete-btn" onClick={() => handleDelete(order.id)}>
                                Xóa
                            </button>
                        </div>
                    </div>
                ))}
            </div>

            <button className="back-btn" onClick={handleGoBack}>
                Quay lại
            </button>
        </div>
    );
};

export default Orders;
