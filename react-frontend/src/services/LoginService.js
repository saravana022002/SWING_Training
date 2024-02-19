import {request} from '../axios_helper';

const LOGIN_API_URL = "/api/v1/login"; // Adjust the URL accordingly

class LoginService {

loginUser(credentials) {
    return request("POST", LOGIN_API_URL, credentials);
}

    // Additional methods related to login could be added here
}

export default new LoginService();
