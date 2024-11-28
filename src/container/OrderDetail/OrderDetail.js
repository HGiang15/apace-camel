import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import "./OrderDetail.css";

const OrderDetail = () => {
    const { orderId } = useParams();
    const navigate = useNavigate();
    const [order, setOrder] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchOrderDetails = async () => {
            try {
                setLoading(true);
                const response = await fetch(`http://localhost:8080/api/camel/get/order/${orderId}`);
                if (!response.ok) {
                    throw new Error("Không thể lấy dữ liệu đơn hàng chi tiết");
                }
                const orderData = await response.json();  // Chuyển đổi dữ liệu từ response thành JSON
                if (!orderData || orderData.length === 0) {  // Kiểm tra nếu không có dữ liệu
                    console.log("Không có đơn hàng nào");
                    setTimeout(() => {
                        navigate("/");
                    }, 5000);
                }
                else {
                    setOrder(orderData);
                    setLoading(false);
                }
            } catch (error) {
                setError("Không thể lấy dữ liệu đơn hàng chi tiết");
                setLoading(false);
            };
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
        <div className="order-detail-container">
            <div className="order-detail">
                <h2>Chi tiết đơn hàng #{order.id}</h2>

                <div className="order-info">
                    <div className="order-summary">
                        <p>
                            <strong>Tên sản phẩm:</strong>
                        </p>
                        <ul>
                            {order && order.products.map((value, index) => (
                                <li key={index} >{value.name} - {value.price}k vnd</li>
                            ))}
                        </ul>
                        <p>
                            <strong>Ngày Order:</strong> {order.date}
                        </p>
                        <p>
                            <strong>Tổng cộng:</strong> {order.totalMoney}k vnd
                        </p>
                    </div>
                </div>

                <button className="back-btn" onClick={handleGoBack}>
                    Quay lại
                </button>
            </div>
        </div>
    );

};

export default OrderDetail;
