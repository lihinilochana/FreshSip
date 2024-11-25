import React, { useState, useEffect, useCallback } from 'react';
import axios from 'axios';
import './BillingForm.css';
import {  useNavigate } from 'react-router-dom';

function BillingForm() {
  const navigate = useNavigate();
  const [date, setDate] = useState('');
  const [time, setTime] = useState('');
  const [cartId, setCartId] = useState('');
  const [fullTotal, setFullTotal] = useState('');
  const [cash, setCash] = useState('');
  const [balance, setBalance] = useState('');

  const [billId, setBillId] = useState('');
  const [isUpdate, setIsUpdate] = useState(false);

  const formatCurrency = (value) => {
    if (!value) return '';
    const formatter = new Intl.NumberFormat('en-US', {
      style: 'currency',
      currency: 'LKR',
      minimumFractionDigits: 2,
    });
    return formatter.format(value);
  };

  const parseCurrency = (value) => {
    if (!value) return 0;
    return parseFloat(value.replace(/[^0-9.-]+/g, '')); // to remove currency symbol
  };

  const calculateBalance = useCallback(() => {
    const cashValue = parseCurrency(cash);
    const fullTotalValue = parseCurrency(fullTotal);
    const calculatedBalance = cashValue - fullTotalValue;

    // Ensure balance is zero if the calculated balance is 0
    setBalance(calculatedBalance === 0 ? '0' : formatCurrency(calculatedBalance));
  }, [cash, fullTotal]);

  useEffect(() => {
    if (fullTotal && cash) {
      calculateBalance();
    }
  }, [fullTotal, cash, calculateBalance]);

  const fetchFullTotal = async (cartId) => {
    if (!cartId) {
      console.error('Cart ID is empty');
      setFullTotal(''); 
      return;
    }

    try {
      console.log('Fetching total for Cart ID:', cartId);
      const totalResponse = await axios.get(`http://localhost:8080/FreshSip/Order/total/${cartId}`);
      console.log('API Response:', totalResponse.data);

      const fullTotalValue = totalResponse.data;
      if (fullTotalValue !== null && fullTotalValue !== undefined) {
        setFullTotal(formatCurrency(fullTotalValue));
      } else {
        alert('No total found for this Cart ID!');
        setFullTotal('');
      }
    } catch (error) {
      console.error('Error fetching fullTotal:', error);
      alert('Error fetching total for Cart ID.');
      setFullTotal('');
    }
  };

  
  useEffect(() => {
    if (cartId) {
      fetchFullTotal(cartId);
    } else {
      setFullTotal(''); 
    }
  }, [cartId]);

  const handleSubmit = (e) => {
    e.preventDefault();

    const billingData = {
      date:date,
      time:time,
      cartId,
      fullTotal: parseCurrency(fullTotal),
      cash: parseCurrency(cash),
      balance: parseCurrency(balance),
    };
console.log(billingData)
    if (!isUpdate) {
      axios
        .post('http://localhost:8080/FreshSip/admin/bill', billingData)
        .then(() => {
          alert('Billing record added successfully');
          resetForm();
          navigate("/billrecords")
        })
        .catch((error) => {
          console.error('There was an error!', error);
        });
    }
  };

  const handleUpdate = (e) => {
    e.preventDefault();

    if (!billId) {
      alert('Bill ID is required.');
      return;
    }

    const updatedBalance = parseCurrency(cash) - parseCurrency(fullTotal);

    const updatedBillingData = {
      cash: parseCurrency(cash),
      balance: updatedBalance,
    };

    axios
      .put(`http://localhost:8080/FreshSip/admin/${billId}`, updatedBillingData)
      .then(() => {
        alert('Cash and Balance updated successfully');
        navigate("/billrecords");
        resetForm();
      })
      .catch((error) => {
        console.error('Error updating cash and balance:', error.response?.data || error);
        alert('Failed to update the bill.');
      });
  };
console.log("t1",date)
console.log("t2",time)
  const fetchBillDetails = (billId) => {
    axios
      .get(`http://localhost:8080/FreshSip/admin/${billId}`)
      .then((response) => {
        const bill = response.data;
        if (bill) {
          setDate(bill.date);
          setTime(bill.time);
          setCartId(bill.cartId);
          setFullTotal(formatCurrency(bill.fullTotal));
          setCash(formatCurrency(bill.cash));
          setBalance(formatCurrency(bill.balance));
          setIsUpdate(true);
        } else {
          alert('Bill not found!');
        }
      })
      .catch((error) => {
        console.error('Error fetching bill:', error);
        alert('Error fetching bill details.');
      });
  };

  const resetForm = () => {
    setDate('');
    setTime('');
    setCartId('');
    setFullTotal('');
    setCash('');
    setBalance('');
    setBillId('');
    setIsUpdate(false);
  };

  return (
    <div className="container">
      <div className="billing-box">
        <h2 className="form-heading">BILL</h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="date" className="form-label">Date</label>
            <input
              type="date"
              id="date"
              className="form-control"
              value={date}
              onChange={(e) => setDate(e.target.value)}
              disabled={isUpdate}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="time" className="form-label">Time</label>
            <input
              type="time"
              id="time"
              className="form-control"
              value={time}
              onChange={(e) => setTime(e.target.value)}
              disabled={isUpdate}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="cartId" className="form-label">Cart ID</label>
            <input
              type="text"
              id="cartId"
              className="form-control"
              value={cartId}
              onChange={(e) => {
                setCartId(e.target.value);
              }}
              disabled={isUpdate}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="fullTotal" className="form-label">Full Total</label>
            <input
              type="text"
              id="fullTotal"
              className="form-control"
              value={fullTotal}
              readOnly
            />
          </div>
          <div className="mb-3">
            <label htmlFor="cash" className="form-label">Cash</label>
            <input
              type="text"
              id="cash"
              className="form-control"
              value={cash}
              onChange={(e) => setCash(e.target.value)}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="balance" className="form-label">Balance</label>
            <input
              type="text"
              id="balance"
              className="form-control"
              value={balance}
              readOnly
            />
          </div>
          <button
            type="submit"
            className="btn btn-gray"
            style={{
              backgroundColor: 'gray',
              color: 'black',
              padding: '10px 20px',
              fontSize: '16px',
              border: '2px solid black',
            }}
           
          >
            Submit
          </button>
        </form>
        <div className="update-section" style={{ marginTop: '20px' }}>
          <h3>Update Existing Bill</h3>
          <div className="mb-3">
            <label htmlFor="billId" className="form-label">Bill ID</label>
            <input
              type="text"
              id="billId"
              className="form-control"
              value={billId}
              onChange={(e) => setBillId(e.target.value)}
              placeholder="Enter Bill ID to update"
            />
            <button
              type="button"
              className="btn btn-gray"
              style={{
                backgroundColor: 'gray',
                color: 'black',
                padding: '5px 10px',
                fontSize: '14px',
                border: '1px solid black',
                marginTop: '5px',
              }}
              onClick={() => fetchBillDetails(billId)}
            >
              Load Bill
            </button>
          </div>
          <button
            type="button"
            onClick={handleUpdate}
            className="btn btn-gray"
            style={{
              backgroundColor: 'gray',
              color: 'black',
              padding: '10px 20px',
              fontSize: '16px',
              border: '2px solid black',
            }}
           
          >
            Update Bill
          </button>
        </div>
      </div>
    </div>
  );
}

export default BillingForm;
