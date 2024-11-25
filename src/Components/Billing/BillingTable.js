import React, { useState, useEffect } from 'react';
import axios from 'axios';
import AdminNav from '../AdminNav'
import { Link, useNavigate } from 'react-router-dom';

function BillingTable() {
  const [billingData, setBillingData] = useState([]);

  // currency format
  const formatCurrency = (value) => {
    if (value === 0) return '0'; // Explicitly return '0' if value is exactly 0
    if (!value) return ''; // If value is falsy (e.g., null, undefined, empty), return empty string
    const formatter = new Intl.NumberFormat('en-LK', {
      style: 'currency',
      currency: 'LKR',
      minimumFractionDigits: 2,
    });
    return formatter.format(value);
  };

  useEffect(() => {
    axios
      .get('http://localhost:8080/FreshSip/admin/bill')
      .then((response) => {
        console.log('Fetched billing data:', response.data);
        setBillingData(response.data);
      })
      .catch((error) => {
        console.error('There was an error fetching the data!', error);
      });
  }, []);
console.log(billingData)
  const handleDelete = (billId) => {
    axios
      .delete(`http://localhost:8080/FreshSip/admin/${billId}`)
      .then(() => {
        setBillingData(billingData.filter((bill) => bill.billId !== billId));
        alert('Billing record deleted successfully!');
      })
      .catch((error) => {
        console.error('There was an error deleting the bill!', error);
      });
  };

  return (
    <div>
      <AdminNav></AdminNav>
    
    <div className="container">
      <h2>Billing Records</h2>
      <Link to={"/billform"} className="btn " id='bti2'>Bill</Link>
      <table className="table table-striped">
        <thead>
          <tr>
            <th>Bill ID</th>
            <th>Date</th>
            <th>Time</th>
            <th>Cart ID</th>
            <th>Full Total</th>
            <th>Cash</th>
            <th>Balance</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {billingData.map((bill) => (
            <tr key={bill.billId}>
              <td>{bill.billId}</td>
              <td>{bill.date}</td>
              <td>{bill.time}</td>
              <td>{bill.cartId}</td>
              <td>{formatCurrency(bill.fullTotal)}</td>
              <td>{formatCurrency(bill.cash)}</td>
              <td>{formatCurrency(bill.balance)}</td> {/* Balance will show 0 if it's zero */}
              <td>
                <button
                  onClick={() => handleDelete(bill.billId)}
                  className="btn"
                  style={{
                    backgroundColor: 'gray',
                    color: 'black',
                    padding: '5px 10px',
                    fontSize: '14px',
                    border: '2px solid black',
                  }}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
    </div>
  );
}

export default BillingTable;
