import React, { Component } from 'react';
import LoginService from '../services/LoginService';
import { setAuthToken } from '../axios_helper';

class LoginComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
        email: '',
        password: '',
        isAuthenticated : false
    };
  }

  handleEmailChange =  (event) => {
    this.setState({ email: event.target.value });
  };

  handlePasswordChange = (event) => {
    this.setState({ password: event.target.value });
  };

  handleSubmit = async (e) => {
    e.preventDefault();
  
    const credentials = {
      email: this.state.email,
      password: this.state.password
    };
  
    console.log('credentials => ' + JSON.stringify(credentials));
  
    try {
      const res = await LoginService.loginUser(credentials);
  
      console.log('Response:', res.data); // Log the entire response for inspection
  
      if (res.data.token) {
        setAuthToken(res.data.token);
        this.setState({
          credentials: res.data,
          isAuthenticated: true // Update this line based on the actual structure of the response
        });
  
        if (this.state.isAuthenticated) {
          this.props.history.push('/employees');
        }
      } else {
        this.handleAuthenticationFailure();
      }
    } catch (error) {
      console.error('Error during login:', error);
      this.handleAuthenticationFailure();
    }
  };
  
  
  handleAuthenticationFailure = () => {
    alert('Authentication failed. Please try again.');
  };
  

  render() {
    const { email, password } = this.state;

    return (
      <div>
        <h1>Login</h1>
        <form onSubmit={this.handleSubmit}>
          <div>
            <label>Email:</label>
            <input
              type="text"
              value={email}
              onChange={this.handleEmailChange}
            />
          </div>
          <div>
            <label>Password:</label>
            <input
              type="password"
              value={password}
              onChange={this.handlePasswordChange}
            />
          </div>
            <div>
                <div>
                    <button type="submit">Login</button>
                </div>
                <div>
                    <a href="http://localhost:3000/signup" rel="noopener noreferrer">
                        Create an account?
                    </a>
                </div>
            </div>
        </form>
      </div>
    );
  }
}

export default LoginComponent;
