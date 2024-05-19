import React from 'react';
import { Route, Redirect } from 'react-router-dom';

import ListEmployeeComponent from '../components/ListEmployeeComponent';
import ListVendorComponent from '../components/ListVendorComponent';
import CreateEmployeeComponent from '../components/CreateEmployeeComponent';
import UpdateEmployeeComponent from '../components/UpdateEmployeeComponent';
import ViewEmployeeComponent from '../components/ViewEmployeeComponent';
import CreateVendorComponent from '../components/CreateVendorComponent';
import UpdateVendorComponent from '../components/UpdateVendorComponent';
import ViewVendorComponent from '../components/ViewVendorComponent';
import HomeComponent from '../components/HomeComponent';
import LoginComponent from '../components/LoginComponent';
import SignUpComponent from '../components/SignUpComponent';

const isAuthenticated = () => {
  const authToken = localStorage.getItem('auth_token');
  return authToken ? true : false; 
};

const requireAuth = (Component) => {
  return isAuthenticated() ? Component : () => <Redirect to="/login" />;
};


// Define your routes in an array
const routes = [
  {
    path: '/',
    exact: true,
    component: () => (
      isAuthenticated() ? <Redirect to="/home" /> : <Redirect to="/login" />
    ),
  },
  {
    path: '/login',
    exact: true,
    component: () => (
      isAuthenticated() ? <Redirect to="/home" /> : <LoginComponent />
    ),
  },
  {
    path: '/signup',
    component: SignUpComponent,
  },
  {
    path: '/home',
    component: requireAuth(HomeComponent),
  },
  {
    path: '/employees',
    component: requireAuth(ListEmployeeComponent),
  },
  {
    path: '/add-employee/:id',
    component: requireAuth(CreateEmployeeComponent),
  },
  {
    path: '/view-employee/:id',
    component:  requireAuth(ViewEmployeeComponent),
  },
  {
    path: '/vendors',
    component:  requireAuth(ListVendorComponent),
  },
  {
    path: '/add-vendor/:id',
    component: requireAuth(CreateVendorComponent),
  },
  {
    path: '/view-vendor/:id',
    component:  requireAuth(ViewVendorComponent),
  }
];

export default routes;
