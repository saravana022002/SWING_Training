import React, { Component } from 'react';
import SignupService from '../services/SignupService';
import { setAuthToken } from '../axios_helper';

class SignUpComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
        userName: '',
        password: '',
        copyPassword: '',
        phone: '',
        isCreated : false
    };
  }

  handleUsernameChange = (event) => {
    this.setState({ userName: event.target.value });
  };

  handlePasswordChange = (event) => {
    this.setState({ password: event.target.value });
  };
  handleCopyPasswordChange = (event) => {
    this.setState({ copyPassword: event.target.value });
  };
  handlePhoneChange = (event) => {
    this.setState({ phone: event.target.value });
  };

  handleSubmit = async (e) => {
    e.preventDefault();
   
    const { userName, password, copyPassword, phone } = this.state;

    // Check if the passwords match
    if (password !== copyPassword) {
        alert('Passwords do not match. Please re-enter.');
        return;
    }
    const signupData = {
        userName: this.state.userName,
        password: this.state.password,
        phone: this.state.phone
    };

    console.log('signupData => ' + JSON.stringify(signupData));

    try {
        const res = await SignupService.signup(signupData).then((response) => {
          setAuthToken(response.data.token)
        });
        const { isCreated } = res.data;

        this.setState({
            signupData: res.data,
            isCreated
        });

        if (isCreated) {
            this.props.history.push('/');
        } else {
            alert('Invalid signup credentials. Please try again.');
        }
    } catch (error) {
        console.error('Error during signup:', error);
        // Handle error appropriately (e.g., show an error message)
    }
};


  render() {
    const { userName, password, copyPassword, phone } = this.state;

    return (
      <div>
        <h1>Signup</h1>
        <form onSubmit={this.handleSubmit}>
          <div>
            <label>Username:</label>
            <input
              type="text"
              value={userName}
              onChange={this.handleUsernameChange}
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
            <label>Re-enter Password:</label>
            <input
              type="password"
              value={copyPassword}
              onChange={this.handleCopyPasswordChange}
            />
          </div>
          <div>
            <label>Phone:</label>
            <input
              type="text"
              value={phone}
              onChange={this.handlePhoneChange}
            />
          </div>
          <div>
            <button type="submit">Signup</button>
          </div>
        </form>
      </div>
    );
  }
}

export default SignUpComponent;
