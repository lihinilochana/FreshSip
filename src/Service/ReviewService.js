import axios from 'axios';

const API_URL = 'http://localhost:8080/FreshSip';
class ReviewService {
    submitReview(reviewData) {
        return axios.post(`${API_URL}/user/review`,reviewData); 
    }
    getReviewById(reviewID) {
        return axios.get(`${API_URL}/user/${reviewID}`);
    }

    updateReview(reviewID,reviewData) {
        return axios.put(`${API_URL}/user/${reviewID}`,reviewData);
    }

    deleteReviewById(reviewID) {
        return axios.delete(`${API_URL}/user/${reviewID}`);
    }
}

export default new ReviewService();