import React, { useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";
import Button from "../../components/Button/Button";
import "./Products.css";

const Products = () => {
  const navigate = useNavigate();
  const handleBackClick = () => {
    navigate('/');
  }
  const [clicked, setClicked] = useState(Array(9).fill(false));
  const [totalMoney, setTotalMoney] = useState(0);
  const [orders, setOrders] = useState([]);
  const [products, setProducts] = useState();
  const handleClick = (index) => {
    const newClicked = [...clicked];
    newClicked[index] = !newClicked[index];
    setClicked(newClicked);
  }

  const handlePayment = () => {
    setClicked(Array(9).fill(false));
    const orderData = {
      "date":new Date().toLocaleDateString(),
      "totalMoney":totalMoney,
      "products":orders
    }
    fetch("http://localhost:8080/api/camel/add/order",
      {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(orderData),
      })
      .then(response => {
        if (response.ok) {
          return response.json();
        }
        throw new Error('Failed to add order');
      })
      .then(data => {
        console.log('Đơn hàng đã được thêm thành công:', data);
        navigate(`/orderdetail/${orders.id}`);
      })
      .catch(error => {
        console.error("Lỗi khi thêm đơn hàng:", error);
      });
  }

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/camel/products/all");
        const productsData = await response.json();
        // console.log(productsData);
        setProducts(productsData);
        setClicked(Array(productsData.length).fill(false));
      } catch (error) {
        console.error("Lỗi khi lấy sản phẩm: ", error);
      }
    };
    fetchProducts();
  }, []);

  useEffect(() => {
    if (products) {
      const selectedProduct = products.filter((_, index) => { return clicked[index] === true; });
      const sum = selectedProduct.reduce((total, product) => total + Number(product.price), 0);
      setOrders(selectedProduct);
      setTotalMoney(sum);
    }
  }, [clicked, products])

  return (
    <div className='product-container'>
      <div className='nav' onClick={handleBackClick} >
        <div className='icon-back'>
          <svg width="30" height="40" viewBox="0 0 56 59" fill="none" xmlns="http://www.w3.org/2000/svg" >
            <path d="M31.36 39.3546L21.9734 29.5L31.36 19.6455M54.6667 29.5C54.6667 44.918 42.7276 57.4167 28 57.4167C13.2724 57.4167 1.33337 44.918 1.33337 29.5C1.33337 14.0821 13.2724 1.58337 28 1.58337C42.7276 1.58337 54.6667 14.0821 54.6667 29.5Z" stroke="#292D32" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" />
          </svg>
        </div>
      </div>
      <div className='product-list'>
        {products && products.map((product, index) => (
          <button
            key={index}
            onClick={() => handleClick(index)}
            style={{
              backgroundColor: clicked[index] ? '#DEB887' : '#021331',
              height: '80px',
              color: 'white',
              padding: '10px 20px',
              border: 'none',
              borderRadius: '5px',
              cursor: 'pointer',
              transition: 'all 0.2s ease'
            }}
          >
            {product.name}
          </button>
        ))}
      </div>
      <div className='footer'>
        <div className='priceTotal'>
          <p>Số tiền: {totalMoney}k VND</p>
        </div>
        <div className='pay'>
          <Button text="Thanh toán" onClick={handlePayment} />
        </div>
      </div>

    </div>

  )
}

export default Products