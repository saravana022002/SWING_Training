import React, { Component } from 'react';
import LoginService from '../services/LoginService';
import { setAuthToken } from '../axios_helper';
import '../styles/LoginComponent.css'; // Import your custom CSS file for additional styling

class LoginComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      email: '',
      password: '',
      isAuthenticated: false,
    };
  }

  handleInputChange = (event, field) => {
    this.setState({ [field]: event.target.value });
  };

  handleSubmit = async (e) => {
    e.preventDefault();

    const credentials = {
      email: this.state.email,
      password: this.state.password,
    };

    console.log('credentials => ' + JSON.stringify(credentials));

    try {
      const res = await LoginService.loginUser(credentials);

      console.log('Response:', res.data);

      if (res.data.token) {
        setAuthToken(res.data.token);
        this.setState({
          credentials: res.data,
          isAuthenticated: true,
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
      <div className="login-container">
        <h1>Login</h1>
        <form onSubmit={this.handleSubmit} className="login-form">
          <div>
            <input
              type="text"
              value={email}
              onChange={(e) => this.handleInputChange(e, 'email')}
              placeholder="Email"
            />
          </div>
          <div>
            <input
              type="password"
              value={password}
              onChange={(e) => this.handleInputChange(e, 'password')}
              placeholder="Password"
            />
          </div>
          <div className="button-container">
            <button type="submit">Login</button>
          </div>
          <div className="signup-link-container">
            <a href="http://localhost:3000/signup" rel="noopener noreferrer">
              Create an account?
            </a>
          </div>
        </form>
      </div>
    );
  }
}

export default LoginComponent;
