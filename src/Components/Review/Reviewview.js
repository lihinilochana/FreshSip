import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import './review.css';
import ReviewService from '../../Service/ReviewService';

export default function ReviewView() {
  const navigate = useNavigate();
  const { email } = useParams();
  const [review, setReview] = useState([]);

  const handleOrderClick5 = () => {
    navigate(`/Review/${email}`);
  };

  useEffect(() => {
    // Fetch all reviews
    const fetchReviewDetails = async () => {
      try {
        const response = await fetch(`http://localhost:8080/FreshSip/adminUser/allReviews`);
        if (!response.ok) {
          throw new Error('Failed to fetch review data');
        }
        const reviewData = await response.json();
        setReview(reviewData);
      } catch (error) {
        console.error('Error fetching review data:', error);
      }
    };

    fetchReviewDetails();
  }, []);
console.log("f",review)
  const handleEditClick = (review) => {
    if (email === review.email) {
      const reviewID = review.reviewId;
      navigate(`/Review_edit/${email}/${reviewID}`);
    } else {
      alert('You can only edit your own reviews.');
    }
  };

  const handleDeleteClick = (review) => {
    const reviewID = review.reviewId;
    if (email === review.email) {
      const confirmed = window.confirm('Do you want to delete?');
      if (confirmed) {
        ReviewService.deleteReviewById(reviewID)
          .then(() => {
            console.log('Review deleted');
            navigate(`/Reviewuser/${email}`);
            setReview((prevReviews) => prevReviews.filter((r) => r.id !== reviewID));
          })
          .catch((error) => {
            console.error('Error deleting review:', error);
          });
      }
    } else {
      alert('You can only delete your own reviews.');
    }
  };

  return (
    <div>
      <p className="heading2_r">Reviews</p>
      <button onClick={handleOrderClick5} className="button1_r" type="button">
        Add Review
      </button>
      <div className="review-container">
        {review.map((review) => (
          <div className="Box2_r" key={review.reviewId}>
            <h3>Name:</h3>
            <p className="content1">{review.email}</p> <p className="content1">{review.review.reviewId}</p>
            <h3>Comment:</h3>
            <p className="content2">{review.review}</p>
            <p className="set1">
              <button className="btn btn-success btn-lg btn-space" onClick={() => handleEditClick(review)} type="button">
                Edit
              </button>
              <button className="btn btn-danger btn-lg" onClick={() => handleDeleteClick(review)} type="button">
                Delete
              </button>
            </p>
          </div>
        ))}
      </div>
    </div>
  );
}
