import axios from 'axios';

const SIGNUP_API_BASE_URL = "http://localhost:8080/api/v1/signup"; // Adjust the URL accordingly

class LoginService {

signup(signupData) {
        return axios.post(SIGNUP_API_BASE_URL, signupData);
}

    // Additional methods related to login could be added here
}

export default new LoginService();
