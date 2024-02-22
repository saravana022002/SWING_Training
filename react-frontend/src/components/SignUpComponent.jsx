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
        email: '',
        phone: '',
        isCreated : false,
        isValidEmail : true,
        doPasswordsMatch : true
    };
  }

  handleUsernameChange = (event) => {
    this.setState({ userName: event.target.value });
  };

  handlePasswordChange = (event) => {
    this.setState({ password: event.target.value });
  };
  handleCopyPasswordChange = (event) => {
    const enteredCopyPassword = event.target.value;
    this.setState({ copyPassword: enteredCopyPassword });

    // Check if the entered password and copyPassword match
    const doPasswordsMatch = this.state.password === enteredCopyPassword;

    // Update the validation state or handle accordingly
    this.setState({ doPasswordsMatch: doPasswordsMatch });
 };
  handleEmailChange = (event) => {
    const enteredEmail = event.target.value;
    this.setState({ email: enteredEmail });
  
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    
    const isValidEmail = emailPattern.test(enteredEmail);
  
    this.setState({ isValidEmail: isValidEmail });
  };
  
  handlePhoneChange = (event) => {
    this.setState({ phone: event.target.value });
  };

  handleSubmit = async (e) => {
    e.preventDefault();
    const signupData = {
        userName: this.state.userName,
        password: this.state.password,
        email: this.state.email,
        phone: this.state.phone
    };

    console.log('signupData => ' + JSON.stringify(signupData));

    try {
        await SignupService.signup(signupData);
        this.props.history.push('/');
    } catch (error) {
        console.error('Error during signup:', error);
        // Handle error appropriately (e.g., show an error message)
    }
};


  render() {
    const { userName, password, copyPassword, email, phone } = this.state;

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
          {!this.state.doPasswordsMatch && <p style={{ color: 'red' }}>Passwords do not match.</p>}
          <div>
            <label>Email:</label>
            <input
              type="text"
              value={email}
              onChange={this.handleEmailChange}
            />
          </div>
          {!this.state.isValidEmail && <p style={{ color: 'red' }}>Please enter a valid email address.</p>}
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
