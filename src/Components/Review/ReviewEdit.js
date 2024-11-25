import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import './review.css';
import ReviewService from '../../Service/ReviewService';

function ReviewEdit() {
  const {  email, reviewID} = useParams();
  const navigate = useNavigate();
  const [reviews, setReviews] = useState({});
  const [comment, setComment] = useState('');
  
  console.log("u",email)
  console.log("r",reviewID)

  useEffect(() => {
    // Fetch the review details
    const fetchReviewDetails = async () => {
      try {
        const response = await fetch(`http://localhost:8080/FreshSip/user/${reviewID}`);
        if (!response.ok) {
          throw new Error('Failed to fetch review data');
        }
        const reviewData = await response.json();
        setReviews(reviewData);
        setComment(reviewData.reveiw); // Prepopulate the comment
      } catch (error) {
        console.error('Error fetching review data:', error);
      }
    };

    fetchReviewDetails();
  }, [reviewID]);

  const handleChange = (event) => {
    const { value } = event.target;
    setComment(value); // Update the comment
  };
console.log("c",comment)
  const handleSubmit = (event) => {
    event.preventDefault();

    const updatedReviewData = {
      reviewId: reviewID,
      email: email, // Use the existing email
      review:comment, // Updated comment
     
    };

    // Submit the updated review
    console.log("R",updatedReviewData)
    ReviewService.updateReview(reviewID, updatedReviewData)
      .then(() => {
        alert('Successfully edited the review.');
        navigate(`/Reviewuser/${email}`);
      })
      .catch((error) => {
        console.error('Error editing the review:', error);
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
                defaultValue={email} // Email is prefilled and read-only
                readOnly
              />
            </div>
            <div className="input-group mb-5">
              <label className="input-group-text">Comment</label>
              <textarea
                className="form-control col-sm-6"
                style={{ width: '300px', height: '100px' }}
                type="text"
                name="comment"
                value={comment}
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

export default ReviewEdit;
