import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080' 
axios.defaults.headers.post["Content-Type"] = 'application/json'

export const getAuthToken = () => {
    const authToken = window.localStorage.getItem("auth_token");
    return authToken ? authToken : null;
}

export const setAuthToken = (token) => {
    window.localStorage.setItem("auth_token", token);
}

export const clearAuthToken = () => {
    localStorage.removeItem('auth_token');
};  

export const request = (method, url, data) => {
    const authToken = getAuthToken();

    console.log("authToken:", authToken);

    const headers = authToken !== null ? { "Authorization": `Bearer ${authToken}`} : {};

    return axios({
        method: method,
        headers: headers,
        url: url,
        data: data
    }); 
};
