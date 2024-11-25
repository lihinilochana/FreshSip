import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import './review.css';
import ReviewService from '../../Service/ReviewService';

function Review() {
  const { email } = useParams();
  const navigate = useNavigate();
  const [user, setUser] = useState({});
  const [review, setReview] = useState('');
 

  // useEffect(() => {
  //   // Fetch user details to get the email
  //   const fetchUserDetails = async () => {
  //     try {
  //       const response = await fetch(`http://localhost:8080/api/v1/user/getUserByUserId/${userID}`);
  //       if (!response.ok) {
  //         throw new Error('Failed to fetch user data');
  //       }
  //       const userData = await response.json();
  //       setUser(userData); // Store user data, including email
  //     } catch (error) {
  //       console.error('Error fetching user data:', error);
  //     }
  //   };

  //   fetchUserDetails();
  // }, [userID]);

  const handleChange = (event) => {
    const { value } = event.target;
    setReview(value); // Only change the comment
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const reviewData = {
      email:email, // Automatically use the fetched email
      
      review, // The comment the user typed
      
    };

    console.log(reviewData)
    ReviewService.submitReview(reviewData)
      .then(() => {
        alert('Successfully submitted the review.');
        navigate(`/ReviewUser/${email}`);
      })
      .catch((error) => {
        console.error('Error submitting the review:', error);
        alert('Invalid inputs');
      });
  };

  return (
    <div>
      <div className="Box1_r">
        <h1 className="heading_r">Your Review</h1>
        <form onSubmit={handleSubmit}>
          <div className="fo2">
            <div className="input-group mb-5">
              <label className="input-group-text">Your Email</label>
              <input
                className="form-control col-sm-6"
                type="text"
                name="email"
                style={{ width: '300px' }}
                defaultValue={email} // Automatically filled with the fetched email
                
              />
            </div>
            <div className="input-group mb-5">
              <label className="input-group-text">Comment</label>
              <textarea
                className="form-control col-sm-6"
                style={{ width: '300px', height: '100px' }}
                type="text"
                name="review"
                value={review}
                onChange={handleChange}
              />
            </div>
          </div>
          <button className="buttonr1" type="submit">
            Submit
          </button>
        </form>
      </div>
    </div>
  );
}

export default Review;
