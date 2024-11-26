import React from 'react';
import './Button.css'; 

const Button = ({ text }) => {
  return (
    <button className="btn">
      <p>{text}</p>
    </button>
  );
};

export default Button;
