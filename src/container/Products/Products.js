import React,{ useState } from 'react'
import Button from "../../components/Button/Button";
import "./Products.css";

const Products = () => {

    const [clicked, setClicked] = useState(Array(9).fill(false)); // Array để theo dõi trạng thái của mỗi button
  
    const handleClick = (index) => {
      const newClicked = [...clicked];
      newClicked[index] = !newClicked[index]; // Đảo ngược trạng thái của nút đã click
      setClicked(newClicked);
    }
     // Hàm xử lý khi click vào nút "Thanh toán"
    const handlePayment = () => {
      setClicked(Array(9).fill(false)); // Đặt lại trạng thái tất cả các nút về false
    }
  return (
    <div className='product-container'>
      <div className='icon-back'><i>back</i></div>
      <div className='product-list'>
        {['Ô long sữa', 'Ô long nhài sữa', 'Gạo khói', 'Ô long hoa sen', 'Ô long nướng', 'Ô long nếp', 'Ô long matcha', 'Ô long hoa dành dành', 'Ô long lụa vàng'].map((text, index) => (
          <button
            key={index}
            onClick={() => handleClick(index)}
            style={{
              backgroundColor: clicked[index] ? '#DEB887' : '#021331',
              height: '80px', // Màu sắc nút thay đổi theo trạng thái
              color: 'white',
              padding: '10px 20px',
              border: 'none',
              borderRadius: '5px',
              cursor: 'pointer',
              transition: 'all 0.2s ease' /* Thêm hiệu ứng mượt */

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