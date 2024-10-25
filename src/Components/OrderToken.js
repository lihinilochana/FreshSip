import { useEffect, useState, useRef } from 'react';
import emailjs from '@emailjs/browser';
import { useNavigate, useParams } from 'react-router-dom';
import './Cart.css';


export const OrderToken = () => {
  const { CartID, userID } = useParams();
  const navigate = useNavigate();
  const [user, setUser] = useState('');
  const [order, setOrder] = useState([]);
  const [time, setTime] = useState("");

  const form = useRef();

  const email = userID;
  const name = user.u_name;


  useEffect(() => {
    const fetchChannelingDetails = async () => {
      try {
        const response = await fetch(`http://localhost:8080/FreshSip/Order/getUserByEmail/${userID}`);
        if (!response.ok) {

          throw new Error('Failed to fetch Channeling data');
        }
        const fetchedChanneling = await response.json();
        setUser(fetchedChanneling);
      } catch (error) {

        console.error('Error fetching Channeling data:', error);
      }
    };

    fetchChannelingDetails();
  }, [userID]);


  useEffect(() => {
    const fetchChannelingDetails = async () => {
      try {
        const response = await fetch(`http://localhost:8080/FreshSip/Order/getCartById/${CartID}`);
        if (!response.ok) {

          throw new Error('Failed to fetch Channeling data');
        }
        const fetchedChanneling = await response.json();
        setOrder(fetchedChanneling);
      } catch (error) {

        console.error('Error fetching Channeling data:', error);
      }
    };

    fetchChannelingDetails();
  }, [userID]);


  const sendEmail = (e) => {
    e.preventDefault();

    emailjs.sendForm('service_8qoxn7c', 'template_y8aagfb', form.current, '6pNawksaQe2m3v005')
      .then(
        () => {
          console.log('SUCCESS!');
          alert('Email sent successfully!');
          navigate("/")
        },
        (error) => {
          console.log('FAILED...', error.text);
          alert('Failed to send email. Please try again.');

        }
      );
    e.target.reset();
  };

  return (
    <div className='Body1'>
      <div className='container d-flex align-items-center justify-content-center' style={{ minHeight: '100vh' }}>
        <div className='col-sm-8 py-2 px-5 offset-2 ' id='set2'>
          <h1 className="heading_a3">Order Token</h1>
          {order ? (
            <form ref={form} onSubmit={sendEmail}>

              <div className='form-group row mb-2' >
                <label htmlFor='impofid' className='col-sm-2 col-form-label'>Name</label>
                <div className='col-sm-10'>
                  <input className='form-control' type="text" name="message1" value={name} readOnly />
                </div>
              </div>
              <div className='form-group row mb-2' >
                <label htmlFor='impofid' className='col-sm-2 col-form-label'>Cart Id</label>
                <div className='col-sm-10'>
                  <input className='form-control' type="text" name="message2" value={order.cart_id || ''} readOnly />
                </div>
              </div>
              <div className='form-group row mb-2' >
                <label htmlFor='impofid' className='col-sm-2 col-form-label'>Full Amount</label>
                <div className='col-sm-10'>
                  <input className='form-control' type="text" name="message3" value={order.full_total || ''} readOnly />
                </div>
              </div>
              <div className='form-group row mb-2' >
                <label htmlFor='impofid' className='col-sm-2 col-form-label'>Email</label>
                <div className='col-sm-10'>
                  <input className='form-control' type="text" name="useremail" value={email} readOnly />
                </div>
              </div>

              <button className="btn" id='bt4' type="submit">Send Message</button>

            </form>
          ) : (
            <p>Loading...</p>
          )}
        </div>
      </div>
    </div>
  );
};

export default OrderToken;
