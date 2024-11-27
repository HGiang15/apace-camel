import React from 'react';
import './Button.css'; 

const Button = ({ text, onClick }) => {
  return (
    <button className="btn" onClick={onClick}>
      <p>{text}</p>
    </button>
  );
};

export default Button;
