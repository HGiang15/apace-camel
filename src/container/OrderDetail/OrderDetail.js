import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import "./OrderDetail.css";

const OrderDetail = () => {
    const { orderId } = useParams();
    const navigate = useNavigate();
    const [order, setOrder] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // Fix cứng
    const fixedOrderDetail = {
        id: orderId,
        name: "Sản phẩm 1",
        price: "100.000",
        quantity: 1,
        date: "26/11/2024",
        total: "1.000.000vnd",
    };

    useEffect(() => {
        const fetchOrderDetails = async () => {
            try {
                setLoading(true);

                setTimeout(() => {
                    // const response = await axios.get(`api/orders/${orderId}`);
                    // setOrder(response.data);

                    setOrder(fixedOrderDetail);

                    setLoading(false);
                }, 500);
            } catch (error) {
                setError("Không thể lấy dữ liệu đơn hàng chi tiết");
                setLoading(false);
            }
        };

        fetchOrderDetails();
    }, [orderId]);

    const handleGoBack = () => {
        navigate(-1);
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
        <div className="order-detail">
            <h2>Chi tiết đơn hàng #{order.id}</h2>

            <div className="order-info">
                <div className="order-summary">
                    <p>
                        <strong>Tên sản phẩm:</strong> {order.name}
                    </p>
                    <p>
                        <strong>Giá:</strong> {order.price} VNĐ
                    </p>
                    <p>
                        <strong>Số lượng:</strong> {order.quantity}
                    </p>
                    <p>
                        <strong>Ngày Order:</strong> {order.date}
                    </p>
                    <p>
                        <strong>Tổng cộng:</strong> {order.total}
                    </p>
                </div>
            </div>

            <button className="back-btn" onClick={handleGoBack}>
                Quay lại
            </button>
        </div>
    );
};

export default OrderDetail;
