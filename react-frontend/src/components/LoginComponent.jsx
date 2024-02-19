import React, { Component } from 'react';
import LoginService from '../services/LoginService';
import { setAuthToken } from '../axios_helper';

class LoginComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
        userName: '',
        password: '',
        isAuthenticated : false
    };
  }

  handleUserNameChange =  (event) => {
    this.setState({ userName: event.target.value });
  };

  handlePasswordChange = (event) => {
    this.setState({ password: event.target.value });
  };

  handleSubmit = async (e) => {

    e.preventDefault();
    let credentials = {userName: this.state.userName, password: this.state.password};
    console.log('credentials => ' + JSON.stringify(credentials));
    try {
      const res = await LoginService.loginUser(credentials).then((response) => {
        setAuthToken(response.data.token)
      });
      const { isAuthenticated } = res.data;
      this.setState({
        credentials: res.data,
        isAuthenticated
      });
      if (isAuthenticated) {
        this.props.history.push('/employees');
      } else {
        alert('Invalid credentials. Please try again.');
      }
  }catch(error){
    console.error('Error during signup:', error);
  }
  };

  render() {
    const { userName, password } = this.state;

    return (
      <div>
        <h1>Login</h1>
        <form onSubmit={this.handleSubmit}>
          <div>
            <label>Username:</label>
            <input
              type="text"
              value={userName}
              onChange={this.handleUserNameChange}
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
