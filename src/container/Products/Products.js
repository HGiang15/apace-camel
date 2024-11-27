import React,{ useState } from 'react'
import Button from "../../components/Button/Button";
import { useNavigate } from "react-router-dom"; 
import "./Products.css";

const Products = () => {
  const navigate = useNavigate();

    const handleBackClick = () => {
        navigate('/');  
    }
    const [clicked, setClicked] = useState(Array(9).fill(false)); 
  
    const handleClick = (index) => {
      const newClicked = [...clicked];
      newClicked[index] = !newClicked[index]; 
      setClicked(newClicked);
    }

    const handlePayment = () => {
      setClicked(Array(9).fill(false)); 
    }
  return (
    <div className='product-container'>
      <div className='nav' onClick={handleBackClick} >
        <div className='icon-back'>
          <svg width="30" height="40" viewBox="0 0 56 59" fill="none" xmlns="http://www.w3.org/2000/svg" >
            <path d="M31.36 39.3546L21.9734 29.5L31.36 19.6455M54.6667 29.5C54.6667 44.918 42.7276 57.4167 28 57.4167C13.2724 57.4167 1.33337 44.918 1.33337 29.5C1.33337 14.0821 13.2724 1.58337 28 1.58337C42.7276 1.58337 54.6667 14.0821 54.6667 29.5Z" stroke="#292D32" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"/>
          </svg>
        </div>
      </div>
      <div className='product-list'>
        {['Ô long sữa', 'Ô long nhài sữa', 'Gạo khói', 'Ô long hoa sen', 'Ô long nướng', 'Ô long nếp', 'Ô long matcha', 'Ô long hoa dành dành', 'Ô long lụa vàng'].map((text, index) => (
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
            
            {text}
          </button>
        ))}
      </div>
      <div className='footer'>
        <div className='priceTotal'>
          <p>Số tiền:</p>
          <p></p>
        </div>
        <div className='pay'>
        <Button text="Thanh toán" onClick={handlePayment} />
        </div>
      </div>

    </div>
    
  )
}

export default Products