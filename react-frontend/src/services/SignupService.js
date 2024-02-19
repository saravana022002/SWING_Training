import {request} from '../axios_helper';

const SIGNUP_API_URL = "/api/v1/signup"; // Adjust the URL accordingly

class LoginService {

signup(signupData) {
    return request("POST", SIGNUP_API_URL, signupData);
}
}

export default new LoginService();
